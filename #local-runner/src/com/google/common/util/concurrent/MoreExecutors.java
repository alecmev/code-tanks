package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class MoreExecutors
{
  @Beta
  public static ExecutorService getExitingExecutorService(ThreadPoolExecutor paramThreadPoolExecutor, long paramLong, TimeUnit paramTimeUnit)
  {
    paramThreadPoolExecutor.setThreadFactory(new ThreadFactoryBuilder().setDaemon(true).setThreadFactory(paramThreadPoolExecutor.getThreadFactory()).build());
    ExecutorService localExecutorService = Executors.unconfigurableExecutorService(paramThreadPoolExecutor);
    addDelayedShutdownHook(localExecutorService, paramLong, paramTimeUnit);
    return localExecutorService;
  }

  @Beta
  public static ScheduledExecutorService getExitingScheduledExecutorService(ScheduledThreadPoolExecutor paramScheduledThreadPoolExecutor, long paramLong, TimeUnit paramTimeUnit)
  {
    paramScheduledThreadPoolExecutor.setThreadFactory(new ThreadFactoryBuilder().setDaemon(true).setThreadFactory(paramScheduledThreadPoolExecutor.getThreadFactory()).build());
    ScheduledExecutorService localScheduledExecutorService = Executors.unconfigurableScheduledExecutorService(paramScheduledThreadPoolExecutor);
    addDelayedShutdownHook(localScheduledExecutorService, paramLong, paramTimeUnit);
    return localScheduledExecutorService;
  }

  @Beta
  public static void addDelayedShutdownHook(ExecutorService paramExecutorService, final long paramLong, TimeUnit paramTimeUnit)
  {
    Runtime.getRuntime().addShutdownHook(new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          this.val$service.shutdown();
          this.val$service.awaitTermination(paramLong, this.val$timeUnit);
        }
        catch (InterruptedException localInterruptedException)
        {
        }
      }
    }));
  }

  @Beta
  public static ExecutorService getExitingExecutorService(ThreadPoolExecutor paramThreadPoolExecutor)
  {
    return getExitingExecutorService(paramThreadPoolExecutor, 120L, TimeUnit.SECONDS);
  }

  @Beta
  public static ScheduledExecutorService getExitingScheduledExecutorService(ScheduledThreadPoolExecutor paramScheduledThreadPoolExecutor)
  {
    return getExitingScheduledExecutorService(paramScheduledThreadPoolExecutor, 120L, TimeUnit.SECONDS);
  }

  public static ListeningExecutorService sameThreadExecutor()
  {
    return new SameThreadExecutorService(null);
  }

  public static ListeningExecutorService listeningDecorator(ExecutorService paramExecutorService)
  {
    return (paramExecutorService instanceof ScheduledExecutorService) ? new ScheduledListeningDecorator((ScheduledExecutorService)paramExecutorService) : (paramExecutorService instanceof ListeningExecutorService) ? (ListeningExecutorService)paramExecutorService : new ListeningDecorator(paramExecutorService);
  }

  public static ListeningScheduledExecutorService listeningDecorator(ScheduledExecutorService paramScheduledExecutorService)
  {
    return (paramScheduledExecutorService instanceof ListeningScheduledExecutorService) ? (ListeningScheduledExecutorService)paramScheduledExecutorService : new ScheduledListeningDecorator(paramScheduledExecutorService);
  }

  private static class ScheduledListeningDecorator extends MoreExecutors.ListeningDecorator
    implements ListeningScheduledExecutorService
  {
    final ScheduledExecutorService delegate;

    ScheduledListeningDecorator(ScheduledExecutorService paramScheduledExecutorService)
    {
      super();
      this.delegate = ((ScheduledExecutorService)Preconditions.checkNotNull(paramScheduledExecutorService));
    }

    public ScheduledFuture schedule(Runnable paramRunnable, long paramLong, TimeUnit paramTimeUnit)
    {
      return this.delegate.schedule(paramRunnable, paramLong, paramTimeUnit);
    }

    public ScheduledFuture schedule(Callable paramCallable, long paramLong, TimeUnit paramTimeUnit)
    {
      return this.delegate.schedule(paramCallable, paramLong, paramTimeUnit);
    }

    public ScheduledFuture scheduleAtFixedRate(Runnable paramRunnable, long paramLong1, long paramLong2, TimeUnit paramTimeUnit)
    {
      return this.delegate.scheduleAtFixedRate(paramRunnable, paramLong1, paramLong2, paramTimeUnit);
    }

    public ScheduledFuture scheduleWithFixedDelay(Runnable paramRunnable, long paramLong1, long paramLong2, TimeUnit paramTimeUnit)
    {
      return this.delegate.scheduleWithFixedDelay(paramRunnable, paramLong1, paramLong2, paramTimeUnit);
    }
  }

  private static class ListeningDecorator extends AbstractListeningExecutorService
  {
    final ExecutorService delegate;

    ListeningDecorator(ExecutorService paramExecutorService)
    {
      this.delegate = ((ExecutorService)Preconditions.checkNotNull(paramExecutorService));
    }

    public boolean awaitTermination(long paramLong, TimeUnit paramTimeUnit)
      throws InterruptedException
    {
      return this.delegate.awaitTermination(paramLong, paramTimeUnit);
    }

    public boolean isShutdown()
    {
      return this.delegate.isShutdown();
    }

    public boolean isTerminated()
    {
      return this.delegate.isTerminated();
    }

    public void shutdown()
    {
      this.delegate.shutdown();
    }

    public List shutdownNow()
    {
      return this.delegate.shutdownNow();
    }

    public void execute(Runnable paramRunnable)
    {
      this.delegate.execute(paramRunnable);
    }
  }

  private static class SameThreadExecutorService extends AbstractListeningExecutorService
  {
    private final Lock lock = new ReentrantLock();
    private final Condition termination = this.lock.newCondition();
    private int runningTasks = 0;
    private boolean shutdown = false;

    public void execute(Runnable paramRunnable)
    {
      startTask();
      try
      {
        paramRunnable.run();
      }
      finally
      {
        endTask();
      }
    }

    public boolean isShutdown()
    {
      this.lock.lock();
      try
      {
        boolean bool = this.shutdown;
        return bool;
      }
      finally
      {
        this.lock.unlock();
      }
    }

    public void shutdown()
    {
      this.lock.lock();
      try
      {
        this.shutdown = true;
      }
      finally
      {
        this.lock.unlock();
      }
    }

    public List shutdownNow()
    {
      shutdown();
      return Collections.emptyList();
    }

    public boolean isTerminated()
    {
      this.lock.lock();
      try
      {
        boolean bool = (this.shutdown) && (this.runningTasks == 0);
        return bool;
      }
      finally
      {
        this.lock.unlock();
      }
    }

    public boolean awaitTermination(long paramLong, TimeUnit paramTimeUnit)
      throws InterruptedException
    {
      long l = paramTimeUnit.toNanos(paramLong);
      this.lock.lock();
      try
      {
        while (true)
        {
          boolean bool;
          if (isTerminated())
          {
            bool = true;
            return bool;
          }
          if (l <= 0L)
          {
            bool = false;
            return bool;
          }
          l = this.termination.awaitNanos(l);
        }
      }
      finally
      {
        this.lock.unlock();
      }
    }

    private void startTask()
    {
      this.lock.lock();
      try
      {
        if (isShutdown())
          throw new RejectedExecutionException("Executor already shutdown");
        this.runningTasks += 1;
      }
      finally
      {
        this.lock.unlock();
      }
    }

    private void endTask()
    {
      this.lock.lock();
      try
      {
        this.runningTasks -= 1;
        if (isTerminated())
          this.termination.signalAll();
      }
      finally
      {
        this.lock.unlock();
      }
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.util.concurrent.MoreExecutors
 * JD-Core Version:    0.6.2
 */