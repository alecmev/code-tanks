package com.google.common.cache;

import com.google.common.annotations.Beta;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.UncheckedExecutionException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Beta
public abstract class AbstractLoadingCache extends AbstractCache
  implements LoadingCache
{
  public Object getUnchecked(Object paramObject)
  {
    try
    {
      return get(paramObject);
    }
    catch (ExecutionException localExecutionException)
    {
      throw new UncheckedExecutionException(localExecutionException.getCause());
    }
  }

  public ImmutableMap getAll(Iterable paramIterable)
    throws ExecutionException
  {
    LinkedHashMap localLinkedHashMap = Maps.newLinkedHashMap();
    Iterator localIterator = paramIterable.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      if (!localLinkedHashMap.containsKey(localObject))
        localLinkedHashMap.put(localObject, get(localObject));
    }
    return ImmutableMap.copyOf(localLinkedHashMap);
  }

  public final Object apply(Object paramObject)
  {
    return getUnchecked(paramObject);
  }

  public void refresh(Object paramObject)
  {
    throw new UnsupportedOperationException();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.cache.AbstractLoadingCache
 * JD-Core Version:    0.6.2
 */