package org.apache.http.impl;

import java.util.Locale;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseFactory;
import org.apache.http.ReasonPhraseCatalog;
import org.apache.http.StatusLine;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.protocol.HttpContext;

public class DefaultHttpResponseFactory
  implements HttpResponseFactory
{
  protected final ReasonPhraseCatalog reasonCatalog;

  public DefaultHttpResponseFactory(ReasonPhraseCatalog paramReasonPhraseCatalog)
  {
    if (paramReasonPhraseCatalog == null)
      throw new IllegalArgumentException("Reason phrase catalog must not be null.");
    this.reasonCatalog = paramReasonPhraseCatalog;
  }

  public DefaultHttpResponseFactory()
  {
    this(EnglishReasonPhraseCatalog.INSTANCE);
  }

  public HttpResponse newHttpResponse(StatusLine paramStatusLine, HttpContext paramHttpContext)
  {
    if (paramStatusLine == null)
      throw new IllegalArgumentException("Status line may not be null");
    Locale localLocale = determineLocale(paramHttpContext);
    return new BasicHttpResponse(paramStatusLine, this.reasonCatalog, localLocale);
  }

  protected Locale determineLocale(HttpContext paramHttpContext)
  {
    return Locale.getDefault();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.DefaultHttpResponseFactory
 * JD-Core Version:    0.6.2
 */