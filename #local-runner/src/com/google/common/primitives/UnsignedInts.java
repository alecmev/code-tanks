package com.google.common.primitives;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.Comparator;

@Beta
@GwtCompatible
public final class UnsignedInts
{
  static final long INT_MASK = 4294967295L;

  static int flip(int paramInt)
  {
    return paramInt ^ 0x80000000;
  }

  public static int compare(int paramInt1, int paramInt2)
  {
    return Ints.compare(flip(paramInt1), flip(paramInt2));
  }

  public static long toLong(int paramInt)
  {
    return paramInt & 0xFFFFFFFF;
  }

  public static int min(int[] paramArrayOfInt)
  {
    Preconditions.checkArgument(paramArrayOfInt.length > 0);
    int i = flip(paramArrayOfInt[0]);
    for (int j = 1; j < paramArrayOfInt.length; j++)
    {
      int k = flip(paramArrayOfInt[j]);
      if (k < i)
        i = k;
    }
    return flip(i);
  }

  public static int max(int[] paramArrayOfInt)
  {
    Preconditions.checkArgument(paramArrayOfInt.length > 0);
    int i = flip(paramArrayOfInt[0]);
    for (int j = 1; j < paramArrayOfInt.length; j++)
    {
      int k = flip(paramArrayOfInt[j]);
      if (k > i)
        i = k;
    }
    return flip(i);
  }

  public static String join(String paramString, int[] paramArrayOfInt)
  {
    Preconditions.checkNotNull(paramString);
    if (paramArrayOfInt.length == 0)
      return "";
    StringBuilder localStringBuilder = new StringBuilder(paramArrayOfInt.length * 5);
    localStringBuilder.append(toString(paramArrayOfInt[0]));
    for (int i = 1; i < paramArrayOfInt.length; i++)
      localStringBuilder.append(paramString).append(toString(paramArrayOfInt[i]));
    return localStringBuilder.toString();
  }

  public static Comparator lexicographicalComparator()
  {
    return LexicographicalComparator.INSTANCE;
  }

  public static int divide(int paramInt1, int paramInt2)
  {
    return (int)(toLong(paramInt1) / toLong(paramInt2));
  }

  public static int remainder(int paramInt1, int paramInt2)
  {
    return (int)(toLong(paramInt1) % toLong(paramInt2));
  }

  public static int parseUnsignedInt(String paramString)
  {
    return parseUnsignedInt(paramString, 10);
  }

  public static int parseUnsignedInt(String paramString, int paramInt)
  {
    Preconditions.checkNotNull(paramString);
    long l = Long.parseLong(paramString, paramInt);
    if ((l & 0xFFFFFFFF) != l)
      throw new NumberFormatException("Input " + paramString + " in base " + paramInt + " is not in the range of an unsigned integer");
    return (int)l;
  }

  public static String toString(int paramInt)
  {
    return toString(paramInt, 10);
  }

  public static String toString(int paramInt1, int paramInt2)
  {
    long l = paramInt1 & 0xFFFFFFFF;
    return Long.toString(l, paramInt2);
  }

  static enum LexicographicalComparator
    implements Comparator
  {
    INSTANCE;

    public int compare(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
    {
      int i = Math.min(paramArrayOfInt1.length, paramArrayOfInt2.length);
      for (int j = 0; j < i; j++)
        if (paramArrayOfInt1[j] != paramArrayOfInt2[j])
          return UnsignedInts.compare(paramArrayOfInt1[j], paramArrayOfInt2[j]);
      return paramArrayOfInt1.length - paramArrayOfInt2.length;
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.primitives.UnsignedInts
 * JD-Core Version:    0.6.2
 */