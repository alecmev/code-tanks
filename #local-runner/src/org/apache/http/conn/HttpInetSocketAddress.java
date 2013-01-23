package org.apache.http.conn;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import org.apache.http.HttpHost;

public class HttpInetSocketAddress extends InetSocketAddress
{
  private final HttpHost httphost;

  public HttpInetSocketAddress(HttpHost paramHttpHost, InetAddress paramInetAddress, int paramInt)
  {
    super(paramInetAddress, paramInt);
    if (paramHttpHost == null)
      throw new IllegalArgumentException("HTTP host may not be null");
    this.httphost = paramHttpHost;
  }

  public HttpHost getHttpHost()
  {
    return this.httphost;
  }

  public String toString()
  {
    return this.httphost.getHostName() + ":" + getPort();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.conn.HttpInetSocketAddress
 * JD-Core Version:    0.6.2
 */