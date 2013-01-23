package org.apache.http.impl.cookie;

import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieRestrictionViolationException;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.cookie.SetCookie;

public class RFC2109VersionHandler extends AbstractCookieAttributeHandler
{
  public void parse(SetCookie paramSetCookie, String paramString)
    throws MalformedCookieException
  {
    if (paramSetCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (paramString == null)
      throw new MalformedCookieException("Missing value for version attribute");
    if (paramString.trim().length() == 0)
      throw new MalformedCookieException("Blank value for version attribute");
    try
    {
      paramSetCookie.setVersion(Integer.parseInt(paramString));
    }
    catch (NumberFormatException localNumberFormatException)
    {
      throw new MalformedCookieException("Invalid version: " + localNumberFormatException.getMessage());
    }
  }

  public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
    throws MalformedCookieException
  {
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (paramCookie.getVersion() < 0)
      throw new CookieRestrictionViolationException("Cookie version may not be negative");
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.cookie.RFC2109VersionHandler
 * JD-Core Version:    0.6.2
 */