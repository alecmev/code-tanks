package org.apache.http.conn;

import java.net.ConnectException;
import org.apache.http.HttpHost;

public class HttpHostConnectException extends ConnectException
{
  private final HttpHost host;

  public HttpHostConnectException(HttpHost paramHttpHost, ConnectException paramConnectException)
  {
    super("Connection to " + paramHttpHost + " refused");
    this.host = paramHttpHost;
    initCause(paramConnectException);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.conn.HttpHostConnectException
 * JD-Core Version:    0.6.2
 */