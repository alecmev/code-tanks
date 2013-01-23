package com.google.common.eventbus;

import com.google.common.base.Preconditions;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class EventHandler
{
  private final Object target;
  private final Method method;

  EventHandler(Object paramObject, Method paramMethod)
  {
    Preconditions.checkNotNull(paramObject, "EventHandler target cannot be null.");
    Preconditions.checkNotNull(paramMethod, "EventHandler method cannot be null.");
    this.target = paramObject;
    this.method = paramMethod;
    paramMethod.setAccessible(true);
  }

  public void handleEvent(Object paramObject)
    throws InvocationTargetException
  {
    try
    {
      this.method.invoke(this.target, new Object[] { paramObject });
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      throw new Error("Method rejected target/argument: " + paramObject, localIllegalArgumentException);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new Error("Method became inaccessible: " + paramObject, localIllegalAccessException);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      if ((localInvocationTargetException.getCause() instanceof Error))
        throw ((Error)localInvocationTargetException.getCause());
      throw localInvocationTargetException;
    }
  }

  public String toString()
  {
    return "[wrapper " + this.method + "]";
  }

  public int hashCode()
  {
    int i = 31;
    return (31 + this.method.hashCode()) * 31 + this.target.hashCode();
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject)
      return true;
    if (paramObject == null)
      return false;
    if (getClass() != paramObject.getClass())
      return false;
    EventHandler localEventHandler = (EventHandler)paramObject;
    return (this.method.equals(localEventHandler.method)) && (this.target == localEventHandler.target);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.eventbus.EventHandler
 * JD-Core Version:    0.6.2
 */