package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Map;

@GwtCompatible
public abstract interface MapDifference
{
  public abstract boolean areEqual();

  public abstract Map entriesOnlyOnLeft();

  public abstract Map entriesOnlyOnRight();

  public abstract Map entriesInCommon();

  public abstract Map entriesDiffering();

  public abstract boolean equals(Object paramObject);

  public abstract int hashCode();

  public static abstract interface ValueDifference
  {
    public abstract Object leftValue();

    public abstract Object rightValue();

    public abstract boolean equals(Object paramObject);

    public abstract int hashCode();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.MapDifference
 * JD-Core Version:    0.6.2
 */