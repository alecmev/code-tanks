package org.apache.http.impl.cookie;

import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.CookieSpecFactory;
import org.apache.http.params.HttpParams;

public class IgnoreSpecFactory
  implements CookieSpecFactory
{
  public CookieSpec newInstance(HttpParams paramHttpParams)
  {
    return new IgnoreSpec();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.cookie.IgnoreSpecFactory
 * JD-Core Version:    0.6.2
 */