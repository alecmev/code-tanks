package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.collect.ForwardingObject;

@Beta
public abstract class ForwardingService extends ForwardingObject
  implements Service
{
  protected abstract Service delegate();

  public ListenableFuture start()
  {
    return delegate().start();
  }

  public Service.State state()
  {
    return delegate().state();
  }

  public ListenableFuture stop()
  {
    return delegate().stop();
  }

  public Service.State startAndWait()
  {
    return delegate().startAndWait();
  }

  public Service.State stopAndWait()
  {
    return delegate().stopAndWait();
  }

  public boolean isRunning()
  {
    return delegate().isRunning();
  }

  protected Service.State standardStartAndWait()
  {
    return (Service.State)Futures.getUnchecked(start());
  }

  protected Service.State standardStopAndWait()
  {
    return (Service.State)Futures.getUnchecked(stop());
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.util.concurrent.ForwardingService
 * JD-Core Version:    0.6.2
 */