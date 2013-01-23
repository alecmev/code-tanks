package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.base.Throwables;
import java.util.concurrent.Executor;

@Beta
public abstract class AbstractIdleService
  implements Service
{
  private final Service delegate = new AbstractService()
  {
    protected final void doStart()
    {
      AbstractIdleService.this.executor(Service.State.STARTING).execute(new Runnable()
      {
        public void run()
        {
          try
          {
            AbstractIdleService.this.startUp();
            AbstractIdleService.1.this.notifyStarted();
          }
          catch (Throwable localThrowable)
          {
            AbstractIdleService.1.this.notifyFailed(localThrowable);
            throw Throwables.propagate(localThrowable);
          }
        }
      });
    }

    protected final void doStop()
    {
      AbstractIdleService.this.executor(Service.State.STOPPING).execute(new Runnable()
      {
        public void run()
        {
          try
          {
            AbstractIdleService.this.shutDown();
            AbstractIdleService.1.this.notifyStopped();
          }
          catch (Throwable localThrowable)
          {
            AbstractIdleService.1.this.notifyFailed(localThrowable);
            throw Throwables.propagate(localThrowable);
          }
        }
      });
    }
  };

  protected abstract void startUp()
    throws Exception;

  protected abstract void shutDown()
    throws Exception;

  protected Executor executor(final Service.State paramState)
  {
    return new Executor()
    {
      public void execute(Runnable paramAnonymousRunnable)
      {
        new Thread(paramAnonymousRunnable, AbstractIdleService.this.getServiceName() + " " + paramState).start();
      }
    };
  }

  public String toString()
  {
    return getServiceName() + " [" + state() + "]";
  }

  public final ListenableFuture start()
  {
    return this.delegate.start();
  }

  public final Service.State startAndWait()
  {
    return this.delegate.startAndWait();
  }

  public final boolean isRunning()
  {
    return this.delegate.isRunning();
  }

  public final Service.State state()
  {
    return this.delegate.state();
  }

  public final ListenableFuture stop()
  {
    return this.delegate.stop();
  }

  public final Service.State stopAndWait()
  {
    return this.delegate.stopAndWait();
  }

  private String getServiceName()
  {
    return getClass().getSimpleName();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.util.concurrent.AbstractIdleService
 * JD-Core Version:    0.6.2
 */