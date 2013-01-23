package com.google.common.collect;

final class DescendingImmutableSortedMultiset extends ImmutableSortedMultiset
{
  private final transient ImmutableSortedMultiset forward;

  DescendingImmutableSortedMultiset(ImmutableSortedMultiset paramImmutableSortedMultiset)
  {
    super(paramImmutableSortedMultiset.reverseComparator());
    this.forward = paramImmutableSortedMultiset;
  }

  public int count(Object paramObject)
  {
    return this.forward.count(paramObject);
  }

  public Multiset.Entry firstEntry()
  {
    return this.forward.lastEntry();
  }

  public Multiset.Entry lastEntry()
  {
    return this.forward.firstEntry();
  }

  public int size()
  {
    return this.forward.size();
  }

  ImmutableSortedSet createElementSet()
  {
    return this.forward.createDescendingElementSet();
  }

  ImmutableSortedSet createDescendingElementSet()
  {
    return this.forward.elementSet();
  }

  ImmutableSet createEntrySet()
  {
    final ImmutableSet localImmutableSet = this.forward.entrySet();
    return new ImmutableMultiset.EntrySet(localImmutableSet)
    {
      public int size()
      {
        return localImmutableSet.size();
      }

      public UnmodifiableIterator iterator()
      {
        return asList().iterator();
      }

      ImmutableList createAsList()
      {
        return localImmutableSet.asList().reverse();
      }
    };
  }

  public ImmutableSortedMultiset descendingMultiset()
  {
    return this.forward;
  }

  public ImmutableSortedMultiset headMultiset(Object paramObject, BoundType paramBoundType)
  {
    return this.forward.tailMultiset(paramObject, paramBoundType).descendingMultiset();
  }

  public ImmutableSortedMultiset tailMultiset(Object paramObject, BoundType paramBoundType)
  {
    return this.forward.headMultiset(paramObject, paramBoundType).descendingMultiset();
  }

  boolean isPartialView()
  {
    return this.forward.isPartialView();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.DescendingImmutableSortedMultiset
 * JD-Core Version:    0.6.2
 */