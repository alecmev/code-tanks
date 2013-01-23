package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;

@Beta
@GwtCompatible
public abstract interface Constraint
{
  public abstract Object checkElement(Object paramObject);

  public abstract String toString();
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.Constraint
 * JD-Core Version:    0.6.2
 */