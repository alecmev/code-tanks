package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.RandomAccess;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

@GwtCompatible
abstract class AbstractMultimap
  implements Multimap, Serializable
{
  private transient Map map;
  private transient int totalSize;
  private transient Set keySet;
  private transient Multiset multiset;
  private transient Collection valuesCollection;
  private transient Collection entries;
  private transient Map asMap;
  private static final long serialVersionUID = 2447537837011683357L;

  protected AbstractMultimap(Map paramMap)
  {
    Preconditions.checkArgument(paramMap.isEmpty());
    this.map = paramMap;
  }

  final void setMap(Map paramMap)
  {
    this.map = paramMap;
    this.totalSize = 0;
    Iterator localIterator = paramMap.values().iterator();
    while (localIterator.hasNext())
    {
      Collection localCollection = (Collection)localIterator.next();
      Preconditions.checkArgument(!localCollection.isEmpty());
      this.totalSize += localCollection.size();
    }
  }

  abstract Collection createCollection();

  Collection createCollection(Object paramObject)
  {
    return createCollection();
  }

  Map backingMap()
  {
    return this.map;
  }

  public int size()
  {
    return this.totalSize;
  }

  public boolean isEmpty()
  {
    return this.totalSize == 0;
  }

  public boolean containsKey(Object paramObject)
  {
    return this.map.containsKey(paramObject);
  }

  public boolean containsValue(Object paramObject)
  {
    Iterator localIterator = this.map.values().iterator();
    while (localIterator.hasNext())
    {
      Collection localCollection = (Collection)localIterator.next();
      if (localCollection.contains(paramObject))
        return true;
    }
    return false;
  }

  public boolean containsEntry(Object paramObject1, Object paramObject2)
  {
    Collection localCollection = (Collection)this.map.get(paramObject1);
    return (localCollection != null) && (localCollection.contains(paramObject2));
  }

  public boolean put(Object paramObject1, Object paramObject2)
  {
    Collection localCollection = (Collection)this.map.get(paramObject1);
    if (localCollection == null)
    {
      localCollection = createCollection(paramObject1);
      if (localCollection.add(paramObject2))
      {
        this.totalSize += 1;
        this.map.put(paramObject1, localCollection);
        return true;
      }
      throw new AssertionError("New Collection violated the Collection spec");
    }
    if (localCollection.add(paramObject2))
    {
      this.totalSize += 1;
      return true;
    }
    return false;
  }

  private Collection getOrCreateCollection(Object paramObject)
  {
    Collection localCollection = (Collection)this.map.get(paramObject);
    if (localCollection == null)
    {
      localCollection = createCollection(paramObject);
      this.map.put(paramObject, localCollection);
    }
    return localCollection;
  }

  public boolean remove(Object paramObject1, Object paramObject2)
  {
    Collection localCollection = (Collection)this.map.get(paramObject1);
    if (localCollection == null)
      return false;
    boolean bool = localCollection.remove(paramObject2);
    if (bool)
    {
      this.totalSize -= 1;
      if (localCollection.isEmpty())
        this.map.remove(paramObject1);
    }
    return bool;
  }

  public boolean putAll(Object paramObject, Iterable paramIterable)
  {
    if (!paramIterable.iterator().hasNext())
      return false;
    Collection localCollection = getOrCreateCollection(paramObject);
    int i = localCollection.size();
    boolean bool = false;
    Object localObject1;
    if ((paramIterable instanceof Collection))
    {
      localObject1 = Collections2.cast(paramIterable);
      bool = localCollection.addAll((Collection)localObject1);
    }
    else
    {
      localObject1 = paramIterable.iterator();
      while (((Iterator)localObject1).hasNext())
      {
        Object localObject2 = ((Iterator)localObject1).next();
        bool |= localCollection.add(localObject2);
      }
    }
    this.totalSize += localCollection.size() - i;
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

  public Collection replaceValues(Object paramObject, Iterable paramIterable)
  {
    Iterator localIterator = paramIterable.iterator();
    if (!localIterator.hasNext())
      return removeAll(paramObject);
    Collection localCollection1 = getOrCreateCollection(paramObject);
    Collection localCollection2 = createCollection();
    localCollection2.addAll(localCollection1);
    this.totalSize -= localCollection1.size();
    localCollection1.clear();
    while (localIterator.hasNext())
      if (localCollection1.add(localIterator.next()))
        this.totalSize += 1;
    return unmodifiableCollectionSubclass(localCollection2);
  }

  public Collection removeAll(Object paramObject)
  {
    Collection localCollection1 = (Collection)this.map.remove(paramObject);
    Collection localCollection2 = createCollection();
    if (localCollection1 != null)
    {
      localCollection2.addAll(localCollection1);
      this.totalSize -= localCollection1.size();
      localCollection1.clear();
    }
    return unmodifiableCollectionSubclass(localCollection2);
  }

  private Collection unmodifiableCollectionSubclass(Collection paramCollection)
  {
    if ((paramCollection instanceof SortedSet))
      return Collections.unmodifiableSortedSet((SortedSet)paramCollection);
    if ((paramCollection instanceof Set))
      return Collections.unmodifiableSet((Set)paramCollection);
    if ((paramCollection instanceof List))
      return Collections.unmodifiableList((List)paramCollection);
    return Collections.unmodifiableCollection(paramCollection);
  }

  public void clear()
  {
    Iterator localIterator = this.map.values().iterator();
    while (localIterator.hasNext())
    {
      Collection localCollection = (Collection)localIterator.next();
      localCollection.clear();
    }
    this.map.clear();
    this.totalSize = 0;
  }

  public Collection get(Object paramObject)
  {
    Collection localCollection = (Collection)this.map.get(paramObject);
    if (localCollection == null)
      localCollection = createCollection(paramObject);
    return wrapCollection(paramObject, localCollection);
  }

  private Collection wrapCollection(Object paramObject, Collection paramCollection)
  {
    if ((paramCollection instanceof SortedSet))
      return new WrappedSortedSet(paramObject, (SortedSet)paramCollection, null);
    if ((paramCollection instanceof Set))
      return new WrappedSet(paramObject, (Set)paramCollection);
    if ((paramCollection instanceof List))
      return wrapList(paramObject, (List)paramCollection, null);
    return new WrappedCollection(paramObject, paramCollection, null);
  }

  private List wrapList(Object paramObject, List paramList, WrappedCollection paramWrappedCollection)
  {
    return (paramList instanceof RandomAccess) ? new RandomAccessWrappedList(paramObject, paramList, paramWrappedCollection) : new WrappedList(paramObject, paramList, paramWrappedCollection);
  }

  private Iterator iteratorOrListIterator(Collection paramCollection)
  {
    return (paramCollection instanceof List) ? ((List)paramCollection).listIterator() : paramCollection.iterator();
  }

  public Set keySet()
  {
    Set localSet = this.keySet;
    return localSet == null ? (this.keySet = createKeySet()) : localSet;
  }

  private Set createKeySet()
  {
    return (this.map instanceof SortedMap) ? new SortedKeySet((SortedMap)this.map) : new KeySet(this.map);
  }

  public Multiset keys()
  {
    Multiset localMultiset = this.multiset;
    if (localMultiset == null)
      return this.multiset = new Multimaps.Keys()
      {
        Multimap multimap()
        {
          return AbstractMultimap.this;
        }
      };
    return localMultiset;
  }

  private int removeValuesForKey(Object paramObject)
  {
    Collection localCollection;
    try
    {
      localCollection = (Collection)this.map.remove(paramObject);
    }
    catch (NullPointerException localNullPointerException)
    {
      return 0;
    }
    catch (ClassCastException localClassCastException)
    {
      return 0;
    }
    int i = 0;
    if (localCollection != null)
    {
      i = localCollection.size();
      localCollection.clear();
      this.totalSize -= i;
    }
    return i;
  }

  public Collection values()
  {
    Collection localCollection = this.valuesCollection;
    if (localCollection == null)
      return this.valuesCollection = new Multimaps.Values()
      {
        Multimap multimap()
        {
          return AbstractMultimap.this;
        }
      };
    return localCollection;
  }

  public Collection entries()
  {
    Collection localCollection = this.entries;
    return localCollection == null ? (this.entries = createEntries()) : localCollection;
  }

  Collection createEntries()
  {
    if ((this instanceof SetMultimap))
      return new Multimaps.EntrySet()
      {
        Multimap multimap()
        {
          return AbstractMultimap.this;
        }

        public Iterator iterator()
        {
          return AbstractMultimap.this.createEntryIterator();
        }
      };
    return new Multimaps.Entries()
    {
      Multimap multimap()
      {
        return AbstractMultimap.this;
      }

      public Iterator iterator()
      {
        return AbstractMultimap.this.createEntryIterator();
      }
    };
  }

  Iterator createEntryIterator()
  {
    return new EntryIterator();
  }

  public Map asMap()
  {
    Map localMap = this.asMap;
    return localMap == null ? (this.asMap = createAsMap()) : localMap;
  }

  private Map createAsMap()
  {
    return (this.map instanceof SortedMap) ? new SortedAsMap((SortedMap)this.map) : new AsMap(this.map);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == this)
      return true;
    if ((paramObject instanceof Serializable))
    {
      Multimap localMultimap = (Serializable)paramObject;
      return this.map.equals(localMultimap.asMap());
    }
    return false;
  }

  public int hashCode()
  {
    return this.map.hashCode();
  }

  public String toString()
  {
    return this.map.toString();
  }

  private class SortedAsMap extends AbstractMultimap.AsMap
    implements SortedMap
  {
    SortedSet sortedKeySet;

    SortedAsMap(SortedMap arg2)
    {
      super(localMap);
    }

    SortedMap sortedMap()
    {
      return (SortedMap)this.submap;
    }

    public Comparator comparator()
    {
      return sortedMap().comparator();
    }

    public Object firstKey()
    {
      return sortedMap().firstKey();
    }

    public Object lastKey()
    {
      return sortedMap().lastKey();
    }

    public SortedMap headMap(Object paramObject)
    {
      return new SortedAsMap(AbstractMultimap.this, sortedMap().headMap(paramObject));
    }

    public SortedMap subMap(Object paramObject1, Object paramObject2)
    {
      return new SortedAsMap(AbstractMultimap.this, sortedMap().subMap(paramObject1, paramObject2));
    }

    public SortedMap tailMap(Object paramObject)
    {
      return new SortedAsMap(AbstractMultimap.this, sortedMap().tailMap(paramObject));
    }

    public SortedSet keySet()
    {
      SortedSet localSortedSet = this.sortedKeySet;
      return localSortedSet == null ? (this.sortedKeySet = new AbstractMultimap.SortedKeySet(AbstractMultimap.this, sortedMap())) : localSortedSet;
    }
  }

  private class AsMap extends AbstractMap
  {
    final transient Map submap;
    transient Set entrySet;

    AsMap(Map arg2)
    {
      Object localObject;
      this.submap = localObject;
    }

    public Set entrySet()
    {
      Set localSet = this.entrySet;
      return localSet == null ? (this.entrySet = new AsMapEntries()) : localSet;
    }

    public boolean containsKey(Object paramObject)
    {
      return Maps.safeContainsKey(this.submap, paramObject);
    }

    public Collection get(Object paramObject)
    {
      Collection localCollection = (Collection)Maps.safeGet(this.submap, paramObject);
      if (localCollection == null)
        return null;
      Object localObject = paramObject;
      return AbstractMultimap.this.wrapCollection(localObject, localCollection);
    }

    public Set keySet()
    {
      return AbstractMultimap.this.keySet();
    }

    public int size()
    {
      return this.submap.size();
    }

    public Collection remove(Object paramObject)
    {
      Collection localCollection1 = (Collection)this.submap.remove(paramObject);
      if (localCollection1 == null)
        return null;
      Collection localCollection2 = AbstractMultimap.this.createCollection();
      localCollection2.addAll(localCollection1);
      AbstractMultimap.access$220(AbstractMultimap.this, localCollection1.size());
      localCollection1.clear();
      return localCollection2;
    }

    public boolean equals(Object paramObject)
    {
      return (this == paramObject) || (this.submap.equals(paramObject));
    }

    public int hashCode()
    {
      return this.submap.hashCode();
    }

    public String toString()
    {
      return this.submap.toString();
    }

    public void clear()
    {
      if (this.submap == AbstractMultimap.this.map)
        AbstractMultimap.this.clear();
      else
        Iterators.clear(new AsMapIterator());
    }

    class AsMapIterator
      implements Iterator
    {
      final Iterator delegateIterator = AbstractMultimap.AsMap.this.submap.entrySet().iterator();
      Collection collection;

      AsMapIterator()
      {
      }

      public boolean hasNext()
      {
        return this.delegateIterator.hasNext();
      }

      public Map.Entry next()
      {
        Map.Entry localEntry = (Map.Entry)this.delegateIterator.next();
        Object localObject = localEntry.getKey();
        this.collection = ((Collection)localEntry.getValue());
        return Maps.immutableEntry(localObject, AbstractMultimap.this.wrapCollection(localObject, this.collection));
      }

      public void remove()
      {
        this.delegateIterator.remove();
        AbstractMultimap.access$220(AbstractMultimap.this, this.collection.size());
        this.collection.clear();
      }
    }

    class AsMapEntries extends Maps.EntrySet
    {
      AsMapEntries()
      {
      }

      Map map()
      {
        return AbstractMultimap.AsMap.this;
      }

      public Iterator iterator()
      {
        return new AbstractMultimap.AsMap.AsMapIterator(AbstractMultimap.AsMap.this);
      }

      public boolean contains(Object paramObject)
      {
        return Collections2.safeContains(AbstractMultimap.AsMap.this.submap.entrySet(), paramObject);
      }

      public boolean remove(Object paramObject)
      {
        if (!contains(paramObject))
          return false;
        Map.Entry localEntry = (Map.Entry)paramObject;
        AbstractMultimap.this.removeValuesForKey(localEntry.getKey());
        return true;
      }
    }
  }

  private class EntryIterator
    implements Iterator
  {
    final Iterator keyIterator = AbstractMultimap.this.map.entrySet().iterator();
    Object key;
    Collection collection;
    Iterator valueIterator;

    EntryIterator()
    {
      if (this.keyIterator.hasNext())
        findValueIteratorAndKey();
      else
        this.valueIterator = Iterators.emptyModifiableIterator();
    }

    void findValueIteratorAndKey()
    {
      Map.Entry localEntry = (Map.Entry)this.keyIterator.next();
      this.key = localEntry.getKey();
      this.collection = ((Collection)localEntry.getValue());
      this.valueIterator = this.collection.iterator();
    }

    public boolean hasNext()
    {
      return (this.keyIterator.hasNext()) || (this.valueIterator.hasNext());
    }

    public Map.Entry next()
    {
      if (!this.valueIterator.hasNext())
        findValueIteratorAndKey();
      return Maps.immutableEntry(this.key, this.valueIterator.next());
    }

    public void remove()
    {
      this.valueIterator.remove();
      if (this.collection.isEmpty())
        this.keyIterator.remove();
      AbstractMultimap.access$210(AbstractMultimap.this);
    }
  }

  private class SortedKeySet extends AbstractMultimap.KeySet
    implements SortedSet
  {
    SortedKeySet(SortedMap arg2)
    {
      super(localMap);
    }

    SortedMap sortedMap()
    {
      return (SortedMap)this.subMap;
    }

    public Comparator comparator()
    {
      return sortedMap().comparator();
    }

    public Object first()
    {
      return sortedMap().firstKey();
    }

    public SortedSet headSet(Object paramObject)
    {
      return new SortedKeySet(AbstractMultimap.this, sortedMap().headMap(paramObject));
    }

    public Object last()
    {
      return sortedMap().lastKey();
    }

    public SortedSet subSet(Object paramObject1, Object paramObject2)
    {
      return new SortedKeySet(AbstractMultimap.this, sortedMap().subMap(paramObject1, paramObject2));
    }

    public SortedSet tailSet(Object paramObject)
    {
      return new SortedKeySet(AbstractMultimap.this, sortedMap().tailMap(paramObject));
    }
  }

  private class KeySet extends Maps.KeySet
  {
    final Map subMap;

    KeySet(Map arg2)
    {
      Object localObject;
      this.subMap = localObject;
    }

    Map map()
    {
      return this.subMap;
    }

    public Iterator iterator()
    {
      return new Iterator()
      {
        final Iterator entryIterator = AbstractMultimap.KeySet.this.subMap.entrySet().iterator();
        Map.Entry entry;

        public boolean hasNext()
        {
          return this.entryIterator.hasNext();
        }

        public Object next()
        {
          this.entry = ((Map.Entry)this.entryIterator.next());
          return this.entry.getKey();
        }

        public void remove()
        {
          Iterators.checkRemove(this.entry != null);
          Collection localCollection = (Collection)this.entry.getValue();
          this.entryIterator.remove();
          AbstractMultimap.access$220(AbstractMultimap.this, localCollection.size());
          localCollection.clear();
        }
      };
    }

    public boolean remove(Object paramObject)
    {
      int i = 0;
      Collection localCollection = (Collection)this.subMap.remove(paramObject);
      if (localCollection != null)
      {
        i = localCollection.size();
        localCollection.clear();
        AbstractMultimap.access$220(AbstractMultimap.this, i);
      }
      return i > 0;
    }

    public void clear()
    {
      Iterators.clear(iterator());
    }

    public boolean containsAll(Collection paramCollection)
    {
      return this.subMap.keySet().containsAll(paramCollection);
    }

    public boolean equals(Object paramObject)
    {
      return (this == paramObject) || (this.subMap.keySet().equals(paramObject));
    }

    public int hashCode()
    {
      return this.subMap.keySet().hashCode();
    }
  }

  private class RandomAccessWrappedList extends AbstractMultimap.WrappedList
    implements RandomAccess
  {
    RandomAccessWrappedList(Object paramList, List paramWrappedCollection, AbstractMultimap.WrappedCollection arg4)
    {
      super(paramList, paramWrappedCollection, localWrappedCollection);
    }
  }

  private class WrappedList extends AbstractMultimap.WrappedCollection
    implements List
  {
    WrappedList(Object paramList, List paramWrappedCollection, AbstractMultimap.WrappedCollection arg4)
    {
      super(paramList, paramWrappedCollection, localWrappedCollection);
    }

    List getListDelegate()
    {
      return (List)getDelegate();
    }

    public boolean addAll(int paramInt, Collection paramCollection)
    {
      if (paramCollection.isEmpty())
        return false;
      int i = size();
      boolean bool = getListDelegate().addAll(paramInt, paramCollection);
      if (bool)
      {
        int j = getDelegate().size();
        AbstractMultimap.access$212(AbstractMultimap.this, j - i);
        if (i == 0)
          addToMap();
      }
      return bool;
    }

    public Object get(int paramInt)
    {
      refreshIfEmpty();
      return getListDelegate().get(paramInt);
    }

    public Object set(int paramInt, Object paramObject)
    {
      refreshIfEmpty();
      return getListDelegate().set(paramInt, paramObject);
    }

    public void add(int paramInt, Object paramObject)
    {
      refreshIfEmpty();
      boolean bool = getDelegate().isEmpty();
      getListDelegate().add(paramInt, paramObject);
      AbstractMultimap.access$208(AbstractMultimap.this);
      if (bool)
        addToMap();
    }

    public Object remove(int paramInt)
    {
      refreshIfEmpty();
      Object localObject = getListDelegate().remove(paramInt);
      AbstractMultimap.access$210(AbstractMultimap.this);
      removeIfEmpty();
      return localObject;
    }

    public int indexOf(Object paramObject)
    {
      refreshIfEmpty();
      return getListDelegate().indexOf(paramObject);
    }

    public int lastIndexOf(Object paramObject)
    {
      refreshIfEmpty();
      return getListDelegate().lastIndexOf(paramObject);
    }

    public ListIterator listIterator()
    {
      refreshIfEmpty();
      return new WrappedListIterator();
    }

    public ListIterator listIterator(int paramInt)
    {
      refreshIfEmpty();
      return new WrappedListIterator(paramInt);
    }

    public List subList(int paramInt1, int paramInt2)
    {
      refreshIfEmpty();
      return AbstractMultimap.this.wrapList(getKey(), getListDelegate().subList(paramInt1, paramInt2), getAncestor() == null ? this : getAncestor());
    }

    private class WrappedListIterator extends AbstractMultimap.WrappedCollection.WrappedIterator
      implements ListIterator
    {
      WrappedListIterator()
      {
        super();
      }

      public WrappedListIterator(int arg2)
      {
        super(AbstractMultimap.WrappedList.this.getListDelegate().listIterator(i));
      }

      private ListIterator getDelegateListIterator()
      {
        return (ListIterator)getDelegateIterator();
      }

      public boolean hasPrevious()
      {
        return getDelegateListIterator().hasPrevious();
      }

      public Object previous()
      {
        return getDelegateListIterator().previous();
      }

      public int nextIndex()
      {
        return getDelegateListIterator().nextIndex();
      }

      public int previousIndex()
      {
        return getDelegateListIterator().previousIndex();
      }

      public void set(Object paramObject)
      {
        getDelegateListIterator().set(paramObject);
      }

      public void add(Object paramObject)
      {
        boolean bool = AbstractMultimap.WrappedList.this.isEmpty();
        getDelegateListIterator().add(paramObject);
        AbstractMultimap.access$208(AbstractMultimap.this);
        if (bool)
          AbstractMultimap.WrappedList.this.addToMap();
      }
    }
  }

  private class WrappedSortedSet extends AbstractMultimap.WrappedCollection
    implements SortedSet
  {
    WrappedSortedSet(Object paramSortedSet, SortedSet paramWrappedCollection, AbstractMultimap.WrappedCollection arg4)
    {
      super(paramSortedSet, paramWrappedCollection, localWrappedCollection);
    }

    SortedSet getSortedSetDelegate()
    {
      return (SortedSet)getDelegate();
    }

    public Comparator comparator()
    {
      return getSortedSetDelegate().comparator();
    }

    public Object first()
    {
      refreshIfEmpty();
      return getSortedSetDelegate().first();
    }

    public Object last()
    {
      refreshIfEmpty();
      return getSortedSetDelegate().last();
    }

    public SortedSet headSet(Object paramObject)
    {
      refreshIfEmpty();
      return new WrappedSortedSet(AbstractMultimap.this, getKey(), getSortedSetDelegate().headSet(paramObject), getAncestor() == null ? this : getAncestor());
    }

    public SortedSet subSet(Object paramObject1, Object paramObject2)
    {
      refreshIfEmpty();
      return new WrappedSortedSet(AbstractMultimap.this, getKey(), getSortedSetDelegate().subSet(paramObject1, paramObject2), getAncestor() == null ? this : getAncestor());
    }

    public SortedSet tailSet(Object paramObject)
    {
      refreshIfEmpty();
      return new WrappedSortedSet(AbstractMultimap.this, getKey(), getSortedSetDelegate().tailSet(paramObject), getAncestor() == null ? this : getAncestor());
    }
  }

  private class WrappedSet extends AbstractMultimap.WrappedCollection
    implements Set
  {
    WrappedSet(Object paramSet, Set arg3)
    {
      super(paramSet, localCollection, null);
    }
  }

  private class WrappedCollection extends AbstractCollection
  {
    final Object key;
    Collection delegate;
    final WrappedCollection ancestor;
    final Collection ancestorDelegate;

    WrappedCollection(Object paramCollection, Collection paramWrappedCollection, WrappedCollection arg4)
    {
      this.key = paramCollection;
      this.delegate = paramWrappedCollection;
      Object localObject;
      this.ancestor = localObject;
      this.ancestorDelegate = (localObject == null ? null : localObject.getDelegate());
    }

    void refreshIfEmpty()
    {
      if (this.ancestor != null)
      {
        this.ancestor.refreshIfEmpty();
        if (this.ancestor.getDelegate() != this.ancestorDelegate)
          throw new ConcurrentModificationException();
      }
      else if (this.delegate.isEmpty())
      {
        Collection localCollection = (Collection)AbstractMultimap.this.map.get(this.key);
        if (localCollection != null)
          this.delegate = localCollection;
      }
    }

    void removeIfEmpty()
    {
      if (this.ancestor != null)
        this.ancestor.removeIfEmpty();
      else if (this.delegate.isEmpty())
        AbstractMultimap.this.map.remove(this.key);
    }

    Object getKey()
    {
      return this.key;
    }

    void addToMap()
    {
      if (this.ancestor != null)
        this.ancestor.addToMap();
      else
        AbstractMultimap.this.map.put(this.key, this.delegate);
    }

    public int size()
    {
      refreshIfEmpty();
      return this.delegate.size();
    }

    public boolean equals(Object paramObject)
    {
      if (paramObject == this)
        return true;
      refreshIfEmpty();
      return this.delegate.equals(paramObject);
    }

    public int hashCode()
    {
      refreshIfEmpty();
      return this.delegate.hashCode();
    }

    public String toString()
    {
      refreshIfEmpty();
      return this.delegate.toString();
    }

    Collection getDelegate()
    {
      return this.delegate;
    }

    public Iterator iterator()
    {
      refreshIfEmpty();
      return new WrappedIterator();
    }

    public boolean add(Object paramObject)
    {
      refreshIfEmpty();
      boolean bool1 = this.delegate.isEmpty();
      boolean bool2 = this.delegate.add(paramObject);
      if (bool2)
      {
        AbstractMultimap.access$208(AbstractMultimap.this);
        if (bool1)
          addToMap();
      }
      return bool2;
    }

    WrappedCollection getAncestor()
    {
      return this.ancestor;
    }

    public boolean addAll(Collection paramCollection)
    {
      if (paramCollection.isEmpty())
        return false;
      int i = size();
      boolean bool = this.delegate.addAll(paramCollection);
      if (bool)
      {
        int j = this.delegate.size();
        AbstractMultimap.access$212(AbstractMultimap.this, j - i);
        if (i == 0)
          addToMap();
      }
      return bool;
    }

    public boolean contains(Object paramObject)
    {
      refreshIfEmpty();
      return this.delegate.contains(paramObject);
    }

    public boolean containsAll(Collection paramCollection)
    {
      refreshIfEmpty();
      return this.delegate.containsAll(paramCollection);
    }

    public void clear()
    {
      int i = size();
      if (i == 0)
        return;
      this.delegate.clear();
      AbstractMultimap.access$220(AbstractMultimap.this, i);
      removeIfEmpty();
    }

    public boolean remove(Object paramObject)
    {
      refreshIfEmpty();
      boolean bool = this.delegate.remove(paramObject);
      if (bool)
      {
        AbstractMultimap.access$210(AbstractMultimap.this);
        removeIfEmpty();
      }
      return bool;
    }

    public boolean removeAll(Collection paramCollection)
    {
      if (paramCollection.isEmpty())
        return false;
      int i = size();
      boolean bool = this.delegate.removeAll(paramCollection);
      if (bool)
      {
        int j = this.delegate.size();
        AbstractMultimap.access$212(AbstractMultimap.this, j - i);
        removeIfEmpty();
      }
      return bool;
    }

    public boolean retainAll(Collection paramCollection)
    {
      Preconditions.checkNotNull(paramCollection);
      int i = size();
      boolean bool = this.delegate.retainAll(paramCollection);
      if (bool)
      {
        int j = this.delegate.size();
        AbstractMultimap.access$212(AbstractMultimap.this, j - i);
        removeIfEmpty();
      }
      return bool;
    }

    class WrappedIterator
      implements Iterator
    {
      final Iterator delegateIterator;
      final Collection originalDelegate = AbstractMultimap.WrappedCollection.this.delegate;

      WrappedIterator()
      {
        this.delegateIterator = AbstractMultimap.this.iteratorOrListIterator(AbstractMultimap.WrappedCollection.this.delegate);
      }

      WrappedIterator(Iterator arg2)
      {
        Object localObject;
        this.delegateIterator = localObject;
      }

      void validateIterator()
      {
        AbstractMultimap.WrappedCollection.this.refreshIfEmpty();
        if (AbstractMultimap.WrappedCollection.this.delegate != this.originalDelegate)
          throw new ConcurrentModificationException();
      }

      public boolean hasNext()
      {
        validateIterator();
        return this.delegateIterator.hasNext();
      }

      public Object next()
      {
        validateIterator();
        return this.delegateIterator.next();
      }

      public void remove()
      {
        this.delegateIterator.remove();
        AbstractMultimap.access$210(AbstractMultimap.this);
        AbstractMultimap.WrappedCollection.this.removeIfEmpty();
      }

      Iterator getDelegateIterator()
      {
        validateIterator();
        return this.delegateIterator;
      }
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.AbstractMultimap
 * JD-Core Version:    0.6.2
 */