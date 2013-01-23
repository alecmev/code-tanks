package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Map;

@GwtCompatible(serializable=true, emulated=true)
public abstract class ImmutableBiMap extends ImmutableMap
  implements BiMap
{
  private static final ImmutableBiMap EMPTY_IMMUTABLE_BIMAP = new EmptyBiMap();

  public static ImmutableBiMap of()
  {
    return EMPTY_IMMUTABLE_BIMAP;
  }

  public static ImmutableBiMap of(Object paramObject1, Object paramObject2)
  {
    return new RegularImmutableBiMap(ImmutableMap.of(paramObject1, paramObject2));
  }

  public static ImmutableBiMap of(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4)
  {
    return new RegularImmutableBiMap(ImmutableMap.of(paramObject1, paramObject2, paramObject3, paramObject4));
  }

  public static ImmutableBiMap of(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5, Object paramObject6)
  {
    return new RegularImmutableBiMap(ImmutableMap.of(paramObject1, paramObject2, paramObject3, paramObject4, paramObject5, paramObject6));
  }

  public static ImmutableBiMap of(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5, Object paramObject6, Object paramObject7, Object paramObject8)
  {
    return new RegularImmutableBiMap(ImmutableMap.of(paramObject1, paramObject2, paramObject3, paramObject4, paramObject5, paramObject6, paramObject7, paramObject8));
  }

  public static ImmutableBiMap of(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5, Object paramObject6, Object paramObject7, Object paramObject8, Object paramObject9, Object paramObject10)
  {
    return new RegularImmutableBiMap(ImmutableMap.of(paramObject1, paramObject2, paramObject3, paramObject4, paramObject5, paramObject6, paramObject7, paramObject8, paramObject9, paramObject10));
  }

  public static Builder builder()
  {
    return new Builder();
  }

  public static ImmutableBiMap copyOf(Map paramMap)
  {
    if ((paramMap instanceof ImmutableBiMap))
    {
      localObject = (ImmutableBiMap)paramMap;
      if (!((ImmutableBiMap)localObject).isPartialView())
        return localObject;
    }
    if (paramMap.isEmpty())
      return of();
    Object localObject = ImmutableMap.copyOf(paramMap);
    return new RegularImmutableBiMap((ImmutableMap)localObject);
  }

  abstract ImmutableMap delegate();

  public abstract ImmutableBiMap inverse();

  public boolean containsKey(Object paramObject)
  {
    return delegate().containsKey(paramObject);
  }

  public boolean containsValue(Object paramObject)
  {
    return inverse().containsKey(paramObject);
  }

  ImmutableSet createEntrySet()
  {
    return delegate().entrySet();
  }

  public Object get(Object paramObject)
  {
    return delegate().get(paramObject);
  }

  public ImmutableSet keySet()
  {
    return delegate().keySet();
  }

  public ImmutableSet values()
  {
    return inverse().keySet();
  }

  public Object forcePut(Object paramObject1, Object paramObject2)
  {
    throw new UnsupportedOperationException();
  }

  public boolean isEmpty()
  {
    return delegate().isEmpty();
  }

  public int size()
  {
    return delegate().size();
  }

  public boolean equals(Object paramObject)
  {
    return (paramObject == this) || (delegate().equals(paramObject));
  }

  public int hashCode()
  {
    return delegate().hashCode();
  }

  public String toString()
  {
    return delegate().toString();
  }

  Object writeReplace()
  {
    return new SerializedForm(this);
  }

  private static class SerializedForm extends ImmutableMap.SerializedForm
  {
    private static final long serialVersionUID = 0L;

    SerializedForm(ImmutableBiMap paramImmutableBiMap)
    {
      super();
    }

    Object readResolve()
    {
      ImmutableBiMap.Builder localBuilder = new ImmutableBiMap.Builder();
      return createMap(localBuilder);
    }
  }

  static class EmptyBiMap extends ImmutableBiMap
  {
    ImmutableMap delegate()
    {
      return ImmutableMap.of();
    }

    public ImmutableBiMap inverse()
    {
      return this;
    }

    boolean isPartialView()
    {
      return false;
    }

    Object readResolve()
    {
      return ImmutableBiMap.EMPTY_IMMUTABLE_BIMAP;
    }
  }

  public static final class Builder extends ImmutableMap.Builder
  {
    public Builder put(Object paramObject1, Object paramObject2)
    {
      super.put(paramObject1, paramObject2);
      return this;
    }

    public Builder putAll(Map paramMap)
    {
      super.putAll(paramMap);
      return this;
    }

    public ImmutableBiMap build()
    {
      ImmutableMap localImmutableMap = super.build();
      if (localImmutableMap.isEmpty())
        return ImmutableBiMap.of();
      return new RegularImmutableBiMap(localImmutableMap);
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.ImmutableBiMap
 * JD-Core Version:    0.6.2
 */