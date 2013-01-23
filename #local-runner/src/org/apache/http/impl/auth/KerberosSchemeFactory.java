package org.apache.http.impl.auth;

import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthSchemeFactory;
import org.apache.http.params.HttpParams;

public class KerberosSchemeFactory
  implements AuthSchemeFactory
{
  private final boolean stripPort;

  public KerberosSchemeFactory(boolean paramBoolean)
  {
    this.stripPort = paramBoolean;
  }

  public KerberosSchemeFactory()
  {
    this(false);
  }

  public AuthScheme newInstance(HttpParams paramHttpParams)
  {
    return new KerberosScheme(this.stripPort);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.auth.KerberosSchemeFactory
 * JD-Core Version:    0.6.2
 */