package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@Beta
public abstract interface TimeLimiter
{
  public abstract Object newProxy(Object paramObject, Class paramClass, long paramLong, TimeUnit paramTimeUnit);

  public abstract Object callWithTimeout(Callable paramCallable, long paramLong, TimeUnit paramTimeUnit, boolean paramBoolean)
    throws Exception;
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.util.concurrent.TimeLimiter
 * JD-Core Version:    0.6.2
 */