package org.apache.http;

public abstract interface StatusLine
{
  public abstract ProtocolVersion getProtocolVersion();

  public abstract int getStatusCode();

  public abstract String getReasonPhrase();
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.StatusLine
 * JD-Core Version:    0.6.2
 */