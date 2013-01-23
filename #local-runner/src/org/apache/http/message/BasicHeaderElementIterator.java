package org.apache.http.message;

import java.util.NoSuchElementException;
import org.apache.http.FormattedHeader;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HeaderIterator;
import org.apache.http.util.CharArrayBuffer;

public class BasicHeaderElementIterator
  implements HeaderElementIterator
{
  private final HeaderIterator headerIt;
  private final HeaderValueParser parser;
  private HeaderElement currentElement = null;
  private CharArrayBuffer buffer = null;
  private ParserCursor cursor = null;

  public BasicHeaderElementIterator(HeaderIterator paramHeaderIterator, HeaderValueParser paramHeaderValueParser)
  {
    if (paramHeaderIterator == null)
      throw new IllegalArgumentException("Header iterator may not be null");
    if (paramHeaderValueParser == null)
      throw new IllegalArgumentException("Parser may not be null");
    this.headerIt = paramHeaderIterator;
    this.parser = paramHeaderValueParser;
  }

  public BasicHeaderElementIterator(HeaderIterator paramHeaderIterator)
  {
    this(paramHeaderIterator, BasicHeaderValueParser.DEFAULT);
  }

  private void bufferHeaderValue()
  {
    this.cursor = null;
    this.buffer = null;
    while (this.headerIt.hasNext())
    {
      Header localHeader = this.headerIt.nextHeader();
      if ((localHeader instanceof FormattedHeader))
      {
        this.buffer = ((FormattedHeader)localHeader).getBuffer();
        this.cursor = new ParserCursor(0, this.buffer.length());
        this.cursor.updatePos(((FormattedHeader)localHeader).getValuePos());
        break;
      }
      String str = localHeader.getValue();
      if (str != null)
      {
        this.buffer = new CharArrayBuffer(str.length());
        this.buffer.append(str);
        this.cursor = new ParserCursor(0, this.buffer.length());
        break;
      }
    }
  }

  private void parseNextElement()
  {
    while ((this.headerIt.hasNext()) || (this.cursor != null))
    {
      if ((this.cursor == null) || (this.cursor.atEnd()))
        bufferHeaderValue();
      if (this.cursor != null)
      {
        while (!this.cursor.atEnd())
        {
          HeaderElement localHeaderElement = this.parser.parseHeaderElement(this.buffer, this.cursor);
          if ((localHeaderElement.getName().length() != 0) || (localHeaderElement.getValue() != null))
          {
            this.currentElement = localHeaderElement;
            return;
          }
        }
        if (this.cursor.atEnd())
        {
          this.cursor = null;
          this.buffer = null;
        }
      }
    }
  }

  public boolean hasNext()
  {
    if (this.currentElement == null)
      parseNextElement();
    return this.currentElement != null;
  }

  public HeaderElement nextElement()
    throws NoSuchElementException
  {
    if (this.currentElement == null)
      parseNextElement();
    if (this.currentElement == null)
      throw new NoSuchElementException("No more header elements available");
    HeaderElement localHeaderElement = this.currentElement;
    this.currentElement = null;
    return localHeaderElement;
  }

  public final Object next()
    throws NoSuchElementException
  {
    return nextElement();
  }

  public void remove()
    throws UnsupportedOperationException
  {
    throw new UnsupportedOperationException("Remove not supported");
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.message.BasicHeaderElementIterator
 * JD-Core Version:    0.6.2
 */