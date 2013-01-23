package org.apache.http.impl.client;

import java.security.Principal;
import javax.net.ssl.SSLSession;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.Credentials;
import org.apache.http.client.UserTokenHandler;
import org.apache.http.conn.HttpRoutedConnection;
import org.apache.http.protocol.HttpContext;

public class DefaultUserTokenHandler
  implements UserTokenHandler
{
  public Object getUserToken(HttpContext paramHttpContext)
  {
    Principal localPrincipal = null;
    AuthState localAuthState = (AuthState)paramHttpContext.getAttribute("http.auth.target-scope");
    Object localObject;
    if (localAuthState != null)
    {
      localPrincipal = getAuthPrincipal(localAuthState);
      if (localPrincipal == null)
      {
        localObject = (AuthState)paramHttpContext.getAttribute("http.auth.proxy-scope");
        localPrincipal = getAuthPrincipal((AuthState)localObject);
      }
    }
    if (localPrincipal == null)
    {
      localObject = (HttpRoutedConnection)paramHttpContext.getAttribute("http.connection");
      if (((HttpRoutedConnection)localObject).isOpen())
      {
        SSLSession localSSLSession = ((HttpRoutedConnection)localObject).getSSLSession();
        if (localSSLSession != null)
          localPrincipal = localSSLSession.getLocalPrincipal();
      }
    }
    return localPrincipal;
  }

  private static Principal getAuthPrincipal(AuthState paramAuthState)
  {
    AuthScheme localAuthScheme = paramAuthState.getAuthScheme();
    if ((localAuthScheme != null) && (localAuthScheme.isComplete()) && (localAuthScheme.isConnectionBased()))
    {
      Credentials localCredentials = paramAuthState.getCredentials();
      if (localCredentials != null)
        return localCredentials.getUserPrincipal();
    }
    return null;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.client.DefaultUserTokenHandler
 * JD-Core Version:    0.6.2
 */