package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.List;

@GwtCompatible
public abstract class ForwardingListMultimap extends ForwardingMultimap
  implements ListMultimap
{
  protected abstract ListMultimap delegate();

  public List get(Object paramObject)
  {
    return delegate().get(paramObject);
  }

  public List removeAll(Object paramObject)
  {
    return delegate().removeAll(paramObject);
  }

  public List replaceValues(Object paramObject, Iterable paramIterable)
  {
    return delegate().replaceValues(paramObject, paramIterable);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.ForwardingListMultimap
 * JD-Core Version:    0.6.2
 */