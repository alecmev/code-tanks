package org.apache.http.entity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;

public class HttpEntityWrapper
  implements HttpEntity
{
  protected HttpEntity wrappedEntity;

  public HttpEntityWrapper(HttpEntity paramHttpEntity)
  {
    if (paramHttpEntity == null)
      throw new IllegalArgumentException("wrapped entity must not be null");
    this.wrappedEntity = paramHttpEntity;
  }

  public boolean isRepeatable()
  {
    return this.wrappedEntity.isRepeatable();
  }

  public boolean isChunked()
  {
    return this.wrappedEntity.isChunked();
  }

  public long getContentLength()
  {
    return this.wrappedEntity.getContentLength();
  }

  public Header getContentType()
  {
    return this.wrappedEntity.getContentType();
  }

  public Header getContentEncoding()
  {
    return this.wrappedEntity.getContentEncoding();
  }

  public InputStream getContent()
    throws IOException
  {
    return this.wrappedEntity.getContent();
  }

  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    this.wrappedEntity.writeTo(paramOutputStream);
  }

  public boolean isStreaming()
  {
    return this.wrappedEntity.isStreaming();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.entity.HttpEntityWrapper
 * JD-Core Version:    0.6.2
 */