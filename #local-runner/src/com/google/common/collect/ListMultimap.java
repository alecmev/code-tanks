package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.List;
import java.util.Map;

@GwtCompatible
public abstract interface ListMultimap extends Multimap
{
  public abstract List get(Object paramObject);

  public abstract List removeAll(Object paramObject);

  public abstract List replaceValues(Object paramObject, Iterable paramIterable);

  public abstract Map asMap();

  public abstract boolean equals(Object paramObject);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.ListMultimap
 * JD-Core Version:    0.6.2
 */