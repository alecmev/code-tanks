package org.apache.http.auth;

public final class AuthOption
{
  private final AuthScheme authScheme;
  private final Credentials creds;

  public AuthOption(AuthScheme paramAuthScheme, Credentials paramCredentials)
  {
    if (paramAuthScheme == null)
      throw new IllegalArgumentException("Auth scheme may not be null");
    if (paramCredentials == null)
      throw new IllegalArgumentException("User credentials may not be null");
    this.authScheme = paramAuthScheme;
    this.creds = paramCredentials;
  }

  public AuthScheme getAuthScheme()
  {
    return this.authScheme;
  }

  public Credentials getCredentials()
  {
    return this.creds;
  }

  public String toString()
  {
    return this.authScheme.toString();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.auth.AuthOption
 * JD-Core Version:    0.6.2
 */