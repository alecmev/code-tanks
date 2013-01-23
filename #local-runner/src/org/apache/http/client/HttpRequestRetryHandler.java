package org.apache.http.client;

import java.io.IOException;
import org.apache.http.protocol.HttpContext;

public abstract interface HttpRequestRetryHandler
{
  public abstract boolean retryRequest(IOException paramIOException, int paramInt, HttpContext paramHttpContext);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.client.HttpRequestRetryHandler
 * JD-Core Version:    0.6.2
 */