package org.apache.http;

import java.io.Closeable;
import java.io.IOException;

public abstract interface HttpConnection extends Closeable
{
  public abstract void close()
    throws IOException;

  public abstract boolean isOpen();

  public abstract boolean isStale();

  public abstract void setSocketTimeout(int paramInt);

  public abstract void shutdown()
    throws IOException;
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.HttpConnection
 * JD-Core Version:    0.6.2
 */