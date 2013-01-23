package org.apache.http.impl.client;

import org.apache.http.conn.routing.HttpRoute;

public class RoutedRequest
{
  protected final RequestWrapper request;
  protected final HttpRoute route;

  public RoutedRequest(RequestWrapper paramRequestWrapper, HttpRoute paramHttpRoute)
  {
    this.request = paramRequestWrapper;
    this.route = paramHttpRoute;
  }

  public final RequestWrapper getRequest()
  {
    return this.request;
  }

  public final HttpRoute getRoute()
  {
    return this.route;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.client.RoutedRequest
 * JD-Core Version:    0.6.2
 */