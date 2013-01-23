package org.apache.http.client.protocol;

import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.RequestLine;
import org.apache.http.auth.AuthState;
import org.apache.http.protocol.HttpContext;

public class RequestTargetAuthentication extends RequestAuthenticationBase
{
  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    String str = paramHttpRequest.getRequestLine().getMethod();
    if (str.equalsIgnoreCase("CONNECT"))
      return;
    if (paramHttpRequest.containsHeader("Authorization"))
      return;
    AuthState localAuthState = (AuthState)paramHttpContext.getAttribute("http.auth.target-scope");
    if (localAuthState == null)
    {
      this.log.debug("Target auth state not set in the context");
      return;
    }
    if (this.log.isDebugEnabled())
      this.log.debug("Target auth state: " + localAuthState.getState());
    process(localAuthState, paramHttpRequest, paramHttpContext);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.client.protocol.RequestTargetAuthentication
 * JD-Core Version:    0.6.2
 */