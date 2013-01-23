package org.apache.http.client.utils;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicHeaderValueParser;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.message.ParserCursor;
import org.apache.http.util.CharArrayBuffer;

public class URLEncodedUtils
{
  private static final char[] DELIM = { '&' };
  private static final BitSet UNRESERVED = new BitSet(256);
  private static final BitSet PUNCT = new BitSet(256);
  private static final BitSet USERINFO = new BitSet(256);
  private static final BitSet PATHSAFE = new BitSet(256);
  private static final BitSet FRAGMENT = new BitSet(256);
  private static final BitSet RESERVED = new BitSet(256);
  private static final BitSet URLENCODER = new BitSet(256);

  public static List parse(String paramString, Charset paramCharset)
  {
    if (paramString == null)
      return Collections.emptyList();
    BasicHeaderValueParser localBasicHeaderValueParser = BasicHeaderValueParser.DEFAULT;
    CharArrayBuffer localCharArrayBuffer = new CharArrayBuffer(paramString.length());
    localCharArrayBuffer.append(paramString);
    ParserCursor localParserCursor = new ParserCursor(0, localCharArrayBuffer.length());
    ArrayList localArrayList = new ArrayList();
    while (!localParserCursor.atEnd())
    {
      NameValuePair localNameValuePair = localBasicHeaderValueParser.parseNameValuePair(localCharArrayBuffer, localParserCursor, DELIM);
      if (localNameValuePair.getName().length() > 0)
        localArrayList.add(new BasicNameValuePair(decodeFormFields(localNameValuePair.getName(), paramCharset), decodeFormFields(localNameValuePair.getValue(), paramCharset)));
    }
    return localArrayList;
  }

  public static String format(Iterable paramIterable, Charset paramCharset)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = paramIterable.iterator();
    while (localIterator.hasNext())
    {
      NameValuePair localNameValuePair = (NameValuePair)localIterator.next();
      String str1 = encodeFormFields(localNameValuePair.getName(), paramCharset);
      String str2 = encodeFormFields(localNameValuePair.getValue(), paramCharset);
      if (localStringBuilder.length() > 0)
        localStringBuilder.append("&");
      localStringBuilder.append(str1);
      if (str2 != null)
      {
        localStringBuilder.append("=");
        localStringBuilder.append(str2);
      }
    }
    return localStringBuilder.toString();
  }

  private static String urlencode(String paramString, Charset paramCharset, BitSet paramBitSet, boolean paramBoolean)
  {
    if (paramString == null)
      return null;
    StringBuilder localStringBuilder = new StringBuilder();
    ByteBuffer localByteBuffer = paramCharset.encode(paramString);
    while (localByteBuffer.hasRemaining())
    {
      int i = localByteBuffer.get() & 0xFF;
      if (paramBitSet.get(i))
      {
        localStringBuilder.append((char)i);
      }
      else if ((paramBoolean) && (i == 32))
      {
        localStringBuilder.append('+');
      }
      else
      {
        localStringBuilder.append("%");
        char c1 = Character.toUpperCase(Character.forDigit(i >> 4 & 0xF, 16));
        char c2 = Character.toUpperCase(Character.forDigit(i & 0xF, 16));
        localStringBuilder.append(c1);
        localStringBuilder.append(c2);
      }
    }
    return localStringBuilder.toString();
  }

  private static String urldecode(String paramString, Charset paramCharset, boolean paramBoolean)
  {
    if (paramString == null)
      return null;
    ByteBuffer localByteBuffer = ByteBuffer.allocate(paramString.length());
    CharBuffer localCharBuffer = CharBuffer.wrap(paramString);
    while (localCharBuffer.hasRemaining())
    {
      int i = localCharBuffer.get();
      if ((i == 37) && (localCharBuffer.remaining() >= 2))
      {
        char c1 = localCharBuffer.get();
        char c2 = localCharBuffer.get();
        int j = Character.digit(c1, 16);
        int k = Character.digit(c2, 16);
        if ((j != -1) && (k != -1))
        {
          localByteBuffer.put((byte)((j << 4) + k));
        }
        else
        {
          localByteBuffer.put((byte)37);
          localByteBuffer.put((byte)c1);
          localByteBuffer.put((byte)c2);
        }
      }
      else if ((paramBoolean) && (i == 43))
      {
        localByteBuffer.put((byte)32);
      }
      else
      {
        localByteBuffer.put((byte)i);
      }
    }
    localByteBuffer.flip();
    return paramCharset.decode(localByteBuffer).toString();
  }

  private static String decodeFormFields(String paramString, Charset paramCharset)
  {
    if (paramString == null)
      return null;
    return urldecode(paramString, paramCharset != null ? paramCharset : Consts.UTF_8, true);
  }

  private static String encodeFormFields(String paramString, Charset paramCharset)
  {
    if (paramString == null)
      return null;
    return urlencode(paramString, paramCharset != null ? paramCharset : Consts.UTF_8, URLENCODER, true);
  }

  static String encUserInfo(String paramString, Charset paramCharset)
  {
    return urlencode(paramString, paramCharset, USERINFO, false);
  }

  static String encFragment(String paramString, Charset paramCharset)
  {
    return urlencode(paramString, paramCharset, FRAGMENT, false);
  }

  static String encPath(String paramString, Charset paramCharset)
  {
    return urlencode(paramString, paramCharset, PATHSAFE, false);
  }

  static
  {
    for (int i = 97; i <= 122; i++)
      UNRESERVED.set(i);
    for (i = 65; i <= 90; i++)
      UNRESERVED.set(i);
    for (i = 48; i <= 57; i++)
      UNRESERVED.set(i);
    UNRESERVED.set(95);
    UNRESERVED.set(45);
    UNRESERVED.set(46);
    UNRESERVED.set(42);
    URLENCODER.or(UNRESERVED);
    UNRESERVED.set(33);
    UNRESERVED.set(126);
    UNRESERVED.set(39);
    UNRESERVED.set(40);
    UNRESERVED.set(41);
    PUNCT.set(44);
    PUNCT.set(59);
    PUNCT.set(58);
    PUNCT.set(36);
    PUNCT.set(38);
    PUNCT.set(43);
    PUNCT.set(61);
    USERINFO.or(UNRESERVED);
    USERINFO.or(PUNCT);
    PATHSAFE.or(UNRESERVED);
    PATHSAFE.set(47);
    PATHSAFE.set(59);
    PATHSAFE.set(58);
    PATHSAFE.set(64);
    PATHSAFE.set(38);
    PATHSAFE.set(61);
    PATHSAFE.set(43);
    PATHSAFE.set(36);
    PATHSAFE.set(44);
    RESERVED.set(59);
    RESERVED.set(47);
    RESERVED.set(63);
    RESERVED.set(58);
    RESERVED.set(64);
    RESERVED.set(38);
    RESERVED.set(61);
    RESERVED.set(43);
    RESERVED.set(36);
    RESERVED.set(44);
    RESERVED.set(91);
    RESERVED.set(93);
    FRAGMENT.or(RESERVED);
    FRAGMENT.or(UNRESERVED);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.client.utils.URLEncodedUtils
 * JD-Core Version:    0.6.2
 */