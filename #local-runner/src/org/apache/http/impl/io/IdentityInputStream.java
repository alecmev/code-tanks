package org.apache.http.impl.io;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.io.BufferInfo;
import org.apache.http.io.SessionInputBuffer;

public class IdentityInputStream extends InputStream
{
  private final SessionInputBuffer in;
  private boolean closed = false;

  public IdentityInputStream(SessionInputBuffer paramSessionInputBuffer)
  {
    if (paramSessionInputBuffer == null)
      throw new IllegalArgumentException("Session input buffer may not be null");
    this.in = paramSessionInputBuffer;
  }

  public int available()
    throws IOException
  {
    if ((this.in instanceof BufferInfo))
      return ((BufferInfo)this.in).length();
    return 0;
  }

  public void close()
    throws IOException
  {
    this.closed = true;
  }

  public int read()
    throws IOException
  {
    if (this.closed)
      return -1;
    return this.in.read();
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this.closed)
      return -1;
    return this.in.read(paramArrayOfByte, paramInt1, paramInt2);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.io.IdentityInputStream
 * JD-Core Version:    0.6.2
 */