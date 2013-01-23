package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Beta
public abstract interface CheckedFuture extends ListenableFuture
{
  public abstract Object checkedGet()
    throws Exception;

  public abstract Object checkedGet(long paramLong, TimeUnit paramTimeUnit)
    throws TimeoutException, Exception;
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.util.concurrent.CheckedFuture
 * JD-Core Version:    0.6.2
 */