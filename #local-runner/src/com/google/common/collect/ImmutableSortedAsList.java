package com.google.common.collect;

import java.util.Comparator;

final class ImmutableSortedAsList extends ImmutableList
  implements SortedIterable
{
  private final transient ImmutableSortedSet backingSet;
  private final transient ImmutableList backingList;

  ImmutableSortedAsList(ImmutableSortedSet paramImmutableSortedSet, ImmutableList paramImmutableList)
  {
    this.backingSet = paramImmutableSortedSet;
    this.backingList = paramImmutableList;
  }

  public Comparator comparator()
  {
    return this.backingSet.comparator();
  }

  public int indexOf(Object paramObject)
  {
    return this.backingSet.indexOf(paramObject);
  }

  public int lastIndexOf(Object paramObject)
  {
    return this.backingSet.indexOf(paramObject);
  }

  ImmutableList subListUnchecked(int paramInt1, int paramInt2)
  {
    return new RegularImmutableSortedSet(this.backingList.subList(paramInt1, paramInt2), this.backingSet.comparator()).asList();
  }

  Object writeReplace()
  {
    return new ImmutableAsList.SerializedForm(this.backingSet);
  }

  public UnmodifiableIterator iterator()
  {
    return this.backingList.iterator();
  }

  public Object get(int paramInt)
  {
    return this.backingList.get(paramInt);
  }

  public UnmodifiableListIterator listIterator()
  {
    return this.backingList.listIterator();
  }

  public UnmodifiableListIterator listIterator(int paramInt)
  {
    return this.backingList.listIterator(paramInt);
  }

  public int size()
  {
    return this.backingList.size();
  }

  public boolean equals(Object paramObject)
  {
    return this.backingList.equals(paramObject);
  }

  public int hashCode()
  {
    return this.backingList.hashCode();
  }

  boolean isPartialView()
  {
    return this.backingList.isPartialView();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.ImmutableSortedAsList
 * JD-Core Version:    0.6.2
 */