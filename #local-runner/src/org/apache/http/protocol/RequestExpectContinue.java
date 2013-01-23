package org.apache.http.protocol;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;
import org.apache.http.params.HttpProtocolParams;

public class RequestExpectContinue
  implements HttpRequestInterceptor
{
  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if ((paramHttpRequest instanceof HttpEntityEnclosingRequest))
    {
      HttpEntity localHttpEntity = ((HttpEntityEnclosingRequest)paramHttpRequest).getEntity();
      if ((localHttpEntity != null) && (localHttpEntity.getContentLength() != 0L))
      {
        ProtocolVersion localProtocolVersion = paramHttpRequest.getRequestLine().getProtocolVersion();
        if ((HttpProtocolParams.useExpectContinue(paramHttpRequest.getParams())) && (!localProtocolVersion.lessEquals(HttpVersion.HTTP_1_0)))
          paramHttpRequest.addHeader("Expect", "100-continue");
      }
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.protocol.RequestExpectContinue
 * JD-Core Version:    0.6.2
 */