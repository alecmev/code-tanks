package org.apache.http;

import org.apache.http.protocol.HttpContext;

public abstract interface HttpResponseFactory
{
  public abstract HttpResponse newHttpResponse(StatusLine paramStatusLine, HttpContext paramHttpContext);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.HttpResponseFactory
 * JD-Core Version:    0.6.2
 */