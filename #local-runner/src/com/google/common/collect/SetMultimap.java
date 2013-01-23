package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Map;
import java.util.Set;

@GwtCompatible
public abstract interface SetMultimap extends Multimap
{
  public abstract Set get(Object paramObject);

  public abstract Set removeAll(Object paramObject);

  public abstract Set replaceValues(Object paramObject, Iterable paramIterable);

  public abstract Set entries();

  public abstract Map asMap();

  public abstract boolean equals(Object paramObject);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.SetMultimap
 * JD-Core Version:    0.6.2
 */