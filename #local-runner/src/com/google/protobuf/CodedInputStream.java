package com.google.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class CodedInputStream
{
  private final byte[] buffer;
  private int bufferSize;
  private int bufferSizeAfterLimit;
  private int bufferPos;
  private final InputStream input;
  private int lastTag;
  private int totalBytesRetired;
  private int currentLimit = 2147483647;
  private int recursionDepth;
  private int recursionLimit = 64;
  private int sizeLimit = 67108864;
  private static final int DEFAULT_RECURSION_LIMIT = 64;
  private static final int DEFAULT_SIZE_LIMIT = 67108864;
  private static final int BUFFER_SIZE = 4096;

  public static CodedInputStream newInstance(InputStream paramInputStream)
  {
    return new CodedInputStream(paramInputStream);
  }

  public static CodedInputStream newInstance(byte[] paramArrayOfByte)
  {
    return newInstance(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public static CodedInputStream newInstance(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    CodedInputStream localCodedInputStream = new CodedInputStream(paramArrayOfByte, paramInt1, paramInt2);
    try
    {
      localCodedInputStream.pushLimit(paramInt2);
    }
    catch (InvalidProtocolBufferException localInvalidProtocolBufferException)
    {
      throw new IllegalArgumentException(localInvalidProtocolBufferException);
    }
    return localCodedInputStream;
  }

  public int readTag()
    throws IOException
  {
    if (isAtEnd())
    {
      this.lastTag = 0;
      return 0;
    }
    this.lastTag = readRawVarint32();
    if (WireFormat.getTagFieldNumber(this.lastTag) == 0)
      throw InvalidProtocolBufferException.invalidTag();
    return this.lastTag;
  }

  public void checkLastTagWas(int paramInt)
    throws InvalidProtocolBufferException
  {
    if (this.lastTag != paramInt)
      throw InvalidProtocolBufferException.invalidEndTag();
  }

  public boolean skipField(int paramInt)
    throws IOException
  {
    switch (WireFormat.getTagWireType(paramInt))
    {
    case 0:
      readInt32();
      return true;
    case 1:
      readRawLittleEndian64();
      return true;
    case 2:
      skipRawBytes(readRawVarint32());
      return true;
    case 3:
      skipMessage();
      checkLastTagWas(WireFormat.makeTag(WireFormat.getTagFieldNumber(paramInt), 4));
      return true;
    case 4:
      return false;
    case 5:
      readRawLittleEndian32();
      return true;
    }
    throw InvalidProtocolBufferException.invalidWireType();
  }

  public void skipMessage()
    throws IOException
  {
    while (true)
    {
      int i = readTag();
      if ((i == 0) || (!skipField(i)))
        return;
    }
  }

  public double readDouble()
    throws IOException
  {
    return Double.longBitsToDouble(readRawLittleEndian64());
  }

  public float readFloat()
    throws IOException
  {
    return Float.intBitsToFloat(readRawLittleEndian32());
  }

  public long readUInt64()
    throws IOException
  {
    return readRawVarint64();
  }

  public long readInt64()
    throws IOException
  {
    return readRawVarint64();
  }

  public int readInt32()
    throws IOException
  {
    return readRawVarint32();
  }

  public long readFixed64()
    throws IOException
  {
    return readRawLittleEndian64();
  }

  public int readFixed32()
    throws IOException
  {
    return readRawLittleEndian32();
  }

  public boolean readBool()
    throws IOException
  {
    return readRawVarint32() != 0;
  }

  public String readString()
    throws IOException
  {
    int i = readRawVarint32();
    if ((i <= this.bufferSize - this.bufferPos) && (i > 0))
    {
      String str = new String(this.buffer, this.bufferPos, i, "UTF-8");
      this.bufferPos += i;
      return str;
    }
    return new String(readRawBytes(i), "UTF-8");
  }

  public void readGroup(int paramInt, MessageLite.Builder paramBuilder, ExtensionRegistryLite paramExtensionRegistryLite)
    throws IOException
  {
    if (this.recursionDepth >= this.recursionLimit)
      throw InvalidProtocolBufferException.recursionLimitExceeded();
    this.recursionDepth += 1;
    paramBuilder.mergeFrom(this, paramExtensionRegistryLite);
    checkLastTagWas(WireFormat.makeTag(paramInt, 4));
    this.recursionDepth -= 1;
  }

  @Deprecated
  public void readUnknownGroup(int paramInt, MessageLite.Builder paramBuilder)
    throws IOException
  {
    readGroup(paramInt, paramBuilder, null);
  }

  public void readMessage(MessageLite.Builder paramBuilder, ExtensionRegistryLite paramExtensionRegistryLite)
    throws IOException
  {
    int i = readRawVarint32();
    if (this.recursionDepth >= this.recursionLimit)
      throw InvalidProtocolBufferException.recursionLimitExceeded();
    int j = pushLimit(i);
    this.recursionDepth += 1;
    paramBuilder.mergeFrom(this, paramExtensionRegistryLite);
    checkLastTagWas(0);
    this.recursionDepth -= 1;
    popLimit(j);
  }

  public ByteString readBytes()
    throws IOException
  {
    int i = readRawVarint32();
    if (i == 0)
      return ByteString.EMPTY;
    if ((i <= this.bufferSize - this.bufferPos) && (i > 0))
    {
      ByteString localByteString = ByteString.copyFrom(this.buffer, this.bufferPos, i);
      this.bufferPos += i;
      return localByteString;
    }
    return ByteString.copyFrom(readRawBytes(i));
  }

  public int readUInt32()
    throws IOException
  {
    return readRawVarint32();
  }

  public int readEnum()
    throws IOException
  {
    return readRawVarint32();
  }

  public int readSFixed32()
    throws IOException
  {
    return readRawLittleEndian32();
  }

  public long readSFixed64()
    throws IOException
  {
    return readRawLittleEndian64();
  }

  public int readSInt32()
    throws IOException
  {
    return decodeZigZag32(readRawVarint32());
  }

  public long readSInt64()
    throws IOException
  {
    return decodeZigZag64(readRawVarint64());
  }

  public int readRawVarint32()
    throws IOException
  {
    int i = readRawByte();
    if (i >= 0)
      return i;
    int j = i & 0x7F;
    if ((i = readRawByte()) >= 0)
    {
      j |= i << 7;
    }
    else
    {
      j |= (i & 0x7F) << 7;
      if ((i = readRawByte()) >= 0)
      {
        j |= i << 14;
      }
      else
      {
        j |= (i & 0x7F) << 14;
        if ((i = readRawByte()) >= 0)
        {
          j |= i << 21;
        }
        else
        {
          j |= (i & 0x7F) << 21;
          j |= (i = readRawByte()) << 28;
          if (i < 0)
          {
            for (int k = 0; k < 5; k++)
              if (readRawByte() >= 0)
                return j;
            throw InvalidProtocolBufferException.malformedVarint();
          }
        }
      }
    }
    return j;
  }

  static int readRawVarint32(InputStream paramInputStream)
    throws IOException
  {
    int i = paramInputStream.read();
    if (i == -1)
      throw InvalidProtocolBufferException.truncatedMessage();
    return readRawVarint32(i, paramInputStream);
  }

  public static int readRawVarint32(int paramInt, InputStream paramInputStream)
    throws IOException
  {
    if ((paramInt & 0x80) == 0)
      return paramInt;
    int i = paramInt & 0x7F;
    int k;
    for (int j = 7; j < 32; j += 7)
    {
      k = paramInputStream.read();
      if (k == -1)
        throw InvalidProtocolBufferException.truncatedMessage();
      i |= (k & 0x7F) << j;
      if ((k & 0x80) == 0)
        return i;
    }
    while (j < 64)
    {
      k = paramInputStream.read();
      if (k == -1)
        throw InvalidProtocolBufferException.truncatedMessage();
      if ((k & 0x80) == 0)
        return i;
      j += 7;
    }
    throw InvalidProtocolBufferException.malformedVarint();
  }

  public long readRawVarint64()
    throws IOException
  {
    int i = 0;
    long l = 0L;
    while (i < 64)
    {
      int j = readRawByte();
      l |= (j & 0x7F) << i;
      if ((j & 0x80) == 0)
        return l;
      i += 7;
    }
    throw InvalidProtocolBufferException.malformedVarint();
  }

  public int readRawLittleEndian32()
    throws IOException
  {
    int i = readRawByte();
    int j = readRawByte();
    int k = readRawByte();
    int m = readRawByte();
    return i & 0xFF | (j & 0xFF) << 8 | (k & 0xFF) << 16 | (m & 0xFF) << 24;
  }

  public long readRawLittleEndian64()
    throws IOException
  {
    int i = readRawByte();
    int j = readRawByte();
    int k = readRawByte();
    int m = readRawByte();
    int n = readRawByte();
    int i1 = readRawByte();
    int i2 = readRawByte();
    int i3 = readRawByte();
    return i & 0xFF | (j & 0xFF) << 8 | (k & 0xFF) << 16 | (m & 0xFF) << 24 | (n & 0xFF) << 32 | (i1 & 0xFF) << 40 | (i2 & 0xFF) << 48 | (i3 & 0xFF) << 56;
  }

  public static int decodeZigZag32(int paramInt)
  {
    return paramInt >>> 1 ^ -(paramInt & 0x1);
  }

  public static long decodeZigZag64(long paramLong)
  {
    return paramLong >>> 1 ^ -(paramLong & 1L);
  }

  private CodedInputStream(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    this.buffer = paramArrayOfByte;
    this.bufferSize = (paramInt1 + paramInt2);
    this.bufferPos = paramInt1;
    this.totalBytesRetired = (-paramInt1);
    this.input = null;
  }

  private CodedInputStream(InputStream paramInputStream)
  {
    this.buffer = new byte[4096];
    this.bufferSize = 0;
    this.bufferPos = 0;
    this.totalBytesRetired = 0;
    this.input = paramInputStream;
  }

  public int setRecursionLimit(int paramInt)
  {
    if (paramInt < 0)
      throw new IllegalArgumentException("Recursion limit cannot be negative: " + paramInt);
    int i = this.recursionLimit;
    this.recursionLimit = paramInt;
    return i;
  }

  public int setSizeLimit(int paramInt)
  {
    if (paramInt < 0)
      throw new IllegalArgumentException("Size limit cannot be negative: " + paramInt);
    int i = this.sizeLimit;
    this.sizeLimit = paramInt;
    return i;
  }

  public void resetSizeCounter()
  {
    this.totalBytesRetired = (-this.bufferPos);
  }

  public int pushLimit(int paramInt)
    throws InvalidProtocolBufferException
  {
    if (paramInt < 0)
      throw InvalidProtocolBufferException.negativeSize();
    paramInt += this.totalBytesRetired + this.bufferPos;
    int i = this.currentLimit;
    if (paramInt > i)
      throw InvalidProtocolBufferException.truncatedMessage();
    this.currentLimit = paramInt;
    recomputeBufferSizeAfterLimit();
    return i;
  }

  private void recomputeBufferSizeAfterLimit()
  {
    this.bufferSize += this.bufferSizeAfterLimit;
    int i = this.totalBytesRetired + this.bufferSize;
    if (i > this.currentLimit)
    {
      this.bufferSizeAfterLimit = (i - this.currentLimit);
      this.bufferSize -= this.bufferSizeAfterLimit;
    }
    else
    {
      this.bufferSizeAfterLimit = 0;
    }
  }

  public void popLimit(int paramInt)
  {
    this.currentLimit = paramInt;
    recomputeBufferSizeAfterLimit();
  }

  public int getBytesUntilLimit()
  {
    if (this.currentLimit == 2147483647)
      return -1;
    int i = this.totalBytesRetired + this.bufferPos;
    return this.currentLimit - i;
  }

  public boolean isAtEnd()
    throws IOException
  {
    return (this.bufferPos == this.bufferSize) && (!refillBuffer(false));
  }

  public int getTotalBytesRead()
  {
    return this.totalBytesRetired + this.bufferPos;
  }

  private boolean refillBuffer(boolean paramBoolean)
    throws IOException
  {
    if (this.bufferPos < this.bufferSize)
      throw new IllegalStateException("refillBuffer() called when buffer wasn't empty.");
    if (this.totalBytesRetired + this.bufferSize == this.currentLimit)
    {
      if (paramBoolean)
        throw InvalidProtocolBufferException.truncatedMessage();
      return false;
    }
    this.totalBytesRetired += this.bufferSize;
    this.bufferPos = 0;
    this.bufferSize = (this.input == null ? -1 : this.input.read(this.buffer));
    if ((this.bufferSize == 0) || (this.bufferSize < -1))
      throw new IllegalStateException("InputStream#read(byte[]) returned invalid result: " + this.bufferSize + "\nThe InputStream implementation is buggy.");
    if (this.bufferSize == -1)
    {
      this.bufferSize = 0;
      if (paramBoolean)
        throw InvalidProtocolBufferException.truncatedMessage();
      return false;
    }
    recomputeBufferSizeAfterLimit();
    int i = this.totalBytesRetired + this.bufferSize + this.bufferSizeAfterLimit;
    if ((i > this.sizeLimit) || (i < 0))
      throw InvalidProtocolBufferException.sizeLimitExceeded();
    return true;
  }

  public byte readRawByte()
    throws IOException
  {
    if (this.bufferPos == this.bufferSize)
      refillBuffer(true);
    return this.buffer[(this.bufferPos++)];
  }

  public byte[] readRawBytes(int paramInt)
    throws IOException
  {
    if (paramInt < 0)
      throw InvalidProtocolBufferException.negativeSize();
    if (this.totalBytesRetired + this.bufferPos + paramInt > this.currentLimit)
    {
      skipRawBytes(this.currentLimit - this.totalBytesRetired - this.bufferPos);
      throw InvalidProtocolBufferException.truncatedMessage();
    }
    byte[] arrayOfByte1;
    if (paramInt <= this.bufferSize - this.bufferPos)
    {
      arrayOfByte1 = new byte[paramInt];
      System.arraycopy(this.buffer, this.bufferPos, arrayOfByte1, 0, paramInt);
      this.bufferPos += paramInt;
      return arrayOfByte1;
    }
    if (paramInt < 4096)
    {
      arrayOfByte1 = new byte[paramInt];
      j = this.bufferSize - this.bufferPos;
      System.arraycopy(this.buffer, this.bufferPos, arrayOfByte1, 0, j);
      this.bufferPos = this.bufferSize;
      refillBuffer(true);
      while (paramInt - j > this.bufferSize)
      {
        System.arraycopy(this.buffer, 0, arrayOfByte1, j, this.bufferSize);
        j += this.bufferSize;
        this.bufferPos = this.bufferSize;
        refillBuffer(true);
      }
      System.arraycopy(this.buffer, 0, arrayOfByte1, j, paramInt - j);
      this.bufferPos = (paramInt - j);
      return arrayOfByte1;
    }
    int i = this.bufferPos;
    int j = this.bufferSize;
    this.totalBytesRetired += this.bufferSize;
    this.bufferPos = 0;
    this.bufferSize = 0;
    int k = paramInt - (j - i);
    ArrayList localArrayList = new ArrayList();
    while (k > 0)
    {
      arrayOfByte2 = new byte[Math.min(k, 4096)];
      m = 0;
      while (m < arrayOfByte2.length)
      {
        int n = this.input == null ? -1 : this.input.read(arrayOfByte2, m, arrayOfByte2.length - m);
        if (n == -1)
          throw InvalidProtocolBufferException.truncatedMessage();
        this.totalBytesRetired += n;
        m += n;
      }
      k -= arrayOfByte2.length;
      localArrayList.add(arrayOfByte2);
    }
    byte[] arrayOfByte2 = new byte[paramInt];
    int m = j - i;
    System.arraycopy(this.buffer, i, arrayOfByte2, 0, m);
    Iterator localIterator = localArrayList.iterator();
    while (localIterator.hasNext())
    {
      byte[] arrayOfByte3 = (byte[])localIterator.next();
      System.arraycopy(arrayOfByte3, 0, arrayOfByte2, m, arrayOfByte3.length);
      m += arrayOfByte3.length;
    }
    return arrayOfByte2;
  }

  public void skipRawBytes(int paramInt)
    throws IOException
  {
    if (paramInt < 0)
      throw InvalidProtocolBufferException.negativeSize();
    if (this.totalBytesRetired + this.bufferPos + paramInt > this.currentLimit)
    {
      skipRawBytes(this.currentLimit - this.totalBytesRetired - this.bufferPos);
      throw InvalidProtocolBufferException.truncatedMessage();
    }
    if (paramInt <= this.bufferSize - this.bufferPos)
    {
      this.bufferPos += paramInt;
    }
    else
    {
      int i = this.bufferSize - this.bufferPos;
      this.bufferPos = this.bufferSize;
      refillBuffer(true);
      while (paramInt - i > this.bufferSize)
      {
        i += this.bufferSize;
        this.bufferPos = this.bufferSize;
        refillBuffer(true);
      }
      this.bufferPos = (paramInt - i);
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.protobuf.CodedInputStream
 * JD-Core Version:    0.6.2
 */