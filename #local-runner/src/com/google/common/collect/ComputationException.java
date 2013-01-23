package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible
public class ComputationException extends RuntimeException
{
  private static final long serialVersionUID = 0L;

  public ComputationException(Throwable paramThrowable)
  {
    super(paramThrowable);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.ComputationException
 * JD-Core Version:    0.6.2
 */