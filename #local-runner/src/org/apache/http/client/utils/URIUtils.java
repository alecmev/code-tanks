package org.apache.http.client.utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Stack;
import org.apache.http.HttpHost;

public class URIUtils
{
  public static URI rewriteURI(URI paramURI, HttpHost paramHttpHost, boolean paramBoolean)
    throws URISyntaxException
  {
    if (paramURI == null)
      throw new IllegalArgumentException("URI may not be null");
    URIBuilder localURIBuilder = new URIBuilder(paramURI);
    if (paramHttpHost != null)
    {
      localURIBuilder.setScheme(paramHttpHost.getSchemeName());
      localURIBuilder.setHost(paramHttpHost.getHostName());
      localURIBuilder.setPort(paramHttpHost.getPort());
    }
    else
    {
      localURIBuilder.setScheme(null);
      localURIBuilder.setHost(null);
      localURIBuilder.setPort(-1);
    }
    if (paramBoolean)
      localURIBuilder.setFragment(null);
    return localURIBuilder.build();
  }

  public static URI rewriteURI(URI paramURI, HttpHost paramHttpHost)
    throws URISyntaxException
  {
    return rewriteURI(paramURI, paramHttpHost, false);
  }

  public static URI rewriteURI(URI paramURI)
    throws URISyntaxException
  {
    if (paramURI == null)
      throw new IllegalArgumentException("URI may not be null");
    if ((paramURI.getFragment() != null) || (paramURI.getUserInfo() != null))
      return new URIBuilder(paramURI).setFragment(null).setUserInfo(null).build();
    return paramURI;
  }

  public static URI resolve(URI paramURI1, URI paramURI2)
  {
    if (paramURI1 == null)
      throw new IllegalArgumentException("Base URI may nor be null");
    if (paramURI2 == null)
      throw new IllegalArgumentException("Reference URI may nor be null");
    String str1 = paramURI2.toString();
    if (str1.startsWith("?"))
      return resolveReferenceStartingWithQueryString(paramURI1, paramURI2);
    int i = str1.length() == 0 ? 1 : 0;
    if (i != 0)
      paramURI2 = URI.create("#");
    URI localURI = paramURI1.resolve(paramURI2);
    if (i != 0)
    {
      String str2 = localURI.toString();
      localURI = URI.create(str2.substring(0, str2.indexOf('#')));
    }
    return removeDotSegments(localURI);
  }

  private static URI resolveReferenceStartingWithQueryString(URI paramURI1, URI paramURI2)
  {
    String str = paramURI1.toString();
    str = str.indexOf('?') > -1 ? str.substring(0, str.indexOf('?')) : str;
    return URI.create(str + paramURI2.toString());
  }

  private static URI removeDotSegments(URI paramURI)
  {
    String str1 = paramURI.getPath();
    if ((str1 == null) || (str1.indexOf("/.") == -1))
      return paramURI;
    String[] arrayOfString = str1.split("/");
    Stack localStack = new Stack();
    for (int i = 0; i < arrayOfString.length; i++)
      if ((arrayOfString[i].length() != 0) && (!".".equals(arrayOfString[i])))
        if ("..".equals(arrayOfString[i]))
        {
          if (!localStack.isEmpty())
            localStack.pop();
        }
        else
          localStack.push(arrayOfString[i]);
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = localStack.iterator();
    while (localIterator.hasNext())
    {
      String str2 = (String)localIterator.next();
      localStringBuilder.append('/').append(str2);
    }
    try
    {
      return new URI(paramURI.getScheme(), paramURI.getAuthority(), localStringBuilder.toString(), paramURI.getQuery(), paramURI.getFragment());
    }
    catch (URISyntaxException localURISyntaxException)
    {
      throw new IllegalArgumentException(localURISyntaxException);
    }
  }

  public static HttpHost extractHost(URI paramURI)
  {
    if (paramURI == null)
      return null;
    HttpHost localHttpHost = null;
    if (paramURI.isAbsolute())
    {
      int i = paramURI.getPort();
      String str1 = paramURI.getHost();
      if (str1 == null)
      {
        str1 = paramURI.getAuthority();
        if (str1 != null)
        {
          int j = str1.indexOf('@');
          if (j >= 0)
            if (str1.length() > j + 1)
              str1 = str1.substring(j + 1);
            else
              str1 = null;
          if (str1 != null)
          {
            int k = str1.indexOf(':');
            if (k >= 0)
            {
              int m = k + 1;
              int n = 0;
              for (int i1 = m; (i1 < str1.length()) && (Character.isDigit(str1.charAt(i1))); i1++)
                n++;
              if (n > 0)
                try
                {
                  i = Integer.parseInt(str1.substring(m, m + n));
                }
                catch (NumberFormatException localNumberFormatException)
                {
                }
              str1 = str1.substring(0, k);
            }
          }
        }
      }
      String str2 = paramURI.getScheme();
      if (str1 != null)
        localHttpHost = new HttpHost(str1, i, str2);
    }
    return localHttpHost;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.client.utils.URIUtils
 * JD-Core Version:    0.6.2
 */