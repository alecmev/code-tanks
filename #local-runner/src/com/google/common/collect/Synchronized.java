package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.RandomAccess;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

@GwtCompatible(emulated=true)
final class Synchronized
{
  private static Collection collection(Collection paramCollection, Object paramObject)
  {
    return new SynchronizedCollection(paramCollection, paramObject, null);
  }

  @VisibleForTesting
  static Set set(Set paramSet, Object paramObject)
  {
    return new SynchronizedSet(paramSet, paramObject);
  }

  private static SortedSet sortedSet(SortedSet paramSortedSet, Object paramObject)
  {
    return new SynchronizedSortedSet(paramSortedSet, paramObject);
  }

  private static List list(List paramList, Object paramObject)
  {
    return (paramList instanceof RandomAccess) ? new SynchronizedRandomAccessList(paramList, paramObject) : new SynchronizedList(paramList, paramObject);
  }

  static Multiset multiset(Multiset paramMultiset, Object paramObject)
  {
    if (((paramMultiset instanceof SynchronizedMultiset)) || ((paramMultiset instanceof ImmutableMultiset)))
      return paramMultiset;
    return new SynchronizedMultiset(paramMultiset, paramObject);
  }

  static Multimap multimap(Multimap paramMultimap, Object paramObject)
  {
    if (((paramMultimap instanceof SynchronizedMultimap)) || ((paramMultimap instanceof ImmutableMultimap)))
      return paramMultimap;
    return new SynchronizedMultimap(paramMultimap, paramObject);
  }

  static ListMultimap listMultimap(ListMultimap paramListMultimap, Object paramObject)
  {
    if (((paramListMultimap instanceof SynchronizedListMultimap)) || ((paramListMultimap instanceof ImmutableListMultimap)))
      return paramListMultimap;
    return new SynchronizedListMultimap(paramListMultimap, paramObject);
  }

  static SetMultimap setMultimap(SetMultimap paramSetMultimap, Object paramObject)
  {
    if (((paramSetMultimap instanceof SynchronizedSetMultimap)) || ((paramSetMultimap instanceof ImmutableSetMultimap)))
      return paramSetMultimap;
    return new SynchronizedSetMultimap(paramSetMultimap, paramObject);
  }

  static SortedSetMultimap sortedSetMultimap(SortedSetMultimap paramSortedSetMultimap, Object paramObject)
  {
    if ((paramSortedSetMultimap instanceof SynchronizedSortedSetMultimap))
      return paramSortedSetMultimap;
    return new SynchronizedSortedSetMultimap(paramSortedSetMultimap, paramObject);
  }

  private static Collection typePreservingCollection(Collection paramCollection, Object paramObject)
  {
    if ((paramCollection instanceof SortedSet))
      return sortedSet((SortedSet)paramCollection, paramObject);
    if ((paramCollection instanceof Set))
      return set((Set)paramCollection, paramObject);
    if ((paramCollection instanceof List))
      return list((List)paramCollection, paramObject);
    return collection(paramCollection, paramObject);
  }

  private static Set typePreservingSet(Set paramSet, Object paramObject)
  {
    if ((paramSet instanceof SortedSet))
      return sortedSet((SortedSet)paramSet, paramObject);
    return set(paramSet, paramObject);
  }

  @VisibleForTesting
  static Map map(Map paramMap, Object paramObject)
  {
    return new SynchronizedMap(paramMap, paramObject);
  }

  static SortedMap sortedMap(SortedMap paramSortedMap, Object paramObject)
  {
    return new SynchronizedSortedMap(paramSortedMap, paramObject);
  }

  static BiMap biMap(BiMap paramBiMap, Object paramObject)
  {
    if (((paramBiMap instanceof SynchronizedBiMap)) || ((paramBiMap instanceof ImmutableBiMap)))
      return paramBiMap;
    return new SynchronizedBiMap(paramBiMap, paramObject, null, null);
  }

  private static class SynchronizedAsMapValues extends Synchronized.SynchronizedCollection
  {
    private static final long serialVersionUID = 0L;

    SynchronizedAsMapValues(Collection paramCollection, Object paramObject)
    {
      super(paramObject, null);
    }

