package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Equivalence;
import com.google.common.base.Equivalences;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Joiner.MapJoiner;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Properties;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentMap;

@GwtCompatible(emulated=true)
public final class Maps
{
  static final Joiner.MapJoiner STANDARD_JOINER = Collections2.STANDARD_JOINER.withKeyValueSeparator("=");

  public static HashMap newHashMap()
  {
    return new HashMap();
  }

  public static HashMap newHashMapWithExpectedSize(int paramInt)
  {
    return new HashMap(capacity(paramInt));
  }

  static int capacity(int paramInt)
  {
    if (paramInt < 3)
    {
      Preconditions.checkArgument(paramInt >= 0);
      return paramInt + 1;
    }
    if (paramInt < 1073741824)
      return paramInt + paramInt / 3;
    return 2147483647;
  }

  public static HashMap newHashMap(Map paramMap)
  {
    return new HashMap(paramMap);
  }

  public static LinkedHashMap newLinkedHashMap()
  {
    return new LinkedHashMap();
  }

  public static LinkedHashMap newLinkedHashMap(Map paramMap)
  {
    return new LinkedHashMap(paramMap);
  }

  public static ConcurrentMap newConcurrentMap()
  {
    return new MapMaker().makeMap();
  }

  public static TreeMap newTreeMap()
  {
    return new TreeMap();
  }

  public static TreeMap newTreeMap(SortedMap paramSortedMap)
  {
    return new TreeMap(paramSortedMap);
  }

  public static TreeMap newTreeMap(Comparator paramComparator)
  {
    return new TreeMap(paramComparator);
  }

  public static EnumMap newEnumMap(Class paramClass)
  {
    return new EnumMap((Class)Preconditions.checkNotNull(paramClass));
  }

  public static EnumMap newEnumMap(Map paramMap)
  {
    return new EnumMap(paramMap);
  }

  public static IdentityHashMap newIdentityHashMap()
  {
    return new IdentityHashMap();
  }

  public static MapDifference difference(Map paramMap1, Map paramMap2)
  {
    if ((paramMap1 instanceof SortedMap))
    {
      SortedMap localSortedMap = (SortedMap)paramMap1;
      SortedMapDifference localSortedMapDifference = difference(localSortedMap, paramMap2);
      return localSortedMapDifference;
    }
    return difference(paramMap1, paramMap2, Equivalences.equals());
  }

