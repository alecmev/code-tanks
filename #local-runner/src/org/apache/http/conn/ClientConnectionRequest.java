package org.apache.http.conn;

import java.util.concurrent.TimeUnit;

public abstract interface ClientConnectionRequest
{
  public abstract ManagedClientConnection getConnection(long paramLong, TimeUnit paramTimeUnit)
    throws InterruptedException, ConnectionPoolTimeoutException;

  public abstract void abortRequest();
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.conn.ClientConnectionRequest
 * JD-Core Version:    0.6.2
 */