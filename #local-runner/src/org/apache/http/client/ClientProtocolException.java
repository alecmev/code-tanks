package org.apache.http.client;

import java.io.IOException;

public class ClientProtocolException extends IOException
{
  public ClientProtocolException()
  {
  }

  public ClientProtocolException(String paramString)
  {
    super(paramString);
  }

  public ClientProtocolException(Throwable paramThrowable)
  {
    initCause(paramThrowable);
  }

  public ClientProtocolException(String paramString, Throwable paramThrowable)
  {
    super(paramString);
    initCause(paramThrowable);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.client.ClientProtocolException
 * JD-Core Version:    0.6.2
 */