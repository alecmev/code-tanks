package org.apache.commons.codec.binary;

public abstract class BaseNCodec
{
  protected final byte PAD = 61;
  private final int unencodedBlockSize;
  private final int encodedBlockSize;
  protected final int lineLength;
  private final int chunkSeparatorLength;
  protected byte[] buffer;
  protected int pos;
  private int readPos;
  protected boolean eof;
  protected int currentLinePos;
  protected int modulus;

  protected BaseNCodec(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.unencodedBlockSize = paramInt1;
    this.encodedBlockSize = paramInt2;
    this.lineLength = ((paramInt3 > 0) && (paramInt4 > 0) ? paramInt3 / paramInt2 * paramInt2 : 0);
    this.chunkSeparatorLength = paramInt4;
  }

  int available()
  {
    return this.buffer != null ? this.pos - this.readPos : 0;
  }

  protected int getDefaultBufferSize()
  {
    return 8192;
  }

  private void resizeBuffer()
  {
    if (this.buffer == null)
    {
      this.buffer = new byte[getDefaultBufferSize()];
      this.pos = 0;
      this.readPos = 0;
    }
    else
    {
      byte[] arrayOfByte = new byte[this.buffer.length * 2];
      System.arraycopy(this.buffer, 0, arrayOfByte, 0, this.buffer.length);
      this.buffer = arrayOfByte;
    }
  }

  protected void ensureBufferSize(int paramInt)
  {
    if ((this.buffer == null) || (this.buffer.length < this.pos + paramInt))
      resizeBuffer();
  }

  int readResults(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (this.buffer != null)
    {
      int i = Math.min(available(), paramInt2);
      System.arraycopy(this.buffer, this.readPos, paramArrayOfByte, paramInt1, i);
      this.readPos += i;
      if (this.readPos >= this.pos)
        this.buffer = null;
      return i;
    }
    return this.eof ? -1 : 0;
  }

  private void reset()
  {
    this.buffer = null;
    this.pos = 0;
    this.readPos = 0;
    this.currentLinePos = 0;
    this.modulus = 0;
    this.eof = false;
  }

  public byte[] decode(byte[] paramArrayOfByte)
  {
    reset();
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0))
      return paramArrayOfByte;
    decode(paramArrayOfByte, 0, paramArrayOfByte.length);
    decode(paramArrayOfByte, 0, -1);
    byte[] arrayOfByte = new byte[this.pos];
    readResults(arrayOfByte, 0, arrayOfByte.length);
    return arrayOfByte;
  }

  public byte[] encode(byte[] paramArrayOfByte)
  {
    reset();
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0))
      return paramArrayOfByte;
    encode(paramArrayOfByte, 0, paramArrayOfByte.length);
    encode(paramArrayOfByte, 0, -1);
    byte[] arrayOfByte = new byte[this.pos - this.readPos];
    readResults(arrayOfByte, 0, arrayOfByte.length);
    return arrayOfByte;
  }

  abstract void encode(byte[] paramArrayOfByte, int paramInt1, int paramInt2);

  abstract void decode(byte[] paramArrayOfByte, int paramInt1, int paramInt2);

  protected abstract boolean isInAlphabet(byte paramByte);

  protected boolean containsAlphabetOrPad(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null)
      return false;
    for (byte b : paramArrayOfByte)
      if ((61 == b) || (isInAlphabet(b)))
        return true;
    return false;
  }

  public long getEncodedLength(byte[] paramArrayOfByte)
  {
    long l = (paramArrayOfByte.length + this.unencodedBlockSize - 1) / this.unencodedBlockSize * this.encodedBlockSize;
    if (this.lineLength > 0)
      l += (l + this.lineLength - 1L) / this.lineLength * this.chunkSeparatorLength;
    return l;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.commons.codec.binary.BaseNCodec
 * JD-Core Version:    0.6.2
 */