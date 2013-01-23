package org.apache.http.impl.cookie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.NameValuePair;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieAttributeHandler;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.message.BufferedHeader;
import org.apache.http.util.CharArrayBuffer;

public class RFC2965Spec extends RFC2109Spec
{
  public RFC2965Spec()
  {
    this(null, false);
  }

  public RFC2965Spec(String[] paramArrayOfString, boolean paramBoolean)
  {
    super(paramArrayOfString, paramBoolean);
    registerAttribHandler("domain", new RFC2965DomainAttributeHandler());
    registerAttribHandler("port", new RFC2965PortAttributeHandler());
    registerAttribHandler("commenturl", new RFC2965CommentUrlAttributeHandler());
    registerAttribHandler("discard", new RFC2965DiscardAttributeHandler());
    registerAttribHandler("version", new RFC2965VersionAttributeHandler());
  }

  public List parse(Header paramHeader, CookieOrigin paramCookieOrigin)
    throws MalformedCookieException
  {
    if (paramHeader == null)
      throw new IllegalArgumentException("Header may not be null");
    if (paramCookieOrigin == null)
      throw new IllegalArgumentException("Cookie origin may not be null");
    if (!paramHeader.getName().equalsIgnoreCase("Set-Cookie2"))
      throw new MalformedCookieException("Unrecognized cookie header '" + paramHeader.toString() + "'");
    paramCookieOrigin = adjustEffectiveHost(paramCookieOrigin);
    HeaderElement[] arrayOfHeaderElement = paramHeader.getElements();
    return createCookies(arrayOfHeaderElement, paramCookieOrigin);
  }

  protected List parse(HeaderElement[] paramArrayOfHeaderElement, CookieOrigin paramCookieOrigin)
    throws MalformedCookieException
  {
    paramCookieOrigin = adjustEffectiveHost(paramCookieOrigin);
    return createCookies(paramArrayOfHeaderElement, paramCookieOrigin);
  }

  private List createCookies(HeaderElement[] paramArrayOfHeaderElement, CookieOrigin paramCookieOrigin)
    throws MalformedCookieException
  {
    ArrayList localArrayList = new ArrayList(paramArrayOfHeaderElement.length);
    for (HeaderElement localHeaderElement : paramArrayOfHeaderElement)
    {
      String str1 = localHeaderElement.getName();
      String str2 = localHeaderElement.getValue();
      if ((str1 == null) || (str1.length() == 0))
        throw new MalformedCookieException("Cookie name may not be empty");
      BasicClientCookie2 localBasicClientCookie2 = new BasicClientCookie2(str1, str2);
      localBasicClientCookie2.setPath(getDefaultPath(paramCookieOrigin));
      localBasicClientCookie2.setDomain(getDefaultDomain(paramCookieOrigin));
      localBasicClientCookie2.setPorts(new int[] { paramCookieOrigin.getPort() });
      NameValuePair[] arrayOfNameValuePair = localHeaderElement.getParameters();
      HashMap localHashMap = new HashMap(arrayOfNameValuePair.length);
      Object localObject;
      for (int k = arrayOfNameValuePair.length - 1; k >= 0; k--)
      {
        localObject = arrayOfNameValuePair[k];
        localHashMap.put(((NameValuePair)localObject).getName().toLowerCase(Locale.ENGLISH), localObject);
      }
      Iterator localIterator = localHashMap.entrySet().iterator();
      while (localIterator.hasNext())
      {
        localObject = (Map.Entry)localIterator.next();
        NameValuePair localNameValuePair = (NameValuePair)((Map.Entry)localObject).getValue();
        String str3 = localNameValuePair.getName().toLowerCase(Locale.ENGLISH);
        localBasicClientCookie2.setAttribute(str3, localNameValuePair.getValue());
        CookieAttributeHandler localCookieAttributeHandler = findAttribHandler(str3);
        if (localCookieAttributeHandler != null)
          localCookieAttributeHandler.parse(localBasicClientCookie2, localNameValuePair.getValue());
      }
      localArrayList.add(localBasicClientCookie2);
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
    paramCookieOrigin = adjustEffectiveHost(paramCookieOrigin);
    super.validate(paramCookie, paramCookieOrigin);
  }

  public boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (paramCookieOrigin == null)
      throw new IllegalArgumentException("Cookie origin may not be null");
    paramCookieOrigin = adjustEffectiveHost(paramCookieOrigin);
    return super.match(paramCookie, paramCookieOrigin);
  }

  protected void formatCookieAsVer(CharArrayBuffer paramCharArrayBuffer, Cookie paramCookie, int paramInt)
  {
    super.formatCookieAsVer(paramCharArrayBuffer, paramCookie, paramInt);
    if ((paramCookie instanceof ClientCookie))
    {
      String str = ((ClientCookie)paramCookie).getAttribute("port");
      if (str != null)
      {
        paramCharArrayBuffer.append("; $Port");
        paramCharArrayBuffer.append("=\"");
        if (str.trim().length() > 0)
        {
          int[] arrayOfInt = paramCookie.getPorts();
          if (arrayOfInt != null)
          {
            int i = 0;
            int j = arrayOfInt.length;
            while (i < j)
            {
              if (i > 0)
                paramCharArrayBuffer.append(",");
              paramCharArrayBuffer.append(Integer.toString(arrayOfInt[i]));
              i++;
            }
          }
        }
        paramCharArrayBuffer.append("\"");
      }
    }
  }

  private static CookieOrigin adjustEffectiveHost(CookieOrigin paramCookieOrigin)
  {
    String str = paramCookieOrigin.getHost();
    int i = 1;
    for (int j = 0; j < str.length(); j++)
    {
      int k = str.charAt(j);
      if ((k == 46) || (k == 58))
      {
        i = 0;
        break;
      }
    }
    if (i != 0)
    {
      str = str + ".local";
      return new CookieOrigin(str, paramCookieOrigin.getPort(), paramCookieOrigin.getPath(), paramCookieOrigin.isSecure());
    }
    return paramCookieOrigin;
  }

  public int getVersion()
  {
    return 1;
  }

  public Header getVersionHeader()
  {
    CharArrayBuffer localCharArrayBuffer = new CharArrayBuffer(40);
    localCharArrayBuffer.append("Cookie2");
    localCharArrayBuffer.append(": ");
    localCharArrayBuffer.append("$Version=");
    localCharArrayBuffer.append(Integer.toString(getVersion()));
    return new BufferedHeader(localCharArrayBuffer);
  }

  public String toString()
  {
    return "rfc2965";
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.cookie.RFC2965Spec
 * JD-Core Version:    0.6.2
 */