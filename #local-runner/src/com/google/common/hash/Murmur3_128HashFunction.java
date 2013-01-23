package com.google.common.hash;

import com.google.common.primitives.UnsignedBytes;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

final class Murmur3_128HashFunction extends AbstractStreamingHashFunction
  implements Serializable
{
  private final int seed;
  private static final long serialVersionUID = 0L;

  Murmur3_128HashFunction(int paramInt)
  {
    this.seed = paramInt;
  }

  public int bits()
  {
    return 128;
  }

  public Hasher newHasher()
  {
    return new Murmur3_128Hasher(this.seed);
  }

  private static final class Murmur3_128Hasher extends AbstractStreamingHashFunction.AbstractStreamingHasher
  {
    long h1;
    long h2;
    long c1 = -8663945395140668459L;
    long c2 = 5545529020109919103L;
    int len;

    Murmur3_128Hasher(int paramInt)
    {
      super();
      this.h1 = paramInt;
      this.h2 = paramInt;
    }

    protected void process(ByteBuffer paramByteBuffer)
    {
      long l1 = paramByteBuffer.getLong();
      long l2 = paramByteBuffer.getLong();
      this.len += 16;
      bmix64(l1, l2);
    }

    private void bmix64(long paramLong1, long paramLong2)
    {
      paramLong1 *= this.c1;
      paramLong1 = Long.rotateLeft(paramLong1, 31);
      paramLong1 *= this.c2;
      this.h1 ^= paramLong1;
      this.h1 = Long.rotateLeft(this.h1, 27);
      this.h1 += this.h2;
      this.h1 = (this.h1 * 5L + 1390208809L);
      paramLong2 *= this.c2;
      paramLong2 = Long.rotateLeft(paramLong2, 33);
      paramLong2 *= this.c1;
      this.h2 ^= paramLong2;
      this.h2 = Long.rotateLeft(this.h2, 31);
      this.h2 += this.h1;
      this.h2 = (this.h2 * 5L + 944331445L);
    }

    protected void processRemaining(ByteBuffer paramByteBuffer)
    {
      long l1 = 0L;
      long l2 = 0L;
      this.len += paramByteBuffer.remaining();
      switch (paramByteBuffer.remaining())
      {
      case 15:
        l2 ^= UnsignedBytes.toInt(paramByteBuffer.get(14)) << 48;
      case 14:
        l2 ^= UnsignedBytes.toInt(paramByteBuffer.get(13)) << 40;
      case 13:
        l2 ^= UnsignedBytes.toInt(paramByteBuffer.get(12)) << 32;
      case 12:
        l2 ^= UnsignedBytes.toInt(paramByteBuffer.get(11)) << 24;
      case 11:
        l2 ^= UnsignedBytes.toInt(paramByteBuffer.get(10)) << 16;
      case 10:
        l2 ^= UnsignedBytes.toInt(paramByteBuffer.get(9)) << 8;
      case 9:
        l2 ^= UnsignedBytes.toInt(paramByteBuffer.get(8)) << 0;
        l2 *= this.c2;
        l2 = Long.rotateLeft(l2, 33);
        l2 *= this.c1;
        this.h2 ^= l2;
      case 8:
        l1 ^= UnsignedBytes.toInt(paramByteBuffer.get(7)) << 56;
      case 7:
        l1 ^= UnsignedBytes.toInt(paramByteBuffer.get(6)) << 48;
      case 6:
        l1 ^= UnsignedBytes.toInt(paramByteBuffer.get(5)) << 40;
      case 5:
        l1 ^= UnsignedBytes.toInt(paramByteBuffer.get(4)) << 32;
      case 4:
        l1 ^= UnsignedBytes.toInt(paramByteBuffer.get(3)) << 24;
      case 3:
        l1 ^= UnsignedBytes.toInt(paramByteBuffer.get(2)) << 16;
      case 2:
        l1 ^= UnsignedBytes.toInt(paramByteBuffer.get(1)) << 8;
      case 1:
        l1 ^= UnsignedBytes.toInt(paramByteBuffer.get(0)) << 0;
        l1 *= this.c1;
        l1 = Long.rotateLeft(l1, 31);
        l1 *= this.c2;
        this.h1 ^= l1;
      }
    }

    public HashCode makeHash()
    {
      this.h1 ^= this.len;
      this.h2 ^= this.len;
      this.h1 += this.h2;
      this.h2 += this.h1;
      this.h1 = fmix64(this.h1);
      this.h2 = fmix64(this.h2);
      this.h1 += this.h2;
      this.h2 += this.h1;
      ByteBuffer localByteBuffer = ByteBuffer.wrap(new byte[16]).order(ByteOrder.LITTLE_ENDIAN);
      localByteBuffer.putLong(this.h1);
      localByteBuffer.putLong(this.h2);
      return HashCodes.fromBytesNoCopy(localByteBuffer.array());
    }

    private long fmix64(long paramLong)
    {
      paramLong ^= paramLong >>> 33;
      paramLong *= -49064778989728563L;
      paramLong ^= paramLong >>> 33;
      paramLong *= -4265267296055464877L;
      paramLong ^= paramLong >>> 33;
      return paramLong;
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.hash.Murmur3_128HashFunction
 * JD-Core Version:    0.6.2
 */