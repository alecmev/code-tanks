package org.apache.http.impl.client;

import java.util.Locale;
import java.util.Map;
import java.util.Queue;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthProtocolState;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.client.AuthenticationStrategy;
import org.apache.http.protocol.HttpContext;

public class HttpAuthenticator
{
  private final Log log;

  public HttpAuthenticator(Log paramLog)
  {
    this.log = (paramLog != null ? paramLog : LogFactory.getLog(getClass()));
  }

  public HttpAuthenticator()
  {
    this(null);
  }

  public boolean isAuthenticationRequested(HttpHost paramHttpHost, HttpResponse paramHttpResponse, AuthenticationStrategy paramAuthenticationStrategy, AuthState paramAuthState, HttpContext paramHttpContext)
  {
    if (paramAuthenticationStrategy.isAuthenticationRequested(paramHttpHost, paramHttpResponse, paramHttpContext))
      return true;
    switch (1.$SwitchMap$org$apache$http$auth$AuthProtocolState[paramAuthState.getState().ordinal()])
    {
    case 1:
    case 2:
      paramAuthState.setState(AuthProtocolState.SUCCESS);
      paramAuthenticationStrategy.authSucceeded(paramHttpHost, paramAuthState.getAuthScheme(), paramHttpContext);
      break;
    case 3:
      break;
    default:
      paramAuthState.setState(AuthProtocolState.UNCHALLENGED);
    }
    return false;
  }

  public boolean authenticate(HttpHost paramHttpHost, HttpResponse paramHttpResponse, AuthenticationStrategy paramAuthenticationStrategy, AuthState paramAuthState, HttpContext paramHttpContext)
  {
    try
    {
      if (this.log.isDebugEnabled())
        this.log.debug(paramHttpHost.toHostString() + " requested authentication");
      Map localMap = paramAuthenticationStrategy.getChallenges(paramHttpHost, paramHttpResponse, paramHttpContext);
      if (localMap.isEmpty())
      {
        this.log.debug("Response contains no authentication challenges");
        return false;
      }
      AuthScheme localAuthScheme = paramAuthState.getAuthScheme();
      switch (1.$SwitchMap$org$apache$http$auth$AuthProtocolState[paramAuthState.getState().ordinal()])
      {
      case 4:
        return false;
      case 3:
        paramAuthState.reset();
        break;
      case 1:
      case 2:
        if (localAuthScheme == null)
        {
          this.log.debug("Auth scheme is null");
          paramAuthenticationStrategy.authFailed(paramHttpHost, null, paramHttpContext);
          paramAuthState.reset();
          paramAuthState.setState(AuthProtocolState.FAILURE);
          return false;
        }
      case 5:
        if (localAuthScheme != null)
        {
          localObject = localAuthScheme.getSchemeName();
          Header localHeader = (Header)localMap.get(((String)localObject).toLowerCase(Locale.US));
          if (localHeader != null)
          {
            this.log.debug("Authorization challenge processed");
            localAuthScheme.processChallenge(localHeader);
            if (localAuthScheme.isComplete())
            {
              this.log.debug("Authentication failed");
              paramAuthenticationStrategy.authFailed(paramHttpHost, paramAuthState.getAuthScheme(), paramHttpContext);
              paramAuthState.reset();
              paramAuthState.setState(AuthProtocolState.FAILURE);
              return false;
            }
            paramAuthState.setState(AuthProtocolState.HANDSHAKE);
            return true;
          }
          paramAuthState.reset();
        }
        break;
      }
      Object localObject = paramAuthenticationStrategy.select(localMap, paramHttpHost, paramHttpResponse, paramHttpContext);
      if ((localObject != null) && (!((Queue)localObject).isEmpty()))
      {
        if (this.log.isDebugEnabled())
          this.log.debug("Selected authentication options: " + localObject);
        paramAuthState.setState(AuthProtocolState.CHALLENGED);
        paramAuthState.update((Queue)localObject);
        return true;
      }
      return false;
    }
    catch (MalformedChallengeException localMalformedChallengeException)
    {
      if (this.log.isWarnEnabled())
        this.log.warn("Malformed challenge: " + localMalformedChallengeException.getMessage());
      paramAuthState.reset();
    }
    return false;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.client.HttpAuthenticator
 * JD-Core Version:    0.6.2
 */