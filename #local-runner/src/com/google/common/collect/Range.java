package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.SortedSet;

@GwtCompatible
@Beta
public final class Range
  implements Predicate, Serializable
{
  final Cut lowerBound;
  final Cut upperBound;
  private static final long serialVersionUID = 0L;

  Range(Cut paramCut1, Cut paramCut2)
  {
    if (paramCut1.compareTo(paramCut2) > 0)
      throw new IllegalArgumentException("Invalid range: " + toString(paramCut1, paramCut2));
    this.lowerBound = paramCut1;
    this.upperBound = paramCut2;
  }

  public boolean hasLowerBound()
  {
    return this.lowerBound != Cut.belowAll();
  }

  public Comparable lowerEndpoint()
  {
    return this.lowerBound.endpoint();
  }

  public BoundType lowerBoundType()
  {
    return this.lowerBound.typeAsLowerBound();
  }

  public boolean hasUpperBound()
  {
    return this.upperBound != Cut.aboveAll();
  }

  public Comparable upperEndpoint()
  {
    return this.upperBound.endpoint();
  }

  public BoundType upperBoundType()
  {
    return this.upperBound.typeAsUpperBound();
  }

  public boolean isEmpty()
  {
    return this.lowerBound.equals(this.upperBound);
  }

  public boolean contains(Comparable paramComparable)
  {
    Preconditions.checkNotNull(paramComparable);
    return (this.lowerBound.isLessThan(paramComparable)) && (!this.upperBound.isLessThan(paramComparable));
  }

  public boolean apply(Comparable paramComparable)
  {
    return contains(paramComparable);
  }

  public boolean containsAll(Iterable paramIterable)
  {
    if (Iterables.isEmpty(paramIterable))
      return true;
    Object localObject2;
    if ((paramIterable instanceof SortedSet))
    {
      localObject1 = cast(paramIterable);
      localObject2 = ((SortedSet)localObject1).comparator();
      if ((Ordering.natural().equals(localObject2)) || (localObject2 == null))
        return (contains((Comparable)((SortedSet)localObject1).first())) && (contains((Comparable)((SortedSet)localObject1).last()));
    }
    Object localObject1 = paramIterable.iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (Comparable)((Iterator)localObject1).next();
      if (!contains((Comparable)localObject2))
        return false;
    }
    return true;
  }

  public boolean encloses(Range paramRange)
  {
    return (this.lowerBound.compareTo(paramRange.lowerBound) <= 0) && (this.upperBound.compareTo(paramRange.upperBound) >= 0);
  }

  public boolean isConnected(Range paramRange)
  {
    return (this.lowerBound.compareTo(paramRange.upperBound) <= 0) && (paramRange.lowerBound.compareTo(this.upperBound) <= 0);
  }

  public Range intersection(Range paramRange)
  {
    Cut localCut1 = (Cut)Ordering.natural().max(this.lowerBound, paramRange.lowerBound);
    Cut localCut2 = (Cut)Ordering.natural().min(this.upperBound, paramRange.upperBound);
    return Ranges.create(localCut1, localCut2);
  }

  public Range span(Range paramRange)
  {
    Cut localCut1 = (Cut)Ordering.natural().min(this.lowerBound, paramRange.lowerBound);
    Cut localCut2 = (Cut)Ordering.natural().max(this.upperBound, paramRange.upperBound);
    return Ranges.create(localCut1, localCut2);
  }

  @GwtCompatible(serializable=false)
  public ContiguousSet asSet(DiscreteDomain paramDiscreteDomain)
  {
    Preconditions.checkNotNull(paramDiscreteDomain);
    Range localRange = this;
    try
    {
      if (!hasLowerBound())
        localRange = localRange.intersection(Ranges.atLeast(paramDiscreteDomain.minValue()));
      if (!hasUpperBound())
        localRange = localRange.intersection(Ranges.atMost(paramDiscreteDomain.maxValue()));
    }
    catch (NoSuchElementException localNoSuchElementException)
    {
      throw new IllegalArgumentException(localNoSuchElementException);
    }
    int i = (localRange.isEmpty()) || (compareOrThrow(this.lowerBound.leastValueAbove(paramDiscreteDomain), this.upperBound.greatestValueBelow(paramDiscreteDomain)) > 0) ? 1 : 0;
    return i != 0 ? new EmptyContiguousSet(paramDiscreteDomain) : new RegularContiguousSet(localRange, paramDiscreteDomain);
  }

  public Range canonical(DiscreteDomain paramDiscreteDomain)
  {
    Preconditions.checkNotNull(paramDiscreteDomain);
    Cut localCut1 = this.lowerBound.canonical(paramDiscreteDomain);
    Cut localCut2 = this.upperBound.canonical(paramDiscreteDomain);
    return (localCut1 == this.lowerBound) && (localCut2 == this.upperBound) ? this : Ranges.create(localCut1, localCut2);
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof Range))
    {
      Range localRange = (Range)paramObject;
      return (this.lowerBound.equals(localRange.lowerBound)) && (this.upperBound.equals(localRange.upperBound));
    }
    return false;
  }

  public int hashCode()
  {
    return this.lowerBound.hashCode() * 31 + this.upperBound.hashCode();
  }

  public String toString()
  {
    return toString(this.lowerBound, this.upperBound);
  }

  private static String toString(Cut paramCut1, Cut paramCut2)
  {
    StringBuilder localStringBuilder = new StringBuilder(16);
    paramCut1.describeAsLowerBound(localStringBuilder);
    localStringBuilder.append('‥');
    paramCut2.describeAsUpperBound(localStringBuilder);
    return localStringBuilder.toString();
  }

  private static SortedSet cast(Iterable paramIterable)
  {
    return (SortedSet)paramIterable;
  }

  static int compareOrThrow(Comparable paramComparable1, Comparable paramComparable2)
  {
    return paramComparable1.compareTo(paramComparable2);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.Range
 * JD-Core Version:    0.6.2
 */