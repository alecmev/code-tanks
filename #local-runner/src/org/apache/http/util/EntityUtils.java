package org.apache.http.util;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpEntity;

public final class EntityUtils
{
  public static void consume(HttpEntity paramHttpEntity)
    throws IOException
  {
    if (paramHttpEntity == null)
      return;
    if (paramHttpEntity.isStreaming())
    {
      InputStream localInputStream = paramHttpEntity.getContent();
      if (localInputStream != null)
        localInputStream.close();
    }
  }

  public static byte[] toByteArray(HttpEntity paramHttpEntity)
    throws IOException
  {
    if (paramHttpEntity == null)
      throw new IllegalArgumentException("HTTP entity may not be null");
    InputStream localInputStream = paramHttpEntity.getContent();
    if (localInputStream == null)
      return null;
    try
    {
      if (paramHttpEntity.getContentLength() > 2147483647L)
        throw new IllegalArgumentException("HTTP entity too large to be buffered in memory");
      int i = (int)paramHttpEntity.getContentLength();
      if (i < 0)
        i = 4096;
      ByteArrayBuffer localByteArrayBuffer = new ByteArrayBuffer(i);
      byte[] arrayOfByte1 = new byte[4096];
      int j;
      while ((j = localInputStream.read(arrayOfByte1)) != -1)
        localByteArrayBuffer.append(arrayOfByte1, 0, j);
      byte[] arrayOfByte2 = localByteArrayBuffer.toByteArray();
      return arrayOfByte2;
    }
    finally
    {
      localInputStream.close();
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.util.EntityUtils
 * JD-Core Version:    0.6.2
 */