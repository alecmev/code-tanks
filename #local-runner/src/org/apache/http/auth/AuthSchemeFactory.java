package org.apache.http.auth;

import org.apache.http.params.HttpParams;

public abstract interface AuthSchemeFactory
{
  public abstract AuthScheme newInstance(HttpParams paramHttpParams);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.auth.AuthSchemeFactory
 * JD-Core Version:    0.6.2
 */