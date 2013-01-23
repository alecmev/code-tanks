package org.apache.http.conn.ssl;

import javax.net.ssl.SSLException;

public class StrictHostnameVerifier extends AbstractVerifier
{
  public final void verify(String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2)
    throws SSLException
  {
    verify(paramString, paramArrayOfString1, paramArrayOfString2, true);
  }

  public final String toString()
  {
    return "STRICT";
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.conn.ssl.StrictHostnameVerifier
 * JD-Core Version:    0.6.2
 */