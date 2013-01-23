package org.apache.http.auth;

import java.io.Serializable;
import java.security.Principal;
import org.apache.http.util.LangUtils;

public class NTUserPrincipal
  implements Serializable, Principal
{
  private final String username;
  private final String domain;
  private final String ntname;

  public String getName()
  {
    return this.ntname;
  }

  public String getDomain()
  {
    return this.domain;
  }

  public String getUsername()
  {
    return this.username;
  }

  public int hashCode()
  {
    int i = 17;
    i = LangUtils.hashCode(i, this.username);
    i = LangUtils.hashCode(i, this.domain);
    return i;
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject)
      return true;
    if ((paramObject instanceof NTUserPrincipal))
    {
      NTUserPrincipal localNTUserPrincipal = (NTUserPrincipal)paramObject;
      if ((LangUtils.equals(this.username, localNTUserPrincipal.username)) && (LangUtils.equals(this.domain, localNTUserPrincipal.domain)))
        return true;
    }
    return false;
  }

  public String toString()
  {
    return this.ntname;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.auth.NTUserPrincipal
 * JD-Core Version:    0.6.2
 */