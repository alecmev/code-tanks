package org.apache.http.client;

import java.util.List;
import org.apache.http.cookie.Cookie;

public abstract interface CookieStore
{
  public abstract void addCookie(Cookie paramCookie);

  public abstract List getCookies();
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.client.CookieStore
 * JD-Core Version:    0.6.2
 */