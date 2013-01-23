package org.apache.http.impl.client;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import javax.net.ssl.SSLException;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.protocol.HttpContext;

public class DefaultHttpRequestRetryHandler
  implements HttpRequestRetryHandler
{
  private final int retryCount;
  private final boolean requestSentRetryEnabled;

  public DefaultHttpRequestRetryHandler(int paramInt, boolean paramBoolean)
  {
    this.retryCount = paramInt;
    this.requestSentRetryEnabled = paramBoolean;
  }

  public DefaultHttpRequestRetryHandler()
  {
    this(3, false);
  }

  public boolean retryRequest(IOException paramIOException, int paramInt, HttpContext paramHttpContext)
  {
    if (paramIOException == null)
      throw new IllegalArgumentException("Exception parameter may not be null");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    if (paramInt > this.retryCount)
      return false;
    if ((paramIOException instanceof InterruptedIOException))
      return false;
    if ((paramIOException instanceof UnknownHostException))
      return false;
    if ((paramIOException instanceof ConnectException))
      return false;
    if ((paramIOException instanceof SSLException))
      return false;
    HttpRequest localHttpRequest = (HttpRequest)paramHttpContext.getAttribute("http.request");
    if (requestIsAborted(localHttpRequest))
      return false;
    if (handleAsIdempotent(localHttpRequest))
      return true;
    Boolean localBoolean = (Boolean)paramHttpContext.getAttribute("http.request_sent");
    int i = (localBoolean != null) && (localBoolean.booleanValue()) ? 1 : 0;
    return (i == 0) || (this.requestSentRetryEnabled);
  }

  protected boolean handleAsIdempotent(HttpRequest paramHttpRequest)
  {
    return !(paramHttpRequest instanceof HttpEntityEnclosingRequest);
  }

  protected boolean requestIsAborted(HttpRequest paramHttpRequest)
  {
    HttpRequest localHttpRequest = paramHttpRequest;
    if ((paramHttpRequest instanceof RequestWrapper))
      localHttpRequest = ((RequestWrapper)paramHttpRequest).getOriginal();
    return ((localHttpRequest instanceof HttpUriRequest)) && (((HttpUriRequest)localHttpRequest).isAborted());
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.client.DefaultHttpRequestRetryHandler
 * JD-Core Version:    0.6.2
 */