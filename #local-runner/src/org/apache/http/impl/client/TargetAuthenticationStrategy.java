package org.apache.http.impl.client;

public class TargetAuthenticationStrategy extends AuthenticationStrategyImpl
{
  public TargetAuthenticationStrategy()
  {
    super(401, "WWW-Authenticate", "http.auth.target-scheme-pref");
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.client.TargetAuthenticationStrategy
 * JD-Core Version:    0.6.2
 */