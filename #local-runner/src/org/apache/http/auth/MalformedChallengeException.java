package org.apache.http.auth;

import org.apache.http.ProtocolException;

public class MalformedChallengeException extends ProtocolException
{
  public MalformedChallengeException()
  {
  }

  public MalformedChallengeException(String paramString)
  {
    super(paramString);
  }

  public MalformedChallengeException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.auth.MalformedChallengeException
 * JD-Core Version:    0.6.2
 */