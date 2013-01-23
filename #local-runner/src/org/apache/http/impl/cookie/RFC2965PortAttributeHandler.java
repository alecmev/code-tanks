package org.apache.http.impl.cookie;

import java.util.StringTokenizer;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieAttributeHandler;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieRestrictionViolationException;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.cookie.SetCookie;
import org.apache.http.cookie.SetCookie2;

public class RFC2965PortAttributeHandler
  implements CookieAttributeHandler
{
  private static int[] parsePortAttribute(String paramString)
    throws MalformedCookieException
  {
    StringTokenizer localStringTokenizer = new StringTokenizer(paramString, ",");
    int[] arrayOfInt = new int[localStringTokenizer.countTokens()];
    try
    {
      for (int i = 0; localStringTokenizer.hasMoreTokens(); i++)
      {
        arrayOfInt[i] = Integer.parseInt(localStringTokenizer.nextToken().trim());
        if (arrayOfInt[i] < 0)
          throw new MalformedCookieException("Invalid Port attribute.");
      }
    }
    catch (NumberFormatException localNumberFormatException)
    {
      throw new MalformedCookieException("Invalid Port attribute: " + localNumberFormatException.getMessage());
    }
    return arrayOfInt;
  }

  private static boolean portMatch(int paramInt, int[] paramArrayOfInt)
  {
    boolean bool = false;
    int i = 0;
    int j = paramArrayOfInt.length;
    while (i < j)
    {
      if (paramInt == paramArrayOfInt[i])
      {
        bool = true;
        break;
      }
      i++;
    }
    return bool;
  }

  public void parse(SetCookie paramSetCookie, String paramString)
    throws MalformedCookieException
  {
    if (paramSetCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if ((paramSetCookie instanceof SetCookie2))
    {
      SetCookie2 localSetCookie2 = (SetCookie2)paramSetCookie;
      if ((paramString != null) && (paramString.trim().length() > 0))
      {
        int[] arrayOfInt = parsePortAttribute(paramString);
        localSetCookie2.setPorts(arrayOfInt);
      }
    }
  }

  public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
    throws MalformedCookieException
  {
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (paramCookieOrigin == null)
      throw new IllegalArgumentException("Cookie origin may not be null");
    int i = paramCookieOrigin.getPort();
    if (((paramCookie instanceof ClientCookie)) && (((ClientCookie)paramCookie).containsAttribute("port")) && (!portMatch(i, paramCookie.getPorts())))
      throw new CookieRestrictionViolationException("Port attribute violates RFC 2965: Request port not found in cookie's port list.");
  }

  public boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (paramCookieOrigin == null)
      throw new IllegalArgumentException("Cookie origin may not be null");
    int i = paramCookieOrigin.getPort();
    if (((paramCookie instanceof ClientCookie)) && (((ClientCookie)paramCookie).containsAttribute("port")))
    {
      if (paramCookie.getPorts() == null)
        return false;
      if (!portMatch(i, paramCookie.getPorts()))
        return false;
    }
    return true;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.cookie.RFC2965PortAttributeHandler
 * JD-Core Version:    0.6.2
 */