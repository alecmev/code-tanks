package org.apache.http.impl.conn;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ClientConnectionOperator;
import org.apache.http.conn.ClientConnectionRequest;
import org.apache.http.conn.ManagedClientConnection;
import org.apache.http.conn.OperatedClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.routing.RouteTracker;
import org.apache.http.conn.scheme.SchemeRegistry;

public class BasicClientConnectionManager
  implements ClientConnectionManager
{
  private final Log log = LogFactory.getLog(getClass());
  private static final AtomicLong COUNTER = new AtomicLong();
  private final SchemeRegistry schemeRegistry;
  private final ClientConnectionOperator connOperator;
  private HttpPoolEntry poolEntry;
  private ManagedClientConnectionImpl conn;
  private volatile boolean shutdown;

  public BasicClientConnectionManager(SchemeRegistry paramSchemeRegistry)
  {
    if (paramSchemeRegistry == null)
      throw new IllegalArgumentException("Scheme registry may not be null");
    this.schemeRegistry = paramSchemeRegistry;
    this.connOperator = createConnectionOperator(paramSchemeRegistry);
  }

  public BasicClientConnectionManager()
  {
    this(SchemeRegistryFactory.createDefault());
  }

  protected void finalize()
    throws Throwable
  {
    try
    {
      shutdown();
    }
    finally
    {
      super.finalize();
    }
  }

  public SchemeRegistry getSchemeRegistry()
  {
    return this.schemeRegistry;
  }

  protected ClientConnectionOperator createConnectionOperator(SchemeRegistry paramSchemeRegistry)
  {
    return new DefaultClientConnectionOperator(paramSchemeRegistry);
  }

  public final ClientConnectionRequest requestConnection(final HttpRoute paramHttpRoute, final Object paramObject)
  {
    return new ClientConnectionRequest()
    {
      public void abortRequest()
      {
      }

      public ManagedClientConnection getConnection(long paramAnonymousLong, TimeUnit paramAnonymousTimeUnit)
      {
        return BasicClientConnectionManager.this.getConnection(paramHttpRoute, paramObject);
      }
    };
  }

  private void assertNotShutdown()
  {
    if (this.shutdown)
      throw new IllegalStateException("Connection manager has been shut down");
  }

  ManagedClientConnection getConnection(HttpRoute paramHttpRoute, Object paramObject)
  {
    if (paramHttpRoute == null)
      throw new IllegalArgumentException("Route may not be null.");
    assertNotShutdown();
    if (this.log.isDebugEnabled())
      this.log.debug("Get connection for route " + paramHttpRoute);
    synchronized (this)
    {
      if (this.conn != null)
        throw new IllegalStateException("Invalid use of BasicClientConnManager: connection still allocated.\nMake sure to release the connection before allocating another one.");
      if ((this.poolEntry != null) && (!this.poolEntry.getPlannedRoute().equals(paramHttpRoute)))
      {
        this.poolEntry.close();
        this.poolEntry = null;
      }
      if (this.poolEntry == null)
      {
        String str = Long.toString(COUNTER.getAndIncrement());
        OperatedClientConnection localOperatedClientConnection = this.connOperator.createConnection();
        this.poolEntry = new HttpPoolEntry(this.log, str, paramHttpRoute, localOperatedClientConnection, 0L, TimeUnit.MILLISECONDS);
      }
      long l = System.currentTimeMillis();
      if (this.poolEntry.isExpired(l))
      {
        this.poolEntry.close();
        this.poolEntry.getTracker().reset();
      }
      this.conn = new ManagedClientConnectionImpl(this, this.connOperator, this.poolEntry);
      return this.conn;
    }
  }

  public void releaseConnection(ManagedClientConnection paramManagedClientConnection, long paramLong, TimeUnit paramTimeUnit)
  {
    assertNotShutdown();
    if (!(paramManagedClientConnection instanceof ManagedClientConnectionImpl))
      throw new IllegalArgumentException("Connection class mismatch, connection not obtained from this manager");
    if (this.log.isDebugEnabled())
      this.log.debug("Releasing connection " + paramManagedClientConnection);
    ManagedClientConnectionImpl localManagedClientConnectionImpl = (ManagedClientConnectionImpl)paramManagedClientConnection;
    synchronized (localManagedClientConnectionImpl)
    {
      if (localManagedClientConnectionImpl.getPoolEntry() == null)
        return;
      ClientConnectionManager localClientConnectionManager = localManagedClientConnectionImpl.getManager();
      if ((localClientConnectionManager != null) && (localClientConnectionManager != this))
        throw new IllegalStateException("Connection not obtained from this manager");
      synchronized (this)
      {
        try
        {
          if ((localManagedClientConnectionImpl.isOpen()) && (!localManagedClientConnectionImpl.isMarkedReusable()))
            try
            {
              localManagedClientConnectionImpl.shutdown();
            }
            catch (IOException localIOException)
            {
              if (this.log.isDebugEnabled())
                this.log.debug("I/O exception shutting down released connection", localIOException);
            }
          this.poolEntry.updateExpiry(paramLong, paramTimeUnit != null ? paramTimeUnit : TimeUnit.MILLISECONDS);
          if (this.log.isDebugEnabled())
          {
            String str;
            if (paramLong > 0L)
              str = "for " + paramLong + " " + paramTimeUnit;
            else
              str = "indefinitely";
            this.log.debug("Connection can be kept alive " + str);
          }
        }
        finally
        {
          localManagedClientConnectionImpl.detach();
          this.conn = null;
          if (this.poolEntry.isClosed())
            this.poolEntry = null;
        }
      }
    }
  }

  public void shutdown()
  {
    this.shutdown = true;
    synchronized (this)
    {
      try
      {
        if (this.poolEntry != null)
          this.poolEntry.close();
      }
      finally
      {
        this.poolEntry = null;
        this.conn = null;
      }
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.conn.BasicClientConnectionManager
 * JD-Core Version:    0.6.2
 */