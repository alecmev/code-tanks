package com.google.common.math;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Beta
@GwtCompatible(emulated=true)
public final class BigIntegerMath
{

  @VisibleForTesting
  static final int SQRT2_PRECOMPUTE_THRESHOLD = 256;

  @VisibleForTesting
  static final BigInteger SQRT2_PRECOMPUTED_BITS = new BigInteger("16a09e667f3bcc908b2fb1366ea957d3e3adec17512775099da2f590b0667322a", 16);

  public static boolean isPowerOfTwo(BigInteger paramBigInteger)
  {
    Preconditions.checkNotNull(paramBigInteger);
    return (paramBigInteger.signum() > 0) && (paramBigInteger.getLowestSetBit() == paramBigInteger.bitLength() - 1);
  }

  public static int log2(BigInteger paramBigInteger, RoundingMode paramRoundingMode)
  {
    MathPreconditions.checkPositive("x", (BigInteger)Preconditions.checkNotNull(paramBigInteger));
    int i = paramBigInteger.bitLength() - 1;
    switch (1.$SwitchMap$java$math$RoundingMode[paramRoundingMode.ordinal()])
    {
    case 1:
      MathPreconditions.checkRoundingUnnecessary(isPowerOfTwo(paramBigInteger));
    case 2:
    case 3:
      return i;
    case 4:
    case 5:
      return isPowerOfTwo(paramBigInteger) ? i : i + 1;
    case 6:
    case 7:
    case 8:
      if (i < 256)
      {
        localBigInteger = SQRT2_PRECOMPUTED_BITS.shiftRight(256 - i);
        if (paramBigInteger.compareTo(localBigInteger) <= 0)
          return i;
        return i + 1;
      }
      BigInteger localBigInteger = paramBigInteger.pow(2);
      int j = localBigInteger.bitLength() - 1;
      return j < 2 * i + 1 ? i : i + 1;
    }
    throw new AssertionError();
  }

  @GwtIncompatible("TODO")
  public static int log10(BigInteger paramBigInteger, RoundingMode paramRoundingMode)
  {
    MathPreconditions.checkPositive("x", paramBigInteger);
    if (fitsInLong(paramBigInteger))
      return LongMath.log10(paramBigInteger.longValue(), paramRoundingMode);
    ArrayList localArrayList = new ArrayList(10);
    for (BigInteger localBigInteger1 = BigInteger.TEN; paramBigInteger.compareTo(localBigInteger1) >= 0; localBigInteger1 = localBigInteger1.pow(2))
      localArrayList.add(localBigInteger1);
    Object localObject = BigInteger.ONE;
    int i = 0;
    BigInteger localBigInteger3;
    for (int j = localArrayList.size() - 1; j >= 0; j--)
    {
      localBigInteger3 = (BigInteger)localArrayList.get(j);
      i *= 2;
      BigInteger localBigInteger4 = localBigInteger3.multiply((BigInteger)localObject);
      if (paramBigInteger.compareTo(localBigInteger4) >= 0)
      {
        localObject = localBigInteger4;
        i++;
      }
    }
    switch (1.$SwitchMap$java$math$RoundingMode[paramRoundingMode.ordinal()])
    {
    case 1:
      MathPreconditions.checkRoundingUnnecessary(((BigInteger)localObject).equals(paramBigInteger));
    case 2:
    case 3:
      return i;
    case 4:
    case 5:
      return ((BigInteger)localObject).equals(paramBigInteger) ? i : i + 1;
    case 6:
    case 7:
    case 8:
      BigInteger localBigInteger2 = paramBigInteger.pow(2);
      localBigInteger3 = ((BigInteger)localObject).pow(2).multiply(BigInteger.TEN);
      return localBigInteger2.compareTo(localBigInteger3) <= 0 ? i : i + 1;
    }
    throw new AssertionError();
  }

  @GwtIncompatible("TODO")
  public static BigInteger sqrt(BigInteger paramBigInteger, RoundingMode paramRoundingMode)
  {
    MathPreconditions.checkNonNegative("x", paramBigInteger);
    if (fitsInLong(paramBigInteger))
      return BigInteger.valueOf(LongMath.sqrt(paramBigInteger.longValue(), paramRoundingMode));
    BigInteger localBigInteger1 = sqrtFloor(paramBigInteger);
    switch (1.$SwitchMap$java$math$RoundingMode[paramRoundingMode.ordinal()])
    {
    case 1:
      MathPreconditions.checkRoundingUnnecessary(localBigInteger1.pow(2).equals(paramBigInteger));
    case 2:
    case 3:
      return localBigInteger1;
    case 4:
    case 5:
      return localBigInteger1.pow(2).equals(paramBigInteger) ? localBigInteger1 : localBigInteger1.add(BigInteger.ONE);
    case 6:
    case 7:
    case 8:
      BigInteger localBigInteger2 = localBigInteger1.pow(2).add(localBigInteger1);
      return localBigInteger2.compareTo(paramBigInteger) >= 0 ? localBigInteger1 : localBigInteger1.add(BigInteger.ONE);
    }
    throw new AssertionError();
  }

