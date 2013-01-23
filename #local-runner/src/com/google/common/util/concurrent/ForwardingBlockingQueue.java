package com.google.common.util.concurrent;

import com.google.common.collect.ForwardingQueue;
import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public abstract class ForwardingBlockingQueue extends ForwardingQueue
  implements BlockingQueue
{
  protected abstract BlockingQueue delegate();

  public int drainTo(Collection paramCollection, int paramInt)
  {
    return delegate().drainTo(paramCollection, paramInt);
  }

  public int drainTo(Collection paramCollection)
  {
    return delegate().drainTo(paramCollection);
  }

  public boolean offer(Object paramObject, long paramLong, TimeUnit paramTimeUnit)
    throws InterruptedException
  {
    return delegate().offer(paramObject, paramLong, paramTimeUnit);
  }

  public Object poll(long paramLong, TimeUnit paramTimeUnit)
    throws InterruptedException
  {
    return delegate().poll(paramLong, paramTimeUnit);
  }

  public void put(Object paramObject)
    throws InterruptedException
  {
    delegate().put(paramObject);
  }

  public int remainingCapacity()
  {
    return delegate().remainingCapacity();
  }

  public Object take()
    throws InterruptedException
  {
    return delegate().take();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.util.concurrent.ForwardingBlockingQueue
 * JD-Core Version:    0.6.2
 */