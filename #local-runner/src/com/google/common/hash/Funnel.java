package com.google.common.hash;

import com.google.common.annotations.Beta;
import java.io.Serializable;

@Beta
public abstract interface Funnel extends Serializable
{
  public abstract void funnel(Object paramObject, PrimitiveSink paramPrimitiveSink);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.hash.Funnel
 * JD-Core Version:    0.6.2
 */