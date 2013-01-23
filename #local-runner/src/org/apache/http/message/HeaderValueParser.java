package org.apache.http.message;

import org.apache.http.HeaderElement;
import org.apache.http.ParseException;
import org.apache.http.util.CharArrayBuffer;

public abstract interface HeaderValueParser
{
  public abstract HeaderElement[] parseElements(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor)
    throws ParseException;

  public abstract HeaderElement parseHeaderElement(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor)
    throws ParseException;
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.message.HeaderValueParser
 * JD-Core Version:    0.6.2
 */