  @GwtIncompatible("TODO")
  private static BigInteger sqrtFloor(BigInteger paramBigInteger)
  {
    int i = log2(paramBigInteger, RoundingMode.FLOOR);
    Object localObject;
    if (i < 1023)
    {
      localObject = sqrtApproxWithDoubles(paramBigInteger);
    }
    else
    {
      int j = i - 52 & 0xFFFFFFFE;
      localObject = sqrtApproxWithDoubles(paramBigInteger.shiftRight(j)).shiftLeft(j >> 1);
    }
    BigInteger localBigInteger = ((BigInteger)localObject).add(paramBigInteger.divide((BigInteger)localObject)).shiftRight(1);
    if (((BigInteger)localObject).equals(localBigInteger))
      return localObject;
    do
    {
      localObject = localBigInteger;
      localBigInteger = ((BigInteger)localObject).add(paramBigInteger.divide((BigInteger)localObject)).shiftRight(1);
    }
    while (localBigInteger.compareTo((BigInteger)localObject) < 0);
    return localObject;
  }

  @GwtIncompatible("TODO")
  private static BigInteger sqrtApproxWithDoubles(BigInteger paramBigInteger)
  {
    return DoubleMath.roundToBigInteger(Math.sqrt(DoubleUtils.bigToDouble(paramBigInteger)), RoundingMode.HALF_EVEN);
  }

  @GwtIncompatible("TODO")
  public static BigInteger divide(BigInteger paramBigInteger1, BigInteger paramBigInteger2, RoundingMode paramRoundingMode)
  {
    BigDecimal localBigDecimal1 = new BigDecimal(paramBigInteger1);
    BigDecimal localBigDecimal2 = new BigDecimal(paramBigInteger2);
    return localBigDecimal1.divide(localBigDecimal2, 0, paramRoundingMode).toBigIntegerExact();
  }

  public static BigInteger factorial(int paramInt)
  {
    MathPreconditions.checkNonNegative("n", paramInt);
    if (paramInt < LongMath.FACTORIALS.length)
      return BigInteger.valueOf(LongMath.FACTORIALS[paramInt]);
    int i = IntMath.divide(paramInt * IntMath.log2(paramInt, RoundingMode.CEILING), 64, RoundingMode.CEILING);
    ArrayList localArrayList = new ArrayList(i);
    int j = LongMath.FACTORIALS.length;
    long l1 = LongMath.FACTORIALS[(j - 1)];
    int k = Long.numberOfTrailingZeros(l1);
    l1 >>= k;
    int m = LongMath.log2(l1, RoundingMode.FLOOR) + 1;
    int n = LongMath.log2(j, RoundingMode.FLOOR) + 1;
    int i1 = 1 << n - 1;
    for (long l2 = j; l2 <= paramInt; l2 += 1L)
    {
      if ((l2 & i1) != 0L)
      {
        i1 <<= 1;
        n++;
      }
      int i2 = Long.numberOfTrailingZeros(l2);
      long l3 = l2 >> i2;
      k += i2;
      int i3 = n - i2;
      if (i3 + m >= 64)
      {
        localArrayList.add(BigInteger.valueOf(l1));
        l1 = 1L;
        m = 0;
      }
      l1 *= l3;
      m = LongMath.log2(l1, RoundingMode.FLOOR) + 1;
    }
    if (l1 > 1L)
      localArrayList.add(BigInteger.valueOf(l1));
    return listProduct(localArrayList).shiftLeft(k);
  }

  static BigInteger listProduct(List paramList)
  {
    return listProduct(paramList, 0, paramList.size());
  }

  static BigInteger listProduct(List paramList, int paramInt1, int paramInt2)
  {
    switch (paramInt2 - paramInt1)
    {
    case 0:
      return BigInteger.ONE;
    case 1:
      return (BigInteger)paramList.get(paramInt1);
    case 2:
      return ((BigInteger)paramList.get(paramInt1)).multiply((BigInteger)paramList.get(paramInt1 + 1));
    case 3:
      return ((BigInteger)paramList.get(paramInt1)).multiply((BigInteger)paramList.get(paramInt1 + 1)).multiply((BigInteger)paramList.get(paramInt1 + 2));
    }
    int i = paramInt2 + paramInt1 >>> 1;
    return listProduct(paramList, paramInt1, i).multiply(listProduct(paramList, i, paramInt2));
  }

  public static BigInteger binomial(int paramInt1, int paramInt2)
  {
    MathPreconditions.checkNonNegative("n", paramInt1);
    MathPreconditions.checkNonNegative("k", paramInt2);
    Preconditions.checkArgument(paramInt2 <= paramInt1, "k (%s) > n (%s)", new Object[] { Integer.valueOf(paramInt2), Integer.valueOf(paramInt1) });
    if (paramInt2 > paramInt1 >> 1)
      paramInt2 = paramInt1 - paramInt2;
    if ((paramInt2 < LongMath.BIGGEST_BINOMIALS.length) && (paramInt1 <= LongMath.BIGGEST_BINOMIALS[paramInt2]))
      return BigInteger.valueOf(LongMath.binomial(paramInt1, paramInt2));
    BigInteger localBigInteger = BigInteger.ONE;
    for (int i = 0; i < paramInt2; i++)
    {
      localBigInteger = localBigInteger.multiply(BigInteger.valueOf(paramInt1 - i));
      localBigInteger = localBigInteger.divide(BigInteger.valueOf(i + 1));
    }
    return localBigInteger;
  }

  @GwtIncompatible("TODO")
  static boolean fitsInLong(BigInteger paramBigInteger)
  {
    return paramBigInteger.bitLength() <= 63;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.math.BigIntegerMath
 * JD-Core Version:    0.6.2
 */