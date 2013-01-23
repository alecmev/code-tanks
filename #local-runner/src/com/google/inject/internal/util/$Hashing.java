package com.google.inject.internal.util;

final class $Hashing
{
  private static final int MAX_TABLE_SIZE = 1073741824;
  private static final int CUTOFF = 536870912;

  static int smear(int paramInt)
  {
    paramInt ^= paramInt >>> 20 ^ paramInt >>> 12;
    return paramInt ^ paramInt >>> 7 ^ paramInt >>> 4;
  }

  static int chooseTableSize(int paramInt)
  {
    if (paramInt < 536870912)
      return Integer.highestOneBit(paramInt) << 2;
    $Preconditions.checkArgument(paramInt < 1073741824, "collection too large");
    return 1073741824;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.util..Hashing
 * JD-Core Version:    0.6.2
 */