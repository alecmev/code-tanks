package org.apache.http.conn;

import java.net.InetAddress;
import java.net.UnknownHostException;

public abstract interface DnsResolver
{
  public abstract InetAddress[] resolve(String paramString)
    throws UnknownHostException;
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.conn.DnsResolver
 * JD-Core Version:    0.6.2
 */