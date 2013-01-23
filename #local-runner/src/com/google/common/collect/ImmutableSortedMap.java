package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedMap;

@GwtCompatible(serializable=true, emulated=true)
public class ImmutableSortedMap extends ImmutableSortedMapFauxverideShim
  implements NavigableMap
{
  private static final Comparator NATURAL_ORDER = Ordering.natural();
  private static final ImmutableSortedMap NATURAL_EMPTY_MAP = new ImmutableSortedMap(ImmutableList.of(), NATURAL_ORDER);
  final transient ImmutableList entries;
  private final transient Comparator comparator;
  private transient ImmutableSortedMap descendingMap;
  private static final long serialVersionUID = 0L;

  public static ImmutableSortedMap of()
  {
    return NATURAL_EMPTY_MAP;
  }

  private static ImmutableSortedMap emptyMap(Comparator paramComparator)
  {
    if (NATURAL_ORDER.equals(paramComparator))
      return NATURAL_EMPTY_MAP;
    return new ImmutableSortedMap(ImmutableList.of(), paramComparator);
  }

  public static ImmutableSortedMap of(Comparable paramComparable, Object paramObject)
  {
    return new ImmutableSortedMap(ImmutableList.of(entryOf(paramComparable, paramObject)), Ordering.natural());
  }

  public static ImmutableSortedMap of(Comparable paramComparable1, Object paramObject1, Comparable paramComparable2, Object paramObject2)
  {
    return new Builder(Ordering.natural()).put(paramComparable1, paramObject1).put(paramComparable2, paramObject2).build();
  }

  public static ImmutableSortedMap of(Comparable paramComparable1, Object paramObject1, Comparable paramComparable2, Object paramObject2, Comparable paramComparable3, Object paramObject3)
  {
    return new Builder(Ordering.natural()).put(paramComparable1, paramObject1).put(paramComparable2, paramObject2).put(paramComparable3, paramObject3).build();
  }

  public static ImmutableSortedMap of(Comparable paramComparable1, Object paramObject1, Comparable paramComparable2, Object paramObject2, Comparable paramComparable3, Object paramObject3, Comparable paramComparable4, Object paramObject4)
  {
    return new Builder(Ordering.natural()).put(paramComparable1, paramObject1).put(paramComparable2, paramObject2).put(paramComparable3, paramObject3).put(paramComparable4, paramObject4).build();
  }

  public static ImmutableSortedMap of(Comparable paramComparable1, Object paramObject1, Comparable paramComparable2, Object paramObject2, Comparable paramComparable3, Object paramObject3, Comparable paramComparable4, Object paramObject4, Comparable paramComparable5, Object paramObject5)
  {
    return new Builder(Ordering.natural()).put(paramComparable1, paramObject1).put(paramComparable2, paramObject2).put(paramComparable3, paramObject3).put(paramComparable4, paramObject4).put(paramComparable5, paramObject5).build();
  }

  public static ImmutableSortedMap copyOf(Map paramMap)
  {
    Ordering localOrdering = Ordering.natural();
    return copyOfInternal(paramMap, localOrdering);
  }

  public static ImmutableSortedMap copyOf(Map paramMap, Comparator paramComparator)
  {
    return copyOfInternal(paramMap, (Comparator)Preconditions.checkNotNull(paramComparator));
  }

  public static ImmutableSortedMap copyOfSorted(SortedMap paramSortedMap)
  {
    Comparator localComparator = paramSortedMap.comparator();
    if (localComparator == null)
      localComparator = NATURAL_ORDER;
    return copyOfInternal(paramSortedMap, localComparator);
  }

  private static ImmutableSortedMap copyOfInternal(Map paramMap, Comparator paramComparator)
  {
    boolean bool = false;
    if ((paramMap instanceof SortedMap))
    {
      localObject1 = (SortedMap)paramMap;
      Comparator localComparator = ((SortedMap)localObject1).comparator();
      bool = localComparator == null ? false : paramComparator == NATURAL_ORDER ? true : paramComparator.equals(localComparator);
    }
    if ((bool) && ((paramMap instanceof ImmutableSortedMap)))
    {
      localObject1 = (ImmutableSortedMap)paramMap;
      if (!((ImmutableSortedMap)localObject1).isPartialView())
        return localObject1;
    }
    Object localObject1 = (Map.Entry[])paramMap.entrySet().toArray(new Map.Entry[0]);
    for (int i = 0; i < localObject1.length; i++)
    {
      Object localObject2 = localObject1[i];
      localObject1[i] = entryOf(localObject2.getKey(), localObject2.getValue());
    }
    List localList = Arrays.asList((Object[])localObject1);
    if (!bool)
    {
      sortEntries(localList, paramComparator);
      validateEntries(localList, paramComparator);
    }
    return new ImmutableSortedMap(ImmutableList.copyOf(localList), paramComparator);
  }

  private static void sortEntries(List paramList, Comparator paramComparator)
  {
    Comparator local1 = new Comparator()
    {
      public int compare(Map.Entry paramAnonymousEntry1, Map.Entry paramAnonymousEntry2)
      {
        return this.val$comparator.compare(paramAnonymousEntry1.getKey(), paramAnonymousEntry2.getKey());
      }
    };
    Collections.sort(paramList, local1);
  }

  private static void validateEntries(List paramList, Comparator paramComparator)
  {
    for (int i = 1; i < paramList.size(); i++)
      if (paramComparator.compare(((Map.Entry)paramList.get(i - 1)).getKey(), ((Map.Entry)paramList.get(i)).getKey()) == 0)
        throw new IllegalArgumentException("Duplicate keys in mappings " + paramList.get(i - 1) + " and " + paramList.get(i));
  }

  public static Builder naturalOrder()
  {
    return new Builder(Ordering.natural());
  }

  public static Builder orderedBy(Comparator paramComparator)
  {
    return new Builder(paramComparator);
  }

  public static Builder reverseOrder()
  {
    return new Builder(Ordering.natural().reverse());
  }

  ImmutableSortedMap(ImmutableList paramImmutableList, Comparator paramComparator)
  {
    this.entries = paramImmutableList;
    this.comparator = paramComparator;
  }

  public int size()
  {
    return this.entries.size();
  }

  Comparator unsafeComparator()
  {
    return this.comparator;
  }

  public Object get(Object paramObject)
  {
    if (paramObject == null)
      return null;
    int i;
    try
    {
      i = index(paramObject, SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.INVERTED_INSERTION_INDEX);
    }
    catch (ClassCastException localClassCastException)
    {
      return null;
    }
    return i >= 0 ? ((Map.Entry)this.entries.get(i)).getValue() : null;
  }

  public boolean containsValue(Object paramObject)
  {
    return (paramObject != null) && (Maps.containsValueImpl(this, paramObject));
  }

  boolean isPartialView()
  {
    return this.entries.isPartialView();
  }

  public ImmutableSet entrySet()
  {
    return super.entrySet();
  }

  ImmutableSet createEntrySet()
  {
    return isEmpty() ? ImmutableSet.of() : new EntrySet(null);
  }

  public ImmutableSortedSet keySet()
  {
    return (ImmutableSortedSet)super.keySet();
  }

  ImmutableSortedSet createKeySet()
  {
    if (isEmpty())
      return ImmutableSortedSet.emptySet(this.comparator);
    return new RegularImmutableSortedSet(new TransformedImmutableList(this.entries)
    {
      Object transform(Map.Entry paramAnonymousEntry)
      {
        return paramAnonymousEntry.getKey();
      }
    }
    , this.comparator);
  }

  public ImmutableCollection values()
  {
    return super.values();
  }

  public Comparator comparator()
  {
    return this.comparator;
  }

  public Object firstKey()
  {
    if (isEmpty())
      throw new NoSuchElementException();
    return ((Map.Entry)this.entries.get(0)).getKey();
  }

  public Object lastKey()
  {
    if (isEmpty())
      throw new NoSuchElementException();
    return ((Map.Entry)this.entries.get(size() - 1)).getKey();
  }

  public ImmutableSortedMap headMap(Object paramObject)
  {
    return headMap(paramObject, false);
  }

  public ImmutableSortedMap headMap(Object paramObject, boolean paramBoolean)
  {
    int i;
    if (paramBoolean)
      i = index(paramObject, SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_LOWER) + 1;
    else
      i = index(paramObject, SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_HIGHER);
    return createSubmap(0, i);
  }

  public ImmutableSortedMap subMap(Object paramObject1, Object paramObject2)
  {
    return subMap(paramObject1, true, paramObject2, false);
  }

  public ImmutableSortedMap subMap(Object paramObject1, boolean paramBoolean1, Object paramObject2, boolean paramBoolean2)
  {
    Preconditions.checkNotNull(paramObject1);
    Preconditions.checkNotNull(paramObject2);
    Preconditions.checkArgument(this.comparator.compare(paramObject1, paramObject2) <= 0);
    return tailMap(paramObject1, paramBoolean1).headMap(paramObject2, paramBoolean2);
  }

  public ImmutableSortedMap tailMap(Object paramObject)
  {
    return tailMap(paramObject, true);
  }

  public ImmutableSortedMap tailMap(Object paramObject, boolean paramBoolean)
  {
    int i;
    if (paramBoolean)
      i = index(paramObject, SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_HIGHER);
    else
      i = index(paramObject, SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_LOWER) + 1;
    return createSubmap(i, size());
  }

  public Map.Entry lowerEntry(Object paramObject)
  {
    return headMap(paramObject, false).lastEntry();
  }

  public Object lowerKey(Object paramObject)
  {
    return Maps.keyOrNull(lowerEntry(paramObject));
  }

  public Map.Entry floorEntry(Object paramObject)
  {
    return headMap(paramObject, true).lastEntry();
  }

  public Object floorKey(Object paramObject)
  {
    return Maps.keyOrNull(floorEntry(paramObject));
  }

  public Map.Entry ceilingEntry(Object paramObject)
  {
    return tailMap(paramObject, true).firstEntry();
  }

  public Object ceilingKey(Object paramObject)
  {
    return Maps.keyOrNull(ceilingEntry(paramObject));
  }

  public Map.Entry higherEntry(Object paramObject)
  {
    return tailMap(paramObject, false).firstEntry();
  }

  public Object higherKey(Object paramObject)
  {
    return Maps.keyOrNull(higherEntry(paramObject));
  }

  public Map.Entry firstEntry()
  {
    return isEmpty() ? null : (Map.Entry)this.entries.get(0);
  }

  public Map.Entry lastEntry()
  {
    return isEmpty() ? null : (Map.Entry)this.entries.get(this.entries.size() - 1);
  }

  public final Map.Entry pollFirstEntry()
  {
    throw new UnsupportedOperationException();
  }

  public final Map.Entry pollLastEntry()
  {
    throw new UnsupportedOperationException();
  }

  public ImmutableSortedMap descendingMap()
  {
    ImmutableSortedMap localImmutableSortedMap = this.descendingMap;
    if (localImmutableSortedMap == null)
    {
      localImmutableSortedMap = this.descendingMap = new ImmutableSortedMap(this.entries.reverse(), Ordering.from(comparator()).reverse());
      localImmutableSortedMap.descendingMap = this;
    }
    return localImmutableSortedMap;
  }

  public ImmutableSortedSet navigableKeySet()
  {
    return keySet();
  }

  public ImmutableSortedSet descendingKeySet()
  {
    return descendingMap().keySet();
  }

  private ImmutableList keyList()
  {
    return new TransformedImmutableList(this.entries)
    {
      Object transform(Map.Entry paramAnonymousEntry)
      {
        return paramAnonymousEntry.getKey();
      }
    };
  }

  private int index(Object paramObject, SortedLists.KeyPresentBehavior paramKeyPresentBehavior, SortedLists.KeyAbsentBehavior paramKeyAbsentBehavior)
  {
    return SortedLists.binarySearch(keyList(), Preconditions.checkNotNull(paramObject), unsafeComparator(), paramKeyPresentBehavior, paramKeyAbsentBehavior);
  }

  private ImmutableSortedMap createSubmap(int paramInt1, int paramInt2)
  {
    if (paramInt1 < paramInt2)
      return new ImmutableSortedMap(this.entries.subList(paramInt1, paramInt2), this.comparator);
    return emptyMap(this.comparator);
  }

  Object writeReplace()
  {
    return new SerializedForm(this);
  }

  private static class SerializedForm extends ImmutableMap.SerializedForm
  {
    private final Comparator comparator;
    private static final long serialVersionUID = 0L;

    SerializedForm(ImmutableSortedMap paramImmutableSortedMap)
    {
      super();
      this.comparator = paramImmutableSortedMap.comparator();
    }

    Object readResolve()
    {
      ImmutableSortedMap.Builder localBuilder = new ImmutableSortedMap.Builder(this.comparator);
      return createMap(localBuilder);
    }
  }

  private class EntrySet extends ImmutableMap.EntrySet
  {
    private EntrySet()
    {
      super();
    }

    public UnmodifiableIterator iterator()
    {
      return ImmutableSortedMap.this.entries.iterator();
    }

    ImmutableList createAsList()
    {
      return ImmutableSortedMap.this.entries;
    }
  }

  public static class Builder extends ImmutableMap.Builder
  {
    private final Comparator comparator;

    public Builder(Comparator paramComparator)
    {
      this.comparator = ((Comparator)Preconditions.checkNotNull(paramComparator));
    }

    public Builder put(Object paramObject1, Object paramObject2)
    {
      this.entries.add(ImmutableMap.entryOf(paramObject1, paramObject2));
      return this;
    }

    public Builder put(Map.Entry paramEntry)
    {
      super.put(paramEntry);
      return this;
    }

    public Builder putAll(Map paramMap)
    {
      Iterator localIterator = paramMap.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        put(localEntry.getKey(), localEntry.getValue());
      }
      return this;
    }

    public ImmutableSortedMap build()
    {
      ImmutableSortedMap.sortEntries(this.entries, this.comparator);
      ImmutableSortedMap.validateEntries(this.entries, this.comparator);
      return new ImmutableSortedMap(ImmutableList.copyOf(this.entries), this.comparator);
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.ImmutableSortedMap
 * JD-Core Version:    0.6.2
 */