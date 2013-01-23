package org.apache.http.conn;

import org.apache.http.HttpResponse;
import org.apache.http.protocol.HttpContext;

public abstract interface ConnectionKeepAliveStrategy
{
  public abstract long getKeepAliveDuration(HttpResponse paramHttpResponse, HttpContext paramHttpContext);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.conn.ConnectionKeepAliveStrategy
 * JD-Core Version:    0.6.2
 */