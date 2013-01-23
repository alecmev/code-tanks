package com.google.common.hash;

import com.google.common.primitives.UnsignedBytes;
import java.io.Serializable;
import java.nio.ByteBuffer;

final class Murmur3_32HashFunction extends AbstractStreamingHashFunction
  implements Serializable
{
  private final int seed;
  private static final long serialVersionUID = 0L;

  Murmur3_32HashFunction(int paramInt)
  {
    this.seed = paramInt;
  }

  public int bits()
  {
    return 32;
  }

  public Hasher newHasher()
  {
    return new Murmur3_32Hasher(this.seed);
  }

  private static final class Murmur3_32Hasher extends AbstractStreamingHashFunction.AbstractStreamingHasher
  {
    int h1;
    int c1 = -862048943;
    int c2 = 461845907;
    int len;

    Murmur3_32Hasher(int paramInt)
    {
      super();
      this.h1 = paramInt;
    }

    protected void process(ByteBuffer paramByteBuffer)
    {
      int i = paramByteBuffer.getInt();
      this.len += 4;
      i *= this.c1;
      i = Integer.rotateLeft(i, 15);
      i *= this.c2;
      this.h1 ^= i;
      this.h1 = Integer.rotateLeft(this.h1, 13);
      this.h1 = (this.h1 * 5 + -430675100);
    }

    protected void processRemaining(ByteBuffer paramByteBuffer)
    {
      this.len += paramByteBuffer.remaining();
      int i = 0;
      switch (paramByteBuffer.remaining())
      {
      case 3:
        i ^= UnsignedBytes.toInt(paramByteBuffer.get(2)) << 16;
      case 2:
        i ^= UnsignedBytes.toInt(paramByteBuffer.get(1)) << 8;
      case 1:
        i ^= UnsignedBytes.toInt(paramByteBuffer.get(0));
      }
      i *= this.c1;
      i = Integer.rotateLeft(i, 15);
      i *= this.c2;
      this.h1 ^= i;
    }

    public HashCode makeHash()
    {
      this.h1 ^= this.len;
      this.h1 ^= this.h1 >>> 16;
      this.h1 *= -2048144789;
      this.h1 ^= this.h1 >>> 13;
      this.h1 *= -1028477387;
      this.h1 ^= this.h1 >>> 16;
      return HashCodes.fromInt(this.h1);
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.hash.Murmur3_32HashFunction
 * JD-Core Version:    0.6.2
 */