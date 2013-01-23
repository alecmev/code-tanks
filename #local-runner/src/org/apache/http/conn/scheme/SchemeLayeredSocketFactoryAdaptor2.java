package org.apache.http.conn.scheme;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.params.HttpParams;

@Deprecated
class SchemeLayeredSocketFactoryAdaptor2
  implements SchemeLayeredSocketFactory
{
  private final LayeredSchemeSocketFactory factory;

  SchemeLayeredSocketFactoryAdaptor2(LayeredSchemeSocketFactory paramLayeredSchemeSocketFactory)
  {
    this.factory = paramLayeredSchemeSocketFactory;
  }

  public Socket createSocket(HttpParams paramHttpParams)
    throws IOException
  {
    return this.factory.createSocket(paramHttpParams);
  }

  public Socket connectSocket(Socket paramSocket, InetSocketAddress paramInetSocketAddress1, InetSocketAddress paramInetSocketAddress2, HttpParams paramHttpParams)
    throws IOException, UnknownHostException, ConnectTimeoutException
  {
    return this.factory.connectSocket(paramSocket, paramInetSocketAddress1, paramInetSocketAddress2, paramHttpParams);
  }

  public boolean isSecure(Socket paramSocket)
    throws IllegalArgumentException
  {
    return this.factory.isSecure(paramSocket);
  }

  public Socket createLayeredSocket(Socket paramSocket, String paramString, int paramInt, HttpParams paramHttpParams)
    throws IOException, UnknownHostException
  {
    return this.factory.createLayeredSocket(paramSocket, paramString, paramInt, true);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.conn.scheme.SchemeLayeredSocketFactoryAdaptor2
 * JD-Core Version:    0.6.2
 */