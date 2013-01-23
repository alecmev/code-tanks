package org.apache.http.auth;

import org.apache.http.ProtocolException;

public class AuthenticationException extends ProtocolException
{
  public AuthenticationException()
  {
  }

  public AuthenticationException(String paramString)
  {
    super(paramString);
  }

  public AuthenticationException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.auth.AuthenticationException
 * JD-Core Version:    0.6.2
 */