    public Iterator iterator()
    {
      final Iterator localIterator = super.iterator();
      return new ForwardingIterator()
      {
        protected Iterator delegate()
        {
          return localIterator;
        }

        public Collection next()
        {
          return Synchronized.typePreservingCollection((Collection)super.next(), Synchronized.SynchronizedAsMapValues.this.mutex);
        }
      };
    }
  }

  private static class SynchronizedAsMap extends Synchronized.SynchronizedMap
  {
    transient Set asMapEntrySet;
    transient Collection asMapValues;
    private static final long serialVersionUID = 0L;

    SynchronizedAsMap(Map paramMap, Object paramObject)
    {
      super(paramObject);
    }

    public Collection get(Object paramObject)
    {
      synchronized (this.mutex)
      {
        Collection localCollection = (Collection)super.get(paramObject);
        return localCollection == null ? null : Synchronized.typePreservingCollection(localCollection, this.mutex);
      }
    }

    public Set entrySet()
    {
      synchronized (this.mutex)
      {
        if (this.asMapEntrySet == null)
          this.asMapEntrySet = new Synchronized.SynchronizedAsMapEntries(delegate().entrySet(), this.mutex);
        return this.asMapEntrySet;
      }
    }

    public Collection values()
    {
      synchronized (this.mutex)
      {
        if (this.asMapValues == null)
          this.asMapValues = new Synchronized.SynchronizedAsMapValues(delegate().values(), this.mutex);
        return this.asMapValues;
      }
    }

    public boolean containsValue(Object paramObject)
    {
      return values().contains(paramObject);
    }
  }

  @VisibleForTesting
  static class SynchronizedBiMap extends Synchronized.SynchronizedMap
    implements BiMap, Serializable
  {
    private transient Set valueSet;
    private transient BiMap inverse;
    private static final long serialVersionUID = 0L;

    private SynchronizedBiMap(BiMap paramBiMap1, Object paramObject, BiMap paramBiMap2)
    {
      super(paramObject);
      this.inverse = paramBiMap2;
    }

    BiMap delegate()
    {
      return (Serializable)super.delegate();
    }

    public Set values()
    {
      synchronized (this.mutex)
      {
        if (this.valueSet == null)
          this.valueSet = Synchronized.set(delegate().values(), this.mutex);
        return this.valueSet;
      }
    }

    public Object forcePut(Object paramObject1, Object paramObject2)
    {
      synchronized (this.mutex)
      {
        return delegate().forcePut(paramObject1, paramObject2);
      }
    }

    public BiMap inverse()
    {
      synchronized (this.mutex)
      {
        if (this.inverse == null)
          this.inverse = new SynchronizedBiMap(delegate().inverse(), this.mutex, this);
        return this.inverse;
      }
    }
  }

  static class SynchronizedSortedMap extends Synchronized.SynchronizedMap
    implements SortedMap
  {
    private static final long serialVersionUID = 0L;

    SynchronizedSortedMap(SortedMap paramSortedMap, Object paramObject)
    {
      super(paramObject);
    }

    SortedMap delegate()
    {
      return (SortedMap)super.delegate();
    }

    public Comparator comparator()
    {
      synchronized (this.mutex)
      {
        return delegate().comparator();
      }
    }

    public Object firstKey()
    {
      synchronized (this.mutex)
      {
        return delegate().firstKey();
      }
    }

    public SortedMap headMap(Object paramObject)
    {
      synchronized (this.mutex)
      {
        return Synchronized.sortedMap(delegate().headMap(paramObject), this.mutex);
      }
    }

    public Object lastKey()
    {
      synchronized (this.mutex)
      {
        return delegate().lastKey();
      }
    }

    public SortedMap subMap(Object paramObject1, Object paramObject2)
    {
      synchronized (this.mutex)
      {
        return Synchronized.sortedMap(delegate().subMap(paramObject1, paramObject2), this.mutex);
      }
    }

    public SortedMap tailMap(Object paramObject)
    {
      synchronized (this.mutex)
      {
        return Synchronized.sortedMap(delegate().tailMap(paramObject), this.mutex);
      }
    }
  }

