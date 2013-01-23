package org.apache.http.client.protocol;

import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.auth.AuthState;
import org.apache.http.conn.HttpRoutedConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.protocol.HttpContext;

public class RequestProxyAuthentication extends RequestAuthenticationBase
{
  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    if (paramHttpRequest.containsHeader("Proxy-Authorization"))
      return;
    HttpRoutedConnection localHttpRoutedConnection = (HttpRoutedConnection)paramHttpContext.getAttribute("http.connection");
    if (localHttpRoutedConnection == null)
    {
      this.log.debug("HTTP connection not set in the context");
      return;
    }
    HttpRoute localHttpRoute = localHttpRoutedConnection.getRoute();
    if (localHttpRoute.isTunnelled())
      return;
    AuthState localAuthState = (AuthState)paramHttpContext.getAttribute("http.auth.proxy-scope");
    if (localAuthState == null)
    {
      this.log.debug("Proxy auth state not set in the context");
      return;
    }
    if (this.log.isDebugEnabled())
      this.log.debug("Proxy auth state: " + localAuthState.getState());
    process(localAuthState, paramHttpRequest, paramHttpContext);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.client.protocol.RequestProxyAuthentication
 * JD-Core Version:    0.6.2
 */