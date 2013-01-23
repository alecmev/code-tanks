package org.apache.http.impl.client;

import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpResponse;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HttpContext;

public class DefaultConnectionKeepAliveStrategy
  implements ConnectionKeepAliveStrategy
{
  public long getKeepAliveDuration(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    if (paramHttpResponse == null)
      throw new IllegalArgumentException("HTTP response may not be null");
    BasicHeaderElementIterator localBasicHeaderElementIterator = new BasicHeaderElementIterator(paramHttpResponse.headerIterator("Keep-Alive"));
    while (localBasicHeaderElementIterator.hasNext())
    {
      HeaderElement localHeaderElement = localBasicHeaderElementIterator.nextElement();
      String str1 = localHeaderElement.getName();
      String str2 = localHeaderElement.getValue();
      if ((str2 != null) && (str1.equalsIgnoreCase("timeout")))
        try
        {
          return Long.parseLong(str2) * 1000L;
        }
        catch (NumberFormatException localNumberFormatException)
        {
        }
    }
    return -1L;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy
 * JD-Core Version:    0.6.2
 */