  private static class SynchronizedMap extends Synchronized.SynchronizedObject
    implements Map
  {
    transient Set keySet;
    transient Collection values;
    transient Set entrySet;
    private static final long serialVersionUID = 0L;

    SynchronizedMap(Map paramMap, Object paramObject)
    {
      super(paramObject);
    }

    Map delegate()
    {
      return (Map)super.delegate();
    }

    public void clear()
    {
      synchronized (this.mutex)
      {
        delegate().clear();
      }
    }

    public boolean containsKey(Object paramObject)
    {
      synchronized (this.mutex)
      {
        return delegate().containsKey(paramObject);
      }
    }

    public boolean containsValue(Object paramObject)
    {
      synchronized (this.mutex)
      {
        return delegate().containsValue(paramObject);
      }
    }

    public Set entrySet()
    {
      synchronized (this.mutex)
      {
        if (this.entrySet == null)
          this.entrySet = Synchronized.set(delegate().entrySet(), this.mutex);
        return this.entrySet;
      }
    }

    public Object get(Object paramObject)
    {
      synchronized (this.mutex)
      {
        return delegate().get(paramObject);
      }
    }

    public boolean isEmpty()
    {
      synchronized (this.mutex)
      {
        return delegate().isEmpty();
      }
    }

    public Set keySet()
    {
      synchronized (this.mutex)
      {
        if (this.keySet == null)
          this.keySet = Synchronized.set(delegate().keySet(), this.mutex);
        return this.keySet;
      }
    }

    public Object put(Object paramObject1, Object paramObject2)
    {
      synchronized (this.mutex)
      {
        return delegate().put(paramObject1, paramObject2);
      }
    }

    public void putAll(Map paramMap)
    {
      synchronized (this.mutex)
      {
        delegate().putAll(paramMap);
      }
    }

    public Object remove(Object paramObject)
    {
      synchronized (this.mutex)
      {
        return delegate().remove(paramObject);
      }
    }

    public int size()
    {
      synchronized (this.mutex)
      {
        return delegate().size();
      }
    }

    public Collection values()
    {
      synchronized (this.mutex)
      {
        if (this.values == null)
          this.values = Synchronized.collection(delegate().values(), this.mutex);
        return this.values;
      }
    }

    public boolean equals(Object paramObject)
    {
      if (paramObject == this)
        return true;
      synchronized (this.mutex)
      {
        return delegate().equals(paramObject);
      }
    }

    public int hashCode()
    {
      synchronized (this.mutex)
      {
        return delegate().hashCode();
      }
    }
  }

  private static class SynchronizedAsMapEntries extends Synchronized.SynchronizedSet
  {
    private static final long serialVersionUID = 0L;

    SynchronizedAsMapEntries(Set paramSet, Object paramObject)
    {
      super(paramObject);
    }

    public Iterator iterator()
    {
      final Iterator localIterator = super.iterator();
      return new ForwardingIterator()
      {
        protected Iterator delegate()
        {
          return localIterator;
        }

        public Map.Entry next()
        {
          final Map.Entry localEntry = (Map.Entry)super.next();
          return new ForwardingMapEntry()
          {
            protected Map.Entry delegate()
            {
              return localEntry;
            }

            public Collection getValue()
            {
              return Synchronized.typePreservingCollection((Collection)localEntry.getValue(), Synchronized.SynchronizedAsMapEntries.this.mutex);
            }
          };
        }
      };
    }

    public Object[] toArray()
    {
      synchronized (this.mutex)
      {
        return ObjectArrays.toArrayImpl(delegate());
      }
    }

    public Object[] toArray(Object[] paramArrayOfObject)
    {
      synchronized (this.mutex)
      {
        return ObjectArrays.toArrayImpl(delegate(), paramArrayOfObject);
      }
    }

    public boolean contains(Object paramObject)
    {
      synchronized (this.mutex)
      {
        return Maps.containsEntryImpl(delegate(), paramObject);
      }
    }

    public boolean containsAll(Collection paramCollection)
    {
      synchronized (this.mutex)
      {
        return Collections2.containsAllImpl(delegate(), paramCollection);
      }
    }

    public boolean equals(Object paramObject)
    {
      if (paramObject == this)
        return true;
      synchronized (this.mutex)
      {
        return Sets.equalsImpl(delegate(), paramObject);
      }
    }

    public boolean remove(Object paramObject)
    {
      synchronized (this.mutex)
      {
        return Maps.removeEntryImpl(delegate(), paramObject);
      }
    }

