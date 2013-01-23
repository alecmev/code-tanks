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

public class NetscapeDraftSpec extends CookieSpecBase
{
  private final String[] datepatterns;

  public NetscapeDraftSpec(String[] paramArrayOfString)
  {
    if (paramArrayOfString != null)
      this.datepatterns = ((String[])paramArrayOfString.clone());
    else
      this.datepatterns = new String[] { "EEE, dd-MMM-yy HH:mm:ss z" };
    registerAttribHandler("path", new BasicPathHandler());
    registerAttribHandler("domain", new NetscapeDomainHandler());
    registerAttribHandler("max-age", new BasicMaxAgeHandler());
    registerAttribHandler("secure", new BasicSecureHandler());
    registerAttribHandler("comment", new BasicCommentHandler());
    registerAttribHandler("expires", new BasicExpiresHandler(this.datepatterns));
  }

  public NetscapeDraftSpec()
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
    if (!paramHeader.getName().equalsIgnoreCase("Set-Cookie"))
      throw new MalformedCookieException("Unrecognized cookie header '" + paramHeader.toString() + "'");
    NetscapeDraftHeaderParser localNetscapeDraftHeaderParser = NetscapeDraftHeaderParser.DEFAULT;
    CharArrayBuffer localCharArrayBuffer;
    ParserCursor localParserCursor;
    if ((paramHeader instanceof FormattedHeader))
    {
      localCharArrayBuffer = ((FormattedHeader)paramHeader).getBuffer();
      localParserCursor = new ParserCursor(((FormattedHeader)paramHeader).getValuePos(), localCharArrayBuffer.length());
    }
    else
    {
      String str = paramHeader.getValue();
      if (str == null)
        throw new MalformedCookieException("Header value is null");
      localCharArrayBuffer = new CharArrayBuffer(str.length());
      localCharArrayBuffer.append(str);
      localParserCursor = new ParserCursor(0, localCharArrayBuffer.length());
    }
    return parse(new HeaderElement[] { localNetscapeDraftHeaderParser.parseHeader(localCharArrayBuffer, localParserCursor) }, paramCookieOrigin);
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
      String str = localCookie.getValue();
      if (str != null)
      {
        localCharArrayBuffer.append("=");
        localCharArrayBuffer.append(str);
      }
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
    return "netscape";
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.cookie.NetscapeDraftSpec
 * JD-Core Version:    0.6.2
 */