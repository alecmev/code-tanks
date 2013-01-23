package org.apache.http.client.methods;

import java.net.URI;

public class HttpGet extends HttpRequestBase
{
  public HttpGet()
  {
  }

  public HttpGet(URI paramURI)
  {
    setURI(paramURI);
  }

  public String getMethod()
  {
    return "GET";
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.client.methods.HttpGet
 * JD-Core Version:    0.6.2
 */