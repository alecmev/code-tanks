package org.apache.http.impl.cookie;

import java.util.Collections;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.MalformedCookieException;

public class IgnoreSpec extends CookieSpecBase
{
  public int getVersion()
  {
    return 0;
  }

  public List parse(Header paramHeader, CookieOrigin paramCookieOrigin)
    throws MalformedCookieException
  {
    return Collections.emptyList();
  }

  public List formatCookies(List paramList)
  {
    return Collections.emptyList();
  }

  public Header getVersionHeader()
  {
    return null;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.cookie.IgnoreSpec
 * JD-Core Version:    0.6.2
 */