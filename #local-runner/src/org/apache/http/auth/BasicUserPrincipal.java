package org.apache.http.auth;

import java.io.Serializable;
import java.security.Principal;
import org.apache.http.util.LangUtils;

public final class BasicUserPrincipal
  implements Serializable, Principal
{
  private final String username;

  public BasicUserPrincipal(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("User name may not be null");
    this.username = paramString;
  }

  public String getName()
  {
    return this.username;
  }

  public int hashCode()
  {
    int i = 17;
    i = LangUtils.hashCode(i, this.username);
    return i;
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject)
      return true;
    if ((paramObject instanceof BasicUserPrincipal))
    {
      BasicUserPrincipal localBasicUserPrincipal = (BasicUserPrincipal)paramObject;
      if (LangUtils.equals(this.username, localBasicUserPrincipal.username))
        return true;
    }
    return false;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[principal: ");
    localStringBuilder.append(this.username);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.auth.BasicUserPrincipal
 * JD-Core Version:    0.6.2
 */