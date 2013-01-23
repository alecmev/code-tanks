package com.google.gson.stream;

import java.io.IOException;

public final class MalformedJsonException extends IOException
{
  private static final long serialVersionUID = 1L;

  public MalformedJsonException(String paramString)
  {
    super(paramString);
  }

  public MalformedJsonException(String paramString, Throwable paramThrowable)
  {
    super(paramString);
    initCause(paramThrowable);
  }

  public MalformedJsonException(Throwable paramThrowable)
  {
    initCause(paramThrowable);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.gson.stream.MalformedJsonException
 * JD-Core Version:    0.6.2
 */