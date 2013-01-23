package org.apache.http.impl.entity;

import java.io.IOException;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.ContentLengthStrategy;
import org.apache.http.impl.io.ChunkedInputStream;
import org.apache.http.impl.io.ContentLengthInputStream;
import org.apache.http.impl.io.IdentityInputStream;
import org.apache.http.io.SessionInputBuffer;

public class EntityDeserializer
{
  private final ContentLengthStrategy lenStrategy;

  public EntityDeserializer(ContentLengthStrategy paramContentLengthStrategy)
  {
    if (paramContentLengthStrategy == null)
      throw new IllegalArgumentException("Content length strategy may not be null");
    this.lenStrategy = paramContentLengthStrategy;
  }

  protected BasicHttpEntity doDeserialize(SessionInputBuffer paramSessionInputBuffer, HttpMessage paramHttpMessage)
    throws HttpException, IOException
  {
    BasicHttpEntity localBasicHttpEntity = new BasicHttpEntity();
    long l = this.lenStrategy.determineLength(paramHttpMessage);
    if (l == -2L)
    {
      localBasicHttpEntity.setChunked(true);
      localBasicHttpEntity.setContentLength(-1L);
      localBasicHttpEntity.setContent(new ChunkedInputStream(paramSessionInputBuffer));
    }
    else if (l == -1L)
    {
      localBasicHttpEntity.setChunked(false);
      localBasicHttpEntity.setContentLength(-1L);
      localBasicHttpEntity.setContent(new IdentityInputStream(paramSessionInputBuffer));
    }
    else
    {
      localBasicHttpEntity.setChunked(false);
      localBasicHttpEntity.setContentLength(l);
      localBasicHttpEntity.setContent(new ContentLengthInputStream(paramSessionInputBuffer, l));
    }
    Header localHeader1 = paramHttpMessage.getFirstHeader("Content-Type");
    if (localHeader1 != null)
      localBasicHttpEntity.setContentType(localHeader1);
    Header localHeader2 = paramHttpMessage.getFirstHeader("Content-Encoding");
    if (localHeader2 != null)
      localBasicHttpEntity.setContentEncoding(localHeader2);
    return localBasicHttpEntity;
  }

  public HttpEntity deserialize(SessionInputBuffer paramSessionInputBuffer, HttpMessage paramHttpMessage)
    throws HttpException, IOException
  {
    if (paramSessionInputBuffer == null)
      throw new IllegalArgumentException("Session input buffer may not be null");
    if (paramHttpMessage == null)
      throw new IllegalArgumentException("HTTP message may not be null");
    return doDeserialize(paramSessionInputBuffer, paramHttpMessage);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.entity.EntityDeserializer
 * JD-Core Version:    0.6.2
 */