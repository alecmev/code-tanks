package org.apache.http.auth.params;

import java.nio.charset.Charset;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;

public final class AuthParams
{
  public static String getCredentialCharset(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    String str = (String)paramHttpParams.getParameter("http.auth.credential-charset");
    if (str == null)
      str = HTTP.DEF_PROTOCOL_CHARSET.name();
    return str;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.auth.params.AuthParams
 * JD-Core Version:    0.6.2
 */