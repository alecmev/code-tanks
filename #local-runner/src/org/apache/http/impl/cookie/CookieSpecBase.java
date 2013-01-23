package org.apache.http.impl.cookie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.apache.http.HeaderElement;
import org.apache.http.NameValuePair;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieAttributeHandler;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.MalformedCookieException;

public abstract class CookieSpecBase extends AbstractCookieSpec
{
  protected static String getDefaultPath(CookieOrigin paramCookieOrigin)
  {
    String str = paramCookieOrigin.getPath();
    int i = str.lastIndexOf('/');
    if (i >= 0)
    {
      if (i == 0)
        i = 1;
      str = str.substring(0, i);
    }
    return str;
  }

  protected static String getDefaultDomain(CookieOrigin paramCookieOrigin)
  {
    return paramCookieOrigin.getHost();
  }

  protected List parse(HeaderElement[] paramArrayOfHeaderElement, CookieOrigin paramCookieOrigin)
    throws MalformedCookieException
  {
    ArrayList localArrayList = new ArrayList(paramArrayOfHeaderElement.length);
    for (HeaderElement localHeaderElement : paramArrayOfHeaderElement)
    {
      String str1 = localHeaderElement.getName();
      String str2 = localHeaderElement.getValue();
      if ((str1 == null) || (str1.length() == 0))
        throw new MalformedCookieException("Cookie name may not be empty");
      BasicClientCookie localBasicClientCookie = new BasicClientCookie(str1, str2);
      localBasicClientCookie.setPath(getDefaultPath(paramCookieOrigin));
      localBasicClientCookie.setDomain(getDefaultDomain(paramCookieOrigin));
      NameValuePair[] arrayOfNameValuePair = localHeaderElement.getParameters();
      for (int k = arrayOfNameValuePair.length - 1; k >= 0; k--)
      {
        NameValuePair localNameValuePair = arrayOfNameValuePair[k];
        String str3 = localNameValuePair.getName().toLowerCase(Locale.ENGLISH);
        localBasicClientCookie.setAttribute(str3, localNameValuePair.getValue());
        CookieAttributeHandler localCookieAttributeHandler = findAttribHandler(str3);
        if (localCookieAttributeHandler != null)
          localCookieAttributeHandler.parse(localBasicClientCookie, localNameValuePair.getValue());
      }
      localArrayList.add(localBasicClientCookie);
    }
    return localArrayList;
  }

  public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
    throws MalformedCookieException
  {
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (paramCookieOrigin == null)
      throw new IllegalArgumentException("Cookie origin may not be null");
    Iterator localIterator = getAttribHandlers().iterator();
    while (localIterator.hasNext())
    {
      CookieAttributeHandler localCookieAttributeHandler = (CookieAttributeHandler)localIterator.next();
      localCookieAttributeHandler.validate(paramCookie, paramCookieOrigin);
    }
  }

  public boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (paramCookieOrigin == null)
      throw new IllegalArgumentException("Cookie origin may not be null");
    Iterator localIterator = getAttribHandlers().iterator();
    while (localIterator.hasNext())
    {
      CookieAttributeHandler localCookieAttributeHandler = (CookieAttributeHandler)localIterator.next();
      if (!localCookieAttributeHandler.match(paramCookie, paramCookieOrigin))
        return false;
    }
    return true;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.cookie.CookieSpecBase
 * JD-Core Version:    0.6.2
 */