package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.List;
import java.util.Map;

@GwtCompatible
abstract class AbstractListMultimap extends AbstractMultimap
  implements ListMultimap
{
  private static final long serialVersionUID = 6588350623831699109L;

  protected AbstractListMultimap(Map paramMap)
  {
    super(paramMap);
  }

  abstract List createCollection();

  public List get(Object paramObject)
  {
    return (List)super.get(paramObject);
  }

  public List removeAll(Object paramObject)
  {
    return (List)super.removeAll(paramObject);
  }

  public List replaceValues(Object paramObject, Iterable paramIterable)
  {
    return (List)super.replaceValues(paramObject, paramIterable);
  }

  public boolean put(Object paramObject1, Object paramObject2)
  {
    return super.put(paramObject1, paramObject2);
  }

  public Map asMap()
  {
    return super.asMap();
  }

  public boolean equals(Object paramObject)
  {
    return super.equals(paramObject);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.AbstractListMultimap
 * JD-Core Version:    0.6.2
 */