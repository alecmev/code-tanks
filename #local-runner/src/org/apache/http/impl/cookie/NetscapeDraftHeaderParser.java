package org.apache.http.impl.cookie;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.HeaderElement;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.message.BasicHeaderElement;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.message.ParserCursor;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.CharArrayBuffer;

public class NetscapeDraftHeaderParser
{
  public static final NetscapeDraftHeaderParser DEFAULT = new NetscapeDraftHeaderParser();

  public HeaderElement parseHeader(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor)
    throws ParseException
  {
    if (paramCharArrayBuffer == null)
      throw new IllegalArgumentException("Char array buffer may not be null");
    if (paramParserCursor == null)
      throw new IllegalArgumentException("Parser cursor may not be null");
    NameValuePair localNameValuePair1 = parseNameValuePair(paramCharArrayBuffer, paramParserCursor);
    ArrayList localArrayList = new ArrayList();
    while (!paramParserCursor.atEnd())
    {
      NameValuePair localNameValuePair2 = parseNameValuePair(paramCharArrayBuffer, paramParserCursor);
      localArrayList.add(localNameValuePair2);
    }
    return new BasicHeaderElement(localNameValuePair1.getName(), localNameValuePair1.getValue(), (NameValuePair[])localArrayList.toArray(new NameValuePair[localArrayList.size()]));
  }

  private NameValuePair parseNameValuePair(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor)
  {
    int i = 0;
    int j = paramParserCursor.getPos();
    int k = paramParserCursor.getPos();
    int m = paramParserCursor.getUpperBound();
    String str1 = null;
    while (j < m)
    {
      int n = paramCharArrayBuffer.charAt(j);
      if (n == 61)
        break;
      if (n == 59)
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
      return new BasicNameValuePair(str1, null);
    }
    String str2 = null;
    int i1 = j;
    while (j < m)
    {
      i2 = paramCharArrayBuffer.charAt(j);
      if (i2 == 59)
      {
        i = 1;
        break;
      }
      j++;
    }
    int i2 = j;
    while ((i1 < i2) && (HTTP.isWhitespace(paramCharArrayBuffer.charAt(i1))))
      i1++;
    while ((i2 > i1) && (HTTP.isWhitespace(paramCharArrayBuffer.charAt(i2 - 1))))
      i2--;
    str2 = paramCharArrayBuffer.substring(i1, i2);
    if (i != 0)
      j++;
    paramParserCursor.updatePos(j);
    return new BasicNameValuePair(str1, str2);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.cookie.NetscapeDraftHeaderParser
 * JD-Core Version:    0.6.2
 */