package org.apache.http.client.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CloneUtils
{
  public static Object clone(Object paramObject)
    throws CloneNotSupportedException
  {
    if (paramObject == null)
      return null;
    if ((paramObject instanceof Cloneable))
    {
      Class localClass = paramObject.getClass();
      Method localMethod;
      try
      {
        localMethod = localClass.getMethod("clone", (Class[])null);
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        throw new NoSuchMethodError(localNoSuchMethodException.getMessage());
      }
      try
      {
        return localMethod.invoke(paramObject, (Object[])null);
      }
      catch (InvocationTargetException localInvocationTargetException)
      {
        Throwable localThrowable = localInvocationTargetException.getCause();
        if ((localThrowable instanceof CloneNotSupportedException))
          throw ((CloneNotSupportedException)localThrowable);
        throw new Error("Unexpected exception", localThrowable);
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        throw new IllegalAccessError(localIllegalAccessException.getMessage());
      }
    }
    throw new CloneNotSupportedException();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.client.utils.CloneUtils
 * JD-Core Version:    0.6.2
 */