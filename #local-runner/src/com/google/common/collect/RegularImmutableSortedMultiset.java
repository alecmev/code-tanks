package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

final class RegularImmutableSortedMultiset extends ImmutableSortedMultiset
{
  final transient ImmutableList entries;

  static RegularImmutableSortedMultiset createFromSorted(Comparator paramComparator, List paramList)
  {
    ArrayList localArrayList = Lists.newArrayListWithCapacity(paramList.size());
    CumulativeCountEntry localCumulativeCountEntry = null;
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      Multiset.Entry localEntry = (Multiset.Entry)localIterator.next();
      localArrayList.add(localCumulativeCountEntry = new CumulativeCountEntry(localEntry.getElement(), localEntry.getCount(), localCumulativeCountEntry));
    }
    return new RegularImmutableSortedMultiset(paramComparator, ImmutableList.copyOf(localArrayList));
  }

  RegularImmutableSortedMultiset(Comparator paramComparator, ImmutableList paramImmutableList)
  {
    super(paramComparator);
    this.entries = paramImmutableList;
    assert (!paramImmutableList.isEmpty());
  }

  ImmutableList elementList()
  {
    return new TransformedImmutableList(this.entries)
    {
      Object transform(RegularImmutableSortedMultiset.CumulativeCountEntry paramAnonymousCumulativeCountEntry)
      {
        return paramAnonymousCumulativeCountEntry.getElement();
      }
    };
  }

  ImmutableSortedSet createElementSet()
  {
    return new RegularImmutableSortedSet(elementList(), comparator());
  }

  ImmutableSortedSet createDescendingElementSet()
  {
    return new RegularImmutableSortedSet(elementList().reverse(), reverseComparator());
  }

  ImmutableSet createEntrySet()
  {
    // Byte code:
    //   0: new 10	com/google/common/collect/RegularImmutableSortedMultiset$2
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 57	com/google/common/collect/RegularImmutableSortedMultiset$2:<init>	(Lcom/google/common/collect/RegularImmutableSortedMultiset;)V
    //   8: areturn
  }

  public CumulativeCountEntry firstEntry()
  {
    return (CumulativeCountEntry)this.entries.get(0);
  }

  public CumulativeCountEntry lastEntry()
  {
    return (CumulativeCountEntry)this.entries.get(this.entries.size() - 1);
  }

  public int size()
  {
    CumulativeCountEntry localCumulativeCountEntry1 = firstEntry();
    CumulativeCountEntry localCumulativeCountEntry2 = lastEntry();
    return Ints.saturatedCast(localCumulativeCountEntry2.cumulativeCount - localCumulativeCountEntry1.cumulativeCount + localCumulativeCountEntry1.count);
  }

  boolean isPartialView()
  {
    return this.entries.isPartialView();
  }

  public int count(Object paramObject)
  {
    if (paramObject == null)
      return 0;
    try
    {
      int i = SortedLists.binarySearch(elementList(), paramObject, comparator(), SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.INVERTED_INSERTION_INDEX);
      return i >= 0 ? ((CumulativeCountEntry)this.entries.get(i)).getCount() : 0;
    }
    catch (ClassCastException localClassCastException)
    {
    }
    return 0;
  }

  public ImmutableSortedMultiset headMultiset(Object paramObject, BoundType paramBoundType)
  {
    int i;
    switch (3.$SwitchMap$com$google$common$collect$BoundType[paramBoundType.ordinal()])
    {
    case 1:
      i = SortedLists.binarySearch(elementList(), Preconditions.checkNotNull(paramObject), comparator(), SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_HIGHER);
      break;
    case 2:
      i = SortedLists.binarySearch(elementList(), Preconditions.checkNotNull(paramObject), comparator(), SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_LOWER) + 1;
      break;
    default:
      throw new AssertionError();
    }
    return createSubMultiset(0, i);
  }

  public ImmutableSortedMultiset tailMultiset(Object paramObject, BoundType paramBoundType)
  {
    int i;
    switch (3.$SwitchMap$com$google$common$collect$BoundType[paramBoundType.ordinal()])
    {
    case 1:
      i = SortedLists.binarySearch(elementList(), Preconditions.checkNotNull(paramObject), comparator(), SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_LOWER) + 1;
      break;
    case 2:
      i = SortedLists.binarySearch(elementList(), Preconditions.checkNotNull(paramObject), comparator(), SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_HIGHER);
      break;
    default:
      throw new AssertionError();
    }
    return createSubMultiset(i, this.entries.size());
  }

  private ImmutableSortedMultiset createSubMultiset(int paramInt1, int paramInt2)
  {
    if ((paramInt1 == 0) && (paramInt2 == this.entries.size()))
      return this;
    if (paramInt1 >= paramInt2)
      return emptyMultiset(comparator());
    return new RegularImmutableSortedMultiset(comparator(), this.entries.subList(paramInt1, paramInt2));
  }

  private static final class CumulativeCountEntry extends Multisets.AbstractEntry
  {
    final Object element;
    final int count;
    final long cumulativeCount;

    CumulativeCountEntry(Object paramObject, int paramInt, CumulativeCountEntry paramCumulativeCountEntry)
    {
      this.element = paramObject;
      this.count = paramInt;
      this.cumulativeCount = (paramInt + (paramCumulativeCountEntry == null ? 0L : paramCumulativeCountEntry.cumulativeCount));
    }

    public Object getElement()
    {
      return this.element;
    }

    public int getCount()
    {
      return this.count;
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.RegularImmutableSortedMultiset
 * JD-Core Version:    0.6.2
 */