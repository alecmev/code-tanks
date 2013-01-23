package org.apache.http.client;

import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.protocol.HttpContext;

@Deprecated
public abstract interface AuthenticationHandler
{
  public abstract boolean isAuthenticationRequested(HttpResponse paramHttpResponse, HttpContext paramHttpContext);

  public abstract Map getChallenges(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws MalformedChallengeException;

  public abstract AuthScheme selectScheme(Map paramMap, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws AuthenticationException;
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.client.AuthenticationHandler
 * JD-Core Version:    0.6.2
 */