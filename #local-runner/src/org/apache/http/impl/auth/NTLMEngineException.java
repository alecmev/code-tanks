package org.apache.http.impl.auth;

import org.apache.http.auth.AuthenticationException;

public class NTLMEngineException extends AuthenticationException
{
  public NTLMEngineException()
  {
  }

  public NTLMEngineException(String paramString)
  {
    super(paramString);
  }

  public NTLMEngineException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.auth.NTLMEngineException
 * JD-Core Version:    0.6.2
 */