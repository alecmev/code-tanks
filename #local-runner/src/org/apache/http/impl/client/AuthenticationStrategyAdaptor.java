package org.apache.http.impl.client;

import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;
import org.apache.commons.logging.Log;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthOption;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.client.AuthCache;
import org.apache.http.client.AuthenticationHandler;
import org.apache.http.client.AuthenticationStrategy;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.protocol.HttpContext;

@Deprecated
class AuthenticationStrategyAdaptor
  implements AuthenticationStrategy
{
  private final Log log;
  private final AuthenticationHandler handler;

  public boolean isAuthenticationRequested(HttpHost paramHttpHost, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    return this.handler.isAuthenticationRequested(paramHttpResponse, paramHttpContext);
  }

  public Map getChallenges(HttpHost paramHttpHost, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws MalformedChallengeException
  {
    return this.handler.getChallenges(paramHttpResponse, paramHttpContext);
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
    CredentialsProvider localCredentialsProvider = (CredentialsProvider)paramHttpContext.getAttribute("http.auth.credentials-provider");
    if (localCredentialsProvider == null)
    {
      this.log.debug("Credentials provider not set in the context");
      return localLinkedList;
    }
    AuthScheme localAuthScheme;
    try
    {
      localAuthScheme = this.handler.selectScheme(paramMap, paramHttpResponse, paramHttpContext);
    }
    catch (AuthenticationException localAuthenticationException)
    {
      if (this.log.isWarnEnabled())
        this.log.warn(localAuthenticationException.getMessage(), localAuthenticationException);
      return localLinkedList;
    }
    String str = localAuthScheme.getSchemeName();
    Header localHeader = (Header)paramMap.get(str.toLowerCase(Locale.US));
    localAuthScheme.processChallenge(localHeader);
    AuthScope localAuthScope = new AuthScope(paramHttpHost.getHostName(), paramHttpHost.getPort(), localAuthScheme.getRealm(), localAuthScheme.getSchemeName());
    Credentials localCredentials = localCredentialsProvider.getCredentials(localAuthScope);
    if (localCredentials != null)
      localLinkedList.add(new AuthOption(localAuthScheme, localCredentials));
    return localLinkedList;
  }

  public void authSucceeded(HttpHost paramHttpHost, AuthScheme paramAuthScheme, HttpContext paramHttpContext)
  {
    Object localObject = (AuthCache)paramHttpContext.getAttribute("http.auth.auth-cache");
    if (isCachable(paramAuthScheme))
    {
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

  public void authFailed(HttpHost paramHttpHost, AuthScheme paramAuthScheme, HttpContext paramHttpContext)
  {
    AuthCache localAuthCache = (AuthCache)paramHttpContext.getAttribute("http.auth.auth-cache");
    if (localAuthCache == null)
      return;
    if (this.log.isDebugEnabled())
      this.log.debug("Removing from cache '" + paramAuthScheme.getSchemeName() + "' auth scheme for " + paramHttpHost);
    localAuthCache.remove(paramHttpHost);
  }

  private boolean isCachable(AuthScheme paramAuthScheme)
  {
    if ((paramAuthScheme == null) || (!paramAuthScheme.isComplete()))
      return false;
    String str = paramAuthScheme.getSchemeName();
    return (str.equalsIgnoreCase("Basic")) || (str.equalsIgnoreCase("Digest"));
  }

  public AuthenticationHandler getHandler()
  {
    return this.handler;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.client.AuthenticationStrategyAdaptor
 * JD-Core Version:    0.6.2
 */