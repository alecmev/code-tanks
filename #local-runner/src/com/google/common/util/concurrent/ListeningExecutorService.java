package com.google.common.util.concurrent;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public abstract interface ListeningExecutorService extends ExecutorService
{
  public abstract ListenableFuture submit(Callable paramCallable);

  public abstract ListenableFuture submit(Runnable paramRunnable);

  public abstract ListenableFuture submit(Runnable paramRunnable, Object paramObject);

  public abstract List invokeAll(Collection paramCollection)
    throws InterruptedException;

  public abstract List invokeAll(Collection paramCollection, long paramLong, TimeUnit paramTimeUnit)
    throws InterruptedException;
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.util.concurrent.ListeningExecutorService
 * JD-Core Version:    0.6.2
 */