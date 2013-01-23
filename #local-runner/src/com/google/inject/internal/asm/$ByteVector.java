package com.google.inject.internal.asm;

public class $ByteVector
{
  byte[] a;
  int b;

  public $ByteVector()
  {
    this.a = new byte[64];
  }

  public $ByteVector(int paramInt)
  {
    this.a = new byte[paramInt];
  }

  public ByteVector putByte(int paramInt)
  {
    int i = this.b;
    if (i + 1 > this.a.length)
      a(1);
    this.a[(i++)] = ((byte)paramInt);
    this.b = i;
    return this;
  }

  ByteVector a(int paramInt1, int paramInt2)
  {
    int i = this.b;
    if (i + 2 > this.a.length)
      a(2);
    byte[] arrayOfByte = this.a;
    arrayOfByte[(i++)] = ((byte)paramInt1);
    arrayOfByte[(i++)] = ((byte)paramInt2);
    this.b = i;
    return this;
  }

  public ByteVector putShort(int paramInt)
  {
    int i = this.b;
    if (i + 2 > this.a.length)
      a(2);
    byte[] arrayOfByte = this.a;
    arrayOfByte[(i++)] = ((byte)(paramInt >>> 8));
    arrayOfByte[(i++)] = ((byte)paramInt);
    this.b = i;
    return this;
  }

  ByteVector b(int paramInt1, int paramInt2)
  {
    int i = this.b;
    if (i + 3 > this.a.length)
      a(3);
    byte[] arrayOfByte = this.a;
    arrayOfByte[(i++)] = ((byte)paramInt1);
    arrayOfByte[(i++)] = ((byte)(paramInt2 >>> 8));
    arrayOfByte[(i++)] = ((byte)paramInt2);
    this.b = i;
    return this;
  }

  public ByteVector putInt(int paramInt)
  {
    int i = this.b;
    if (i + 4 > this.a.length)
      a(4);
    byte[] arrayOfByte = this.a;
    arrayOfByte[(i++)] = ((byte)(paramInt >>> 24));
    arrayOfByte[(i++)] = ((byte)(paramInt >>> 16));
    arrayOfByte[(i++)] = ((byte)(paramInt >>> 8));
    arrayOfByte[(i++)] = ((byte)paramInt);
    this.b = i;
    return this;
  }

  public ByteVector putLong(long paramLong)
  {
    int i = this.b;
    if (i + 8 > this.a.length)
      a(8);
    byte[] arrayOfByte = this.a;
    int j = (int)(paramLong >>> 32);
    arrayOfByte[(i++)] = ((byte)(j >>> 24));
    arrayOfByte[(i++)] = ((byte)(j >>> 16));
    arrayOfByte[(i++)] = ((byte)(j >>> 8));
    arrayOfByte[(i++)] = ((byte)j);
    j = (int)paramLong;
    arrayOfByte[(i++)] = ((byte)(j >>> 24));
    arrayOfByte[(i++)] = ((byte)(j >>> 16));
    arrayOfByte[(i++)] = ((byte)(j >>> 8));
    arrayOfByte[(i++)] = ((byte)j);
    this.b = i;
    return this;
  }

  public ByteVector putUTF8(String paramString)
  {
    int i = paramString.length();
    if (this.b + 2 + i > this.a.length)
      a(2 + i);
    int j = this.b;
    byte[] arrayOfByte = this.a;
    arrayOfByte[(j++)] = ((byte)(i >>> 8));
    arrayOfByte[(j++)] = ((byte)i);
    for (int k = 0; k < i; k++)
    {
      int m = paramString.charAt(k);
      if ((m >= 1) && (m <= 127))
      {
        arrayOfByte[(j++)] = ((byte)m);
      }
      else
      {
        int n = k;
        for (int i1 = k; i1 < i; i1++)
        {
          m = paramString.charAt(i1);
          if ((m >= 1) && (m <= 127))
            n++;
          else if (m > 2047)
            n += 3;
          else
            n += 2;
        }
        arrayOfByte[this.b] = ((byte)(n >>> 8));
        arrayOfByte[(this.b + 1)] = ((byte)n);
        if (this.b + 2 + n > arrayOfByte.length)
        {
          this.b = j;
          a(2 + n);
          arrayOfByte = this.a;
        }
        for (i1 = k; i1 < i; i1++)
        {
          m = paramString.charAt(i1);
          if ((m >= 1) && (m <= 127))
          {
            arrayOfByte[(j++)] = ((byte)m);
          }
          else if (m > 2047)
          {
            arrayOfByte[(j++)] = ((byte)(0xE0 | m >> 12 & 0xF));
            arrayOfByte[(j++)] = ((byte)(0x80 | m >> 6 & 0x3F));
            arrayOfByte[(j++)] = ((byte)(0x80 | m & 0x3F));
          }
          else
          {
            arrayOfByte[(j++)] = ((byte)(0xC0 | m >> 6 & 0x1F));
            arrayOfByte[(j++)] = ((byte)(0x80 | m & 0x3F));
          }
        }
        break;
      }
    }
    this.b = j;
    return this;
  }

  public ByteVector putByteArray(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (this.b + paramInt2 > this.a.length)
      a(paramInt2);
    if (paramArrayOfByte != null)
      System.arraycopy(paramArrayOfByte, paramInt1, this.a, this.b, paramInt2);
    this.b += paramInt2;
    return this;
  }

  private void a(int paramInt)
  {
    int i = 2 * this.a.length;
    int j = this.b + paramInt;
    byte[] arrayOfByte = new byte[i > j ? i : j];
    System.arraycopy(this.a, 0, arrayOfByte, 0, this.b);
    this.a = arrayOfByte;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.asm..ByteVector
 * JD-Core Version:    0.6.2
 */