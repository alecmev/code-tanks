package org.apache.http.conn;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpHost;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

public abstract interface ManagedClientConnection extends HttpClientConnection, ConnectionReleaseTrigger, HttpRoutedConnection
{
  public abstract HttpRoute getRoute();

  public abstract void open(HttpRoute paramHttpRoute, HttpContext paramHttpContext, HttpParams paramHttpParams)
    throws IOException;

  public abstract void tunnelTarget(boolean paramBoolean, HttpParams paramHttpParams)
    throws IOException;

  public abstract void tunnelProxy(HttpHost paramHttpHost, boolean paramBoolean, HttpParams paramHttpParams)
    throws IOException;

  public abstract void layerProtocol(HttpContext paramHttpContext, HttpParams paramHttpParams)
    throws IOException;

  public abstract void markReusable();

  public abstract void setState(Object paramObject);

  public abstract void setIdleDuration(long paramLong, TimeUnit paramTimeUnit);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.conn.ManagedClientConnection
 * JD-Core Version:    0.6.2
 */