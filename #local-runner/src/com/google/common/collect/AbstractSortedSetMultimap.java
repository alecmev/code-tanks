package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Collection;
import java.util.Map;
import java.util.SortedSet;

@GwtCompatible
abstract class AbstractSortedSetMultimap extends AbstractSetMultimap
  implements SortedSetMultimap
{
  private static final long serialVersionUID = 430848587173315748L;

  protected AbstractSortedSetMultimap(Map paramMap)
  {
    super(paramMap);
  }

  abstract SortedSet createCollection();

  public SortedSet get(Object paramObject)
  {
    return (SortedSet)super.get(paramObject);
  }

  public SortedSet removeAll(Object paramObject)
  {
    return (SortedSet)super.removeAll(paramObject);
  }

  public SortedSet replaceValues(Object paramObject, Iterable paramIterable)
  {
    return (SortedSet)super.replaceValues(paramObject, paramIterable);
  }

  public Map asMap()
  {
    return super.asMap();
  }

  public Collection values()
  {
    return super.values();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.AbstractSortedSetMultimap
 * JD-Core Version:    0.6.2
 */