package org.apache.http.impl.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.ProtocolException;
import org.apache.http.entity.HttpEntityWrapper;

public class EntityEnclosingRequestWrapper extends RequestWrapper
  implements HttpEntityEnclosingRequest
{
  private HttpEntity entity;
  private boolean consumed;

  public EntityEnclosingRequestWrapper(HttpEntityEnclosingRequest paramHttpEntityEnclosingRequest)
    throws ProtocolException
  {
    super(paramHttpEntityEnclosingRequest);
    setEntity(paramHttpEntityEnclosingRequest.getEntity());
  }

  public HttpEntity getEntity()
  {
    return this.entity;
  }

  public void setEntity(HttpEntity paramHttpEntity)
  {
    this.entity = (paramHttpEntity != null ? new EntityWrapper(paramHttpEntity) : null);
    this.consumed = false;
  }

  public boolean expectContinue()
  {
    Header localHeader = getFirstHeader("Expect");
    return (localHeader != null) && ("100-continue".equalsIgnoreCase(localHeader.getValue()));
  }

  public boolean isRepeatable()
  {
    return (this.entity == null) || (this.entity.isRepeatable()) || (!this.consumed);
  }

  class EntityWrapper extends HttpEntityWrapper
  {
    EntityWrapper(HttpEntity arg2)
    {
      super();
    }

    public InputStream getContent()
      throws IOException
    {
      EntityEnclosingRequestWrapper.this.consumed = true;
      return super.getContent();
    }

    public void writeTo(OutputStream paramOutputStream)
      throws IOException
    {
      EntityEnclosingRequestWrapper.this.consumed = true;
      super.writeTo(paramOutputStream);
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.client.EntityEnclosingRequestWrapper
 * JD-Core Version:    0.6.2
 */