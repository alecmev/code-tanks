package org.apache.http.client;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScheme;

public abstract interface AuthCache
{
  public abstract void put(HttpHost paramHttpHost, AuthScheme paramAuthScheme);

  public abstract AuthScheme get(HttpHost paramHttpHost);

  public abstract void remove(HttpHost paramHttpHost);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.client.AuthCache
 * JD-Core Version:    0.6.2
 */