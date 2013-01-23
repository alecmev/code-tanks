package org.apache.http.conn.ssl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import org.apache.http.HttpHost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpInetSocketAddress;
import org.apache.http.conn.scheme.HostNameResolver;
import org.apache.http.conn.scheme.LayeredSchemeSocketFactory;
import org.apache.http.conn.scheme.SchemeLayeredSocketFactory;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class SSLSocketFactory
  implements LayeredSchemeSocketFactory, SchemeLayeredSocketFactory
{
  public static final X509HostnameVerifier ALLOW_ALL_HOSTNAME_VERIFIER = new AllowAllHostnameVerifier();
  public static final X509HostnameVerifier BROWSER_COMPATIBLE_HOSTNAME_VERIFIER = new BrowserCompatHostnameVerifier();
  public static final X509HostnameVerifier STRICT_HOSTNAME_VERIFIER = new StrictHostnameVerifier();
  private static final char[] EMPTY_PASSWORD = "".toCharArray();
  private final javax.net.ssl.SSLSocketFactory socketfactory;
  private final HostNameResolver nameResolver;
  private volatile X509HostnameVerifier hostnameVerifier;

  public static SSLSocketFactory getSocketFactory()
    throws SSLInitializationException
  {
    return new SSLSocketFactory(createDefaultSSLContext());
  }

  private static SSLContext createSSLContext(String paramString1, KeyStore paramKeyStore1, String paramString2, KeyStore paramKeyStore2, SecureRandom paramSecureRandom, TrustStrategy paramTrustStrategy)
    throws NoSuchAlgorithmException, KeyStoreException, UnrecoverableKeyException, KeyManagementException
  {
    if (paramString1 == null)
      paramString1 = "TLS";
    KeyManagerFactory localKeyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
    localKeyManagerFactory.init(paramKeyStore1, paramString2 != null ? paramString2.toCharArray() : null);
    KeyManager[] arrayOfKeyManager = localKeyManagerFactory.getKeyManagers();
    TrustManagerFactory localTrustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
    localTrustManagerFactory.init(paramKeyStore2);
    TrustManager[] arrayOfTrustManager = localTrustManagerFactory.getTrustManagers();
    if ((arrayOfTrustManager != null) && (paramTrustStrategy != null))
      for (int i = 0; i < arrayOfTrustManager.length; i++)
      {
        TrustManager localTrustManager = arrayOfTrustManager[i];
        if ((localTrustManager instanceof X509TrustManager))
          arrayOfTrustManager[i] = new TrustManagerDecorator((X509TrustManager)localTrustManager, paramTrustStrategy);
      }
    SSLContext localSSLContext = SSLContext.getInstance(paramString1);
    localSSLContext.init(arrayOfKeyManager, arrayOfTrustManager, paramSecureRandom);
    return localSSLContext;
  }

  private static SSLContext createDefaultSSLContext()
    throws SSLInitializationException
  {
    try
    {
      return createSSLContext("TLS", null, null, null, null, null);
    }
    catch (Exception localException)
    {
      throw new SSLInitializationException("Failure initializing default SSL context", localException);
    }
  }

  public SSLSocketFactory(SSLContext paramSSLContext)
  {
    this(paramSSLContext, BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
  }

  public SSLSocketFactory(SSLContext paramSSLContext, X509HostnameVerifier paramX509HostnameVerifier)
  {
    if (paramSSLContext == null)
      throw new IllegalArgumentException("SSL context may not be null");
    this.socketfactory = paramSSLContext.getSocketFactory();
    this.hostnameVerifier = paramX509HostnameVerifier;
    this.nameResolver = null;
  }

  public Socket createSocket(HttpParams paramHttpParams)
    throws IOException
  {
    SSLSocket localSSLSocket = (SSLSocket)this.socketfactory.createSocket();
    prepareSocket(localSSLSocket);
    return localSSLSocket;
  }

  public Socket connectSocket(Socket paramSocket, InetSocketAddress paramInetSocketAddress1, InetSocketAddress paramInetSocketAddress2, HttpParams paramHttpParams)
    throws IOException, UnknownHostException, ConnectTimeoutException
  {
    if (paramInetSocketAddress1 == null)
      throw new IllegalArgumentException("Remote address may not be null");
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    Socket localSocket = paramSocket != null ? paramSocket : this.socketfactory.createSocket();
    if (paramInetSocketAddress2 != null)
    {
      localSocket.setReuseAddress(HttpConnectionParams.getSoReuseaddr(paramHttpParams));
      localSocket.bind(paramInetSocketAddress2);
    }
    int i = HttpConnectionParams.getConnectionTimeout(paramHttpParams);
    int j = HttpConnectionParams.getSoTimeout(paramHttpParams);
    try
    {
      localSocket.setSoTimeout(j);
      localSocket.connect(paramInetSocketAddress1, i);
    }
    catch (SocketTimeoutException localSocketTimeoutException)
    {
      throw new ConnectTimeoutException("Connect to " + paramInetSocketAddress1 + " timed out");
    }
    String str;
    if ((paramInetSocketAddress1 instanceof HttpInetSocketAddress))
      str = ((HttpInetSocketAddress)paramInetSocketAddress1).getHttpHost().getHostName();
    else
      str = paramInetSocketAddress1.getHostName();
    SSLSocket localSSLSocket;
    if ((localSocket instanceof SSLSocket))
    {
      localSSLSocket = (SSLSocket)localSocket;
    }
    else
    {
      int k = paramInetSocketAddress1.getPort();
      localSSLSocket = (SSLSocket)this.socketfactory.createSocket(localSocket, str, k, true);
      prepareSocket(localSSLSocket);
    }
    if (this.hostnameVerifier != null)
      try
      {
        this.hostnameVerifier.verify(str, localSSLSocket);
      }
      catch (IOException localIOException)
      {
        try
        {
          localSSLSocket.close();
        }
        catch (Exception localException)
        {
        }
        throw localIOException;
      }
    return localSSLSocket;
  }

  public boolean isSecure(Socket paramSocket)
    throws IllegalArgumentException
  {
    if (paramSocket == null)
      throw new IllegalArgumentException("Socket may not be null");
    if (!(paramSocket instanceof SSLSocket))
      throw new IllegalArgumentException("Socket not created by this factory");
    if (paramSocket.isClosed())
      throw new IllegalArgumentException("Socket is closed");
    return true;
  }

  public Socket createLayeredSocket(Socket paramSocket, String paramString, int paramInt, HttpParams paramHttpParams)
    throws IOException, UnknownHostException
  {
    SSLSocket localSSLSocket = (SSLSocket)this.socketfactory.createSocket(paramSocket, paramString, paramInt, true);
    prepareSocket(localSSLSocket);
    if (this.hostnameVerifier != null)
      this.hostnameVerifier.verify(paramString, localSSLSocket);
    return localSSLSocket;
  }

  public Socket createLayeredSocket(Socket paramSocket, String paramString, int paramInt, boolean paramBoolean)
    throws IOException, UnknownHostException
  {
    SSLSocket localSSLSocket = (SSLSocket)this.socketfactory.createSocket(paramSocket, paramString, paramInt, paramBoolean);
    prepareSocket(localSSLSocket);
    if (this.hostnameVerifier != null)
      this.hostnameVerifier.verify(paramString, localSSLSocket);
    return localSSLSocket;
  }

  protected void prepareSocket(SSLSocket paramSSLSocket)
    throws IOException
  {
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.conn.ssl.SSLSocketFactory
 * JD-Core Version:    0.6.2
 */