package org.apache.http.client.methods;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;
import org.apache.http.client.utils.CloneUtils;
import org.apache.http.conn.ClientConnectionRequest;
import org.apache.http.conn.ConnectionReleaseTrigger;
import org.apache.http.message.AbstractHttpMessage;
import org.apache.http.message.BasicRequestLine;
import org.apache.http.message.HeaderGroup;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

public abstract class HttpRequestBase extends AbstractHttpMessage
  implements Cloneable, AbortableHttpRequest, HttpUriRequest
{
  private Lock abortLock = new ReentrantLock();
  private volatile boolean aborted;
  private URI uri;
  private ClientConnectionRequest connRequest;
  private ConnectionReleaseTrigger releaseTrigger;

  public abstract String getMethod();

  public ProtocolVersion getProtocolVersion()
  {
    return HttpProtocolParams.getVersion(getParams());
  }

  public URI getURI()
  {
    return this.uri;
  }

  public RequestLine getRequestLine()
  {
    String str1 = getMethod();
    ProtocolVersion localProtocolVersion = getProtocolVersion();
    URI localURI = getURI();
    String str2 = null;
    if (localURI != null)
      str2 = localURI.toASCIIString();
    if ((str2 == null) || (str2.length() == 0))
      str2 = "/";
    return new BasicRequestLine(str1, str2, localProtocolVersion);
  }

  public void setURI(URI paramURI)
  {
    this.uri = paramURI;
  }

  public void setConnectionRequest(ClientConnectionRequest paramClientConnectionRequest)
    throws IOException
  {
    if (this.aborted)
      throw new IOException("Request already aborted");
    this.abortLock.lock();
    try
    {
      this.connRequest = paramClientConnectionRequest;
    }
    finally
    {
      this.abortLock.unlock();
    }
  }

  public void setReleaseTrigger(ConnectionReleaseTrigger paramConnectionReleaseTrigger)
    throws IOException
  {
    if (this.aborted)
      throw new IOException("Request already aborted");
    this.abortLock.lock();
    try
    {
      this.releaseTrigger = paramConnectionReleaseTrigger;
    }
    finally
    {
      this.abortLock.unlock();
    }
  }

  private void cleanup()
  {
    if (this.connRequest != null)
    {
      this.connRequest.abortRequest();
      this.connRequest = null;
    }
    if (this.releaseTrigger != null)
    {
      try
      {
        this.releaseTrigger.abortConnection();
      }
      catch (IOException localIOException)
      {
      }
      this.releaseTrigger = null;
    }
  }

  public void abort()
  {
    if (this.aborted)
      return;
    this.abortLock.lock();
    try
    {
      this.aborted = true;
      cleanup();
    }
    finally
    {
      this.abortLock.unlock();
    }
  }

  public boolean isAborted()
  {
    return this.aborted;
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    HttpRequestBase localHttpRequestBase = (HttpRequestBase)super.clone();
    localHttpRequestBase.abortLock = new ReentrantLock();
    localHttpRequestBase.aborted = false;
    localHttpRequestBase.releaseTrigger = null;
    localHttpRequestBase.connRequest = null;
    localHttpRequestBase.headergroup = ((HeaderGroup)CloneUtils.clone(this.headergroup));
    localHttpRequestBase.params = ((HttpParams)CloneUtils.clone(this.params));
    return localHttpRequestBase;
  }

  public String toString()
  {
    return getMethod() + " " + getURI() + " " + getProtocolVersion();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.client.methods.HttpRequestBase
 * JD-Core Version:    0.6.2
 */