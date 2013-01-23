package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Beta
public final class Uninterruptibles
{
  public static void awaitUninterruptibly(CountDownLatch paramCountDownLatch)
  {
    int i = 0;
    try
    {
      paramCountDownLatch.await();
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      while (true)
        i = 1;
    }
    finally
    {
      if (i != 0)
        Thread.currentThread().interrupt();
    }
  }

  public static boolean awaitUninterruptibly(CountDownLatch paramCountDownLatch, long paramLong, TimeUnit paramTimeUnit)
  {
    int i = 0;
    try
    {
      long l1 = paramTimeUnit.toNanos(paramLong);
      long l2 = System.nanoTime() + l1;
      while (true)
        try
        {
          boolean bool = paramCountDownLatch.await(l1, TimeUnit.NANOSECONDS);
          return bool;
        }
        catch (InterruptedException localInterruptedException)
        {
          i = 1;
          l1 = l2 - System.nanoTime();
        }
    }
    finally
    {
      if (i != 0)
        Thread.currentThread().interrupt();
    }
  }

  public static void joinUninterruptibly(Thread paramThread)
  {
    int i = 0;
    try
    {
      paramThread.join();
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      while (true)
        i = 1;
    }
    finally
    {
      if (i != 0)
        Thread.currentThread().interrupt();
    }
  }

  public static Object getUninterruptibly(Future paramFuture)
    throws ExecutionException
  {
    int i = 0;
    try
    {
      Object localObject1 = paramFuture.get();
      return localObject1;
    }
    catch (InterruptedException localInterruptedException)
    {
      while (true)
        i = 1;
    }
    finally
    {
      if (i != 0)
        Thread.currentThread().interrupt();
    }
  }

  public static Object getUninterruptibly(Future paramFuture, long paramLong, TimeUnit paramTimeUnit)
    throws ExecutionException, TimeoutException
  {
    int i = 0;
    try
    {
      long l1 = paramTimeUnit.toNanos(paramLong);
      long l2 = System.nanoTime() + l1;
      while (true)
        try
        {
          Object localObject1 = paramFuture.get(l1, TimeUnit.NANOSECONDS);
          return localObject1;
        }
        catch (InterruptedException localInterruptedException)
        {
          i = 1;
          l1 = l2 - System.nanoTime();
        }
    }
    finally
    {
      if (i != 0)
        Thread.currentThread().interrupt();
    }
  }

  public static void joinUninterruptibly(Thread paramThread, long paramLong, TimeUnit paramTimeUnit)
  {
    Preconditions.checkNotNull(paramThread);
    int i = 0;
    try
    {
      long l1 = paramTimeUnit.toNanos(paramLong);
      long l2 = System.nanoTime() + l1;
      while (true)
        try
        {
          TimeUnit.NANOSECONDS.timedJoin(paramThread, l1);
          return;
        }
        catch (InterruptedException localInterruptedException)
        {
          i = 1;
          l1 = l2 - System.nanoTime();
        }
    }
    finally
    {
      if (i != 0)
        Thread.currentThread().interrupt();
    }
  }

  public static Object takeUninterruptibly(BlockingQueue paramBlockingQueue)
  {
    int i = 0;
    try
    {
      Object localObject1 = paramBlockingQueue.take();
      return localObject1;
    }
    catch (InterruptedException localInterruptedException)
    {
      while (true)
        i = 1;
    }
    finally
    {
      if (i != 0)
        Thread.currentThread().interrupt();
    }
  }

  public static void putUninterruptibly(BlockingQueue paramBlockingQueue, Object paramObject)
  {
    int i = 0;
    try
    {
      paramBlockingQueue.put(paramObject);
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      while (true)
        i = 1;
    }
    finally
    {
      if (i != 0)
        Thread.currentThread().interrupt();
    }
  }

  public static void sleepUninterruptibly(long paramLong, TimeUnit paramTimeUnit)
  {
    int i = 0;
    try
    {
      long l1 = paramTimeUnit.toNanos(paramLong);
      long l2 = System.nanoTime() + l1;
      while (true)
        try
        {
          TimeUnit.NANOSECONDS.sleep(l1);
          return;
        }
        catch (InterruptedException localInterruptedException)
        {
          i = 1;
          l1 = l2 - System.nanoTime();
        }
    }
    finally
    {
      if (i != 0)
        Thread.currentThread().interrupt();
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.util.concurrent.Uninterruptibles
 * JD-Core Version:    0.6.2
 */