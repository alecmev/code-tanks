package org.apache.http.auth;

import java.io.Serializable;
import java.security.Principal;
import org.apache.http.util.LangUtils;

public class NTCredentials
  implements Serializable, Credentials
{
  private final NTUserPrincipal principal;
  private final String password;
  private final String workstation;

  public Principal getUserPrincipal()
  {
    return this.principal;
  }

  public String getUserName()
  {
    return this.principal.getUsername();
  }

  public String getPassword()
  {
    return this.password;
  }

  public String getDomain()
  {
    return this.principal.getDomain();
  }

  public String getWorkstation()
  {
    return this.workstation;
  }

  public int hashCode()
  {
    int i = 17;
    i = LangUtils.hashCode(i, this.principal);
    i = LangUtils.hashCode(i, this.workstation);
    return i;
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject)
      return true;
    if ((paramObject instanceof NTCredentials))
    {
      NTCredentials localNTCredentials = (NTCredentials)paramObject;
      if ((LangUtils.equals(this.principal, localNTCredentials.principal)) && (LangUtils.equals(this.workstation, localNTCredentials.workstation)))
        return true;
    }
    return false;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[principal: ");
    localStringBuilder.append(this.principal);
    localStringBuilder.append("][workstation: ");
    localStringBuilder.append(this.workstation);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.auth.NTCredentials
 * JD-Core Version:    0.6.2
 */