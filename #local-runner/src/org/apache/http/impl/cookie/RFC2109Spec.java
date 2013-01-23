package org.apache.http.impl.cookie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookiePathComparator;
import org.apache.http.cookie.CookieRestrictionViolationException;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.message.BufferedHeader;
import org.apache.http.util.CharArrayBuffer;

public class RFC2109Spec extends CookieSpecBase
{
  private static final CookiePathComparator PATH_COMPARATOR = new CookiePathComparator();
  private static final String[] DATE_PATTERNS = { "EEE, dd MMM yyyy HH:mm:ss zzz", "EEEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy" };
  private final String[] datepatterns;
  private final boolean oneHeader;

  public RFC2109Spec(String[] paramArrayOfString, boolean paramBoolean)
  {
    if (paramArrayOfString != null)
      this.datepatterns = ((String[])paramArrayOfString.clone());
    else
      this.datepatterns = DATE_PATTERNS;
    this.oneHeader = paramBoolean;
    registerAttribHandler("version", new RFC2109VersionHandler());
    registerAttribHandler("path", new BasicPathHandler());
    registerAttribHandler("domain", new RFC2109DomainHandler());
    registerAttribHandler("max-age", new BasicMaxAgeHandler());
    registerAttribHandler("secure", new BasicSecureHandler());
    registerAttribHandler("comment", new BasicCommentHandler());
    registerAttribHandler("expires", new BasicExpiresHandler(this.datepatterns));
  }

  public RFC2109Spec()
  {
    this(null, false);
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
    HeaderElement[] arrayOfHeaderElement = paramHeader.getElements();
    return parse(arrayOfHeaderElement, paramCookieOrigin);
  }

  public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
    throws MalformedCookieException
  {
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    String str = paramCookie.getName();
    if (str.indexOf(' ') != -1)
      throw new CookieRestrictionViolationException("Cookie name may not contain blanks");
    if (str.startsWith("$"))
      throw new CookieRestrictionViolationException("Cookie name may not start with $");
    super.validate(paramCookie, paramCookieOrigin);
  }

  public List formatCookies(List paramList)
  {
    if (paramList == null)
      throw new IllegalArgumentException("List of cookies may not be null");
    if (paramList.isEmpty())
      throw new IllegalArgumentException("List of cookies may not be empty");
    if (paramList.size() > 1)
    {
      paramList = new ArrayList(paramList);
      Collections.sort(paramList, PATH_COMPARATOR);
    }
    if (this.oneHeader)
      return doFormatOneHeader(paramList);
    return doFormatManyHeaders(paramList);
  }

  private List doFormatOneHeader(List paramList)
  {
    int i = 2147483647;
    Object localObject1 = paramList.iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (Cookie)((Iterator)localObject1).next();
      if (((Cookie)localObject2).getVersion() < i)
        i = ((Cookie)localObject2).getVersion();
    }
    localObject1 = new CharArrayBuffer(40 * paramList.size());
    ((CharArrayBuffer)localObject1).append("Cookie");
    ((CharArrayBuffer)localObject1).append(": ");
    ((CharArrayBuffer)localObject1).append("$Version=");
    ((CharArrayBuffer)localObject1).append(Integer.toString(i));
    Object localObject2 = paramList.iterator();
    while (((Iterator)localObject2).hasNext())
    {
      Cookie localCookie1 = (Cookie)((Iterator)localObject2).next();
      ((CharArrayBuffer)localObject1).append("; ");
      Cookie localCookie2 = localCookie1;
      formatCookieAsVer((CharArrayBuffer)localObject1, localCookie2, i);
    }
    localObject2 = new ArrayList(1);
    ((List)localObject2).add(new BufferedHeader((CharArrayBuffer)localObject1));
    return localObject2;
  }

  private List doFormatManyHeaders(List paramList)
  {
    ArrayList localArrayList = new ArrayList(paramList.size());
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      Cookie localCookie = (Cookie)localIterator.next();
      int i = localCookie.getVersion();
      CharArrayBuffer localCharArrayBuffer = new CharArrayBuffer(40);
      localCharArrayBuffer.append("Cookie: ");
      localCharArrayBuffer.append("$Version=");
      localCharArrayBuffer.append(Integer.toString(i));
      localCharArrayBuffer.append("; ");
      formatCookieAsVer(localCharArrayBuffer, localCookie, i);
      localArrayList.add(new BufferedHeader(localCharArrayBuffer));
    }
    return localArrayList;
  }

  protected void formatParamAsVer(CharArrayBuffer paramCharArrayBuffer, String paramString1, String paramString2, int paramInt)
  {
    paramCharArrayBuffer.append(paramString1);
    paramCharArrayBuffer.append("=");
    if (paramString2 != null)
      if (paramInt > 0)
      {
        paramCharArrayBuffer.append('"');
        paramCharArrayBuffer.append(paramString2);
        paramCharArrayBuffer.append('"');
      }
      else
      {
        paramCharArrayBuffer.append(paramString2);
      }
  }

  protected void formatCookieAsVer(CharArrayBuffer paramCharArrayBuffer, Cookie paramCookie, int paramInt)
  {
    formatParamAsVer(paramCharArrayBuffer, paramCookie.getName(), paramCookie.getValue(), paramInt);
    if ((paramCookie.getPath() != null) && ((paramCookie instanceof ClientCookie)) && (((ClientCookie)paramCookie).containsAttribute("path")))
    {
      paramCharArrayBuffer.append("; ");
      formatParamAsVer(paramCharArrayBuffer, "$Path", paramCookie.getPath(), paramInt);
    }
    if ((paramCookie.getDomain() != null) && ((paramCookie instanceof ClientCookie)) && (((ClientCookie)paramCookie).containsAttribute("domain")))
    {
      paramCharArrayBuffer.append("; ");
      formatParamAsVer(paramCharArrayBuffer, "$Domain", paramCookie.getDomain(), paramInt);
    }
  }

  public int getVersion()
  {
    return 1;
  }

  public Header getVersionHeader()
  {
    return null;
  }

  public String toString()
  {
    return "rfc2109";
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.cookie.RFC2109Spec
 * JD-Core Version:    0.6.2
 */