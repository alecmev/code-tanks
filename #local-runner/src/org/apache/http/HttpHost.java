package org.apache.http;

import java.io.Serializable;
import java.util.Locale;
import org.apache.http.util.LangUtils;

public final class HttpHost
  implements Serializable, Cloneable
{
  protected final String hostname;
  protected final String lcHostname;
  protected final int port;
  protected final String schemeName;

  public HttpHost(String paramString1, int paramInt, String paramString2)
  {
    if (paramString1 == null)
      throw new IllegalArgumentException("Host name may not be null");
    this.hostname = paramString1;
    this.lcHostname = paramString1.toLowerCase(Locale.ENGLISH);
    if (paramString2 != null)
      this.schemeName = paramString2.toLowerCase(Locale.ENGLISH);
    else
      this.schemeName = "http";
    this.port = paramInt;
  }

  public HttpHost(String paramString, int paramInt)
  {
    this(paramString, paramInt, null);
  }

  public String getHostName()
  {
    return this.hostname;
  }

  public int getPort()
  {
    return this.port;
  }

  public String getSchemeName()
  {
    return this.schemeName;
  }

  public String toURI()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.schemeName);
    localStringBuilder.append("://");
    localStringBuilder.append(this.hostname);
    if (this.port != -1)
    {
      localStringBuilder.append(':');
      localStringBuilder.append(Integer.toString(this.port));
    }
    return localStringBuilder.toString();
  }

  public String toHostString()
  {
    if (this.port != -1)
    {
      StringBuilder localStringBuilder = new StringBuilder(this.hostname.length() + 6);
      localStringBuilder.append(this.hostname);
      localStringBuilder.append(":");
      localStringBuilder.append(Integer.toString(this.port));
      return localStringBuilder.toString();
    }
    return this.hostname;
  }

  public String toString()
  {
    return toURI();
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject)
      return true;
    if ((paramObject instanceof HttpHost))
    {
      HttpHost localHttpHost = (HttpHost)paramObject;
      return (this.lcHostname.equals(localHttpHost.lcHostname)) && (this.port == localHttpHost.port) && (this.schemeName.equals(localHttpHost.schemeName));
    }
    return false;
  }

  public int hashCode()
  {
    int i = 17;
    i = LangUtils.hashCode(i, this.lcHostname);
    i = LangUtils.hashCode(i, this.port);
    i = LangUtils.hashCode(i, this.schemeName);
    return i;
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    return super.clone();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.HttpHost
 * JD-Core Version:    0.6.2
 */