    public boolean removeAll(Collection paramCollection)
    {
      synchronized (this.mutex)
      {
        return Iterators.removeAll(delegate().iterator(), paramCollection);
      }
    }

    public boolean retainAll(Collection paramCollection)
    {
      synchronized (this.mutex)
      {
        return Iterators.retainAll(delegate().iterator(), paramCollection);
      }
    }
  }

  private static class SynchronizedSortedSetMultimap extends Synchronized.SynchronizedSetMultimap
    implements SortedSetMultimap
  {
    private static final long serialVersionUID = 0L;

    SynchronizedSortedSetMultimap(SortedSetMultimap paramSortedSetMultimap, Object paramObject)
    {
      super(paramObject);
    }

    SortedSetMultimap delegate()
    {
      return (SortedSetMultimap)super.delegate();
    }

    public SortedSet get(Object paramObject)
    {
      synchronized (this.mutex)
      {
        return Synchronized.sortedSet(delegate().get(paramObject), this.mutex);
      }
    }

    public SortedSet removeAll(Object paramObject)
    {
      synchronized (this.mutex)
      {
        return delegate().removeAll(paramObject);
      }
    }

    public SortedSet replaceValues(Object paramObject, Iterable paramIterable)
    {
      synchronized (this.mutex)
      {
        return delegate().replaceValues(paramObject, paramIterable);
      }
    }

    public Comparator valueComparator()
    {
      synchronized (this.mutex)
      {
        return delegate().valueComparator();
      }
    }
  }

  private static class SynchronizedSetMultimap extends Synchronized.SynchronizedMultimap
    implements SetMultimap
  {
    transient Set entrySet;
    private static final long serialVersionUID = 0L;

    SynchronizedSetMultimap(SetMultimap paramSetMultimap, Object paramObject)
    {
      super(paramObject);
    }

    SetMultimap delegate()
    {
      return (SetMultimap)super.delegate();
    }

    public Set get(Object paramObject)
    {
      synchronized (this.mutex)
      {
        return Synchronized.set(delegate().get(paramObject), this.mutex);
      }
    }

    public Set removeAll(Object paramObject)
    {
      synchronized (this.mutex)
      {
        return delegate().removeAll(paramObject);
      }
    }

    public Set replaceValues(Object paramObject, Iterable paramIterable)
    {
      synchronized (this.mutex)
      {
        return delegate().replaceValues(paramObject, paramIterable);
      }
    }

    public Set entries()
    {
      synchronized (this.mutex)
      {
        if (this.entrySet == null)
          this.entrySet = Synchronized.set(delegate().entries(), this.mutex);
        return this.entrySet;
      }
    }
  }

  private static class SynchronizedListMultimap extends Synchronized.SynchronizedMultimap
    implements ListMultimap
  {
    private static final long serialVersionUID = 0L;

    SynchronizedListMultimap(ListMultimap paramListMultimap, Object paramObject)
    {
      super(paramObject);
    }

    ListMultimap delegate()
    {
      return (ListMultimap)super.delegate();
    }

    public List get(Object paramObject)
    {
      synchronized (this.mutex)
      {
        return Synchronized.list(delegate().get(paramObject), this.mutex);
      }
    }

    public List removeAll(Object paramObject)
    {
      synchronized (this.mutex)
      {
        return delegate().removeAll(paramObject);
      }
    }

    public List replaceValues(Object paramObject, Iterable paramIterable)
    {
      synchronized (this.mutex)
      {
        return delegate().replaceValues(paramObject, paramIterable);
      }
    }
  }

