package org.apache.http.impl;

import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.ParseException;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.TokenIterator;
import org.apache.http.message.BasicTokenIterator;
import org.apache.http.protocol.HttpContext;

public class DefaultConnectionReuseStrategy
  implements ConnectionReuseStrategy
{
  public boolean keepAlive(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    if (paramHttpResponse == null)
      throw new IllegalArgumentException("HTTP response may not be null.");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null.");
    ProtocolVersion localProtocolVersion = paramHttpResponse.getStatusLine().getProtocolVersion();
    Header localHeader = paramHttpResponse.getFirstHeader("Transfer-Encoding");
    TokenIterator localTokenIterator;
    if (localHeader != null)
    {
      if (!"chunked".equalsIgnoreCase(localHeader.getValue()))
        return false;
    }
    else
    {
      localObject = paramHttpResponse.getHeaders("Content-Length");
      if ((localObject == null) || (localObject.length != 1))
        return false;
      localTokenIterator = localObject[0];
      try
      {
        int i = Integer.parseInt(localTokenIterator.getValue());
        if (i < 0)
          return false;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        return false;
      }
    }
    Object localObject = paramHttpResponse.headerIterator("Connection");
    if (!((HeaderIterator)localObject).hasNext())
      localObject = paramHttpResponse.headerIterator("Proxy-Connection");
    if (((HeaderIterator)localObject).hasNext())
      try
      {
        localTokenIterator = createTokenIterator((HeaderIterator)localObject);
        int j = 0;
        while (localTokenIterator.hasNext())
        {
          String str = localTokenIterator.nextToken();
          if ("Close".equalsIgnoreCase(str))
            return false;
          if ("Keep-Alive".equalsIgnoreCase(str))
            j = 1;
        }
        if (j != 0)
          return true;
      }
      catch (ParseException localParseException)
      {
        return false;
      }
    return !localProtocolVersion.lessEquals(HttpVersion.HTTP_1_0);
  }

  protected TokenIterator createTokenIterator(HeaderIterator paramHeaderIterator)
  {
    return new BasicTokenIterator(paramHeaderIterator);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.DefaultConnectionReuseStrategy
 * JD-Core Version:    0.6.2
 */