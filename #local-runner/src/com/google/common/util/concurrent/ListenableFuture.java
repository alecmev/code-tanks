package com.google.common.util.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.Future;

public abstract interface ListenableFuture extends Future
{
  public abstract void addListener(Runnable paramRunnable, Executor paramExecutor);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.util.concurrent.ListenableFuture
 * JD-Core Version:    0.6.2
 */