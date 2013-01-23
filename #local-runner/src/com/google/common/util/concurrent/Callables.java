package com.google.common.util.concurrent;

import java.util.concurrent.Callable;

public final class Callables
{
  public static Callable returning(Object paramObject)
  {
    return new Callable()
    {
      public Object call()
      {
        return this.val$value;
      }
    };
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.util.concurrent.Callables
 * JD-Core Version:    0.6.2
 */