package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.common.math.IntMath;
import java.math.RoundingMode;
import java.util.Arrays;

 enum BloomFilterStrategies
  implements BloomFilter.Strategy
{
  MURMUR128_MITZ_32;

  static class BitArray
  {
    final long[] data;

    BitArray(int paramInt)
    {
      this(new long[IntMath.divide(paramInt, 64, RoundingMode.CEILING)]);
    }

    BitArray(long[] paramArrayOfLong)
    {
      Preconditions.checkArgument(paramArrayOfLong.length > 0, "data length is zero!");
      this.data = paramArrayOfLong;
    }

    boolean set(int paramInt)
    {
      boolean bool = get(paramInt);
      this.data[(paramInt >> 6)] |= 1L << paramInt;
      return !bool;
    }

    boolean get(int paramInt)
    {
      return (this.data[(paramInt >> 6)] & 1L << paramInt) != 0L;
    }

    int size()
    {
      return this.data.length * 64;
    }

    BitArray copy()
    {
      return new BitArray((long[])this.data.clone());
    }

    public boolean equals(Object paramObject)
    {
      if ((paramObject instanceof BitArray))
      {
        BitArray localBitArray = (BitArray)paramObject;
        return Arrays.equals(this.data, localBitArray.data);
      }
      return false;
    }

    public int hashCode()
    {
      return Arrays.hashCode(this.data);
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.hash.BloomFilterStrategies
 * JD-Core Version:    0.6.2
 */