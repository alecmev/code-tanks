package org.apache.http.conn.params;

import java.net.InetAddress;
import org.apache.http.HttpHost;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.params.HttpParams;

public class ConnRouteParams
{
  public static final HttpHost NO_HOST = new HttpHost("127.0.0.255", 0, "no-host");
  public static final HttpRoute NO_ROUTE = new HttpRoute(NO_HOST);

  public static HttpHost getDefaultProxy(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("Parameters must not be null.");
    HttpHost localHttpHost = (HttpHost)paramHttpParams.getParameter("http.route.default-proxy");
    if ((localHttpHost != null) && (NO_HOST.equals(localHttpHost)))
      localHttpHost = null;
    return localHttpHost;
  }

  public static HttpRoute getForcedRoute(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("Parameters must not be null.");
    HttpRoute localHttpRoute = (HttpRoute)paramHttpParams.getParameter("http.route.forced-route");
    if ((localHttpRoute != null) && (NO_ROUTE.equals(localHttpRoute)))
      localHttpRoute = null;
    return localHttpRoute;
  }

  public static InetAddress getLocalAddress(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("Parameters must not be null.");
    InetAddress localInetAddress = (InetAddress)paramHttpParams.getParameter("http.route.local-address");
    return localInetAddress;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.conn.params.ConnRouteParams
 * JD-Core Version:    0.6.2
 */