package org.apache.http.impl.cookie;

import java.util.Locale;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieAttributeHandler;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieRestrictionViolationException;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.cookie.SetCookie;

public class RFC2109DomainHandler
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
    if (!str2.equals(str1))
    {
      int i = str2.indexOf('.');
      if (i == -1)
        throw new CookieRestrictionViolationException("Domain attribute \"" + str2 + "\" does not match the host \"" + str1 + "\"");
      if (!str2.startsWith("."))
        throw new CookieRestrictionViolationException("Domain attribute \"" + str2 + "\" violates RFC 2109: domain must start with a dot");
      i = str2.indexOf('.', 1);
      if ((i < 0) || (i == str2.length() - 1))
        throw new CookieRestrictionViolationException("Domain attribute \"" + str2 + "\" violates RFC 2109: domain must contain an embedded dot");
      str1 = str1.toLowerCase(Locale.ENGLISH);
      if (!str1.endsWith(str2))
        throw new CookieRestrictionViolationException("Illegal domain attribute \"" + str2 + "\". Domain of origin: \"" + str1 + "\"");
      String str3 = str1.substring(0, str1.length() - str2.length());
      if (str3.indexOf('.') != -1)
        throw new CookieRestrictionViolationException("Domain attribute \"" + str2 + "\" violates RFC 2109: host minus domain may not contain any dots");
    }
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
    return (str1.equals(str2)) || ((str2.startsWith(".")) && (str1.endsWith(str2)));
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.cookie.RFC2109DomainHandler
 * JD-Core Version:    0.6.2
 */