package com.google.common.hash;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import java.io.Serializable;

@Beta
public final class HashCodes
{
  public static HashCode fromInt(int paramInt)
  {
    return new IntHashCode(paramInt);
  }

  public static HashCode fromLong(long paramLong)
  {
    return new LongHashCode(paramLong);
  }

  public static HashCode fromBytes(byte[] paramArrayOfByte)
  {
    Preconditions.checkArgument(paramArrayOfByte.length >= 4, "A HashCode must contain at least 4 bytes.");
    return fromBytesNoCopy((byte[])paramArrayOfByte.clone());
  }

  static HashCode fromBytesNoCopy(byte[] paramArrayOfByte)
  {
    return new BytesHashCode(paramArrayOfByte);
  }

  private static final class BytesHashCode extends HashCode
    implements Serializable
  {
    final byte[] bytes;
    private static final long serialVersionUID = 0L;

    BytesHashCode(byte[] paramArrayOfByte)
    {
      this.bytes = paramArrayOfByte;
    }

    public int bits()
    {
      return this.bytes.length * 8;
    }

    public byte[] asBytes()
    {
      return (byte[])this.bytes.clone();
    }

    public int asInt()
    {
      return this.bytes[0] & 0xFF | (this.bytes[1] & 0xFF) << 8 | (this.bytes[2] & 0xFF) << 16 | (this.bytes[3] & 0xFF) << 24;
    }

    public long asLong()
    {
      if (this.bytes.length < 8)
        throw new IllegalStateException("Not enough bytes");
      return this.bytes[0] & 0xFF | (this.bytes[1] & 0xFF) << 8 | (this.bytes[2] & 0xFF) << 16 | (this.bytes[3] & 0xFF) << 24 | (this.bytes[4] & 0xFF) << 32 | (this.bytes[5] & 0xFF) << 40 | (this.bytes[6] & 0xFF) << 48 | (this.bytes[7] & 0xFF) << 56;
    }
  }

  private static final class LongHashCode extends HashCode
    implements Serializable
  {
    final long hash;
    private static final long serialVersionUID = 0L;

    LongHashCode(long paramLong)
    {
      this.hash = paramLong;
    }

    public int bits()
    {
      return 64;
    }

    public byte[] asBytes()
    {
      return new byte[] { (byte)(int)this.hash, (byte)(int)(this.hash >> 8), (byte)(int)(this.hash >> 16), (byte)(int)(this.hash >> 24), (byte)(int)(this.hash >> 32), (byte)(int)(this.hash >> 40), (byte)(int)(this.hash >> 48), (byte)(int)(this.hash >> 56) };
    }

    public int asInt()
    {
      return (int)this.hash;
    }

    public long asLong()
    {
      return this.hash;
    }
  }

  private static final class IntHashCode extends HashCode
    implements Serializable
  {
    final int hash;
    private static final long serialVersionUID = 0L;

    IntHashCode(int paramInt)
    {
      this.hash = paramInt;
    }

    public int bits()
    {
      return 32;
    }

    public byte[] asBytes()
    {
      return new byte[] { (byte)this.hash, (byte)(this.hash >> 8), (byte)(this.hash >> 16), (byte)(this.hash >> 24) };
    }

    public int asInt()
    {
      return this.hash;
    }

    public long asLong()
    {
      throw new IllegalStateException("this HashCode only has 32 bits; cannot create a long");
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.hash.HashCodes
 * JD-Core Version:    0.6.2
 */