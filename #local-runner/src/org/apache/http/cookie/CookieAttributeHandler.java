package org.apache.http.cookie;

public abstract interface CookieAttributeHandler
{
  public abstract void parse(SetCookie paramSetCookie, String paramString)
    throws MalformedCookieException;

  public abstract void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
    throws MalformedCookieException;

  public abstract boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.cookie.CookieAttributeHandler
 * JD-Core Version:    0.6.2
 */