package org.apache.http.conn;

import java.io.IOException;

public abstract interface ConnectionReleaseTrigger
{
  public abstract void releaseConnection()
    throws IOException;

  public abstract void abortConnection()
    throws IOException;
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.conn.ConnectionReleaseTrigger
 * JD-Core Version:    0.6.2
 */