package org.apache.http.entity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class InputStreamEntity extends AbstractHttpEntity
{
  private final InputStream content;
  private final long length;

  public InputStreamEntity(InputStream paramInputStream, long paramLong)
  {
    this(paramInputStream, paramLong, null);
  }

  public InputStreamEntity(InputStream paramInputStream, long paramLong, ContentType paramContentType)
  {
    if (paramInputStream == null)
      throw new IllegalArgumentException("Source input stream may not be null");
    this.content = paramInputStream;
    this.length = paramLong;
    if (paramContentType != null)
      setContentType(paramContentType.toString());
  }

  public boolean isRepeatable()
  {
    return false;
  }

  public long getContentLength()
  {
    return this.length;
  }

  public InputStream getContent()
    throws IOException
  {
    return this.content;
  }

  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    if (paramOutputStream == null)
      throw new IllegalArgumentException("Output stream may not be null");
    InputStream localInputStream = this.content;
    try
    {
      byte[] arrayOfByte = new byte[2048];
      int i;
      if (this.length < 0L)
        while ((i = localInputStream.read(arrayOfByte)) != -1)
          paramOutputStream.write(arrayOfByte, 0, i);
      for (long l = this.length; l > 0L; l -= i)
      {
        i = localInputStream.read(arrayOfByte, 0, (int)Math.min(2048L, l));
        if (i == -1)
          break;
        paramOutputStream.write(arrayOfByte, 0, i);
      }
    }
    finally
    {
      localInputStream.close();
    }
  }

  public boolean isStreaming()
  {
    return true;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.entity.InputStreamEntity
 * JD-Core Version:    0.6.2
 */