package org.apache.http.cookie;

import java.io.Serializable;
import java.util.Comparator;

public class CookiePathComparator
  implements Serializable, Comparator
{
  private String normalizePath(Cookie paramCookie)
  {
    String str = paramCookie.getPath();
    if (str == null)
      str = "/";
    if (!str.endsWith("/"))
      str = str + '/';
    return str;
  }

  public int compare(Cookie paramCookie1, Cookie paramCookie2)
  {
    String str1 = normalizePath(paramCookie1);
    String str2 = normalizePath(paramCookie2);
    if (str1.equals(str2))
      return 0;
    if (str1.startsWith(str2))
      return -1;
    if (str2.startsWith(str1))
      return 1;
    return 0;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.cookie.CookiePathComparator
 * JD-Core Version:    0.6.2
 */