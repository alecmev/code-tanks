package org.apache.http;

import org.apache.http.protocol.HttpContext;

public abstract interface ConnectionReuseStrategy
{
  public abstract boolean keepAlive(HttpResponse paramHttpResponse, HttpContext paramHttpContext);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.ConnectionReuseStrategy
 * JD-Core Version:    0.6.2
 */