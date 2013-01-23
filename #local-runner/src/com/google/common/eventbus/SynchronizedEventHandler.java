package com.google.common.eventbus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class SynchronizedEventHandler extends EventHandler
{
  public SynchronizedEventHandler(Object paramObject, Method paramMethod)
  {
    super(paramObject, paramMethod);
  }

  public synchronized void handleEvent(Object paramObject)
    throws InvocationTargetException
  {
    super.handleEvent(paramObject);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.eventbus.SynchronizedEventHandler
 * JD-Core Version:    0.6.2
 */