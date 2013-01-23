package org.apache.http.protocol;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;

public final class BasicHttpProcessor
  implements Cloneable, HttpProcessor
{
  protected final List requestInterceptors = new ArrayList();
  protected final List responseInterceptors = new ArrayList();

  public void addRequestInterceptor(HttpRequestInterceptor paramHttpRequestInterceptor)
  {
    if (paramHttpRequestInterceptor == null)
      return;
    this.requestInterceptors.add(paramHttpRequestInterceptor);
  }

  public final void addInterceptor(HttpRequestInterceptor paramHttpRequestInterceptor)
  {
    addRequestInterceptor(paramHttpRequestInterceptor);
  }

  public int getRequestInterceptorCount()
  {
    return this.requestInterceptors.size();
  }

  public HttpRequestInterceptor getRequestInterceptor(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= this.requestInterceptors.size()))
      return null;
    return (HttpRequestInterceptor)this.requestInterceptors.get(paramInt);
  }

  public void addResponseInterceptor(HttpResponseInterceptor paramHttpResponseInterceptor)
  {
    if (paramHttpResponseInterceptor == null)
      return;
    this.responseInterceptors.add(paramHttpResponseInterceptor);
  }

  public final void addInterceptor(HttpResponseInterceptor paramHttpResponseInterceptor)
  {
    addResponseInterceptor(paramHttpResponseInterceptor);
  }

  public int getResponseInterceptorCount()
  {
    return this.responseInterceptors.size();
  }

  public HttpResponseInterceptor getResponseInterceptor(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= this.responseInterceptors.size()))
      return null;
    return (HttpResponseInterceptor)this.responseInterceptors.get(paramInt);
  }

  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws IOException, HttpException
  {
    for (int i = 0; i < this.requestInterceptors.size(); i++)
    {
      HttpRequestInterceptor localHttpRequestInterceptor = (HttpRequestInterceptor)this.requestInterceptors.get(i);
      localHttpRequestInterceptor.process(paramHttpRequest, paramHttpContext);
    }
  }

  public void process(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws IOException, HttpException
  {
    for (int i = 0; i < this.responseInterceptors.size(); i++)
    {
      HttpResponseInterceptor localHttpResponseInterceptor = (HttpResponseInterceptor)this.responseInterceptors.get(i);
      localHttpResponseInterceptor.process(paramHttpResponse, paramHttpContext);
    }
  }

  protected void copyInterceptors(BasicHttpProcessor paramBasicHttpProcessor)
  {
    paramBasicHttpProcessor.requestInterceptors.clear();
    paramBasicHttpProcessor.requestInterceptors.addAll(this.requestInterceptors);
    paramBasicHttpProcessor.responseInterceptors.clear();
    paramBasicHttpProcessor.responseInterceptors.addAll(this.responseInterceptors);
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    BasicHttpProcessor localBasicHttpProcessor = (BasicHttpProcessor)super.clone();
    copyInterceptors(localBasicHttpProcessor);
    return localBasicHttpProcessor;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.protocol.BasicHttpProcessor
 * JD-Core Version:    0.6.2
 */