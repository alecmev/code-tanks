package org.apache.http.impl.cookie;

import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.cookie.SetCookie;

public class BasicCommentHandler extends AbstractCookieAttributeHandler
{
  public void parse(SetCookie paramSetCookie, String paramString)
    throws MalformedCookieException
  {
    if (paramSetCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    paramSetCookie.setComment(paramString);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.cookie.BasicCommentHandler
 * JD-Core Version:    0.6.2
 */