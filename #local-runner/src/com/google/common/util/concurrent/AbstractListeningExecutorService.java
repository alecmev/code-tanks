package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

abstract class AbstractListeningExecutorService
  implements ListeningExecutorService
{
  public ListenableFuture submit(Runnable paramRunnable)
  {
    ListenableFutureTask localListenableFutureTask = ListenableFutureTask.create(paramRunnable, null);
    execute(localListenableFutureTask);
    return localListenableFutureTask;
  }

  public ListenableFuture submit(Runnable paramRunnable, Object paramObject)
  {
    ListenableFutureTask localListenableFutureTask = ListenableFutureTask.create(paramRunnable, paramObject);
    execute(localListenableFutureTask);
    return localListenableFutureTask;
  }

  public ListenableFuture submit(Callable paramCallable)
  {
    ListenableFutureTask localListenableFutureTask = ListenableFutureTask.create(paramCallable);
    execute(localListenableFutureTask);
    return localListenableFutureTask;
  }

  private Object doInvokeAny(Collection paramCollection, boolean paramBoolean, long paramLong)
    throws InterruptedException, ExecutionException, TimeoutException
  {
    int i = paramCollection.size();
    Preconditions.checkArgument(i > 0);
    ArrayList localArrayList = new ArrayList(i);
    ExecutorCompletionService localExecutorCompletionService = new ExecutorCompletionService(this);
    try
    {
      Object localObject1 = null;
      long l1 = paramBoolean ? System.nanoTime() : 0L;
      Iterator localIterator1 = paramCollection.iterator();
      localArrayList.add(localExecutorCompletionService.submit((Callable)localIterator1.next()));
      i--;
      int j = 1;
      while (true)
      {
        Future localFuture1 = localExecutorCompletionService.poll();
        if (localFuture1 == null)
          if (i > 0)
          {
            i--;
            localArrayList.add(localExecutorCompletionService.submit((Callable)localIterator1.next()));
            j++;
          }
          else
          {
            if (j == 0)
              break;
            if (paramBoolean)
            {
              localFuture1 = localExecutorCompletionService.poll(paramLong, TimeUnit.NANOSECONDS);
              if (localFuture1 == null)
                throw new TimeoutException();
              long l2 = System.nanoTime();
              paramLong -= l2 - l1;
              l1 = l2;
            }
            else
            {
              localFuture1 = localExecutorCompletionService.take();
            }
          }
        if (localFuture1 != null)
        {
          j--;
          try
          {
            Object localObject2 = localFuture1.get();
            Iterator localIterator2;
            Future localFuture2;
            return localObject2;
          }
          catch (ExecutionException localExecutionException)
          {
            localObject1 = localExecutionException;
          }
          catch (RuntimeException localRuntimeException)
          {
            localObject1 = new ExecutionException(localRuntimeException);
          }
        }
      }
      if (localObject1 == null)
        localObject1 = new ExecutionException(null);
      throw ((Throwable)localObject1);
    }
    finally
    {
      Iterator localIterator3 = localArrayList.iterator();
      while (localIterator3.hasNext())
      {
        Future localFuture3 = (Future)localIterator3.next();
        localFuture3.cancel(true);
      }
    }
  }

  public Object invokeAny(Collection paramCollection)
    throws InterruptedException, ExecutionException
  {
    try
    {
      return doInvokeAny(paramCollection, false, 0L);
    }
    catch (TimeoutException localTimeoutException)
    {
    }
    throw new AssertionError();
  }

  public Object invokeAny(Collection paramCollection, long paramLong, TimeUnit paramTimeUnit)
    throws InterruptedException, ExecutionException, TimeoutException
  {
    return doInvokeAny(paramCollection, true, paramTimeUnit.toNanos(paramLong));
  }

  public List invokeAll(Collection paramCollection)
    throws InterruptedException
  {
    if (paramCollection == null)
      throw new NullPointerException();
    ArrayList localArrayList = new ArrayList(paramCollection.size());
    int i = 0;
    try
    {
      Object localObject1 = paramCollection.iterator();
      Object localObject2;
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (Callable)((Iterator)localObject1).next();
        ListenableFutureTask localListenableFutureTask = ListenableFutureTask.create((Callable)localObject2);
        localArrayList.add(localListenableFutureTask);
        execute(localListenableFutureTask);
      }
      localObject1 = localArrayList.iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (Future)((Iterator)localObject1).next();
        if (!((Future)localObject2).isDone())
          try
          {
            ((Future)localObject2).get();
          }
          catch (CancellationException localCancellationException)
          {
          }
          catch (ExecutionException localExecutionException)
          {
          }
      }
      i = 1;
      localObject1 = localArrayList;
      if (i == 0)
      {
        localObject2 = localArrayList.iterator();
        while (((Iterator)localObject2).hasNext())
        {
          Future localFuture1 = (Future)((Iterator)localObject2).next();
          localFuture1.cancel(true);
        }
      }
      return localObject1;
    }
    finally
    {
      if (i == 0)
      {
        Iterator localIterator = localArrayList.iterator();
        while (localIterator.hasNext())
        {
          Future localFuture2 = (Future)localIterator.next();
          localFuture2.cancel(true);
        }
      }
    }
  }

  public List invokeAll(Collection paramCollection, long paramLong, TimeUnit paramTimeUnit)
    throws InterruptedException
  {
    if ((paramCollection == null) || (paramTimeUnit == null))
      throw new NullPointerException();
    long l1 = paramTimeUnit.toNanos(paramLong);
    ArrayList localArrayList1 = new ArrayList(paramCollection.size());
    int i = 0;
    try
    {
      Iterator localIterator1 = paramCollection.iterator();
      while (localIterator1.hasNext())
      {
        Callable localCallable = (Callable)localIterator1.next();
        localArrayList1.add(ListenableFutureTask.create(localCallable));
      }
      long l2 = System.nanoTime();
      Iterator localIterator2 = localArrayList1.iterator();
      ArrayList localArrayList2;
      Object localObject3;
      Object localObject4;
      while (localIterator2.hasNext())
      {
        execute((Runnable)localIterator2.next());
        long l3 = System.nanoTime();
        l1 -= l3 - l2;
        l2 = l3;
        if (l1 <= 0L)
        {
          localArrayList2 = localArrayList1;
          if (i == 0)
          {
            localObject3 = localArrayList1.iterator();
            while (((Iterator)localObject3).hasNext())
            {
              localObject4 = (Future)((Iterator)localObject3).next();
              ((Future)localObject4).cancel(true);
            }
          }
          return localArrayList2;
        }
      }
      Object localObject1 = localArrayList1.iterator();
      Object localObject2;
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (Future)((Iterator)localObject1).next();
        if (!((Future)localObject2).isDone())
        {
          if (l1 <= 0L)
          {
            localArrayList2 = localArrayList1;
            if (i == 0)
            {
              localObject3 = localArrayList1.iterator();
              while (((Iterator)localObject3).hasNext())
              {
                localObject4 = (Future)((Iterator)localObject3).next();
                ((Future)localObject4).cancel(true);
              }
            }
            return localArrayList2;
          }
          try
          {
            ((Future)localObject2).get(l1, TimeUnit.NANOSECONDS);
          }
          catch (CancellationException localCancellationException)
          {
          }
          catch (ExecutionException localExecutionException)
          {
          }
          catch (TimeoutException localTimeoutException)
          {
            localObject3 = localArrayList1;
            if (i == 0)
            {
              localObject4 = localArrayList1.iterator();
              while (((Iterator)localObject4).hasNext())
              {
                Future localFuture2 = (Future)((Iterator)localObject4).next();
                localFuture2.cancel(true);
              }
            }
            return localObject3;
          }
          long l4 = System.nanoTime();
          l1 -= l4 - l2;
          l2 = l4;
        }
      }
      i = 1;
      localObject1 = localArrayList1;
      if (i == 0)
      {
        localObject2 = localArrayList1.iterator();
        while (((Iterator)localObject2).hasNext())
        {
          Future localFuture1 = (Future)((Iterator)localObject2).next();
          localFuture1.cancel(true);
        }
      }
      return localObject1;
    }
    finally
    {
      if (i == 0)
      {
        Iterator localIterator3 = localArrayList1.iterator();
        while (localIterator3.hasNext())
        {
          Future localFuture3 = (Future)localIterator3.next();
          localFuture3.cancel(true);
        }
      }
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.util.concurrent.AbstractListeningExecutorService
 * JD-Core Version:    0.6.2
 */