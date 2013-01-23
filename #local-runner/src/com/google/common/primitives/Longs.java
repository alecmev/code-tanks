package com.google.common.primitives;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.RandomAccess;

@GwtCompatible(emulated=true)
public final class Longs
{
  public static final int BYTES = 8;
  public static final long MAX_POWER_OF_TWO = 4611686018427387904L;

  public static int hashCode(long paramLong)
  {
    return (int)(paramLong ^ paramLong >>> 32);
  }

  public static int compare(long paramLong1, long paramLong2)
  {
    return paramLong1 > paramLong2 ? 1 : paramLong1 < paramLong2 ? -1 : 0;
  }

  public static boolean contains(long[] paramArrayOfLong, long paramLong)
  {
    for (long l : paramArrayOfLong)
      if (l == paramLong)
        return true;
    return false;
  }

  public static int indexOf(long[] paramArrayOfLong, long paramLong)
  {
    return indexOf(paramArrayOfLong, paramLong, 0, paramArrayOfLong.length);
  }

  private static int indexOf(long[] paramArrayOfLong, long paramLong, int paramInt1, int paramInt2)
  {
    for (int i = paramInt1; i < paramInt2; i++)
      if (paramArrayOfLong[i] == paramLong)
        return i;
    return -1;
  }

  public static int indexOf(long[] paramArrayOfLong1, long[] paramArrayOfLong2)
  {
    Preconditions.checkNotNull(paramArrayOfLong1, "array");
    Preconditions.checkNotNull(paramArrayOfLong2, "target");
    if (paramArrayOfLong2.length == 0)
      return 0;
    label65: for (int i = 0; i < paramArrayOfLong1.length - paramArrayOfLong2.length + 1; i++)
    {
      for (int j = 0; j < paramArrayOfLong2.length; j++)
        if (paramArrayOfLong1[(i + j)] != paramArrayOfLong2[j])
          break label65;
      return i;
    }
    return -1;
  }

  public static int lastIndexOf(long[] paramArrayOfLong, long paramLong)
  {
    return lastIndexOf(paramArrayOfLong, paramLong, 0, paramArrayOfLong.length);
  }

  private static int lastIndexOf(long[] paramArrayOfLong, long paramLong, int paramInt1, int paramInt2)
  {
    for (int i = paramInt2 - 1; i >= paramInt1; i--)
      if (paramArrayOfLong[i] == paramLong)
        return i;
    return -1;
  }

  public static long min(long[] paramArrayOfLong)
  {
    Preconditions.checkArgument(paramArrayOfLong.length > 0);
    long l = paramArrayOfLong[0];
    for (int i = 1; i < paramArrayOfLong.length; i++)
      if (paramArrayOfLong[i] < l)
        l = paramArrayOfLong[i];
    return l;
  }

  public static long max(long[] paramArrayOfLong)
  {
    Preconditions.checkArgument(paramArrayOfLong.length > 0);
    long l = paramArrayOfLong[0];
    for (int i = 1; i < paramArrayOfLong.length; i++)
      if (paramArrayOfLong[i] > l)
        l = paramArrayOfLong[i];
    return l;
  }

  public static long[] concat(long[][] paramArrayOfLong)
  {
    int i = 0;
    for (Object localObject2 : paramArrayOfLong)
      i += localObject2.length;
    ??? = new long[i];
    ??? = 0;
    for (long[] arrayOfLong1 : paramArrayOfLong)
    {
      System.arraycopy(arrayOfLong1, 0, ???, ???, arrayOfLong1.length);
      ??? += arrayOfLong1.length;
    }
    return ???;
  }

  @GwtIncompatible("doesn't work")
  public static byte[] toByteArray(long paramLong)
  {
    return new byte[] { (byte)(int)(paramLong >> 56), (byte)(int)(paramLong >> 48), (byte)(int)(paramLong >> 40), (byte)(int)(paramLong >> 32), (byte)(int)(paramLong >> 24), (byte)(int)(paramLong >> 16), (byte)(int)(paramLong >> 8), (byte)(int)paramLong };
  }

  @GwtIncompatible("doesn't work")
  public static long fromByteArray(byte[] paramArrayOfByte)
  {
    Preconditions.checkArgument(paramArrayOfByte.length >= 8, "array too small: %s < %s", new Object[] { Integer.valueOf(paramArrayOfByte.length), Integer.valueOf(8) });
    return fromBytes(paramArrayOfByte[0], paramArrayOfByte[1], paramArrayOfByte[2], paramArrayOfByte[3], paramArrayOfByte[4], paramArrayOfByte[5], paramArrayOfByte[6], paramArrayOfByte[7]);
  }

  @GwtIncompatible("doesn't work")
  public static long fromBytes(byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4, byte paramByte5, byte paramByte6, byte paramByte7, byte paramByte8)
  {
    return (paramByte1 & 0xFF) << 56 | (paramByte2 & 0xFF) << 48 | (paramByte3 & 0xFF) << 40 | (paramByte4 & 0xFF) << 32 | (paramByte5 & 0xFF) << 24 | (paramByte6 & 0xFF) << 16 | (paramByte7 & 0xFF) << 8 | paramByte8 & 0xFF;
  }

  public static long[] ensureCapacity(long[] paramArrayOfLong, int paramInt1, int paramInt2)
  {
    Preconditions.checkArgument(paramInt1 >= 0, "Invalid minLength: %s", new Object[] { Integer.valueOf(paramInt1) });
    Preconditions.checkArgument(paramInt2 >= 0, "Invalid padding: %s", new Object[] { Integer.valueOf(paramInt2) });
    return paramArrayOfLong.length < paramInt1 ? copyOf(paramArrayOfLong, paramInt1 + paramInt2) : paramArrayOfLong;
  }

