package org.apache.http.conn;

import javax.net.ssl.SSLSession;
import org.apache.http.HttpInetConnection;
import org.apache.http.conn.routing.HttpRoute;

public abstract interface HttpRoutedConnection extends HttpInetConnection
{
  public abstract boolean isSecure();

  public abstract HttpRoute getRoute();

  public abstract SSLSession getSSLSession();
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.conn.HttpRoutedConnection
 * JD-Core Version:    0.6.2
 */