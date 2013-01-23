package org.apache.http.impl.io;

import org.apache.http.io.HttpTransportMetrics;

public class HttpTransportMetricsImpl
  implements HttpTransportMetrics
{
  private long bytesTransferred = 0L;

  public void incrementBytesTransferred(long paramLong)
  {
    this.bytesTransferred += paramLong;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.io.HttpTransportMetricsImpl
 * JD-Core Version:    0.6.2
 */