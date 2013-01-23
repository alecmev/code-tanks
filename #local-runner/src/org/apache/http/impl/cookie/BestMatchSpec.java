package org.apache.http.impl.cookie;

import java.util.Iterator;
import java.util.List;
import org.apache.http.FormattedHeader;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.cookie.SetCookie2;
import org.apache.http.message.ParserCursor;
import org.apache.http.util.CharArrayBuffer;

public class BestMatchSpec
  implements CookieSpec
{
  private final String[] datepatterns;
  private final boolean oneHeader;
  private RFC2965Spec strict;
  private RFC2109Spec obsoleteStrict;
  private BrowserCompatSpec compat;

  public BestMatchSpec(String[] paramArrayOfString, boolean paramBoolean)
  {
    this.datepatterns = (paramArrayOfString == null ? null : (String[])paramArrayOfString.clone());
    this.oneHeader = paramBoolean;
  }

  public BestMatchSpec()
  {
    this(null, false);
  }

  private RFC2965Spec getStrict()
  {
    if (this.strict == null)
      this.strict = new RFC2965Spec(this.datepatterns, this.oneHeader);
    return this.strict;
  }

  private RFC2109Spec getObsoleteStrict()
  {
    if (this.obsoleteStrict == null)
      this.obsoleteStrict = new RFC2109Spec(this.datepatterns, this.oneHeader);
    return this.obsoleteStrict;
  }

  private BrowserCompatSpec getCompat()
  {
    if (this.compat == null)
      this.compat = new BrowserCompatSpec(this.datepatterns);
    return this.compat;
  }

  public List parse(Header paramHeader, CookieOrigin paramCookieOrigin)
    throws MalformedCookieException
  {
    if (paramHeader == null)
      throw new IllegalArgumentException("Header may not be null");
    if (paramCookieOrigin == null)
      throw new IllegalArgumentException("Cookie origin may not be null");
    HeaderElement[] arrayOfHeaderElement = paramHeader.getElements();
    int i = 0;
    int j = 0;
    String str;
    for (str : arrayOfHeaderElement)
    {
      if (str.getParameterByName("version") != null)
        i = 1;
      if (str.getParameterByName("expires") != null)
        j = 1;
    }
    if ((j != 0) || (i == 0))
    {
      ??? = NetscapeDraftHeaderParser.DEFAULT;
      CharArrayBuffer localCharArrayBuffer;
      ParserCursor localParserCursor;
      if ((paramHeader instanceof FormattedHeader))
      {
        localCharArrayBuffer = ((FormattedHeader)paramHeader).getBuffer();
        localParserCursor = new ParserCursor(((FormattedHeader)paramHeader).getValuePos(), localCharArrayBuffer.length());
      }
      else
      {
        str = paramHeader.getValue();
        if (str == null)
          throw new MalformedCookieException("Header value is null");
        localCharArrayBuffer = new CharArrayBuffer(str.length());
        localCharArrayBuffer.append(str);
        localParserCursor = new ParserCursor(0, localCharArrayBuffer.length());
      }
      arrayOfHeaderElement = new HeaderElement[] { ((NetscapeDraftHeaderParser)???).parseHeader(localCharArrayBuffer, localParserCursor) };
      return getCompat().parse(arrayOfHeaderElement, paramCookieOrigin);
    }
    if ("Set-Cookie2".equals(paramHeader.getName()))
      return getStrict().parse(arrayOfHeaderElement, paramCookieOrigin);
    return getObsoleteStrict().parse(arrayOfHeaderElement, paramCookieOrigin);
  }

  public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
    throws MalformedCookieException
  {
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (paramCookieOrigin == null)
      throw new IllegalArgumentException("Cookie origin may not be null");
    if (paramCookie.getVersion() > 0)
    {
      if ((paramCookie instanceof SetCookie2))
        getStrict().validate(paramCookie, paramCookieOrigin);
      else
        getObsoleteStrict().validate(paramCookie, paramCookieOrigin);
    }
    else
      getCompat().validate(paramCookie, paramCookieOrigin);
  }

  public boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (paramCookieOrigin == null)
      throw new IllegalArgumentException("Cookie origin may not be null");
    if (paramCookie.getVersion() > 0)
    {
      if ((paramCookie instanceof SetCookie2))
        return getStrict().match(paramCookie, paramCookieOrigin);
      return getObsoleteStrict().match(paramCookie, paramCookieOrigin);
    }
    return getCompat().match(paramCookie, paramCookieOrigin);
  }

  public List formatCookies(List paramList)
  {
    if (paramList == null)
      throw new IllegalArgumentException("List of cookies may not be null");
    int i = 2147483647;
    int j = 1;
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      Cookie localCookie = (Cookie)localIterator.next();
      if (!(localCookie instanceof SetCookie2))
        j = 0;
      if (localCookie.getVersion() < i)
        i = localCookie.getVersion();
    }
    if (i > 0)
    {
      if (j != 0)
        return getStrict().formatCookies(paramList);
      return getObsoleteStrict().formatCookies(paramList);
    }
    return getCompat().formatCookies(paramList);
  }

  public int getVersion()
  {
    return getStrict().getVersion();
  }

  public Header getVersionHeader()
  {
    return getStrict().getVersionHeader();
  }

  public String toString()
  {
    return "best-match";
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.cookie.BestMatchSpec
 * JD-Core Version:    0.6.2
 */