package com.google.protobuf;

import java.io.UnsupportedEncodingException;

public class Internal
{
  public static String stringDefaultValue(String paramString)
  {
    try
    {
      return new String(paramString.getBytes("ISO-8859-1"), "UTF-8");
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      throw new IllegalStateException("Java VM does not support a standard character set.", localUnsupportedEncodingException);
    }
  }

  public static ByteString bytesDefaultValue(String paramString)
  {
    try
    {
      return ByteString.copyFrom(paramString.getBytes("ISO-8859-1"));
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      throw new IllegalStateException("Java VM does not support a standard character set.", localUnsupportedEncodingException);
    }
  }

  public static boolean isValidUtf8(ByteString paramByteString)
  {
    int i = 0;
    int j = paramByteString.size();
    while (i < j)
    {
      int k = paramByteString.byteAt(i++) & 0xFF;
      if (k >= 128)
      {
        if ((k < 194) || (k > 244))
          return false;
        if (i >= j)
          return false;
        int m = paramByteString.byteAt(i++) & 0xFF;
        if ((m < 128) || (m > 191))
          return false;
        if (k > 223)
        {
          if (i >= j)
            return false;
          int n = paramByteString.byteAt(i++) & 0xFF;
          if ((n < 128) || (n > 191))
            return false;
          if (k <= 239)
          {
            if (((k == 224) && (m < 160)) || ((k == 237) && (m > 159)))
              return false;
          }
          else
          {
            if (i >= j)
              return false;
            int i1 = paramByteString.byteAt(i++) & 0xFF;
            if ((i1 < 128) || (i1 > 191))
              return false;
            if (((k == 240) && (m < 144)) || ((k == 244) && (m > 143)))
              return false;
          }
        }
      }
    }
    return true;
  }

  public static abstract interface EnumLiteMap
  {
    public abstract Internal.EnumLite findValueByNumber(int paramInt);
  }

  public static abstract interface EnumLite
  {
    public abstract int getNumber();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.protobuf.Internal
 * JD-Core Version:    0.6.2
 */