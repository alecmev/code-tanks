package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractSequentialList;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

@GwtCompatible(serializable=true, emulated=true)
public class LinkedListMultimap
  implements ListMultimap, Serializable
{
  private transient Node head;
  private transient Node tail;
  private transient Multiset keyCount;
  private transient Map keyToKeyHead;
  private transient Map keyToKeyTail;
  private transient Set keySet;
  private transient Multiset keys;
  private transient List valuesList;
  private transient List entries;
  private transient Map map;

  @GwtIncompatible("java serialization not supported")
  private static final long serialVersionUID = 0L;

  public static LinkedListMultimap create()
  {
    return new LinkedListMultimap();
  }

  public static LinkedListMultimap create(int paramInt)
  {
    return new LinkedListMultimap(paramInt);
  }

  public static LinkedListMultimap create(Multimap paramMultimap)
  {
    return new LinkedListMultimap(paramMultimap);
  }

  LinkedListMultimap()
  {
    this.keyCount = LinkedHashMultiset.create();
    this.keyToKeyHead = Maps.newHashMap();
    this.keyToKeyTail = Maps.newHashMap();
  }

  private LinkedListMultimap(int paramInt)
  {
    this.keyCount = LinkedHashMultiset.create(paramInt);
    this.keyToKeyHead = Maps.newHashMapWithExpectedSize(paramInt);
    this.keyToKeyTail = Maps.newHashMapWithExpectedSize(paramInt);
  }

  private LinkedListMultimap(Multimap paramMultimap)
  {
    this(paramMultimap.keySet().size());
    putAll(paramMultimap);
  }

  private Node addNode(Object paramObject1, Object paramObject2, Node paramNode)
  {
    Node localNode1 = new Node(paramObject1, paramObject2);
    if (this.head == null)
    {
      this.head = (this.tail = localNode1);
      this.keyToKeyHead.put(paramObject1, localNode1);
      this.keyToKeyTail.put(paramObject1, localNode1);
    }
    else if (paramNode == null)
    {
      this.tail.next = localNode1;
      localNode1.previous = this.tail;
      Node localNode2 = (Node)this.keyToKeyTail.get(paramObject1);
      if (localNode2 == null)
      {
        this.keyToKeyHead.put(paramObject1, localNode1);
      }
      else
      {
        localNode2.nextSibling = localNode1;
        localNode1.previousSibling = localNode2;
      }
      this.keyToKeyTail.put(paramObject1, localNode1);
      this.tail = localNode1;
    }
    else
    {
      localNode1.previous = paramNode.previous;
      localNode1.previousSibling = paramNode.previousSibling;
      localNode1.next = paramNode;
      localNode1.nextSibling = paramNode;
      if (paramNode.previousSibling == null)
        this.keyToKeyHead.put(paramObject1, localNode1);
      else
        paramNode.previousSibling.nextSibling = localNode1;
      if (paramNode.previous == null)
        this.head = localNode1;
      else
        paramNode.previous.next = localNode1;
      paramNode.previous = localNode1;
      paramNode.previousSibling = localNode1;
    }
    this.keyCount.add(paramObject1);
    return localNode1;
  }

  private void removeNode(Node paramNode)
  {
    if (paramNode.previous != null)
      paramNode.previous.next = paramNode.next;
    else
      this.head = paramNode.next;
    if (paramNode.next != null)
      paramNode.next.previous = paramNode.previous;
    else
      this.tail = paramNode.previous;
    if (paramNode.previousSibling != null)
      paramNode.previousSibling.nextSibling = paramNode.nextSibling;
    else if (paramNode.nextSibling != null)
      this.keyToKeyHead.put(paramNode.key, paramNode.nextSibling);
    else
      this.keyToKeyHead.remove(paramNode.key);
    if (paramNode.nextSibling != null)
      paramNode.nextSibling.previousSibling = paramNode.previousSibling;
    else if (paramNode.previousSibling != null)
      this.keyToKeyTail.put(paramNode.key, paramNode.previousSibling);
    else
      this.keyToKeyTail.remove(paramNode.key);
    this.keyCount.remove(paramNode.key);
  }

  private void removeAllNodes(Object paramObject)
  {
    ValueForKeyIterator localValueForKeyIterator = new ValueForKeyIterator(paramObject);
    while (localValueForKeyIterator.hasNext())
    {
      localValueForKeyIterator.next();
      localValueForKeyIterator.remove();
    }
  }

  private static void checkElement(Object paramObject)
  {
    if (paramObject == null)
      throw new NoSuchElementException();
  }

  public int size()
  {
    return this.keyCount.size();
  }

  public boolean isEmpty()
  {
    return this.head == null;
  }

  public boolean containsKey(Object paramObject)
  {
    return this.keyToKeyHead.containsKey(paramObject);
  }

  public boolean containsValue(Object paramObject)
  {
    NodeIterator localNodeIterator = new NodeIterator();
    while (localNodeIterator.hasNext())
      if (Objects.equal(((Node)localNodeIterator.next()).value, paramObject))
        return true;
    return false;
  }

  public boolean containsEntry(Object paramObject1, Object paramObject2)
  {
    ValueForKeyIterator localValueForKeyIterator = new ValueForKeyIterator(paramObject1);
    while (localValueForKeyIterator.hasNext())
      if (Objects.equal(localValueForKeyIterator.next(), paramObject2))
        return true;
    return false;
  }

  public boolean put(Object paramObject1, Object paramObject2)
  {
    addNode(paramObject1, paramObject2, null);
    return true;
  }

  public boolean remove(Object paramObject1, Object paramObject2)
  {
    ValueForKeyIterator localValueForKeyIterator = new ValueForKeyIterator(paramObject1);
    while (localValueForKeyIterator.hasNext())
      if (Objects.equal(localValueForKeyIterator.next(), paramObject2))
      {
        localValueForKeyIterator.remove();
        return true;
      }
    return false;
  }

  public boolean putAll(Object paramObject, Iterable paramIterable)
  {
    boolean bool = false;
    Iterator localIterator = paramIterable.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      bool |= put(paramObject, localObject);
    }
    return bool;
  }

  public boolean putAll(Multimap paramMultimap)
  {
    boolean bool = false;
    Iterator localIterator = paramMultimap.entries().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      bool |= put(localEntry.getKey(), localEntry.getValue());
    }
    return bool;
  }

  public List replaceValues(Object paramObject, Iterable paramIterable)
  {
    List localList = getCopy(paramObject);
    ValueForKeyIterator localValueForKeyIterator = new ValueForKeyIterator(paramObject);
    Iterator localIterator = paramIterable.iterator();
    while ((localValueForKeyIterator.hasNext()) && (localIterator.hasNext()))
    {
      localValueForKeyIterator.next();
      localValueForKeyIterator.set(localIterator.next());
    }
    while (localValueForKeyIterator.hasNext())
    {
      localValueForKeyIterator.next();
      localValueForKeyIterator.remove();
    }
    while (localIterator.hasNext())
      localValueForKeyIterator.add(localIterator.next());
    return localList;
  }

  private List getCopy(Object paramObject)
  {
    return Collections.unmodifiableList(Lists.newArrayList(new ValueForKeyIterator(paramObject)));
  }

  public List removeAll(Object paramObject)
  {
    List localList = getCopy(paramObject);
    removeAllNodes(paramObject);
    return localList;
  }

  public void clear()
  {
    this.head = null;
    this.tail = null;
    this.keyCount.clear();
    this.keyToKeyHead.clear();
    this.keyToKeyTail.clear();
  }

  public List get(final Object paramObject)
  {
    return new AbstractSequentialList()
    {
      public int size()
      {
        return LinkedListMultimap.this.keyCount.count(paramObject);
      }

      public ListIterator listIterator(int paramAnonymousInt)
      {
        return new LinkedListMultimap.ValueForKeyIterator(LinkedListMultimap.this, paramObject, paramAnonymousInt);
      }

      public boolean removeAll(Collection paramAnonymousCollection)
      {
        return Iterators.removeAll(iterator(), paramAnonymousCollection);
      }

      public boolean retainAll(Collection paramAnonymousCollection)
      {
        return Iterators.retainAll(iterator(), paramAnonymousCollection);
      }
    };
  }

  public Set keySet()
  {
    Object localObject = this.keySet;
    if (localObject == null)
      this.keySet = (localObject = new AbstractSet()
      {
        public int size()
        {
          return LinkedListMultimap.this.keyCount.elementSet().size();
        }

        public Iterator iterator()
        {
          return new LinkedListMultimap.DistinctKeyIterator(LinkedListMultimap.this, null);
        }

        public boolean contains(Object paramAnonymousObject)
        {
          return LinkedListMultimap.this.containsKey(paramAnonymousObject);
        }

        public boolean remove(Object paramAnonymousObject)
        {
          return !LinkedListMultimap.this.removeAll(paramAnonymousObject).isEmpty();
        }

        public boolean removeAll(Collection paramAnonymousCollection)
        {
          Preconditions.checkNotNull(paramAnonymousCollection);
          return super.removeAll(paramAnonymousCollection);
        }
      });
    return localObject;
  }

  public Multiset keys()
  {
    Object localObject = this.keys;
    if (localObject == null)
      this.keys = (localObject = new MultisetView(null));
    return localObject;
  }

  public List values()
  {
    Object localObject = this.valuesList;
    if (localObject == null)
      this.valuesList = (localObject = new AbstractSequentialList()
      {
        public int size()
        {
          return LinkedListMultimap.this.keyCount.size();
        }

        public ListIterator listIterator(int paramAnonymousInt)
        {
          final LinkedListMultimap.NodeIterator localNodeIterator = new LinkedListMultimap.NodeIterator(LinkedListMultimap.this, paramAnonymousInt);
          return new TransformedListIterator(localNodeIterator)
          {
            Object transform(LinkedListMultimap.Node paramAnonymous2Node)
            {
              return paramAnonymous2Node.value;
            }

            public void set(Object paramAnonymous2Object)
            {
              localNodeIterator.setValue(paramAnonymous2Object);
            }
          };
        }
      });
    return localObject;
  }

  private static Map.Entry createEntry(Node paramNode)
  {
    return new AbstractMapEntry()
    {
      public Object getKey()
      {
        return this.val$node.key;
      }

      public Object getValue()
      {
        return this.val$node.value;
      }

      public Object setValue(Object paramAnonymousObject)
      {
        Object localObject = this.val$node.value;
        this.val$node.value = paramAnonymousObject;
        return localObject;
      }
    };
  }

  public List entries()
  {
    Object localObject = this.entries;
    if (localObject == null)
      this.entries = (localObject = new AbstractSequentialList()
      {
        public int size()
        {
          return LinkedListMultimap.this.keyCount.size();
        }

        public ListIterator listIterator(int paramAnonymousInt)
        {
          return new TransformedListIterator(new LinkedListMultimap.NodeIterator(LinkedListMultimap.this, paramAnonymousInt))
          {
            Map.Entry transform(LinkedListMultimap.Node paramAnonymous2Node)
            {
              return LinkedListMultimap.createEntry(paramAnonymous2Node);
            }
          };
        }
      });
    return localObject;
  }

  public Map asMap()
  {
    Object localObject = this.map;
    if (localObject == null)
      this.map = (localObject = new Multimaps.AsMap()
      {
        public int size()
        {
          return LinkedListMultimap.this.keyCount.elementSet().size();
        }

        Multimap multimap()
        {
          return LinkedListMultimap.this;
        }

        Iterator entryIterator()
        {
          return new TransformedIterator(new LinkedListMultimap.DistinctKeyIterator(LinkedListMultimap.this, null))
          {
            Map.Entry transform(final Object paramAnonymous2Object)
            {
              return new AbstractMapEntry()
              {
                public Object getKey()
                {
                  return paramAnonymous2Object;
                }

                public Collection getValue()
                {
                  return LinkedListMultimap.this.get(paramAnonymous2Object);
                }
              };
            }
          };
        }
      });
    return localObject;
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == this)
      return true;
    if ((paramObject instanceof Multimap))
    {
      Multimap localMultimap = (Multimap)paramObject;
      return asMap().equals(localMultimap.asMap());
    }
    return false;
  }

  public int hashCode()
  {
    return asMap().hashCode();
  }

  public String toString()
  {
    return asMap().toString();
  }

  @GwtIncompatible("java.io.ObjectOutputStream")
  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
    paramObjectOutputStream.defaultWriteObject();
    paramObjectOutputStream.writeInt(size());
    Iterator localIterator = entries().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      paramObjectOutputStream.writeObject(localEntry.getKey());
      paramObjectOutputStream.writeObject(localEntry.getValue());
    }
  }

  @GwtIncompatible("java.io.ObjectInputStream")
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    paramObjectInputStream.defaultReadObject();
    this.keyCount = LinkedHashMultiset.create();
    this.keyToKeyHead = Maps.newHashMap();
    this.keyToKeyTail = Maps.newHashMap();
    int i = paramObjectInputStream.readInt();
    for (int j = 0; j < i; j++)
    {
      Object localObject1 = paramObjectInputStream.readObject();
      Object localObject2 = paramObjectInputStream.readObject();
      put(localObject1, localObject2);
    }
  }

  private class MultisetView extends AbstractMultiset
  {
    private MultisetView()
    {
    }

    public int size()
    {
      return LinkedListMultimap.this.keyCount.size();
    }

    public int count(Object paramObject)
    {
      return LinkedListMultimap.this.keyCount.count(paramObject);
    }

    Iterator entryIterator()
    {
      return new TransformedIterator(new LinkedListMultimap.DistinctKeyIterator(LinkedListMultimap.this, null))
      {
        Multiset.Entry transform(final Object paramAnonymousObject)
        {
          return new Multisets.AbstractEntry()
          {
            public Object getElement()
            {
              return paramAnonymousObject;
            }

            public int getCount()
            {
              return LinkedListMultimap.this.keyCount.count(paramAnonymousObject);
            }
          };
        }
      };
    }

    int distinctElements()
    {
      return elementSet().size();
    }

    public Iterator iterator()
    {
      return new TransformedIterator(new LinkedListMultimap.NodeIterator(LinkedListMultimap.this))
      {
        Object transform(LinkedListMultimap.Node paramAnonymousNode)
        {
          return paramAnonymousNode.key;
        }
      };
    }

    public int remove(Object paramObject, int paramInt)
    {
      Preconditions.checkArgument(paramInt >= 0);
      int i = count(paramObject);
      LinkedListMultimap.ValueForKeyIterator localValueForKeyIterator = new LinkedListMultimap.ValueForKeyIterator(LinkedListMultimap.this, paramObject);
      while ((paramInt-- > 0) && (localValueForKeyIterator.hasNext()))
      {
        localValueForKeyIterator.next();
        localValueForKeyIterator.remove();
      }
      return i;
    }

    public Set elementSet()
    {
      return LinkedListMultimap.this.keySet();
    }

    public boolean equals(Object paramObject)
    {
      return LinkedListMultimap.this.keyCount.equals(paramObject);
    }

    public int hashCode()
    {
      return LinkedListMultimap.this.keyCount.hashCode();
    }

    public String toString()
    {
      return LinkedListMultimap.this.keyCount.toString();
    }
  }

  private class ValueForKeyIterator
    implements ListIterator
  {
    final Object key;
    int nextIndex;
    LinkedListMultimap.Node next;
    LinkedListMultimap.Node current;
    LinkedListMultimap.Node previous;

    ValueForKeyIterator(Object arg2)
    {
      Object localObject;
      this.key = localObject;
      this.next = ((LinkedListMultimap.Node)LinkedListMultimap.this.keyToKeyHead.get(localObject));
    }

    public ValueForKeyIterator(Object paramInt, int arg3)
    {
      int j = LinkedListMultimap.this.keyCount.count(paramInt);
      int i;
      Preconditions.checkPositionIndex(i, j);
      if (i >= j / 2)
      {
        this.previous = ((LinkedListMultimap.Node)LinkedListMultimap.this.keyToKeyTail.get(paramInt));
        this.nextIndex = j;
        while (i++ < j)
          previous();
      }
      this.next = ((LinkedListMultimap.Node)LinkedListMultimap.this.keyToKeyHead.get(paramInt));
      while (i-- > 0)
        next();
      this.key = paramInt;
      this.current = null;
    }

    public boolean hasNext()
    {
      return this.next != null;
    }

    public Object next()
    {
      LinkedListMultimap.checkElement(this.next);
      this.previous = (this.current = this.next);
      this.next = this.next.nextSibling;
      this.nextIndex += 1;
      return this.current.value;
    }

    public boolean hasPrevious()
    {
      return this.previous != null;
    }

    public Object previous()
    {
      LinkedListMultimap.checkElement(this.previous);
      this.next = (this.current = this.previous);
      this.previous = this.previous.previousSibling;
      this.nextIndex -= 1;
      return this.current.value;
    }

    public int nextIndex()
    {
      return this.nextIndex;
    }

    public int previousIndex()
    {
      return this.nextIndex - 1;
    }

    public void remove()
    {
      Preconditions.checkState(this.current != null);
      if (this.current != this.next)
      {
        this.previous = this.current.previousSibling;
        this.nextIndex -= 1;
      }
      else
      {
        this.next = this.current.nextSibling;
      }
      LinkedListMultimap.this.removeNode(this.current);
      this.current = null;
    }

    public void set(Object paramObject)
    {
      Preconditions.checkState(this.current != null);
      this.current.value = paramObject;
    }

    public void add(Object paramObject)
    {
      this.previous = LinkedListMultimap.this.addNode(this.key, paramObject, this.next);
      this.nextIndex += 1;
      this.current = null;
    }
  }

  private class DistinctKeyIterator
    implements Iterator
  {
    final Set seenKeys = Sets.newHashSetWithExpectedSize(LinkedListMultimap.this.keySet().size());
    LinkedListMultimap.Node next = LinkedListMultimap.this.head;
    LinkedListMultimap.Node current;

    private DistinctKeyIterator()
    {
    }

    public boolean hasNext()
    {
      return this.next != null;
    }

    public Object next()
    {
      LinkedListMultimap.checkElement(this.next);
      this.current = this.next;
      this.seenKeys.add(this.current.key);
      do
        this.next = this.next.next;
      while ((this.next != null) && (!this.seenKeys.add(this.next.key)));
      return this.current.key;
    }

    public void remove()
    {
      Preconditions.checkState(this.current != null);
      LinkedListMultimap.this.removeAllNodes(this.current.key);
      this.current = null;
    }
  }

  private class NodeIterator
    implements ListIterator
  {
    int nextIndex;
    LinkedListMultimap.Node next;
    LinkedListMultimap.Node current;
    LinkedListMultimap.Node previous;

    NodeIterator()
    {
      this.next = LinkedListMultimap.this.head;
    }

    NodeIterator(int arg2)
    {
      int j = LinkedListMultimap.this.size();
      int i;
      Preconditions.checkPositionIndex(i, j);
      if (i >= j / 2)
      {
        this.previous = LinkedListMultimap.this.tail;
        this.nextIndex = j;
        while (i++ < j)
          previous();
      }
      this.next = LinkedListMultimap.this.head;
      while (i-- > 0)
        next();
      this.current = null;
    }

    public boolean hasNext()
    {
      return this.next != null;
    }

    public LinkedListMultimap.Node next()
    {
      LinkedListMultimap.checkElement(this.next);
      this.previous = (this.current = this.next);
      this.next = this.next.next;
      this.nextIndex += 1;
      return this.current;
    }

    public void remove()
    {
      Preconditions.checkState(this.current != null);
      if (this.current != this.next)
      {
        this.previous = this.current.previous;
        this.nextIndex -= 1;
      }
      else
      {
        this.next = this.current.next;
      }
      LinkedListMultimap.this.removeNode(this.current);
      this.current = null;
    }

    public boolean hasPrevious()
    {
      return this.previous != null;
    }

    public LinkedListMultimap.Node previous()
    {
      LinkedListMultimap.checkElement(this.previous);
      this.next = (this.current = this.previous);
      this.previous = this.previous.previous;
      this.nextIndex -= 1;
      return this.current;
    }

    public int nextIndex()
    {
      return this.nextIndex;
    }

    public int previousIndex()
    {
      return this.nextIndex - 1;
    }

    public void set(LinkedListMultimap.Node paramNode)
    {
      throw new UnsupportedOperationException();
    }

    public void add(LinkedListMultimap.Node paramNode)
    {
      throw new UnsupportedOperationException();
    }

    void setValue(Object paramObject)
    {
      Preconditions.checkState(this.current != null);
      this.current.value = paramObject;
    }
  }

  private static final class Node
  {
    final Object key;
    Object value;
    Node next;
    Node previous;
    Node nextSibling;
    Node previousSibling;

    Node(Object paramObject1, Object paramObject2)
    {
      this.key = paramObject1;
      this.value = paramObject2;
    }

    public String toString()
    {
      return this.key + "=" + this.value;
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.LinkedListMultimap
 * JD-Core Version:    0.6.2
 */