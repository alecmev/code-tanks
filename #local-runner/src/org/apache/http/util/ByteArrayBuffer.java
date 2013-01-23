package org.apache.http.util;

import java.io.Serializable;

public final class ByteArrayBuffer
  implements Serializable
{
  private byte[] buffer;
  private int len;

  public ByteArrayBuffer(int paramInt)
  {
    if (paramInt < 0)
      throw new IllegalArgumentException("Buffer capacity may not be negative");
    this.buffer = new byte[paramInt];
  }

  private void expand(int paramInt)
  {
    byte[] arrayOfByte = new byte[Math.max(this.buffer.length << 1, paramInt)];
    System.arraycopy(this.buffer, 0, arrayOfByte, 0, this.len);
    this.buffer = arrayOfByte;
  }

  public void append(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (paramArrayOfByte == null)
      return;
    if ((paramInt1 < 0) || (paramInt1 > paramArrayOfByte.length) || (paramInt2 < 0) || (paramInt1 + paramInt2 < 0) || (paramInt1 + paramInt2 > paramArrayOfByte.length))
      throw new IndexOutOfBoundsException("off: " + paramInt1 + " len: " + paramInt2 + " b.length: " + paramArrayOfByte.length);
    if (paramInt2 == 0)
      return;
    int i = this.len + paramInt2;
    if (i > this.buffer.length)
      expand(i);
    System.arraycopy(paramArrayOfByte, paramInt1, this.buffer, this.len, paramInt2);
    this.len = i;
  }

  public void append(int paramInt)
  {
    int i = this.len + 1;
    if (i > this.buffer.length)
      expand(i);
    this.buffer[this.len] = ((byte)paramInt);
    this.len = i;
  }

  public void append(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    if (paramArrayOfChar == null)
      return;
    if ((paramInt1 < 0) || (paramInt1 > paramArrayOfChar.length) || (paramInt2 < 0) || (paramInt1 + paramInt2 < 0) || (paramInt1 + paramInt2 > paramArrayOfChar.length))
      throw new IndexOutOfBoundsException("off: " + paramInt1 + " len: " + paramInt2 + " b.length: " + paramArrayOfChar.length);
    if (paramInt2 == 0)
      return;
    int i = this.len;
    int j = i + paramInt2;
    if (j > this.buffer.length)
      expand(j);
    int k = paramInt1;
    for (int m = i; m < j; m++)
    {
      this.buffer[m] = ((byte)paramArrayOfChar[k]);
      k++;
    }
    this.len = j;
  }

  public void append(CharArrayBuffer paramCharArrayBuffer, int paramInt1, int paramInt2)
  {
    if (paramCharArrayBuffer == null)
      return;
    append(paramCharArrayBuffer.buffer(), paramInt1, paramInt2);
  }

  public void clear()
  {
    this.len = 0;
  }

  public byte[] toByteArray()
  {
    byte[] arrayOfByte = new byte[this.len];
    if (this.len > 0)
      System.arraycopy(this.buffer, 0, arrayOfByte, 0, this.len);
    return arrayOfByte;
  }

  public int byteAt(int paramInt)
  {
    return this.buffer[paramInt];
  }

  public int capacity()
  {
    return this.buffer.length;
  }

  public int length()
  {
    return this.len;
  }

  public byte[] buffer()
  {
    return this.buffer;
  }

  public boolean isEmpty()
  {
    return this.len == 0;
  }

  public boolean isFull()
  {
    return this.len == this.buffer.length;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.util.ByteArrayBuffer
 * JD-Core Version:    0.6.2
 */