  private static class SynchronizedMultimap extends Synchronized.SynchronizedObject
    implements Multimap
  {
    transient Set keySet;
    transient Collection valuesCollection;
    transient Collection entries;
    transient Map asMap;
    transient Multiset keys;
    private static final long serialVersionUID = 0L;

    Multimap delegate()
    {
      return (Multimap)super.delegate();
    }

    SynchronizedMultimap(Multimap paramMultimap, Object paramObject)
    {
      super(paramObject);
    }

    public int size()
    {
      synchronized (this.mutex)
      {
        return delegate().size();
      }
    }

    public boolean isEmpty()
    {
      synchronized (this.mutex)
      {
        return delegate().isEmpty();
      }
    }

    public boolean containsKey(Object paramObject)
    {
      synchronized (this.mutex)
      {
        return delegate().containsKey(paramObject);
      }
    }

    public boolean containsValue(Object paramObject)
    {
      synchronized (this.mutex)
      {
        return delegate().containsValue(paramObject);
      }
    }

    public boolean containsEntry(Object paramObject1, Object paramObject2)
    {
      synchronized (this.mutex)
      {
        return delegate().containsEntry(paramObject1, paramObject2);
      }
    }

    public Collection get(Object paramObject)
    {
      synchronized (this.mutex)
      {
        return Synchronized.typePreservingCollection(delegate().get(paramObject), this.mutex);
      }
    }

    public boolean put(Object paramObject1, Object paramObject2)
    {
      synchronized (this.mutex)
      {
        return delegate().put(paramObject1, paramObject2);
      }
    }

    public boolean putAll(Object paramObject, Iterable paramIterable)
    {
      synchronized (this.mutex)
      {
        return delegate().putAll(paramObject, paramIterable);
      }
    }

    public boolean putAll(Multimap paramMultimap)
    {
      synchronized (this.mutex)
      {
        return delegate().putAll(paramMultimap);
      }
    }

    public Collection replaceValues(Object paramObject, Iterable paramIterable)
    {
      synchronized (this.mutex)
      {
        return delegate().replaceValues(paramObject, paramIterable);
      }
    }

    public boolean remove(Object paramObject1, Object paramObject2)
    {
      synchronized (this.mutex)
      {
        return delegate().remove(paramObject1, paramObject2);
      }
    }

    public Collection removeAll(Object paramObject)
    {
      synchronized (this.mutex)
      {
        return delegate().removeAll(paramObject);
      }
    }

    public void clear()
    {
      synchronized (this.mutex)
      {
        delegate().clear();
      }
    }

    public Set keySet()
    {
      synchronized (this.mutex)
      {
        if (this.keySet == null)
          this.keySet = Synchronized.typePreservingSet(delegate().keySet(), this.mutex);
        return this.keySet;
      }
    }

    public Collection values()
    {
      synchronized (this.mutex)
      {
        if (this.valuesCollection == null)
          this.valuesCollection = Synchronized.collection(delegate().values(), this.mutex);
        return this.valuesCollection;
      }
    }

    public Collection entries()
    {
      synchronized (this.mutex)
      {
        if (this.entries == null)
          this.entries = Synchronized.typePreservingCollection(delegate().entries(), this.mutex);
        return this.entries;
      }
    }

    public Map asMap()
    {
      synchronized (this.mutex)
      {
        if (this.asMap == null)
          this.asMap = new Synchronized.SynchronizedAsMap(delegate().asMap(), this.mutex);
        return this.asMap;
      }
    }

    public Multiset keys()
    {
      synchronized (this.mutex)
      {
        if (this.keys == null)
          this.keys = Synchronized.multiset(delegate().keys(), this.mutex);
        return this.keys;
      }
    }

    public boolean equals(Object paramObject)
    {
      if (paramObject == this)
        return true;
      synchronized (this.mutex)
      {
        return delegate().equals(paramObject);
      }
    }

    public int hashCode()
    {
      synchronized (this.mutex)
      {
        return delegate().hashCode();
      }
    }
  }

