package org.apache.http.conn;

import java.util.concurrent.TimeUnit;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.SchemeRegistry;

public abstract interface ClientConnectionManager
{
  public abstract SchemeRegistry getSchemeRegistry();

  public abstract ClientConnectionRequest requestConnection(HttpRoute paramHttpRoute, Object paramObject);

  public abstract void releaseConnection(ManagedClientConnection paramManagedClientConnection, long paramLong, TimeUnit paramTimeUnit);

  public abstract void shutdown();
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.conn.ClientConnectionManager
 * JD-Core Version:    0.6.2
 */