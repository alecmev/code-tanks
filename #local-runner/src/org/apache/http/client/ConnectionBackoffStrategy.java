package org.apache.http.client;

import org.apache.http.HttpResponse;

public abstract interface ConnectionBackoffStrategy
{
  public abstract boolean shouldBackoff(Throwable paramThrowable);

  public abstract boolean shouldBackoff(HttpResponse paramHttpResponse);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.client.ConnectionBackoffStrategy
 * JD-Core Version:    0.6.2
 */