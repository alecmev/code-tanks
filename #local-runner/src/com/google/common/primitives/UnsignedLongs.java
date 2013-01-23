package com.google.common.primitives;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.math.BigInteger;
import java.util.Comparator;

@Beta
@GwtCompatible
public final class UnsignedLongs
{
  public static final long MAX_VALUE = -1L;
  private static final long[] maxValueDivs = new long[37];
  private static final int[] maxValueMods = new int[37];
  private static final int[] maxSafeDigits = new int[37];

  private static long flip(long paramLong)
  {
    return paramLong ^ 0x0;
  }

  public static int compare(long paramLong1, long paramLong2)
  {
    return Longs.compare(flip(paramLong1), flip(paramLong2));
  }

  public static long min(long[] paramArrayOfLong)
  {
    Preconditions.checkArgument(paramArrayOfLong.length > 0);
    long l1 = flip(paramArrayOfLong[0]);
    for (int i = 1; i < paramArrayOfLong.length; i++)
    {
      long l2 = flip(paramArrayOfLong[i]);
      if (l2 < l1)
        l1 = l2;
    }
    return flip(l1);
  }

  public static long max(long[] paramArrayOfLong)
  {
    Preconditions.checkArgument(paramArrayOfLong.length > 0);
    long l1 = flip(paramArrayOfLong[0]);
    for (int i = 1; i < paramArrayOfLong.length; i++)
    {
      long l2 = flip(paramArrayOfLong[i]);
      if (l2 > l1)
        l1 = l2;
    }
    return flip(l1);
  }

  public static String join(String paramString, long[] paramArrayOfLong)
  {
    Preconditions.checkNotNull(paramString);
    if (paramArrayOfLong.length == 0)
      return "";
    StringBuilder localStringBuilder = new StringBuilder(paramArrayOfLong.length * 5);
    localStringBuilder.append(toString(paramArrayOfLong[0]));
    for (int i = 1; i < paramArrayOfLong.length; i++)
      localStringBuilder.append(paramString).append(toString(paramArrayOfLong[i]));
    return localStringBuilder.toString();
  }

  public static Comparator lexicographicalComparator()
  {
    return LexicographicalComparator.INSTANCE;
  }

  public static long divide(long paramLong1, long paramLong2)
  {
    if (paramLong2 < 0L)
    {
      if (compare(paramLong1, paramLong2) < 0)
        return 0L;
      return 1L;
    }
    if (paramLong1 >= 0L)
      return paramLong1 / paramLong2;
    long l1 = (paramLong1 >>> 1) / paramLong2 << 1;
    long l2 = paramLong1 - l1 * paramLong2;
    return l1 + (compare(l2, paramLong2) >= 0 ? 1 : 0);
  }

  public static long remainder(long paramLong1, long paramLong2)
  {
    if (paramLong2 < 0L)
    {
      if (compare(paramLong1, paramLong2) < 0)
        return paramLong1;
      return paramLong1 - paramLong2;
    }
    if (paramLong1 >= 0L)
      return paramLong1 % paramLong2;
    long l1 = (paramLong1 >>> 1) / paramLong2 << 1;
    long l2 = paramLong1 - l1 * paramLong2;
    return l2 - (compare(l2, paramLong2) >= 0 ? paramLong2 : 0L);
  }

  public static long parseUnsignedLong(String paramString)
  {
    return parseUnsignedLong(paramString, 10);
  }

  public static long parseUnsignedLong(String paramString, int paramInt)
  {
    Preconditions.checkNotNull(paramString);
    if (paramString.length() == 0)
      throw new NumberFormatException("empty string");
    if ((paramInt < 2) || (paramInt > 36))
      throw new NumberFormatException("illegal radix: " + paramInt);
    int i = maxSafeDigits[paramInt] - 1;
    long l = 0L;
    for (int j = 0; j < paramString.length(); j++)
    {
      int k = Character.digit(paramString.charAt(j), paramInt);
      if (k == -1)
        throw new NumberFormatException(paramString);
      if ((j > i) && (overflowInParse(l, k, paramInt)))
        throw new NumberFormatException("Too large for unsigned long: " + paramString);
      l = l * paramInt + k;
    }
    return l;
  }

  private static boolean overflowInParse(long paramLong, int paramInt1, int paramInt2)
  {
    if (paramLong >= 0L)
    {
      if (paramLong < maxValueDivs[paramInt2])
        return false;
      if (paramLong > maxValueDivs[paramInt2])
        return true;
      return paramInt1 > maxValueMods[paramInt2];
    }
    return true;
  }

  public static String toString(long paramLong)
  {
    return toString(paramLong, 10);
  }

  public static String toString(long paramLong, int paramInt)
  {
    Preconditions.checkArgument((paramInt >= 2) && (paramInt <= 36), "radix (%s) must be between Character.MIN_RADIX and Character.MAX_RADIX", new Object[] { Integer.valueOf(paramInt) });
    if (paramLong == 0L)
      return "0";
    char[] arrayOfChar = new char[64];
    int i = arrayOfChar.length;
    long l1;
    if (paramLong < 0L)
    {
      l1 = divide(paramLong, paramInt);
      long l2 = paramLong - l1 * paramInt;
      arrayOfChar[(--i)] = Character.forDigit((int)l2, paramInt);
    }
    for (paramLong = l1; paramLong > 0L; paramLong /= paramInt)
      arrayOfChar[(--i)] = Character.forDigit((int)(paramLong % paramInt), paramInt);
    return new String(arrayOfChar, i, arrayOfChar.length - i);
  }

  static
  {
    BigInteger localBigInteger = new BigInteger("10000000000000000", 16);
    for (int i = 2; i <= 36; i++)
    {
      maxValueDivs[i] = divide(-1L, i);
      maxValueMods[i] = ((int)remainder(-1L, i));
      maxSafeDigits[i] = (localBigInteger.toString(i).length() - 1);
    }
  }

  static enum LexicographicalComparator
    implements Comparator
  {
    INSTANCE;

    public int compare(long[] paramArrayOfLong1, long[] paramArrayOfLong2)
    {
      int i = Math.min(paramArrayOfLong1.length, paramArrayOfLong2.length);
      for (int j = 0; j < i; j++)
        if (paramArrayOfLong1[j] != paramArrayOfLong2[j])
          return UnsignedLongs.compare(paramArrayOfLong1[j], paramArrayOfLong2[j]);
      return paramArrayOfLong1.length - paramArrayOfLong2.length;
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.primitives.UnsignedLongs
 * JD-Core Version:    0.6.2
 */