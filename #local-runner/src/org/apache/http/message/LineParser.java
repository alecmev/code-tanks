package org.apache.http.message;

import org.apache.http.Header;
import org.apache.http.ParseException;
import org.apache.http.StatusLine;
import org.apache.http.util.CharArrayBuffer;

public abstract interface LineParser
{
  public abstract boolean hasProtocolVersion(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor);

  public abstract StatusLine parseStatusLine(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor)
    throws ParseException;

  public abstract Header parseHeader(CharArrayBuffer paramCharArrayBuffer)
    throws ParseException;
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.message.LineParser
 * JD-Core Version:    0.6.2
 */