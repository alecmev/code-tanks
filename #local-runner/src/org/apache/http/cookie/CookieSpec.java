package org.apache.http.cookie;

import java.util.List;
import org.apache.http.Header;

public abstract interface CookieSpec
{
  public abstract int getVersion();

  public abstract List parse(Header paramHeader, CookieOrigin paramCookieOrigin)
    throws MalformedCookieException;

  public abstract void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
    throws MalformedCookieException;

  public abstract boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin);

  public abstract List formatCookies(List paramList);

  public abstract Header getVersionHeader();
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.cookie.CookieSpec
 * JD-Core Version:    0.6.2
 */