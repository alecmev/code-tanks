package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map.Entry;
import java.util.Set;

@GwtCompatible(serializable=true, emulated=true)
public final class LinkedHashMultimap extends AbstractSetMultimap
{
  private static final int DEFAULT_VALUES_PER_KEY = 8;

  @VisibleForTesting
  transient int expectedValuesPerKey = 8;
  transient Collection linkedEntries;

  @GwtIncompatible("java serialization not supported")
  private static final long serialVersionUID = 0L;

  public static LinkedHashMultimap create()
  {
    return new LinkedHashMultimap();
  }

  public static LinkedHashMultimap create(int paramInt1, int paramInt2)
  {
    return new LinkedHashMultimap(paramInt1, paramInt2);
  }

  public static LinkedHashMultimap create(Multimap paramMultimap)
  {
    return new LinkedHashMultimap(paramMultimap);
  }

  private LinkedHashMultimap()
  {
    super(new LinkedHashMap());
    this.linkedEntries = Sets.newLinkedHashSet();
  }

  private LinkedHashMultimap(int paramInt1, int paramInt2)
  {
    super(new LinkedHashMap(paramInt1));
    Preconditions.checkArgument(paramInt2 >= 0);
    this.expectedValuesPerKey = paramInt2;
    this.linkedEntries = new LinkedHashSet((int)Math.min(1073741824L, paramInt1 * paramInt2));
  }

  private LinkedHashMultimap(Multimap paramMultimap)
  {
    super(new LinkedHashMap(Maps.capacity(paramMultimap.keySet().size())));
    this.linkedEntries = new LinkedHashSet(Maps.capacity(paramMultimap.size()));
    putAll(paramMultimap);
  }

  Set createCollection()
  {
    return new LinkedHashSet(Maps.capacity(this.expectedValuesPerKey));
  }

  Collection createCollection(Object paramObject)
  {
    return new SetDecorator(paramObject, createCollection());
  }

  Iterator createEntryIterator()
  {
    final Iterator localIterator = this.linkedEntries.iterator();
    return new Iterator()
    {
      Map.Entry entry;

      public boolean hasNext()
      {
        return localIterator.hasNext();
      }

      public Map.Entry next()
      {
        this.entry = ((Map.Entry)localIterator.next());
        return this.entry;
      }

      public void remove()
      {
        localIterator.remove();
        LinkedHashMultimap.this.remove(this.entry.getKey(), this.entry.getValue());
      }
    };
  }

  public Set replaceValues(Object paramObject, Iterable paramIterable)
  {
    return super.replaceValues(paramObject, paramIterable);
  }

  public Set entries()
  {
    return super.entries();
  }

  public Collection values()
  {
    return super.values();
  }

  @GwtIncompatible("java.io.ObjectOutputStream")
  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
    paramObjectOutputStream.defaultWriteObject();
    paramObjectOutputStream.writeInt(this.expectedValuesPerKey);
    Serialization.writeMultimap(this, paramObjectOutputStream);
    Iterator localIterator = this.linkedEntries.iterator();
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
    this.expectedValuesPerKey = paramObjectInputStream.readInt();
    int i = Serialization.readCount(paramObjectInputStream);
    setMap(new LinkedHashMap(Maps.capacity(i)));
    this.linkedEntries = new LinkedHashSet(i * this.expectedValuesPerKey);
    Serialization.populateMultimap(this, paramObjectInputStream, i);
    this.linkedEntries.clear();
    for (int j = 0; j < size(); j++)
    {
      Object localObject1 = paramObjectInputStream.readObject();
      Object localObject2 = paramObjectInputStream.readObject();
      this.linkedEntries.add(Maps.immutableEntry(localObject1, localObject2));
    }
  }

  private class SetDecorator extends ForwardingSet
  {
    final Set delegate;
    final Object key;

    SetDecorator(Object paramSet, Set arg3)
    {
      Object localObject;
      this.delegate = localObject;
      this.key = paramSet;
    }

    protected Set delegate()
    {
      return this.delegate;
    }

    Map.Entry createEntry(Object paramObject)
    {
      return Maps.immutableEntry(this.key, paramObject);
    }

    Collection createEntries(Collection paramCollection)
    {
      ArrayList localArrayList = Lists.newArrayListWithExpectedSize(paramCollection.size());
      Iterator localIterator = paramCollection.iterator();
      while (localIterator.hasNext())
      {
        Object localObject = localIterator.next();
        localArrayList.add(createEntry(localObject));
      }
      return localArrayList;
    }

    public boolean add(Object paramObject)
    {
      boolean bool = this.delegate.add(paramObject);
      if (bool)
        LinkedHashMultimap.this.linkedEntries.add(createEntry(paramObject));
      return bool;
    }

    public boolean addAll(Collection paramCollection)
    {
      boolean bool = this.delegate.addAll(paramCollection);
      if (bool)
        LinkedHashMultimap.this.linkedEntries.addAll(createEntries(delegate()));
      return bool;
    }

    public void clear()
    {
      Iterator localIterator = this.delegate.iterator();
      while (localIterator.hasNext())
      {
        Object localObject = localIterator.next();
        LinkedHashMultimap.this.linkedEntries.remove(createEntry(localObject));
      }
      this.delegate.clear();
    }

    public Iterator iterator()
    {
      final Iterator localIterator = this.delegate.iterator();
      return new Iterator()
      {
        Object value;

        public boolean hasNext()
        {
          return localIterator.hasNext();
        }

        public Object next()
        {
          this.value = localIterator.next();
          return this.value;
        }

        public void remove()
        {
          localIterator.remove();
          LinkedHashMultimap.this.linkedEntries.remove(LinkedHashMultimap.SetDecorator.this.createEntry(this.value));
        }
      };
    }

    public boolean remove(Object paramObject)
    {
      boolean bool = this.delegate.remove(paramObject);
      if (bool)
        LinkedHashMultimap.this.linkedEntries.remove(createEntry(paramObject));
      return bool;
    }

    public boolean removeAll(Collection paramCollection)
    {
      boolean bool = this.delegate.removeAll(paramCollection);
      if (bool)
        LinkedHashMultimap.this.linkedEntries.removeAll(createEntries(paramCollection));
      return bool;
    }

    public boolean retainAll(Collection paramCollection)
    {
      boolean bool = false;
      Iterator localIterator = this.delegate.iterator();
      while (localIterator.hasNext())
      {
        Object localObject = localIterator.next();
        if (!paramCollection.contains(localObject))
        {
          localIterator.remove();
          LinkedHashMultimap.this.linkedEntries.remove(Maps.immutableEntry(this.key, localObject));
          bool = true;
        }
      }
      return bool;
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.LinkedHashMultimap
 * JD-Core Version:    0.6.2
 */