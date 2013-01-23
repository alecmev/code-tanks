package com.google.inject.internal;

import java.lang.reflect.InvocationTargetException;

class Exceptions
{
  public static RuntimeException throwCleanly(InvocationTargetException paramInvocationTargetException)
  {
    Object localObject = paramInvocationTargetException;
    if (((Throwable)localObject).getCause() != null)
      localObject = ((Throwable)localObject).getCause();
    if ((localObject instanceof RuntimeException))
      throw ((RuntimeException)localObject);
    if ((localObject instanceof Error))
      throw ((Error)localObject);
    throw new UnhandledCheckedUserException((Throwable)localObject);
  }

  static class UnhandledCheckedUserException extends RuntimeException
  {
    public UnhandledCheckedUserException(Throwable paramThrowable)
    {
      super();
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.Exceptions
 * JD-Core Version:    0.6.2
 */