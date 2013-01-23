package com.google.common.util.concurrent;

import java.util.concurrent.Callable;

public abstract class ForwardingListeningExecutorService extends ForwardingExecutorService
  implements ListeningExecutorService
{
  protected abstract ListeningExecutorService delegate();

  public ListenableFuture submit(Callable paramCallable)
  {
    return delegate().submit(paramCallable);
  }

  public ListenableFuture submit(Runnable paramRunnable)
  {
    return delegate().submit(paramRunnable);
  }

  public ListenableFuture submit(Runnable paramRunnable, Object paramObject)
  {
    return delegate().submit(paramRunnable, paramObject);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.util.concurrent.ForwardingListeningExecutorService
 * JD-Core Version:    0.6.2
 */