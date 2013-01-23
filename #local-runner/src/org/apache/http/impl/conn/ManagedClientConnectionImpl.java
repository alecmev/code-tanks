package org.apache.http.impl.conn;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ClientConnectionOperator;
import org.apache.http.conn.ManagedClientConnection;
import org.apache.http.conn.OperatedClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.routing.RouteTracker;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

class ManagedClientConnectionImpl
  implements ManagedClientConnection
{
  private final ClientConnectionManager manager;
  private final ClientConnectionOperator operator;
  private volatile HttpPoolEntry poolEntry;
  private volatile boolean reusable;
  private volatile long duration;

  ManagedClientConnectionImpl(ClientConnectionManager paramClientConnectionManager, ClientConnectionOperator paramClientConnectionOperator, HttpPoolEntry paramHttpPoolEntry)
  {
    if (paramClientConnectionManager == null)
      throw new IllegalArgumentException("Connection manager may not be null");
    if (paramClientConnectionOperator == null)
      throw new IllegalArgumentException("Connection operator may not be null");
    if (paramHttpPoolEntry == null)
      throw new IllegalArgumentException("HTTP pool entry may not be null");
    this.manager = paramClientConnectionManager;
    this.operator = paramClientConnectionOperator;
    this.poolEntry = paramHttpPoolEntry;
    this.reusable = false;
    this.duration = 9223372036854775807L;
  }

  HttpPoolEntry getPoolEntry()
  {
    return this.poolEntry;
  }

  HttpPoolEntry detach()
  {
    HttpPoolEntry localHttpPoolEntry = this.poolEntry;
    this.poolEntry = null;
    return localHttpPoolEntry;
  }

  public ClientConnectionManager getManager()
  {
    return this.manager;
  }

  private OperatedClientConnection getConnection()
  {
    HttpPoolEntry localHttpPoolEntry = this.poolEntry;
    if (localHttpPoolEntry == null)
      return null;
    return (OperatedClientConnection)localHttpPoolEntry.getConnection();
  }

  private OperatedClientConnection ensureConnection()
  {
    HttpPoolEntry localHttpPoolEntry = this.poolEntry;
    if (localHttpPoolEntry == null)
      throw new ConnectionShutdownException();
    return (OperatedClientConnection)localHttpPoolEntry.getConnection();
  }

  private HttpPoolEntry ensurePoolEntry()
  {
    HttpPoolEntry localHttpPoolEntry = this.poolEntry;
    if (localHttpPoolEntry == null)
      throw new ConnectionShutdownException();
    return localHttpPoolEntry;
  }

  public void close()
    throws IOException
  {
    HttpPoolEntry localHttpPoolEntry = this.poolEntry;
    if (localHttpPoolEntry != null)
    {
      OperatedClientConnection localOperatedClientConnection = (OperatedClientConnection)localHttpPoolEntry.getConnection();
      localHttpPoolEntry.getTracker().reset();
      localOperatedClientConnection.close();
    }
  }

  public void shutdown()
    throws IOException
  {
    HttpPoolEntry localHttpPoolEntry = this.poolEntry;
    if (localHttpPoolEntry != null)
    {
      OperatedClientConnection localOperatedClientConnection = (OperatedClientConnection)localHttpPoolEntry.getConnection();
      localHttpPoolEntry.getTracker().reset();
      localOperatedClientConnection.shutdown();
    }
  }

  public boolean isOpen()
  {
    OperatedClientConnection localOperatedClientConnection = getConnection();
    if (localOperatedClientConnection != null)
      return localOperatedClientConnection.isOpen();
    return false;
  }

  public boolean isStale()
  {
    OperatedClientConnection localOperatedClientConnection = getConnection();
    if (localOperatedClientConnection != null)
      return localOperatedClientConnection.isStale();
    return true;
  }

  public void setSocketTimeout(int paramInt)
  {
    OperatedClientConnection localOperatedClientConnection = ensureConnection();
    localOperatedClientConnection.setSocketTimeout(paramInt);
  }

  public void flush()
    throws IOException
  {
    OperatedClientConnection localOperatedClientConnection = ensureConnection();
    localOperatedClientConnection.flush();
  }

  public boolean isResponseAvailable(int paramInt)
    throws IOException
  {
    OperatedClientConnection localOperatedClientConnection = ensureConnection();
    return localOperatedClientConnection.isResponseAvailable(paramInt);
  }

  public void receiveResponseEntity(HttpResponse paramHttpResponse)
    throws HttpException, IOException
  {
    OperatedClientConnection localOperatedClientConnection = ensureConnection();
    localOperatedClientConnection.receiveResponseEntity(paramHttpResponse);
  }

  public HttpResponse receiveResponseHeader()
    throws HttpException, IOException
  {
    OperatedClientConnection localOperatedClientConnection = ensureConnection();
    return localOperatedClientConnection.receiveResponseHeader();
  }

  public void sendRequestEntity(HttpEntityEnclosingRequest paramHttpEntityEnclosingRequest)
    throws HttpException, IOException
  {
    OperatedClientConnection localOperatedClientConnection = ensureConnection();
    localOperatedClientConnection.sendRequestEntity(paramHttpEntityEnclosingRequest);
  }

  public void sendRequestHeader(HttpRequest paramHttpRequest)
    throws HttpException, IOException
  {
    OperatedClientConnection localOperatedClientConnection = ensureConnection();
    localOperatedClientConnection.sendRequestHeader(paramHttpRequest);
  }

  public InetAddress getRemoteAddress()
  {
    OperatedClientConnection localOperatedClientConnection = ensureConnection();
    return localOperatedClientConnection.getRemoteAddress();
  }

  public int getRemotePort()
  {
    OperatedClientConnection localOperatedClientConnection = ensureConnection();
    return localOperatedClientConnection.getRemotePort();
  }

  public boolean isSecure()
  {
    OperatedClientConnection localOperatedClientConnection = ensureConnection();
    return localOperatedClientConnection.isSecure();
  }

  public SSLSession getSSLSession()
  {
    OperatedClientConnection localOperatedClientConnection = ensureConnection();
    SSLSession localSSLSession = null;
    Socket localSocket = localOperatedClientConnection.getSocket();
    if ((localSocket instanceof SSLSocket))
      localSSLSession = ((SSLSocket)localSocket).getSession();
    return localSSLSession;
  }

  public HttpRoute getRoute()
  {
    HttpPoolEntry localHttpPoolEntry = ensurePoolEntry();
    return localHttpPoolEntry.getEffectiveRoute();
  }

  public void open(HttpRoute paramHttpRoute, HttpContext paramHttpContext, HttpParams paramHttpParams)
    throws IOException
  {
    if (paramHttpRoute == null)
      throw new IllegalArgumentException("Route may not be null");
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    OperatedClientConnection localOperatedClientConnection;
    synchronized (this)
    {
      if (this.poolEntry == null)
        throw new ConnectionShutdownException();
      RouteTracker localRouteTracker1 = this.poolEntry.getTracker();
      if (localRouteTracker1.isConnected())
        throw new IllegalStateException("Connection already open");
      localOperatedClientConnection = (OperatedClientConnection)this.poolEntry.getConnection();
    }
    ??? = paramHttpRoute.getProxyHost();
    this.operator.openConnection(localOperatedClientConnection, ??? != null ? ??? : paramHttpRoute.getTargetHost(), paramHttpRoute.getLocalAddress(), paramHttpContext, paramHttpParams);
    synchronized (this)
    {
      if (this.poolEntry == null)
        throw new InterruptedIOException();
      RouteTracker localRouteTracker2 = this.poolEntry.getTracker();
      if (??? == null)
        localRouteTracker2.connectTarget(localOperatedClientConnection.isSecure());
      else
        localRouteTracker2.connectProxy((HttpHost)???, localOperatedClientConnection.isSecure());
    }
  }

  public void tunnelTarget(boolean paramBoolean, HttpParams paramHttpParams)
    throws IOException
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    RouteTracker localRouteTracker;
    HttpHost localHttpHost;
    OperatedClientConnection localOperatedClientConnection;
    synchronized (this)
    {
      if (this.poolEntry == null)
        throw new ConnectionShutdownException();
      localRouteTracker = this.poolEntry.getTracker();
      if (!localRouteTracker.isConnected())
        throw new IllegalStateException("Connection not open");
      if (localRouteTracker.isTunnelled())
        throw new IllegalStateException("Connection is already tunnelled");
      localHttpHost = localRouteTracker.getTargetHost();
      localOperatedClientConnection = (OperatedClientConnection)this.poolEntry.getConnection();
    }
    localOperatedClientConnection.update(null, localHttpHost, paramBoolean, paramHttpParams);
    synchronized (this)
    {
      if (this.poolEntry == null)
        throw new InterruptedIOException();
      localRouteTracker = this.poolEntry.getTracker();
      localRouteTracker.tunnelTarget(paramBoolean);
    }
  }

  public void tunnelProxy(HttpHost paramHttpHost, boolean paramBoolean, HttpParams paramHttpParams)
    throws IOException
  {
    if (paramHttpHost == null)
      throw new IllegalArgumentException("Next proxy amy not be null");
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    RouteTracker localRouteTracker;
    OperatedClientConnection localOperatedClientConnection;
    synchronized (this)
    {
      if (this.poolEntry == null)
        throw new ConnectionShutdownException();
      localRouteTracker = this.poolEntry.getTracker();
      if (!localRouteTracker.isConnected())
        throw new IllegalStateException("Connection not open");
      localOperatedClientConnection = (OperatedClientConnection)this.poolEntry.getConnection();
    }
    localOperatedClientConnection.update(null, paramHttpHost, paramBoolean, paramHttpParams);
    synchronized (this)
    {
      if (this.poolEntry == null)
        throw new InterruptedIOException();
      localRouteTracker = this.poolEntry.getTracker();
      localRouteTracker.tunnelProxy(paramHttpHost, paramBoolean);
    }
  }

  public void layerProtocol(HttpContext paramHttpContext, HttpParams paramHttpParams)
    throws IOException
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    RouteTracker localRouteTracker;
    HttpHost localHttpHost;
    OperatedClientConnection localOperatedClientConnection;
    synchronized (this)
    {
      if (this.poolEntry == null)
        throw new ConnectionShutdownException();
      localRouteTracker = this.poolEntry.getTracker();
      if (!localRouteTracker.isConnected())
        throw new IllegalStateException("Connection not open");
      if (!localRouteTracker.isTunnelled())
        throw new IllegalStateException("Protocol layering without a tunnel not supported");
      if (localRouteTracker.isLayered())
        throw new IllegalStateException("Multiple protocol layering not supported");
      localHttpHost = localRouteTracker.getTargetHost();
      localOperatedClientConnection = (OperatedClientConnection)this.poolEntry.getConnection();
    }
    this.operator.updateSecureConnection(localOperatedClientConnection, localHttpHost, paramHttpContext, paramHttpParams);
    synchronized (this)
    {
      if (this.poolEntry == null)
        throw new InterruptedIOException();
      localRouteTracker = this.poolEntry.getTracker();
      localRouteTracker.layerProtocol(localOperatedClientConnection.isSecure());
    }
  }

  public void setState(Object paramObject)
  {
    HttpPoolEntry localHttpPoolEntry = ensurePoolEntry();
    localHttpPoolEntry.setState(paramObject);
  }

  public void markReusable()
  {
    this.reusable = true;
  }

  public boolean isMarkedReusable()
  {
    return this.reusable;
  }

  public void setIdleDuration(long paramLong, TimeUnit paramTimeUnit)
  {
    if (paramLong > 0L)
      this.duration = paramTimeUnit.toMillis(paramLong);
    else
      this.duration = -1L;
  }

  public void releaseConnection()
  {
    synchronized (this)
    {
      if (this.poolEntry == null)
        return;
      this.manager.releaseConnection(this, this.duration, TimeUnit.MILLISECONDS);
      this.poolEntry = null;
    }
  }

  public void abortConnection()
  {
    synchronized (this)
    {
      if (this.poolEntry == null)
        return;
      this.reusable = false;
      OperatedClientConnection localOperatedClientConnection = (OperatedClientConnection)this.poolEntry.getConnection();
      try
      {
        localOperatedClientConnection.shutdown();
      }
      catch (IOException localIOException)
      {
      }
      this.manager.releaseConnection(this, this.duration, TimeUnit.MILLISECONDS);
      this.poolEntry = null;
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.conn.ManagedClientConnectionImpl
 * JD-Core Version:    0.6.2
 */