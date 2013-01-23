package org.apache.http.auth;

import java.util.Locale;
import org.apache.http.HttpHost;
import org.apache.http.util.LangUtils;

public class AuthScope
{
  public static final String ANY_HOST = null;
  public static final String ANY_REALM = null;
  public static final String ANY_SCHEME = null;
  public static final AuthScope ANY = new AuthScope(ANY_HOST, -1, ANY_REALM, ANY_SCHEME);
  private final String scheme;
  private final String realm;
  private final String host;
  private final int port;

  public AuthScope(String paramString1, int paramInt, String paramString2, String paramString3)
  {
    this.host = (paramString1 == null ? ANY_HOST : paramString1.toLowerCase(Locale.ENGLISH));
    this.port = (paramInt < 0 ? -1 : paramInt);
    this.realm = (paramString2 == null ? ANY_REALM : paramString2);
    this.scheme = (paramString3 == null ? ANY_SCHEME : paramString3.toUpperCase(Locale.ENGLISH));
  }

  public AuthScope(HttpHost paramHttpHost, String paramString1, String paramString2)
  {
    this(paramHttpHost.getHostName(), paramHttpHost.getPort(), paramString1, paramString2);
  }

  public int match(AuthScope paramAuthScope)
  {
    int i = 0;
    if (LangUtils.equals(this.scheme, paramAuthScope.scheme))
      i++;
    else if ((this.scheme != ANY_SCHEME) && (paramAuthScope.scheme != ANY_SCHEME))
      return -1;
    if (LangUtils.equals(this.realm, paramAuthScope.realm))
      i += 2;
    else if ((this.realm != ANY_REALM) && (paramAuthScope.realm != ANY_REALM))
      return -1;
    if (this.port == paramAuthScope.port)
      i += 4;
    else if ((this.port != -1) && (paramAuthScope.port != -1))
      return -1;
    if (LangUtils.equals(this.host, paramAuthScope.host))
      i += 8;
    else if ((this.host != ANY_HOST) && (paramAuthScope.host != ANY_HOST))
      return -1;
    return i;
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null)
      return false;
    if (paramObject == this)
      return true;
    if (!(paramObject instanceof AuthScope))
      return super.equals(paramObject);
    AuthScope localAuthScope = (AuthScope)paramObject;
    return (LangUtils.equals(this.host, localAuthScope.host)) && (this.port == localAuthScope.port) && (LangUtils.equals(this.realm, localAuthScope.realm)) && (LangUtils.equals(this.scheme, localAuthScope.scheme));
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (this.scheme != null)
    {
      localStringBuilder.append(this.scheme.toUpperCase(Locale.ENGLISH));
      localStringBuilder.append(' ');
    }
    if (this.realm != null)
    {
      localStringBuilder.append('\'');
      localStringBuilder.append(this.realm);
      localStringBuilder.append('\'');
    }
    else
    {
      localStringBuilder.append("<any realm>");
    }
    if (this.host != null)
    {
      localStringBuilder.append('@');
      localStringBuilder.append(this.host);
      if (this.port >= 0)
      {
        localStringBuilder.append(':');
        localStringBuilder.append(this.port);
      }
    }
    return localStringBuilder.toString();
  }

  public int hashCode()
  {
    int i = 17;
    i = LangUtils.hashCode(i, this.host);
    i = LangUtils.hashCode(i, this.port);
    i = LangUtils.hashCode(i, this.realm);
    i = LangUtils.hashCode(i, this.scheme);
    return i;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.auth.AuthScope
 * JD-Core Version:    0.6.2
 */