package org.apache.http.client;

import org.apache.http.ProtocolException;

public class NonRepeatableRequestException extends ProtocolException
{
  public NonRepeatableRequestException()
  {
  }

  public NonRepeatableRequestException(String paramString)
  {
    super(paramString);
  }

  public NonRepeatableRequestException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.client.NonRepeatableRequestException
 * JD-Core Version:    0.6.2
 */