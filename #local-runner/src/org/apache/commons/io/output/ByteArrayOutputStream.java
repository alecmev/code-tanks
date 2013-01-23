package org.apache.commons.io.output;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ByteArrayOutputStream extends OutputStream
{
  private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
  private final List buffers = new ArrayList();
  private int currentBufferIndex;
  private int filledBufferSum;
  private byte[] currentBuffer;
  private int count;

  public ByteArrayOutputStream()
  {
    this(1024);
  }

  public ByteArrayOutputStream(int paramInt)
  {
    if (paramInt < 0)
      throw new IllegalArgumentException("Negative initial size: " + paramInt);
    synchronized (this)
    {
      needNewBuffer(paramInt);
    }
  }

  private void needNewBuffer(int paramInt)
  {
    if (this.currentBufferIndex < this.buffers.size() - 1)
    {
      this.filledBufferSum += this.currentBuffer.length;
      this.currentBufferIndex += 1;
      this.currentBuffer = ((byte[])this.buffers.get(this.currentBufferIndex));
    }
    else
    {
      int i;
      if (this.currentBuffer == null)
      {
        i = paramInt;
        this.filledBufferSum = 0;
      }
      else
      {
        i = Math.max(this.currentBuffer.length << 1, paramInt - this.filledBufferSum);
        this.filledBufferSum += this.currentBuffer.length;
      }
      this.currentBufferIndex += 1;
      this.currentBuffer = new byte[i];
      this.buffers.add(this.currentBuffer);
    }
  }

  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if ((paramInt1 < 0) || (paramInt1 > paramArrayOfByte.length) || (paramInt2 < 0) || (paramInt1 + paramInt2 > paramArrayOfByte.length) || (paramInt1 + paramInt2 < 0))
      throw new IndexOutOfBoundsException();
    if (paramInt2 == 0)
      return;
    synchronized (this)
    {
      int i = this.count + paramInt2;
      int j = paramInt2;
      int k = this.count - this.filledBufferSum;
      while (j > 0)
      {
        int m = Math.min(j, this.currentBuffer.length - k);
        System.arraycopy(paramArrayOfByte, paramInt1 + paramInt2 - j, this.currentBuffer, k, m);
        j -= m;
        if (j > 0)
        {
          needNewBuffer(i);
          k = 0;
        }
      }
      this.count = i;
    }
  }

  public synchronized void write(int paramInt)
  {
    int i = this.count - this.filledBufferSum;
    if (i == this.currentBuffer.length)
    {
      needNewBuffer(this.count + 1);
      i = 0;
    }
    this.currentBuffer[i] = ((byte)paramInt);
    this.count += 1;
  }

  public void close()
    throws IOException
  {
  }

  public synchronized byte[] toByteArray()
  {
    int i = this.count;
    if (i == 0)
      return EMPTY_BYTE_ARRAY;
    byte[] arrayOfByte1 = new byte[i];
    int j = 0;
    Iterator localIterator = this.buffers.iterator();
    while (localIterator.hasNext())
    {
      byte[] arrayOfByte2 = (byte[])localIterator.next();
      int k = Math.min(arrayOfByte2.length, i);
      System.arraycopy(arrayOfByte2, 0, arrayOfByte1, j, k);
      j += k;
      i -= k;
      if (i == 0)
        break;
    }
    return arrayOfByte1;
  }

  public String toString()
  {
    return new String(toByteArray());
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.commons.io.output.ByteArrayOutputStream
 * JD-Core Version:    0.6.2
 */