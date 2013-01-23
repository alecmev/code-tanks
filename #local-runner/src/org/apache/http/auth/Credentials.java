package org.apache.http.auth;

import java.security.Principal;

public abstract interface Credentials
{
  public abstract Principal getUserPrincipal();

  public abstract String getPassword();
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.auth.Credentials
 * JD-Core Version:    0.6.2
 */