package org.apache.http.impl.io;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.http.io.SessionOutputBuffer;

public class IdentityOutputStream extends OutputStream
{
  private final SessionOutputBuffer out;
  private boolean closed = false;

  public IdentityOutputStream(SessionOutputBuffer paramSessionOutputBuffer)
  {
    if (paramSessionOutputBuffer == null)
      throw new IllegalArgumentException("Session output buffer may not be null");
    this.out = paramSessionOutputBuffer;
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
    this.out.write(paramArrayOfByte, paramInt1, paramInt2);
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
    this.out.write(paramInt);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.io.IdentityOutputStream
 * JD-Core Version:    0.6.2
 */