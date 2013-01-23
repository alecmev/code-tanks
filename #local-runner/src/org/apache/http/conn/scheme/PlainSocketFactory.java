package org.apache.http.conn.scheme;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class PlainSocketFactory
  implements SchemeSocketFactory
{
  private final HostNameResolver nameResolver = null;

  public static PlainSocketFactory getSocketFactory()
  {
    return new PlainSocketFactory();
  }

  public Socket createSocket(HttpParams paramHttpParams)
  {
    return new Socket();
  }

  public Socket createSocket()
  {
    return new Socket();
  }

  public Socket connectSocket(Socket paramSocket, InetSocketAddress paramInetSocketAddress1, InetSocketAddress paramInetSocketAddress2, HttpParams paramHttpParams)
    throws IOException, ConnectTimeoutException
  {
    if (paramInetSocketAddress1 == null)
      throw new IllegalArgumentException("Remote address may not be null");
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    Socket localSocket = paramSocket;
    if (localSocket == null)
      localSocket = createSocket();
    if (paramInetSocketAddress2 != null)
    {
      localSocket.setReuseAddress(HttpConnectionParams.getSoReuseaddr(paramHttpParams));
      localSocket.bind(paramInetSocketAddress2);
    }
    int i = HttpConnectionParams.getConnectionTimeout(paramHttpParams);
    int j = HttpConnectionParams.getSoTimeout(paramHttpParams);
    try
    {
      localSocket.setSoTimeout(j);
      localSocket.connect(paramInetSocketAddress1, i);
    }
    catch (SocketTimeoutException localSocketTimeoutException)
    {
      throw new ConnectTimeoutException("Connect to " + paramInetSocketAddress1 + " timed out");
    }
    return localSocket;
  }

  public final boolean isSecure(Socket paramSocket)
    throws IllegalArgumentException
  {
    if (paramSocket == null)
      throw new IllegalArgumentException("Socket may not be null.");
    if (paramSocket.isClosed())
      throw new IllegalArgumentException("Socket is closed.");
    return false;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.conn.scheme.PlainSocketFactory
 * JD-Core Version:    0.6.2
 */