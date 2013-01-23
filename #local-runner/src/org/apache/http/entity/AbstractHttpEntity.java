package org.apache.http.entity;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.message.BasicHeader;

public abstract class AbstractHttpEntity
  implements HttpEntity
{
  protected Header contentType;
  protected Header contentEncoding;
  protected boolean chunked;

  public Header getContentType()
  {
    return this.contentType;
  }

  public Header getContentEncoding()
  {
    return this.contentEncoding;
  }

  public boolean isChunked()
  {
    return this.chunked;
  }

  public void setContentType(Header paramHeader)
  {
    this.contentType = paramHeader;
  }

  public void setContentType(String paramString)
  {
    BasicHeader localBasicHeader = null;
    if (paramString != null)
      localBasicHeader = new BasicHeader("Content-Type", paramString);
    setContentType(localBasicHeader);
  }

  public void setContentEncoding(Header paramHeader)
  {
    this.contentEncoding = paramHeader;
  }

  public void setChunked(boolean paramBoolean)
  {
    this.chunked = paramBoolean;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.entity.AbstractHttpEntity
 * JD-Core Version:    0.6.2
 */