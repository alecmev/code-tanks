package org.apache.http.impl.conn;

import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseFactory;
import org.apache.http.NoHttpResponseException;
import org.apache.http.ProtocolException;
import org.apache.http.StatusLine;
import org.apache.http.impl.io.AbstractMessageParser;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.message.LineParser;
import org.apache.http.message.ParserCursor;
import org.apache.http.params.HttpParams;
import org.apache.http.util.CharArrayBuffer;

public class DefaultHttpResponseParser extends AbstractMessageParser
{
  private final Log log = LogFactory.getLog(getClass());
  private final HttpResponseFactory responseFactory;
  private final CharArrayBuffer lineBuf;

  public DefaultHttpResponseParser(SessionInputBuffer paramSessionInputBuffer, LineParser paramLineParser, HttpResponseFactory paramHttpResponseFactory, HttpParams paramHttpParams)
  {
    super(paramSessionInputBuffer, paramLineParser, paramHttpParams);
    if (paramHttpResponseFactory == null)
      throw new IllegalArgumentException("Response factory may not be null");
    this.responseFactory = paramHttpResponseFactory;
    this.lineBuf = new CharArrayBuffer(128);
  }

  protected HttpResponse parseHead(SessionInputBuffer paramSessionInputBuffer)
    throws IOException, HttpException
  {
    int i = 0;
    ParserCursor localParserCursor = null;
    while (true)
    {
      this.lineBuf.clear();
      int j = paramSessionInputBuffer.readLine(this.lineBuf);
      if ((j == -1) && (i == 0))
        throw new NoHttpResponseException("The target server failed to respond");
      localParserCursor = new ParserCursor(0, this.lineBuf.length());
      if (this.lineParser.hasProtocolVersion(this.lineBuf, localParserCursor))
        break;
      if ((j == -1) || (reject(this.lineBuf, i)))
        throw new ProtocolException("The server failed to respond with a valid HTTP response");
      if (this.log.isDebugEnabled())
        this.log.debug("Garbage in response: " + this.lineBuf.toString());
      i++;
    }
    StatusLine localStatusLine = this.lineParser.parseStatusLine(this.lineBuf, localParserCursor);
    return this.responseFactory.newHttpResponse(localStatusLine, null);
  }

  protected boolean reject(CharArrayBuffer paramCharArrayBuffer, int paramInt)
  {
    return false;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.conn.DefaultHttpResponseParser
 * JD-Core Version:    0.6.2
 */