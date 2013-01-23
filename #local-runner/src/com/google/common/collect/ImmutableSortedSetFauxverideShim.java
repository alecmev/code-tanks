package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible
abstract class ImmutableSortedSetFauxverideShim extends ImmutableSet
{
  @Deprecated
  public static ImmutableSortedSet.Builder builder()
  {
    throw new UnsupportedOperationException();
  }

  @Deprecated
  public static ImmutableSortedSet of(Object paramObject)
  {
    throw new UnsupportedOperationException();
  }

  @Deprecated
  public static ImmutableSortedSet of(Object paramObject1, Object paramObject2)
  {
    throw new UnsupportedOperationException();
  }

  @Deprecated
  public static ImmutableSortedSet of(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    throw new UnsupportedOperationException();
  }

  @Deprecated
  public static ImmutableSortedSet of(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4)
  {
    throw new UnsupportedOperationException();
  }

  @Deprecated
  public static ImmutableSortedSet of(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5)
  {
    throw new UnsupportedOperationException();
  }

  @Deprecated
  public static ImmutableSortedSet of(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5, Object paramObject6, Object[] paramArrayOfObject)
  {
    throw new UnsupportedOperationException();
  }

  @Deprecated
  public static ImmutableSortedSet copyOf(Object[] paramArrayOfObject)
  {
    throw new UnsupportedOperationException();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.ImmutableSortedSetFauxverideShim
 * JD-Core Version:    0.6.2
 */