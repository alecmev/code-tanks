package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible
final class Hashing
{
  static int smear(int paramInt)
  {
    paramInt ^= paramInt >>> 20 ^ paramInt >>> 12;
    return paramInt ^ paramInt >>> 7 ^ paramInt >>> 4;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.Hashing
 * JD-Core Version:    0.6.2
 */