package org.apache.http.client;

import java.net.URI;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.protocol.HttpContext;

@Deprecated
public abstract interface RedirectHandler
{
  public abstract boolean isRedirectRequested(HttpResponse paramHttpResponse, HttpContext paramHttpContext);

  public abstract URI getLocationURI(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws ProtocolException;
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.client.RedirectHandler
 * JD-Core Version:    0.6.2
 */