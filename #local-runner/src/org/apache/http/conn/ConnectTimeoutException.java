package org.apache.http.conn;

import java.io.InterruptedIOException;

public class ConnectTimeoutException extends InterruptedIOException
{
  public ConnectTimeoutException()
  {
  }

  public ConnectTimeoutException(String paramString)
  {
    super(paramString);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.conn.ConnectTimeoutException
 * JD-Core Version:    0.6.2
 */