package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hasher;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.zip.Checksum;

@Beta
public final class ByteStreams
{
  private static final int BUF_SIZE = 4096;

  public static InputSupplier newInputStreamSupplier(byte[] paramArrayOfByte)
  {
    return newInputStreamSupplier(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public static InputSupplier newInputStreamSupplier(byte[] paramArrayOfByte, final int paramInt1, final int paramInt2)
  {
    return new InputSupplier()
    {
      public ByteArrayInputStream getInput()
      {
        return new ByteArrayInputStream(this.val$b, paramInt1, paramInt2);
      }
    };
  }

  public static void write(byte[] paramArrayOfByte, OutputSupplier paramOutputSupplier)
    throws IOException
  {
    Preconditions.checkNotNull(paramArrayOfByte);
    boolean bool = true;
    OutputStream localOutputStream = (OutputStream)paramOutputSupplier.getOutput();
    try
    {
      localOutputStream.write(paramArrayOfByte);
      bool = false;
    }
    finally
    {
      Closeables.close(localOutputStream, bool);
    }
  }

  public static long copy(InputSupplier paramInputSupplier, OutputSupplier paramOutputSupplier)
    throws IOException
  {
    int i = 0;
    InputStream localInputStream = (InputStream)paramInputSupplier.getInput();
    try
    {
      OutputStream localOutputStream = (OutputStream)paramOutputSupplier.getOutput();
      try
      {
        long l1 = copy(localInputStream, localOutputStream);
        i++;
        long l2 = l1;
        Closeables.close(localOutputStream, i < 1);
        i++;
        Closeables.close(localInputStream, i < 2);
        return l2;
      }
      finally
      {
        Closeables.close(localOutputStream, i < 1);
        i++;
      }
    }
    finally
    {
      Closeables.close(localInputStream, i < 2);
    }
  }

  public static long copy(InputSupplier paramInputSupplier, OutputStream paramOutputStream)
    throws IOException
  {
    boolean bool = true;
    InputStream localInputStream = (InputStream)paramInputSupplier.getInput();
    try
    {
      long l1 = copy(localInputStream, paramOutputStream);
      bool = false;
      long l2 = l1;
      return l2;
    }
    finally
    {
      Closeables.close(localInputStream, bool);
    }
  }

  public static long copy(InputStream paramInputStream, OutputSupplier paramOutputSupplier)
    throws IOException
  {
    boolean bool = true;
    OutputStream localOutputStream = (OutputStream)paramOutputSupplier.getOutput();
    try
    {
      long l1 = copy(paramInputStream, localOutputStream);
      bool = false;
      long l2 = l1;
      return l2;
    }
    finally
    {
      Closeables.close(localOutputStream, bool);
    }
  }

  public static long copy(InputStream paramInputStream, OutputStream paramOutputStream)
    throws IOException
  {
    byte[] arrayOfByte = new byte[4096];
    int i;
    for (long l = 0L; ; l += i)
    {
      i = paramInputStream.read(arrayOfByte);
      if (i == -1)
        break;
      paramOutputStream.write(arrayOfByte, 0, i);
    }
    return l;
  }

  public static long copy(ReadableByteChannel paramReadableByteChannel, WritableByteChannel paramWritableByteChannel)
    throws IOException
  {
    ByteBuffer localByteBuffer = ByteBuffer.allocate(4096);
    long l = 0L;
    while (paramReadableByteChannel.read(localByteBuffer) != -1)
    {
      localByteBuffer.flip();
      while (localByteBuffer.hasRemaining())
        l += paramWritableByteChannel.write(localByteBuffer);
      localByteBuffer.clear();
    }
    return l;
  }

  public static byte[] toByteArray(InputStream paramInputStream)
    throws IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    copy(paramInputStream, localByteArrayOutputStream);
    return localByteArrayOutputStream.toByteArray();
  }

  public static byte[] toByteArray(InputSupplier paramInputSupplier)
    throws IOException
  {
    boolean bool = true;
    InputStream localInputStream = (InputStream)paramInputSupplier.getInput();
    try
    {
      byte[] arrayOfByte1 = toByteArray(localInputStream);
      bool = false;
      byte[] arrayOfByte2 = arrayOfByte1;
      return arrayOfByte2;
    }
    finally
    {
      Closeables.close(localInputStream, bool);
    }
  }

  public static ByteArrayDataInput newDataInput(byte[] paramArrayOfByte)
  {
    return new ByteArrayDataInputStream(paramArrayOfByte);
  }

  public static ByteArrayDataInput newDataInput(byte[] paramArrayOfByte, int paramInt)
  {
    Preconditions.checkPositionIndex(paramInt, paramArrayOfByte.length);
    return new ByteArrayDataInputStream(paramArrayOfByte, paramInt);
  }

  public static ByteArrayDataOutput newDataOutput()
  {
    return new ByteArrayDataOutputStream();
  }

  public static ByteArrayDataOutput newDataOutput(int paramInt)
  {
    Preconditions.checkArgument(paramInt >= 0, "Invalid size: %s", new Object[] { Integer.valueOf(paramInt) });
    return new ByteArrayDataOutputStream(paramInt);
  }

  public static long length(InputSupplier paramInputSupplier)
    throws IOException
  {
    long l1 = 0L;
    boolean bool = true;
    InputStream localInputStream = (InputStream)paramInputSupplier.getInput();
    try
    {
      while (true)
      {
        long l2 = localInputStream.skip(2147483647L);
        if (l2 == 0L)
        {
          if (localInputStream.read() == -1)
          {
            bool = false;
            long l3 = l1;
            return l3;
          }
          l1 += 1L;
        }
        else
        {
          l1 += l2;
        }
      }
    }
    finally
    {
      Closeables.close(localInputStream, bool);
    }
  }

  public static boolean equal(InputSupplier paramInputSupplier1, InputSupplier paramInputSupplier2)
    throws IOException
  {
    byte[] arrayOfByte1 = new byte[4096];
    byte[] arrayOfByte2 = new byte[4096];
    boolean bool1 = true;
    InputStream localInputStream1 = (InputStream)paramInputSupplier1.getInput();
    try
    {
      InputStream localInputStream2 = (InputStream)paramInputSupplier2.getInput();
      try
      {
        while (true)
        {
          int i = read(localInputStream1, arrayOfByte1, 0, 4096);
          int j = read(localInputStream2, arrayOfByte2, 0, 4096);
          boolean bool2;
          if ((i != j) || (!Arrays.equals(arrayOfByte1, arrayOfByte2)))
          {
            bool1 = false;
            bool2 = false;
            return bool2;
          }
          if (i != 4096)
          {
            bool1 = false;
            bool2 = true;
            return bool2;
          }
        }
      }
      finally
      {
      }
    }
    finally
    {
      Closeables.close(localInputStream1, bool1);
    }
  }

  public static void readFully(InputStream paramInputStream, byte[] paramArrayOfByte)
    throws IOException
  {
    readFully(paramInputStream, paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public static void readFully(InputStream paramInputStream, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (read(paramInputStream, paramArrayOfByte, paramInt1, paramInt2) != paramInt2)
      throw new EOFException();
  }

  public static void skipFully(InputStream paramInputStream, long paramLong)
    throws IOException
  {
    while (paramLong > 0L)
    {
      long l = paramInputStream.skip(paramLong);
      if (l == 0L)
      {
        if (paramInputStream.read() == -1)
          throw new EOFException();
        paramLong -= 1L;
      }
      else
      {
        paramLong -= l;
      }
    }
  }

  public static Object readBytes(InputSupplier paramInputSupplier, ByteProcessor paramByteProcessor)
    throws IOException
  {
    byte[] arrayOfByte = new byte[4096];
    boolean bool = true;
    InputStream localInputStream = (InputStream)paramInputSupplier.getInput();
    try
    {
      int i;
      do
      {
        i = localInputStream.read(arrayOfByte);
        if (i == -1)
        {
          bool = false;
          break;
        }
      }
      while (paramByteProcessor.processBytes(arrayOfByte, 0, i));
      Object localObject1 = paramByteProcessor.getResult();
      return localObject1;
    }
    finally
    {
      Closeables.close(localInputStream, bool);
    }
  }

  public static long getChecksum(InputSupplier paramInputSupplier, Checksum paramChecksum)
    throws IOException
  {
    return ((Long)readBytes(paramInputSupplier, new ByteProcessor()
    {
      public boolean processBytes(byte[] paramAnonymousArrayOfByte, int paramAnonymousInt1, int paramAnonymousInt2)
      {
        this.val$checksum.update(paramAnonymousArrayOfByte, paramAnonymousInt1, paramAnonymousInt2);
        return true;
      }

      public Long getResult()
      {
        long l = this.val$checksum.getValue();
        this.val$checksum.reset();
        return Long.valueOf(l);
      }
    })).longValue();
  }

  @Deprecated
  public static byte[] getDigest(InputSupplier paramInputSupplier, MessageDigest paramMessageDigest)
    throws IOException
  {
    return (byte[])readBytes(paramInputSupplier, new ByteProcessor()
    {
      public boolean processBytes(byte[] paramAnonymousArrayOfByte, int paramAnonymousInt1, int paramAnonymousInt2)
      {
        this.val$md.update(paramAnonymousArrayOfByte, paramAnonymousInt1, paramAnonymousInt2);
        return true;
      }

      public byte[] getResult()
      {
        return this.val$md.digest();
      }
    });
  }

  public static HashCode hash(InputSupplier paramInputSupplier, HashFunction paramHashFunction)
    throws IOException
  {
    Hasher localHasher = paramHashFunction.newHasher();
    return (HashCode)readBytes(paramInputSupplier, new ByteProcessor()
    {
      public boolean processBytes(byte[] paramAnonymousArrayOfByte, int paramAnonymousInt1, int paramAnonymousInt2)
      {
        this.val$hasher.putBytes(paramAnonymousArrayOfByte, paramAnonymousInt1, paramAnonymousInt2);
        return true;
      }

      public HashCode getResult()
      {
        return this.val$hasher.hash();
      }
    });
  }

  public static int read(InputStream paramInputStream, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (paramInt2 < 0)
      throw new IndexOutOfBoundsException("len is negative");
    int i = 0;
    while (i < paramInt2)
    {
      int j = paramInputStream.read(paramArrayOfByte, paramInt1 + i, paramInt2 - i);
      if (j == -1)
        break;
      i += j;
    }
    return i;
  }

  public static InputSupplier slice(InputSupplier paramInputSupplier, final long paramLong1, long paramLong2)
  {
    Preconditions.checkNotNull(paramInputSupplier);
    Preconditions.checkArgument(paramLong1 >= 0L, "offset is negative");
    Preconditions.checkArgument(paramLong2 >= 0L, "length is negative");
    return new InputSupplier()
    {
      public InputStream getInput()
        throws IOException
      {
        InputStream localInputStream = (InputStream)this.val$supplier.getInput();
        if (paramLong1 > 0L)
          try
          {
            ByteStreams.skipFully(localInputStream, paramLong1);
          }
          catch (IOException localIOException)
          {
            Closeables.closeQuietly(localInputStream);
            throw localIOException;
          }
        return new LimitInputStream(localInputStream, this.val$length);
      }
    };
  }

  public static InputSupplier join(Iterable paramIterable)
  {
    return new InputSupplier()
    {
      public InputStream getInput()
        throws IOException
      {
        return new MultiInputStream(this.val$suppliers.iterator());
      }
    };
  }

  public static InputSupplier join(InputSupplier[] paramArrayOfInputSupplier)
  {
    return join(Arrays.asList(paramArrayOfInputSupplier));
  }

  private static class ByteArrayDataOutputStream
    implements ByteArrayDataOutput
  {
    final DataOutput output;
    final ByteArrayOutputStream byteArrayOutputSteam;

    ByteArrayDataOutputStream()
    {
      this(new ByteArrayOutputStream());
    }

    ByteArrayDataOutputStream(int paramInt)
    {
      this(new ByteArrayOutputStream(paramInt));
    }

    ByteArrayDataOutputStream(ByteArrayOutputStream paramByteArrayOutputStream)
    {
      this.byteArrayOutputSteam = paramByteArrayOutputStream;
      this.output = new DataOutputStream(paramByteArrayOutputStream);
    }

    public void write(int paramInt)
    {
      try
      {
        this.output.write(paramInt);
      }
      catch (IOException localIOException)
      {
        throw new AssertionError(localIOException);
      }
    }

    public void write(byte[] paramArrayOfByte)
    {
      try
      {
        this.output.write(paramArrayOfByte);
      }
      catch (IOException localIOException)
      {
        throw new AssertionError(localIOException);
      }
    }

    public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    {
      try
      {
        this.output.write(paramArrayOfByte, paramInt1, paramInt2);
      }
      catch (IOException localIOException)
      {
        throw new AssertionError(localIOException);
      }
    }

    public void writeBoolean(boolean paramBoolean)
    {
      try
      {
        this.output.writeBoolean(paramBoolean);
      }
      catch (IOException localIOException)
      {
        throw new AssertionError(localIOException);
      }
    }

    public void writeByte(int paramInt)
    {
      try
      {
        this.output.writeByte(paramInt);
      }
      catch (IOException localIOException)
      {
        throw new AssertionError(localIOException);
      }
    }

    public void writeBytes(String paramString)
    {
      try
      {
        this.output.writeBytes(paramString);
      }
      catch (IOException localIOException)
      {
        throw new AssertionError(localIOException);
      }
    }

    public void writeChar(int paramInt)
    {
      try
      {
        this.output.writeChar(paramInt);
      }
      catch (IOException localIOException)
      {
        throw new AssertionError(localIOException);
      }
    }

    public void writeChars(String paramString)
    {
      try
      {
        this.output.writeChars(paramString);
      }
      catch (IOException localIOException)
      {
        throw new AssertionError(localIOException);
      }
    }

    public void writeDouble(double paramDouble)
    {
      try
      {
        this.output.writeDouble(paramDouble);
      }
      catch (IOException localIOException)
      {
        throw new AssertionError(localIOException);
      }
    }

    public void writeFloat(float paramFloat)
    {
      try
      {
        this.output.writeFloat(paramFloat);
      }
      catch (IOException localIOException)
      {
        throw new AssertionError(localIOException);
      }
    }

    public void writeInt(int paramInt)
    {
      try
      {
        this.output.writeInt(paramInt);
      }
      catch (IOException localIOException)
      {
        throw new AssertionError(localIOException);
      }
    }

    public void writeLong(long paramLong)
    {
      try
      {
        this.output.writeLong(paramLong);
      }
      catch (IOException localIOException)
      {
        throw new AssertionError(localIOException);
      }
    }

    public void writeShort(int paramInt)
    {
      try
      {
        this.output.writeShort(paramInt);
      }
      catch (IOException localIOException)
      {
        throw new AssertionError(localIOException);
      }
    }

    public void writeUTF(String paramString)
    {
      try
      {
        this.output.writeUTF(paramString);
      }
      catch (IOException localIOException)
      {
        throw new AssertionError(localIOException);
      }
    }

    public byte[] toByteArray()
    {
      return this.byteArrayOutputSteam.toByteArray();
    }
  }

  private static class ByteArrayDataInputStream
    implements ByteArrayDataInput
  {
    final DataInput input;

    ByteArrayDataInputStream(byte[] paramArrayOfByte)
    {
      this.input = new DataInputStream(new ByteArrayInputStream(paramArrayOfByte));
    }

    ByteArrayDataInputStream(byte[] paramArrayOfByte, int paramInt)
    {
      this.input = new DataInputStream(new ByteArrayInputStream(paramArrayOfByte, paramInt, paramArrayOfByte.length - paramInt));
    }

    public void readFully(byte[] paramArrayOfByte)
    {
      try
      {
        this.input.readFully(paramArrayOfByte);
      }
      catch (IOException localIOException)
      {
        throw new IllegalStateException(localIOException);
      }
    }

    public void readFully(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    {
      try
      {
        this.input.readFully(paramArrayOfByte, paramInt1, paramInt2);
      }
      catch (IOException localIOException)
      {
        throw new IllegalStateException(localIOException);
      }
    }

    public int skipBytes(int paramInt)
    {
      try
      {
        return this.input.skipBytes(paramInt);
      }
      catch (IOException localIOException)
      {
        throw new IllegalStateException(localIOException);
      }
    }

    public boolean readBoolean()
    {
      try
      {
        return this.input.readBoolean();
      }
      catch (IOException localIOException)
      {
        throw new IllegalStateException(localIOException);
      }
    }

    public byte readByte()
    {
      try
      {
        return this.input.readByte();
      }
      catch (EOFException localEOFException)
      {
        throw new IllegalStateException(localEOFException);
      }
      catch (IOException localIOException)
      {
        throw new AssertionError(localIOException);
      }
    }

    public int readUnsignedByte()
    {
      try
      {
        return this.input.readUnsignedByte();
      }
      catch (IOException localIOException)
      {
        throw new IllegalStateException(localIOException);
      }
    }

    public short readShort()
    {
      try
      {
        return this.input.readShort();
      }
      catch (IOException localIOException)
      {
        throw new IllegalStateException(localIOException);
      }
    }

    public int readUnsignedShort()
    {
      try
      {
        return this.input.readUnsignedShort();
      }
      catch (IOException localIOException)
      {
        throw new IllegalStateException(localIOException);
      }
    }

    public char readChar()
    {
      try
      {
        return this.input.readChar();
      }
      catch (IOException localIOException)
      {
        throw new IllegalStateException(localIOException);
      }
    }

    public int readInt()
    {
      try
      {
        return this.input.readInt();
      }
      catch (IOException localIOException)
      {
        throw new IllegalStateException(localIOException);
      }
    }

    public long readLong()
    {
      try
      {
        return this.input.readLong();
      }
      catch (IOException localIOException)
      {
        throw new IllegalStateException(localIOException);
      }
    }

    public float readFloat()
    {
      try
      {
        return this.input.readFloat();
      }
      catch (IOException localIOException)
      {
        throw new IllegalStateException(localIOException);
      }
    }

    public double readDouble()
    {
      try
      {
        return this.input.readDouble();
      }
      catch (IOException localIOException)
      {
        throw new IllegalStateException(localIOException);
      }
    }

    public String readLine()
    {
      try
      {
        return this.input.readLine();
      }
      catch (IOException localIOException)
      {
        throw new IllegalStateException(localIOException);
      }
    }

    public String readUTF()
    {
      try
      {
        return this.input.readUTF();
      }
      catch (IOException localIOException)
      {
        throw new IllegalStateException(localIOException);
      }
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.io.ByteStreams
 * JD-Core Version:    0.6.2
 */