package org.apache.http.impl.client;

import java.nio.charset.Charset;
import org.apache.http.HttpVersion;
import org.apache.http.client.protocol.RequestAddCookies;
import org.apache.http.client.protocol.RequestAuthCache;
import org.apache.http.client.protocol.RequestClientConnControl;
import org.apache.http.client.protocol.RequestDefaultHeaders;
import org.apache.http.client.protocol.RequestProxyAuthentication;
import org.apache.http.client.protocol.RequestTargetAuthentication;
import org.apache.http.client.protocol.ResponseProcessCookies;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.params.SyncBasicHttpParams;
import org.apache.http.protocol.BasicHttpProcessor;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.RequestContent;
import org.apache.http.protocol.RequestExpectContinue;
import org.apache.http.protocol.RequestTargetHost;
import org.apache.http.protocol.RequestUserAgent;
import org.apache.http.util.VersionInfo;

public class DefaultHttpClient extends AbstractHttpClient
{
  public DefaultHttpClient(ClientConnectionManager paramClientConnectionManager, HttpParams paramHttpParams)
  {
    super(paramClientConnectionManager, paramHttpParams);
  }

  public DefaultHttpClient()
  {
    super(null, null);
  }

  protected HttpParams createHttpParams()
  {
    SyncBasicHttpParams localSyncBasicHttpParams = new SyncBasicHttpParams();
    setDefaultHttpParams(localSyncBasicHttpParams);
    return localSyncBasicHttpParams;
  }

  public static void setDefaultHttpParams(HttpParams paramHttpParams)
  {
    HttpProtocolParams.setVersion(paramHttpParams, HttpVersion.HTTP_1_1);
    HttpProtocolParams.setContentCharset(paramHttpParams, HTTP.DEF_CONTENT_CHARSET.name());
    HttpConnectionParams.setTcpNoDelay(paramHttpParams, true);
    HttpConnectionParams.setSocketBufferSize(paramHttpParams, 8192);
    VersionInfo localVersionInfo = VersionInfo.loadVersionInfo("org.apache.http.client", DefaultHttpClient.class.getClassLoader());
    String str = localVersionInfo != null ? localVersionInfo.getRelease() : "UNAVAILABLE";
    HttpProtocolParams.setUserAgent(paramHttpParams, "Apache-HttpClient/" + str + " (java 1.5)");
  }

  protected BasicHttpProcessor createHttpProcessor()
  {
    BasicHttpProcessor localBasicHttpProcessor = new BasicHttpProcessor();
    localBasicHttpProcessor.addInterceptor(new RequestDefaultHeaders());
    localBasicHttpProcessor.addInterceptor(new RequestContent());
    localBasicHttpProcessor.addInterceptor(new RequestTargetHost());
    localBasicHttpProcessor.addInterceptor(new RequestClientConnControl());
    localBasicHttpProcessor.addInterceptor(new RequestUserAgent());
    localBasicHttpProcessor.addInterceptor(new RequestExpectContinue());
    localBasicHttpProcessor.addInterceptor(new RequestAddCookies());
    localBasicHttpProcessor.addInterceptor(new ResponseProcessCookies());
    localBasicHttpProcessor.addInterceptor(new RequestAuthCache());
    localBasicHttpProcessor.addInterceptor(new RequestTargetAuthentication());
    localBasicHttpProcessor.addInterceptor(new RequestProxyAuthentication());
    return localBasicHttpProcessor;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.client.DefaultHttpClient
 * JD-Core Version:    0.6.2
 */