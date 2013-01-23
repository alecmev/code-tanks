package com.google.inject.internal;

abstract interface ConstructionProxyFactory
{
  public abstract ConstructionProxy create()
    throws ErrorsException;
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.ConstructionProxyFactory
 * JD-Core Version:    0.6.2
 */