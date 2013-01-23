package com.google.common.cache;

import com.google.common.annotations.Beta;

@Beta
public abstract interface Weigher
{
  public abstract int weigh(Object paramObject1, Object paramObject2);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.cache.Weigher
 * JD-Core Version:    0.6.2
 */