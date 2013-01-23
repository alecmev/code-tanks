package org.apache.http.impl.client;

import java.io.IOException;
import java.lang.reflect.UndeclaredThrowableException;
import java.net.URI;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.auth.AuthSchemeRegistry;
import org.apache.http.client.AuthenticationStrategy;
import org.apache.http.client.BackoffManager;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ConnectionBackoffStrategy;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.RedirectStrategy;
import org.apache.http.client.RequestDirector;
import org.apache.http.client.UserTokenHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ClientConnectionManagerFactory;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.cookie.CookieSpecRegistry;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.auth.BasicSchemeFactory;
import org.apache.http.impl.auth.DigestSchemeFactory;
import org.apache.http.impl.auth.KerberosSchemeFactory;
import org.apache.http.impl.auth.NTLMSchemeFactory;
import org.apache.http.impl.auth.SPNegoSchemeFactory;
import org.apache.http.impl.conn.BasicClientConnectionManager;
import org.apache.http.impl.conn.DefaultHttpRoutePlanner;
import org.apache.http.impl.conn.SchemeRegistryFactory;
import org.apache.http.impl.cookie.BestMatchSpecFactory;
import org.apache.http.impl.cookie.BrowserCompatSpecFactory;
import org.apache.http.impl.cookie.IgnoreSpecFactory;
import org.apache.http.impl.cookie.NetscapeDraftSpecFactory;
import org.apache.http.impl.cookie.RFC2109SpecFactory;
import org.apache.http.impl.cookie.RFC2965SpecFactory;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.BasicHttpProcessor;
import org.apache.http.protocol.DefaultedHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpRequestExecutor;
import org.apache.http.protocol.ImmutableHttpProcessor;

