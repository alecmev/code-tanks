package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import java.util.SortedMap;

@Beta
@GwtCompatible
public abstract interface SortedMapDifference extends MapDifference
{
  public abstract SortedMap entriesOnlyOnLeft();

  public abstract SortedMap entriesOnlyOnRight();

  public abstract SortedMap entriesInCommon();

  public abstract SortedMap entriesDiffering();
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.SortedMapDifference
 * JD-Core Version:    0.6.2
 */