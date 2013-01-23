package org.apache.http.impl.client;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;

public class TunnelRefusedException extends HttpException
{
  private final HttpResponse response;

  public TunnelRefusedException(String paramString, HttpResponse paramHttpResponse)
  {
    super(paramString);
    this.response = paramHttpResponse;
  }

  public HttpResponse getResponse()
  {
    return this.response;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.client.TunnelRefusedException
 * JD-Core Version:    0.6.2
 */