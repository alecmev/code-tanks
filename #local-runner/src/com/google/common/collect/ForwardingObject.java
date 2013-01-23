package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible
public abstract class ForwardingObject
{
  protected abstract Object delegate();

  public String toString()
  {
    return delegate().toString();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.ForwardingObject
 * JD-Core Version:    0.6.2
 */