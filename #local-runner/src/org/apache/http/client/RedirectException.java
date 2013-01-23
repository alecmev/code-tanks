package org.apache.http.client;

import org.apache.http.ProtocolException;

public class RedirectException extends ProtocolException
{
  public RedirectException()
  {
  }

  public RedirectException(String paramString)
  {
    super(paramString);
  }

  public RedirectException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.client.RedirectException
 * JD-Core Version:    0.6.2
 */