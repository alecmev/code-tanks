package org.apache.http.impl.conn;

import java.net.InetAddress;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.protocol.HttpContext;

public class DefaultHttpRoutePlanner
  implements HttpRoutePlanner
{
  protected final SchemeRegistry schemeRegistry;

  public DefaultHttpRoutePlanner(SchemeRegistry paramSchemeRegistry)
  {
    if (paramSchemeRegistry == null)
      throw new IllegalArgumentException("SchemeRegistry must not be null.");
    this.schemeRegistry = paramSchemeRegistry;
  }

  public HttpRoute determineRoute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException
  {
    if (paramHttpRequest == null)
      throw new IllegalStateException("Request must not be null.");
    HttpRoute localHttpRoute = ConnRouteParams.getForcedRoute(paramHttpRequest.getParams());
    if (localHttpRoute != null)
      return localHttpRoute;
    if (paramHttpHost == null)
      throw new IllegalStateException("Target host must not be null.");
    InetAddress localInetAddress = ConnRouteParams.getLocalAddress(paramHttpRequest.getParams());
    HttpHost localHttpHost = ConnRouteParams.getDefaultProxy(paramHttpRequest.getParams());
    Scheme localScheme;
    try
    {
      localScheme = this.schemeRegistry.getScheme(paramHttpHost.getSchemeName());
    }
    catch (IllegalStateException localIllegalStateException)
    {
      throw new HttpException(localIllegalStateException.getMessage());
    }
    boolean bool = localScheme.isLayered();
    if (localHttpHost == null)
      localHttpRoute = new HttpRoute(paramHttpHost, localInetAddress, bool);
    else
      localHttpRoute = new HttpRoute(paramHttpHost, localInetAddress, localHttpHost, bool);
    return localHttpRoute;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.conn.DefaultHttpRoutePlanner
 * JD-Core Version:    0.6.2
 */