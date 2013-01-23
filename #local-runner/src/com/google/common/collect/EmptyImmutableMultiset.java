package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible(serializable=true)
final class EmptyImmutableMultiset extends ImmutableMultiset
{
  static final EmptyImmutableMultiset INSTANCE = new EmptyImmutableMultiset();
  private static final long serialVersionUID = 0L;

  public int count(Object paramObject)
  {
    return 0;
  }

  public ImmutableSet elementSet()
  {
    return ImmutableSet.of();
  }

  public int size()
  {
    return 0;
  }

  boolean isPartialView()
  {
    return false;
  }

  ImmutableSet createEntrySet()
  {
    return ImmutableSet.of();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.EmptyImmutableMultiset
 * JD-Core Version:    0.6.2
 */