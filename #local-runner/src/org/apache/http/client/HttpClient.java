package org.apache.http.client;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;

public abstract interface HttpClient
{
  public abstract ClientConnectionManager getConnectionManager();

  public abstract HttpResponse execute(HttpUriRequest paramHttpUriRequest)
    throws IOException, ClientProtocolException;
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.client.HttpClient
 * JD-Core Version:    0.6.2
 */