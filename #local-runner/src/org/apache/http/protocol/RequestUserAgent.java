package org.apache.http.protocol;

import java.io.IOException;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.params.HttpProtocolParams;

public class RequestUserAgent
  implements HttpRequestInterceptor
{
  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if (!paramHttpRequest.containsHeader("User-Agent"))
    {
      String str = HttpProtocolParams.getUserAgent(paramHttpRequest.getParams());
      if (str != null)
        paramHttpRequest.addHeader("User-Agent", str);
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.protocol.RequestUserAgent
 * JD-Core Version:    0.6.2
 */