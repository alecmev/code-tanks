package com.google.common.primitives;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.math.BigInteger;

@Beta
@GwtCompatible(emulated=true)
public final class UnsignedInteger extends Number
  implements Comparable
{
  public static final UnsignedInteger ZERO = asUnsigned(0);
  public static final UnsignedInteger ONE = asUnsigned(1);
  public static final UnsignedInteger MAX_VALUE = asUnsigned(-1);
  private final int value;

  private UnsignedInteger(int paramInt)
  {
    this.value = (paramInt & 0xFFFFFFFF);
  }

  public static UnsignedInteger asUnsigned(int paramInt)
  {
    return new UnsignedInteger(paramInt);
  }

  public static UnsignedInteger valueOf(long paramLong)
  {
    Preconditions.checkArgument((paramLong & 0xFFFFFFFF) == paramLong, "value (%s) is outside the range for an unsigned integer value", new Object[] { Long.valueOf(paramLong) });
    return asUnsigned((int)paramLong);
  }

  public static UnsignedInteger valueOf(BigInteger paramBigInteger)
  {
    Preconditions.checkNotNull(paramBigInteger);
    Preconditions.checkArgument((paramBigInteger.signum() >= 0) && (paramBigInteger.bitLength() <= 32), "value (%s) is outside the range for an unsigned integer value", new Object[] { paramBigInteger });
    return asUnsigned(paramBigInteger.intValue());
  }

  public static UnsignedInteger valueOf(String paramString)
  {
    return valueOf(paramString, 10);
  }

  public static UnsignedInteger valueOf(String paramString, int paramInt)
  {
    return asUnsigned(UnsignedInts.parseUnsignedInt(paramString, paramInt));
  }

  public UnsignedInteger add(UnsignedInteger paramUnsignedInteger)
  {
    Preconditions.checkNotNull(paramUnsignedInteger);
    return asUnsigned(this.value + paramUnsignedInteger.value);
  }

  public UnsignedInteger subtract(UnsignedInteger paramUnsignedInteger)
  {
    Preconditions.checkNotNull(paramUnsignedInteger);
    return asUnsigned(this.value - paramUnsignedInteger.value);
  }

  @GwtIncompatible("Does not truncate correctly")
  public UnsignedInteger multiply(UnsignedInteger paramUnsignedInteger)
  {
    Preconditions.checkNotNull(paramUnsignedInteger);
    return asUnsigned(this.value * paramUnsignedInteger.value);
  }

  public UnsignedInteger divide(UnsignedInteger paramUnsignedInteger)
  {
    Preconditions.checkNotNull(paramUnsignedInteger);
    return asUnsigned(UnsignedInts.divide(this.value, paramUnsignedInteger.value));
  }

  public UnsignedInteger remainder(UnsignedInteger paramUnsignedInteger)
  {
    Preconditions.checkNotNull(paramUnsignedInteger);
    return asUnsigned(UnsignedInts.remainder(this.value, paramUnsignedInteger.value));
  }

  public int intValue()
  {
    return this.value;
  }

  public long longValue()
  {
    return UnsignedInts.toLong(this.value);
  }

  public float floatValue()
  {
    return (float)longValue();
  }

  public double doubleValue()
  {
    return longValue();
  }

  public BigInteger bigIntegerValue()
  {
    return BigInteger.valueOf(longValue());
  }

  public int compareTo(UnsignedInteger paramUnsignedInteger)
  {
    Preconditions.checkNotNull(paramUnsignedInteger);
    return UnsignedInts.compare(this.value, paramUnsignedInteger.value);
  }

  public int hashCode()
  {
    return this.value;
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof UnsignedInteger))
    {
      UnsignedInteger localUnsignedInteger = (UnsignedInteger)paramObject;
      return this.value == localUnsignedInteger.value;
    }
    return false;
  }

  public String toString()
  {
    return toString(10);
  }

  public String toString(int paramInt)
  {
    return UnsignedInts.toString(this.value, paramInt);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.primitives.UnsignedInteger
 * JD-Core Version:    0.6.2
 */