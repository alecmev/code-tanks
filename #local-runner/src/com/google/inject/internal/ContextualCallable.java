package com.google.inject.internal;

abstract interface ContextualCallable
{
  public abstract Object call(InternalContext paramInternalContext)
    throws ErrorsException;
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.ContextualCallable
 * JD-Core Version:    0.6.2
 */