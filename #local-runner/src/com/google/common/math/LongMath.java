package com.google.common.math;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.math.RoundingMode;

@Beta
@GwtCompatible(emulated=true)
public final class LongMath
{

  @VisibleForTesting
  static final long MAX_POWER_OF_SQRT2_UNSIGNED = -5402926248376769404L;

  @GwtIncompatible("TODO")
  @VisibleForTesting
  static final long[] POWERS_OF_10 = { 1L, 10L, 100L, 1000L, 10000L, 100000L, 1000000L, 10000000L, 100000000L, 1000000000L, 10000000000L, 100000000000L, 1000000000000L, 10000000000000L, 100000000000000L, 1000000000000000L, 10000000000000000L, 100000000000000000L, 1000000000000000000L };

  @GwtIncompatible("TODO")
  @VisibleForTesting
  static final long[] HALF_POWERS_OF_10 = { 3L, 31L, 316L, 3162L, 31622L, 316227L, 3162277L, 31622776L, 316227766L, 3162277660L, 31622776601L, 316227766016L, 3162277660168L, 31622776601683L, 316227766016837L, 3162277660168379L, 31622776601683793L, 316227766016837933L, 3162277660168379331L };

  @GwtIncompatible("TODO")
  @VisibleForTesting
  static final long FLOOR_SQRT_MAX_LONG = 3037000499L;
  static final long[] FACTORIALS = { 1L, 1L, 2L, 6L, 24L, 120L, 720L, 5040L, 40320L, 362880L, 3628800L, 39916800L, 479001600L, 6227020800L, 87178291200L, 1307674368000L, 20922789888000L, 355687428096000L, 6402373705728000L, 121645100408832000L, 2432902008176640000L };
  static final int[] BIGGEST_BINOMIALS = { 2147483647, 2147483647, 2147483647, 3810779, 121977, 16175, 4337, 1733, 887, 534, 361, 265, 206, 169, 143, 125, 111, 101, 94, 88, 83, 79, 76, 74, 72, 70, 69, 68, 67, 67, 66, 66, 66, 66 };

  @VisibleForTesting
  static final int[] BIGGEST_SIMPLE_BINOMIALS = { 2147483647, 2147483647, 2147483647, 2642246, 86251, 11724, 3218, 1313, 684, 419, 287, 214, 169, 139, 119, 105, 95, 87, 81, 76, 73, 70, 68, 66, 64, 63, 62, 62, 61, 61, 61 };

  public static boolean isPowerOfTwo(long paramLong)
  {
    return (paramLong > 0L ? 1 : 0) & ((paramLong & paramLong - 1L) == 0L ? 1 : 0);
  }

  public static int log2(long paramLong, RoundingMode paramRoundingMode)
  {
    MathPreconditions.checkPositive("x", paramLong);
    switch (1.$SwitchMap$java$math$RoundingMode[paramRoundingMode.ordinal()])
    {
    case 1:
      MathPreconditions.checkRoundingUnnecessary(isPowerOfTwo(paramLong));
    case 2:
    case 3:
      return 63 - Long.numberOfLeadingZeros(paramLong);
    case 4:
    case 5:
      return 64 - Long.numberOfLeadingZeros(paramLong - 1L);
    case 6:
    case 7:
    case 8:
      int i = Long.numberOfLeadingZeros(paramLong);
      long l = -5402926248376769404L >>> i;
      int j = 63 - i;
      return paramLong <= l ? j : j + 1;
    }
    throw new AssertionError("impossible");
  }

  @GwtIncompatible("TODO")
  public static int log10(long paramLong, RoundingMode paramRoundingMode)
  {
    MathPreconditions.checkPositive("x", paramLong);
    if (fitsInInt(paramLong))
      return IntMath.log10((int)paramLong, paramRoundingMode);
    int i = log10Floor(paramLong);
    long l = POWERS_OF_10[i];
    switch (1.$SwitchMap$java$math$RoundingMode[paramRoundingMode.ordinal()])
    {
    case 1:
      MathPreconditions.checkRoundingUnnecessary(paramLong == l);
    case 2:
    case 3:
      return i;
    case 4:
    case 5:
      return paramLong == l ? i : i + 1;
    case 6:
    case 7:
    case 8:
      return paramLong <= HALF_POWERS_OF_10[i] ? i : i + 1;
    }
    throw new AssertionError();
  }

  @GwtIncompatible("TODO")
  static int log10Floor(long paramLong)
  {
    for (int i = 1; i < POWERS_OF_10.length; i++)
      if (paramLong < POWERS_OF_10[i])
        return i - 1;
    return POWERS_OF_10.length - 1;
  }

