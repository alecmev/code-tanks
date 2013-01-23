package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@Beta
public final class FakeTimeLimiter
  implements TimeLimiter
{
  public Object newProxy(Object paramObject, Class paramClass, long paramLong, TimeUnit paramTimeUnit)
  {
    return paramObject;
  }

  public Object callWithTimeout(Callable paramCallable, long paramLong, TimeUnit paramTimeUnit, boolean paramBoolean)
    throws Exception
  {
    return paramCallable.call();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.util.concurrent.FakeTimeLimiter
 * JD-Core Version:    0.6.2
 */