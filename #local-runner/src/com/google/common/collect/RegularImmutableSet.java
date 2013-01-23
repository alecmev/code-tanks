package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;

@GwtCompatible(serializable=true, emulated=true)
final class RegularImmutableSet extends ImmutableSet.ArrayImmutableSet
{

  @VisibleForTesting
  final transient Object[] table;
  private final transient int mask;
  private final transient int hashCode;

  RegularImmutableSet(Object[] paramArrayOfObject1, int paramInt1, Object[] paramArrayOfObject2, int paramInt2)
  {
    super(paramArrayOfObject1);
    this.table = paramArrayOfObject2;
    this.mask = paramInt2;
    this.hashCode = paramInt1;
  }

  public boolean contains(Object paramObject)
  {
    if (paramObject == null)
      return false;
    for (int i = Hashing.smear(paramObject.hashCode()); ; i++)
    {
      Object localObject = this.table[(i & this.mask)];
      if (localObject == null)
        return false;
      if (localObject.equals(paramObject))
        return true;
    }
  }

  public int hashCode()
  {
    return this.hashCode;
  }

  boolean isHashCodeFast()
  {
    return true;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.RegularImmutableSet
 * JD-Core Version:    0.6.2
 */