package org.apache.http.client.protocol;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.protocol.HttpContext;

public class ResponseProcessCookies
  implements HttpResponseInterceptor
{
  private final Log log = LogFactory.getLog(getClass());

  public void process(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpResponse == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    CookieSpec localCookieSpec = (CookieSpec)paramHttpContext.getAttribute("http.cookie-spec");
    if (localCookieSpec == null)
    {
      this.log.debug("Cookie spec not specified in HTTP context");
      return;
    }
    CookieStore localCookieStore = (CookieStore)paramHttpContext.getAttribute("http.cookie-store");
    if (localCookieStore == null)
    {
      this.log.debug("Cookie store not specified in HTTP context");
      return;
    }
    CookieOrigin localCookieOrigin = (CookieOrigin)paramHttpContext.getAttribute("http.cookie-origin");
    if (localCookieOrigin == null)
    {
      this.log.debug("Cookie origin not specified in HTTP context");
      return;
    }
    HeaderIterator localHeaderIterator = paramHttpResponse.headerIterator("Set-Cookie");
    processCookies(localHeaderIterator, localCookieSpec, localCookieOrigin, localCookieStore);
    if (localCookieSpec.getVersion() > 0)
    {
      localHeaderIterator = paramHttpResponse.headerIterator("Set-Cookie2");
      processCookies(localHeaderIterator, localCookieSpec, localCookieOrigin, localCookieStore);
    }
  }

  private void processCookies(HeaderIterator paramHeaderIterator, CookieSpec paramCookieSpec, CookieOrigin paramCookieOrigin, CookieStore paramCookieStore)
  {
    while (paramHeaderIterator.hasNext())
    {
      Header localHeader = paramHeaderIterator.nextHeader();
      try
      {
        List localList = paramCookieSpec.parse(localHeader, paramCookieOrigin);
        Iterator localIterator = localList.iterator();
        while (localIterator.hasNext())
        {
          Cookie localCookie = (Cookie)localIterator.next();
          try
          {
            paramCookieSpec.validate(localCookie, paramCookieOrigin);
            paramCookieStore.addCookie(localCookie);
            if (this.log.isDebugEnabled())
              this.log.debug("Cookie accepted: \"" + localCookie + "\". ");
          }
          catch (MalformedCookieException localMalformedCookieException2)
          {
            if (this.log.isWarnEnabled())
              this.log.warn("Cookie rejected: \"" + localCookie + "\". " + localMalformedCookieException2.getMessage());
          }
        }
      }
      catch (MalformedCookieException localMalformedCookieException1)
      {
        if (this.log.isWarnEnabled())
          this.log.warn("Invalid cookie header: \"" + localHeader + "\". " + localMalformedCookieException1.getMessage());
      }
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.client.protocol.ResponseProcessCookies
 * JD-Core Version:    0.6.2
 */