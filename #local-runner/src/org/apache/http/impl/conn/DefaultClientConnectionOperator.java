package org.apache.http.impl.conn;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpHost;
import org.apache.http.conn.ClientConnectionOperator;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.DnsResolver;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.conn.HttpInetSocketAddress;
import org.apache.http.conn.OperatedClientConnection;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeLayeredSocketFactory;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SchemeSocketFactory;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

public class DefaultClientConnectionOperator
  implements ClientConnectionOperator
{
  private final Log log = LogFactory.getLog(getClass());
  protected final SchemeRegistry schemeRegistry;
  protected final DnsResolver dnsResolver;

  public DefaultClientConnectionOperator(SchemeRegistry paramSchemeRegistry)
  {
    if (paramSchemeRegistry == null)
      throw new IllegalArgumentException("Scheme registry amy not be null");
    this.schemeRegistry = paramSchemeRegistry;
    this.dnsResolver = new SystemDefaultDnsResolver();
  }

  public OperatedClientConnection createConnection()
  {
    return new DefaultClientConnection();
  }

  public void openConnection(OperatedClientConnection paramOperatedClientConnection, HttpHost paramHttpHost, InetAddress paramInetAddress, HttpContext paramHttpContext, HttpParams paramHttpParams)
    throws IOException
  {
    if (paramOperatedClientConnection == null)
      throw new IllegalArgumentException("Connection may not be null");
    if (paramHttpHost == null)
      throw new IllegalArgumentException("Target host may not be null");
    if (paramHttpParams == null)
      throw new IllegalArgumentException("Parameters may not be null");
    if (paramOperatedClientConnection.isOpen())
      throw new IllegalStateException("Connection must not be open");
    Scheme localScheme = this.schemeRegistry.getScheme(paramHttpHost.getSchemeName());
    SchemeSocketFactory localSchemeSocketFactory = localScheme.getSchemeSocketFactory();
    InetAddress[] arrayOfInetAddress = resolveHostname(paramHttpHost.getHostName());
    int i = localScheme.resolvePort(paramHttpHost.getPort());
    for (int j = 0; j < arrayOfInetAddress.length; j++)
    {
      InetAddress localInetAddress = arrayOfInetAddress[j];
      int k = j == arrayOfInetAddress.length - 1 ? 1 : 0;
      Object localObject = localSchemeSocketFactory.createSocket(paramHttpParams);
      paramOperatedClientConnection.opening((Socket)localObject, paramHttpHost);
      HttpInetSocketAddress localHttpInetSocketAddress = new HttpInetSocketAddress(paramHttpHost, localInetAddress, i);
      InetSocketAddress localInetSocketAddress = null;
      if (paramInetAddress != null)
        localInetSocketAddress = new InetSocketAddress(paramInetAddress, 0);
      if (this.log.isDebugEnabled())
        this.log.debug("Connecting to " + localHttpInetSocketAddress);
      try
      {
        Socket localSocket = localSchemeSocketFactory.connectSocket((Socket)localObject, localHttpInetSocketAddress, localInetSocketAddress, paramHttpParams);
        if (localObject != localSocket)
        {
          localObject = localSocket;
          paramOperatedClientConnection.opening((Socket)localObject, paramHttpHost);
        }
        prepareSocket((Socket)localObject, paramHttpContext, paramHttpParams);
        paramOperatedClientConnection.openCompleted(localSchemeSocketFactory.isSecure((Socket)localObject), paramHttpParams);
        return;
      }
      catch (ConnectException localConnectException)
      {
        if (k != 0)
          throw new HttpHostConnectException(paramHttpHost, localConnectException);
      }
      catch (ConnectTimeoutException localConnectTimeoutException)
      {
        if (k != 0)
          throw localConnectTimeoutException;
      }
      if (this.log.isDebugEnabled())
        this.log.debug("Connect to " + localHttpInetSocketAddress + " timed out. " + "Connection will be retried using another IP address");
    }
  }

  public void updateSecureConnection(OperatedClientConnection paramOperatedClientConnection, HttpHost paramHttpHost, HttpContext paramHttpContext, HttpParams paramHttpParams)
    throws IOException
  {
    if (paramOperatedClientConnection == null)
      throw new IllegalArgumentException("Connection may not be null");
    if (paramHttpHost == null)
      throw new IllegalArgumentException("Target host may not be null");
    if (paramHttpParams == null)
      throw new IllegalArgumentException("Parameters may not be null");
    if (!paramOperatedClientConnection.isOpen())
      throw new IllegalStateException("Connection must be open");
    Scheme localScheme = this.schemeRegistry.getScheme(paramHttpHost.getSchemeName());
    if (!(localScheme.getSchemeSocketFactory() instanceof SchemeLayeredSocketFactory))
      throw new IllegalArgumentException("Target scheme (" + localScheme.getName() + ") must have layered socket factory.");
    SchemeLayeredSocketFactory localSchemeLayeredSocketFactory = (SchemeLayeredSocketFactory)localScheme.getSchemeSocketFactory();
    Socket localSocket;
    try
    {
      localSocket = localSchemeLayeredSocketFactory.createLayeredSocket(paramOperatedClientConnection.getSocket(), paramHttpHost.getHostName(), paramHttpHost.getPort(), paramHttpParams);
    }
    catch (ConnectException localConnectException)
    {
      throw new HttpHostConnectException(paramHttpHost, localConnectException);
    }
    prepareSocket(localSocket, paramHttpContext, paramHttpParams);
    paramOperatedClientConnection.update(localSocket, paramHttpHost, localSchemeLayeredSocketFactory.isSecure(localSocket), paramHttpParams);
  }

  protected void prepareSocket(Socket paramSocket, HttpContext paramHttpContext, HttpParams paramHttpParams)
    throws IOException
  {
    paramSocket.setTcpNoDelay(HttpConnectionParams.getTcpNoDelay(paramHttpParams));
    paramSocket.setSoTimeout(HttpConnectionParams.getSoTimeout(paramHttpParams));
    int i = HttpConnectionParams.getLinger(paramHttpParams);
    if (i >= 0)
      paramSocket.setSoLinger(i > 0, i);
  }

  protected InetAddress[] resolveHostname(String paramString)
    throws UnknownHostException
  {
    return this.dnsResolver.resolve(paramString);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.conn.DefaultClientConnectionOperator
 * JD-Core Version:    0.6.2
 */