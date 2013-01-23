package org.apache.http.impl.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.FormattedHeader;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.auth.AuthOption;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthSchemeRegistry;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.client.AuthCache;
import org.apache.http.client.AuthenticationStrategy;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.CharArrayBuffer;

class AuthenticationStrategyImpl
  implements AuthenticationStrategy
{
  private final Log log = LogFactory.getLog(getClass());
  private static final List DEFAULT_SCHEME_PRIORITY = Collections.unmodifiableList(Arrays.asList(new String[] { "negotiate", "Kerberos", "NTLM", "Digest", "Basic" }));
  private final int challengeCode;
  private final String headerName;
  private final String prefParamName;

  AuthenticationStrategyImpl(int paramInt, String paramString1, String paramString2)
  {
    this.challengeCode = paramInt;
    this.headerName = paramString1;
    this.prefParamName = paramString2;
  }

  public boolean isAuthenticationRequested(HttpHost paramHttpHost, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    if (paramHttpResponse == null)
      throw new IllegalArgumentException("HTTP response may not be null");
    int i = paramHttpResponse.getStatusLine().getStatusCode();
    return i == this.challengeCode;
  }

  public Map getChallenges(HttpHost paramHttpHost, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws MalformedChallengeException
  {
    if (paramHttpResponse == null)
      throw new IllegalArgumentException("HTTP response may not be null");
    Header[] arrayOfHeader1 = paramHttpResponse.getHeaders(this.headerName);
    HashMap localHashMap = new HashMap(arrayOfHeader1.length);
    for (Header localHeader : arrayOfHeader1)
    {
      CharArrayBuffer localCharArrayBuffer;
      if ((localHeader instanceof FormattedHeader))
      {
        localCharArrayBuffer = ((FormattedHeader)localHeader).getBuffer();
        str1 = ((FormattedHeader)localHeader).getValuePos();
      }
      else
      {
        str2 = localHeader.getValue();
        if (str2 == null)
          throw new MalformedChallengeException("Header value is null");
        localCharArrayBuffer = new CharArrayBuffer(str2.length());
        localCharArrayBuffer.append(str2);
      }
      for (String str1 = 0; (str1 < localCharArrayBuffer.length()) && (HTTP.isWhitespace(localCharArrayBuffer.charAt(str1))); str1++);
      String str2 = str1;
      while ((str1 < localCharArrayBuffer.length()) && (!HTTP.isWhitespace(localCharArrayBuffer.charAt(str1))))
        str1++;
      int k = str1;
      String str3 = localCharArrayBuffer.substring(str2, k);
      localHashMap.put(str3.toLowerCase(Locale.US), localHeader);
    }
    return localHashMap;
  }

  public Queue select(Map paramMap, HttpHost paramHttpHost, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws MalformedChallengeException
  {
    if (paramMap == null)
      throw new IllegalArgumentException("Map of auth challenges may not be null");
    if (paramHttpHost == null)
      throw new IllegalArgumentException("Host may not be null");
    if (paramHttpResponse == null)
      throw new IllegalArgumentException("HTTP response may not be null");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    LinkedList localLinkedList = new LinkedList();
    AuthSchemeRegistry localAuthSchemeRegistry = (AuthSchemeRegistry)paramHttpContext.getAttribute("http.authscheme-registry");
    if (localAuthSchemeRegistry == null)
    {
      this.log.debug("Auth scheme registry not set in the context");
      return localLinkedList;
    }
    CredentialsProvider localCredentialsProvider = (CredentialsProvider)paramHttpContext.getAttribute("http.auth.credentials-provider");
    if (localCredentialsProvider == null)
    {
      this.log.debug("Credentials provider not set in the context");
      return localLinkedList;
    }
    List localList = (List)paramHttpResponse.getParams().getParameter(this.prefParamName);
    if (localList == null)
      localList = DEFAULT_SCHEME_PRIORITY;
    if (this.log.isDebugEnabled())
      this.log.debug("Authentication schemes in the order of preference: " + localList);
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      Header localHeader = (Header)paramMap.get(str.toLowerCase(Locale.US));
      if (localHeader != null)
        try
        {
          AuthScheme localAuthScheme = localAuthSchemeRegistry.getAuthScheme(str, paramHttpResponse.getParams());
          localAuthScheme.processChallenge(localHeader);
          AuthScope localAuthScope = new AuthScope(paramHttpHost.getHostName(), paramHttpHost.getPort(), localAuthScheme.getRealm(), localAuthScheme.getSchemeName());
          Credentials localCredentials = localCredentialsProvider.getCredentials(localAuthScope);
          if (localCredentials != null)
            localLinkedList.add(new AuthOption(localAuthScheme, localCredentials));
        }
        catch (IllegalStateException localIllegalStateException)
        {
          if (this.log.isWarnEnabled())
            this.log.warn("Authentication scheme " + str + " not supported");
        }
      else if (this.log.isDebugEnabled())
        this.log.debug("Challenge for " + str + " authentication scheme not available");
    }
    return localLinkedList;
  }

  public void authSucceeded(HttpHost paramHttpHost, AuthScheme paramAuthScheme, HttpContext paramHttpContext)
  {
    if (paramHttpHost == null)
      throw new IllegalArgumentException("Host may not be null");
    if (paramAuthScheme == null)
      throw new IllegalArgumentException("Auth scheme may not be null");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    if (isCachable(paramAuthScheme))
    {
      Object localObject = (AuthCache)paramHttpContext.getAttribute("http.auth.auth-cache");
      if (localObject == null)
      {
        localObject = new BasicAuthCache();
        paramHttpContext.setAttribute("http.auth.auth-cache", localObject);
      }
      if (this.log.isDebugEnabled())
        this.log.debug("Caching '" + paramAuthScheme.getSchemeName() + "' auth scheme for " + paramHttpHost);
      ((AuthCache)localObject).put(paramHttpHost, paramAuthScheme);
    }
  }

  protected boolean isCachable(AuthScheme paramAuthScheme)
  {
    if ((paramAuthScheme == null) || (!paramAuthScheme.isComplete()))
      return false;
    String str = paramAuthScheme.getSchemeName();
    return (str.equalsIgnoreCase("Basic")) || (str.equalsIgnoreCase("Digest"));
  }

  public void authFailed(HttpHost paramHttpHost, AuthScheme paramAuthScheme, HttpContext paramHttpContext)
  {
    if (paramHttpHost == null)
      throw new IllegalArgumentException("Host may not be null");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    AuthCache localAuthCache = (AuthCache)paramHttpContext.getAttribute("http.auth.auth-cache");
    if (localAuthCache != null)
    {
      if (this.log.isDebugEnabled())
        this.log.debug("Clearing cached auth scheme for " + paramHttpHost);
      localAuthCache.remove(paramHttpHost);
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.client.AuthenticationStrategyImpl
 * JD-Core Version:    0.6.2
 */