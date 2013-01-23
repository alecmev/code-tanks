package org.apache.http.impl.io;

import java.io.IOException;
import org.apache.http.HttpRequest;
import org.apache.http.io.SessionOutputBuffer;
import org.apache.http.message.LineFormatter;
import org.apache.http.params.HttpParams;

public class HttpRequestWriter extends AbstractMessageWriter
{
  public HttpRequestWriter(SessionOutputBuffer paramSessionOutputBuffer, LineFormatter paramLineFormatter, HttpParams paramHttpParams)
  {
    super(paramSessionOutputBuffer, paramLineFormatter, paramHttpParams);
  }

  protected void writeHeadLine(HttpRequest paramHttpRequest)
    throws IOException
  {
    this.lineFormatter.formatRequestLine(this.lineBuf, paramHttpRequest.getRequestLine());
    this.sessionBuffer.writeLine(this.lineBuf);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.io.HttpRequestWriter
 * JD-Core Version:    0.6.2
 */