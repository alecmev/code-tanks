package org.apache.http.client;

import org.apache.http.protocol.HttpContext;

public abstract interface UserTokenHandler
{
  public abstract Object getUserToken(HttpContext paramHttpContext);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.client.UserTokenHandler
 * JD-Core Version:    0.6.2
 */