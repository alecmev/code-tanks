package com.google.common.reflect;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

@Beta
public final class Reflection
{
  public static String getPackageName(Class paramClass)
  {
    return getPackageName(paramClass.getName());
  }

  public static String getPackageName(String paramString)
  {
    int i = paramString.lastIndexOf('.');
    if (i < 0)
      return "";
    return paramString.substring(0, i);
  }

  public static void initialize(Class[] paramArrayOfClass)
  {
    for (Class localClass : paramArrayOfClass)
      try
      {
        Class.forName(localClass.getName(), true, localClass.getClassLoader());
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        throw new AssertionError(localClassNotFoundException);
      }
  }

  public static Object newProxy(Class paramClass, InvocationHandler paramInvocationHandler)
  {
    Preconditions.checkNotNull(paramClass);
    Preconditions.checkNotNull(paramInvocationHandler);
    Preconditions.checkArgument(paramClass.isInterface());
    Object localObject = Proxy.newProxyInstance(paramClass.getClassLoader(), new Class[] { paramClass }, paramInvocationHandler);
    return paramClass.cast(localObject);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.reflect.Reflection
 * JD-Core Version:    0.6.2
 */