  @GwtIncompatible("TODO")
  public static long pow(long paramLong, int paramInt)
  {
    MathPreconditions.checkNonNegative("exponent", paramInt);
    if ((-2L <= paramLong) && (paramLong <= 2L))
      switch ((int)paramLong)
      {
      case 0:
        return paramInt == 0 ? 1L : 0L;
      case 1:
        return 1L;
      case -1:
        return (paramInt & 0x1) == 0 ? 1L : -1L;
      case 2:
        return paramInt < 64 ? 1L << paramInt : 0L;
      case -2:
        if (paramInt < 64)
          return (paramInt & 0x1) == 0 ? 1L << paramInt : -(1L << paramInt);
        return 0L;
      }
    long l = 1L;
    while (true)
    {
      switch (paramInt)
      {
      case 0:
        return l;
      case 1:
        return l * paramLong;
      }
      l *= ((paramInt & 0x1) == 0 ? 1L : paramLong);
      paramLong *= paramLong;
      paramInt >>= 1;
    }
  }

  @GwtIncompatible("TODO")
  public static long sqrt(long paramLong, RoundingMode paramRoundingMode)
  {
    MathPreconditions.checkNonNegative("x", paramLong);
    if (fitsInInt(paramLong))
      return IntMath.sqrt((int)paramLong, paramRoundingMode);
    long l1 = sqrtFloor(paramLong);
    switch (1.$SwitchMap$java$math$RoundingMode[paramRoundingMode.ordinal()])
    {
    case 1:
      MathPreconditions.checkRoundingUnnecessary(l1 * l1 == paramLong);
    case 2:
    case 3:
      return l1;
    case 4:
    case 5:
      return l1 * l1 == paramLong ? l1 : l1 + 1L;
    case 6:
    case 7:
    case 8:
      long l2 = l1 * l1 + l1;
      return ((l2 >= paramLong ? 1 : 0) | (l2 < 0L ? 1 : 0)) != 0 ? l1 : l1 + 1L;
    }
    throw new AssertionError();
  }

  @GwtIncompatible("TODO")
  private static long sqrtFloor(long paramLong)
  {
    long l1 = ()Math.sqrt(paramLong);
    long l2 = l1 + paramLong / l1 >> 1;
    if (l2 == l1)
      return l1;
    do
    {
      l1 = l2;
      l2 = l1 + paramLong / l1 >> 1;
    }
    while (l2 < l1);
    return l1;
  }

  @GwtIncompatible("TODO")
  public static long divide(long paramLong1, long paramLong2, RoundingMode paramRoundingMode)
  {
    Preconditions.checkNotNull(paramRoundingMode);
    long l1 = paramLong1 / paramLong2;
    long l2 = paramLong1 - paramLong2 * l1;
    if (l2 == 0L)
      return l1;
    int i = 0x1 | (int)((paramLong1 ^ paramLong2) >> 63);
    int j;
    switch (1.$SwitchMap$java$math$RoundingMode[paramRoundingMode.ordinal()])
    {
    case 1:
      MathPreconditions.checkRoundingUnnecessary(l2 == 0L);
    case 2:
      j = 0;
      break;
    case 4:
      j = 1;
      break;
    case 5:
      j = i > 0 ? 1 : 0;
      break;
    case 3:
      j = i < 0 ? 1 : 0;
      break;
    case 6:
    case 7:
    case 8:
      long l3 = Math.abs(l2);
      long l4 = l3 - (Math.abs(paramLong2) - l3);
      if (l4 == 0L)
        j = (paramRoundingMode == RoundingMode.HALF_UP ? 1 : 0) | (paramRoundingMode == RoundingMode.HALF_EVEN ? 1 : 0) & ((l1 & 1L) != 0L ? 1 : 0);
      else
        j = l4 > 0L ? 1 : 0;
      break;
    default:
      throw new AssertionError();
    }
    return j != 0 ? l1 + i : l1;
  }

  @GwtIncompatible("TODO")
  public static int mod(long paramLong, int paramInt)
  {
    return (int)mod(paramLong, paramInt);
  }

  @GwtIncompatible("TODO")
  public static long mod(long paramLong1, long paramLong2)
  {
    if (paramLong2 <= 0L)
      throw new ArithmeticException("Modulus " + paramLong2 + " must be > 0");
    long l = paramLong1 % paramLong2;
    return l >= 0L ? l : l + paramLong2;
  }

  @GwtIncompatible("TODO")
  public static long gcd(long paramLong1, long paramLong2)
  {
    MathPreconditions.checkNonNegative("a", paramLong1);
    MathPreconditions.checkNonNegative("b", paramLong2);
    if (((paramLong1 == 0L ? 1 : 0) | (paramLong2 == 0L ? 1 : 0)) != 0)
      return paramLong1 | paramLong2;
    int i = Long.numberOfTrailingZeros(paramLong1);
    paramLong1 >>= i;
    int j = Long.numberOfTrailingZeros(paramLong2);
    paramLong2 >>= j;
    while (paramLong1 != paramLong2)
    {
      if (paramLong1 < paramLong2)
      {
        long l = paramLong2;
        paramLong2 = paramLong1;
        paramLong1 = l;
      }
      paramLong1 -= paramLong2;
      paramLong1 >>= Long.numberOfTrailingZeros(paramLong1);
    }
    return paramLong1 << Math.min(i, j);
  }

