package org.apache.http.client.methods;

import java.io.IOException;
import org.apache.http.conn.ClientConnectionRequest;
import org.apache.http.conn.ConnectionReleaseTrigger;

public abstract interface AbortableHttpRequest
{
  public abstract void setConnectionRequest(ClientConnectionRequest paramClientConnectionRequest)
    throws IOException;

  public abstract void setReleaseTrigger(ConnectionReleaseTrigger paramConnectionReleaseTrigger)
    throws IOException;
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.client.methods.AbortableHttpRequest
 * JD-Core Version:    0.6.2
 */