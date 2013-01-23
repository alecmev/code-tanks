package com.google.common.util.concurrent;

public class UncheckedTimeoutException extends RuntimeException
{
  private static final long serialVersionUID = 0L;

  public UncheckedTimeoutException()
  {
  }

  public UncheckedTimeoutException(String paramString)
  {
    super(paramString);
  }

  public UncheckedTimeoutException(Throwable paramThrowable)
  {
    super(paramThrowable);
  }

  public UncheckedTimeoutException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.util.concurrent.UncheckedTimeoutException
 * JD-Core Version:    0.6.2
 */