package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
@Beta
public abstract interface MapConstraint
{
  public abstract void checkKeyValue(Object paramObject1, Object paramObject2);

  public abstract String toString();
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.MapConstraint
 * JD-Core Version:    0.6.2
 */