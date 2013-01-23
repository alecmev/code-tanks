package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import java.util.NoSuchElementException;
import java.util.Queue;

@GwtCompatible
public abstract class ForwardingQueue extends ForwardingCollection
  implements Queue
{
  protected abstract Queue delegate();

  public boolean offer(Object paramObject)
  {
    return delegate().offer(paramObject);
  }

  public Object poll()
  {
    return delegate().poll();
  }

  public Object remove()
  {
    return delegate().remove();
  }

  public Object peek()
  {
    return delegate().peek();
  }

  public Object element()
  {
    return delegate().element();
  }

  @Beta
  protected boolean standardOffer(Object paramObject)
  {
    try
    {
      return add(paramObject);
    }
    catch (IllegalStateException localIllegalStateException)
    {
    }
    return false;
  }

  @Beta
  protected Object standardPeek()
  {
    try
    {
      return element();
    }
    catch (NoSuchElementException localNoSuchElementException)
    {
    }
    return null;
  }

  @Beta
  protected Object standardPoll()
  {
    try
    {
      return remove();
    }
    catch (NoSuchElementException localNoSuchElementException)
    {
    }
    return null;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.ForwardingQueue
 * JD-Core Version:    0.6.2
 */