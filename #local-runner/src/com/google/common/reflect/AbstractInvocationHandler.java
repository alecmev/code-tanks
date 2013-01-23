package com.google.common.reflect;

import com.google.common.annotations.Beta;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Beta
public abstract class AbstractInvocationHandler
  implements InvocationHandler
{
  private static final Object[] NO_ARGS = new Object[0];

  public final Object invoke(Object paramObject, Method paramMethod, Object[] paramArrayOfObject)
    throws Throwable
  {
    if (paramArrayOfObject == null)
      paramArrayOfObject = NO_ARGS;
    if ((paramArrayOfObject.length == 0) && (paramMethod.getName().equals("hashCode")))
      return Integer.valueOf(System.identityHashCode(paramObject));
    if ((paramArrayOfObject.length == 1) && (paramMethod.getName().equals("equals")) && (paramMethod.getParameterTypes()[0] == Object.class))
      return Boolean.valueOf(paramObject == paramArrayOfObject[0]);
    if ((paramArrayOfObject.length == 0) && (paramMethod.getName().equals("toString")))
      return toString();
    return handleInvocation(paramObject, paramMethod, paramArrayOfObject);
  }

  protected abstract Object handleInvocation(Object paramObject, Method paramMethod, Object[] paramArrayOfObject)
    throws Throwable;

  public String toString()
  {
    return super.toString();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.reflect.AbstractInvocationHandler
 * JD-Core Version:    0.6.2
 */