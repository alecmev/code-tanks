package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.Iterator;

@GwtCompatible
@Beta
public final class Ranges
{
  static Range create(Cut paramCut1, Cut paramCut2)
  {
    return new Range(paramCut1, paramCut2);
  }

  public static Range open(Comparable paramComparable1, Comparable paramComparable2)
  {
    return create(Cut.aboveValue(paramComparable1), Cut.belowValue(paramComparable2));
  }

  public static Range closed(Comparable paramComparable1, Comparable paramComparable2)
  {
    return create(Cut.belowValue(paramComparable1), Cut.aboveValue(paramComparable2));
  }

  public static Range closedOpen(Comparable paramComparable1, Comparable paramComparable2)
  {
    return create(Cut.belowValue(paramComparable1), Cut.belowValue(paramComparable2));
  }

  public static Range openClosed(Comparable paramComparable1, Comparable paramComparable2)
  {
    return create(Cut.aboveValue(paramComparable1), Cut.aboveValue(paramComparable2));
  }

  public static Range range(Comparable paramComparable1, BoundType paramBoundType1, Comparable paramComparable2, BoundType paramBoundType2)
  {
    Preconditions.checkNotNull(paramBoundType1);
    Preconditions.checkNotNull(paramBoundType2);
    Cut localCut1 = paramBoundType1 == BoundType.OPEN ? Cut.aboveValue(paramComparable1) : Cut.belowValue(paramComparable1);
    Cut localCut2 = paramBoundType2 == BoundType.OPEN ? Cut.belowValue(paramComparable2) : Cut.aboveValue(paramComparable2);
    return create(localCut1, localCut2);
  }

  public static Range lessThan(Comparable paramComparable)
  {
    return create(Cut.belowAll(), Cut.belowValue(paramComparable));
  }

  public static Range atMost(Comparable paramComparable)
  {
    return create(Cut.belowAll(), Cut.aboveValue(paramComparable));
  }

  public static Range upTo(Comparable paramComparable, BoundType paramBoundType)
  {
    switch (1.$SwitchMap$com$google$common$collect$BoundType[paramBoundType.ordinal()])
    {
    case 1:
      return lessThan(paramComparable);
    case 2:
      return atMost(paramComparable);
    }
    throw new AssertionError();
  }

  public static Range greaterThan(Comparable paramComparable)
  {
    return create(Cut.aboveValue(paramComparable), Cut.aboveAll());
  }

  public static Range atLeast(Comparable paramComparable)
  {
    return create(Cut.belowValue(paramComparable), Cut.aboveAll());
  }

  public static Range downTo(Comparable paramComparable, BoundType paramBoundType)
  {
    switch (1.$SwitchMap$com$google$common$collect$BoundType[paramBoundType.ordinal()])
    {
    case 1:
      return greaterThan(paramComparable);
    case 2:
      return atLeast(paramComparable);
    }
    throw new AssertionError();
  }

  public static Range all()
  {
    return create(Cut.belowAll(), Cut.aboveAll());
  }

  public static Range singleton(Comparable paramComparable)
  {
    return closed(paramComparable, paramComparable);
  }

  public static Range encloseAll(Iterable paramIterable)
  {
    Preconditions.checkNotNull(paramIterable);
    if ((paramIterable instanceof ContiguousSet))
      return ((ContiguousSet)paramIterable).range();
    Iterator localIterator = paramIterable.iterator();
    Comparable localComparable1 = (Comparable)Preconditions.checkNotNull(localIterator.next());
    Comparable localComparable3;
    for (Comparable localComparable2 = localComparable1; localIterator.hasNext(); localComparable2 = (Comparable)Ordering.natural().max(localComparable2, localComparable3))
    {
      localComparable3 = (Comparable)Preconditions.checkNotNull(localIterator.next());
      localComparable1 = (Comparable)Ordering.natural().min(localComparable1, localComparable3);
    }
    return closed(localComparable1, localComparable2);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.Ranges
 * JD-Core Version:    0.6.2
 */