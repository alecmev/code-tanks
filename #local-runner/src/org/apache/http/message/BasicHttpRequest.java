package org.apache.http.message;

import org.apache.http.HttpRequest;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;
import org.apache.http.params.HttpProtocolParams;

public class BasicHttpRequest extends AbstractHttpMessage
  implements HttpRequest
{
  private final String method;
  private final String uri;
  private RequestLine requestline;

  public BasicHttpRequest(String paramString1, String paramString2, ProtocolVersion paramProtocolVersion)
  {
    this(new BasicRequestLine(paramString1, paramString2, paramProtocolVersion));
  }

  public BasicHttpRequest(RequestLine paramRequestLine)
  {
    if (paramRequestLine == null)
      throw new IllegalArgumentException("Request line may not be null");
    this.requestline = paramRequestLine;
    this.method = paramRequestLine.getMethod();
    this.uri = paramRequestLine.getUri();
  }

  public ProtocolVersion getProtocolVersion()
  {
    return getRequestLine().getProtocolVersion();
  }

  public RequestLine getRequestLine()
  {
    if (this.requestline == null)
    {
      ProtocolVersion localProtocolVersion = HttpProtocolParams.getVersion(getParams());
      this.requestline = new BasicRequestLine(this.method, this.uri, localProtocolVersion);
    }
    return this.requestline;
  }

  public String toString()
  {
    return this.method + " " + this.uri + " " + this.headergroup;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.message.BasicHttpRequest
 * JD-Core Version:    0.6.2
 */