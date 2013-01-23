package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Map;
import java.util.Set;

@GwtCompatible
abstract class AbstractSetMultimap extends AbstractMultimap
  implements SetMultimap
{
  private static final long serialVersionUID = 7431625294878419160L;

  protected AbstractSetMultimap(Map paramMap)
  {
    super(paramMap);
  }

  abstract Set createCollection();

  public Set get(Object paramObject)
  {
    return (Set)super.get(paramObject);
  }

  public Set entries()
  {
    return (Set)super.entries();
  }

  public Set removeAll(Object paramObject)
  {
    return (Set)super.removeAll(paramObject);
  }

  public Set replaceValues(Object paramObject, Iterable paramIterable)
  {
    return (Set)super.replaceValues(paramObject, paramIterable);
  }

  public Map asMap()
  {
    return super.asMap();
  }

  public boolean put(Object paramObject1, Object paramObject2)
  {
    return super.put(paramObject1, paramObject2);
  }

  public boolean equals(Object paramObject)
  {
    return super.equals(paramObject);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.AbstractSetMultimap
 * JD-Core Version:    0.6.2
 */