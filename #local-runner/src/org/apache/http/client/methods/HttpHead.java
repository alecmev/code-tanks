package org.apache.http.client.methods;

import java.net.URI;

public class HttpHead extends HttpRequestBase
{
  public HttpHead()
  {
  }

  public HttpHead(URI paramURI)
  {
    setURI(paramURI);
  }

  public String getMethod()
  {
    return "HEAD";
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.client.methods.HttpHead
 * JD-Core Version:    0.6.2
 */