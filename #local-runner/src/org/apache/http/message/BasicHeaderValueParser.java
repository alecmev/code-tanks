package org.apache.http.message;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.HeaderElement;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.CharArrayBuffer;

public class BasicHeaderValueParser
  implements HeaderValueParser
{
  public static final BasicHeaderValueParser DEFAULT = new BasicHeaderValueParser();
  private static final char[] ALL_DELIMITERS = { ';', ',' };

  public static final HeaderElement[] parseElements(String paramString, HeaderValueParser paramHeaderValueParser)
    throws ParseException
  {
    if (paramString == null)
      throw new IllegalArgumentException("Value to parse may not be null");
    if (paramHeaderValueParser == null)
      paramHeaderValueParser = DEFAULT;
    CharArrayBuffer localCharArrayBuffer = new CharArrayBuffer(paramString.length());
    localCharArrayBuffer.append(paramString);
    ParserCursor localParserCursor = new ParserCursor(0, paramString.length());
    return paramHeaderValueParser.parseElements(localCharArrayBuffer, localParserCursor);
  }

  public HeaderElement[] parseElements(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor)
  {
    if (paramCharArrayBuffer == null)
      throw new IllegalArgumentException("Char array buffer may not be null");
    if (paramParserCursor == null)
      throw new IllegalArgumentException("Parser cursor may not be null");
    ArrayList localArrayList = new ArrayList();
    while (!paramParserCursor.atEnd())
    {
      HeaderElement localHeaderElement = parseHeaderElement(paramCharArrayBuffer, paramParserCursor);
      if ((localHeaderElement.getName().length() != 0) || (localHeaderElement.getValue() != null))
        localArrayList.add(localHeaderElement);
    }
    return (HeaderElement[])localArrayList.toArray(new HeaderElement[localArrayList.size()]);
  }

  public HeaderElement parseHeaderElement(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor)
  {
    if (paramCharArrayBuffer == null)
      throw new IllegalArgumentException("Char array buffer may not be null");
    if (paramParserCursor == null)
      throw new IllegalArgumentException("Parser cursor may not be null");
    NameValuePair localNameValuePair = parseNameValuePair(paramCharArrayBuffer, paramParserCursor);
    NameValuePair[] arrayOfNameValuePair = null;
    if (!paramParserCursor.atEnd())
    {
      int i = paramCharArrayBuffer.charAt(paramParserCursor.getPos() - 1);
      if (i != 44)
        arrayOfNameValuePair = parseParameters(paramCharArrayBuffer, paramParserCursor);
    }
    return createHeaderElement(localNameValuePair.getName(), localNameValuePair.getValue(), arrayOfNameValuePair);
  }

  protected HeaderElement createHeaderElement(String paramString1, String paramString2, NameValuePair[] paramArrayOfNameValuePair)
  {
    return new BasicHeaderElement(paramString1, paramString2, paramArrayOfNameValuePair);
  }

  public NameValuePair[] parseParameters(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor)
  {
    if (paramCharArrayBuffer == null)
      throw new IllegalArgumentException("Char array buffer may not be null");
    if (paramParserCursor == null)
      throw new IllegalArgumentException("Parser cursor may not be null");
    int i = paramParserCursor.getPos();
    int j = paramParserCursor.getUpperBound();
    while (i < j)
    {
      char c = paramCharArrayBuffer.charAt(i);
      if (!HTTP.isWhitespace(c))
        break;
      i++;
    }
    paramParserCursor.updatePos(i);
    if (paramParserCursor.atEnd())
      return new NameValuePair[0];
    ArrayList localArrayList = new ArrayList();
    while (!paramParserCursor.atEnd())
    {
      NameValuePair localNameValuePair = parseNameValuePair(paramCharArrayBuffer, paramParserCursor);
      localArrayList.add(localNameValuePair);
      int k = paramCharArrayBuffer.charAt(paramParserCursor.getPos() - 1);
      if (k == 44)
        break;
    }
    return (NameValuePair[])localArrayList.toArray(new NameValuePair[localArrayList.size()]);
  }

  public NameValuePair parseNameValuePair(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor)
  {
    return parseNameValuePair(paramCharArrayBuffer, paramParserCursor, ALL_DELIMITERS);
  }

  private static boolean isOneOf(char paramChar, char[] paramArrayOfChar)
  {
    if (paramArrayOfChar != null)
      for (int i = 0; i < paramArrayOfChar.length; i++)
        if (paramChar == paramArrayOfChar[i])
          return true;
    return false;
  }

  public NameValuePair parseNameValuePair(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor, char[] paramArrayOfChar)
  {
    if (paramCharArrayBuffer == null)
      throw new IllegalArgumentException("Char array buffer may not be null");
    if (paramParserCursor == null)
      throw new IllegalArgumentException("Parser cursor may not be null");
    int i = 0;
    int j = paramParserCursor.getPos();
    int k = paramParserCursor.getPos();
    int m = paramParserCursor.getUpperBound();
    String str1 = null;
    while (j < m)
    {
      char c = paramCharArrayBuffer.charAt(j);
      if (c == '=')
        break;
      if (isOneOf(c, paramArrayOfChar))
      {
        i = 1;
        break;
      }
      j++;
    }
    if (j == m)
    {
      i = 1;
      str1 = paramCharArrayBuffer.substringTrimmed(k, m);
    }
    else
    {
      str1 = paramCharArrayBuffer.substringTrimmed(k, j);
      j++;
    }
    if (i != 0)
    {
      paramParserCursor.updatePos(j);
      return createNameValuePair(str1, null);
    }
    String str2 = null;
    int n = j;
    int i1 = 0;
    int i2 = 0;
    while (j < m)
    {
      i3 = paramCharArrayBuffer.charAt(j);
      if ((i3 == 34) && (i2 == 0))
        i1 = i1 == 0 ? 1 : 0;
      if ((i1 == 0) && (i2 == 0) && (isOneOf(i3, paramArrayOfChar)))
      {
        i = 1;
        break;
      }
      if (i2 != 0)
        i2 = 0;
      else
        i2 = (i1 != 0) && (i3 == 92) ? 1 : 0;
      j++;
    }
    int i3 = j;
    while ((n < i3) && (HTTP.isWhitespace(paramCharArrayBuffer.charAt(n))))
      n++;
    while ((i3 > n) && (HTTP.isWhitespace(paramCharArrayBuffer.charAt(i3 - 1))))
      i3--;
    if ((i3 - n >= 2) && (paramCharArrayBuffer.charAt(n) == '"') && (paramCharArrayBuffer.charAt(i3 - 1) == '"'))
    {
      n++;
      i3--;
    }
    str2 = paramCharArrayBuffer.substring(n, i3);
    if (i != 0)
      j++;
    paramParserCursor.updatePos(j);
    return createNameValuePair(str1, str2);
  }

  protected NameValuePair createNameValuePair(String paramString1, String paramString2)
  {
    return new BasicNameValuePair(paramString1, paramString2);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.message.BasicHeaderValueParser
 * JD-Core Version:    0.6.2
 */