  @Beta
  public static MapDifference difference(Map paramMap1, Map paramMap2, Equivalence paramEquivalence)
  {
    Preconditions.checkNotNull(paramEquivalence);
    HashMap localHashMap1 = newHashMap();
    HashMap localHashMap2 = new HashMap(paramMap2);
    HashMap localHashMap3 = newHashMap();
    HashMap localHashMap4 = newHashMap();
    int i = 1;
    Iterator localIterator = paramMap1.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      Object localObject1 = localEntry.getKey();
      Object localObject2 = localEntry.getValue();
      if (paramMap2.containsKey(localObject1))
      {
        Object localObject3 = localHashMap2.remove(localObject1);
        if (paramEquivalence.equivalent(localObject2, localObject3))
        {
          localHashMap3.put(localObject1, localObject2);
        }
        else
        {
          i = 0;
          localHashMap4.put(localObject1, ValueDifferenceImpl.create(localObject2, localObject3));
        }
      }
      else
      {
        i = 0;
        localHashMap1.put(localObject1, localObject2);
      }
    }
    boolean bool = (i != 0) && (localHashMap2.isEmpty());
    return mapDifference(bool, localHashMap1, localHashMap2, localHashMap3, localHashMap4);
  }

  private static MapDifference mapDifference(boolean paramBoolean, Map paramMap1, Map paramMap2, Map paramMap3, Map paramMap4)
  {
    return new MapDifferenceImpl(paramBoolean, Collections.unmodifiableMap(paramMap1), Collections.unmodifiableMap(paramMap2), Collections.unmodifiableMap(paramMap3), Collections.unmodifiableMap(paramMap4));
  }

  public static SortedMapDifference difference(SortedMap paramSortedMap, Map paramMap)
  {
    Preconditions.checkNotNull(paramSortedMap);
    Preconditions.checkNotNull(paramMap);
    Comparator localComparator = orNaturalOrder(paramSortedMap.comparator());
    TreeMap localTreeMap1 = newTreeMap(localComparator);
    TreeMap localTreeMap2 = newTreeMap(localComparator);
    localTreeMap2.putAll(paramMap);
    TreeMap localTreeMap3 = newTreeMap(localComparator);
    TreeMap localTreeMap4 = newTreeMap(localComparator);
    int i = 1;
    Iterator localIterator = paramSortedMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      Object localObject1 = localEntry.getKey();
      Object localObject2 = localEntry.getValue();
      if (paramMap.containsKey(localObject1))
      {
        Object localObject3 = localTreeMap2.remove(localObject1);
        if (Objects.equal(localObject2, localObject3))
        {
          localTreeMap3.put(localObject1, localObject2);
        }
        else
        {
          i = 0;
          localTreeMap4.put(localObject1, ValueDifferenceImpl.create(localObject2, localObject3));
        }
      }
      else
      {
        i = 0;
        localTreeMap1.put(localObject1, localObject2);
      }
    }
    boolean bool = (i != 0) && (localTreeMap2.isEmpty());
    return sortedMapDifference(bool, localTreeMap1, localTreeMap2, localTreeMap3, localTreeMap4);
  }

  private static SortedMapDifference sortedMapDifference(boolean paramBoolean, SortedMap paramSortedMap1, SortedMap paramSortedMap2, SortedMap paramSortedMap3, SortedMap paramSortedMap4)
  {
    return new SortedMapDifferenceImpl(paramBoolean, Collections.unmodifiableSortedMap(paramSortedMap1), Collections.unmodifiableSortedMap(paramSortedMap2), Collections.unmodifiableSortedMap(paramSortedMap3), Collections.unmodifiableSortedMap(paramSortedMap4));
  }

  static Comparator orNaturalOrder(Comparator paramComparator)
  {
    if (paramComparator != null)
      return paramComparator;
    return Ordering.natural();
  }

  public static ImmutableMap uniqueIndex(Iterable paramIterable, Function paramFunction)
  {
    return uniqueIndex(paramIterable.iterator(), paramFunction);
  }

  public static ImmutableMap uniqueIndex(Iterator paramIterator, Function paramFunction)
  {
    Preconditions.checkNotNull(paramFunction);
    ImmutableMap.Builder localBuilder = ImmutableMap.builder();
    while (paramIterator.hasNext())
    {
      Object localObject = paramIterator.next();
      localBuilder.put(paramFunction.apply(localObject), localObject);
    }
    return localBuilder.build();
  }

  @GwtIncompatible("java.util.Properties")
  public static ImmutableMap fromProperties(Properties paramProperties)
  {
    ImmutableMap.Builder localBuilder = ImmutableMap.builder();
    Enumeration localEnumeration = paramProperties.propertyNames();
    while (localEnumeration.hasMoreElements())
    {
      String str = (String)localEnumeration.nextElement();
      localBuilder.put(str, paramProperties.getProperty(str));
    }
    return localBuilder.build();
  }

  @GwtCompatible(serializable=true)
  public static Map.Entry immutableEntry(Object paramObject1, Object paramObject2)
  {
    return new ImmutableEntry(paramObject1, paramObject2);
  }

  static Set unmodifiableEntrySet(Set paramSet)
  {
    return new UnmodifiableEntrySet(Collections.unmodifiableSet(paramSet));
  }

  static Map.Entry unmodifiableEntry(Map.Entry paramEntry)
  {
    Preconditions.checkNotNull(paramEntry);
    return new AbstractMapEntry()
    {
      public Object getKey()
      {
        return this.val$entry.getKey();
      }

      public Object getValue()
      {
        return this.val$entry.getValue();
      }
    };
  }

  public static BiMap synchronizedBiMap(BiMap paramBiMap)
  {
    return Synchronized.biMap(paramBiMap, null);
  }

  public static BiMap unmodifiableBiMap(BiMap paramBiMap)
  {
    return new UnmodifiableBiMap(paramBiMap, null);
  }

  public static Map transformValues(Map paramMap, Function paramFunction)
  {
    Preconditions.checkNotNull(paramFunction);
    EntryTransformer local2 = new EntryTransformer()
    {
      public Object transformEntry(Object paramAnonymousObject1, Object paramAnonymousObject2)
      {
        return this.val$function.apply(paramAnonymousObject2);
      }
    };
    return transformEntries(paramMap, local2);
  }

  @Beta
  public static SortedMap transformValues(SortedMap paramSortedMap, Function paramFunction)
  {
    Preconditions.checkNotNull(paramFunction);
    EntryTransformer local3 = new EntryTransformer()
    {
      public Object transformEntry(Object paramAnonymousObject1, Object paramAnonymousObject2)
      {
        return this.val$function.apply(paramAnonymousObject2);
      }
    };
    return transformEntries(paramSortedMap, local3);
  }

  public static Map transformEntries(Map paramMap, EntryTransformer paramEntryTransformer)
  {
    if ((paramMap instanceof SortedMap))
      return transformEntries((SortedMap)paramMap, paramEntryTransformer);
    return new TransformedEntriesMap(paramMap, paramEntryTransformer);
  }

  @Beta
  public static SortedMap transformEntries(SortedMap paramSortedMap, EntryTransformer paramEntryTransformer)
  {
    return new TransformedEntriesSortedMap(paramSortedMap, paramEntryTransformer);
  }

  public static Map filterKeys(Map paramMap, Predicate paramPredicate)
  {
    if ((paramMap instanceof SortedMap))
      return filterKeys((SortedMap)paramMap, paramPredicate);
    Preconditions.checkNotNull(paramPredicate);
    Predicate local4 = new Predicate()
    {
      public boolean apply(Map.Entry paramAnonymousEntry)
      {
        return this.val$keyPredicate.apply(paramAnonymousEntry.getKey());
      }
    };
    return (paramMap instanceof AbstractFilteredMap) ? filterFiltered((AbstractFilteredMap)paramMap, local4) : new FilteredKeyMap((Map)Preconditions.checkNotNull(paramMap), paramPredicate, local4);
  }

  @Beta
  public static SortedMap filterKeys(SortedMap paramSortedMap, Predicate paramPredicate)
  {
    Preconditions.checkNotNull(paramPredicate);
    Predicate local5 = new Predicate()
    {
      public boolean apply(Map.Entry paramAnonymousEntry)
      {
        return this.val$keyPredicate.apply(paramAnonymousEntry.getKey());
      }
    };
    return filterEntries(paramSortedMap, local5);
  }

  public static Map filterValues(Map paramMap, Predicate paramPredicate)
  {
    if ((paramMap instanceof SortedMap))
      return filterValues((SortedMap)paramMap, paramPredicate);
    Preconditions.checkNotNull(paramPredicate);
    Predicate local6 = new Predicate()
    {
      public boolean apply(Map.Entry paramAnonymousEntry)
      {
        return this.val$valuePredicate.apply(paramAnonymousEntry.getValue());
      }
    };
    return filterEntries(paramMap, local6);
  }

  @Beta
  public static SortedMap filterValues(SortedMap paramSortedMap, Predicate paramPredicate)
  {
    Preconditions.checkNotNull(paramPredicate);
    Predicate local7 = new Predicate()
    {
      public boolean apply(Map.Entry paramAnonymousEntry)
      {
        return this.val$valuePredicate.apply(paramAnonymousEntry.getValue());
      }
    };
    return filterEntries(paramSortedMap, local7);
  }

  public static Map filterEntries(Map paramMap, Predicate paramPredicate)
  {
    if ((paramMap instanceof SortedMap))
      return filterEntries((SortedMap)paramMap, paramPredicate);
    Preconditions.checkNotNull(paramPredicate);
    return (paramMap instanceof AbstractFilteredMap) ? filterFiltered((AbstractFilteredMap)paramMap, paramPredicate) : new FilteredEntryMap((Map)Preconditions.checkNotNull(paramMap), paramPredicate);
  }

  @Beta
  public static SortedMap filterEntries(SortedMap paramSortedMap, Predicate paramPredicate)
  {
    Preconditions.checkNotNull(paramPredicate);
    return (paramSortedMap instanceof FilteredEntrySortedMap) ? filterFiltered((FilteredEntrySortedMap)paramSortedMap, paramPredicate) : new FilteredEntrySortedMap((SortedMap)Preconditions.checkNotNull(paramSortedMap), paramPredicate);
  }

  private static Map filterFiltered(AbstractFilteredMap paramAbstractFilteredMap, Predicate paramPredicate)
  {
    Predicate localPredicate = Predicates.and(paramAbstractFilteredMap.predicate, paramPredicate);
    return new FilteredEntryMap(paramAbstractFilteredMap.unfiltered, localPredicate);
  }

  private static SortedMap filterFiltered(FilteredEntrySortedMap paramFilteredEntrySortedMap, Predicate paramPredicate)
  {
    Predicate localPredicate = Predicates.and(paramFilteredEntrySortedMap.predicate, paramPredicate);
    return new FilteredEntrySortedMap(paramFilteredEntrySortedMap.sortedMap(), localPredicate);
  }

  @GwtIncompatible("NavigableMap")
  public static NavigableMap unmodifiableNavigableMap(NavigableMap paramNavigableMap)
  {
    Preconditions.checkNotNull(paramNavigableMap);
    if ((paramNavigableMap instanceof UnmodifiableNavigableMap))
      return paramNavigableMap;
    return new UnmodifiableNavigableMap(paramNavigableMap);
  }

  private static Map.Entry unmodifiableOrNull(Map.Entry paramEntry)
  {
    return paramEntry == null ? null : unmodifiableEntry(paramEntry);
  }

  static Object safeGet(Map paramMap, Object paramObject)
  {
    try
    {
      return paramMap.get(paramObject);
    }
    catch (ClassCastException localClassCastException)
    {
    }
    return null;
  }

  static boolean safeContainsKey(Map paramMap, Object paramObject)
  {
    try
    {
      return paramMap.containsKey(paramObject);
    }
    catch (ClassCastException localClassCastException)
    {
    }
    return false;
  }

  static boolean containsEntryImpl(Collection paramCollection, Object paramObject)
  {
    if (!(paramObject instanceof Map.Entry))
      return false;
    return paramCollection.contains(unmodifiableEntry((Map.Entry)paramObject));
  }

  static boolean removeEntryImpl(Collection paramCollection, Object paramObject)
  {
    if (!(paramObject instanceof Map.Entry))
      return false;
    return paramCollection.remove(unmodifiableEntry((Map.Entry)paramObject));
  }

  static boolean equalsImpl(Map paramMap, Object paramObject)
  {
    if (paramMap == paramObject)
      return true;
    if ((paramObject instanceof Map))
    {
      Map localMap = (Map)paramObject;
      return paramMap.entrySet().equals(localMap.entrySet());
    }
    return false;
  }

  static int hashCodeImpl(Map paramMap)
  {
    return Sets.hashCodeImpl(paramMap.entrySet());
  }

  static String toStringImpl(Map paramMap)
  {
    StringBuilder localStringBuilder = Collections2.newStringBuilderForCollection(paramMap.size()).append('{');
    STANDARD_JOINER.appendTo(localStringBuilder, paramMap);
    return '}';
  }

  static void putAllImpl(Map paramMap1, Map paramMap2)
  {
    Iterator localIterator = paramMap2.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      paramMap1.put(localEntry.getKey(), localEntry.getValue());
    }
  }

  static boolean containsKeyImpl(Map paramMap, Object paramObject)
  {
    Iterator localIterator = paramMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if (Objects.equal(localEntry.getKey(), paramObject))
        return true;
    }
    return false;
  }

  static boolean containsValueImpl(Map paramMap, Object paramObject)
  {
    Iterator localIterator = paramMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if (Objects.equal(localEntry.getValue(), paramObject))
        return true;
    }
    return false;
  }

  static Iterator keyIterator(Iterator paramIterator)
  {
    return new TransformedIterator(paramIterator)
    {
      Object transform(Map.Entry paramAnonymousEntry)
      {
        return paramAnonymousEntry.getKey();
      }
    };
  }

  static Object keyOrNull(Map.Entry paramEntry)
  {
    return paramEntry == null ? null : paramEntry.getKey();
  }

  static Iterator valueIterator(Iterator paramIterator)
  {
    return new TransformedIterator(paramIterator)
    {
      Object transform(Map.Entry paramAnonymousEntry)
      {
        return paramAnonymousEntry.getValue();
      }
    };
  }

  static UnmodifiableIterator valueIterator(UnmodifiableIterator paramUnmodifiableIterator)
  {
    return new UnmodifiableIterator()
    {
      public boolean hasNext()
      {
        return this.val$entryIterator.hasNext();
      }

      public Object next()
      {
        return ((Map.Entry)this.val$entryIterator.next()).getValue();
      }
    };
  }

  @GwtIncompatible("NavigableMap")
  static abstract class DescendingMap extends ForwardingMap
    implements NavigableMap
  {
    private transient Comparator comparator;
    private transient Set entrySet;
    private transient NavigableSet navigableKeySet;

    abstract NavigableMap forward();

    protected final Map delegate()
    {
      return forward();
    }

    public Comparator comparator()
    {
      Object localObject1 = this.comparator;
      if (localObject1 == null)
      {
        Object localObject2 = forward().comparator();
        if (localObject2 == null)
          localObject2 = Ordering.natural();
        localObject1 = this.comparator = reverse((Comparator)localObject2);
      }
      return localObject1;
    }

    private static Ordering reverse(Comparator paramComparator)
    {
      return Ordering.from(paramComparator).reverse();
    }

    public Object firstKey()
    {
      return forward().lastKey();
    }

    public Object lastKey()
    {
      return forward().firstKey();
    }

    public Map.Entry lowerEntry(Object paramObject)
    {
      return forward().higherEntry(paramObject);
    }

    public Object lowerKey(Object paramObject)
    {
      return forward().higherKey(paramObject);
    }

    public Map.Entry floorEntry(Object paramObject)
    {
      return forward().ceilingEntry(paramObject);
    }

    public Object floorKey(Object paramObject)
    {
      return forward().ceilingKey(paramObject);
    }

    public Map.Entry ceilingEntry(Object paramObject)
    {
      return forward().floorEntry(paramObject);
    }

    public Object ceilingKey(Object paramObject)
    {
      return forward().floorKey(paramObject);
    }

    public Map.Entry higherEntry(Object paramObject)
    {
      return forward().lowerEntry(paramObject);
    }

    public Object higherKey(Object paramObject)
    {
      return forward().lowerKey(paramObject);
    }

    public Map.Entry firstEntry()
    {
      return forward().lastEntry();
    }

    public Map.Entry lastEntry()
    {
      return forward().firstEntry();
    }

    public Map.Entry pollFirstEntry()
    {
      return forward().pollLastEntry();
    }

    public Map.Entry pollLastEntry()
    {
      return forward().pollFirstEntry();
    }

    public NavigableMap descendingMap()
    {
      return forward();
    }

    public Set entrySet()
    {
      Set localSet = this.entrySet;
      return localSet == null ? (this.entrySet = createEntrySet()) : localSet;
    }

    abstract Iterator entryIterator();

    Set createEntrySet()
    {
      return new Maps.EntrySet()
      {
        Map map()
        {
          return Maps.DescendingMap.this;
        }

        public Iterator iterator()
        {
          return Maps.DescendingMap.this.entryIterator();
        }
      };
    }

    public Set keySet()
    {
      return navigableKeySet();
    }

    public NavigableSet navigableKeySet()
    {
      Object localObject = this.navigableKeySet;
      if (localObject == null)
        localObject = this.navigableKeySet = new Maps.NavigableKeySet()
        {
          NavigableMap map()
          {
            return Maps.DescendingMap.this;
          }
        };
      return localObject;
    }

    public NavigableSet descendingKeySet()
    {
      return forward().navigableKeySet();
    }

    public NavigableMap subMap(Object paramObject1, boolean paramBoolean1, Object paramObject2, boolean paramBoolean2)
    {
      return forward().subMap(paramObject2, paramBoolean2, paramObject1, paramBoolean1).descendingMap();
    }

    public NavigableMap headMap(Object paramObject, boolean paramBoolean)
    {
      return forward().tailMap(paramObject, paramBoolean).descendingMap();
    }

    public NavigableMap tailMap(Object paramObject, boolean paramBoolean)
    {
      return forward().headMap(paramObject, paramBoolean).descendingMap();
    }

    public SortedMap subMap(Object paramObject1, Object paramObject2)
    {
      return subMap(paramObject1, true, paramObject2, false);
    }

    public SortedMap headMap(Object paramObject)
    {
      return headMap(paramObject, false);
    }

    public SortedMap tailMap(Object paramObject)
    {
      return tailMap(paramObject, true);
    }

    public Collection values()
    {
      return new Maps.Values()
      {
        Map map()
        {
          return Maps.DescendingMap.this;
        }
      };
    }
  }

  static abstract class EntrySet extends AbstractSet
  {
    abstract Map map();

    public int size()
    {
      return map().size();
    }

    public void clear()
    {
      map().clear();
    }

    public boolean contains(Object paramObject)
    {
      if ((paramObject instanceof Map.Entry))
      {
        Map.Entry localEntry = (Map.Entry)paramObject;
        Object localObject1 = localEntry.getKey();
        Object localObject2 = map().get(localObject1);
        return (Objects.equal(localObject2, localEntry.getValue())) && ((localObject2 != null) || (map().containsKey(localObject1)));
      }
      return false;
    }

    public boolean isEmpty()
    {
      return map().isEmpty();
    }

    public boolean remove(Object paramObject)
    {
      if (contains(paramObject))
      {
        Map.Entry localEntry = (Map.Entry)paramObject;
        return map().keySet().remove(localEntry.getKey());
      }
      return false;
    }

    public boolean removeAll(Collection paramCollection)
    {
      try
      {
        return super.removeAll((Collection)Preconditions.checkNotNull(paramCollection));
      }
      catch (UnsupportedOperationException localUnsupportedOperationException)
      {
        boolean bool = true;
        Iterator localIterator = paramCollection.iterator();
        while (localIterator.hasNext())
        {
          Object localObject = localIterator.next();
          bool |= remove(localObject);
        }
        return bool;
      }
    }

    public boolean retainAll(Collection paramCollection)
    {
      try
      {
        return super.retainAll((Collection)Preconditions.checkNotNull(paramCollection));
      }
      catch (UnsupportedOperationException localUnsupportedOperationException)
      {
        HashSet localHashSet = Sets.newHashSetWithExpectedSize(paramCollection.size());
        Iterator localIterator = paramCollection.iterator();
        while (localIterator.hasNext())
        {
          Object localObject = localIterator.next();
          if (contains(localObject))
          {
            Map.Entry localEntry = (Map.Entry)localObject;
            localHashSet.add(localEntry.getKey());
          }
        }
        return map().keySet().retainAll(localHashSet);
      }
    }
  }

  static abstract class Values extends AbstractCollection
  {
    abstract Map map();

    public Iterator iterator()
    {
      return Maps.valueIterator(map().entrySet().iterator());
    }

    public boolean remove(Object paramObject)
    {
      try
      {
        return super.remove(paramObject);
      }
      catch (UnsupportedOperationException localUnsupportedOperationException)
      {
        Iterator localIterator = map().entrySet().iterator();
        while (localIterator.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)localIterator.next();
          if (Objects.equal(paramObject, localEntry.getValue()))
          {
            map().remove(localEntry.getKey());
            return true;
          }
        }
      }
      return false;
    }

    public boolean removeAll(Collection paramCollection)
    {
      try
      {
        return super.removeAll((Collection)Preconditions.checkNotNull(paramCollection));
      }
      catch (UnsupportedOperationException localUnsupportedOperationException)
      {
        HashSet localHashSet = Sets.newHashSet();
        Iterator localIterator = map().entrySet().iterator();
        while (localIterator.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)localIterator.next();
          if (paramCollection.contains(localEntry.getValue()))
            localHashSet.add(localEntry.getKey());
        }
        return map().keySet().removeAll(localHashSet);
      }
    }

    public boolean retainAll(Collection paramCollection)
    {
      try
      {
        return super.retainAll((Collection)Preconditions.checkNotNull(paramCollection));
      }
      catch (UnsupportedOperationException localUnsupportedOperationException)
      {
        HashSet localHashSet = Sets.newHashSet();
        Iterator localIterator = map().entrySet().iterator();
        while (localIterator.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)localIterator.next();
          if (paramCollection.contains(localEntry.getValue()))
            localHashSet.add(localEntry.getKey());
        }
        return map().keySet().retainAll(localHashSet);
      }
    }

    public int size()
    {
      return map().size();
    }

    public boolean isEmpty()
    {
      return map().isEmpty();
    }

    public boolean contains(Object paramObject)
    {
      return map().containsValue(paramObject);
    }

    public void clear()
    {
      map().clear();
    }
  }

  @GwtIncompatible("NavigableMap")
  static abstract class NavigableKeySet extends Maps.KeySet
    implements NavigableSet
  {
    abstract NavigableMap map();

    public Comparator comparator()
    {
      return map().comparator();
    }

    public Object first()
    {
      return map().firstKey();
    }

    public Object last()
    {
      return map().lastKey();
    }

    public Object lower(Object paramObject)
    {
      return map().lowerKey(paramObject);
    }

    public Object floor(Object paramObject)
    {
      return map().floorKey(paramObject);
    }

    public Object ceiling(Object paramObject)
    {
      return map().ceilingKey(paramObject);
    }

    public Object higher(Object paramObject)
    {
      return map().higherKey(paramObject);
    }

    public Object pollFirst()
    {
      return Maps.keyOrNull(map().pollFirstEntry());
    }

    public Object pollLast()
    {
      return Maps.keyOrNull(map().pollLastEntry());
    }

    public NavigableSet descendingSet()
    {
      return map().descendingKeySet();
    }

    public Iterator descendingIterator()
    {
      return descendingSet().iterator();
    }

    public NavigableSet subSet(Object paramObject1, boolean paramBoolean1, Object paramObject2, boolean paramBoolean2)
    {
      return map().subMap(paramObject1, paramBoolean1, paramObject2, paramBoolean2).navigableKeySet();
    }

    public NavigableSet headSet(Object paramObject, boolean paramBoolean)
    {
      return map().headMap(paramObject, paramBoolean).navigableKeySet();
    }

    public NavigableSet tailSet(Object paramObject, boolean paramBoolean)
    {
      return map().tailMap(paramObject, paramBoolean).navigableKeySet();
    }

    public SortedSet subSet(Object paramObject1, Object paramObject2)
    {
      return subSet(paramObject1, true, paramObject2, false);
    }

    public SortedSet headSet(Object paramObject)
    {
      return headSet(paramObject, false);
    }

    public SortedSet tailSet(Object paramObject)
    {
      return tailSet(paramObject, true);
    }
  }

  static abstract class KeySet extends AbstractSet
  {
    abstract Map map();

    public Iterator iterator()
    {
      return Maps.keyIterator(map().entrySet().iterator());
    }

    public int size()
    {
      return map().size();
    }

    public boolean isEmpty()
    {
      return map().isEmpty();
    }

    public boolean contains(Object paramObject)
    {
      return map().containsKey(paramObject);
    }

    public boolean remove(Object paramObject)
    {
      if (contains(paramObject))
      {
        map().remove(paramObject);
        return true;
      }
      return false;
    }

    public boolean removeAll(Collection paramCollection)
    {
      return super.removeAll((Collection)Preconditions.checkNotNull(paramCollection));
    }

    public void clear()
    {
      map().clear();
    }
  }

  @GwtCompatible
  static abstract class ImprovedAbstractMap extends AbstractMap
  {
    private Set entrySet;
    private Set keySet;
    private Collection values;

    protected abstract Set createEntrySet();

    public Set entrySet()
    {
      Set localSet = this.entrySet;
      if (localSet == null)
        this.entrySet = (localSet = createEntrySet());
      return localSet;
    }

    public Set keySet()
    {
      Set localSet = this.keySet;
      if (localSet == null)
        return this.keySet = new Maps.KeySet()
        {
          Map map()
          {
            return Maps.ImprovedAbstractMap.this;
          }
        };
      return localSet;
    }

    public Collection values()
    {
      Collection localCollection = this.values;
      if (localCollection == null)
        return this.values = new Maps.Values()
        {
          Map map()
          {
            return Maps.ImprovedAbstractMap.this;
          }
        };
      return localCollection;
    }

    public boolean isEmpty()
    {
      return entrySet().isEmpty();
    }
  }

  @GwtIncompatible("NavigableMap")
  static class UnmodifiableNavigableMap extends ForwardingSortedMap
    implements Serializable, NavigableMap
  {
    private final NavigableMap delegate;
    private transient UnmodifiableNavigableMap descendingMap;

    UnmodifiableNavigableMap(NavigableMap paramNavigableMap)
    {
      this.delegate = paramNavigableMap;
    }

    protected SortedMap delegate()
    {
      return Collections.unmodifiableSortedMap(this.delegate);
    }

    public Map.Entry lowerEntry(Object paramObject)
    {
      return Maps.unmodifiableOrNull(this.delegate.lowerEntry(paramObject));
    }

    public Object lowerKey(Object paramObject)
    {
      return this.delegate.lowerKey(paramObject);
    }

    public Map.Entry floorEntry(Object paramObject)
    {
      return Maps.unmodifiableOrNull(this.delegate.floorEntry(paramObject));
    }

    public Object floorKey(Object paramObject)
    {
      return this.delegate.floorKey(paramObject);
    }

    public Map.Entry ceilingEntry(Object paramObject)
    {
      return Maps.unmodifiableOrNull(this.delegate.ceilingEntry(paramObject));
    }

    public Object ceilingKey(Object paramObject)
    {
      return this.delegate.ceilingKey(paramObject);
    }

    public Map.Entry higherEntry(Object paramObject)
    {
      return Maps.unmodifiableOrNull(this.delegate.higherEntry(paramObject));
    }

    public Object higherKey(Object paramObject)
    {
      return this.delegate.higherKey(paramObject);
    }

    public Map.Entry firstEntry()
    {
      return Maps.unmodifiableOrNull(this.delegate.firstEntry());
    }

    public Map.Entry lastEntry()
    {
      return Maps.unmodifiableOrNull(this.delegate.lastEntry());
    }

    public final Map.Entry pollFirstEntry()
    {
      throw new UnsupportedOperationException();
    }

    public final Map.Entry pollLastEntry()
    {
      throw new UnsupportedOperationException();
    }

    public NavigableMap descendingMap()
    {
      UnmodifiableNavigableMap localUnmodifiableNavigableMap = this.descendingMap;
      if (localUnmodifiableNavigableMap == null)
      {
        this.descendingMap = (localUnmodifiableNavigableMap = new UnmodifiableNavigableMap(this.delegate.descendingMap()));
        localUnmodifiableNavigableMap.descendingMap = this;
      }
      return localUnmodifiableNavigableMap;
    }

    public Set keySet()
    {
      return navigableKeySet();
    }

    public NavigableSet navigableKeySet()
    {
      return Sets.unmodifiableNavigableSet(this.delegate.navigableKeySet());
    }

    public NavigableSet descendingKeySet()
    {
      return Sets.unmodifiableNavigableSet(this.delegate.descendingKeySet());
    }

    public SortedMap subMap(Object paramObject1, Object paramObject2)
    {
      return subMap(paramObject1, true, paramObject2, false);
    }

    public SortedMap headMap(Object paramObject)
    {
      return headMap(paramObject, false);
    }

    public SortedMap tailMap(Object paramObject)
    {
      return tailMap(paramObject, true);
    }

    public NavigableMap subMap(Object paramObject1, boolean paramBoolean1, Object paramObject2, boolean paramBoolean2)
    {
      return Maps.unmodifiableNavigableMap(this.delegate.subMap(paramObject1, paramBoolean1, paramObject2, paramBoolean2));
    }

    public NavigableMap headMap(Object paramObject, boolean paramBoolean)
    {
      return Maps.unmodifiableNavigableMap(this.delegate.headMap(paramObject, paramBoolean));
    }

    public NavigableMap tailMap(Object paramObject, boolean paramBoolean)
    {
      return Maps.unmodifiableNavigableMap(this.delegate.tailMap(paramObject, paramBoolean));
    }
  }

  static class FilteredEntryMap extends Maps.AbstractFilteredMap
  {
    final Set filteredEntrySet = Sets.filter(paramMap.entrySet(), this.predicate);
    Set entrySet;
    Set keySet;

    FilteredEntryMap(Map paramMap, Predicate paramPredicate)
    {
      super(paramPredicate);
    }

    public Set entrySet()
    {
      Set localSet = this.entrySet;
      return localSet == null ? (this.entrySet = new EntrySet(null)) : localSet;
    }

    public Set keySet()
    {
      Set localSet = this.keySet;
      return localSet == null ? (this.keySet = new KeySet(null)) : localSet;
    }

    private class KeySet extends AbstractSet
    {
      private KeySet()
      {
      }

      public Iterator iterator()
      {
        final Iterator localIterator = Maps.FilteredEntryMap.this.filteredEntrySet.iterator();
        return new UnmodifiableIterator()
        {
          public boolean hasNext()
          {
            return localIterator.hasNext();
          }

          public Object next()
          {
            return ((Map.Entry)localIterator.next()).getKey();
          }
        };
      }

      public int size()
      {
        return Maps.FilteredEntryMap.this.filteredEntrySet.size();
      }

      public void clear()
      {
        Maps.FilteredEntryMap.this.filteredEntrySet.clear();
      }

      public boolean contains(Object paramObject)
      {
        return Maps.FilteredEntryMap.this.containsKey(paramObject);
      }

      public boolean remove(Object paramObject)
      {
        if (Maps.FilteredEntryMap.this.containsKey(paramObject))
        {
          Maps.FilteredEntryMap.this.unfiltered.remove(paramObject);
          return true;
        }
        return false;
      }

      public boolean removeAll(Collection paramCollection)
      {
        Preconditions.checkNotNull(paramCollection);
        boolean bool = false;
        Iterator localIterator = paramCollection.iterator();
        while (localIterator.hasNext())
        {
          Object localObject = localIterator.next();
          bool |= remove(localObject);
        }
        return bool;
      }

      public boolean retainAll(Collection paramCollection)
      {
        Preconditions.checkNotNull(paramCollection);
        boolean bool = false;
        Iterator localIterator = Maps.FilteredEntryMap.this.unfiltered.entrySet().iterator();
        while (localIterator.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)localIterator.next();
          if ((!paramCollection.contains(localEntry.getKey())) && (Maps.FilteredEntryMap.this.predicate.apply(localEntry)))
          {
            localIterator.remove();
            bool = true;
          }
        }
        return bool;
      }

      public Object[] toArray()
      {
        return Lists.newArrayList(iterator()).toArray();
      }

      public Object[] toArray(Object[] paramArrayOfObject)
      {
        return Lists.newArrayList(iterator()).toArray(paramArrayOfObject);
      }
    }

    private class EntrySet extends ForwardingSet
    {
      private EntrySet()
      {
      }

      protected Set delegate()
      {
        return Maps.FilteredEntryMap.this.filteredEntrySet;
      }

      public Iterator iterator()
      {
        final Iterator localIterator = Maps.FilteredEntryMap.this.filteredEntrySet.iterator();
        return new UnmodifiableIterator()
        {
          public boolean hasNext()
          {
            return localIterator.hasNext();
          }

          public Map.Entry next()
          {
            final Map.Entry localEntry = (Map.Entry)localIterator.next();
            return new ForwardingMapEntry()
            {
              protected Map.Entry delegate()
              {
                return localEntry;
              }

              public Object setValue(Object paramAnonymous2Object)
              {
                Preconditions.checkArgument(Maps.FilteredEntryMap.this.apply(localEntry.getKey(), paramAnonymous2Object));
                return super.setValue(paramAnonymous2Object);
              }
            };
          }
        };
      }
    }
  }

  private static class FilteredKeyMap extends Maps.AbstractFilteredMap
  {
    Predicate keyPredicate;
    Set entrySet;
    Set keySet;

    FilteredKeyMap(Map paramMap, Predicate paramPredicate1, Predicate paramPredicate2)
    {
      super(paramPredicate2);
      this.keyPredicate = paramPredicate1;
    }

    public Set entrySet()
    {
      Set localSet = this.entrySet;
      return localSet == null ? (this.entrySet = Sets.filter(this.unfiltered.entrySet(), this.predicate)) : localSet;
    }

    public Set keySet()
    {
      Set localSet = this.keySet;
      return localSet == null ? (this.keySet = Sets.filter(this.unfiltered.keySet(), this.keyPredicate)) : localSet;
    }

    public boolean containsKey(Object paramObject)
    {
      return (this.unfiltered.containsKey(paramObject)) && (this.keyPredicate.apply(paramObject));
    }
  }

  private static class FilteredEntrySortedMap extends Maps.FilteredEntryMap
    implements SortedMap
  {
    FilteredEntrySortedMap(SortedMap paramSortedMap, Predicate paramPredicate)
    {
      super(paramPredicate);
    }

    SortedMap sortedMap()
    {
      return (SortedMap)this.unfiltered;
    }

    public Comparator comparator()
    {
      return sortedMap().comparator();
    }

    public Object firstKey()
    {
      return keySet().iterator().next();
    }

    public Object lastKey()
    {
      Object localObject;
      for (SortedMap localSortedMap = sortedMap(); ; localSortedMap = sortedMap().headMap(localObject))
      {
        localObject = localSortedMap.lastKey();
        if (apply(localObject, this.unfiltered.get(localObject)))
          return localObject;
      }
    }

    public SortedMap headMap(Object paramObject)
    {
      return new FilteredEntrySortedMap(sortedMap().headMap(paramObject), this.predicate);
    }

    public SortedMap subMap(Object paramObject1, Object paramObject2)
    {
      return new FilteredEntrySortedMap(sortedMap().subMap(paramObject1, paramObject2), this.predicate);
    }

    public SortedMap tailMap(Object paramObject)
    {
      return new FilteredEntrySortedMap(sortedMap().tailMap(paramObject), this.predicate);
    }
  }

  private static abstract class AbstractFilteredMap extends AbstractMap
  {
    final Map unfiltered;
    final Predicate predicate;
    Collection values;

    AbstractFilteredMap(Map paramMap, Predicate paramPredicate)
    {
      this.unfiltered = paramMap;
      this.predicate = paramPredicate;
    }

    boolean apply(Object paramObject1, Object paramObject2)
    {
      Object localObject = paramObject1;
      return this.predicate.apply(Maps.immutableEntry(localObject, paramObject2));
    }

    public Object put(Object paramObject1, Object paramObject2)
    {
      Preconditions.checkArgument(apply(paramObject1, paramObject2));
      return this.unfiltered.put(paramObject1, paramObject2);
    }

    public void putAll(Map paramMap)
    {
      Iterator localIterator = paramMap.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        Preconditions.checkArgument(apply(localEntry.getKey(), localEntry.getValue()));
      }
      this.unfiltered.putAll(paramMap);
    }

    public boolean containsKey(Object paramObject)
    {
      return (this.unfiltered.containsKey(paramObject)) && (apply(paramObject, this.unfiltered.get(paramObject)));
    }

    public Object get(Object paramObject)
    {
      Object localObject = this.unfiltered.get(paramObject);
      return (localObject != null) && (apply(paramObject, localObject)) ? localObject : null;
    }

    public boolean isEmpty()
    {
      return entrySet().isEmpty();
    }

    public Object remove(Object paramObject)
    {
      return containsKey(paramObject) ? this.unfiltered.remove(paramObject) : null;
    }

    public Collection values()
    {
      Collection localCollection = this.values;
      return localCollection == null ? (this.values = new Values()) : localCollection;
    }

    class Values extends AbstractCollection
    {
      Values()
      {
      }

      public Iterator iterator()
      {
        final Iterator localIterator = Maps.AbstractFilteredMap.this.entrySet().iterator();
        return new UnmodifiableIterator()
        {
          public boolean hasNext()
          {
            return localIterator.hasNext();
          }

          public Object next()
          {
            return ((Map.Entry)localIterator.next()).getValue();
          }
        };
      }

      public int size()
      {
        return Maps.AbstractFilteredMap.this.entrySet().size();
      }

      public void clear()
      {
        Maps.AbstractFilteredMap.this.entrySet().clear();
      }

      public boolean isEmpty()
      {
        return Maps.AbstractFilteredMap.this.entrySet().isEmpty();
      }

      public boolean remove(Object paramObject)
      {
        Iterator localIterator = Maps.AbstractFilteredMap.this.unfiltered.entrySet().iterator();
        while (localIterator.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)localIterator.next();
          if ((Objects.equal(paramObject, localEntry.getValue())) && (Maps.AbstractFilteredMap.this.predicate.apply(localEntry)))
          {
            localIterator.remove();
            return true;
          }
        }
        return false;
      }

      public boolean removeAll(Collection paramCollection)
      {
        Preconditions.checkNotNull(paramCollection);
        boolean bool = false;
        Iterator localIterator = Maps.AbstractFilteredMap.this.unfiltered.entrySet().iterator();
        while (localIterator.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)localIterator.next();
          if ((paramCollection.contains(localEntry.getValue())) && (Maps.AbstractFilteredMap.this.predicate.apply(localEntry)))
          {
            localIterator.remove();
            bool = true;
          }
        }
        return bool;
      }

      public boolean retainAll(Collection paramCollection)
      {
        Preconditions.checkNotNull(paramCollection);
        boolean bool = false;
        Iterator localIterator = Maps.AbstractFilteredMap.this.unfiltered.entrySet().iterator();
        while (localIterator.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)localIterator.next();
          if ((!paramCollection.contains(localEntry.getValue())) && (Maps.AbstractFilteredMap.this.predicate.apply(localEntry)))
          {
            localIterator.remove();
            bool = true;
          }
        }
        return bool;
      }

      public Object[] toArray()
      {
        return Lists.newArrayList(iterator()).toArray();
      }

      public Object[] toArray(Object[] paramArrayOfObject)
      {
        return Lists.newArrayList(iterator()).toArray(paramArrayOfObject);
      }
    }
  }

  static class TransformedEntriesSortedMap extends Maps.TransformedEntriesMap
    implements SortedMap
  {
    protected SortedMap fromMap()
    {
      return (SortedMap)this.fromMap;
    }

    TransformedEntriesSortedMap(SortedMap paramSortedMap, Maps.EntryTransformer paramEntryTransformer)
    {
      super(paramEntryTransformer);
    }

    public Comparator comparator()
    {
      return fromMap().comparator();
    }

    public Object firstKey()
    {
      return fromMap().firstKey();
    }

    public SortedMap headMap(Object paramObject)
    {
      return Maps.transformEntries(fromMap().headMap(paramObject), this.transformer);
    }

    public Object lastKey()
    {
      return fromMap().lastKey();
    }

    public SortedMap subMap(Object paramObject1, Object paramObject2)
    {
      return Maps.transformEntries(fromMap().subMap(paramObject1, paramObject2), this.transformer);
    }

    public SortedMap tailMap(Object paramObject)
    {
      return Maps.transformEntries(fromMap().tailMap(paramObject), this.transformer);
    }
  }

  static class TransformedEntriesMap extends AbstractMap
  {
    final Map fromMap;
    final Maps.EntryTransformer transformer;
    Set entrySet;
    Collection values;

    TransformedEntriesMap(Map paramMap, Maps.EntryTransformer paramEntryTransformer)
    {
      this.fromMap = ((Map)Preconditions.checkNotNull(paramMap));
      this.transformer = ((Maps.EntryTransformer)Preconditions.checkNotNull(paramEntryTransformer));
    }

    public int size()
    {
      return this.fromMap.size();
    }

    public boolean containsKey(Object paramObject)
    {
      return this.fromMap.containsKey(paramObject);
    }

    public Object get(Object paramObject)
    {
      Object localObject = this.fromMap.get(paramObject);
      return (localObject != null) || (this.fromMap.containsKey(paramObject)) ? this.transformer.transformEntry(paramObject, localObject) : null;
    }

    public Object remove(Object paramObject)
    {
      return this.fromMap.containsKey(paramObject) ? this.transformer.transformEntry(paramObject, this.fromMap.remove(paramObject)) : null;
    }

    public void clear()
    {
      this.fromMap.clear();
    }

    public Set keySet()
    {
      return this.fromMap.keySet();
    }

    public Set entrySet()
    {
      Object localObject = this.entrySet;
      if (localObject == null)
        this.entrySet = (localObject = new Maps.EntrySet()
        {
          Map map()
          {
            return Maps.TransformedEntriesMap.this;
          }

          public Iterator iterator()
          {
            return new TransformedIterator(Maps.TransformedEntriesMap.this.fromMap.entrySet().iterator())
            {
              Map.Entry transform(final Map.Entry paramAnonymous2Entry)
              {
                return new AbstractMapEntry()
                {
                  public Object getKey()
                  {
                    return paramAnonymous2Entry.getKey();
                  }

                  public Object getValue()
                  {
                    return Maps.TransformedEntriesMap.this.transformer.transformEntry(paramAnonymous2Entry.getKey(), paramAnonymous2Entry.getValue());
                  }
                };
              }
            };
          }
        });
      return localObject;
    }

    public Collection values()
    {
      Collection localCollection = this.values;
      if (localCollection == null)
        return this.values = new Maps.Values()
        {
          Map map()
          {
            return Maps.TransformedEntriesMap.this;
          }
        };
      return localCollection;
    }
  }

  public static abstract interface EntryTransformer
  {
    public abstract Object transformEntry(Object paramObject1, Object paramObject2);
  }

  private static class UnmodifiableBiMap extends ForwardingMap
    implements BiMap, Serializable
  {
    final Map unmodifiableMap;
    final BiMap delegate;
    transient BiMap inverse;
    transient Set values;
    private static final long serialVersionUID = 0L;

    UnmodifiableBiMap(BiMap paramBiMap1, BiMap paramBiMap2)
    {
      this.unmodifiableMap = Collections.unmodifiableMap(paramBiMap1);
      this.delegate = paramBiMap1;
      this.inverse = paramBiMap2;
    }

    protected Map delegate()
    {
      return this.unmodifiableMap;
    }

    public Object forcePut(Object paramObject1, Object paramObject2)
    {
      throw new UnsupportedOperationException();
    }

    public BiMap inverse()
    {
      BiMap localBiMap = this.inverse;
      return localBiMap == null ? (this.inverse = new UnmodifiableBiMap(this.delegate.inverse(), this)) : localBiMap;
    }

    public Set values()
    {
      Set localSet = this.values;
      return localSet == null ? (this.values = Collections.unmodifiableSet(this.delegate.values())) : localSet;
    }
  }

  static class UnmodifiableEntrySet extends Maps.UnmodifiableEntries
    implements Set
  {
    UnmodifiableEntrySet(Set paramSet)
    {
      super();
    }

    public boolean equals(Object paramObject)
    {
      return Sets.equalsImpl(this, paramObject);
    }

    public int hashCode()
    {
      return Sets.hashCodeImpl(this);
    }
  }

  static class UnmodifiableEntries extends ForwardingCollection
  {
    private final Collection entries;

    UnmodifiableEntries(Collection paramCollection)
    {
      this.entries = paramCollection;
    }

    protected Collection delegate()
    {
      return this.entries;
    }

    public Iterator iterator()
    {
      final Iterator localIterator = super.iterator();
      return new ForwardingIterator()
      {
        public Map.Entry next()
        {
          return Maps.unmodifiableEntry((Map.Entry)super.next());
        }

        public void remove()
        {
          throw new UnsupportedOperationException();
        }

        protected Iterator delegate()
        {
          return localIterator;
        }
      };
    }

    public boolean add(Map.Entry paramEntry)
    {
      throw new UnsupportedOperationException();
    }

    public boolean addAll(Collection paramCollection)
    {
      throw new UnsupportedOperationException();
    }

    public void clear()
    {
      throw new UnsupportedOperationException();
    }

    public boolean remove(Object paramObject)
    {
      throw new UnsupportedOperationException();
    }

    public boolean removeAll(Collection paramCollection)
    {
      throw new UnsupportedOperationException();
    }

    public boolean retainAll(Collection paramCollection)
    {
      throw new UnsupportedOperationException();
    }

    public Object[] toArray()
    {
      return standardToArray();
    }

    public Object[] toArray(Object[] paramArrayOfObject)
    {
      return standardToArray(paramArrayOfObject);
    }
  }

  static class SortedMapDifferenceImpl extends Maps.MapDifferenceImpl
    implements SortedMapDifference
  {
    SortedMapDifferenceImpl(boolean paramBoolean, SortedMap paramSortedMap1, SortedMap paramSortedMap2, SortedMap paramSortedMap3, SortedMap paramSortedMap4)
    {
      super(paramSortedMap1, paramSortedMap2, paramSortedMap3, paramSortedMap4);
    }

    public SortedMap entriesDiffering()
    {
      return (SortedMap)super.entriesDiffering();
    }

    public SortedMap entriesInCommon()
    {
      return (SortedMap)super.entriesInCommon();
    }

    public SortedMap entriesOnlyOnLeft()
    {
      return (SortedMap)super.entriesOnlyOnLeft();
    }

    public SortedMap entriesOnlyOnRight()
    {
      return (SortedMap)super.entriesOnlyOnRight();
    }
  }

  static class ValueDifferenceImpl
    implements MapDifference.ValueDifference
  {
    private final Object left;
    private final Object right;

    static MapDifference.ValueDifference create(Object paramObject1, Object paramObject2)
    {
      return new ValueDifferenceImpl(paramObject1, paramObject2);
    }

    private ValueDifferenceImpl(Object paramObject1, Object paramObject2)
    {
      this.left = paramObject1;
      this.right = paramObject2;
    }

    public Object leftValue()
    {
      return this.left;
    }

    public Object rightValue()
    {
      return this.right;
    }

    public boolean equals(Object paramObject)
    {
      if ((paramObject instanceof MapDifference.ValueDifference))
      {
        MapDifference.ValueDifference localValueDifference = (MapDifference.ValueDifference)paramObject;
        return (Objects.equal(this.left, localValueDifference.leftValue())) && (Objects.equal(this.right, localValueDifference.rightValue()));
      }
      return false;
    }

    public int hashCode()
    {
      return Objects.hashCode(new Object[] { this.left, this.right });
    }

    public String toString()
    {
      return "(" + this.left + ", " + this.right + ")";
    }
  }

  static class MapDifferenceImpl
    implements MapDifference
  {
    final boolean areEqual;
    final Map onlyOnLeft;
    final Map onlyOnRight;
    final Map onBoth;
    final Map differences;

    MapDifferenceImpl(boolean paramBoolean, Map paramMap1, Map paramMap2, Map paramMap3, Map paramMap4)
    {
      this.areEqual = paramBoolean;
      this.onlyOnLeft = paramMap1;
      this.onlyOnRight = paramMap2;
      this.onBoth = paramMap3;
      this.differences = paramMap4;
    }

    public boolean areEqual()
    {
      return this.areEqual;
    }

    public Map entriesOnlyOnLeft()
    {
      return this.onlyOnLeft;
    }

    public Map entriesOnlyOnRight()
    {
      return this.onlyOnRight;
    }

    public Map entriesInCommon()
    {
      return this.onBoth;
    }

    public Map entriesDiffering()
    {
      return this.differences;
    }

    public boolean equals(Object paramObject)
    {
      if (paramObject == this)
        return true;
      if ((paramObject instanceof MapDifference))
      {
        MapDifference localMapDifference = (MapDifference)paramObject;
        return (entriesOnlyOnLeft().equals(localMapDifference.entriesOnlyOnLeft())) && (entriesOnlyOnRight().equals(localMapDifference.entriesOnlyOnRight())) && (entriesInCommon().equals(localMapDifference.entriesInCommon())) && (entriesDiffering().equals(localMapDifference.entriesDiffering()));
      }
      return false;
    }

    public int hashCode()
    {
      return Objects.hashCode(new Object[] { entriesOnlyOnLeft(), entriesOnlyOnRight(), entriesInCommon(), entriesDiffering() });
    }

    public String toString()
    {
      if (this.areEqual)
        return "equal";
      StringBuilder localStringBuilder = new StringBuilder("not equal");
      if (!this.onlyOnLeft.isEmpty())
        localStringBuilder.append(": only on left=").append(this.onlyOnLeft);
      if (!this.onlyOnRight.isEmpty())
        localStringBuilder.append(": only on right=").append(this.onlyOnRight);
      if (!this.differences.isEmpty())
        localStringBuilder.append(": value differences=").append(this.differences);
      return localStringBuilder.toString();
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.Maps
 * JD-Core Version:    0.6.2
 */