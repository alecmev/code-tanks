package org.apache.http.impl.cookie;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.FormattedHeader;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.message.BufferedHeader;
import org.apache.http.message.ParserCursor;
import org.apache.http.util.CharArrayBuffer;

public class BrowserCompatSpec extends CookieSpecBase
{
  private static final String[] DEFAULT_DATE_PATTERNS = { "EEE, dd MMM yyyy HH:mm:ss zzz", "EEEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy", "EEE, dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MMM-yyyy HH-mm-ss z", "EEE, dd MMM yy HH:mm:ss z", "EEE dd-MMM-yyyy HH:mm:ss z", "EEE dd MMM yyyy HH:mm:ss z", "EEE dd-MMM-yyyy HH-mm-ss z", "EEE dd-MMM-yy HH:mm:ss z", "EEE dd MMM yy HH:mm:ss z", "EEE,dd-MMM-yy HH:mm:ss z", "EEE,dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MM-yyyy HH:mm:ss z" };
  private final String[] datepatterns;

  public BrowserCompatSpec(String[] paramArrayOfString)
  {
    if (paramArrayOfString != null)
      this.datepatterns = ((String[])paramArrayOfString.clone());
    else
      this.datepatterns = DEFAULT_DATE_PATTERNS;
    registerAttribHandler("path", new BasicPathHandler());
    registerAttribHandler("domain", new BasicDomainHandler());
    registerAttribHandler("max-age", new BasicMaxAgeHandler());
    registerAttribHandler("secure", new BasicSecureHandler());
    registerAttribHandler("comment", new BasicCommentHandler());
    registerAttribHandler("expires", new BasicExpiresHandler(this.datepatterns));
  }

  public BrowserCompatSpec()
  {
    this(null);
  }

  public List parse(Header paramHeader, CookieOrigin paramCookieOrigin)
    throws MalformedCookieException
  {
    if (paramHeader == null)
      throw new IllegalArgumentException("Header may not be null");
    if (paramCookieOrigin == null)
      throw new IllegalArgumentException("Cookie origin may not be null");
    String str1 = paramHeader.getName();
    if (!str1.equalsIgnoreCase("Set-Cookie"))
      throw new MalformedCookieException("Unrecognized cookie header '" + paramHeader.toString() + "'");
    HeaderElement[] arrayOfHeaderElement = paramHeader.getElements();
    int i = 0;
    int j = 0;
    String str2;
    for (str2 : arrayOfHeaderElement)
    {
      if (str2.getParameterByName("version") != null)
        i = 1;
      if (str2.getParameterByName("expires") != null)
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
        str2 = paramHeader.getValue();
        if (str2 == null)
          throw new MalformedCookieException("Header value is null");
        localCharArrayBuffer = new CharArrayBuffer(str2.length());
        localCharArrayBuffer.append(str2);
        localParserCursor = new ParserCursor(0, localCharArrayBuffer.length());
      }
      arrayOfHeaderElement = new HeaderElement[] { ((NetscapeDraftHeaderParser)???).parseHeader(localCharArrayBuffer, localParserCursor) };
    }
    return parse(arrayOfHeaderElement, paramCookieOrigin);
  }

  public List formatCookies(List paramList)
  {
    if (paramList == null)
      throw new IllegalArgumentException("List of cookies may not be null");
    if (paramList.isEmpty())
      throw new IllegalArgumentException("List of cookies may not be empty");
    CharArrayBuffer localCharArrayBuffer = new CharArrayBuffer(20 * paramList.size());
    localCharArrayBuffer.append("Cookie");
    localCharArrayBuffer.append(": ");
    for (int i = 0; i < paramList.size(); i++)
    {
      Cookie localCookie = (Cookie)paramList.get(i);
      if (i > 0)
        localCharArrayBuffer.append("; ");
      localCharArrayBuffer.append(localCookie.getName());
      localCharArrayBuffer.append("=");
      String str = localCookie.getValue();
      if (str != null)
        localCharArrayBuffer.append(str);
    }
    ArrayList localArrayList = new ArrayList(1);
    localArrayList.add(new BufferedHeader(localCharArrayBuffer));
    return localArrayList;
  }

  public int getVersion()
  {
    return 0;
  }

  public Header getVersionHeader()
  {
    return null;
  }

  public String toString()
  {
    return "compatibility";
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.cookie.BrowserCompatSpec
 * JD-Core Version:    0.6.2
 */