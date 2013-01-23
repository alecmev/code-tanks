package org.apache.http.conn.scheme;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.params.HttpParams;

public abstract interface SchemeSocketFactory
{
  public abstract Socket createSocket(HttpParams paramHttpParams)
    throws IOException;

  public abstract Socket connectSocket(Socket paramSocket, InetSocketAddress paramInetSocketAddress1, InetSocketAddress paramInetSocketAddress2, HttpParams paramHttpParams)
    throws IOException, UnknownHostException, ConnectTimeoutException;

  public abstract boolean isSecure(Socket paramSocket)
    throws IllegalArgumentException;
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.conn.scheme.SchemeSocketFactory
 * JD-Core Version:    0.6.2
 */