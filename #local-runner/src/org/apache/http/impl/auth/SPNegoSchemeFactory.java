package org.apache.http.impl.auth;

import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthSchemeFactory;
import org.apache.http.params.HttpParams;

public class SPNegoSchemeFactory
  implements AuthSchemeFactory
{
  private final boolean stripPort;

  public SPNegoSchemeFactory(boolean paramBoolean)
  {
    this.stripPort = paramBoolean;
  }

  public SPNegoSchemeFactory()
  {
    this(false);
  }

  public AuthScheme newInstance(HttpParams paramHttpParams)
  {
    return new SPNegoScheme(this.stripPort);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.auth.SPNegoSchemeFactory
 * JD-Core Version:    0.6.2
 */