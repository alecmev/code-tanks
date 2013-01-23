package org.apache.http.conn;

import java.io.IOException;
import java.io.InputStream;

public abstract interface EofSensorWatcher
{
  public abstract boolean eofDetected(InputStream paramInputStream)
    throws IOException;

  public abstract boolean streamClosed(InputStream paramInputStream)
    throws IOException;

  public abstract boolean streamAbort(InputStream paramInputStream)
    throws IOException;
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.conn.EofSensorWatcher
 * JD-Core Version:    0.6.2
 */