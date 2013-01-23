package com.google.common.primitives;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.lang.reflect.Field;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Comparator;
import sun.misc.Unsafe;

public final class UnsignedBytes
{
  public static final byte MAX_POWER_OF_TWO = -128;

  public static int toInt(byte paramByte)
  {
    return paramByte & 0xFF;
  }

  public static byte checkedCast(long paramLong)
  {
    Preconditions.checkArgument(paramLong >> 8 == 0L, "out of range: %s", new Object[] { Long.valueOf(paramLong) });
    return (byte)(int)paramLong;
  }

  public static byte saturatedCast(long paramLong)
  {
    if (paramLong > 255L)
      return -1;
    if (paramLong < 0L)
      return 0;
    return (byte)(int)paramLong;
  }

  public static int compare(byte paramByte1, byte paramByte2)
  {
    return toInt(paramByte1) - toInt(paramByte2);
  }

  public static byte min(byte[] paramArrayOfByte)
  {
    Preconditions.checkArgument(paramArrayOfByte.length > 0);
    int i = toInt(paramArrayOfByte[0]);
    for (int j = 1; j < paramArrayOfByte.length; j++)
    {
      int k = toInt(paramArrayOfByte[j]);
      if (k < i)
        i = k;
    }
    return (byte)i;
  }

  public static byte max(byte[] paramArrayOfByte)
  {
    Preconditions.checkArgument(paramArrayOfByte.length > 0);
    int i = toInt(paramArrayOfByte[0]);
    for (int j = 1; j < paramArrayOfByte.length; j++)
    {
      int k = toInt(paramArrayOfByte[j]);
      if (k > i)
        i = k;
    }
    return (byte)i;
  }

  public static String join(String paramString, byte[] paramArrayOfByte)
  {
    Preconditions.checkNotNull(paramString);
    if (paramArrayOfByte.length == 0)
      return "";
    StringBuilder localStringBuilder = new StringBuilder(paramArrayOfByte.length * 5);
    localStringBuilder.append(toInt(paramArrayOfByte[0]));
    for (int i = 1; i < paramArrayOfByte.length; i++)
      localStringBuilder.append(paramString).append(toInt(paramArrayOfByte[i]));
    return localStringBuilder.toString();
  }

  public static Comparator lexicographicalComparator()
  {
    return LexicographicalComparatorHolder.BEST_COMPARATOR;
  }

  @VisibleForTesting
  static Comparator lexicographicalComparatorJavaImpl()
  {
    return UnsignedBytes.LexicographicalComparatorHolder.PureJavaComparator.INSTANCE;
  }

  @VisibleForTesting
  static class LexicographicalComparatorHolder
  {
    static final String UNSAFE_COMPARATOR_NAME = LexicographicalComparatorHolder.class.getName() + "$UnsafeComparator";
    static final Comparator BEST_COMPARATOR = getBestComparator();

    static Comparator getBestComparator()
    {
      try
      {
        Class localClass = Class.forName(UNSAFE_COMPARATOR_NAME);
        Comparator localComparator = (Comparator)localClass.getEnumConstants()[0];
        return localComparator;
      }
      catch (Throwable localThrowable)
      {
      }
      return UnsignedBytes.lexicographicalComparatorJavaImpl();
    }

    static enum PureJavaComparator
      implements Comparator
    {
      INSTANCE;

      public int compare(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
      {
        int i = Math.min(paramArrayOfByte1.length, paramArrayOfByte2.length);
        for (int j = 0; j < i; j++)
        {
          int k = UnsignedBytes.compare(paramArrayOfByte1[j], paramArrayOfByte2[j]);
          if (k != 0)
            return k;
        }
        return paramArrayOfByte1.length - paramArrayOfByte2.length;
      }
    }

    @VisibleForTesting
    static enum UnsafeComparator
      implements Comparator
    {
      INSTANCE;

      static final boolean littleEndian;
      static final Unsafe theUnsafe;
      static final int BYTE_ARRAY_BASE_OFFSET;

      public int compare(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
      {
        int i = Math.min(paramArrayOfByte1.length, paramArrayOfByte2.length);
        int j = i / 8;
        for (int k = 0; k < j * 8; k += 8)
        {
          long l1 = theUnsafe.getLong(paramArrayOfByte1, BYTE_ARRAY_BASE_OFFSET + k);
          long l2 = theUnsafe.getLong(paramArrayOfByte2, BYTE_ARRAY_BASE_OFFSET + k);
          long l3 = l1 ^ l2;
          if (l3 != 0L)
          {
            if (!littleEndian)
              return UnsignedLongs.compare(l1, l2);
            int n = 0;
            int i2 = (int)l3;
            if (i2 == 0)
            {
              i2 = (int)(l3 >>> 32);
              n = 32;
            }
            int i1 = i2 << 16;
            if (i1 == 0)
              n += 16;
            else
              i2 = i1;
            i1 = i2 << 8;
            if (i1 == 0)
              n += 8;
            return (int)((l1 >>> n & 0xFF) - (l2 >>> n & 0xFF));
          }
        }
        for (k = j * 8; k < i; k++)
        {
          int m = UnsignedBytes.compare(paramArrayOfByte1[k], paramArrayOfByte2[k]);
          if (m != 0)
            return m;
        }
        return paramArrayOfByte1.length - paramArrayOfByte2.length;
      }

      static
      {
        littleEndian = ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN);
        theUnsafe = (Unsafe)AccessController.doPrivileged(new PrivilegedAction()
        {
          public Object run()
          {
            try
            {
              Field localField = Unsafe.class.getDeclaredField("theUnsafe");
              localField.setAccessible(true);
              return localField.get(null);
            }
            catch (NoSuchFieldException localNoSuchFieldException)
            {
              throw new Error();
            }
            catch (IllegalAccessException localIllegalAccessException)
            {
            }
            throw new Error();
          }
        });
        BYTE_ARRAY_BASE_OFFSET = theUnsafe.arrayBaseOffset([B.class);
        if (theUnsafe.arrayIndexScale([B.class) != 1)
          throw new AssertionError();
      }
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.primitives.UnsignedBytes
 * JD-Core Version:    0.6.2
 */