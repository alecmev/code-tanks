package org.apache.http.params;

import java.nio.charset.Charset;
import java.nio.charset.CodingErrorAction;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolVersion;
import org.apache.http.protocol.HTTP;

public final class HttpProtocolParams
{
  public static String getHttpElementCharset(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    String str = (String)paramHttpParams.getParameter("http.protocol.element-charset");
    if (str == null)
      str = HTTP.DEF_PROTOCOL_CHARSET.name();
    return str;
  }

  public static void setContentCharset(HttpParams paramHttpParams, String paramString)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    paramHttpParams.setParameter("http.protocol.content-charset", paramString);
  }

  public static ProtocolVersion getVersion(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    Object localObject = paramHttpParams.getParameter("http.protocol.version");
    if (localObject == null)
      return HttpVersion.HTTP_1_1;
    return (ProtocolVersion)localObject;
  }

  public static void setVersion(HttpParams paramHttpParams, ProtocolVersion paramProtocolVersion)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    paramHttpParams.setParameter("http.protocol.version", paramProtocolVersion);
  }

  public static String getUserAgent(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    return (String)paramHttpParams.getParameter("http.useragent");
  }

  public static void setUserAgent(HttpParams paramHttpParams, String paramString)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    paramHttpParams.setParameter("http.useragent", paramString);
  }

  public static boolean useExpectContinue(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    return paramHttpParams.getBooleanParameter("http.protocol.expect-continue", false);
  }

  public static CodingErrorAction getMalformedInputAction(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    Object localObject = paramHttpParams.getParameter("http.malformed.input.action");
    if (localObject == null)
      return CodingErrorAction.REPORT;
    return (CodingErrorAction)localObject;
  }

  public static CodingErrorAction getUnmappableInputAction(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    Object localObject = paramHttpParams.getParameter("http.unmappable.input.action");
    if (localObject == null)
      return CodingErrorAction.REPORT;
    return (CodingErrorAction)localObject;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.params.HttpProtocolParams
 * JD-Core Version:    0.6.2
 */