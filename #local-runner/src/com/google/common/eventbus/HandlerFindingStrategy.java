package com.google.common.eventbus;

import com.google.common.collect.Multimap;

abstract interface HandlerFindingStrategy
{
  public abstract Multimap findAllHandlers(Object paramObject);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.eventbus.HandlerFindingStrategy
 * JD-Core Version:    0.6.2
 */