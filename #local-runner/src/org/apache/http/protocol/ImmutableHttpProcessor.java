package org.apache.http.protocol;

import java.io.IOException;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;

public final class ImmutableHttpProcessor
  implements HttpProcessor
{
  private final HttpRequestInterceptor[] requestInterceptors;
  private final HttpResponseInterceptor[] responseInterceptors;

  public ImmutableHttpProcessor(HttpRequestInterceptor[] paramArrayOfHttpRequestInterceptor, HttpResponseInterceptor[] paramArrayOfHttpResponseInterceptor)
  {
    int i;
    int j;
    if (paramArrayOfHttpRequestInterceptor != null)
    {
      i = paramArrayOfHttpRequestInterceptor.length;
      this.requestInterceptors = new HttpRequestInterceptor[i];
      for (j = 0; j < i; j++)
        this.requestInterceptors[j] = paramArrayOfHttpRequestInterceptor[j];
    }
    else
    {
      this.requestInterceptors = new HttpRequestInterceptor[0];
    }
    if (paramArrayOfHttpResponseInterceptor != null)
    {
      i = paramArrayOfHttpResponseInterceptor.length;
      this.responseInterceptors = new HttpResponseInterceptor[i];
      for (j = 0; j < i; j++)
        this.responseInterceptors[j] = paramArrayOfHttpResponseInterceptor[j];
    }
    else
    {
      this.responseInterceptors = new HttpResponseInterceptor[0];
    }
  }

  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws IOException, HttpException
  {
    for (int i = 0; i < this.requestInterceptors.length; i++)
      this.requestInterceptors[i].process(paramHttpRequest, paramHttpContext);
  }

  public void process(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws IOException, HttpException
  {
    for (int i = 0; i < this.responseInterceptors.length; i++)
      this.responseInterceptors[i].process(paramHttpResponse, paramHttpContext);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.protocol.ImmutableHttpProcessor
 * JD-Core Version:    0.6.2
 */