  private static class SynchronizedMultiset extends Synchronized.SynchronizedCollection
    implements Multiset
  {
    transient Set elementSet;
    transient Set entrySet;
    private static final long serialVersionUID = 0L;

    SynchronizedMultiset(Multiset paramMultiset, Object paramObject)
    {
      super(paramObject, null);
    }

    Multiset delegate()
    {
      return (Multiset)super.delegate();
    }

    public int count(Object paramObject)
    {
      synchronized (this.mutex)
      {
        return delegate().count(paramObject);
      }
    }

    public int add(Object paramObject, int paramInt)
    {
      synchronized (this.mutex)
      {
        return delegate().add(paramObject, paramInt);
      }
    }

    public int remove(Object paramObject, int paramInt)
    {
      synchronized (this.mutex)
      {
        return delegate().remove(paramObject, paramInt);
      }
    }

    public int setCount(Object paramObject, int paramInt)
    {
      synchronized (this.mutex)
      {
        return delegate().setCount(paramObject, paramInt);
      }
    }

    public boolean setCount(Object paramObject, int paramInt1, int paramInt2)
    {
      synchronized (this.mutex)
      {
        return delegate().setCount(paramObject, paramInt1, paramInt2);
      }
    }

    public Set elementSet()
    {
      synchronized (this.mutex)
      {
        if (this.elementSet == null)
          this.elementSet = Synchronized.typePreservingSet(delegate().elementSet(), this.mutex);
        return this.elementSet;
      }
    }

    public Set entrySet()
    {
      synchronized (this.mutex)
      {
        if (this.entrySet == null)
          this.entrySet = Synchronized.typePreservingSet(delegate().entrySet(), this.mutex);
        return this.entrySet;
      }
    }

    public boolean equals(Object paramObject)
    {
      if (paramObject == this)
        return true;
      synchronized (this.mutex)
      {
        return delegate().equals(paramObject);
      }
    }

    public int hashCode()
    {
      synchronized (this.mutex)
      {
        return delegate().hashCode();
      }
    }
  }

  private static class SynchronizedRandomAccessList extends Synchronized.SynchronizedList
    implements RandomAccess
  {
    private static final long serialVersionUID = 0L;

    SynchronizedRandomAccessList(List paramList, Object paramObject)
    {
      super(paramObject);
    }
  }

  private static class SynchronizedList extends Synchronized.SynchronizedCollection
    implements List
  {
    private static final long serialVersionUID = 0L;

    SynchronizedList(List paramList, Object paramObject)
    {
      super(paramObject, null);
    }

    List delegate()
    {
      return (List)super.delegate();
    }

    public void add(int paramInt, Object paramObject)
    {
      synchronized (this.mutex)
      {
        delegate().add(paramInt, paramObject);
      }
    }

    public boolean addAll(int paramInt, Collection paramCollection)
    {
      synchronized (this.mutex)
      {
        return delegate().addAll(paramInt, paramCollection);
      }
    }

    public Object get(int paramInt)
    {
      synchronized (this.mutex)
      {
        return delegate().get(paramInt);
      }
    }

    public int indexOf(Object paramObject)
    {
      synchronized (this.mutex)
      {
        return delegate().indexOf(paramObject);
      }
    }

    public int lastIndexOf(Object paramObject)
    {
      synchronized (this.mutex)
      {
        return delegate().lastIndexOf(paramObject);
      }
    }

    public ListIterator listIterator()
    {
      return delegate().listIterator();
    }

    public ListIterator listIterator(int paramInt)
    {
      return delegate().listIterator(paramInt);
    }

    public Object remove(int paramInt)
    {
      synchronized (this.mutex)
      {
        return delegate().remove(paramInt);
      }
    }

    public Object set(int paramInt, Object paramObject)
    {
      synchronized (this.mutex)
      {
        return delegate().set(paramInt, paramObject);
      }
    }

    public List subList(int paramInt1, int paramInt2)
    {
      synchronized (this.mutex)
      {
        return Synchronized.list(delegate().subList(paramInt1, paramInt2), this.mutex);
      }
    }

    public boolean equals(Object paramObject)
    {
      if (paramObject == this)
        return true;
      synchronized (this.mutex)
      {
        return delegate().equals(paramObject);
      }
    }

    public int hashCode()
    {
      synchronized (this.mutex)
      {
        return delegate().hashCode();
      }
    }
  }

  static class SynchronizedSortedSet extends Synchronized.SynchronizedSet
    implements SortedSet
  {
    private static final long serialVersionUID = 0L;

    SynchronizedSortedSet(SortedSet paramSortedSet, Object paramObject)
    {
      super(paramObject);
    }

    SortedSet delegate()
    {
      return (SortedSet)super.delegate();
    }

    public Comparator comparator()
    {
      synchronized (this.mutex)
      {
        return delegate().comparator();
      }
    }

    public SortedSet subSet(Object paramObject1, Object paramObject2)
    {
      synchronized (this.mutex)
      {
        return Synchronized.sortedSet(delegate().subSet(paramObject1, paramObject2), this.mutex);
      }
    }

    public SortedSet headSet(Object paramObject)
    {
      synchronized (this.mutex)
      {
        return Synchronized.sortedSet(delegate().headSet(paramObject), this.mutex);
      }
    }

    public SortedSet tailSet(Object paramObject)
    {
      synchronized (this.mutex)
      {
        return Synchronized.sortedSet(delegate().tailSet(paramObject), this.mutex);
      }
    }

    public Object first()
    {
      synchronized (this.mutex)
      {
        return delegate().first();
      }
    }

    public Object last()
    {
      synchronized (this.mutex)
      {
        return delegate().last();
      }
    }
  }

