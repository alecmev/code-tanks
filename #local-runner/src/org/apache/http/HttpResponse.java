package org.apache.http;

public abstract interface HttpResponse extends HttpMessage
{
  public abstract StatusLine getStatusLine();

  public abstract HttpEntity getEntity();

  public abstract void setEntity(HttpEntity paramHttpEntity);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.HttpResponse
 * JD-Core Version:    0.6.2
 */