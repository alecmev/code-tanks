package com.google.inject.internal;

import com.google.inject.spi.Message;

abstract interface ErrorHandler
{
  public abstract void handle(Object paramObject, Errors paramErrors);

  public abstract void handle(Message paramMessage);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.ErrorHandler
 * JD-Core Version:    0.6.2
 */