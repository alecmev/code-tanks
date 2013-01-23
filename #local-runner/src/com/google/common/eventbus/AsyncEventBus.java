package com.google.common.eventbus;

import com.google.common.annotations.Beta;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;

@Beta
public class AsyncEventBus extends EventBus
{
  private final Executor executor;
  private final ConcurrentLinkedQueue eventsToDispatch = new ConcurrentLinkedQueue();

  public AsyncEventBus(String paramString, Executor paramExecutor)
  {
    super(paramString);
    this.executor = paramExecutor;
  }

  public AsyncEventBus(Executor paramExecutor)
  {
    this.executor = paramExecutor;
  }

  protected void enqueueEvent(Object paramObject, EventHandler paramEventHandler)
  {
    this.eventsToDispatch.offer(new EventBus.EventWithHandler(paramObject, paramEventHandler));
  }

  protected void dispatchQueuedEvents()
  {
    while (true)
    {
      EventBus.EventWithHandler localEventWithHandler = (EventBus.EventWithHandler)this.eventsToDispatch.poll();
      if (localEventWithHandler == null)
        break;
      dispatch(localEventWithHandler.event, localEventWithHandler.handler);
    }
  }

  protected void dispatch(final Object paramObject, final EventHandler paramEventHandler)
  {
    this.executor.execute(new Runnable()
    {
      public void run()
      {
        AsyncEventBus.this.dispatch(paramObject, paramEventHandler);
      }
    });
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.eventbus.AsyncEventBus
 * JD-Core Version:    0.6.2
 */