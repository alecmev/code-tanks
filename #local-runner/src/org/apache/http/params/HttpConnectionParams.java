package org.apache.http.params;

public final class HttpConnectionParams
{
  public static int getSoTimeout(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    return paramHttpParams.getIntParameter("http.socket.timeout", 0);
  }

  public static boolean getSoReuseaddr(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    return paramHttpParams.getBooleanParameter("http.socket.reuseaddr", false);
  }

  public static boolean getTcpNoDelay(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    return paramHttpParams.getBooleanParameter("http.tcp.nodelay", true);
  }

  public static void setTcpNoDelay(HttpParams paramHttpParams, boolean paramBoolean)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    paramHttpParams.setBooleanParameter("http.tcp.nodelay", paramBoolean);
  }

  public static int getSocketBufferSize(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    return paramHttpParams.getIntParameter("http.socket.buffer-size", -1);
  }

  public static void setSocketBufferSize(HttpParams paramHttpParams, int paramInt)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    paramHttpParams.setIntParameter("http.socket.buffer-size", paramInt);
  }

  public static int getLinger(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    return paramHttpParams.getIntParameter("http.socket.linger", -1);
  }

  public static int getConnectionTimeout(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    return paramHttpParams.getIntParameter("http.connection.timeout", 0);
  }

  public static boolean isStaleCheckingEnabled(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    return paramHttpParams.getBooleanParameter("http.connection.stalecheck", true);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.params.HttpConnectionParams
 * JD-Core Version:    0.6.2
 */