  private static long[] copyOf(long[] paramArrayOfLong, int paramInt)
  {
    long[] arrayOfLong = new long[paramInt];
    System.arraycopy(paramArrayOfLong, 0, arrayOfLong, 0, Math.min(paramArrayOfLong.length, paramInt));
    return arrayOfLong;
  }

  public static String join(String paramString, long[] paramArrayOfLong)
  {
    Preconditions.checkNotNull(paramString);
    if (paramArrayOfLong.length == 0)
      return "";
    StringBuilder localStringBuilder = new StringBuilder(paramArrayOfLong.length * 10);
    localStringBuilder.append(paramArrayOfLong[0]);
    for (int i = 1; i < paramArrayOfLong.length; i++)
      localStringBuilder.append(paramString).append(paramArrayOfLong[i]);
    return localStringBuilder.toString();
  }

  public static Comparator lexicographicalComparator()
  {
    return LexicographicalComparator.INSTANCE;
  }

  public static long[] toArray(Collection paramCollection)
  {
    if ((paramCollection instanceof LongArrayAsList))
      return ((LongArrayAsList)paramCollection).toLongArray();
    Object[] arrayOfObject = paramCollection.toArray();
    int i = arrayOfObject.length;
    long[] arrayOfLong = new long[i];
    for (int j = 0; j < i; j++)
      arrayOfLong[j] = ((Number)Preconditions.checkNotNull(arrayOfObject[j])).longValue();
    return arrayOfLong;
  }

  public static List asList(long[] paramArrayOfLong)
  {
    if (paramArrayOfLong.length == 0)
      return Collections.emptyList();
    return new LongArrayAsList(paramArrayOfLong);
  }

  @GwtCompatible
  private static class LongArrayAsList extends AbstractList
    implements Serializable, RandomAccess
  {
    final long[] array;
    final int start;
    final int end;
    private static final long serialVersionUID = 0L;

    LongArrayAsList(long[] paramArrayOfLong)
    {
      this(paramArrayOfLong, 0, paramArrayOfLong.length);
    }

    LongArrayAsList(long[] paramArrayOfLong, int paramInt1, int paramInt2)
    {
      this.array = paramArrayOfLong;
      this.start = paramInt1;
      this.end = paramInt2;
    }

    public int size()
    {
      return this.end - this.start;
    }

    public boolean isEmpty()
    {
      return false;
    }

    public Long get(int paramInt)
    {
      Preconditions.checkElementIndex(paramInt, size());
      return Long.valueOf(this.array[(this.start + paramInt)]);
    }

    public boolean contains(Object paramObject)
    {
      return ((paramObject instanceof Long)) && (Longs.indexOf(this.array, ((Long)paramObject).longValue(), this.start, this.end) != -1);
    }

    public int indexOf(Object paramObject)
    {
      if ((paramObject instanceof Long))
      {
        int i = Longs.indexOf(this.array, ((Long)paramObject).longValue(), this.start, this.end);
        if (i >= 0)
          return i - this.start;
      }
      return -1;
    }

    public int lastIndexOf(Object paramObject)
    {
      if ((paramObject instanceof Long))
      {
        int i = Longs.lastIndexOf(this.array, ((Long)paramObject).longValue(), this.start, this.end);
        if (i >= 0)
          return i - this.start;
      }
      return -1;
    }

    public Long set(int paramInt, Long paramLong)
    {
      Preconditions.checkElementIndex(paramInt, size());
      long l = this.array[(this.start + paramInt)];
      this.array[(this.start + paramInt)] = ((Long)Preconditions.checkNotNull(paramLong)).longValue();
      return Long.valueOf(l);
    }

    public List subList(int paramInt1, int paramInt2)
    {
      int i = size();
      Preconditions.checkPositionIndexes(paramInt1, paramInt2, i);
      if (paramInt1 == paramInt2)
        return Collections.emptyList();
      return new LongArrayAsList(this.array, this.start + paramInt1, this.start + paramInt2);
    }

    public boolean equals(Object paramObject)
    {
      if (paramObject == this)
        return true;
      if ((paramObject instanceof LongArrayAsList))
      {
        LongArrayAsList localLongArrayAsList = (LongArrayAsList)paramObject;
        int i = size();
        if (localLongArrayAsList.size() != i)
          return false;
        for (int j = 0; j < i; j++)
          if (this.array[(this.start + j)] != localLongArrayAsList.array[(localLongArrayAsList.start + j)])
            return false;
        return true;
      }
      return super.equals(paramObject);
    }

    public int hashCode()
    {
      int i = 1;
      for (int j = this.start; j < this.end; j++)
        i = 31 * i + Longs.hashCode(this.array[j]);
      return i;
    }

    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder(size() * 10);
      localStringBuilder.append('[').append(this.array[this.start]);
      for (int i = this.start + 1; i < this.end; i++)
        localStringBuilder.append(", ").append(this.array[i]);
      return ']';
    }

    long[] toLongArray()
    {
      int i = size();
      long[] arrayOfLong = new long[i];
      System.arraycopy(this.array, this.start, arrayOfLong, 0, i);
      return arrayOfLong;
    }
  }

  private static enum LexicographicalComparator
    implements Comparator
  {
    INSTANCE;

    public int compare(long[] paramArrayOfLong1, long[] paramArrayOfLong2)
    {
      int i = Math.min(paramArrayOfLong1.length, paramArrayOfLong2.length);
      for (int j = 0; j < i; j++)
      {
        int k = Longs.compare(paramArrayOfLong1[j], paramArrayOfLong2[j]);
        if (k != 0)
          return k;
      }
      return paramArrayOfLong1.length - paramArrayOfLong2.length;
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.primitives.Longs
 * JD-Core Version:    0.6.2
 */