package org.apache.http.cookie;

import java.io.Serializable;
import java.util.Comparator;

public class CookieIdentityComparator
  implements Serializable, Comparator
{
  public int compare(Cookie paramCookie1, Cookie paramCookie2)
  {
    int i = paramCookie1.getName().compareTo(paramCookie2.getName());
    String str1;
    String str2;
    if (i == 0)
    {
      str1 = paramCookie1.getDomain();
      if (str1 == null)
        str1 = "";
      else if (str1.indexOf('.') == -1)
        str1 = str1 + ".local";
      str2 = paramCookie2.getDomain();
      if (str2 == null)
        str2 = "";
      else if (str2.indexOf('.') == -1)
        str2 = str2 + ".local";
      i = str1.compareToIgnoreCase(str2);
    }
    if (i == 0)
    {
      str1 = paramCookie1.getPath();
      if (str1 == null)
        str1 = "/";
      str2 = paramCookie2.getPath();
      if (str2 == null)
        str2 = "/";
      i = str1.compareTo(str2);
    }
    return i;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.cookie.CookieIdentityComparator
 * JD-Core Version:    0.6.2
 */