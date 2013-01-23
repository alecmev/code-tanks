package org.apache.http.impl.client;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RedirectLocations
{
  private final Set unique = new HashSet();
  private final List all = new ArrayList();

  public boolean contains(URI paramURI)
  {
    return this.unique.contains(paramURI);
  }

  public void add(URI paramURI)
  {
    this.unique.add(paramURI);
    this.all.add(paramURI);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.client.RedirectLocations
 * JD-Core Version:    0.6.2
 */