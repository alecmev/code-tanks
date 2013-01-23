package org.apache.http.client.params;

import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class HttpClientParams
{
  public static boolean isRedirecting(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    return paramHttpParams.getBooleanParameter("http.protocol.handle-redirects", true);
  }

  public static boolean isAuthenticating(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    return paramHttpParams.getBooleanParameter("http.protocol.handle-authentication", true);
  }

  public static String getCookiePolicy(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    String str = (String)paramHttpParams.getParameter("http.protocol.cookie-policy");
    if (str == null)
      return "best-match";
    return str;
  }

  public static long getConnectionManagerTimeout(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    Long localLong = (Long)paramHttpParams.getParameter("http.conn-manager.timeout");
    if (localLong != null)
      return localLong.longValue();
    return HttpConnectionParams.getConnectionTimeout(paramHttpParams);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.client.params.HttpClientParams
 * JD-Core Version:    0.6.2
 */