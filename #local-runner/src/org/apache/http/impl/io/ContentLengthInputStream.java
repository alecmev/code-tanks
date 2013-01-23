package org.apache.http.impl.io;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.ConnectionClosedException;
import org.apache.http.io.BufferInfo;
import org.apache.http.io.SessionInputBuffer;

public class ContentLengthInputStream extends InputStream
{
  private long contentLength;
  private long pos = 0L;
  private boolean closed = false;
  private SessionInputBuffer in = null;

  public ContentLengthInputStream(SessionInputBuffer paramSessionInputBuffer, long paramLong)
  {
    if (paramSessionInputBuffer == null)
      throw new IllegalArgumentException("Input stream may not be null");
    if (paramLong < 0L)
      throw new IllegalArgumentException("Content length may not be negative");
    this.in = paramSessionInputBuffer;
    this.contentLength = paramLong;
  }

  public void close()
    throws IOException
  {
    if (!this.closed)
      try
      {
        if (this.pos < this.contentLength)
        {
          byte[] arrayOfByte = new byte[2048];
          while (read(arrayOfByte) >= 0);
        }
      }
      finally
      {
        this.closed = true;
      }
  }

  public int available()
    throws IOException
  {
    if ((this.in instanceof BufferInfo))
    {
      int i = ((BufferInfo)this.in).length();
      return Math.min(i, (int)(this.contentLength - this.pos));
    }
    return 0;
  }

  public int read()
    throws IOException
  {
    if (this.closed)
      throw new IOException("Attempted read from closed stream.");
    if (this.pos >= this.contentLength)
      return -1;
    int i = this.in.read();
    if (i == -1)
    {
      if (this.pos < this.contentLength)
        throw new ConnectionClosedException("Premature end of Content-Length delimited message body (expected: " + this.contentLength + "; received: " + this.pos);
    }
    else
      this.pos += 1L;
    return i;
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this.closed)
      throw new IOException("Attempted read from closed stream.");
    if (this.pos >= this.contentLength)
      return -1;
    if (this.pos + paramInt2 > this.contentLength)
      paramInt2 = (int)(this.contentLength - this.pos);
    int i = this.in.read(paramArrayOfByte, paramInt1, paramInt2);
    if ((i == -1) && (this.pos < this.contentLength))
      throw new ConnectionClosedException("Premature end of Content-Length delimited message body (expected: " + this.contentLength + "; received: " + this.pos);
    if (i > 0)
      this.pos += i;
    return i;
  }

  public int read(byte[] paramArrayOfByte)
    throws IOException
  {
    return read(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public long skip(long paramLong)
    throws IOException
  {
    if (paramLong <= 0L)
      return 0L;
    byte[] arrayOfByte = new byte[2048];
    long l1 = Math.min(paramLong, this.contentLength - this.pos);
    long l2 = 0L;
    while (l1 > 0L)
    {
      int i = read(arrayOfByte, 0, (int)Math.min(2048L, l1));
      if (i == -1)
        break;
      l2 += i;
      l1 -= i;
    }
    return l2;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.io.ContentLengthInputStream
 * JD-Core Version:    0.6.2
 */