package com.google.common.util.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;

public final class ListenableFutureTask extends FutureTask
  implements ListenableFuture
{
  private final ExecutionList executionList = new ExecutionList();

  public static ListenableFutureTask create(Callable paramCallable)
  {
    return new ListenableFutureTask(paramCallable);
  }

  public static ListenableFutureTask create(Runnable paramRunnable, Object paramObject)
  {
    return new ListenableFutureTask(paramRunnable, paramObject);
  }

  private ListenableFutureTask(Callable paramCallable)
  {
    super(paramCallable);
  }

  private ListenableFutureTask(Runnable paramRunnable, Object paramObject)
  {
    super(paramRunnable, paramObject);
  }

  public void addListener(Runnable paramRunnable, Executor paramExecutor)
  {
    this.executionList.add(paramRunnable, paramExecutor);
  }

  protected void done()
  {
    this.executionList.execute();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.util.concurrent.ListenableFutureTask
 * JD-Core Version:    0.6.2
 */