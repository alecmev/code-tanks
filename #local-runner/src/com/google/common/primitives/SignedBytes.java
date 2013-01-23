package com.google.common.primitives;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.Comparator;

@GwtCompatible
public final class SignedBytes
{
  public static final byte MAX_POWER_OF_TWO = 64;

  public static byte checkedCast(long paramLong)
  {
    byte b = (byte)(int)paramLong;
    Preconditions.checkArgument(b == paramLong, "Out of range: %s", new Object[] { Long.valueOf(paramLong) });
    return b;
  }

  public static byte saturatedCast(long paramLong)
  {
    if (paramLong > 127L)
      return 127;
    if (paramLong < -128L)
      return -128;
    return (byte)(int)paramLong;
  }

  public static int compare(byte paramByte1, byte paramByte2)
  {
    return paramByte1 - paramByte2;
  }

  public static byte min(byte[] paramArrayOfByte)
  {
    Preconditions.checkArgument(paramArrayOfByte.length > 0);
    byte b = paramArrayOfByte[0];
    for (int i = 1; i < paramArrayOfByte.length; i++)
      if (paramArrayOfByte[i] < b)
        b = paramArrayOfByte[i];
    return b;
  }

  public static byte max(byte[] paramArrayOfByte)
  {
    Preconditions.checkArgument(paramArrayOfByte.length > 0);
    byte b = paramArrayOfByte[0];
    for (int i = 1; i < paramArrayOfByte.length; i++)
      if (paramArrayOfByte[i] > b)
        b = paramArrayOfByte[i];
    return b;
  }

  public static String join(String paramString, byte[] paramArrayOfByte)
  {
    Preconditions.checkNotNull(paramString);
    if (paramArrayOfByte.length == 0)
      return "";
    StringBuilder localStringBuilder = new StringBuilder(paramArrayOfByte.length * 5);
    localStringBuilder.append(paramArrayOfByte[0]);
    for (int i = 1; i < paramArrayOfByte.length; i++)
      localStringBuilder.append(paramString).append(paramArrayOfByte[i]);
    return localStringBuilder.toString();
  }

  public static Comparator lexicographicalComparator()
  {
    return LexicographicalComparator.INSTANCE;
  }

  private static enum LexicographicalComparator
    implements Comparator
  {
    INSTANCE;

    public int compare(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
    {
      int i = Math.min(paramArrayOfByte1.length, paramArrayOfByte2.length);
      for (int j = 0; j < i; j++)
      {
        int k = SignedBytes.compare(paramArrayOfByte1[j], paramArrayOfByte2[j]);
        if (k != 0)
          return k;
      }
      return paramArrayOfByte1.length - paramArrayOfByte2.length;
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.primitives.SignedBytes
 * JD-Core Version:    0.6.2
 */