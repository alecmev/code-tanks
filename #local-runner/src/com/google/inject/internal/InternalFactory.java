package com.google.inject.internal;

import com.google.inject.spi.Dependency;

abstract interface InternalFactory
{
  public abstract Object get(Errors paramErrors, InternalContext paramInternalContext, Dependency paramDependency, boolean paramBoolean)
    throws ErrorsException;
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.InternalFactory
 * JD-Core Version:    0.6.2
 */