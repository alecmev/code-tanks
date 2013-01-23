package org.apache.http.client.utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.List;
import org.apache.http.Consts;
import org.apache.http.conn.util.InetAddressUtils;

public class URIBuilder
{
  private String scheme;
  private String encodedSchemeSpecificPart;
  private String encodedAuthority;
  private String userInfo;
  private String encodedUserInfo;
  private String host;
  private int port;
  private String path;
  private String encodedPath;
  private String encodedQuery;
  private List queryParams;
  private String fragment;
  private String encodedFragment;

  public URIBuilder()
  {
    this.port = -1;
  }

  public URIBuilder(URI paramURI)
  {
    digestURI(paramURI);
  }

  private List parseQuery(String paramString, Charset paramCharset)
  {
    if ((paramString != null) && (paramString.length() > 0))
      return URLEncodedUtils.parse(paramString, paramCharset);
    return null;
  }

  public URI build()
    throws URISyntaxException
  {
    return new URI(buildString());
  }

  private String buildString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (this.scheme != null)
      localStringBuilder.append(this.scheme).append(':');
    if (this.encodedSchemeSpecificPart != null)
    {
      localStringBuilder.append(this.encodedSchemeSpecificPart);
    }
    else
    {
      if (this.encodedAuthority != null)
      {
        localStringBuilder.append("//").append(this.encodedAuthority);
      }
      else if (this.host != null)
      {
        localStringBuilder.append("//");
        if (this.encodedUserInfo != null)
          localStringBuilder.append(this.encodedUserInfo).append("@");
        else if (this.userInfo != null)
          localStringBuilder.append(encodeUserInfo(this.userInfo)).append("@");
        if (InetAddressUtils.isIPv6Address(this.host))
          localStringBuilder.append("[").append(this.host).append("]");
        else
          localStringBuilder.append(this.host);
        if (this.port >= 0)
          localStringBuilder.append(":").append(this.port);
      }
      if (this.encodedPath != null)
        localStringBuilder.append(normalizePath(this.encodedPath));
      else if (this.path != null)
        localStringBuilder.append(encodePath(normalizePath(this.path)));
      if (this.encodedQuery != null)
        localStringBuilder.append("?").append(this.encodedQuery);
      else if (this.queryParams != null)
        localStringBuilder.append("?").append(encodeQuery(this.queryParams));
    }
    if (this.encodedFragment != null)
      localStringBuilder.append("#").append(this.encodedFragment);
    else if (this.fragment != null)
      localStringBuilder.append("#").append(encodeFragment(this.fragment));
    return localStringBuilder.toString();
  }

  private void digestURI(URI paramURI)
  {
    this.scheme = paramURI.getScheme();
    this.encodedSchemeSpecificPart = paramURI.getRawSchemeSpecificPart();
    this.encodedAuthority = paramURI.getRawAuthority();
    this.host = paramURI.getHost();
    this.port = paramURI.getPort();
    this.encodedUserInfo = paramURI.getRawUserInfo();
    this.userInfo = paramURI.getUserInfo();
    this.encodedPath = paramURI.getRawPath();
    this.path = paramURI.getPath();
    this.encodedQuery = paramURI.getRawQuery();
    this.queryParams = parseQuery(paramURI.getRawQuery(), Consts.UTF_8);
    this.encodedFragment = paramURI.getRawFragment();
    this.fragment = paramURI.getFragment();
  }

  private String encodeUserInfo(String paramString)
  {
    return URLEncodedUtils.encUserInfo(paramString, Consts.UTF_8);
  }

  private String encodePath(String paramString)
  {
    return URLEncodedUtils.encPath(paramString, Consts.UTF_8);
  }

  private String encodeQuery(List paramList)
  {
    return URLEncodedUtils.format(paramList, Consts.UTF_8);
  }

  private String encodeFragment(String paramString)
  {
    return URLEncodedUtils.encFragment(paramString, Consts.UTF_8);
  }

  public URIBuilder setScheme(String paramString)
  {
    this.scheme = paramString;
    return this;
  }

  public URIBuilder setUserInfo(String paramString)
  {
    this.userInfo = paramString;
    this.encodedSchemeSpecificPart = null;
    this.encodedAuthority = null;
    this.encodedUserInfo = null;
    return this;
  }

  public URIBuilder setHost(String paramString)
  {
    this.host = paramString;
    this.encodedSchemeSpecificPart = null;
    this.encodedAuthority = null;
    return this;
  }

  public URIBuilder setPort(int paramInt)
  {
    this.port = (paramInt < 0 ? -1 : paramInt);
    this.encodedSchemeSpecificPart = null;
    this.encodedAuthority = null;
    return this;
  }

  public URIBuilder setFragment(String paramString)
  {
    this.fragment = paramString;
    this.encodedFragment = null;
    return this;
  }

  public String toString()
  {
    return buildString();
  }

  private static String normalizePath(String paramString)
  {
    if (paramString == null)
      return null;
    for (int i = 0; (i < paramString.length()) && (paramString.charAt(i) == '/'); i++);
    if (i > 1)
      paramString = paramString.substring(i - 1);
    return paramString;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.client.utils.URIBuilder
 * JD-Core Version:    0.6.2
 */