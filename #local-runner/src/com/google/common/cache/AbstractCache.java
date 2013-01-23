package com.google.common.cache;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicLong;

@Beta
@GwtCompatible
public abstract class AbstractCache
  implements Cache
{
  public Object get(Object paramObject, Callable paramCallable)
    throws ExecutionException
  {
    throw new UnsupportedOperationException();
  }

  public ImmutableMap getAllPresent(Iterable paramIterable)
  {
    LinkedHashMap localLinkedHashMap = Maps.newLinkedHashMap();
    Iterator localIterator = paramIterable.iterator();
    while (localIterator.hasNext())
    {
      Object localObject1 = localIterator.next();
      if (!localLinkedHashMap.containsKey(localObject1))
      {
        Object localObject2 = localObject1;
        localLinkedHashMap.put(localObject2, getIfPresent(localObject1));
      }
    }
    return ImmutableMap.copyOf(localLinkedHashMap);
  }

  public void put(Object paramObject1, Object paramObject2)
  {
    throw new UnsupportedOperationException();
  }

  public void putAll(Map paramMap)
  {
    Iterator localIterator = paramMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      put(localEntry.getKey(), localEntry.getValue());
    }
  }

  public void cleanUp()
  {
  }

  public long size()
  {
    throw new UnsupportedOperationException();
  }

  public void invalidate(Object paramObject)
  {
    throw new UnsupportedOperationException();
  }

  public void invalidateAll(Iterable paramIterable)
  {
    Iterator localIterator = paramIterable.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      invalidate(localObject);
    }
  }

  public void invalidateAll()
  {
    throw new UnsupportedOperationException();
  }

  public CacheStats stats()
  {
    throw new UnsupportedOperationException();
  }

  public ConcurrentMap asMap()
  {
    throw new UnsupportedOperationException();
  }

  @Beta
  public static class SimpleStatsCounter
    implements AbstractCache.StatsCounter
  {
    private final AtomicLong hitCount = new AtomicLong();
    private final AtomicLong missCount = new AtomicLong();
    private final AtomicLong loadSuccessCount = new AtomicLong();
    private final AtomicLong loadExceptionCount = new AtomicLong();
    private final AtomicLong totalLoadTime = new AtomicLong();
    private final AtomicLong evictionCount = new AtomicLong();

    public void recordHits(int paramInt)
    {
      this.hitCount.addAndGet(paramInt);
    }

    public void recordMisses(int paramInt)
    {
      this.missCount.addAndGet(paramInt);
    }

    public void recordLoadSuccess(long paramLong)
    {
      this.loadSuccessCount.incrementAndGet();
      this.totalLoadTime.addAndGet(paramLong);
    }

    public void recordLoadException(long paramLong)
    {
      this.loadExceptionCount.incrementAndGet();
      this.totalLoadTime.addAndGet(paramLong);
    }

    public void recordEviction()
    {
      this.evictionCount.incrementAndGet();
    }

    public CacheStats snapshot()
    {
      return new CacheStats(this.hitCount.get(), this.missCount.get(), this.loadSuccessCount.get(), this.loadExceptionCount.get(), this.totalLoadTime.get(), this.evictionCount.get());
    }

    public void incrementBy(AbstractCache.StatsCounter paramStatsCounter)
    {
      CacheStats localCacheStats = paramStatsCounter.snapshot();
      this.hitCount.addAndGet(localCacheStats.hitCount());
      this.missCount.addAndGet(localCacheStats.missCount());
      this.loadSuccessCount.addAndGet(localCacheStats.loadSuccessCount());
      this.loadExceptionCount.addAndGet(localCacheStats.loadExceptionCount());
      this.totalLoadTime.addAndGet(localCacheStats.totalLoadTime());
      this.evictionCount.addAndGet(localCacheStats.evictionCount());
    }
  }

  @Beta
  public static abstract interface StatsCounter
  {
    public abstract void recordHits(int paramInt);

    public abstract void recordMisses(int paramInt);

    public abstract void recordLoadSuccess(long paramLong);

    public abstract void recordLoadException(long paramLong);

    public abstract void recordEviction();

    public abstract CacheStats snapshot();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.cache.AbstractCache
 * JD-Core Version:    0.6.2
 */