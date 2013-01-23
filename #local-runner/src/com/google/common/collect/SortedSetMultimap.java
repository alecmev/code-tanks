package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Comparator;
import java.util.Map;
import java.util.SortedSet;

@GwtCompatible
public abstract interface SortedSetMultimap extends SetMultimap
{
  public abstract SortedSet get(Object paramObject);

  public abstract SortedSet removeAll(Object paramObject);

  public abstract SortedSet replaceValues(Object paramObject, Iterable paramIterable);

  public abstract Map asMap();

  public abstract Comparator valueComparator();
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.SortedSetMultimap
 * JD-Core Version:    0.6.2
 */