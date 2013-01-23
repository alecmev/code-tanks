package org.apache.http.protocol;

public abstract interface HttpContext
{
  public abstract Object getAttribute(String paramString);

  public abstract void setAttribute(String paramString, Object paramObject);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.protocol.HttpContext
 * JD-Core Version:    0.6.2
 */