package org.apache.http.client.methods;

import java.net.URI;
import org.apache.http.HttpRequest;

public abstract interface HttpUriRequest extends HttpRequest
{
  public abstract String getMethod();

  public abstract URI getURI();

  public abstract boolean isAborted();
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.client.methods.HttpUriRequest
 * JD-Core Version:    0.6.2
 */