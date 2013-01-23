package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@GwtCompatible(serializable=true)
final class ComparatorOrdering extends Ordering
  implements Serializable
{
  final Comparator comparator;
  private static final long serialVersionUID = 0L;

  ComparatorOrdering(Comparator paramComparator)
  {
    this.comparator = ((Comparator)Preconditions.checkNotNull(paramComparator));
  }

  public int compare(Object paramObject1, Object paramObject2)
  {
    return this.comparator.compare(paramObject1, paramObject2);
  }

  public int binarySearch(List paramList, Object paramObject)
  {
    return Collections.binarySearch(paramList, paramObject, this.comparator);
  }

  public List sortedCopy(Iterable paramIterable)
  {
    ArrayList localArrayList = Lists.newArrayList(paramIterable);
    Collections.sort(localArrayList, this.comparator);
    return localArrayList;
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == this)
      return true;
    if ((paramObject instanceof ComparatorOrdering))
    {
      ComparatorOrdering localComparatorOrdering = (ComparatorOrdering)paramObject;
      return this.comparator.equals(localComparatorOrdering.comparator);
    }
    return false;
  }

  public int hashCode()
  {
    return this.comparator.hashCode();
  }

  public String toString()
  {
    return this.comparator.toString();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.ComparatorOrdering
 * JD-Core Version:    0.6.2
 */