package org.apache.http.impl.cookie;

import org.apache.http.cookie.ClientCookie;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieAttributeHandler;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieRestrictionViolationException;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.cookie.SetCookie;
import org.apache.http.cookie.SetCookie2;

public class RFC2965VersionAttributeHandler
  implements CookieAttributeHandler
{
  public void parse(SetCookie paramSetCookie, String paramString)
    throws MalformedCookieException
  {
    if (paramSetCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (paramString == null)
      throw new MalformedCookieException("Missing value for version attribute");
    int i = -1;
    try
    {
      i = Integer.parseInt(paramString);
    }
    catch (NumberFormatException localNumberFormatException)
    {
      i = -1;
    }
    if (i < 0)
      throw new MalformedCookieException("Invalid cookie version.");
    paramSetCookie.setVersion(i);
  }

  public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
    throws MalformedCookieException
  {
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (((paramCookie instanceof SetCookie2)) && ((paramCookie instanceof ClientCookie)) && (!((ClientCookie)paramCookie).containsAttribute("version")))
      throw new CookieRestrictionViolationException("Violates RFC 2965. Version attribute is required.");
  }

  public boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    return true;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.cookie.RFC2965VersionAttributeHandler
 * JD-Core Version:    0.6.2
 */