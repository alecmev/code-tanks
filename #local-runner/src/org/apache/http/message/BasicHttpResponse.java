package org.apache.http.message;

import java.util.Locale;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.ReasonPhraseCatalog;
import org.apache.http.StatusLine;

public class BasicHttpResponse extends AbstractHttpMessage
  implements HttpResponse
{
  private StatusLine statusline;
  private HttpEntity entity;
  private ReasonPhraseCatalog reasonCatalog;
  private Locale locale;

  public BasicHttpResponse(StatusLine paramStatusLine, ReasonPhraseCatalog paramReasonPhraseCatalog, Locale paramLocale)
  {
    if (paramStatusLine == null)
      throw new IllegalArgumentException("Status line may not be null.");
    this.statusline = paramStatusLine;
    this.reasonCatalog = paramReasonPhraseCatalog;
    this.locale = (paramLocale != null ? paramLocale : Locale.getDefault());
  }

  public ProtocolVersion getProtocolVersion()
  {
    return this.statusline.getProtocolVersion();
  }

  public StatusLine getStatusLine()
  {
    return this.statusline;
  }

  public HttpEntity getEntity()
  {
    return this.entity;
  }

  public void setEntity(HttpEntity paramHttpEntity)
  {
    this.entity = paramHttpEntity;
  }

  public String toString()
  {
    return this.statusline + " " + this.headergroup;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.message.BasicHttpResponse
 * JD-Core Version:    0.6.2
 */