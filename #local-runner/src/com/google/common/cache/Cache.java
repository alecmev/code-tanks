package com.google.common.cache;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;

@Beta
@GwtCompatible
public abstract interface Cache
{
  public abstract Object getIfPresent(Object paramObject);

  public abstract Object get(Object paramObject, Callable paramCallable)
    throws ExecutionException;

  public abstract ImmutableMap getAllPresent(Iterable paramIterable);

  public abstract void put(Object paramObject1, Object paramObject2);

  public abstract void putAll(Map paramMap);

  public abstract void invalidate(Object paramObject);

  public abstract void invalidateAll(Iterable paramIterable);

  public abstract void invalidateAll();

  public abstract long size();

  public abstract CacheStats stats();

  public abstract ConcurrentMap asMap();

  public abstract void cleanUp();
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.cache.Cache
 * JD-Core Version:    0.6.2
 */