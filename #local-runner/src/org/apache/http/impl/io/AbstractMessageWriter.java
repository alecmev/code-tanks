package org.apache.http.impl.io;

import java.io.IOException;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;
import org.apache.http.io.HttpMessageWriter;
import org.apache.http.io.SessionOutputBuffer;
import org.apache.http.message.BasicLineFormatter;
import org.apache.http.message.LineFormatter;
import org.apache.http.params.HttpParams;
import org.apache.http.util.CharArrayBuffer;

public abstract class AbstractMessageWriter
  implements HttpMessageWriter
{
  protected final SessionOutputBuffer sessionBuffer;
  protected final CharArrayBuffer lineBuf;
  protected final LineFormatter lineFormatter;

  public AbstractMessageWriter(SessionOutputBuffer paramSessionOutputBuffer, LineFormatter paramLineFormatter, HttpParams paramHttpParams)
  {
    if (paramSessionOutputBuffer == null)
      throw new IllegalArgumentException("Session input buffer may not be null");
    this.sessionBuffer = paramSessionOutputBuffer;
    this.lineBuf = new CharArrayBuffer(128);
    this.lineFormatter = (paramLineFormatter != null ? paramLineFormatter : BasicLineFormatter.DEFAULT);
  }

  protected abstract void writeHeadLine(HttpMessage paramHttpMessage)
    throws IOException;

  public void write(HttpMessage paramHttpMessage)
    throws IOException, HttpException
  {
    if (paramHttpMessage == null)
      throw new IllegalArgumentException("HTTP message may not be null");
    writeHeadLine(paramHttpMessage);
    HeaderIterator localHeaderIterator = paramHttpMessage.headerIterator();
    while (localHeaderIterator.hasNext())
    {
      Header localHeader = localHeaderIterator.nextHeader();
      this.sessionBuffer.writeLine(this.lineFormatter.formatHeader(this.lineBuf, localHeader));
    }
    this.lineBuf.clear();
    this.sessionBuffer.writeLine(this.lineBuf);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.io.AbstractMessageWriter
 * JD-Core Version:    0.6.2
 */