package com.google.common.hash;

import com.google.common.annotations.Beta;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.io.Serializable;

@Beta
public final class BloomFilter
  implements Serializable
{
  private final BloomFilterStrategies.BitArray bits;
  private final int numHashFunctions;
  private final Funnel funnel;
  private final Strategy strategy;
  private static final double LN2 = Math.log(2.0D);
  private static final double LN2_SQUARED = LN2 * LN2;

  private BloomFilter(BloomFilterStrategies.BitArray paramBitArray, int paramInt, Funnel paramFunnel, Strategy paramStrategy)
  {
    Preconditions.checkArgument(paramInt > 0, "numHashFunctions zero or negative");
    this.bits = ((BloomFilterStrategies.BitArray)Preconditions.checkNotNull(paramBitArray));
    this.numHashFunctions = paramInt;
    this.funnel = ((Funnel)Preconditions.checkNotNull(paramFunnel));
    this.strategy = paramStrategy;
    if (paramInt > 255)
      throw new AssertionError("Currently we don't allow BloomFilters that would use more than255 hash functions, please contact the guava team");
  }

  public BloomFilter copy()
  {
    return new BloomFilter(this.bits.copy(), this.numHashFunctions, this.funnel, this.strategy);
  }

  public boolean mightContain(Object paramObject)
  {
    return this.strategy.mightContain(paramObject, this.funnel, this.numHashFunctions, this.bits);
  }

  public boolean put(Object paramObject)
  {
    return this.strategy.put(paramObject, this.funnel, this.numHashFunctions, this.bits);
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof BloomFilter))
    {
      BloomFilter localBloomFilter = (BloomFilter)paramObject;
      return (this.numHashFunctions == localBloomFilter.numHashFunctions) && (this.bits.equals(localBloomFilter.bits)) && (this.funnel == localBloomFilter.funnel) && (this.strategy == localBloomFilter.strategy);
    }
    return false;
  }

  public int hashCode()
  {
    return this.bits.hashCode();
  }

  @VisibleForTesting
  int getHashCount()
  {
    return this.numHashFunctions;
  }

  @VisibleForTesting
  double computeExpectedFalsePositiveRate(int paramInt)
  {
    return Math.pow(1.0D - Math.exp(-this.numHashFunctions * (paramInt / this.bits.size())), this.numHashFunctions);
  }

  public static BloomFilter create(Funnel paramFunnel, int paramInt, double paramDouble)
  {
    Preconditions.checkNotNull(paramFunnel);
    Preconditions.checkArgument(paramInt > 0, "Expected insertions must be positive");
    Preconditions.checkArgument((paramDouble > 0.0D ? 1 : 0) & (paramDouble < 1.0D ? 1 : 0), "False positive probability in (0.0, 1.0)");
    int i = optimalNumOfBits(paramInt, paramDouble);
    int j = optimalNumOfHashFunctions(paramInt, i);
    return new BloomFilter(new BloomFilterStrategies.BitArray(i), j, paramFunnel, BloomFilterStrategies.MURMUR128_MITZ_32);
  }

  public static BloomFilter create(Funnel paramFunnel, int paramInt)
  {
    return create(paramFunnel, paramInt, 0.03D);
  }

  @VisibleForTesting
  static int optimalNumOfHashFunctions(int paramInt1, int paramInt2)
  {
    return Math.max(1, (int)Math.round(paramInt2 / paramInt1 * LN2));
  }

  @VisibleForTesting
  static int optimalNumOfBits(int paramInt, double paramDouble)
  {
    return (int)(-paramInt * Math.log(paramDouble) / LN2_SQUARED);
  }

  private Object writeReplace()
  {
    return new SerialForm(this);
  }

  private static class SerialForm
    implements Serializable
  {
    final long[] data;
    final int numHashFunctions;
    final Funnel funnel;
    final BloomFilter.Strategy strategy;
    private static final long serialVersionUID = 1L;

    SerialForm(BloomFilter paramBloomFilter)
    {
      this.data = paramBloomFilter.bits.data;
      this.numHashFunctions = paramBloomFilter.numHashFunctions;
      this.funnel = paramBloomFilter.funnel;
      this.strategy = paramBloomFilter.strategy;
    }

    Object readResolve()
    {
      return new BloomFilter(new BloomFilterStrategies.BitArray(this.data), this.numHashFunctions, this.funnel, this.strategy, null);
    }
  }

  static abstract interface Strategy extends Serializable
  {
    public abstract boolean put(Object paramObject, Funnel paramFunnel, int paramInt, BloomFilterStrategies.BitArray paramBitArray);

    public abstract boolean mightContain(Object paramObject, Funnel paramFunnel, int paramInt, BloomFilterStrategies.BitArray paramBitArray);

    public abstract int ordinal();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.hash.BloomFilter
 * JD-Core Version:    0.6.2
 */