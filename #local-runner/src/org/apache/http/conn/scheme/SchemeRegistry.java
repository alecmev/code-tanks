package org.apache.http.conn.scheme;

import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.HttpHost;

public final class SchemeRegistry
{
  private final ConcurrentHashMap registeredSchemes = new ConcurrentHashMap();

  public final Scheme getScheme(String paramString)
  {
    Scheme localScheme = get(paramString);
    if (localScheme == null)
      throw new IllegalStateException("Scheme '" + paramString + "' not registered.");
    return localScheme;
  }

  public final Scheme getScheme(HttpHost paramHttpHost)
  {
    if (paramHttpHost == null)
      throw new IllegalArgumentException("Host must not be null.");
    return getScheme(paramHttpHost.getSchemeName());
  }

  public final Scheme get(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Name must not be null.");
    Scheme localScheme = (Scheme)this.registeredSchemes.get(paramString);
    return localScheme;
  }

  public final Scheme register(Scheme paramScheme)
  {
    if (paramScheme == null)
      throw new IllegalArgumentException("Scheme must not be null.");
    Scheme localScheme = (Scheme)this.registeredSchemes.put(paramScheme.getName(), paramScheme);
    return localScheme;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.conn.scheme.SchemeRegistry
 * JD-Core Version:    0.6.2
 */