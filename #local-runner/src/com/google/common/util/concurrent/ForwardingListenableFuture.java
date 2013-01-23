package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import java.util.concurrent.Executor;

public abstract class ForwardingListenableFuture extends ForwardingFuture
  implements ListenableFuture
{
  protected abstract ListenableFuture delegate();

  public void addListener(Runnable paramRunnable, Executor paramExecutor)
  {
    delegate().addListener(paramRunnable, paramExecutor);
  }

  public static abstract class SimpleForwardingListenableFuture extends ForwardingListenableFuture
  {
    private final ListenableFuture delegate;

    protected SimpleForwardingListenableFuture(ListenableFuture paramListenableFuture)
    {
      this.delegate = ((ListenableFuture)Preconditions.checkNotNull(paramListenableFuture));
    }

    protected final ListenableFuture delegate()
    {
      return this.delegate;
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.util.concurrent.ForwardingListenableFuture
 * JD-Core Version:    0.6.2
 */