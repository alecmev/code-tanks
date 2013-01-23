package org.apache.http.impl.entity;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;
import org.apache.http.ParseException;
import org.apache.http.ProtocolException;
import org.apache.http.entity.ContentLengthStrategy;
import org.apache.http.params.HttpParams;

public class LaxContentLengthStrategy
  implements ContentLengthStrategy
{
  private final int implicitLen;

  public LaxContentLengthStrategy(int paramInt)
  {
    this.implicitLen = paramInt;
  }

  public LaxContentLengthStrategy()
  {
    this(-1);
  }

  public long determineLength(HttpMessage paramHttpMessage)
    throws HttpException
  {
    if (paramHttpMessage == null)
      throw new IllegalArgumentException("HTTP message may not be null");
    HttpParams localHttpParams = paramHttpMessage.getParams();
    boolean bool = localHttpParams.isParameterTrue("http.protocol.strict-transfer-encoding");
    Header localHeader1 = paramHttpMessage.getFirstHeader("Transfer-Encoding");
    if (localHeader1 != null)
    {
      localObject = null;
      try
      {
        localObject = localHeader1.getElements();
      }
      catch (ParseException localParseException)
      {
        throw new ProtocolException("Invalid Transfer-Encoding header value: " + localHeader1, localParseException);
      }
      if (bool)
        for (i = 0; i < localObject.length; i++)
        {
          String str = localObject[i].getName();
          if ((str != null) && (str.length() > 0) && (!str.equalsIgnoreCase("chunked")) && (!str.equalsIgnoreCase("identity")))
            throw new ProtocolException("Unsupported transfer encoding: " + str);
        }
      int i = localObject.length;
      if ("identity".equalsIgnoreCase(localHeader1.getValue()))
        return -1L;
      if ((i > 0) && ("chunked".equalsIgnoreCase(localObject[(i - 1)].getName())))
        return -2L;
      if (bool)
        throw new ProtocolException("Chunk-encoding must be the last one applied");
      return -1L;
    }
    Object localObject = paramHttpMessage.getFirstHeader("Content-Length");
    if (localObject != null)
    {
      long l = -1L;
      Header[] arrayOfHeader = paramHttpMessage.getHeaders("Content-Length");
      if ((bool) && (arrayOfHeader.length > 1))
        throw new ProtocolException("Multiple content length headers");
      int j = arrayOfHeader.length - 1;
      while (j >= 0)
      {
        Header localHeader2 = arrayOfHeader[j];
        try
        {
          l = Long.parseLong(localHeader2.getValue());
        }
        catch (NumberFormatException localNumberFormatException)
        {
          if (bool)
            throw new ProtocolException("Invalid content length: " + localHeader2.getValue());
          j--;
        }
      }
      if (l >= 0L)
        return l;
      return -1L;
    }
    return this.implicitLen;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.entity.LaxContentLengthStrategy
 * JD-Core Version:    0.6.2
 */