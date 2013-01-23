package org.apache.http.message;

import org.apache.http.Header;
import org.apache.http.HttpVersion;
import org.apache.http.ParseException;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.CharArrayBuffer;

public class BasicLineParser
  implements LineParser
{
  public static final BasicLineParser DEFAULT = new BasicLineParser();
  protected final ProtocolVersion protocol;

  public BasicLineParser(ProtocolVersion paramProtocolVersion)
  {
    if (paramProtocolVersion == null)
      paramProtocolVersion = HttpVersion.HTTP_1_1;
    this.protocol = paramProtocolVersion;
  }

  public BasicLineParser()
  {
    this(null);
  }

  public ProtocolVersion parseProtocolVersion(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor)
    throws ParseException
  {
    if (paramCharArrayBuffer == null)
      throw new IllegalArgumentException("Char array buffer may not be null");
    if (paramParserCursor == null)
      throw new IllegalArgumentException("Parser cursor may not be null");
    String str = this.protocol.getProtocol();
    int i = str.length();
    int j = paramParserCursor.getPos();
    int k = paramParserCursor.getUpperBound();
    skipWhitespace(paramCharArrayBuffer, paramParserCursor);
    int m = paramParserCursor.getPos();
    if (m + i + 4 > k)
      throw new ParseException("Not a valid protocol version: " + paramCharArrayBuffer.substring(j, k));
    int n = 1;
    for (int i1 = 0; (n != 0) && (i1 < i); i1++)
      n = paramCharArrayBuffer.charAt(m + i1) == str.charAt(i1) ? 1 : 0;
    if (n != 0)
      n = paramCharArrayBuffer.charAt(m + i) == '/' ? 1 : 0;
    if (n == 0)
      throw new ParseException("Not a valid protocol version: " + paramCharArrayBuffer.substring(j, k));
    m += i + 1;
    i1 = paramCharArrayBuffer.indexOf(46, m, k);
    if (i1 == -1)
      throw new ParseException("Invalid protocol version number: " + paramCharArrayBuffer.substring(j, k));
    int i2;
    try
    {
      i2 = Integer.parseInt(paramCharArrayBuffer.substringTrimmed(m, i1));
    }
    catch (NumberFormatException localNumberFormatException1)
    {
      throw new ParseException("Invalid protocol major version number: " + paramCharArrayBuffer.substring(j, k));
    }
    m = i1 + 1;
    int i3 = paramCharArrayBuffer.indexOf(32, m, k);
    if (i3 == -1)
      i3 = k;
    int i4;
    try
    {
      i4 = Integer.parseInt(paramCharArrayBuffer.substringTrimmed(m, i3));
    }
    catch (NumberFormatException localNumberFormatException2)
    {
      throw new ParseException("Invalid protocol minor version number: " + paramCharArrayBuffer.substring(j, k));
    }
    paramParserCursor.updatePos(i3);
    return createProtocolVersion(i2, i4);
  }

  protected ProtocolVersion createProtocolVersion(int paramInt1, int paramInt2)
  {
    return this.protocol.forVersion(paramInt1, paramInt2);
  }

  public boolean hasProtocolVersion(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor)
  {
    if (paramCharArrayBuffer == null)
      throw new IllegalArgumentException("Char array buffer may not be null");
    if (paramParserCursor == null)
      throw new IllegalArgumentException("Parser cursor may not be null");
    int i = paramParserCursor.getPos();
    String str = this.protocol.getProtocol();
    int j = str.length();
    if (paramCharArrayBuffer.length() < j + 4)
      return false;
    if (i < 0)
      i = paramCharArrayBuffer.length() - 4 - j;
    else if (i == 0)
      while ((i < paramCharArrayBuffer.length()) && (HTTP.isWhitespace(paramCharArrayBuffer.charAt(i))))
        i++;
    if (i + j + 4 > paramCharArrayBuffer.length())
      return false;
    boolean bool = true;
    for (int k = 0; (bool) && (k < j); k++)
      bool = paramCharArrayBuffer.charAt(i + k) == str.charAt(k);
    if (bool)
      bool = paramCharArrayBuffer.charAt(i + j) == '/';
    return bool;
  }

  public StatusLine parseStatusLine(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor)
    throws ParseException
  {
    if (paramCharArrayBuffer == null)
      throw new IllegalArgumentException("Char array buffer may not be null");
    if (paramParserCursor == null)
      throw new IllegalArgumentException("Parser cursor may not be null");
    int i = paramParserCursor.getPos();
    int j = paramParserCursor.getUpperBound();
    try
    {
      ProtocolVersion localProtocolVersion = parseProtocolVersion(paramCharArrayBuffer, paramParserCursor);
      skipWhitespace(paramCharArrayBuffer, paramParserCursor);
      int k = paramParserCursor.getPos();
      int m = paramCharArrayBuffer.indexOf(32, k, j);
      if (m < 0)
        m = j;
      int n = 0;
      String str1 = paramCharArrayBuffer.substringTrimmed(k, m);
      for (int i1 = 0; i1 < str1.length(); i1++)
        if (!Character.isDigit(str1.charAt(i1)))
          throw new ParseException("Status line contains invalid status code: " + paramCharArrayBuffer.substring(i, j));
      try
      {
        n = Integer.parseInt(str1);
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw new ParseException("Status line contains invalid status code: " + paramCharArrayBuffer.substring(i, j));
      }
      k = m;
      String str2 = null;
      if (k < j)
        str2 = paramCharArrayBuffer.substringTrimmed(k, j);
      else
        str2 = "";
      return createStatusLine(localProtocolVersion, n, str2);
    }
    catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
    {
    }
    throw new ParseException("Invalid status line: " + paramCharArrayBuffer.substring(i, j));
  }

  protected StatusLine createStatusLine(ProtocolVersion paramProtocolVersion, int paramInt, String paramString)
  {
    return new BasicStatusLine(paramProtocolVersion, paramInt, paramString);
  }

  public Header parseHeader(CharArrayBuffer paramCharArrayBuffer)
    throws ParseException
  {
    return new BufferedHeader(paramCharArrayBuffer);
  }

  protected void skipWhitespace(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor)
  {
    int i = paramParserCursor.getPos();
    int j = paramParserCursor.getUpperBound();
    while ((i < j) && (HTTP.isWhitespace(paramCharArrayBuffer.charAt(i))))
      i++;
    paramParserCursor.updatePos(i);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.message.BasicLineParser
 * JD-Core Version:    0.6.2
 */