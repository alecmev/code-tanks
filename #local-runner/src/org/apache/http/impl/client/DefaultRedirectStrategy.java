package org.apache.http.impl.client;

import java.net.URI;
import java.net.URISyntaxException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.RequestLine;
import org.apache.http.StatusLine;
import org.apache.http.client.CircularRedirectException;
import org.apache.http.client.RedirectStrategy;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

public class DefaultRedirectStrategy
  implements RedirectStrategy
{
  private final Log log = LogFactory.getLog(getClass());
  private static final String[] REDIRECT_METHODS = { "GET", "HEAD" };

  public boolean isRedirected(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws ProtocolException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if (paramHttpResponse == null)
      throw new IllegalArgumentException("HTTP response may not be null");
    int i = paramHttpResponse.getStatusLine().getStatusCode();
    String str = paramHttpRequest.getRequestLine().getMethod();
    Header localHeader = paramHttpResponse.getFirstHeader("location");
    switch (i)
    {
    case 302:
      return (isRedirectable(str)) && (localHeader != null);
    case 301:
    case 307:
      return isRedirectable(str);
    case 303:
      return true;
    case 304:
    case 305:
    case 306:
    }
    return false;
  }

  public URI getLocationURI(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws ProtocolException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if (paramHttpResponse == null)
      throw new IllegalArgumentException("HTTP response may not be null");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    Header localHeader = paramHttpResponse.getFirstHeader("location");
    if (localHeader == null)
      throw new ProtocolException("Received redirect response " + paramHttpResponse.getStatusLine() + " but no location header");
    String str = localHeader.getValue();
    if (this.log.isDebugEnabled())
      this.log.debug("Redirect requested to location '" + str + "'");
    URI localURI1 = createLocationURI(str);
    HttpParams localHttpParams = paramHttpRequest.getParams();
    try
    {
      localURI1 = URIUtils.rewriteURI(localURI1);
      if (!localURI1.isAbsolute())
      {
        if (localHttpParams.isParameterTrue("http.protocol.reject-relative-redirect"))
          throw new ProtocolException("Relative redirect location '" + localURI1 + "' not allowed");
        HttpHost localHttpHost = (HttpHost)paramHttpContext.getAttribute("http.target_host");
        if (localHttpHost == null)
          throw new IllegalStateException("Target host not available in the HTTP context");
        URI localURI2 = new URI(paramHttpRequest.getRequestLine().getUri());
        URI localURI3 = URIUtils.rewriteURI(localURI2, localHttpHost, true);
        localURI1 = URIUtils.resolve(localURI3, localURI1);
      }
    }
    catch (URISyntaxException localURISyntaxException)
    {
      throw new ProtocolException(localURISyntaxException.getMessage(), localURISyntaxException);
    }
    RedirectLocations localRedirectLocations = (RedirectLocations)paramHttpContext.getAttribute("http.protocol.redirect-locations");
    if (localRedirectLocations == null)
    {
      localRedirectLocations = new RedirectLocations();
      paramHttpContext.setAttribute("http.protocol.redirect-locations", localRedirectLocations);
    }
    if ((localHttpParams.isParameterFalse("http.protocol.allow-circular-redirects")) && (localRedirectLocations.contains(localURI1)))
      throw new CircularRedirectException("Circular redirect to '" + localURI1 + "'");
    localRedirectLocations.add(localURI1);
    return localURI1;
  }

  protected URI createLocationURI(String paramString)
    throws ProtocolException
  {
    try
    {
      return new URI(paramString).normalize();
    }
    catch (URISyntaxException localURISyntaxException)
    {
      throw new ProtocolException("Invalid redirect URI: " + paramString, localURISyntaxException);
    }
  }

  protected boolean isRedirectable(String paramString)
  {
    for (String str : REDIRECT_METHODS)
      if (str.equalsIgnoreCase(paramString))
        return true;
    return false;
  }

  public HttpUriRequest getRedirect(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws ProtocolException
  {
    URI localURI = getLocationURI(paramHttpRequest, paramHttpResponse, paramHttpContext);
    String str = paramHttpRequest.getRequestLine().getMethod();
    if (str.equalsIgnoreCase("HEAD"))
      return new HttpHead(localURI);
    return new HttpGet(localURI);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.client.DefaultRedirectStrategy
 * JD-Core Version:    0.6.2
 */