package org.apache.http.impl.entity;

import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolException;
import org.apache.http.ProtocolVersion;
import org.apache.http.entity.ContentLengthStrategy;

public class StrictContentLengthStrategy
  implements ContentLengthStrategy
{
  private final int implicitLen;

  public StrictContentLengthStrategy(int paramInt)
  {
    this.implicitLen = paramInt;
  }

  public StrictContentLengthStrategy()
  {
    this(-1);
  }

  public long determineLength(HttpMessage paramHttpMessage)
    throws HttpException
  {
    if (paramHttpMessage == null)
      throw new IllegalArgumentException("HTTP message may not be null");
    Header localHeader = paramHttpMessage.getFirstHeader("Transfer-Encoding");
    if (localHeader != null)
    {
      localObject = localHeader.getValue();
      if ("chunked".equalsIgnoreCase((String)localObject))
      {
        if (paramHttpMessage.getProtocolVersion().lessEquals(HttpVersion.HTTP_1_0))
          throw new ProtocolException("Chunked transfer encoding not allowed for " + paramHttpMessage.getProtocolVersion());
        return -2L;
      }
      if ("identity".equalsIgnoreCase((String)localObject))
        return -1L;
      throw new ProtocolException("Unsupported transfer encoding: " + (String)localObject);
    }
    Object localObject = paramHttpMessage.getFirstHeader("Content-Length");
    if (localObject != null)
    {
      String str = ((Header)localObject).getValue();
      try
      {
        long l = Long.parseLong(str);
        if (l < 0L)
          throw new ProtocolException("Negative content length: " + str);
        return l;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw new ProtocolException("Invalid content length: " + str);
      }
    }
    return this.implicitLen;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.entity.StrictContentLengthStrategy
 * JD-Core Version:    0.6.2
 */