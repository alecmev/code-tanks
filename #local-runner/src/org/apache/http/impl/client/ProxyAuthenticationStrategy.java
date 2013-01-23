package org.apache.http.impl.client;

public class ProxyAuthenticationStrategy extends AuthenticationStrategyImpl
{
  public ProxyAuthenticationStrategy()
  {
    super(407, "Proxy-Authenticate", "http.auth.proxy-scheme-pref");
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.client.ProxyAuthenticationStrategy
 * JD-Core Version:    0.6.2
 */