package com.google.common.base;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible
public abstract interface Predicate
{
  public abstract boolean apply(Object paramObject);

  public abstract boolean equals(Object paramObject);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.base.Predicate
 * JD-Core Version:    0.6.2
 */