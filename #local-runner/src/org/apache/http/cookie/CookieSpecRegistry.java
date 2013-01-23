package org.apache.http.cookie;

import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.params.HttpParams;

public final class CookieSpecRegistry
{
  private final ConcurrentHashMap registeredSpecs = new ConcurrentHashMap();

  public void register(String paramString, CookieSpecFactory paramCookieSpecFactory)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Name may not be null");
    if (paramCookieSpecFactory == null)
      throw new IllegalArgumentException("Cookie spec factory may not be null");
    this.registeredSpecs.put(paramString.toLowerCase(Locale.ENGLISH), paramCookieSpecFactory);
  }

  public CookieSpec getCookieSpec(String paramString, HttpParams paramHttpParams)
    throws IllegalStateException
  {
    if (paramString == null)
      throw new IllegalArgumentException("Name may not be null");
    CookieSpecFactory localCookieSpecFactory = (CookieSpecFactory)this.registeredSpecs.get(paramString.toLowerCase(Locale.ENGLISH));
    if (localCookieSpecFactory != null)
      return localCookieSpecFactory.newInstance(paramHttpParams);
    throw new IllegalStateException("Unsupported cookie spec: " + paramString);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.cookie.CookieSpecRegistry
 * JD-Core Version:    0.6.2
 */