  @GwtIncompatible("TODO")
  public static long checkedAdd(long paramLong1, long paramLong2)
  {
    long l = paramLong1 + paramLong2;
    MathPreconditions.checkNoOverflow(((paramLong1 ^ paramLong2) < 0L ? 1 : 0) | ((paramLong1 ^ l) >= 0L ? 1 : 0));
    return l;
  }

  @GwtIncompatible("TODO")
  public static long checkedSubtract(long paramLong1, long paramLong2)
  {
    long l = paramLong1 - paramLong2;
    MathPreconditions.checkNoOverflow(((paramLong1 ^ paramLong2) >= 0L ? 1 : 0) | ((paramLong1 ^ l) >= 0L ? 1 : 0));
    return l;
  }

  @GwtIncompatible("TODO")
  public static long checkedMultiply(long paramLong1, long paramLong2)
  {
    int i = Long.numberOfLeadingZeros(paramLong1) + Long.numberOfLeadingZeros(paramLong1 ^ 0xFFFFFFFF) + Long.numberOfLeadingZeros(paramLong2) + Long.numberOfLeadingZeros(paramLong2 ^ 0xFFFFFFFF);
    if (i > 65)
      return paramLong1 * paramLong2;
    MathPreconditions.checkNoOverflow(i >= 64);
    MathPreconditions.checkNoOverflow((paramLong1 >= 0L ? 1 : 0) | (paramLong2 != -9223372036854775808L ? 1 : 0));
    long l = paramLong1 * paramLong2;
    MathPreconditions.checkNoOverflow((paramLong1 == 0L) || (l / paramLong1 == paramLong2));
    return l;
  }

  @GwtIncompatible("TODO")
  public static long checkedPow(long paramLong, int paramInt)
  {
    MathPreconditions.checkNonNegative("exponent", paramInt);
    if (((paramLong >= -2L ? 1 : 0) & (paramLong <= 2L ? 1 : 0)) != 0)
      switch ((int)paramLong)
      {
      case 0:
        return paramInt == 0 ? 1L : 0L;
      case 1:
        return 1L;
      case -1:
        return (paramInt & 0x1) == 0 ? 1L : -1L;
      case 2:
        MathPreconditions.checkNoOverflow(paramInt < 63);
        return 1L << paramInt;
      case -2:
        MathPreconditions.checkNoOverflow(paramInt < 64);
        return (paramInt & 0x1) == 0 ? 1L << paramInt : -1L << paramInt;
      }
    long l = 1L;
    while (true)
    {
      switch (paramInt)
      {
      case 0:
        return l;
      case 1:
        return checkedMultiply(l, paramLong);
      }
      if ((paramInt & 0x1) != 0)
        l = checkedMultiply(l, paramLong);
      paramInt >>= 1;
      if (paramInt > 0)
      {
        MathPreconditions.checkNoOverflow(paramLong <= 3037000499L);
        paramLong *= paramLong;
      }
    }
  }

  @GwtIncompatible("TODO")
  public static long factorial(int paramInt)
  {
    MathPreconditions.checkNonNegative("n", paramInt);
    return paramInt < FACTORIALS.length ? FACTORIALS[paramInt] : 9223372036854775807L;
  }

  public static long binomial(int paramInt1, int paramInt2)
  {
    MathPreconditions.checkNonNegative("n", paramInt1);
    MathPreconditions.checkNonNegative("k", paramInt2);
    Preconditions.checkArgument(paramInt2 <= paramInt1, "k (%s) > n (%s)", new Object[] { Integer.valueOf(paramInt2), Integer.valueOf(paramInt1) });
    if (paramInt2 > paramInt1 >> 1)
      paramInt2 = paramInt1 - paramInt2;
    if ((paramInt2 >= BIGGEST_BINOMIALS.length) || (paramInt1 > BIGGEST_BINOMIALS[paramInt2]))
      return 9223372036854775807L;
    long l = 1L;
    int i;
    if ((paramInt2 < BIGGEST_SIMPLE_BINOMIALS.length) && (paramInt1 <= BIGGEST_SIMPLE_BINOMIALS[paramInt2]))
    {
      for (i = 0; i < paramInt2; i++)
      {
        l *= (paramInt1 - i);
        l /= (i + 1);
      }
    }
    else
    {
      i = 1;
      while (i <= paramInt2)
      {
        int j = IntMath.gcd(paramInt1, i);
        l /= i / j;
        l *= paramInt1 / j;
        i++;
        paramInt1--;
      }
    }
    return l;
  }

  @GwtIncompatible("TODO")
  static boolean fitsInInt(long paramLong)
  {
    return (int)paramLong == paramLong;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.math.LongMath
 * JD-Core Version:    0.6.2
 */