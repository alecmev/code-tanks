package org.apache.http.cookie;

import org.apache.http.params.HttpParams;

public abstract interface CookieSpecFactory
{
  public abstract CookieSpec newInstance(HttpParams paramHttpParams);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.cookie.CookieSpecFactory
 * JD-Core Version:    0.6.2
 */