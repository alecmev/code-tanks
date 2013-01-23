package org.apache.http.conn.ssl;

import javax.net.ssl.SSLException;

public class BrowserCompatHostnameVerifier extends AbstractVerifier
{
  public final void verify(String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2)
    throws SSLException
  {
    verify(paramString, paramArrayOfString1, paramArrayOfString2, false);
  }

  public final String toString()
  {
    return "BROWSER_COMPATIBLE";
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.conn.ssl.BrowserCompatHostnameVerifier
 * JD-Core Version:    0.6.2
 */