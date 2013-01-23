package org.apache.http.auth;

import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.params.HttpParams;

public final class AuthSchemeRegistry
{
  private final ConcurrentHashMap registeredSchemes = new ConcurrentHashMap();

  public void register(String paramString, AuthSchemeFactory paramAuthSchemeFactory)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Name may not be null");
    if (paramAuthSchemeFactory == null)
      throw new IllegalArgumentException("Authentication scheme factory may not be null");
    this.registeredSchemes.put(paramString.toLowerCase(Locale.ENGLISH), paramAuthSchemeFactory);
  }

  public AuthScheme getAuthScheme(String paramString, HttpParams paramHttpParams)
    throws IllegalStateException
  {
    if (paramString == null)
      throw new IllegalArgumentException("Name may not be null");
    AuthSchemeFactory localAuthSchemeFactory = (AuthSchemeFactory)this.registeredSchemes.get(paramString.toLowerCase(Locale.ENGLISH));
    if (localAuthSchemeFactory != null)
      return localAuthSchemeFactory.newInstance(paramHttpParams);
    throw new IllegalStateException("Unsupported authentication scheme: " + paramString);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.auth.AuthSchemeRegistry
 * JD-Core Version:    0.6.2
 */