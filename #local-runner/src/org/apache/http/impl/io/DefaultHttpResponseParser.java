package org.apache.http.impl.io;

import java.io.IOException;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseFactory;
import org.apache.http.NoHttpResponseException;
import org.apache.http.ParseException;
import org.apache.http.StatusLine;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.message.LineParser;
import org.apache.http.message.ParserCursor;
import org.apache.http.params.HttpParams;
import org.apache.http.util.CharArrayBuffer;

public class DefaultHttpResponseParser extends AbstractMessageParser
{
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
    throws IOException, HttpException, ParseException
  {
    this.lineBuf.clear();
    int i = paramSessionInputBuffer.readLine(this.lineBuf);
    if (i == -1)
      throw new NoHttpResponseException("The target server failed to respond");
    ParserCursor localParserCursor = new ParserCursor(0, this.lineBuf.length());
    StatusLine localStatusLine = this.lineParser.parseStatusLine(this.lineBuf, localParserCursor);
    return this.responseFactory.newHttpResponse(localStatusLine, null);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.io.DefaultHttpResponseParser
 * JD-Core Version:    0.6.2
 */