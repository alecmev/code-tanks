package org.apache.http.client.protocol;

import java.io.IOException;
import java.util.Queue;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.auth.AuthOption;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.ContextAwareAuthScheme;
import org.apache.http.auth.Credentials;
import org.apache.http.protocol.HttpContext;

abstract class RequestAuthenticationBase
  implements HttpRequestInterceptor
{
  final Log log = LogFactory.getLog(getClass());

  void process(AuthState paramAuthState, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    AuthScheme localAuthScheme = paramAuthState.getAuthScheme();
    Credentials localCredentials = paramAuthState.getCredentials();
    Object localObject;
    switch (1.$SwitchMap$org$apache$http$auth$AuthProtocolState[paramAuthState.getState().ordinal()])
    {
    case 1:
      return;
    case 2:
      ensureAuthScheme(localAuthScheme);
      if (localAuthScheme.isConnectionBased())
        return;
      break;
    case 3:
      localObject = paramAuthState.getAuthOptions();
      if (localObject != null)
      {
        while (!((Queue)localObject).isEmpty())
        {
          AuthOption localAuthOption = (AuthOption)((Queue)localObject).remove();
          localAuthScheme = localAuthOption.getAuthScheme();
          localCredentials = localAuthOption.getCredentials();
          paramAuthState.update(localAuthScheme, localCredentials);
          if (this.log.isDebugEnabled())
            this.log.debug("Generating response to an authentication challenge using " + localAuthScheme.getSchemeName() + " scheme");
          try
          {
            Header localHeader = authenticate(localAuthScheme, localCredentials, paramHttpRequest, paramHttpContext);
            paramHttpRequest.addHeader(localHeader);
          }
          catch (AuthenticationException localAuthenticationException2)
          {
            if (this.log.isWarnEnabled())
              this.log.warn(localAuthScheme + " authentication error: " + localAuthenticationException2.getMessage());
          }
        }
        return;
      }
      ensureAuthScheme(localAuthScheme);
    }
    if (localAuthScheme != null)
      try
      {
        localObject = authenticate(localAuthScheme, localCredentials, paramHttpRequest, paramHttpContext);
        paramHttpRequest.addHeader((Header)localObject);
      }
      catch (AuthenticationException localAuthenticationException1)
      {
        if (this.log.isErrorEnabled())
          this.log.error(localAuthScheme + " authentication error: " + localAuthenticationException1.getMessage());
      }
  }

  private void ensureAuthScheme(AuthScheme paramAuthScheme)
  {
    if (paramAuthScheme == null)
      throw new IllegalStateException("Auth scheme is not set");
  }

  private Header authenticate(AuthScheme paramAuthScheme, Credentials paramCredentials, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws AuthenticationException
  {
    if (paramAuthScheme == null)
      throw new IllegalStateException("Auth state object is null");
    if ((paramAuthScheme instanceof ContextAwareAuthScheme))
      return ((ContextAwareAuthScheme)paramAuthScheme).authenticate(paramCredentials, paramHttpRequest, paramHttpContext);
    return paramAuthScheme.authenticate(paramCredentials, paramHttpRequest);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.client.protocol.RequestAuthenticationBase
 * JD-Core Version:    0.6.2
 */