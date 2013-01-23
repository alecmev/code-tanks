package com.google.common.hash;

import com.google.common.annotations.Beta;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.primitives.UnsignedInts;
import java.nio.ByteBuffer;
import java.util.Iterator;

@Beta
public final class Hashing
{
  private static final int GOOD_FAST_HASH_SEED = (int)System.currentTimeMillis();
  private static final Murmur3_32HashFunction MURMUR3_32 = new Murmur3_32HashFunction(0);
  private static final Murmur3_128HashFunction MURMUR3_128 = new Murmur3_128HashFunction(0);
  private static final HashFunction MD5 = new MessageDigestHashFunction("MD5");
  private static final HashFunction SHA_1 = new MessageDigestHashFunction("SHA-1");
  private static final HashFunction SHA_256 = new MessageDigestHashFunction("SHA-256");
  private static final HashFunction SHA_512 = new MessageDigestHashFunction("SHA-512");

  public static HashFunction goodFastHash(int paramInt)
  {
    int i = checkPositiveAndMakeMultipleOf32(paramInt);
    if (i == 32)
      return murmur3_32(GOOD_FAST_HASH_SEED);
    if (i <= 128)
      return murmur3_128(GOOD_FAST_HASH_SEED);
    int j = (i + 127) / 128;
    HashFunction[] arrayOfHashFunction = new HashFunction[j];
    int k = GOOD_FAST_HASH_SEED;
    for (int m = 0; m < j; m++)
    {
      arrayOfHashFunction[m] = murmur3_128(k);
      k += 1500450271;
    }
    return new ConcatenatedHashFunction(arrayOfHashFunction);
  }

  public static HashFunction murmur3_32(int paramInt)
  {
    return new Murmur3_32HashFunction(paramInt);
  }

  public static HashFunction murmur3_32()
  {
    return MURMUR3_32;
  }

  public static HashFunction murmur3_128(int paramInt)
  {
    return new Murmur3_128HashFunction(paramInt);
  }

  public static HashFunction murmur3_128()
  {
    return MURMUR3_128;
  }

  public static HashFunction md5()
  {
    return MD5;
  }

  public static HashFunction sha1()
  {
    return SHA_1;
  }

  public static HashFunction sha256()
  {
    return SHA_256;
  }

  public static HashFunction sha512()
  {
    return SHA_512;
  }

  public static long padToLong(HashCode paramHashCode)
  {
    return paramHashCode.bits() < 64 ? UnsignedInts.toLong(paramHashCode.asInt()) : paramHashCode.asLong();
  }

  public static int consistentHash(HashCode paramHashCode, int paramInt)
  {
    return consistentHash(padToLong(paramHashCode), paramInt);
  }

  public static int consistentHash(long paramLong, int paramInt)
  {
    Preconditions.checkArgument(paramInt > 0, "buckets must be positive: %s", new Object[] { Integer.valueOf(paramInt) });
    long l = paramLong;
    int i = 0;
    while (true)
    {
      l = 2862933555777941757L * l + 1L;
      double d = 2147483648.0D / ((int)(l >>> 33) + 1);
      int j = (int)((i + 1) * d);
      if ((j >= 0) && (j < paramInt))
        i = j;
      else
        return i;
    }
  }

  public static HashCode combineOrdered(Iterable paramIterable)
  {
    Iterator localIterator1 = paramIterable.iterator();
    Preconditions.checkArgument(localIterator1.hasNext(), "Must be at least 1 hash code to combine.");
    int i = ((HashCode)localIterator1.next()).bits();
    byte[] arrayOfByte1 = new byte[i / 8];
    Iterator localIterator2 = paramIterable.iterator();
    while (localIterator2.hasNext())
    {
      HashCode localHashCode = (HashCode)localIterator2.next();
      byte[] arrayOfByte2 = localHashCode.asBytes();
      Preconditions.checkArgument(arrayOfByte2.length == arrayOfByte1.length, "All hashcodes must have the same bit length.");
      for (int j = 0; j < arrayOfByte2.length; j++)
        arrayOfByte1[j] = ((byte)(arrayOfByte1[j] * 37 ^ arrayOfByte2[j]));
    }
    return HashCodes.fromBytesNoCopy(arrayOfByte1);
  }

  public static HashCode combineUnordered(Iterable paramIterable)
  {
    Iterator localIterator1 = paramIterable.iterator();
    Preconditions.checkArgument(localIterator1.hasNext(), "Must be at least 1 hash code to combine.");
    byte[] arrayOfByte1 = new byte[((HashCode)localIterator1.next()).bits() / 8];
    Iterator localIterator2 = paramIterable.iterator();
    while (localIterator2.hasNext())
    {
      HashCode localHashCode = (HashCode)localIterator2.next();
      byte[] arrayOfByte2 = localHashCode.asBytes();
      Preconditions.checkArgument(arrayOfByte2.length == arrayOfByte1.length, "All hashcodes must have the same bit length.");
      for (int i = 0; i < arrayOfByte2.length; i++)
      {
        int tmp102_100 = i;
        byte[] tmp102_99 = arrayOfByte1;
        tmp102_99[tmp102_100] = ((byte)(tmp102_99[tmp102_100] + arrayOfByte2[i]));
      }
    }
    return HashCodes.fromBytesNoCopy(arrayOfByte1);
  }

  static int checkPositiveAndMakeMultipleOf32(int paramInt)
  {
    Preconditions.checkArgument(paramInt > 0, "Number of bits must be positive");
    return paramInt + 31 & 0xFFFFFFE0;
  }

  @VisibleForTesting
  static final class ConcatenatedHashFunction extends AbstractCompositeHashFunction
  {
    private final int bits;

    ConcatenatedHashFunction(HashFunction[] paramArrayOfHashFunction)
    {
      super();
      int i = 0;
      for (HashFunction localHashFunction : paramArrayOfHashFunction)
        i += localHashFunction.bits();
      this.bits = i;
    }

    HashCode makeHash(Hasher[] paramArrayOfHasher)
    {
      byte[] arrayOfByte = new byte[this.bits / 8];
      ByteBuffer localByteBuffer = ByteBuffer.wrap(arrayOfByte);
      for (Hasher localHasher : paramArrayOfHasher)
        localByteBuffer.put(localHasher.hash().asBytes());
      return HashCodes.fromBytesNoCopy(arrayOfByte);
    }

    public int bits()
    {
      return this.bits;
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.hash.Hashing
 * JD-Core Version:    0.6.2
 */