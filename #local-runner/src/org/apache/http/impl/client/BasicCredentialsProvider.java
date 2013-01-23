package org.apache.http.impl.client;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.client.CredentialsProvider;

public class BasicCredentialsProvider
  implements CredentialsProvider
{
  private final ConcurrentHashMap credMap = new ConcurrentHashMap();

  private static Credentials matchCredentials(Map paramMap, AuthScope paramAuthScope)
  {
    Credentials localCredentials = (Credentials)paramMap.get(paramAuthScope);
    if (localCredentials == null)
    {
      int i = -1;
      Object localObject = null;
      Iterator localIterator = paramMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        AuthScope localAuthScope = (AuthScope)localIterator.next();
        int j = paramAuthScope.match(localAuthScope);
        if (j > i)
        {
          i = j;
          localObject = localAuthScope;
        }
      }
      if (localObject != null)
        localCredentials = (Credentials)paramMap.get(localObject);
    }
    return localCredentials;
  }

  public Credentials getCredentials(AuthScope paramAuthScope)
  {
    if (paramAuthScope == null)
      throw new IllegalArgumentException("Authentication scope may not be null");
    return matchCredentials(this.credMap, paramAuthScope);
  }

  public String toString()
  {
    return this.credMap.toString();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.client.BasicCredentialsProvider
 * JD-Core Version:    0.6.2
 */