package org.apache.http.impl.client;

import java.net.URI;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.RequestLine;
import org.apache.http.client.RedirectHandler;
import org.apache.http.client.RedirectStrategy;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.protocol.HttpContext;

@Deprecated
class DefaultRedirectStrategyAdaptor
  implements RedirectStrategy
{
  private final RedirectHandler handler;

  public boolean isRedirected(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws ProtocolException
  {
    return this.handler.isRedirectRequested(paramHttpResponse, paramHttpContext);
  }

  public HttpUriRequest getRedirect(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws ProtocolException
  {
    URI localURI = this.handler.getLocationURI(paramHttpResponse, paramHttpContext);
    String str = paramHttpRequest.getRequestLine().getMethod();
    if (str.equalsIgnoreCase("HEAD"))
      return new HttpHead(localURI);
    return new HttpGet(localURI);
  }

  public RedirectHandler getHandler()
  {
    return this.handler;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.client.DefaultRedirectStrategyAdaptor
 * JD-Core Version:    0.6.2
 */