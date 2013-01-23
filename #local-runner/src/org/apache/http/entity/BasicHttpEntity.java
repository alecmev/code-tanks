package org.apache.http.entity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BasicHttpEntity extends AbstractHttpEntity
{
  private InputStream content;
  private long length = -1L;

  public long getContentLength()
  {
    return this.length;
  }

  public InputStream getContent()
    throws IllegalStateException
  {
    if (this.content == null)
      throw new IllegalStateException("Content has not been provided");
    return this.content;
  }

  public boolean isRepeatable()
  {
    return false;
  }

  public void setContentLength(long paramLong)
  {
    this.length = paramLong;
  }

  public void setContent(InputStream paramInputStream)
  {
    this.content = paramInputStream;
  }

  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    if (paramOutputStream == null)
      throw new IllegalArgumentException("Output stream may not be null");
    InputStream localInputStream = getContent();
    try
    {
      byte[] arrayOfByte = new byte[2048];
      int i;
      while ((i = localInputStream.read(arrayOfByte)) != -1)
        paramOutputStream.write(arrayOfByte, 0, i);
    }
    finally
    {
      localInputStream.close();
    }
  }

  public boolean isStreaming()
  {
    return this.content != null;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.entity.BasicHttpEntity
 * JD-Core Version:    0.6.2
 */