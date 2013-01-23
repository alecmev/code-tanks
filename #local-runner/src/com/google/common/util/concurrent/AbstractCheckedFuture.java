package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Beta
public abstract class AbstractCheckedFuture extends ForwardingListenableFuture.SimpleForwardingListenableFuture
  implements CheckedFuture
{
  protected AbstractCheckedFuture(ListenableFuture paramListenableFuture)
  {
    super(paramListenableFuture);
  }

  protected abstract Exception mapException(Exception paramException);

  public Object checkedGet()
    throws Exception
  {
    try
    {
      return get();
    }
    catch (InterruptedException localInterruptedException)
    {
      Thread.currentThread().interrupt();
      throw mapException(localInterruptedException);
    }
    catch (CancellationException localCancellationException)
    {
      throw mapException(localCancellationException);
    }
    catch (ExecutionException localExecutionException)
    {
      throw mapException(localExecutionException);
    }
  }

  public Object checkedGet(long paramLong, TimeUnit paramTimeUnit)
    throws TimeoutException, Exception
  {
    try
    {
      return get(paramLong, paramTimeUnit);
    }
    catch (InterruptedException localInterruptedException)
    {
      Thread.currentThread().interrupt();
      throw mapException(localInterruptedException);
    }
    catch (CancellationException localCancellationException)
    {
      throw mapException(localCancellationException);
    }
    catch (ExecutionException localExecutionException)
    {
      throw mapException(localExecutionException);
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.util.concurrent.AbstractCheckedFuture
 * JD-Core Version:    0.6.2
 */