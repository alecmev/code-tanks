package com.google.inject.internal;

import com.google.inject.spi.InjectionPoint;

abstract interface SingleMemberInjector
{
  public abstract void inject(Errors paramErrors, InternalContext paramInternalContext, Object paramObject);

  public abstract InjectionPoint getInjectionPoint();
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.SingleMemberInjector
 * JD-Core Version:    0.6.2
 */