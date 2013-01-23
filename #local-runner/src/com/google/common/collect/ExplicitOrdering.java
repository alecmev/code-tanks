package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

@GwtCompatible(serializable=true)
final class ExplicitOrdering extends Ordering
  implements Serializable
{
  final ImmutableMap rankMap;
  private static final long serialVersionUID = 0L;

  ExplicitOrdering(List paramList)
  {
    this(buildRankMap(paramList));
  }

  ExplicitOrdering(ImmutableMap paramImmutableMap)
  {
    this.rankMap = paramImmutableMap;
  }

  public int compare(Object paramObject1, Object paramObject2)
  {
    return rank(paramObject1) - rank(paramObject2);
  }

  private int rank(Object paramObject)
  {
    Integer localInteger = (Integer)this.rankMap.get(paramObject);
    if (localInteger == null)
      throw new Ordering.IncomparableValueException(paramObject);
    return localInteger.intValue();
  }

  private static ImmutableMap buildRankMap(List paramList)
  {
    ImmutableMap.Builder localBuilder = ImmutableMap.builder();
    int i = 0;
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      localBuilder.put(localObject, Integer.valueOf(i++));
    }
    return localBuilder.build();
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof ExplicitOrdering))
    {
      ExplicitOrdering localExplicitOrdering = (ExplicitOrdering)paramObject;
      return this.rankMap.equals(localExplicitOrdering.rankMap);
    }
    return false;
  }

  public int hashCode()
  {
    return this.rankMap.hashCode();
  }

  public String toString()
  {
    return "Ordering.explicit(" + this.rankMap.keySet() + ")";
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.ExplicitOrdering
 * JD-Core Version:    0.6.2
 */