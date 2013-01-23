package com.google.common.util.concurrent;

public final class SettableFuture extends AbstractFuture
{
  public static SettableFuture create()
  {
    return new SettableFuture();
  }

  public boolean set(Object paramObject)
  {
    return super.set(paramObject);
  }

  public boolean setException(Throwable paramThrowable)
  {
    return super.setException(paramThrowable);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.util.concurrent.SettableFuture
 * JD-Core Version:    0.6.2
 */