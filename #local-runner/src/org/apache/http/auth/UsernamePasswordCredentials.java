package org.apache.http.auth;

import java.io.Serializable;
import java.security.Principal;
import org.apache.http.util.LangUtils;

public class UsernamePasswordCredentials
  implements Serializable, Credentials
{
  private final BasicUserPrincipal principal;
  private final String password;

  public UsernamePasswordCredentials(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Username:password string may not be null");
    int i = paramString.indexOf(':');
    if (i >= 0)
    {
      this.principal = new BasicUserPrincipal(paramString.substring(0, i));
      this.password = paramString.substring(i + 1);
    }
    else
    {
      this.principal = new BasicUserPrincipal(paramString);
      this.password = null;
    }
  }

  public Principal getUserPrincipal()
  {
    return this.principal;
  }

  public String getPassword()
  {
    return this.password;
  }

  public int hashCode()
  {
    return this.principal.hashCode();
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject)
      return true;
    if ((paramObject instanceof UsernamePasswordCredentials))
    {
      UsernamePasswordCredentials localUsernamePasswordCredentials = (UsernamePasswordCredentials)paramObject;
      if (LangUtils.equals(this.principal, localUsernamePasswordCredentials.principal))
        return true;
    }
    return false;
  }

  public String toString()
  {
    return this.principal.toString();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.auth.UsernamePasswordCredentials
 * JD-Core Version:    0.6.2
 */