package org.apache.http.cookie;

import org.apache.http.ProtocolException;

public class MalformedCookieException extends ProtocolException
{
  public MalformedCookieException()
  {
  }

  public MalformedCookieException(String paramString)
  {
    super(paramString);
  }

  public MalformedCookieException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.cookie.MalformedCookieException
 * JD-Core Version:    0.6.2
 */