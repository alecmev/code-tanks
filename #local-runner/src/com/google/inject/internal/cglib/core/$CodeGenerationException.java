package com.google.inject.internal.cglib.core;

public class $CodeGenerationException extends RuntimeException
{
  private Throwable cause;

  public $CodeGenerationException(Throwable paramThrowable)
  {
    super(paramThrowable.getClass().getName() + "-->" + paramThrowable.getMessage());
    this.cause = paramThrowable;
  }

  public Throwable getCause()
  {
    return this.cause;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.cglib.core..CodeGenerationException
 * JD-Core Version:    0.6.2
 */