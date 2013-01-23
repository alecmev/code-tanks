package org.apache.http.impl.auth;

import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;

class HttpEntityDigester extends OutputStream
{
  private final MessageDigest digester;
  private boolean closed;
  private byte[] digest;

  HttpEntityDigester(MessageDigest paramMessageDigest)
  {
    this.digester = paramMessageDigest;
    this.digester.reset();
  }

  public void write(int paramInt)
    throws IOException
  {
    if (this.closed)
      throw new IOException("Stream has been already closed");
    this.digester.update((byte)paramInt);
  }

  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this.closed)
      throw new IOException("Stream has been already closed");
    this.digester.update(paramArrayOfByte, paramInt1, paramInt2);
  }

  public void close()
    throws IOException
  {
    if (this.closed)
      return;
    this.closed = true;
    this.digest = this.digester.digest();
    super.close();
  }

  public byte[] getDigest()
  {
    return this.digest;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.auth.HttpEntityDigester
 * JD-Core Version:    0.6.2
 */