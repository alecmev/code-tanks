package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible
abstract interface BstModifier
{
  public abstract BstModificationResult modify(Object paramObject, BstNode paramBstNode);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.BstModifier
 * JD-Core Version:    0.6.2
 */