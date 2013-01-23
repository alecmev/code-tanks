package org.apache.http.client.protocol;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.RequestLine;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

public class RequestDefaultHeaders
  implements HttpRequestInterceptor
{
  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    String str = paramHttpRequest.getRequestLine().getMethod();
    if (str.equalsIgnoreCase("CONNECT"))
      return;
    Collection localCollection = (Collection)paramHttpRequest.getParams().getParameter("http.default-headers");
    if (localCollection != null)
    {
      Iterator localIterator = localCollection.iterator();
      while (localIterator.hasNext())
      {
        Header localHeader = (Header)localIterator.next();
        paramHttpRequest.addHeader(localHeader);
      }
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.client.protocol.RequestDefaultHeaders
 * JD-Core Version:    0.6.2
 */