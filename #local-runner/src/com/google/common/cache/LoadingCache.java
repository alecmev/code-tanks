package com.google.common.cache;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;

@Beta
@GwtCompatible
public abstract interface LoadingCache extends Function, Cache
{
  public abstract Object get(Object paramObject)
    throws ExecutionException;

  public abstract Object getUnchecked(Object paramObject);

  public abstract ImmutableMap getAll(Iterable paramIterable)
    throws ExecutionException;

  public abstract Object apply(Object paramObject);

  public abstract void refresh(Object paramObject);

  public abstract ConcurrentMap asMap();
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.cache.LoadingCache
 * JD-Core Version:    0.6.2
 */