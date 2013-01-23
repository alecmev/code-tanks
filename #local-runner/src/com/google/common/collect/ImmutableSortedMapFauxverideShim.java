package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible
abstract class ImmutableSortedMapFauxverideShim extends ImmutableMap
{
  @Deprecated
  public static ImmutableSortedMap.Builder builder()
  {
    throw new UnsupportedOperationException();
  }

  @Deprecated
  public static ImmutableSortedMap of(Object paramObject1, Object paramObject2)
  {
    throw new UnsupportedOperationException();
  }

  @Deprecated
  public static ImmutableSortedMap of(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4)
  {
    throw new UnsupportedOperationException();
  }

  @Deprecated
  public static ImmutableSortedMap of(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5, Object paramObject6)
  {
    throw new UnsupportedOperationException();
  }

  @Deprecated
  public static ImmutableSortedMap of(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5, Object paramObject6, Object paramObject7, Object paramObject8)
  {
    throw new UnsupportedOperationException();
  }

  @Deprecated
  public static ImmutableSortedMap of(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5, Object paramObject6, Object paramObject7, Object paramObject8, Object paramObject9, Object paramObject10)
  {
    throw new UnsupportedOperationException();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.ImmutableSortedMapFauxverideShim
 * JD-Core Version:    0.6.2
 */