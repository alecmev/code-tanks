package org.apache.http.protocol;

import java.io.IOException;
import java.net.InetAddress;
import org.apache.http.HttpConnection;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpInetConnection;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolException;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;

public class RequestTargetHost
  implements HttpRequestInterceptor
{
  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    ProtocolVersion localProtocolVersion = paramHttpRequest.getRequestLine().getProtocolVersion();
    String str = paramHttpRequest.getRequestLine().getMethod();
    if ((str.equalsIgnoreCase("CONNECT")) && (localProtocolVersion.lessEquals(HttpVersion.HTTP_1_0)))
      return;
    if (!paramHttpRequest.containsHeader("Host"))
    {
      HttpHost localHttpHost = (HttpHost)paramHttpContext.getAttribute("http.target_host");
      if (localHttpHost == null)
      {
        HttpConnection localHttpConnection = (HttpConnection)paramHttpContext.getAttribute("http.connection");
        if ((localHttpConnection instanceof HttpInetConnection))
        {
          InetAddress localInetAddress = ((HttpInetConnection)localHttpConnection).getRemoteAddress();
          int i = ((HttpInetConnection)localHttpConnection).getRemotePort();
          if (localInetAddress != null)
            localHttpHost = new HttpHost(localInetAddress.getHostName(), i);
        }
        if (localHttpHost == null)
        {
          if (localProtocolVersion.lessEquals(HttpVersion.HTTP_1_0))
            return;
          throw new ProtocolException("Target host missing");
        }
      }
      paramHttpRequest.addHeader("Host", localHttpHost.toHostString());
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.protocol.RequestTargetHost
 * JD-Core Version:    0.6.2
 */