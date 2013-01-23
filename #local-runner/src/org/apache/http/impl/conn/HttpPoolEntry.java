package org.apache.http.impl.conn;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.apache.commons.logging.Log;
import org.apache.http.conn.OperatedClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.routing.RouteTracker;
import org.apache.http.pool.PoolEntry;

class HttpPoolEntry extends PoolEntry
{
  private final Log log;
  private final RouteTracker tracker;

  public HttpPoolEntry(Log paramLog, String paramString, HttpRoute paramHttpRoute, OperatedClientConnection paramOperatedClientConnection, long paramLong, TimeUnit paramTimeUnit)
  {
    super(paramString, paramHttpRoute, paramOperatedClientConnection, paramLong, paramTimeUnit);
    this.log = paramLog;
    this.tracker = new RouteTracker(paramHttpRoute);
  }

  public boolean isExpired(long paramLong)
  {
    boolean bool = super.isExpired(paramLong);
    if ((bool) && (this.log.isDebugEnabled()))
      this.log.debug("Connection " + this + " expired @ " + new Date(getExpiry()));
    return bool;
  }

  RouteTracker getTracker()
  {
    return this.tracker;
  }

  HttpRoute getPlannedRoute()
  {
    return (HttpRoute)getRoute();
  }

  HttpRoute getEffectiveRoute()
  {
    return this.tracker.toRoute();
  }

  public boolean isClosed()
  {
    OperatedClientConnection localOperatedClientConnection = (OperatedClientConnection)getConnection();
    return !localOperatedClientConnection.isOpen();
  }

  public void close()
  {
    OperatedClientConnection localOperatedClientConnection = (OperatedClientConnection)getConnection();
    try
    {
      localOperatedClientConnection.close();
    }
    catch (IOException localIOException)
    {
      this.log.debug("I/O error closing connection", localIOException);
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.conn.HttpPoolEntry
 * JD-Core Version:    0.6.2
 */