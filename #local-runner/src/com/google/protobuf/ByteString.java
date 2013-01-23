package com.google.protobuf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;

public final class ByteString
{
  private final byte[] bytes;
  public static final ByteString EMPTY = new ByteString(new byte[0]);
  private volatile int hash = 0;

  private ByteString(byte[] paramArrayOfByte)
  {
    this.bytes = paramArrayOfByte;
  }

  public byte byteAt(int paramInt)
  {
    return this.bytes[paramInt];
  }

  public int size()
  {
    return this.bytes.length;
  }

  public boolean isEmpty()
  {
    return this.bytes.length == 0;
  }

  public static ByteString copyFrom(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    byte[] arrayOfByte = new byte[paramInt2];
    System.arraycopy(paramArrayOfByte, paramInt1, arrayOfByte, 0, paramInt2);
    return new ByteString(arrayOfByte);
  }

  public static ByteString copyFrom(byte[] paramArrayOfByte)
  {
    return copyFrom(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public static ByteString copyFrom(ByteBuffer paramByteBuffer, int paramInt)
  {
    byte[] arrayOfByte = new byte[paramInt];
    paramByteBuffer.get(arrayOfByte);
    return new ByteString(arrayOfByte);
  }

  public static ByteString copyFrom(ByteBuffer paramByteBuffer)
  {
    return copyFrom(paramByteBuffer, paramByteBuffer.remaining());
  }

  public static ByteString copyFrom(String paramString1, String paramString2)
    throws UnsupportedEncodingException
  {
    return new ByteString(paramString1.getBytes(paramString2));
  }

  public static ByteString copyFromUtf8(String paramString)
  {
    try
    {
      return new ByteString(paramString.getBytes("UTF-8"));
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      throw new RuntimeException("UTF-8 not supported?", localUnsupportedEncodingException);
    }
  }

  public static ByteString copyFrom(List paramList)
  {
    if (paramList.size() == 0)
      return EMPTY;
    if (paramList.size() == 1)
      return (ByteString)paramList.get(0);
    int i = 0;
    Object localObject = paramList.iterator();
    while (((Iterator)localObject).hasNext())
    {
      ByteString localByteString1 = (ByteString)((Iterator)localObject).next();
      i += localByteString1.size();
    }
    localObject = new byte[i];
    int j = 0;
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      ByteString localByteString2 = (ByteString)localIterator.next();
      System.arraycopy(localByteString2.bytes, 0, localObject, j, localByteString2.size());
      j += localByteString2.size();
    }
    return new ByteString((byte[])localObject);
  }

  public void copyTo(byte[] paramArrayOfByte, int paramInt)
  {
    System.arraycopy(this.bytes, 0, paramArrayOfByte, paramInt, this.bytes.length);
  }

  public void copyTo(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3)
  {
    System.arraycopy(this.bytes, paramInt1, paramArrayOfByte, paramInt2, paramInt3);
  }

  public void copyTo(ByteBuffer paramByteBuffer)
  {
    paramByteBuffer.put(this.bytes, 0, this.bytes.length);
  }

  public byte[] toByteArray()
  {
    int i = this.bytes.length;
    byte[] arrayOfByte = new byte[i];
    System.arraycopy(this.bytes, 0, arrayOfByte, 0, i);
    return arrayOfByte;
  }

  public ByteBuffer asReadOnlyByteBuffer()
  {
    ByteBuffer localByteBuffer = ByteBuffer.wrap(this.bytes);
    return localByteBuffer.asReadOnlyBuffer();
  }

  public String toString(String paramString)
    throws UnsupportedEncodingException
  {
    return new String(this.bytes, paramString);
  }

  public String toStringUtf8()
  {
    try
    {
      return new String(this.bytes, "UTF-8");
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      throw new RuntimeException("UTF-8 not supported?", localUnsupportedEncodingException);
    }
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == this)
      return true;
    if (!(paramObject instanceof ByteString))
      return false;
    ByteString localByteString = (ByteString)paramObject;
    int i = this.bytes.length;
    if (i != localByteString.bytes.length)
      return false;
    byte[] arrayOfByte1 = this.bytes;
    byte[] arrayOfByte2 = localByteString.bytes;
    for (int j = 0; j < i; j++)
      if (arrayOfByte1[j] != arrayOfByte2[j])
        return false;
    return true;
  }

  public int hashCode()
  {
    int i = this.hash;
    if (i == 0)
    {
      byte[] arrayOfByte = this.bytes;
      int j = this.bytes.length;
      i = j;
      for (int k = 0; k < j; k++)
        i = i * 31 + arrayOfByte[k];
      if (i == 0)
        i = 1;
      this.hash = i;
    }
    return i;
  }

  public InputStream newInput()
  {
    return new ByteArrayInputStream(this.bytes);
  }

  public CodedInputStream newCodedInput()
  {
    return CodedInputStream.newInstance(this.bytes);
  }

  public static Output newOutput(int paramInt)
  {
    return new Output(new ByteArrayOutputStream(paramInt), null);
  }

  public static Output newOutput()
  {
    return newOutput(32);
  }

  static CodedBuilder newCodedBuilder(int paramInt)
  {
    return new CodedBuilder(paramInt, null);
  }

  static final class CodedBuilder
  {
    private final CodedOutputStream output;
    private final byte[] buffer;

    private CodedBuilder(int paramInt)
    {
      this.buffer = new byte[paramInt];
      this.output = CodedOutputStream.newInstance(this.buffer);
    }

    public ByteString build()
    {
      this.output.checkNoSpaceLeft();
      return new ByteString(this.buffer, null);
    }

    public CodedOutputStream getCodedOutput()
    {
      return this.output;
    }
  }

  public static final class Output extends FilterOutputStream
  {
    private final ByteArrayOutputStream bout;

    private Output(ByteArrayOutputStream paramByteArrayOutputStream)
    {
      super();
      this.bout = paramByteArrayOutputStream;
    }

    public ByteString toByteString()
    {
      byte[] arrayOfByte = this.bout.toByteArray();
      return new ByteString(arrayOfByte, null);
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.protobuf.ByteString
 * JD-Core Version:    0.6.2
 */