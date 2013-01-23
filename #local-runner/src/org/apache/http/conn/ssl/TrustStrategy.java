package org.apache.http.conn.ssl;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public abstract interface TrustStrategy
{
  public abstract boolean isTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
    throws CertificateException;
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.conn.ssl.TrustStrategy
 * JD-Core Version:    0.6.2
 */