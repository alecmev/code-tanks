package org.apache.http.client.protocol;

import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.auth.AuthProtocolState;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.Credentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.protocol.HttpContext;

public class RequestAuthCache
  implements HttpRequestInterceptor
{
  private final Log log = LogFactory.getLog(getClass());

  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    AuthCache localAuthCache = (AuthCache)paramHttpContext.getAttribute("http.auth.auth-cache");
    if (localAuthCache == null)
    {
      this.log.debug("Auth cache not set in the context");
      return;
    }
    CredentialsProvider localCredentialsProvider = (CredentialsProvider)paramHttpContext.getAttribute("http.auth.credentials-provider");
    if (localCredentialsProvider == null)
    {
      this.log.debug("Credentials provider not set in the context");
      return;
    }
    HttpHost localHttpHost = (HttpHost)paramHttpContext.getAttribute("http.target_host");
    if (localHttpHost.getPort() < 0)
    {
      localObject1 = (SchemeRegistry)paramHttpContext.getAttribute("http.scheme-registry");
      localObject2 = ((SchemeRegistry)localObject1).getScheme(localHttpHost);
      localHttpHost = new HttpHost(localHttpHost.getHostName(), ((Scheme)localObject2).resolvePort(localHttpHost.getPort()), localHttpHost.getSchemeName());
    }
    Object localObject1 = (AuthState)paramHttpContext.getAttribute("http.auth.target-scope");
    if ((localHttpHost != null) && (localObject1 != null) && (((AuthState)localObject1).getState() == AuthProtocolState.UNCHALLENGED))
    {
      localObject2 = localAuthCache.get(localHttpHost);
      if (localObject2 != null)
        doPreemptiveAuth(localHttpHost, (AuthScheme)localObject2, (AuthState)localObject1, localCredentialsProvider);
    }
    Object localObject2 = (HttpHost)paramHttpContext.getAttribute("http.proxy_host");
    AuthState localAuthState = (AuthState)paramHttpContext.getAttribute("http.auth.proxy-scope");
    if ((localObject2 != null) && (localAuthState != null) && (localAuthState.getState() == AuthProtocolState.UNCHALLENGED))
    {
      AuthScheme localAuthScheme = localAuthCache.get((HttpHost)localObject2);
      if (localAuthScheme != null)
        doPreemptiveAuth((HttpHost)localObject2, localAuthScheme, localAuthState, localCredentialsProvider);
    }
  }

  private void doPreemptiveAuth(HttpHost paramHttpHost, AuthScheme paramAuthScheme, AuthState paramAuthState, CredentialsProvider paramCredentialsProvider)
  {
    String str = paramAuthScheme.getSchemeName();
    if (this.log.isDebugEnabled())
      this.log.debug("Re-using cached '" + str + "' auth scheme for " + paramHttpHost);
    AuthScope localAuthScope = new AuthScope(paramHttpHost, AuthScope.ANY_REALM, str);
    Credentials localCredentials = paramCredentialsProvider.getCredentials(localAuthScope);
    if (localCredentials != null)
    {
      paramAuthState.setState(AuthProtocolState.SUCCESS);
      paramAuthState.update(paramAuthScheme, localCredentials);
    }
    else
    {
      this.log.debug("No credentials for preemptive authentication");
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.client.protocol.RequestAuthCache
 * JD-Core Version:    0.6.2
 */