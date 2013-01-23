package org.apache.http;

import java.net.InetAddress;

public abstract interface HttpInetConnection extends HttpConnection
{
  public abstract InetAddress getRemoteAddress();

  public abstract int getRemotePort();
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.HttpInetConnection
 * JD-Core Version:    0.6.2
 */