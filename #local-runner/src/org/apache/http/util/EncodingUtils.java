package org.apache.http.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import org.apache.http.Consts;

public final class EncodingUtils
{
  public static byte[] getBytes(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      throw new IllegalArgumentException("data may not be null");
    if ((paramString2 == null) || (paramString2.length() == 0))
      throw new IllegalArgumentException("charset may not be null or empty");
    try
    {
      return paramString1.getBytes(paramString2);
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    return paramString1.getBytes();
  }

  public static byte[] getAsciiBytes(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Parameter may not be null");
    try
    {
      return paramString.getBytes(Consts.ASCII.name());
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    throw new Error("HttpClient requires ASCII support");
  }

  public static String getAsciiString(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (paramArrayOfByte == null)
      throw new IllegalArgumentException("Parameter may not be null");
    try
    {
      return new String(paramArrayOfByte, paramInt1, paramInt2, Consts.ASCII.name());
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    throw new Error("HttpClient requires ASCII support");
  }

  public static String getAsciiString(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null)
      throw new IllegalArgumentException("Parameter may not be null");
    return getAsciiString(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.util.EncodingUtils
 * JD-Core Version:    0.6.2
 */