package org.apache.http.protocol;

import java.util.HashMap;
import java.util.Map;

public class BasicHttpContext
  implements HttpContext
{
  private final HttpContext parentContext;
  private Map map = null;

  public BasicHttpContext()
  {
    this(null);
  }

  public BasicHttpContext(HttpContext paramHttpContext)
  {
    this.parentContext = paramHttpContext;
  }

  public Object getAttribute(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Id may not be null");
    Object localObject = null;
    if (this.map != null)
      localObject = this.map.get(paramString);
    if ((localObject == null) && (this.parentContext != null))
      localObject = this.parentContext.getAttribute(paramString);
    return localObject;
  }

  public void setAttribute(String paramString, Object paramObject)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Id may not be null");
    if (this.map == null)
      this.map = new HashMap();
    this.map.put(paramString, paramObject);
  }

  public String toString()
  {
    if (this.map != null)
      return this.map.toString();
    return "{}";
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.protocol.BasicHttpContext
 * JD-Core Version:    0.6.2
 */