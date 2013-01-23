package org.apache.commons.codec.binary;

import java.io.UnsupportedEncodingException;

public class StringUtils
{
  private static IllegalStateException newIllegalStateException(String paramString, UnsupportedEncodingException paramUnsupportedEncodingException)
  {
    return new IllegalStateException(paramString + ": " + paramUnsupportedEncodingException);
  }

  public static String newString(byte[] paramArrayOfByte, String paramString)
  {
    if (paramArrayOfByte == null)
      return null;
    try
    {
      return new String(paramArrayOfByte, paramString);
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      throw newIllegalStateException(paramString, localUnsupportedEncodingException);
    }
  }

  public static String newStringUtf8(byte[] paramArrayOfByte)
  {
    return newString(paramArrayOfByte, "UTF-8");
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.commons.codec.binary.StringUtils
 * JD-Core Version:    0.6.2
 */