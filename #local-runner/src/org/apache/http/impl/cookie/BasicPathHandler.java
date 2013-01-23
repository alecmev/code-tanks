package org.apache.http.impl.cookie;

import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieAttributeHandler;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieRestrictionViolationException;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.cookie.SetCookie;

public class BasicPathHandler
  implements CookieAttributeHandler
{
  public void parse(SetCookie paramSetCookie, String paramString)
    throws MalformedCookieException
  {
    if (paramSetCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if ((paramString == null) || (paramString.trim().length() == 0))
      paramString = "/";
    paramSetCookie.setPath(paramString);
  }

  public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
    throws MalformedCookieException
  {
    if (!match(paramCookie, paramCookieOrigin))
      throw new CookieRestrictionViolationException("Illegal path attribute \"" + paramCookie.getPath() + "\". Path of origin: \"" + paramCookieOrigin.getPath() + "\"");
  }

  public boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (paramCookieOrigin == null)
      throw new IllegalArgumentException("Cookie origin may not be null");
    String str1 = paramCookieOrigin.getPath();
    String str2 = paramCookie.getPath();
    if (str2 == null)
      str2 = "/";
    if ((str2.length() > 1) && (str2.endsWith("/")))
      str2 = str2.substring(0, str2.length() - 1);
    boolean bool = str1.startsWith(str2);
    if ((bool) && (str1.length() != str2.length()) && (!str2.endsWith("/")))
      bool = str1.charAt(str2.length()) == '/';
    return bool;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.cookie.BasicPathHandler
 * JD-Core Version:    0.6.2
 */