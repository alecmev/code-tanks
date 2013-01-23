package org.apache.http;

public abstract interface RequestLine
{
  public abstract String getMethod();

  public abstract ProtocolVersion getProtocolVersion();

  public abstract String getUri();
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.RequestLine
 * JD-Core Version:    0.6.2
 */