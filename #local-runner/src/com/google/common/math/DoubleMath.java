package com.google.common.math;

import com.google.common.annotations.Beta;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.math.BigInteger;
import java.math.RoundingMode;

@Beta
public final class DoubleMath
{
  private static final double MIN_INT_AS_DOUBLE = -2147483648.0D;
  private static final double MAX_INT_AS_DOUBLE = 2147483647.0D;
  private static final double MIN_LONG_AS_DOUBLE = -9.223372036854776E+018D;
  private static final double MAX_LONG_AS_DOUBLE_PLUS_ONE = 9.223372036854776E+018D;
  private static final double LN_2 = Math.log(2.0D);

  @VisibleForTesting
  static final int MAX_FACTORIAL = 170;

  @VisibleForTesting
  static final double[] EVERY_SIXTEENTH_FACTORIAL = { 1.0D, 20922789888000.0D, 2.631308369336935E+035D, 1.241391559253607E+061D, 1.268869321858842E+089D, 7.156945704626381E+118D, 9.916779348709497E+149D, 1.974506857221074E+182D, 3.856204823625804E+215D, 5.550293832739304E+249D, 4.714723635992062E+284D };

  static double roundIntermediate(double paramDouble, RoundingMode paramRoundingMode)
  {
    if (!DoubleUtils.isFinite(paramDouble))
      throw new ArithmeticException("input is infinite or NaN");
    switch (1.$SwitchMap$java$math$RoundingMode[paramRoundingMode.ordinal()])
    {
    case 1:
      MathPreconditions.checkRoundingUnnecessary(isMathematicalInteger(paramDouble));
      return paramDouble;
    case 2:
      return paramDouble >= 0.0D ? paramDouble : Math.floor(paramDouble);
    case 3:
      return paramDouble >= 0.0D ? Math.ceil(paramDouble) : paramDouble;
    case 4:
      return paramDouble;
    case 5:
      return paramDouble >= 0.0D ? Math.ceil(paramDouble) : Math.floor(paramDouble);
    case 6:
      return Math.rint(paramDouble);
    case 7:
      if (isMathematicalInteger(paramDouble))
        return paramDouble;
      return paramDouble >= 0.0D ? paramDouble + 0.5D : paramDouble - 0.5D;
    case 8:
      if (isMathematicalInteger(paramDouble))
        return paramDouble;
      if (paramDouble >= 0.0D)
      {
        d = paramDouble + 0.5D;
        return d == paramDouble ? paramDouble : DoubleUtils.nextDown(d);
      }
      double d = paramDouble - 0.5D;
      return d == paramDouble ? paramDouble : Math.nextUp(d);
    }
    throw new AssertionError();
  }

  public static int roundToInt(double paramDouble, RoundingMode paramRoundingMode)
  {
    double d = roundIntermediate(paramDouble, paramRoundingMode);
    MathPreconditions.checkInRange((d > -2147483649.0D ? 1 : 0) & (d < 2147483648.0D ? 1 : 0));
    return (int)d;
  }

  public static long roundToLong(double paramDouble, RoundingMode paramRoundingMode)
  {
    double d = roundIntermediate(paramDouble, paramRoundingMode);
    MathPreconditions.checkInRange((-9.223372036854776E+018D - d < 1.0D ? 1 : 0) & (d < 9.223372036854776E+018D ? 1 : 0));
    return ()d;
  }

  public static BigInteger roundToBigInteger(double paramDouble, RoundingMode paramRoundingMode)
  {
    paramDouble = roundIntermediate(paramDouble, paramRoundingMode);
    if (((-9.223372036854776E+018D - paramDouble < 1.0D ? 1 : 0) & (paramDouble < 9.223372036854776E+018D ? 1 : 0)) != 0)
      return BigInteger.valueOf(()paramDouble);
    int i = Math.getExponent(paramDouble);
    if (i < 0)
      return BigInteger.ZERO;
    long l = DoubleUtils.getSignificand(paramDouble);
    BigInteger localBigInteger = BigInteger.valueOf(l).shiftLeft(i - 52);
    return paramDouble < 0.0D ? localBigInteger.negate() : localBigInteger;
  }

  public static boolean isPowerOfTwo(double paramDouble)
  {
    return (paramDouble > 0.0D) && (DoubleUtils.isFinite(paramDouble)) && (LongMath.isPowerOfTwo(DoubleUtils.getSignificand(paramDouble)));
  }

  public static double log2(double paramDouble)
  {
    return Math.log(paramDouble) / LN_2;
  }

  public static int log2(double paramDouble, RoundingMode paramRoundingMode)
  {
    Preconditions.checkArgument((paramDouble > 0.0D) && (DoubleUtils.isFinite(paramDouble)), "x must be positive and finite");
    int i = Math.getExponent(paramDouble);
    if (!DoubleUtils.isNormal(paramDouble))
      return log2(paramDouble * 4503599627370496.0D, paramRoundingMode) - 52;
    int j;
    switch (1.$SwitchMap$java$math$RoundingMode[paramRoundingMode.ordinal()])
    {
    case 1:
      MathPreconditions.checkRoundingUnnecessary(isPowerOfTwo(paramDouble));
    case 2:
      j = 0;
      break;
    case 3:
      j = !isPowerOfTwo(paramDouble) ? 1 : 0;
      break;
    case 4:
      j = (i < 0 ? 1 : 0) & (!isPowerOfTwo(paramDouble) ? 1 : 0);
      break;
    case 5:
      j = (i >= 0 ? 1 : 0) & (!isPowerOfTwo(paramDouble) ? 1 : 0);
      break;
    case 6:
    case 7:
    case 8:
      double d = DoubleUtils.scaleNormalize(paramDouble);
      j = d * d > 2.0D ? 1 : 0;
      break;
    default:
      throw new AssertionError();
    }
    return j != 0 ? i + 1 : i;
  }

  public static boolean isMathematicalInteger(double paramDouble)
  {
    return (DoubleUtils.isFinite(paramDouble)) && ((paramDouble == 0.0D) || (52 - Long.numberOfTrailingZeros(DoubleUtils.getSignificand(paramDouble)) <= Math.getExponent(paramDouble)));
  }

  public static double factorial(int paramInt)
  {
    MathPreconditions.checkNonNegative("n", paramInt);
    if (paramInt > 170)
      return (1.0D / 0.0D);
    double d = 1.0D;
    for (int i = 1 + (paramInt & 0xFFFFFFF0); i <= paramInt; i++)
      d *= i;
    return d * EVERY_SIXTEENTH_FACTORIAL[(paramInt >> 4)];
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.math.DoubleMath
 * JD-Core Version:    0.6.2
 */