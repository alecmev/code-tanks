package org.apache.http.impl.cookie;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.cookie.SetCookie;

public class BasicClientCookie
  implements Serializable, Cloneable, ClientCookie, SetCookie
{
  private final String name;
  private Map attribs;
  private String value;
  private String cookieComment;
  private String cookieDomain;
  private Date cookieExpiryDate;
  private String cookiePath;
  private boolean isSecure;
  private int cookieVersion;

  public BasicClientCookie(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      throw new IllegalArgumentException("Name may not be null");
    this.name = paramString1;
    this.attribs = new HashMap();
    this.value = paramString2;
  }

  public String getName()
  {
    return this.name;
  }

  public String getValue()
  {
    return this.value;
  }

  public void setComment(String paramString)
  {
    this.cookieComment = paramString;
  }

  public void setExpiryDate(Date paramDate)
  {
    this.cookieExpiryDate = paramDate;
  }

  public String getDomain()
  {
    return this.cookieDomain;
  }

  public void setDomain(String paramString)
  {
    if (paramString != null)
      this.cookieDomain = paramString.toLowerCase(Locale.ENGLISH);
    else
      this.cookieDomain = null;
  }

  public String getPath()
  {
    return this.cookiePath;
  }

  public void setPath(String paramString)
  {
    this.cookiePath = paramString;
  }

  public boolean isSecure()
  {
    return this.isSecure;
  }

  public void setSecure(boolean paramBoolean)
  {
    this.isSecure = paramBoolean;
  }

  public int[] getPorts()
  {
    return null;
  }

  public int getVersion()
  {
    return this.cookieVersion;
  }

  public void setVersion(int paramInt)
  {
    this.cookieVersion = paramInt;
  }

  public boolean isExpired(Date paramDate)
  {
    if (paramDate == null)
      throw new IllegalArgumentException("Date may not be null");
    return (this.cookieExpiryDate != null) && (this.cookieExpiryDate.getTime() <= paramDate.getTime());
  }

  public void setAttribute(String paramString1, String paramString2)
  {
    this.attribs.put(paramString1, paramString2);
  }

  public String getAttribute(String paramString)
  {
    return (String)this.attribs.get(paramString);
  }

  public boolean containsAttribute(String paramString)
  {
    return this.attribs.get(paramString) != null;
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    BasicClientCookie localBasicClientCookie = (BasicClientCookie)super.clone();
    localBasicClientCookie.attribs = new HashMap(this.attribs);
    return localBasicClientCookie;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[version: ");
    localStringBuilder.append(Integer.toString(this.cookieVersion));
    localStringBuilder.append("]");
    localStringBuilder.append("[name: ");
    localStringBuilder.append(this.name);
    localStringBuilder.append("]");
    localStringBuilder.append("[value: ");
    localStringBuilder.append(this.value);
    localStringBuilder.append("]");
    localStringBuilder.append("[domain: ");
    localStringBuilder.append(this.cookieDomain);
    localStringBuilder.append("]");
    localStringBuilder.append("[path: ");
    localStringBuilder.append(this.cookiePath);
    localStringBuilder.append("]");
    localStringBuilder.append("[expiry: ");
    localStringBuilder.append(this.cookieExpiryDate);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.cookie.BasicClientCookie
 * JD-Core Version:    0.6.2
 */