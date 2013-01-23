package org.apache.http.impl.conn;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseFactory;
import org.apache.http.conn.OperatedClientConnection;
import org.apache.http.impl.SocketHttpClientConnection;
import org.apache.http.io.HttpMessageParser;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.io.SessionOutputBuffer;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HttpContext;

public class DefaultClientConnection extends SocketHttpClientConnection
  implements OperatedClientConnection, HttpContext
{
  private final Log log = LogFactory.getLog(getClass());
  private final Log headerLog = LogFactory.getLog("org.apache.http.headers");
  private final Log wireLog = LogFactory.getLog("org.apache.http.wire");
  private volatile Socket socket;
  private HttpHost targetHost;
  private boolean connSecure;
  private volatile boolean shutdown;
  private final Map attributes = new HashMap();

  public final boolean isSecure()
  {
    return this.connSecure;
  }

  public final Socket getSocket()
  {
    return this.socket;
  }

  public void opening(Socket paramSocket, HttpHost paramHttpHost)
    throws IOException
  {
    assertNotOpen();
    this.socket = paramSocket;
    this.targetHost = paramHttpHost;
    if (this.shutdown)
    {
      paramSocket.close();
      throw new InterruptedIOException("Connection already shutdown");
    }
  }

  public void openCompleted(boolean paramBoolean, HttpParams paramHttpParams)
    throws IOException
  {
    assertNotOpen();
    if (paramHttpParams == null)
      throw new IllegalArgumentException("Parameters must not be null.");
    this.connSecure = paramBoolean;
    bind(this.socket, paramHttpParams);
  }

  public void shutdown()
    throws IOException
  {
    this.shutdown = true;
    try
    {
      super.shutdown();
      if (this.log.isDebugEnabled())
        this.log.debug("Connection " + this + " shut down");
      Socket localSocket = this.socket;
      if (localSocket != null)
        localSocket.close();
    }
    catch (IOException localIOException)
    {
      this.log.debug("I/O error shutting down connection", localIOException);
    }
  }

  public void close()
    throws IOException
  {
    try
    {
      super.close();
      if (this.log.isDebugEnabled())
        this.log.debug("Connection " + this + " closed");
    }
    catch (IOException localIOException)
    {
      this.log.debug("I/O error closing connection", localIOException);
    }
  }

  protected SessionInputBuffer createSessionInputBuffer(Socket paramSocket, int paramInt, HttpParams paramHttpParams)
    throws IOException
  {
    if (paramInt == -1)
      paramInt = 8192;
    Object localObject = super.createSessionInputBuffer(paramSocket, paramInt, paramHttpParams);
    if (this.wireLog.isDebugEnabled())
      localObject = new LoggingSessionInputBuffer((SessionInputBuffer)localObject, new Wire(this.wireLog), HttpProtocolParams.getHttpElementCharset(paramHttpParams));
    return localObject;
  }

  protected SessionOutputBuffer createSessionOutputBuffer(Socket paramSocket, int paramInt, HttpParams paramHttpParams)
    throws IOException
  {
    if (paramInt == -1)
      paramInt = 8192;
    Object localObject = super.createSessionOutputBuffer(paramSocket, paramInt, paramHttpParams);
    if (this.wireLog.isDebugEnabled())
      localObject = new LoggingSessionOutputBuffer((SessionOutputBuffer)localObject, new Wire(this.wireLog), HttpProtocolParams.getHttpElementCharset(paramHttpParams));
    return localObject;
  }

  protected HttpMessageParser createResponseParser(SessionInputBuffer paramSessionInputBuffer, HttpResponseFactory paramHttpResponseFactory, HttpParams paramHttpParams)
  {
    return new DefaultHttpResponseParser(paramSessionInputBuffer, null, paramHttpResponseFactory, paramHttpParams);
  }

  public void update(Socket paramSocket, HttpHost paramHttpHost, boolean paramBoolean, HttpParams paramHttpParams)
    throws IOException
  {
    assertOpen();
    if (paramHttpHost == null)
      throw new IllegalArgumentException("Target host must not be null.");
    if (paramHttpParams == null)
      throw new IllegalArgumentException("Parameters must not be null.");
    if (paramSocket != null)
    {
      this.socket = paramSocket;
      bind(paramSocket, paramHttpParams);
    }
    this.targetHost = paramHttpHost;
    this.connSecure = paramBoolean;
  }

  public HttpResponse receiveResponseHeader()
    throws HttpException, IOException
  {
    HttpResponse localHttpResponse = super.receiveResponseHeader();
    if (this.log.isDebugEnabled())
      this.log.debug("Receiving response: " + localHttpResponse.getStatusLine());
    if (this.headerLog.isDebugEnabled())
    {
      this.headerLog.debug("<< " + localHttpResponse.getStatusLine().toString());
      Header[] arrayOfHeader1 = localHttpResponse.getAllHeaders();
      for (Header localHeader : arrayOfHeader1)
        this.headerLog.debug("<< " + localHeader.toString());
    }
    return localHttpResponse;
  }

  public void sendRequestHeader(HttpRequest paramHttpRequest)
    throws HttpException, IOException
  {
    if (this.log.isDebugEnabled())
      this.log.debug("Sending request: " + paramHttpRequest.getRequestLine());
    super.sendRequestHeader(paramHttpRequest);
    if (this.headerLog.isDebugEnabled())
    {
      this.headerLog.debug(">> " + paramHttpRequest.getRequestLine().toString());
      Header[] arrayOfHeader1 = paramHttpRequest.getAllHeaders();
      for (Header localHeader : arrayOfHeader1)
        this.headerLog.debug(">> " + localHeader.toString());
    }
  }

  public Object getAttribute(String paramString)
  {
    return this.attributes.get(paramString);
  }

  public void setAttribute(String paramString, Object paramObject)
  {
    this.attributes.put(paramString, paramObject);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.conn.DefaultClientConnection
 * JD-Core Version:    0.6.2
 */