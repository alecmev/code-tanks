package org.apache.http.impl.client;

import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.HttpRequest;
import org.apache.http.ProtocolException;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.AbstractHttpMessage;
import org.apache.http.message.BasicRequestLine;
import org.apache.http.message.HeaderGroup;
import org.apache.http.params.HttpProtocolParams;

public class RequestWrapper extends AbstractHttpMessage
  implements HttpUriRequest
{
  private final HttpRequest original;
  private URI uri;
  private String method;
  private ProtocolVersion version;
  private int execCount;

  public RequestWrapper(HttpRequest paramHttpRequest)
    throws ProtocolException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    this.original = paramHttpRequest;
    setParams(paramHttpRequest.getParams());
    setHeaders(paramHttpRequest.getAllHeaders());
    if ((paramHttpRequest instanceof HttpUriRequest))
    {
      this.uri = ((HttpUriRequest)paramHttpRequest).getURI();
      this.method = ((HttpUriRequest)paramHttpRequest).getMethod();
      this.version = null;
    }
    else
    {
      RequestLine localRequestLine = paramHttpRequest.getRequestLine();
      try
      {
        this.uri = new URI(localRequestLine.getUri());
      }
      catch (URISyntaxException localURISyntaxException)
      {
        throw new ProtocolException("Invalid request URI: " + localRequestLine.getUri(), localURISyntaxException);
      }
      this.method = localRequestLine.getMethod();
      this.version = paramHttpRequest.getProtocolVersion();
    }
    this.execCount = 0;
  }

  public void resetHeaders()
  {
    this.headergroup.clear();
    setHeaders(this.original.getAllHeaders());
  }

  public String getMethod()
  {
    return this.method;
  }

  public ProtocolVersion getProtocolVersion()
  {
    if (this.version == null)
      this.version = HttpProtocolParams.getVersion(getParams());
    return this.version;
  }

  public URI getURI()
  {
    return this.uri;
  }

  public void setURI(URI paramURI)
  {
    this.uri = paramURI;
  }

  public RequestLine getRequestLine()
  {
    String str1 = getMethod();
    ProtocolVersion localProtocolVersion = getProtocolVersion();
    String str2 = null;
    if (this.uri != null)
      str2 = this.uri.toASCIIString();
    if ((str2 == null) || (str2.length() == 0))
      str2 = "/";
    return new BasicRequestLine(str1, str2, localProtocolVersion);
  }

  public boolean isAborted()
  {
    return false;
  }

  public HttpRequest getOriginal()
  {
    return this.original;
  }

  public boolean isRepeatable()
  {
    return true;
  }

  public int getExecCount()
  {
    return this.execCount;
  }

  public void incrementExecCount()
  {
    this.execCount += 1;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.client.RequestWrapper
 * JD-Core Version:    0.6.2
 */