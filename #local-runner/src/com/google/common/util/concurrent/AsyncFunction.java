package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;

@Beta
public abstract interface AsyncFunction
{
  public abstract ListenableFuture apply(Object paramObject)
    throws Exception;
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.util.concurrent.AsyncFunction
 * JD-Core Version:    0.6.2
 */