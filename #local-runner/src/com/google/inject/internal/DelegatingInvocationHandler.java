package com.google.inject.internal;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class DelegatingInvocationHandler
  implements InvocationHandler
{
  private Object delegate;

  public Object invoke(Object paramObject, Method paramMethod, Object[] paramArrayOfObject)
    throws Throwable
  {
    if (this.delegate == null)
      throw new IllegalStateException("This is a proxy used to support circular references involving constructors. The object we're proxying is not constructed yet. Please wait until after injection has completed to use this object.");
    try
    {
      return paramMethod.invoke(this.delegate, paramArrayOfObject);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new RuntimeException(localIllegalAccessException);
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      throw new RuntimeException(localIllegalArgumentException);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      throw localInvocationTargetException.getTargetException();
    }
  }

  public Object getDelegate()
  {
    return this.delegate;
  }

  void setDelegate(Object paramObject)
  {
    this.delegate = paramObject;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.DelegatingInvocationHandler
 * JD-Core Version:    0.6.2
 */