package org.apache.http.impl.io;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.http.io.SessionOutputBuffer;

public class ContentLengthOutputStream extends OutputStream
{
  private final SessionOutputBuffer out;
  private final long contentLength;
  private long total = 0L;
  private boolean closed = false;

  public ContentLengthOutputStream(SessionOutputBuffer paramSessionOutputBuffer, long paramLong)
  {
    if (paramSessionOutputBuffer == null)
      throw new IllegalArgumentException("Session output buffer may not be null");
    if (paramLong < 0L)
      throw new IllegalArgumentException("Content length may not be negative");
    this.out = paramSessionOutputBuffer;
    this.contentLength = paramLong;
  }

  public void close()
    throws IOException
  {
    if (!this.closed)
    {
      this.closed = true;
      this.out.flush();
    }
  }

  public void flush()
    throws IOException
  {
    this.out.flush();
  }

  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this.closed)
      throw new IOException("Attempted write to closed stream.");
    if (this.total < this.contentLength)
    {
      long l = this.contentLength - this.total;
      if (paramInt2 > l)
        paramInt2 = (int)l;
      this.out.write(paramArrayOfByte, paramInt1, paramInt2);
      this.total += paramInt2;
    }
  }

  public void write(byte[] paramArrayOfByte)
    throws IOException
  {
    write(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public void write(int paramInt)
    throws IOException
  {
    if (this.closed)
      throw new IOException("Attempted write to closed stream.");
    if (this.total < this.contentLength)
    {
      this.out.write(paramInt);
      this.total += 1L;
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.io.ContentLengthOutputStream
 * JD-Core Version:    0.6.2
 */