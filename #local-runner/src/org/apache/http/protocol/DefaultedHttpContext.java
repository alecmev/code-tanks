package org.apache.http.protocol;

public final class DefaultedHttpContext
  implements HttpContext
{
  private final HttpContext local;
  private final HttpContext defaults;

  public DefaultedHttpContext(HttpContext paramHttpContext1, HttpContext paramHttpContext2)
  {
    if (paramHttpContext1 == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    this.local = paramHttpContext1;
    this.defaults = paramHttpContext2;
  }

  public Object getAttribute(String paramString)
  {
    Object localObject = this.local.getAttribute(paramString);
    if (localObject == null)
      return this.defaults.getAttribute(paramString);
    return localObject;
  }

  public void setAttribute(String paramString, Object paramObject)
  {
    this.local.setAttribute(paramString, paramObject);
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[local: ").append(this.local);
    localStringBuilder.append("defaults: ").append(this.defaults);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.protocol.DefaultedHttpContext
 * JD-Core Version:    0.6.2
 */