  static class SynchronizedSet extends Synchronized.SynchronizedCollection
    implements Set
  {
    private static final long serialVersionUID = 0L;

    SynchronizedSet(Set paramSet, Object paramObject)
    {
      super(paramObject, null);
    }

    Set delegate()
    {
      return (Set)super.delegate();
    }

    public boolean equals(Object paramObject)
    {
      if (paramObject == this)
        return true;
      synchronized (this.mutex)
      {
        return delegate().equals(paramObject);
      }
    }

    public int hashCode()
    {
      synchronized (this.mutex)
      {
        return delegate().hashCode();
      }
    }
  }

  @VisibleForTesting
  static class SynchronizedCollection extends Synchronized.SynchronizedObject
    implements Collection
  {
    private static final long serialVersionUID = 0L;

    private SynchronizedCollection(Collection paramCollection, Object paramObject)
    {
      super(paramObject);
    }

    Collection delegate()
    {
      return (Collection)super.delegate();
    }

    public boolean add(Object paramObject)
    {
      synchronized (this.mutex)
      {
        return delegate().add(paramObject);
      }
    }

    public boolean addAll(Collection paramCollection)
    {
      synchronized (this.mutex)
      {
        return delegate().addAll(paramCollection);
      }
    }

    public void clear()
    {
      synchronized (this.mutex)
      {
        delegate().clear();
      }
    }

    public boolean contains(Object paramObject)
    {
      synchronized (this.mutex)
      {
        return delegate().contains(paramObject);
      }
    }

    public boolean containsAll(Collection paramCollection)
    {
      synchronized (this.mutex)
      {
        return delegate().containsAll(paramCollection);
      }
    }

    public boolean isEmpty()
    {
      synchronized (this.mutex)
      {
        return delegate().isEmpty();
      }
    }

    public Iterator iterator()
    {
      return delegate().iterator();
    }

    public boolean remove(Object paramObject)
    {
      synchronized (this.mutex)
      {
        return delegate().remove(paramObject);
      }
    }

    public boolean removeAll(Collection paramCollection)
    {
      synchronized (this.mutex)
      {
        return delegate().removeAll(paramCollection);
      }
    }

    public boolean retainAll(Collection paramCollection)
    {
      synchronized (this.mutex)
      {
        return delegate().retainAll(paramCollection);
      }
    }

    public int size()
    {
      synchronized (this.mutex)
      {
        return delegate().size();
      }
    }

    public Object[] toArray()
    {
      synchronized (this.mutex)
      {
        return delegate().toArray();
      }
    }

    public Object[] toArray(Object[] paramArrayOfObject)
    {
      synchronized (this.mutex)
      {
        return delegate().toArray(paramArrayOfObject);
      }
    }
  }

  static class SynchronizedObject
    implements Serializable
  {
    final Object delegate;
    final Object mutex;

    @GwtIncompatible("not needed in emulated source")
    private static final long serialVersionUID = 0L;

    SynchronizedObject(Object paramObject1, Object paramObject2)
    {
      this.delegate = Preconditions.checkNotNull(paramObject1);
      this.mutex = (paramObject2 == null ? this : paramObject2);
    }

    Object delegate()
    {
      return this.delegate;
    }

    public String toString()
    {
      synchronized (this.mutex)
      {
        return this.delegate.toString();
      }
    }

    @GwtIncompatible("java.io.ObjectOutputStream")
    private void writeObject(ObjectOutputStream paramObjectOutputStream)
      throws IOException
    {
      synchronized (this.mutex)
      {
        paramObjectOutputStream.defaultWriteObject();
      }
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.Synchronized
 * JD-Core Version:    0.6.2
 */