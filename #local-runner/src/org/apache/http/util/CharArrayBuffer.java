package org.apache.http.util;

import java.io.Serializable;
import org.apache.http.protocol.HTTP;

public final class CharArrayBuffer
  implements Serializable
{
  private char[] buffer;
  private int len;

  public CharArrayBuffer(int paramInt)
  {
    if (paramInt < 0)
      throw new IllegalArgumentException("Buffer capacity may not be negative");
    this.buffer = new char[paramInt];
  }

  private void expand(int paramInt)
  {
    char[] arrayOfChar = new char[Math.max(this.buffer.length << 1, paramInt)];
    System.arraycopy(this.buffer, 0, arrayOfChar, 0, this.len);
    this.buffer = arrayOfChar;
  }

  public void append(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    if (paramArrayOfChar == null)
      return;
    if ((paramInt1 < 0) || (paramInt1 > paramArrayOfChar.length) || (paramInt2 < 0) || (paramInt1 + paramInt2 < 0) || (paramInt1 + paramInt2 > paramArrayOfChar.length))
      throw new IndexOutOfBoundsException("off: " + paramInt1 + " len: " + paramInt2 + " b.length: " + paramArrayOfChar.length);
    if (paramInt2 == 0)
      return;
    int i = this.len + paramInt2;
    if (i > this.buffer.length)
      expand(i);
    System.arraycopy(paramArrayOfChar, paramInt1, this.buffer, this.len, paramInt2);
    this.len = i;
  }

  public void append(String paramString)
  {
    if (paramString == null)
      paramString = "null";
    int i = paramString.length();
    int j = this.len + i;
    if (j > this.buffer.length)
      expand(j);
    paramString.getChars(0, i, this.buffer, this.len);
    this.len = j;
  }

  public void append(CharArrayBuffer paramCharArrayBuffer, int paramInt1, int paramInt2)
  {
    if (paramCharArrayBuffer == null)
      return;
    append(paramCharArrayBuffer.buffer, paramInt1, paramInt2);
  }

  public void append(char paramChar)
  {
    int i = this.len + 1;
    if (i > this.buffer.length)
      expand(i);
    this.buffer[this.len] = paramChar;
    this.len = i;
  }

  public void append(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (paramArrayOfByte == null)
      return;
    if ((paramInt1 < 0) || (paramInt1 > paramArrayOfByte.length) || (paramInt2 < 0) || (paramInt1 + paramInt2 < 0) || (paramInt1 + paramInt2 > paramArrayOfByte.length))
      throw new IndexOutOfBoundsException("off: " + paramInt1 + " len: " + paramInt2 + " b.length: " + paramArrayOfByte.length);
    if (paramInt2 == 0)
      return;
    int i = this.len;
    int j = i + paramInt2;
    if (j > this.buffer.length)
      expand(j);
    int k = paramInt1;
    for (int m = i; m < j; m++)
    {
      this.buffer[m] = ((char)(paramArrayOfByte[k] & 0xFF));
      k++;
    }
    this.len = j;
  }

  public void append(ByteArrayBuffer paramByteArrayBuffer, int paramInt1, int paramInt2)
  {
    if (paramByteArrayBuffer == null)
      return;
    append(paramByteArrayBuffer.buffer(), paramInt1, paramInt2);
  }

  public void clear()
  {
    this.len = 0;
  }

  public char charAt(int paramInt)
  {
    return this.buffer[paramInt];
  }

  public char[] buffer()
  {
    return this.buffer;
  }

  public int length()
  {
    return this.len;
  }

  public void ensureCapacity(int paramInt)
  {
    if (paramInt <= 0)
      return;
    int i = this.buffer.length - this.len;
    if (paramInt > i)
      expand(this.len + paramInt);
  }

  public boolean isEmpty()
  {
    return this.len == 0;
  }

  public int indexOf(int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt2 < 0)
      paramInt2 = 0;
    if (paramInt3 > this.len)
      paramInt3 = this.len;
    if (paramInt2 > paramInt3)
      return -1;
    for (int i = paramInt2; i < paramInt3; i++)
      if (this.buffer[i] == paramInt1)
        return i;
    return -1;
  }

  public int indexOf(int paramInt)
  {
    return indexOf(paramInt, 0, this.len);
  }

  public String substring(int paramInt1, int paramInt2)
  {
    return new String(this.buffer, paramInt1, paramInt2 - paramInt1);
  }

  public String substringTrimmed(int paramInt1, int paramInt2)
  {
    if (paramInt1 < 0)
      throw new IndexOutOfBoundsException("Negative beginIndex: " + paramInt1);
    if (paramInt2 > this.len)
      throw new IndexOutOfBoundsException("endIndex: " + paramInt2 + " > length: " + this.len);
    if (paramInt1 > paramInt2)
      throw new IndexOutOfBoundsException("beginIndex: " + paramInt1 + " > endIndex: " + paramInt2);
    while ((paramInt1 < paramInt2) && (HTTP.isWhitespace(this.buffer[paramInt1])))
      paramInt1++;
    while ((paramInt2 > paramInt1) && (HTTP.isWhitespace(this.buffer[(paramInt2 - 1)])))
      paramInt2--;
    return new String(this.buffer, paramInt1, paramInt2 - paramInt1);
  }

  public String toString()
  {
    return new String(this.buffer, 0, this.len);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.util.CharArrayBuffer
 * JD-Core Version:    0.6.2
 */