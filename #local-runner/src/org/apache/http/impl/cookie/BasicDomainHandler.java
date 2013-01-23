package org.apache.http.impl.cookie;

import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieAttributeHandler;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieRestrictionViolationException;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.cookie.SetCookie;

public class BasicDomainHandler
  implements CookieAttributeHandler
{
  public void parse(SetCookie paramSetCookie, String paramString)
    throws MalformedCookieException
  {
    if (paramSetCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (paramString == null)
      throw new MalformedCookieException("Missing value for domain attribute");
    if (paramString.trim().length() == 0)
      throw new MalformedCookieException("Blank value for domain attribute");
    paramSetCookie.setDomain(paramString);
  }

  public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
    throws MalformedCookieException
  {
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (paramCookieOrigin == null)
      throw new IllegalArgumentException("Cookie origin may not be null");
    String str1 = paramCookieOrigin.getHost();
    String str2 = paramCookie.getDomain();
    if (str2 == null)
      throw new CookieRestrictionViolationException("Cookie domain may not be null");
    if (str1.contains("."))
    {
      if (!str1.endsWith(str2))
      {
        if (str2.startsWith("."))
          str2 = str2.substring(1, str2.length());
        if (!str1.equals(str2))
          throw new CookieRestrictionViolationException("Illegal domain attribute \"" + str2 + "\". Domain of origin: \"" + str1 + "\"");
      }
    }
    else if (!str1.equals(str2))
      throw new CookieRestrictionViolationException("Illegal domain attribute \"" + str2 + "\". Domain of origin: \"" + str1 + "\"");
  }

  public boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (paramCookieOrigin == null)
      throw new IllegalArgumentException("Cookie origin may not be null");
    String str1 = paramCookieOrigin.getHost();
    String str2 = paramCookie.getDomain();
    if (str2 == null)
      return false;
    if (str1.equals(str2))
      return true;
    if (!str2.startsWith("."))
      str2 = '.' + str2;
    return (str1.endsWith(str2)) || (str1.equals(str2.substring(1)));
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.cookie.BasicDomainHandler
 * JD-Core Version:    0.6.2
 */