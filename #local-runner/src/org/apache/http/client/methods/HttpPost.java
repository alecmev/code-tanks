package org.apache.http.client.methods;

import java.net.URI;

public class HttpPost extends HttpEntityEnclosingRequestBase
{
  public HttpPost()
  {
  }

  public HttpPost(String paramString)
  {
    setURI(URI.create(paramString));
  }

  public String getMethod()
  {
    return "POST";
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.client.methods.HttpPost
 * JD-Core Version:    0.6.2
 */