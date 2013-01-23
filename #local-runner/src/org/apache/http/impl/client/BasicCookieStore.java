package org.apache.http.impl.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieIdentityComparator;

public class BasicCookieStore
  implements Serializable, CookieStore
{
  private final TreeSet cookies = new TreeSet(new CookieIdentityComparator());

  public synchronized void addCookie(Cookie paramCookie)
  {
    if (paramCookie != null)
    {
      this.cookies.remove(paramCookie);
      if (!paramCookie.isExpired(new Date()))
        this.cookies.add(paramCookie);
    }
  }

  public synchronized List getCookies()
  {
    return new ArrayList(this.cookies);
  }

  public synchronized String toString()
  {
    return this.cookies.toString();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.client.BasicCookieStore
 * JD-Core Version:    0.6.2
 */