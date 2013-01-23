package org.apache.http.impl.cookie;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.cookie.CookieAttributeHandler;
import org.apache.http.cookie.CookieSpec;

public abstract class AbstractCookieSpec
  implements CookieSpec
{
  private final Map attribHandlerMap = new HashMap(10);

  public void registerAttribHandler(String paramString, CookieAttributeHandler paramCookieAttributeHandler)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Attribute name may not be null");
    if (paramCookieAttributeHandler == null)
      throw new IllegalArgumentException("Attribute handler may not be null");
    this.attribHandlerMap.put(paramString, paramCookieAttributeHandler);
  }

  protected CookieAttributeHandler findAttribHandler(String paramString)
  {
    return (CookieAttributeHandler)this.attribHandlerMap.get(paramString);
  }

  protected Collection getAttribHandlers()
  {
    return this.attribHandlerMap.values();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.cookie.AbstractCookieSpec
 * JD-Core Version:    0.6.2
 */