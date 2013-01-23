package org.apache.http.client.protocol;

import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.RequestLine;
import org.apache.http.conn.HttpRoutedConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.protocol.HttpContext;

public class RequestClientConnControl
  implements HttpRequestInterceptor
{
  private final Log log = LogFactory.getLog(getClass());

  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    String str = paramHttpRequest.getRequestLine().getMethod();
    if (str.equalsIgnoreCase("CONNECT"))
    {
      paramHttpRequest.setHeader("Proxy-Connection", "Keep-Alive");
      return;
    }
    HttpRoutedConnection localHttpRoutedConnection = (HttpRoutedConnection)paramHttpContext.getAttribute("http.connection");
    if (localHttpRoutedConnection == null)
    {
      this.log.debug("HTTP connection not set in the context");
      return;
    }
    HttpRoute localHttpRoute = localHttpRoutedConnection.getRoute();
    if (((localHttpRoute.getHopCount() == 1) || (localHttpRoute.isTunnelled())) && (!paramHttpRequest.containsHeader("Connection")))
      paramHttpRequest.addHeader("Connection", "Keep-Alive");
    if ((localHttpRoute.getHopCount() == 2) && (!localHttpRoute.isTunnelled()) && (!paramHttpRequest.containsHeader("Proxy-Connection")))
      paramHttpRequest.addHeader("Proxy-Connection", "Keep-Alive");
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.client.protocol.RequestClientConnControl
 * JD-Core Version:    0.6.2
 */