package org.apache.http.protocol;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolException;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;

public class RequestContent
  implements HttpRequestInterceptor
{
  private final boolean overwrite;

  public RequestContent()
  {
    this(false);
  }

  public RequestContent(boolean paramBoolean)
  {
    this.overwrite = paramBoolean;
  }

  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if ((paramHttpRequest instanceof HttpEntityEnclosingRequest))
    {
      if (this.overwrite)
      {
        paramHttpRequest.removeHeaders("Transfer-Encoding");
        paramHttpRequest.removeHeaders("Content-Length");
      }
      else
      {
        if (paramHttpRequest.containsHeader("Transfer-Encoding"))
          throw new ProtocolException("Transfer-encoding header already present");
        if (paramHttpRequest.containsHeader("Content-Length"))
          throw new ProtocolException("Content-Length header already present");
      }
      ProtocolVersion localProtocolVersion = paramHttpRequest.getRequestLine().getProtocolVersion();
      HttpEntity localHttpEntity = ((HttpEntityEnclosingRequest)paramHttpRequest).getEntity();
      if (localHttpEntity == null)
      {
        paramHttpRequest.addHeader("Content-Length", "0");
        return;
      }
      if ((localHttpEntity.isChunked()) || (localHttpEntity.getContentLength() < 0L))
      {
        if (localProtocolVersion.lessEquals(HttpVersion.HTTP_1_0))
          throw new ProtocolException("Chunked transfer encoding not allowed for " + localProtocolVersion);
        paramHttpRequest.addHeader("Transfer-Encoding", "chunked");
      }
      else
      {
        paramHttpRequest.addHeader("Content-Length", Long.toString(localHttpEntity.getContentLength()));
      }
      if ((localHttpEntity.getContentType() != null) && (!paramHttpRequest.containsHeader("Content-Type")))
        paramHttpRequest.addHeader(localHttpEntity.getContentType());
      if ((localHttpEntity.getContentEncoding() != null) && (!paramHttpRequest.containsHeader("Content-Encoding")))
        paramHttpRequest.addHeader(localHttpEntity.getContentEncoding());
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.protocol.RequestContent
 * JD-Core Version:    0.6.2
 */