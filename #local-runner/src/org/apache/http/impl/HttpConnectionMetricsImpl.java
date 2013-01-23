package org.apache.http.impl;

import org.apache.http.io.HttpTransportMetrics;

public class HttpConnectionMetricsImpl
{
  private final HttpTransportMetrics inTransportMetric;
  private final HttpTransportMetrics outTransportMetric;
  private long requestCount = 0L;
  private long responseCount = 0L;

  public HttpConnectionMetricsImpl(HttpTransportMetrics paramHttpTransportMetrics1, HttpTransportMetrics paramHttpTransportMetrics2)
  {
    this.inTransportMetric = paramHttpTransportMetrics1;
    this.outTransportMetric = paramHttpTransportMetrics2;
  }

  public void incrementRequestCount()
  {
    this.requestCount += 1L;
  }

  public void incrementResponseCount()
  {
    this.responseCount += 1L;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.HttpConnectionMetricsImpl
 * JD-Core Version:    0.6.2
 */