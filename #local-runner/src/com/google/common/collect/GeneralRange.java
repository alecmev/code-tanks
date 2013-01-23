package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.Comparator;

@GwtCompatible(serializable=true)
final class GeneralRange
  implements Serializable
{
  private final Comparator comparator;
  private final boolean hasLowerBound;
  private final Object lowerEndpoint;
  private final BoundType lowerBoundType;
  private final boolean hasUpperBound;
  private final Object upperEndpoint;
  private final BoundType upperBoundType;
  private transient GeneralRange reverse;

  static GeneralRange from(Range paramRange)
  {
    Object localObject1 = paramRange.hasLowerBound() ? paramRange.lowerEndpoint() : null;
    BoundType localBoundType1 = paramRange.hasLowerBound() ? paramRange.lowerBoundType() : BoundType.OPEN;
    Object localObject2 = paramRange.hasUpperBound() ? paramRange.upperEndpoint() : null;
    BoundType localBoundType2 = paramRange.hasUpperBound() ? paramRange.upperBoundType() : BoundType.OPEN;
    return new GeneralRange(Ordering.natural(), paramRange.hasLowerBound(), localObject1, localBoundType1, paramRange.hasUpperBound(), localObject2, localBoundType2);
  }

  static GeneralRange all(Comparator paramComparator)
  {
    return new GeneralRange(paramComparator, false, null, BoundType.OPEN, false, null, BoundType.OPEN);
  }

  static GeneralRange downTo(Comparator paramComparator, Object paramObject, BoundType paramBoundType)
  {
    return new GeneralRange(paramComparator, true, paramObject, paramBoundType, false, null, BoundType.OPEN);
  }

  static GeneralRange upTo(Comparator paramComparator, Object paramObject, BoundType paramBoundType)
  {
    return new GeneralRange(paramComparator, false, null, BoundType.OPEN, true, paramObject, paramBoundType);
  }

  static GeneralRange range(Comparator paramComparator, Object paramObject1, BoundType paramBoundType1, Object paramObject2, BoundType paramBoundType2)
  {
    return new GeneralRange(paramComparator, true, paramObject1, paramBoundType1, true, paramObject2, paramBoundType2);
  }

  private GeneralRange(Comparator paramComparator, boolean paramBoolean1, Object paramObject1, BoundType paramBoundType1, boolean paramBoolean2, Object paramObject2, BoundType paramBoundType2)
  {
    this.comparator = ((Comparator)Preconditions.checkNotNull(paramComparator));
    this.hasLowerBound = paramBoolean1;
    this.hasUpperBound = paramBoolean2;
    this.lowerEndpoint = paramObject1;
    this.lowerBoundType = ((BoundType)Preconditions.checkNotNull(paramBoundType1));
    this.upperEndpoint = paramObject2;
    this.upperBoundType = ((BoundType)Preconditions.checkNotNull(paramBoundType2));
    if (paramBoolean1)
      paramComparator.compare(paramObject1, paramObject1);
    if (paramBoolean2)
      paramComparator.compare(paramObject2, paramObject2);
    if ((paramBoolean1) && (paramBoolean2))
    {
      int i = paramComparator.compare(paramObject1, paramObject2);
      Preconditions.checkArgument(i <= 0, "lowerEndpoint (%s) > upperEndpoint (%s)", new Object[] { paramObject1, paramObject2 });
      if (i == 0)
        Preconditions.checkArgument((paramBoundType1 != BoundType.OPEN ? 1 : 0) | (paramBoundType2 != BoundType.OPEN ? 1 : 0));
    }
  }

  Comparator comparator()
  {
    return this.comparator;
  }

  boolean hasLowerBound()
  {
    return this.hasLowerBound;
  }

  boolean hasUpperBound()
  {
    return this.hasUpperBound;
  }

  boolean isEmpty()
  {
    return ((hasUpperBound()) && (tooLow(this.upperEndpoint))) || ((hasLowerBound()) && (tooHigh(this.lowerEndpoint)));
  }

  boolean tooLow(Object paramObject)
  {
    if (!hasLowerBound())
      return false;
    Object localObject = this.lowerEndpoint;
    int i = this.comparator.compare(paramObject, localObject);
    return (i < 0 ? 1 : 0) | (i == 0 ? 1 : 0) & (this.lowerBoundType == BoundType.OPEN ? 1 : 0);
  }

  boolean tooHigh(Object paramObject)
  {
    if (!hasUpperBound())
      return false;
    Object localObject = this.upperEndpoint;
    int i = this.comparator.compare(paramObject, localObject);
    return (i > 0 ? 1 : 0) | (i == 0 ? 1 : 0) & (this.upperBoundType == BoundType.OPEN ? 1 : 0);
  }

  boolean contains(Object paramObject)
  {
    return (!tooLow(paramObject)) && (!tooHigh(paramObject));
  }

  GeneralRange intersect(GeneralRange paramGeneralRange)
  {
    Preconditions.checkNotNull(paramGeneralRange);
    Preconditions.checkArgument(this.comparator.equals(paramGeneralRange.comparator));
    boolean bool1 = this.hasLowerBound;
    Object localObject1 = this.lowerEndpoint;
    BoundType localBoundType1 = this.lowerBoundType;
    if (!hasLowerBound())
    {
      bool1 = paramGeneralRange.hasLowerBound;
      localObject1 = paramGeneralRange.lowerEndpoint;
      localBoundType1 = paramGeneralRange.lowerBoundType;
    }
    else if (paramGeneralRange.hasLowerBound())
    {
      int i = this.comparator.compare(this.lowerEndpoint, paramGeneralRange.lowerEndpoint);
      if ((i < 0) || ((i == 0) && (paramGeneralRange.lowerBoundType == BoundType.OPEN)))
      {
        localObject1 = paramGeneralRange.lowerEndpoint;
        localBoundType1 = paramGeneralRange.lowerBoundType;
      }
    }
    boolean bool2 = this.hasUpperBound;
    Object localObject2 = this.upperEndpoint;
    BoundType localBoundType2 = this.upperBoundType;
    int j;
    if (!hasUpperBound())
    {
      bool2 = paramGeneralRange.hasUpperBound;
      localObject2 = paramGeneralRange.upperEndpoint;
      localBoundType2 = paramGeneralRange.upperBoundType;
    }
    else if (paramGeneralRange.hasUpperBound())
    {
      j = this.comparator.compare(this.upperEndpoint, paramGeneralRange.upperEndpoint);
      if ((j > 0) || ((j == 0) && (paramGeneralRange.upperBoundType == BoundType.OPEN)))
      {
        localObject2 = paramGeneralRange.upperEndpoint;
        localBoundType2 = paramGeneralRange.upperBoundType;
      }
    }
    if ((bool1) && (bool2))
    {
      j = this.comparator.compare(localObject1, localObject2);
      if ((j > 0) || ((j == 0) && (localBoundType1 == BoundType.OPEN) && (localBoundType2 == BoundType.OPEN)))
      {
        localObject1 = localObject2;
        localBoundType1 = BoundType.OPEN;
        localBoundType2 = BoundType.CLOSED;
      }
    }
    return new GeneralRange(this.comparator, bool1, localObject1, localBoundType1, bool2, localObject2, localBoundType2);
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof GeneralRange))
    {
      GeneralRange localGeneralRange = (GeneralRange)paramObject;
      return (this.comparator.equals(localGeneralRange.comparator)) && (this.hasLowerBound == localGeneralRange.hasLowerBound) && (this.hasUpperBound == localGeneralRange.hasUpperBound) && (this.lowerBoundType.equals(localGeneralRange.lowerBoundType)) && (this.upperBoundType.equals(localGeneralRange.upperBoundType)) && (Objects.equal(this.lowerEndpoint, localGeneralRange.lowerEndpoint)) && (Objects.equal(this.upperEndpoint, localGeneralRange.upperEndpoint));
    }
    return false;
  }

  public int hashCode()
  {
    return Objects.hashCode(new Object[] { this.comparator, this.lowerEndpoint, this.lowerBoundType, this.upperEndpoint, this.upperBoundType });
  }

  public GeneralRange reverse()
  {
    GeneralRange localGeneralRange = this.reverse;
    if (localGeneralRange == null)
    {
      localGeneralRange = new GeneralRange(Ordering.from(this.comparator).reverse(), this.hasUpperBound, this.upperEndpoint, this.upperBoundType, this.hasLowerBound, this.lowerEndpoint, this.lowerBoundType);
      localGeneralRange.reverse = this;
      return this.reverse = localGeneralRange;
    }
    return localGeneralRange;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.comparator).append(":");
    switch (1.$SwitchMap$com$google$common$collect$BoundType[this.lowerBoundType.ordinal()])
    {
    case 1:
      localStringBuilder.append('[');
      break;
    case 2:
      localStringBuilder.append('(');
    }
    if (hasLowerBound())
      localStringBuilder.append(this.lowerEndpoint);
    else
      localStringBuilder.append("-∞");
    localStringBuilder.append(',');
    if (hasUpperBound())
      localStringBuilder.append(this.upperEndpoint);
    else
      localStringBuilder.append("∞");
    switch (1.$SwitchMap$com$google$common$collect$BoundType[this.upperBoundType.ordinal()])
    {
    case 1:
      localStringBuilder.append(']');
      break;
    case 2:
      localStringBuilder.append(')');
    }
    return localStringBuilder.toString();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.GeneralRange
 * JD-Core Version:    0.6.2
 */