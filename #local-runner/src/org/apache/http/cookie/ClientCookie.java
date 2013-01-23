package org.apache.http.cookie;

public abstract interface ClientCookie extends Cookie
{
  public abstract String getAttribute(String paramString);

  public abstract boolean containsAttribute(String paramString);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.cookie.ClientCookie
 * JD-Core Version:    0.6.2
 */