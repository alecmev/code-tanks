package org.apache.http.impl.cookie;

import java.util.Collection;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.CookieSpecFactory;
import org.apache.http.params.HttpParams;

public class BrowserCompatSpecFactory
  implements CookieSpecFactory
{
  public CookieSpec newInstance(HttpParams paramHttpParams)
  {
    if (paramHttpParams != null)
    {
      String[] arrayOfString = null;
      Collection localCollection = (Collection)paramHttpParams.getParameter("http.protocol.cookie-datepatterns");
      if (localCollection != null)
      {
        arrayOfString = new String[localCollection.size()];
        arrayOfString = (String[])localCollection.toArray(arrayOfString);
      }
      return new BrowserCompatSpec(arrayOfString);
    }
    return new BrowserCompatSpec();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.cookie.BrowserCompatSpecFactory
 * JD-Core Version:    0.6.2
 */