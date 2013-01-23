package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

abstract class AbstractNonStreamingHashFunction
  implements HashFunction
{
  public Hasher newHasher()
  {
    return new BufferingHasher(32);
  }

  public Hasher newHasher(int paramInt)
  {
    Preconditions.checkArgument(paramInt >= 0);
    return new BufferingHasher(paramInt);
  }

  private static final class ExposedByteArrayOutputStream extends ByteArrayOutputStream
  {
    ExposedByteArrayOutputStream(int paramInt)
    {
      super();
    }

    byte[] byteArray()
    {
      return this.buf;
    }

    int length()
    {
      return this.count;
    }
  }

  private final class BufferingHasher extends AbstractHasher
  {
    final AbstractNonStreamingHashFunction.ExposedByteArrayOutputStream stream;
    static final int BOTTOM_BYTE = 255;

    BufferingHasher(int arg2)
    {
      int i;
      this.stream = new AbstractNonStreamingHashFunction.ExposedByteArrayOutputStream(i);
    }

    public Hasher putByte(byte paramByte)
    {
      this.stream.write(paramByte);
      return this;
    }

    public Hasher putBytes(byte[] paramArrayOfByte)
    {
      try
      {
        this.stream.write(paramArrayOfByte);
      }
      catch (IOException localIOException)
      {
        throw Throwables.propagate(localIOException);
      }
      return this;
    }

    public Hasher putBytes(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    {
      this.stream.write(paramArrayOfByte, paramInt1, paramInt2);
      return this;
    }

    public Hasher putShort(short paramShort)
    {
      this.stream.write(paramShort & 0xFF);
      this.stream.write(paramShort >>> 8 & 0xFF);
      return this;
    }

    public Hasher putInt(int paramInt)
    {
      this.stream.write(paramInt & 0xFF);
      this.stream.write(paramInt >>> 8 & 0xFF);
      this.stream.write(paramInt >>> 16 & 0xFF);
      this.stream.write(paramInt >>> 24 & 0xFF);
      return this;
    }

    public Hasher putLong(long paramLong)
    {
      for (int i = 0; i < 64; i += 8)
        this.stream.write((byte)(int)(paramLong >>> i & 0xFF));
      return this;
    }

    public Hasher putChar(char paramChar)
    {
      this.stream.write(paramChar & 0xFF);
      this.stream.write(paramChar >>> '\b' & 0xFF);
      return this;
    }

    public Hasher putObject(Object paramObject, Funnel paramFunnel)
    {
      paramFunnel.funnel(paramObject, this);
      return this;
    }

    public HashCode hash()
    {
      return AbstractNonStreamingHashFunction.this.hashBytes(this.stream.byteArray(), 0, this.stream.length());
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.hash.AbstractNonStreamingHashFunction
 * JD-Core Version:    0.6.2
 */