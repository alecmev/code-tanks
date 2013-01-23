package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.ReentrantLock;

@Beta
public abstract class AbstractService
  implements Service
{
  private final ReentrantLock lock = new ReentrantLock();
  private final Transition startup = new Transition(null);
  private final Transition shutdown = new Transition(null);
  private Service.State state = Service.State.NEW;
  private boolean shutdownWhenStartupFinishes = false;

  protected abstract void doStart();

  protected abstract void doStop();

  public final ListenableFuture start()
  {
    this.lock.lock();
    try
    {
      if (this.state == Service.State.NEW)
      {
        this.state = Service.State.STARTING;
        doStart();
      }
    }
    catch (Throwable localThrowable)
    {
      notifyFailed(localThrowable);
    }
    finally
    {
      this.lock.unlock();
    }
    return this.startup;
  }

  public final ListenableFuture stop()
  {
    this.lock.lock();
    try
    {
      if (this.state == Service.State.NEW)
      {
        this.state = Service.State.TERMINATED;
        this.startup.set(Service.State.TERMINATED);
        this.shutdown.set(Service.State.TERMINATED);
      }
      else if (this.state == Service.State.STARTING)
      {
        this.shutdownWhenStartupFinishes = true;
        this.startup.set(Service.State.STOPPING);
      }
      else if (this.state == Service.State.RUNNING)
      {
        this.state = Service.State.STOPPING;
        doStop();
      }
    }
    catch (Throwable localThrowable)
    {
      notifyFailed(localThrowable);
    }
    finally
    {
      this.lock.unlock();
    }
    return this.shutdown;
  }

  public Service.State startAndWait()
  {
    return (Service.State)Futures.getUnchecked(start());
  }

  public Service.State stopAndWait()
  {
    return (Service.State)Futures.getUnchecked(stop());
  }

  protected final void notifyStarted()
  {
    this.lock.lock();
    try
    {
      if (this.state != Service.State.STARTING)
      {
        IllegalStateException localIllegalStateException = new IllegalStateException("Cannot notifyStarted() when the service is " + this.state);
        notifyFailed(localIllegalStateException);
        throw localIllegalStateException;
      }
      this.state = Service.State.RUNNING;
      if (this.shutdownWhenStartupFinishes)
        stop();
      else
        this.startup.set(Service.State.RUNNING);
    }
    finally
    {
      this.lock.unlock();
    }
  }

  protected final void notifyStopped()
  {
    this.lock.lock();
    try
    {
      if ((this.state != Service.State.STOPPING) && (this.state != Service.State.RUNNING))
      {
        IllegalStateException localIllegalStateException = new IllegalStateException("Cannot notifyStopped() when the service is " + this.state);
        notifyFailed(localIllegalStateException);
        throw localIllegalStateException;
      }
      this.state = Service.State.TERMINATED;
      this.shutdown.set(Service.State.TERMINATED);
    }
    finally
    {
      this.lock.unlock();
    }
  }

  protected final void notifyFailed(Throwable paramThrowable)
  {
    Preconditions.checkNotNull(paramThrowable);
    this.lock.lock();
    try
    {
      if (this.state == Service.State.STARTING)
      {
        this.startup.setException(paramThrowable);
        this.shutdown.setException(new Exception("Service failed to start.", paramThrowable));
      }
      else if (this.state == Service.State.STOPPING)
      {
        this.shutdown.setException(paramThrowable);
      }
      else if (this.state == Service.State.RUNNING)
      {
        this.shutdown.setException(new Exception("Service failed while running", paramThrowable));
      }
      else if ((this.state == Service.State.NEW) || (this.state == Service.State.TERMINATED))
      {
        throw new IllegalStateException("Failed while in state:" + this.state, paramThrowable);
      }
      this.state = Service.State.FAILED;
    }
    finally
    {
      this.lock.unlock();
    }
  }

  public final boolean isRunning()
  {
    return state() == Service.State.RUNNING;
  }

  public final Service.State state()
  {
    this.lock.lock();
    try
    {
      if ((this.shutdownWhenStartupFinishes) && (this.state == Service.State.STARTING))
      {
        localState = Service.State.STOPPING;
        return localState;
      }
      Service.State localState = this.state;
      return localState;
    }
    finally
    {
      this.lock.unlock();
    }
  }

  public String toString()
  {
    return getClass().getSimpleName() + " [" + state() + "]";
  }

  private class Transition extends AbstractFuture
  {
    private Transition()
    {
    }

    public Service.State get(long paramLong, TimeUnit paramTimeUnit)
      throws InterruptedException, TimeoutException, ExecutionException
    {
      try
      {
        return (Service.State)super.get(paramLong, paramTimeUnit);
      }
      catch (TimeoutException localTimeoutException)
      {
      }
      throw new TimeoutException(AbstractService.this.toString());
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.util.concurrent.AbstractService
 * JD-Core Version:    0.6.2
 */