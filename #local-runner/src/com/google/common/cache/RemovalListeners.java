package com.google.common.cache;

import com.google.common.annotations.Beta;
import java.util.concurrent.Executor;

@Beta
public final class RemovalListeners
{
  public static RemovalListener asynchronous(final RemovalListener paramRemovalListener, Executor paramExecutor)
  {
    return new RemovalListener()
    {
      public void onRemoval(final RemovalNotification paramAnonymousRemovalNotification)
      {
        this.val$executor.execute(new Runnable()
        {
          public void run()
          {
            RemovalListeners.1.this.val$listener.onRemoval(paramAnonymousRemovalNotification);
          }
        });
      }
    };
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.cache.RemovalListeners
 * JD-Core Version:    0.6.2
 */