package org.apache.http.message;

import org.apache.http.Header;
import org.apache.http.RequestLine;
import org.apache.http.util.CharArrayBuffer;

public abstract interface LineFormatter
{
  public abstract CharArrayBuffer formatRequestLine(CharArrayBuffer paramCharArrayBuffer, RequestLine paramRequestLine);

  public abstract CharArrayBuffer formatHeader(CharArrayBuffer paramCharArrayBuffer, Header paramHeader);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.message.LineFormatter
 * JD-Core Version:    0.6.2
 */