package com.google.common.primitives;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.math.BigInteger;

@Beta
@GwtCompatible(serializable=true)
public final class UnsignedLong extends Number
  implements Serializable, Comparable
{
  private static final long UNSIGNED_MASK = 9223372036854775807L;
  public static final UnsignedLong ZERO = new UnsignedLong(0L);
  public static final UnsignedLong ONE = new UnsignedLong(1L);
  public static final UnsignedLong MAX_VALUE = new UnsignedLong(-1L);
  private final long value;

  private UnsignedLong(long paramLong)
  {
    this.value = paramLong;
  }

  public static UnsignedLong asUnsigned(long paramLong)
  {
    return new UnsignedLong(paramLong);
  }

  public static UnsignedLong valueOf(BigInteger paramBigInteger)
  {
    Preconditions.checkNotNull(paramBigInteger);
    Preconditions.checkArgument((paramBigInteger.signum() >= 0) && (paramBigInteger.bitLength() <= 64), "value (%s) is outside the range for an unsigned long value", new Object[] { paramBigInteger });
    return asUnsigned(paramBigInteger.longValue());
  }

  public static UnsignedLong valueOf(String paramString)
  {
    return valueOf(paramString, 10);
  }

  public static UnsignedLong valueOf(String paramString, int paramInt)
  {
    return asUnsigned(UnsignedLongs.parseUnsignedLong(paramString, paramInt));
  }

  public UnsignedLong add(UnsignedLong paramUnsignedLong)
  {
    Preconditions.checkNotNull(paramUnsignedLong);
    return asUnsigned(this.value + paramUnsignedLong.value);
  }

  public UnsignedLong subtract(UnsignedLong paramUnsignedLong)
  {
    Preconditions.checkNotNull(paramUnsignedLong);
    return asUnsigned(this.value - paramUnsignedLong.value);
  }

  public UnsignedLong multiply(UnsignedLong paramUnsignedLong)
  {
    Preconditions.checkNotNull(paramUnsignedLong);
    return asUnsigned(this.value * paramUnsignedLong.value);
  }

  public UnsignedLong divide(UnsignedLong paramUnsignedLong)
  {
    Preconditions.checkNotNull(paramUnsignedLong);
    return asUnsigned(UnsignedLongs.divide(this.value, paramUnsignedLong.value));
  }

  public UnsignedLong remainder(UnsignedLong paramUnsignedLong)
  {
    Preconditions.checkNotNull(paramUnsignedLong);
    return asUnsigned(UnsignedLongs.remainder(this.value, paramUnsignedLong.value));
  }

  public int intValue()
  {
    return (int)this.value;
  }

  public long longValue()
  {
    return this.value;
  }

  public float floatValue()
  {
    float f = (float)(this.value & 0xFFFFFFFF);
    if (this.value < 0L)
      f += 9.223372E+018F;
    return f;
  }

  public double doubleValue()
  {
    double d = this.value & 0xFFFFFFFF;
    if (this.value < 0L)
      d += 9.223372036854776E+018D;
    return d;
  }

  public BigInteger bigIntegerValue()
  {
    BigInteger localBigInteger = BigInteger.valueOf(this.value & 0xFFFFFFFF);
    if (this.value < 0L)
      localBigInteger = localBigInteger.setBit(63);
    return localBigInteger;
  }

  public int compareTo(UnsignedLong paramUnsignedLong)
  {
    Preconditions.checkNotNull(paramUnsignedLong);
    return UnsignedLongs.compare(this.value, paramUnsignedLong.value);
  }

  public int hashCode()
  {
    return Longs.hashCode(this.value);
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof UnsignedLong))
    {
      UnsignedLong localUnsignedLong = (UnsignedLong)paramObject;
      return this.value == localUnsignedLong.value;
    }
    return false;
  }

  public String toString()
  {
    return UnsignedLongs.toString(this.value);
  }

  public String toString(int paramInt)
  {
    return UnsignedLongs.toString(this.value, paramInt);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.primitives.UnsignedLong
 * JD-Core Version:    0.6.2
 */