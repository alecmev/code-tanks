package org.apache.http.impl.conn;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.apache.http.conn.DnsResolver;

public class SystemDefaultDnsResolver
  implements DnsResolver
{
  public InetAddress[] resolve(String paramString)
    throws UnknownHostException
  {
    return InetAddress.getAllByName(paramString);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.conn.SystemDefaultDnsResolver
 * JD-Core Version:    0.6.2
 */