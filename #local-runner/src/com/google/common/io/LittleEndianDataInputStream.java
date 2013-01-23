package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

@Beta
public final class LittleEndianDataInputStream extends FilterInputStream
  implements DataInput
{
  public LittleEndianDataInputStream(InputStream paramInputStream)
  {
    super((InputStream)Preconditions.checkNotNull(paramInputStream));
  }

  public String readLine()
  {
    throw new UnsupportedOperationException("readLine is not supported");
  }

  public void readFully(byte[] paramArrayOfByte)
    throws IOException
  {
    ByteStreams.readFully(this, paramArrayOfByte);
  }

  public void readFully(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    ByteStreams.readFully(this, paramArrayOfByte, paramInt1, paramInt2);
  }

  public int skipBytes(int paramInt)
    throws IOException
  {
    return (int)this.in.skip(paramInt);
  }

  public int readUnsignedByte()
    throws IOException
  {
    int i = this.in.read();
    if (0 > i)
      throw new EOFException();
    return i;
  }

  public int readUnsignedShort()
    throws IOException
  {
    byte b1 = readAndCheckByte();
    byte b2 = readAndCheckByte();
    return Ints.fromBytes((byte)0, (byte)0, b2, b1);
  }

  public int readInt()
    throws IOException
  {
    byte b1 = readAndCheckByte();
    byte b2 = readAndCheckByte();
    byte b3 = readAndCheckByte();
    byte b4 = readAndCheckByte();
    return Ints.fromBytes(b4, b3, b2, b1);
  }

  public long readLong()
    throws IOException
  {
    byte b1 = readAndCheckByte();
    byte b2 = readAndCheckByte();
    byte b3 = readAndCheckByte();
    byte b4 = readAndCheckByte();
    byte b5 = readAndCheckByte();
    byte b6 = readAndCheckByte();
    byte b7 = readAndCheckByte();
    byte b8 = readAndCheckByte();
    return Longs.fromBytes(b8, b7, b6, b5, b4, b3, b2, b1);
  }

  public float readFloat()
    throws IOException
  {
    return Float.intBitsToFloat(readInt());
  }

  public double readDouble()
    throws IOException
  {
    return Double.longBitsToDouble(readLong());
  }

  public String readUTF()
    throws IOException
  {
    return new DataInputStream(this.in).readUTF();
  }

  public short readShort()
    throws IOException
  {
    return (short)readUnsignedShort();
  }

  public char readChar()
    throws IOException
  {
    return (char)readUnsignedShort();
  }

  public byte readByte()
    throws IOException
  {
    return (byte)readUnsignedByte();
  }

  public boolean readBoolean()
    throws IOException
  {
    return readUnsignedByte() != 0;
  }

  private byte readAndCheckByte()
    throws IOException, EOFException
  {
    int i = this.in.read();
    if (-1 == i)
      throw new EOFException();
    return (byte)i;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.io.LittleEndianDataInputStream
 * JD-Core Version:    0.6.2
 */