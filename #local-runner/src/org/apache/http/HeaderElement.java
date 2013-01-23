package org.apache.http;

public abstract interface HeaderElement
{
  public abstract String getName();

  public abstract String getValue();

  public abstract NameValuePair[] getParameters();

  public abstract NameValuePair getParameterByName(String paramString);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.HeaderElement
 * JD-Core Version:    0.6.2
 */