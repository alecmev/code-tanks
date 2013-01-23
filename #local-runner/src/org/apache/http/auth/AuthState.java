package org.apache.http.auth;

import java.util.Queue;

public class AuthState
{
  private AuthProtocolState state = AuthProtocolState.UNCHALLENGED;
  private AuthScheme authScheme;
  private AuthScope authScope;
  private Credentials credentials;
  private Queue authOptions;

  public void reset()
  {
    this.state = AuthProtocolState.UNCHALLENGED;
    this.authOptions = null;
    this.authScheme = null;
    this.authScope = null;
    this.credentials = null;
  }

  public AuthProtocolState getState()
  {
    return this.state;
  }

  public void setState(AuthProtocolState paramAuthProtocolState)
  {
    this.state = (paramAuthProtocolState != null ? paramAuthProtocolState : AuthProtocolState.UNCHALLENGED);
  }

  public AuthScheme getAuthScheme()
  {
    return this.authScheme;
  }

  public Credentials getCredentials()
  {
    return this.credentials;
  }

  public void update(AuthScheme paramAuthScheme, Credentials paramCredentials)
  {
    if (paramAuthScheme == null)
      throw new IllegalArgumentException("Auth scheme may not be null or empty");
    if (paramCredentials == null)
      throw new IllegalArgumentException("Credentials may not be null or empty");
    this.authScheme = paramAuthScheme;
    this.credentials = paramCredentials;
    this.authOptions = null;
  }

  public Queue getAuthOptions()
  {
    return this.authOptions;
  }

  public void update(Queue paramQueue)
  {
    if ((paramQueue == null) || (paramQueue.isEmpty()))
      throw new IllegalArgumentException("Queue of auth options may not be null or empty");
    this.authOptions = paramQueue;
    this.authScheme = null;
    this.credentials = null;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("state:").append(this.state).append(";");
    if (this.authScheme != null)
      localStringBuilder.append("auth scheme:").append(this.authScheme.getSchemeName()).append(";");
    if (this.credentials != null)
      localStringBuilder.append("credentials present");
    return localStringBuilder.toString();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.auth.AuthState
 * JD-Core Version:    0.6.2
 */