package org.apache.http.conn.ssl;

import java.io.IOException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSocket;

public abstract interface X509HostnameVerifier extends HostnameVerifier
{
  public abstract void verify(String paramString, SSLSocket paramSSLSocket)
    throws IOException;

  public abstract void verify(String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2)
    throws SSLException;
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.conn.ssl.X509HostnameVerifier
 * JD-Core Version:    0.6.2
 */