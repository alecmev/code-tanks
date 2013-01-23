package org.apache.http.impl.client;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.logging.Log;
import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;
import org.apache.http.StatusLine;
import org.apache.http.auth.AuthProtocolState;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthenticationHandler;
import org.apache.http.client.AuthenticationStrategy;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.NonRepeatableRequestException;
import org.apache.http.client.RedirectException;
import org.apache.http.client.RedirectHandler;
import org.apache.http.client.RedirectStrategy;
import org.apache.http.client.RequestDirector;
import org.apache.http.client.UserTokenHandler;
import org.apache.http.client.methods.AbortableHttpRequest;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.conn.BasicManagedEntity;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ClientConnectionRequest;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.ManagedClientConnection;
import org.apache.http.conn.routing.BasicRouteDirector;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.routing.HttpRouteDirector;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.conn.ConnectionShutdownException;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpRequestExecutor;
import org.apache.http.util.EntityUtils;

public class DefaultRequestDirector
  implements RequestDirector
{
  private final Log log;
  protected final ClientConnectionManager connManager;
  protected final HttpRoutePlanner routePlanner;
  protected final ConnectionReuseStrategy reuseStrategy;
  protected final ConnectionKeepAliveStrategy keepAliveStrategy;
  protected final HttpRequestExecutor requestExec;
  protected final HttpProcessor httpProcessor;
  protected final HttpRequestRetryHandler retryHandler;

  @Deprecated
  protected final RedirectHandler redirectHandler;
  protected final RedirectStrategy redirectStrategy;

  @Deprecated
  protected final AuthenticationHandler targetAuthHandler;
  protected final AuthenticationStrategy targetAuthStrategy;

  @Deprecated
  protected final AuthenticationHandler proxyAuthHandler;
  protected final AuthenticationStrategy proxyAuthStrategy;
  protected final UserTokenHandler userTokenHandler;
  protected final HttpParams params;
  protected ManagedClientConnection managedConn;
  protected final AuthState targetAuthState;
  protected final AuthState proxyAuthState;
  private final HttpAuthenticator authenticator;
  private int execCount;
  private int redirectCount;
  private int maxRedirects;
  private HttpHost virtualHost;

  public DefaultRequestDirector(Log paramLog, HttpRequestExecutor paramHttpRequestExecutor, ClientConnectionManager paramClientConnectionManager, ConnectionReuseStrategy paramConnectionReuseStrategy, ConnectionKeepAliveStrategy paramConnectionKeepAliveStrategy, HttpRoutePlanner paramHttpRoutePlanner, HttpProcessor paramHttpProcessor, HttpRequestRetryHandler paramHttpRequestRetryHandler, RedirectStrategy paramRedirectStrategy, AuthenticationStrategy paramAuthenticationStrategy1, AuthenticationStrategy paramAuthenticationStrategy2, UserTokenHandler paramUserTokenHandler, HttpParams paramHttpParams)
  {
    if (paramLog == null)
      throw new IllegalArgumentException("Log may not be null.");
    if (paramHttpRequestExecutor == null)
      throw new IllegalArgumentException("Request executor may not be null.");
    if (paramClientConnectionManager == null)
      throw new IllegalArgumentException("Client connection manager may not be null.");
    if (paramConnectionReuseStrategy == null)
      throw new IllegalArgumentException("Connection reuse strategy may not be null.");
    if (paramConnectionKeepAliveStrategy == null)
      throw new IllegalArgumentException("Connection keep alive strategy may not be null.");
    if (paramHttpRoutePlanner == null)
      throw new IllegalArgumentException("Route planner may not be null.");
    if (paramHttpProcessor == null)
      throw new IllegalArgumentException("HTTP protocol processor may not be null.");
    if (paramHttpRequestRetryHandler == null)
      throw new IllegalArgumentException("HTTP request retry handler may not be null.");
    if (paramRedirectStrategy == null)
      throw new IllegalArgumentException("Redirect strategy may not be null.");
    if (paramAuthenticationStrategy1 == null)
      throw new IllegalArgumentException("Target authentication strategy may not be null.");
    if (paramAuthenticationStrategy2 == null)
      throw new IllegalArgumentException("Proxy authentication strategy may not be null.");
    if (paramUserTokenHandler == null)
      throw new IllegalArgumentException("User token handler may not be null.");
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    this.log = paramLog;
    this.authenticator = new HttpAuthenticator(paramLog);
    this.requestExec = paramHttpRequestExecutor;
    this.connManager = paramClientConnectionManager;
    this.reuseStrategy = paramConnectionReuseStrategy;
    this.keepAliveStrategy = paramConnectionKeepAliveStrategy;
    this.routePlanner = paramHttpRoutePlanner;
    this.httpProcessor = paramHttpProcessor;
    this.retryHandler = paramHttpRequestRetryHandler;
    this.redirectStrategy = paramRedirectStrategy;
    this.targetAuthStrategy = paramAuthenticationStrategy1;
    this.proxyAuthStrategy = paramAuthenticationStrategy2;
    this.userTokenHandler = paramUserTokenHandler;
    this.params = paramHttpParams;
    if ((paramRedirectStrategy instanceof DefaultRedirectStrategyAdaptor))
      this.redirectHandler = ((DefaultRedirectStrategyAdaptor)paramRedirectStrategy).getHandler();
    else
      this.redirectHandler = null;
    if ((paramAuthenticationStrategy1 instanceof AuthenticationStrategyAdaptor))
      this.targetAuthHandler = ((AuthenticationStrategyAdaptor)paramAuthenticationStrategy1).getHandler();
    else
      this.targetAuthHandler = null;
    if ((paramAuthenticationStrategy2 instanceof AuthenticationStrategyAdaptor))
      this.proxyAuthHandler = ((AuthenticationStrategyAdaptor)paramAuthenticationStrategy2).getHandler();
    else
      this.proxyAuthHandler = null;
    this.managedConn = null;
    this.execCount = 0;
    this.redirectCount = 0;
    this.targetAuthState = new AuthState();
    this.proxyAuthState = new AuthState();
    this.maxRedirects = this.params.getIntParameter("http.protocol.max-redirects", 100);
  }

  private RequestWrapper wrapRequest(HttpRequest paramHttpRequest)
    throws ProtocolException
  {
    if ((paramHttpRequest instanceof HttpEntityEnclosingRequest))
      return new EntityEnclosingRequestWrapper((HttpEntityEnclosingRequest)paramHttpRequest);
    return new RequestWrapper(paramHttpRequest);
  }

  protected void rewriteRequestURI(RequestWrapper paramRequestWrapper, HttpRoute paramHttpRoute)
    throws ProtocolException
  {
    try
    {
      URI localURI = paramRequestWrapper.getURI();
      if ((paramHttpRoute.getProxyHost() != null) && (!paramHttpRoute.isTunnelled()))
      {
        if (!localURI.isAbsolute())
        {
          HttpHost localHttpHost = paramHttpRoute.getTargetHost();
          localURI = URIUtils.rewriteURI(localURI, localHttpHost, true);
        }
        else
        {
          localURI = URIUtils.rewriteURI(localURI);
        }
      }
      else if (localURI.isAbsolute())
        localURI = URIUtils.rewriteURI(localURI, null);
      else
        localURI = URIUtils.rewriteURI(localURI);
      paramRequestWrapper.setURI(localURI);
    }
    catch (URISyntaxException localURISyntaxException)
    {
      throw new ProtocolException("Invalid URI: " + paramRequestWrapper.getRequestLine().getUri(), localURISyntaxException);
    }
  }

  public HttpResponse execute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    paramHttpContext.setAttribute("http.auth.target-scope", this.targetAuthState);
    paramHttpContext.setAttribute("http.auth.proxy-scope", this.proxyAuthState);
    HttpRequest localHttpRequest = paramHttpRequest;
    RequestWrapper localRequestWrapper = wrapRequest(localHttpRequest);
    localRequestWrapper.setParams(this.params);
    HttpRoute localHttpRoute1 = determineRoute(paramHttpHost, localRequestWrapper, paramHttpContext);
    this.virtualHost = ((HttpHost)localRequestWrapper.getParams().getParameter("http.virtual-host"));
    if ((this.virtualHost != null) && (this.virtualHost.getPort() == -1))
    {
      int i = paramHttpHost.getPort();
      if (i != -1)
        this.virtualHost = new HttpHost(this.virtualHost.getHostName(), i, this.virtualHost.getSchemeName());
    }
    Object localObject1 = new RoutedRequest(localRequestWrapper, localHttpRoute1);
    boolean bool = false;
    int j = 0;
    try
    {
      HttpResponse localHttpResponse = null;
      while (j == 0)
      {
        localObject2 = ((RoutedRequest)localObject1).getRequest();
        HttpRoute localHttpRoute2 = ((RoutedRequest)localObject1).getRoute();
        localHttpResponse = null;
        Object localObject3 = paramHttpContext.getAttribute("http.user-token");
        Object localObject4;
        if (this.managedConn == null)
        {
          ClientConnectionRequest localClientConnectionRequest = this.connManager.requestConnection(localHttpRoute2, localObject3);
          if ((localHttpRequest instanceof AbortableHttpRequest))
            ((AbortableHttpRequest)localHttpRequest).setConnectionRequest(localClientConnectionRequest);
          long l1 = HttpClientParams.getConnectionManagerTimeout(this.params);
          try
          {
            this.managedConn = localClientConnectionRequest.getConnection(l1, TimeUnit.MILLISECONDS);
          }
          catch (InterruptedException localInterruptedException)
          {
            localObject4 = new InterruptedIOException();
            ((InterruptedIOException)localObject4).initCause(localInterruptedException);
            throw ((Throwable)localObject4);
          }
          if ((HttpConnectionParams.isStaleCheckingEnabled(this.params)) && (this.managedConn.isOpen()))
          {
            this.log.debug("Stale connection check");
            if (this.managedConn.isStale())
            {
              this.log.debug("Stale connection detected");
              this.managedConn.close();
            }
          }
        }
        if ((localHttpRequest instanceof AbortableHttpRequest))
          ((AbortableHttpRequest)localHttpRequest).setReleaseTrigger(this.managedConn);
        try
        {
          tryConnect((RoutedRequest)localObject1, paramHttpContext);
        }
        catch (TunnelRefusedException localTunnelRefusedException)
        {
          if (this.log.isDebugEnabled())
            this.log.debug(localTunnelRefusedException.getMessage());
          localHttpResponse = localTunnelRefusedException.getResponse();
          break;
        }
        String str = ((RequestWrapper)localObject2).getURI().getUserInfo();
        if (str != null)
          this.targetAuthState.update(new BasicScheme(), new UsernamePasswordCredentials(str));
        ((RequestWrapper)localObject2).resetHeaders();
        rewriteRequestURI((RequestWrapper)localObject2, localHttpRoute2);
        paramHttpHost = this.virtualHost;
        if (paramHttpHost == null)
          paramHttpHost = localHttpRoute2.getTargetHost();
        HttpHost localHttpHost = localHttpRoute2.getProxyHost();
        paramHttpContext.setAttribute("http.target_host", paramHttpHost);
        paramHttpContext.setAttribute("http.proxy_host", localHttpHost);
        paramHttpContext.setAttribute("http.connection", this.managedConn);
        this.requestExec.preProcess((HttpRequest)localObject2, this.httpProcessor, paramHttpContext);
        localHttpResponse = tryExecute((RoutedRequest)localObject1, paramHttpContext);
        if (localHttpResponse != null)
        {
          localHttpResponse.setParams(this.params);
          this.requestExec.postProcess(localHttpResponse, this.httpProcessor, paramHttpContext);
          bool = this.reuseStrategy.keepAlive(localHttpResponse, paramHttpContext);
          if (bool)
          {
            long l2 = this.keepAliveStrategy.getKeepAliveDuration(localHttpResponse, paramHttpContext);
            if (this.log.isDebugEnabled())
            {
              if (l2 > 0L)
                localObject4 = "for " + l2 + " " + TimeUnit.MILLISECONDS;
              else
                localObject4 = "indefinitely";
              this.log.debug("Connection can be kept alive " + (String)localObject4);
            }
            this.managedConn.setIdleDuration(l2, TimeUnit.MILLISECONDS);
          }
          RoutedRequest localRoutedRequest = handleResponse((RoutedRequest)localObject1, localHttpResponse, paramHttpContext);
          if (localRoutedRequest == null)
          {
            j = 1;
          }
          else
          {
            if (bool)
            {
              HttpEntity localHttpEntity = localHttpResponse.getEntity();
              EntityUtils.consume(localHttpEntity);
              this.managedConn.markReusable();
            }
            else
            {
              this.managedConn.close();
              if ((this.proxyAuthState.getState() == AuthProtocolState.SUCCESS) && (this.proxyAuthState.getAuthScheme() != null) && (this.proxyAuthState.getAuthScheme().isConnectionBased()))
              {
                this.log.debug("Resetting proxy auth state");
                this.proxyAuthState.reset();
              }
              if ((this.targetAuthState.getState() == AuthProtocolState.SUCCESS) && (this.targetAuthState.getAuthScheme() != null) && (this.targetAuthState.getAuthScheme().isConnectionBased()))
              {
                this.log.debug("Resetting target auth state");
                this.targetAuthState.reset();
              }
            }
            if (!localRoutedRequest.getRoute().equals(((RoutedRequest)localObject1).getRoute()))
              releaseConnection();
            localObject1 = localRoutedRequest;
          }
          if (this.managedConn != null)
          {
            if (localObject3 == null)
            {
              localObject3 = this.userTokenHandler.getUserToken(paramHttpContext);
              paramHttpContext.setAttribute("http.user-token", localObject3);
            }
            if (localObject3 != null)
              this.managedConn.setState(localObject3);
          }
        }
      }
      if ((localHttpResponse == null) || (localHttpResponse.getEntity() == null) || (!localHttpResponse.getEntity().isStreaming()))
      {
        if (bool)
          this.managedConn.markReusable();
        releaseConnection();
      }
      else
      {
        localObject2 = localHttpResponse.getEntity();
        localObject2 = new BasicManagedEntity((HttpEntity)localObject2, this.managedConn, bool);
        localHttpResponse.setEntity((HttpEntity)localObject2);
      }
      return localHttpResponse;
    }
    catch (ConnectionShutdownException localConnectionShutdownException)
    {
      Object localObject2 = new InterruptedIOException("Connection has been shut down");
      ((InterruptedIOException)localObject2).initCause(localConnectionShutdownException);
      throw ((Throwable)localObject2);
    }
    catch (HttpException localHttpException)
    {
      abortConnection();
      throw localHttpException;
    }
    catch (IOException localIOException)
    {
      abortConnection();
      throw localIOException;
    }
    catch (RuntimeException localRuntimeException)
    {
      abortConnection();
      throw localRuntimeException;
    }
  }

  private void tryConnect(RoutedRequest paramRoutedRequest, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    HttpRoute localHttpRoute = paramRoutedRequest.getRoute();
    RequestWrapper localRequestWrapper = paramRoutedRequest.getRequest();
    int i = 0;
    while (true)
    {
      paramHttpContext.setAttribute("http.request", localRequestWrapper);
      i++;
      try
      {
        if (!this.managedConn.isOpen())
          this.managedConn.open(localHttpRoute, paramHttpContext, this.params);
        else
          this.managedConn.setSocketTimeout(HttpConnectionParams.getSoTimeout(this.params));
        establishRoute(localHttpRoute, paramHttpContext);
      }
      catch (IOException localIOException1)
      {
        try
        {
          this.managedConn.close();
        }
        catch (IOException localIOException2)
        {
        }
        if (this.retryHandler.retryRequest(localIOException1, i, paramHttpContext))
        {
          if (this.log.isInfoEnabled())
          {
            this.log.info("I/O exception (" + localIOException1.getClass().getName() + ") caught when connecting to the target host: " + localIOException1.getMessage());
            if (this.log.isDebugEnabled())
              this.log.debug(localIOException1.getMessage(), localIOException1);
            this.log.info("Retrying connect");
          }
        }
        else
          throw localIOException1;
      }
    }
  }

  private HttpResponse tryExecute(RoutedRequest paramRoutedRequest, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    RequestWrapper localRequestWrapper = paramRoutedRequest.getRequest();
    HttpRoute localHttpRoute = paramRoutedRequest.getRoute();
    HttpResponse localHttpResponse = null;
    Object localObject = null;
    while (true)
    {
      this.execCount += 1;
      localRequestWrapper.incrementExecCount();
      if (!localRequestWrapper.isRepeatable())
      {
        this.log.debug("Cannot retry non-repeatable request");
        if (localObject != null)
          throw new NonRepeatableRequestException("Cannot retry request with a non-repeatable request entity.  The cause lists the reason the original request failed.", (Throwable)localObject);
        throw new NonRepeatableRequestException("Cannot retry request with a non-repeatable request entity.");
      }
      try
      {
        if (!this.managedConn.isOpen())
          if (!localHttpRoute.isTunnelled())
          {
            this.log.debug("Reopening the direct connection.");
            this.managedConn.open(localHttpRoute, paramHttpContext, this.params);
          }
          else
          {
            this.log.debug("Proxied connection. Need to start over.");
            break;
          }
        if (this.log.isDebugEnabled())
          this.log.debug("Attempt " + this.execCount + " to execute request");
        localHttpResponse = this.requestExec.execute(localRequestWrapper, this.managedConn, paramHttpContext);
      }
      catch (IOException localIOException1)
      {
        this.log.debug("Closing the connection.");
        try
        {
          this.managedConn.close();
        }
        catch (IOException localIOException2)
        {
        }
        if (this.retryHandler.retryRequest(localIOException1, localRequestWrapper.getExecCount(), paramHttpContext))
        {
          if (this.log.isInfoEnabled())
            this.log.info("I/O exception (" + localIOException1.getClass().getName() + ") caught when processing request: " + localIOException1.getMessage());
          if (this.log.isDebugEnabled())
            this.log.debug(localIOException1.getMessage(), localIOException1);
          this.log.info("Retrying request");
          localObject = localIOException1;
        }
        else
        {
          throw localIOException1;
        }
      }
    }
    return localHttpResponse;
  }

  protected void releaseConnection()
  {
    try
    {
      this.managedConn.releaseConnection();
    }
    catch (IOException localIOException)
    {
      this.log.debug("IOException releasing connection", localIOException);
    }
    this.managedConn = null;
  }

  protected HttpRoute determineRoute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException
  {
    if (paramHttpHost == null)
      paramHttpHost = (HttpHost)paramHttpRequest.getParams().getParameter("http.default-host");
    if (paramHttpHost == null)
      throw new IllegalStateException("Target host must not be null, or set in parameters.");
    return this.routePlanner.determineRoute(paramHttpHost, paramHttpRequest, paramHttpContext);
  }

  protected void establishRoute(HttpRoute paramHttpRoute, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    BasicRouteDirector localBasicRouteDirector = new BasicRouteDirector();
    int i;
    do
    {
      HttpRoute localHttpRoute = this.managedConn.getRoute();
      i = localBasicRouteDirector.nextStep(paramHttpRoute, localHttpRoute);
      switch (i)
      {
      case 1:
      case 2:
        this.managedConn.open(paramHttpRoute, paramHttpContext, this.params);
        break;
      case 3:
        boolean bool1 = createTunnelToTarget(paramHttpRoute, paramHttpContext);
        this.log.debug("Tunnel to target created.");
        this.managedConn.tunnelTarget(bool1, this.params);
        break;
      case 4:
        int j = localHttpRoute.getHopCount() - 1;
        boolean bool2 = createTunnelToProxy(paramHttpRoute, j, paramHttpContext);
        this.log.debug("Tunnel to proxy created.");
        this.managedConn.tunnelProxy(paramHttpRoute.getHopTarget(j), bool2, this.params);
        break;
      case 5:
        this.managedConn.layerProtocol(paramHttpContext, this.params);
        break;
      case -1:
        throw new HttpException("Unable to establish route: planned = " + paramHttpRoute + "; current = " + localHttpRoute);
      case 0:
        break;
      default:
        throw new IllegalStateException("Unknown step indicator " + i + " from RouteDirector.");
      }
    }
    while (i > 0);
  }

  protected boolean createTunnelToTarget(HttpRoute paramHttpRoute, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    HttpHost localHttpHost1 = paramHttpRoute.getProxyHost();
    HttpHost localHttpHost2 = paramHttpRoute.getTargetHost();
    HttpResponse localHttpResponse = null;
    while (true)
    {
      if (!this.managedConn.isOpen())
        this.managedConn.open(paramHttpRoute, paramHttpContext, this.params);
      HttpRequest localHttpRequest = createConnectRequest(paramHttpRoute, paramHttpContext);
      localHttpRequest.setParams(this.params);
      paramHttpContext.setAttribute("http.target_host", localHttpHost2);
      paramHttpContext.setAttribute("http.proxy_host", localHttpHost1);
      paramHttpContext.setAttribute("http.connection", this.managedConn);
      paramHttpContext.setAttribute("http.request", localHttpRequest);
      this.requestExec.preProcess(localHttpRequest, this.httpProcessor, paramHttpContext);
      localHttpResponse = this.requestExec.execute(localHttpRequest, this.managedConn, paramHttpContext);
      localHttpResponse.setParams(this.params);
      this.requestExec.postProcess(localHttpResponse, this.httpProcessor, paramHttpContext);
      int j = localHttpResponse.getStatusLine().getStatusCode();
      if (j < 200)
        throw new HttpException("Unexpected response to CONNECT request: " + localHttpResponse.getStatusLine());
      if (HttpClientParams.isAuthenticating(this.params))
      {
        if ((!this.authenticator.isAuthenticationRequested(localHttpHost1, localHttpResponse, this.proxyAuthStrategy, this.proxyAuthState, paramHttpContext)) || (!this.authenticator.authenticate(localHttpHost1, localHttpResponse, this.proxyAuthStrategy, this.proxyAuthState, paramHttpContext)))
          break;
        if (this.reuseStrategy.keepAlive(localHttpResponse, paramHttpContext))
        {
          this.log.debug("Connection kept alive");
          HttpEntity localHttpEntity2 = localHttpResponse.getEntity();
          EntityUtils.consume(localHttpEntity2);
        }
        else
        {
          this.managedConn.close();
        }
      }
    }
    int i = localHttpResponse.getStatusLine().getStatusCode();
    if (i > 299)
    {
      HttpEntity localHttpEntity1 = localHttpResponse.getEntity();
      if (localHttpEntity1 != null)
        localHttpResponse.setEntity(new BufferedHttpEntity(localHttpEntity1));
      this.managedConn.close();
      throw new TunnelRefusedException("CONNECT refused by proxy: " + localHttpResponse.getStatusLine(), localHttpResponse);
    }
    this.managedConn.markReusable();
    return false;
  }

  protected boolean createTunnelToProxy(HttpRoute paramHttpRoute, int paramInt, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    throw new HttpException("Proxy chains are not supported.");
  }

  protected HttpRequest createConnectRequest(HttpRoute paramHttpRoute, HttpContext paramHttpContext)
  {
    HttpHost localHttpHost = paramHttpRoute.getTargetHost();
    String str1 = localHttpHost.getHostName();
    int i = localHttpHost.getPort();
    if (i < 0)
    {
      localObject = this.connManager.getSchemeRegistry().getScheme(localHttpHost.getSchemeName());
      i = ((Scheme)localObject).getDefaultPort();
    }
    Object localObject = new StringBuilder(str1.length() + 6);
    ((StringBuilder)localObject).append(str1);
    ((StringBuilder)localObject).append(':');
    ((StringBuilder)localObject).append(Integer.toString(i));
    String str2 = ((StringBuilder)localObject).toString();
    ProtocolVersion localProtocolVersion = HttpProtocolParams.getVersion(this.params);
    BasicHttpRequest localBasicHttpRequest = new BasicHttpRequest("CONNECT", str2, localProtocolVersion);
    return localBasicHttpRequest;
  }

  protected RoutedRequest handleResponse(RoutedRequest paramRoutedRequest, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    HttpRoute localHttpRoute1 = paramRoutedRequest.getRoute();
    RequestWrapper localRequestWrapper = paramRoutedRequest.getRequest();
    HttpParams localHttpParams = localRequestWrapper.getParams();
    Object localObject1;
    Object localObject2;
    if ((HttpClientParams.isRedirecting(localHttpParams)) && (this.redirectStrategy.isRedirected(localRequestWrapper, paramHttpResponse, paramHttpContext)))
    {
      if (this.redirectCount >= this.maxRedirects)
        throw new RedirectException("Maximum redirects (" + this.maxRedirects + ") exceeded");
      this.redirectCount += 1;
      this.virtualHost = null;
      localObject1 = this.redirectStrategy.getRedirect(localRequestWrapper, paramHttpResponse, paramHttpContext);
      localObject2 = localRequestWrapper.getOriginal();
      ((HttpUriRequest)localObject1).setHeaders(((HttpRequest)localObject2).getAllHeaders());
      URI localURI = ((HttpUriRequest)localObject1).getURI();
      if (localURI.getHost() == null)
        throw new ProtocolException("Redirect URI does not specify a valid host name: " + localURI);
      HttpHost localHttpHost = new HttpHost(localURI.getHost(), localURI.getPort(), localURI.getScheme());
      if (!localHttpRoute1.getTargetHost().equals(localHttpHost))
      {
        this.log.debug("Resetting target auth state");
        this.targetAuthState.reset();
        localObject3 = this.proxyAuthState.getAuthScheme();
        if ((localObject3 != null) && (((AuthScheme)localObject3).isConnectionBased()))
        {
          this.log.debug("Resetting proxy auth state");
          this.proxyAuthState.reset();
        }
      }
      Object localObject3 = wrapRequest((HttpRequest)localObject1);
      ((RequestWrapper)localObject3).setParams(localHttpParams);
      HttpRoute localHttpRoute2 = determineRoute(localHttpHost, (HttpRequest)localObject3, paramHttpContext);
      RoutedRequest localRoutedRequest = new RoutedRequest((RequestWrapper)localObject3, localHttpRoute2);
      if (this.log.isDebugEnabled())
        this.log.debug("Redirecting to '" + localURI + "' via " + localHttpRoute2);
      return localRoutedRequest;
    }
    if (HttpClientParams.isAuthenticating(localHttpParams))
    {
      localObject1 = (HttpHost)paramHttpContext.getAttribute("http.target_host");
      if (localObject1 == null)
        localObject1 = localHttpRoute1.getTargetHost();
      if (((HttpHost)localObject1).getPort() < 0)
      {
        localObject2 = this.connManager.getSchemeRegistry().getScheme((HttpHost)localObject1);
        localObject1 = new HttpHost(((HttpHost)localObject1).getHostName(), ((Scheme)localObject2).getDefaultPort(), ((HttpHost)localObject1).getSchemeName());
      }
      if (this.authenticator.isAuthenticationRequested((HttpHost)localObject1, paramHttpResponse, this.targetAuthStrategy, this.targetAuthState, paramHttpContext))
      {
        if (this.authenticator.authenticate((HttpHost)localObject1, paramHttpResponse, this.targetAuthStrategy, this.targetAuthState, paramHttpContext))
          return paramRoutedRequest;
        return null;
      }
      localObject2 = localHttpRoute1.getProxyHost();
      if (this.authenticator.isAuthenticationRequested((HttpHost)localObject2, paramHttpResponse, this.proxyAuthStrategy, this.proxyAuthState, paramHttpContext))
      {
        if (this.authenticator.authenticate((HttpHost)localObject2, paramHttpResponse, this.proxyAuthStrategy, this.proxyAuthState, paramHttpContext))
          return paramRoutedRequest;
        return null;
      }
    }
    return null;
  }

  private void abortConnection()
  {
    ManagedClientConnection localManagedClientConnection = this.managedConn;
    if (localManagedClientConnection != null)
    {
      this.managedConn = null;
      try
      {
        localManagedClientConnection.abortConnection();
      }
      catch (IOException localIOException1)
      {
        if (this.log.isDebugEnabled())
          this.log.debug(localIOException1.getMessage(), localIOException1);
      }
      try
      {
        localManagedClientConnection.releaseConnection();
      }
      catch (IOException localIOException2)
      {
        this.log.debug("Error releasing connection", localIOException2);
      }
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.client.DefaultRequestDirector
 * JD-Core Version:    0.6.2
 */