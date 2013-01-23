package org.apache.http;

public abstract interface HttpEntityEnclosingRequest extends HttpRequest
{
  public abstract boolean expectContinue();

  public abstract HttpEntity getEntity();
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.HttpEntityEnclosingRequest
 * JD-Core Version:    0.6.2
 */