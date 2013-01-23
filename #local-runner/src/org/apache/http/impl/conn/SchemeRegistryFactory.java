package org.apache.http.impl.conn;

import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;

public final class SchemeRegistryFactory
{
  public static SchemeRegistry createDefault()
  {
    SchemeRegistry localSchemeRegistry = new SchemeRegistry();
    localSchemeRegistry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
    localSchemeRegistry.register(new Scheme("https", 443, SSLSocketFactory.getSocketFactory()));
    return localSchemeRegistry;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.conn.SchemeRegistryFactory
 * JD-Core Version:    0.6.2
 */