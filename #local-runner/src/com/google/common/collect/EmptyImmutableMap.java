package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Map;

@GwtCompatible(serializable=true, emulated=true)
final class EmptyImmutableMap extends ImmutableMap
{
  static final EmptyImmutableMap INSTANCE = new EmptyImmutableMap();
  private static final long serialVersionUID = 0L;

  public Object get(Object paramObject)
  {
    return null;
  }

  public int size()
  {
    return 0;
  }

  public boolean isEmpty()
  {
    return true;
  }

  public boolean containsKey(Object paramObject)
  {
    return false;
  }

  public boolean containsValue(Object paramObject)
  {
    return false;
  }

  ImmutableSet createEntrySet()
  {
    throw new AssertionError("should never be called");
  }

  public ImmutableSet entrySet()
  {
    return ImmutableSet.of();
  }

  public ImmutableSet keySet()
  {
    return ImmutableSet.of();
  }

  public ImmutableCollection values()
  {
    return ImmutableCollection.EMPTY_IMMUTABLE_COLLECTION;
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof Map))
    {
      Map localMap = (Map)paramObject;
      return localMap.isEmpty();
    }
    return false;
  }

  boolean isPartialView()
  {
    return false;
  }

  public int hashCode()
  {
    return 0;
  }

  public String toString()
  {
    return "{}";
  }

  Object readResolve()
  {
    return INSTANCE;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.EmptyImmutableMap
 * JD-Core Version:    0.6.2
 */