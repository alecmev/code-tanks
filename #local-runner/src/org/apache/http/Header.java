package org.apache.http;

public abstract interface Header
{
  public abstract String getName();

  public abstract String getValue();

  public abstract HeaderElement[] getElements()
    throws ParseException;
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.Header
 * JD-Core Version:    0.6.2
 */