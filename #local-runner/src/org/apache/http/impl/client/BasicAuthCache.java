package org.apache.http.impl.client;

import java.util.HashMap;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScheme;
import org.apache.http.client.AuthCache;

public class BasicAuthCache
  implements AuthCache
{
  private final HashMap map = new HashMap();

  public void put(HttpHost paramHttpHost, AuthScheme paramAuthScheme)
  {
    if (paramHttpHost == null)
      throw new IllegalArgumentException("HTTP host may not be null");
    this.map.put(paramHttpHost, paramAuthScheme);
  }

  public AuthScheme get(HttpHost paramHttpHost)
  {
    if (paramHttpHost == null)
      throw new IllegalArgumentException("HTTP host may not be null");
    return (AuthScheme)this.map.get(paramHttpHost);
  }

  public void remove(HttpHost paramHttpHost)
  {
    if (paramHttpHost == null)
      throw new IllegalArgumentException("HTTP host may not be null");
    this.map.remove(paramHttpHost);
  }

  public String toString()
  {
    return this.map.toString();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.client.BasicAuthCache
 * JD-Core Version:    0.6.2
 */