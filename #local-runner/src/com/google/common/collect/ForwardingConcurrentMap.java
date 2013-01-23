package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.concurrent.ConcurrentMap;

@GwtCompatible
public abstract class ForwardingConcurrentMap extends ForwardingMap
  implements ConcurrentMap
{
  protected abstract ConcurrentMap delegate();

  public Object putIfAbsent(Object paramObject1, Object paramObject2)
  {
    return delegate().putIfAbsent(paramObject1, paramObject2);
  }

  public boolean remove(Object paramObject1, Object paramObject2)
  {
    return delegate().remove(paramObject1, paramObject2);
  }

  public Object replace(Object paramObject1, Object paramObject2)
  {
    return delegate().replace(paramObject1, paramObject2);
  }

  public boolean replace(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    return delegate().replace(paramObject1, paramObject2, paramObject3);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.ForwardingConcurrentMap
 * JD-Core Version:    0.6.2
 */