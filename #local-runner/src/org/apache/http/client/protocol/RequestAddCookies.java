package org.apache.http.client.protocol;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.ProtocolException;
import org.apache.http.RequestLine;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.HttpRoutedConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.CookieSpecRegistry;
import org.apache.http.cookie.SetCookie2;
import org.apache.http.protocol.HttpContext;

public class RequestAddCookies
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
    String str1 = paramHttpRequest.getRequestLine().getMethod();
    if (str1.equalsIgnoreCase("CONNECT"))
      return;
    CookieStore localCookieStore = (CookieStore)paramHttpContext.getAttribute("http.cookie-store");
    if (localCookieStore == null)
    {
      this.log.debug("Cookie store not specified in HTTP context");
      return;
    }
    CookieSpecRegistry localCookieSpecRegistry = (CookieSpecRegistry)paramHttpContext.getAttribute("http.cookiespec-registry");
    if (localCookieSpecRegistry == null)
    {
      this.log.debug("CookieSpec registry not specified in HTTP context");
      return;
    }
    HttpHost localHttpHost = (HttpHost)paramHttpContext.getAttribute("http.target_host");
    if (localHttpHost == null)
    {
      this.log.debug("Target host not set in the context");
      return;
    }
    HttpRoutedConnection localHttpRoutedConnection = (HttpRoutedConnection)paramHttpContext.getAttribute("http.connection");
    if (localHttpRoutedConnection == null)
    {
      this.log.debug("HTTP connection not set in the context");
      return;
    }
    String str2 = HttpClientParams.getCookiePolicy(paramHttpRequest.getParams());
    if (this.log.isDebugEnabled())
      this.log.debug("CookieSpec selected: " + str2);
    URI localURI;
    if ((paramHttpRequest instanceof HttpUriRequest))
      localURI = ((HttpUriRequest)paramHttpRequest).getURI();
    else
      try
      {
        localURI = new URI(paramHttpRequest.getRequestLine().getUri());
      }
      catch (URISyntaxException localURISyntaxException)
      {
        throw new ProtocolException("Invalid request URI: " + paramHttpRequest.getRequestLine().getUri(), localURISyntaxException);
      }
    String str3 = localHttpHost.getHostName();
    int i = localHttpHost.getPort();
    if (i < 0)
    {
      localObject1 = localHttpRoutedConnection.getRoute();
      if (((HttpRoute)localObject1).getHopCount() == 1)
      {
        i = localHttpRoutedConnection.getRemotePort();
      }
      else
      {
        localObject2 = localHttpHost.getSchemeName();
        if (((String)localObject2).equalsIgnoreCase("http"))
          i = 80;
        else if (((String)localObject2).equalsIgnoreCase("https"))
          i = 443;
        else
          i = 0;
      }
    }
    Object localObject1 = new CookieOrigin(str3, i, localURI.getPath(), localHttpRoutedConnection.isSecure());
    Object localObject2 = localCookieSpecRegistry.getCookieSpec(str2, paramHttpRequest.getParams());
    ArrayList localArrayList1 = new ArrayList(localCookieStore.getCookies());
    ArrayList localArrayList2 = new ArrayList();
    Date localDate = new Date();
    Object localObject3 = localArrayList1.iterator();
    Object localObject4;
    while (((Iterator)localObject3).hasNext())
    {
      localObject4 = (Cookie)((Iterator)localObject3).next();
      if (!((Cookie)localObject4).isExpired(localDate))
      {
        if (((CookieSpec)localObject2).match((Cookie)localObject4, (CookieOrigin)localObject1))
        {
          if (this.log.isDebugEnabled())
            this.log.debug("Cookie " + localObject4 + " match " + localObject1);
          localArrayList2.add(localObject4);
        }
      }
      else if (this.log.isDebugEnabled())
        this.log.debug("Cookie " + localObject4 + " expired");
    }
    Object localObject5;
    if (!localArrayList2.isEmpty())
    {
      localObject3 = ((CookieSpec)localObject2).formatCookies(localArrayList2);
      localObject4 = ((List)localObject3).iterator();
      while (((Iterator)localObject4).hasNext())
      {
        localObject5 = (Header)((Iterator)localObject4).next();
        paramHttpRequest.addHeader((Header)localObject5);
      }
    }
    int j = ((CookieSpec)localObject2).getVersion();
    if (j > 0)
    {
      int k = 0;
      localObject5 = localArrayList2.iterator();
      while (((Iterator)localObject5).hasNext())
      {
        Cookie localCookie = (Cookie)((Iterator)localObject5).next();
        if ((j != localCookie.getVersion()) || (!(localCookie instanceof SetCookie2)))
          k = 1;
      }
      if (k != 0)
      {
        localObject5 = ((CookieSpec)localObject2).getVersionHeader();
        if (localObject5 != null)
          paramHttpRequest.addHeader((Header)localObject5);
      }
    }
    paramHttpContext.setAttribute("http.cookie-spec", localObject2);
    paramHttpContext.setAttribute("http.cookie-origin", localObject1);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.client.protocol.RequestAddCookies
 * JD-Core Version:    0.6.2
 */