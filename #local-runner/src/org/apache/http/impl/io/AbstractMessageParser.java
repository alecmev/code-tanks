package org.apache.http.impl.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;
import org.apache.http.ParseException;
import org.apache.http.ProtocolException;
import org.apache.http.io.HttpMessageParser;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.message.BasicLineParser;
import org.apache.http.message.LineParser;
import org.apache.http.params.HttpParams;
import org.apache.http.util.CharArrayBuffer;

public abstract class AbstractMessageParser
  implements HttpMessageParser
{
  private final SessionInputBuffer sessionBuffer;
  private final int maxHeaderCount;
  private final int maxLineLen;
  private final List headerLines;
  protected final LineParser lineParser;
  private int state;
  private HttpMessage message;

  public AbstractMessageParser(SessionInputBuffer paramSessionInputBuffer, LineParser paramLineParser, HttpParams paramHttpParams)
  {
    if (paramSessionInputBuffer == null)
      throw new IllegalArgumentException("Session input buffer may not be null");
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    this.sessionBuffer = paramSessionInputBuffer;
    this.maxHeaderCount = paramHttpParams.getIntParameter("http.connection.max-header-count", -1);
    this.maxLineLen = paramHttpParams.getIntParameter("http.connection.max-line-length", -1);
    this.lineParser = (paramLineParser != null ? paramLineParser : BasicLineParser.DEFAULT);
    this.headerLines = new ArrayList();
    this.state = 0;
  }

  public static Header[] parseHeaders(SessionInputBuffer paramSessionInputBuffer, int paramInt1, int paramInt2, LineParser paramLineParser)
    throws HttpException, IOException
  {
    if (paramLineParser == null)
      paramLineParser = BasicLineParser.DEFAULT;
    ArrayList localArrayList = new ArrayList();
    return parseHeaders(paramSessionInputBuffer, paramInt1, paramInt2, paramLineParser, localArrayList);
  }

  public static Header[] parseHeaders(SessionInputBuffer paramSessionInputBuffer, int paramInt1, int paramInt2, LineParser paramLineParser, List paramList)
    throws HttpException, IOException
  {
    if (paramSessionInputBuffer == null)
      throw new IllegalArgumentException("Session input buffer may not be null");
    if (paramLineParser == null)
      throw new IllegalArgumentException("Line parser may not be null");
    if (paramList == null)
      throw new IllegalArgumentException("Header line list may not be null");
    CharArrayBuffer localCharArrayBuffer1 = null;
    CharArrayBuffer localCharArrayBuffer2 = null;
    while (true)
    {
      if (localCharArrayBuffer1 == null)
        localCharArrayBuffer1 = new CharArrayBuffer(64);
      else
        localCharArrayBuffer1.clear();
      int i = paramSessionInputBuffer.readLine(localCharArrayBuffer1);
      if ((i == -1) || (localCharArrayBuffer1.length() < 1))
        break;
      if (((localCharArrayBuffer1.charAt(0) == ' ') || (localCharArrayBuffer1.charAt(0) == '\t')) && (localCharArrayBuffer2 != null))
      {
        for (j = 0; j < localCharArrayBuffer1.length(); j++)
        {
          int k = localCharArrayBuffer1.charAt(j);
          if ((k != 32) && (k != 9))
            break;
        }
        if ((paramInt2 > 0) && (localCharArrayBuffer2.length() + 1 + localCharArrayBuffer1.length() - j > paramInt2))
          throw new IOException("Maximum line length limit exceeded");
        localCharArrayBuffer2.append(' ');
        localCharArrayBuffer2.append(localCharArrayBuffer1, j, localCharArrayBuffer1.length() - j);
      }
      else
      {
        paramList.add(localCharArrayBuffer1);
        localCharArrayBuffer2 = localCharArrayBuffer1;
        localCharArrayBuffer1 = null;
      }
      if ((paramInt1 > 0) && (paramList.size() >= paramInt1))
        throw new IOException("Maximum header count exceeded");
    }
    Header[] arrayOfHeader = new Header[paramList.size()];
    for (int j = 0; j < paramList.size(); j++)
    {
      CharArrayBuffer localCharArrayBuffer3 = (CharArrayBuffer)paramList.get(j);
      try
      {
        arrayOfHeader[j] = paramLineParser.parseHeader(localCharArrayBuffer3);
      }
      catch (ParseException localParseException)
      {
        throw new ProtocolException(localParseException.getMessage());
      }
    }
    return arrayOfHeader;
  }

  protected abstract HttpMessage parseHead(SessionInputBuffer paramSessionInputBuffer)
    throws IOException, HttpException, ParseException;

  public HttpMessage parse()
    throws IOException, HttpException
  {
    int i = this.state;
    switch (i)
    {
    case 0:
      try
      {
        this.message = parseHead(this.sessionBuffer);
      }
      catch (ParseException localParseException)
      {
        throw new ProtocolException(localParseException.getMessage(), localParseException);
      }
      this.state = 1;
    case 1:
      Header[] arrayOfHeader = parseHeaders(this.sessionBuffer, this.maxHeaderCount, this.maxLineLen, this.lineParser, this.headerLines);
      this.message.setHeaders(arrayOfHeader);
      HttpMessage localHttpMessage = this.message;
      this.message = null;
      this.headerLines.clear();
      this.state = 0;
      return localHttpMessage;
    }
    throw new IllegalStateException("Inconsistent parser state");
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.io.AbstractMessageParser
 * JD-Core Version:    0.6.2
 */