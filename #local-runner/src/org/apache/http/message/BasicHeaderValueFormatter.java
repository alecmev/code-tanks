package org.apache.http.message;

import org.apache.http.NameValuePair;
import org.apache.http.util.CharArrayBuffer;

public class BasicHeaderValueFormatter
{
  public static final BasicHeaderValueFormatter DEFAULT = new BasicHeaderValueFormatter();

  public CharArrayBuffer formatNameValuePair(CharArrayBuffer paramCharArrayBuffer, NameValuePair paramNameValuePair, boolean paramBoolean)
  {
    if (paramNameValuePair == null)
      throw new IllegalArgumentException("NameValuePair must not be null.");
    int i = estimateNameValuePairLen(paramNameValuePair);
    if (paramCharArrayBuffer == null)
      paramCharArrayBuffer = new CharArrayBuffer(i);
    else
      paramCharArrayBuffer.ensureCapacity(i);
    paramCharArrayBuffer.append(paramNameValuePair.getName());
    String str = paramNameValuePair.getValue();
    if (str != null)
    {
      paramCharArrayBuffer.append('=');
      doFormatValue(paramCharArrayBuffer, str, paramBoolean);
    }
    return paramCharArrayBuffer;
  }

  protected int estimateNameValuePairLen(NameValuePair paramNameValuePair)
  {
    if (paramNameValuePair == null)
      return 0;
    int i = paramNameValuePair.getName().length();
    String str = paramNameValuePair.getValue();
    if (str != null)
      i += 3 + str.length();
    return i;
  }

  protected void doFormatValue(CharArrayBuffer paramCharArrayBuffer, String paramString, boolean paramBoolean)
  {
    if (!paramBoolean)
      for (i = 0; (i < paramString.length()) && (!paramBoolean); i++)
        paramBoolean = isSeparator(paramString.charAt(i));
    if (paramBoolean)
      paramCharArrayBuffer.append('"');
    for (int i = 0; i < paramString.length(); i++)
    {
      char c = paramString.charAt(i);
      if (isUnsafe(c))
        paramCharArrayBuffer.append('\\');
      paramCharArrayBuffer.append(c);
    }
    if (paramBoolean)
      paramCharArrayBuffer.append('"');
  }

  protected boolean isSeparator(char paramChar)
  {
    return " ;,:@()<>\\\"/[]?={}\t".indexOf(paramChar) >= 0;
  }

  protected boolean isUnsafe(char paramChar)
  {
    return "\"\\".indexOf(paramChar) >= 0;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.message.BasicHeaderValueFormatter
 * JD-Core Version:    0.6.2
 */