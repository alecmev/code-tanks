package org.apache.http;

import java.io.IOException;
import org.apache.http.protocol.HttpContext;

public abstract interface HttpResponseInterceptor
{
  public abstract void process(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws HttpException, IOException;
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.HttpResponseInterceptor
 * JD-Core Version:    0.6.2
 */