public abstract class AbstractHttpClient
  implements HttpClient
{
  private final Log log = LogFactory.getLog(getClass());
  private HttpParams defaultParams;
  private HttpRequestExecutor requestExec;
  private ClientConnectionManager connManager;
  private ConnectionReuseStrategy reuseStrategy;
  private ConnectionKeepAliveStrategy keepAliveStrategy;
  private CookieSpecRegistry supportedCookieSpecs;
  private AuthSchemeRegistry supportedAuthSchemes;
  private BasicHttpProcessor mutableProcessor;
  private ImmutableHttpProcessor protocolProcessor;
  private HttpRequestRetryHandler retryHandler;
  private RedirectStrategy redirectStrategy;
  private AuthenticationStrategy targetAuthStrategy;
  private AuthenticationStrategy proxyAuthStrategy;
  private CookieStore cookieStore;
  private CredentialsProvider credsProvider;
  private HttpRoutePlanner routePlanner;
  private UserTokenHandler userTokenHandler;
  private ConnectionBackoffStrategy connectionBackoffStrategy;
  private BackoffManager backoffManager;

  protected AbstractHttpClient(ClientConnectionManager paramClientConnectionManager, HttpParams paramHttpParams)
  {
    this.defaultParams = paramHttpParams;
    this.connManager = paramClientConnectionManager;
  }

  protected abstract HttpParams createHttpParams();

  protected abstract BasicHttpProcessor createHttpProcessor();

  protected HttpContext createHttpContext()
  {
    BasicHttpContext localBasicHttpContext = new BasicHttpContext();
    localBasicHttpContext.setAttribute("http.scheme-registry", getConnectionManager().getSchemeRegistry());
    localBasicHttpContext.setAttribute("http.authscheme-registry", getAuthSchemes());
    localBasicHttpContext.setAttribute("http.cookiespec-registry", getCookieSpecs());
    localBasicHttpContext.setAttribute("http.cookie-store", getCookieStore());
    localBasicHttpContext.setAttribute("http.auth.credentials-provider", getCredentialsProvider());
    return localBasicHttpContext;
  }

  protected ClientConnectionManager createClientConnectionManager()
  {
    SchemeRegistry localSchemeRegistry = SchemeRegistryFactory.createDefault();
    Object localObject = null;
    HttpParams localHttpParams = getParams();
    ClientConnectionManagerFactory localClientConnectionManagerFactory = null;
    String str = (String)localHttpParams.getParameter("http.connection-manager.factory-class-name");
    if (str != null)
      try
      {
        Class localClass = Class.forName(str);
        localClientConnectionManagerFactory = (ClientConnectionManagerFactory)localClass.newInstance();
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        throw new IllegalStateException("Invalid class name: " + str);
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        throw new IllegalAccessError(localIllegalAccessException.getMessage());
      }
      catch (InstantiationException localInstantiationException)
      {
        throw new InstantiationError(localInstantiationException.getMessage());
      }
    if (localClientConnectionManagerFactory != null)
      localObject = localClientConnectionManagerFactory.newInstance(localHttpParams, localSchemeRegistry);
    else
      localObject = new BasicClientConnectionManager(localSchemeRegistry);
    return localObject;
  }

  protected AuthSchemeRegistry createAuthSchemeRegistry()
  {
    AuthSchemeRegistry localAuthSchemeRegistry = new AuthSchemeRegistry();
    localAuthSchemeRegistry.register("Basic", new BasicSchemeFactory());
    localAuthSchemeRegistry.register("Digest", new DigestSchemeFactory());
    localAuthSchemeRegistry.register("NTLM", new NTLMSchemeFactory());
    localAuthSchemeRegistry.register("negotiate", new SPNegoSchemeFactory());
    localAuthSchemeRegistry.register("Kerberos", new KerberosSchemeFactory());
    return localAuthSchemeRegistry;
  }

  protected CookieSpecRegistry createCookieSpecRegistry()
  {
    CookieSpecRegistry localCookieSpecRegistry = new CookieSpecRegistry();
    localCookieSpecRegistry.register("best-match", new BestMatchSpecFactory());
    localCookieSpecRegistry.register("compatibility", new BrowserCompatSpecFactory());
    localCookieSpecRegistry.register("netscape", new NetscapeDraftSpecFactory());
    localCookieSpecRegistry.register("rfc2109", new RFC2109SpecFactory());
    localCookieSpecRegistry.register("rfc2965", new RFC2965SpecFactory());
    localCookieSpecRegistry.register("ignoreCookies", new IgnoreSpecFactory());
    return localCookieSpecRegistry;
  }

  protected HttpRequestExecutor createRequestExecutor()
  {
    return new HttpRequestExecutor();
  }

  protected ConnectionReuseStrategy createConnectionReuseStrategy()
  {
    return new DefaultConnectionReuseStrategy();
  }

  protected ConnectionKeepAliveStrategy createConnectionKeepAliveStrategy()
  {
    return new DefaultConnectionKeepAliveStrategy();
  }

  protected HttpRequestRetryHandler createHttpRequestRetryHandler()
  {
    return new DefaultHttpRequestRetryHandler();
  }

  protected AuthenticationStrategy createTargetAuthenticationStrategy()
  {
    return new TargetAuthenticationStrategy();
  }

  protected AuthenticationStrategy createProxyAuthenticationStrategy()
  {
    return new ProxyAuthenticationStrategy();
  }

  protected CookieStore createCookieStore()
  {
    return new BasicCookieStore();
  }

  protected CredentialsProvider createCredentialsProvider()
  {
    return new BasicCredentialsProvider();
  }

  protected HttpRoutePlanner createHttpRoutePlanner()
  {
    return new DefaultHttpRoutePlanner(getConnectionManager().getSchemeRegistry());
  }

  protected UserTokenHandler createUserTokenHandler()
  {
    return new DefaultUserTokenHandler();
  }

  public final synchronized HttpParams getParams()
  {
    if (this.defaultParams == null)
      this.defaultParams = createHttpParams();
    return this.defaultParams;
  }

  public final synchronized ClientConnectionManager getConnectionManager()
  {
    if (this.connManager == null)
      this.connManager = createClientConnectionManager();
    return this.connManager;
  }

  public final synchronized HttpRequestExecutor getRequestExecutor()
  {
    if (this.requestExec == null)
      this.requestExec = createRequestExecutor();
    return this.requestExec;
  }

  public final synchronized AuthSchemeRegistry getAuthSchemes()
  {
    if (this.supportedAuthSchemes == null)
      this.supportedAuthSchemes = createAuthSchemeRegistry();
    return this.supportedAuthSchemes;
  }

  public final synchronized ConnectionBackoffStrategy getConnectionBackoffStrategy()
  {
    return this.connectionBackoffStrategy;
  }

  public final synchronized CookieSpecRegistry getCookieSpecs()
  {
    if (this.supportedCookieSpecs == null)
      this.supportedCookieSpecs = createCookieSpecRegistry();
    return this.supportedCookieSpecs;
  }

  public final synchronized BackoffManager getBackoffManager()
  {
    return this.backoffManager;
  }

  public final synchronized ConnectionReuseStrategy getConnectionReuseStrategy()
  {
    if (this.reuseStrategy == null)
      this.reuseStrategy = createConnectionReuseStrategy();
    return this.reuseStrategy;
  }

  public final synchronized ConnectionKeepAliveStrategy getConnectionKeepAliveStrategy()
  {
    if (this.keepAliveStrategy == null)
      this.keepAliveStrategy = createConnectionKeepAliveStrategy();
    return this.keepAliveStrategy;
  }

  public final synchronized HttpRequestRetryHandler getHttpRequestRetryHandler()
  {
    if (this.retryHandler == null)
      this.retryHandler = createHttpRequestRetryHandler();
    return this.retryHandler;
  }

  public final synchronized RedirectStrategy getRedirectStrategy()
  {
    if (this.redirectStrategy == null)
      this.redirectStrategy = new DefaultRedirectStrategy();
    return this.redirectStrategy;
  }

  public final synchronized AuthenticationStrategy getTargetAuthenticationStrategy()
  {
    if (this.targetAuthStrategy == null)
      this.targetAuthStrategy = createTargetAuthenticationStrategy();
    return this.targetAuthStrategy;
  }

  public final synchronized AuthenticationStrategy getProxyAuthenticationStrategy()
  {
    if (this.proxyAuthStrategy == null)
      this.proxyAuthStrategy = createProxyAuthenticationStrategy();
    return this.proxyAuthStrategy;
  }

  public final synchronized CookieStore getCookieStore()
  {
    if (this.cookieStore == null)
      this.cookieStore = createCookieStore();
    return this.cookieStore;
  }

  public final synchronized CredentialsProvider getCredentialsProvider()
  {
    if (this.credsProvider == null)
      this.credsProvider = createCredentialsProvider();
    return this.credsProvider;
  }

  public final synchronized HttpRoutePlanner getRoutePlanner()
  {
    if (this.routePlanner == null)
      this.routePlanner = createHttpRoutePlanner();
    return this.routePlanner;
  }

  public final synchronized UserTokenHandler getUserTokenHandler()
  {
    if (this.userTokenHandler == null)
      this.userTokenHandler = createUserTokenHandler();
    return this.userTokenHandler;
  }

  protected final synchronized BasicHttpProcessor getHttpProcessor()
  {
    if (this.mutableProcessor == null)
      this.mutableProcessor = createHttpProcessor();
    return this.mutableProcessor;
  }

  private final synchronized HttpProcessor getProtocolProcessor()
  {
    if (this.protocolProcessor == null)
    {
      BasicHttpProcessor localBasicHttpProcessor = getHttpProcessor();
      int i = localBasicHttpProcessor.getRequestInterceptorCount();
      HttpRequestInterceptor[] arrayOfHttpRequestInterceptor = new HttpRequestInterceptor[i];
      for (int j = 0; j < i; j++)
        arrayOfHttpRequestInterceptor[j] = localBasicHttpProcessor.getRequestInterceptor(j);
      j = localBasicHttpProcessor.getResponseInterceptorCount();
      HttpResponseInterceptor[] arrayOfHttpResponseInterceptor = new HttpResponseInterceptor[j];
      for (int k = 0; k < j; k++)
        arrayOfHttpResponseInterceptor[k] = localBasicHttpProcessor.getResponseInterceptor(k);
      this.protocolProcessor = new ImmutableHttpProcessor(arrayOfHttpRequestInterceptor, arrayOfHttpResponseInterceptor);
    }
    return this.protocolProcessor;
  }

  public final HttpResponse execute(HttpUriRequest paramHttpUriRequest)
    throws IOException, ClientProtocolException
  {
    return execute(paramHttpUriRequest, (HttpContext)null);
  }

  public final HttpResponse execute(HttpUriRequest paramHttpUriRequest, HttpContext paramHttpContext)
    throws IOException, ClientProtocolException
  {
    if (paramHttpUriRequest == null)
      throw new IllegalArgumentException("Request must not be null.");
    return execute(determineTarget(paramHttpUriRequest), paramHttpUriRequest, paramHttpContext);
  }

  private static HttpHost determineTarget(HttpUriRequest paramHttpUriRequest)
    throws ClientProtocolException
  {
    HttpHost localHttpHost = null;
    URI localURI = paramHttpUriRequest.getURI();
    if (localURI.isAbsolute())
    {
      localHttpHost = URIUtils.extractHost(localURI);
      if (localHttpHost == null)
        throw new ClientProtocolException("URI does not specify a valid host name: " + localURI);
    }
    return localHttpHost;
  }

  public final HttpResponse execute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws IOException, ClientProtocolException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("Request must not be null.");
    Object localObject1 = null;
    RequestDirector localRequestDirector = null;
    HttpRoutePlanner localHttpRoutePlanner = null;
    ConnectionBackoffStrategy localConnectionBackoffStrategy = null;
    BackoffManager localBackoffManager = null;
    Object localObject2;
    synchronized (this)
    {
      localObject2 = createHttpContext();
      if (paramHttpContext == null)
        localObject1 = localObject2;
      else
        localObject1 = new DefaultedHttpContext(paramHttpContext, (HttpContext)localObject2);
      localRequestDirector = createClientRequestDirector(getRequestExecutor(), getConnectionManager(), getConnectionReuseStrategy(), getConnectionKeepAliveStrategy(), getRoutePlanner(), getProtocolProcessor(), getHttpRequestRetryHandler(), getRedirectStrategy(), getTargetAuthenticationStrategy(), getProxyAuthenticationStrategy(), getUserTokenHandler(), determineParams(paramHttpRequest));
      localHttpRoutePlanner = getRoutePlanner();
      localConnectionBackoffStrategy = getConnectionBackoffStrategy();
      localBackoffManager = getBackoffManager();
    }
    try
    {
      if ((localConnectionBackoffStrategy != null) && (localBackoffManager != null))
      {
        ??? = paramHttpHost != null ? paramHttpHost : (HttpHost)determineParams(paramHttpRequest).getParameter("http.default-host");
        localObject2 = localHttpRoutePlanner.determineRoute((HttpHost)???, paramHttpRequest, (HttpContext)localObject1);
        HttpResponse localHttpResponse;
        try
        {
          localHttpResponse = localRequestDirector.execute(paramHttpHost, paramHttpRequest, (HttpContext)localObject1);
        }
        catch (RuntimeException localRuntimeException)
        {
          if (localConnectionBackoffStrategy.shouldBackoff(localRuntimeException))
            localBackoffManager.backOff((HttpRoute)localObject2);
          throw localRuntimeException;
        }
        catch (Exception localException)
        {
          if (localConnectionBackoffStrategy.shouldBackoff(localException))
            localBackoffManager.backOff((HttpRoute)localObject2);
          if ((localException instanceof HttpException))
            throw ((HttpException)localException);
          if ((localException instanceof IOException))
            throw ((IOException)localException);
          throw new UndeclaredThrowableException(localException);
        }
        if (localConnectionBackoffStrategy.shouldBackoff(localHttpResponse))
          localBackoffManager.backOff((HttpRoute)localObject2);
        else
          localBackoffManager.probe((HttpRoute)localObject2);
        return localHttpResponse;
      }
      return localRequestDirector.execute(paramHttpHost, paramHttpRequest, (HttpContext)localObject1);
    }
    catch (HttpException localHttpException)
    {
      throw new ClientProtocolException(localHttpException);
    }
  }

  protected RequestDirector createClientRequestDirector(HttpRequestExecutor paramHttpRequestExecutor, ClientConnectionManager paramClientConnectionManager, ConnectionReuseStrategy paramConnectionReuseStrategy, ConnectionKeepAliveStrategy paramConnectionKeepAliveStrategy, HttpRoutePlanner paramHttpRoutePlanner, HttpProcessor paramHttpProcessor, HttpRequestRetryHandler paramHttpRequestRetryHandler, RedirectStrategy paramRedirectStrategy, AuthenticationStrategy paramAuthenticationStrategy1, AuthenticationStrategy paramAuthenticationStrategy2, UserTokenHandler paramUserTokenHandler, HttpParams paramHttpParams)
  {
    return new DefaultRequestDirector(this.log, paramHttpRequestExecutor, paramClientConnectionManager, paramConnectionReuseStrategy, paramConnectionKeepAliveStrategy, paramHttpRoutePlanner, paramHttpProcessor, paramHttpRequestRetryHandler, paramRedirectStrategy, paramAuthenticationStrategy1, paramAuthenticationStrategy2, paramUserTokenHandler, paramHttpParams);
  }

  protected HttpParams determineParams(HttpRequest paramHttpRequest)
  {
    return new ClientParamsStack(null, getParams(), paramHttpRequest.getParams(), null);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.client.AbstractHttpClient
 * JD-Core Version:    0.6.2
 */