package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.Comparator;

final class EmptyImmutableSortedMultiset extends ImmutableSortedMultiset
{
  EmptyImmutableSortedMultiset(Comparator paramComparator)
  {
    super(paramComparator);
  }

  public Multiset.Entry firstEntry()
  {
    return null;
  }

  public Multiset.Entry lastEntry()
  {
    return null;
  }

  public int count(Object paramObject)
  {
    return 0;
  }

  public int size()
  {
    return 0;
  }

  ImmutableSortedSet createElementSet()
  {
    return ImmutableSortedSet.emptySet(comparator());
  }

  ImmutableSortedSet createDescendingElementSet()
  {
    return ImmutableSortedSet.emptySet(reverseComparator());
  }

  ImmutableSet createEntrySet()
  {
    return ImmutableSet.of();
  }

  public ImmutableSortedMultiset headMultiset(Object paramObject, BoundType paramBoundType)
  {
    Preconditions.checkNotNull(paramObject);
    Preconditions.checkNotNull(paramBoundType);
    return this;
  }

  public ImmutableSortedMultiset tailMultiset(Object paramObject, BoundType paramBoundType)
  {
    Preconditions.checkNotNull(paramObject);
    Preconditions.checkNotNull(paramBoundType);
    return this;
  }

  boolean isPartialView()
  {
    return false;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.EmptyImmutableSortedMultiset
 * JD-Core Version:    0.6.2
 */