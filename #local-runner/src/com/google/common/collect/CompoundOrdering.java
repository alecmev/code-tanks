package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

@GwtCompatible(serializable=true)
final class CompoundOrdering extends Ordering
  implements Serializable
{
  final ImmutableList comparators;
  private static final long serialVersionUID = 0L;

  CompoundOrdering(Comparator paramComparator1, Comparator paramComparator2)
  {
    this.comparators = ImmutableList.of(paramComparator1, paramComparator2);
  }

  CompoundOrdering(Iterable paramIterable)
  {
    this.comparators = ImmutableList.copyOf(paramIterable);
  }

  CompoundOrdering(List paramList, Comparator paramComparator)
  {
    this.comparators = new ImmutableList.Builder().addAll(paramList).add(paramComparator).build();
  }

  public int compare(Object paramObject1, Object paramObject2)
  {
    Iterator localIterator = this.comparators.iterator();
    while (localIterator.hasNext())
    {
      Comparator localComparator = (Comparator)localIterator.next();
      int i = localComparator.compare(paramObject1, paramObject2);
      if (i != 0)
        return i;
    }
    return 0;
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == this)
      return true;
    if ((paramObject instanceof CompoundOrdering))
    {
      CompoundOrdering localCompoundOrdering = (CompoundOrdering)paramObject;
      return this.comparators.equals(localCompoundOrdering.comparators);
    }
    return false;
  }

  public int hashCode()
  {
    return this.comparators.hashCode();
  }

  public String toString()
  {
    return "Ordering.compound(" + this.comparators + ")";
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.CompoundOrdering
 * JD-Core Version:    0.6.2
 */