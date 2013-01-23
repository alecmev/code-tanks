package org.apache.http.message;

import org.apache.http.FormattedHeader;
import org.apache.http.Header;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;
import org.apache.http.StatusLine;
import org.apache.http.util.CharArrayBuffer;

public class BasicLineFormatter
  implements LineFormatter
{
  public static final BasicLineFormatter DEFAULT = new BasicLineFormatter();

  protected CharArrayBuffer initBuffer(CharArrayBuffer paramCharArrayBuffer)
  {
    if (paramCharArrayBuffer != null)
      paramCharArrayBuffer.clear();
    else
      paramCharArrayBuffer = new CharArrayBuffer(64);
    return paramCharArrayBuffer;
  }

  public CharArrayBuffer appendProtocolVersion(CharArrayBuffer paramCharArrayBuffer, ProtocolVersion paramProtocolVersion)
  {
    if (paramProtocolVersion == null)
      throw new IllegalArgumentException("Protocol version may not be null");
    CharArrayBuffer localCharArrayBuffer = paramCharArrayBuffer;
    int i = estimateProtocolVersionLen(paramProtocolVersion);
    if (localCharArrayBuffer == null)
      localCharArrayBuffer = new CharArrayBuffer(i);
    else
      localCharArrayBuffer.ensureCapacity(i);
    localCharArrayBuffer.append(paramProtocolVersion.getProtocol());
    localCharArrayBuffer.append('/');
    localCharArrayBuffer.append(Integer.toString(paramProtocolVersion.getMajor()));
    localCharArrayBuffer.append('.');
    localCharArrayBuffer.append(Integer.toString(paramProtocolVersion.getMinor()));
    return localCharArrayBuffer;
  }

  protected int estimateProtocolVersionLen(ProtocolVersion paramProtocolVersion)
  {
    return paramProtocolVersion.getProtocol().length() + 4;
  }

  public CharArrayBuffer formatRequestLine(CharArrayBuffer paramCharArrayBuffer, RequestLine paramRequestLine)
  {
    if (paramRequestLine == null)
      throw new IllegalArgumentException("Request line may not be null");
    CharArrayBuffer localCharArrayBuffer = initBuffer(paramCharArrayBuffer);
    doFormatRequestLine(localCharArrayBuffer, paramRequestLine);
    return localCharArrayBuffer;
  }

  protected void doFormatRequestLine(CharArrayBuffer paramCharArrayBuffer, RequestLine paramRequestLine)
  {
    String str1 = paramRequestLine.getMethod();
    String str2 = paramRequestLine.getUri();
    int i = str1.length() + 1 + str2.length() + 1 + estimateProtocolVersionLen(paramRequestLine.getProtocolVersion());
    paramCharArrayBuffer.ensureCapacity(i);
    paramCharArrayBuffer.append(str1);
    paramCharArrayBuffer.append(' ');
    paramCharArrayBuffer.append(str2);
    paramCharArrayBuffer.append(' ');
    appendProtocolVersion(paramCharArrayBuffer, paramRequestLine.getProtocolVersion());
  }

  public CharArrayBuffer formatStatusLine(CharArrayBuffer paramCharArrayBuffer, StatusLine paramStatusLine)
  {
    if (paramStatusLine == null)
      throw new IllegalArgumentException("Status line may not be null");
    CharArrayBuffer localCharArrayBuffer = initBuffer(paramCharArrayBuffer);
    doFormatStatusLine(localCharArrayBuffer, paramStatusLine);
    return localCharArrayBuffer;
  }

  protected void doFormatStatusLine(CharArrayBuffer paramCharArrayBuffer, StatusLine paramStatusLine)
  {
    int i = estimateProtocolVersionLen(paramStatusLine.getProtocolVersion()) + 1 + 3 + 1;
    String str = paramStatusLine.getReasonPhrase();
    if (str != null)
      i += str.length();
    paramCharArrayBuffer.ensureCapacity(i);
    appendProtocolVersion(paramCharArrayBuffer, paramStatusLine.getProtocolVersion());
    paramCharArrayBuffer.append(' ');
    paramCharArrayBuffer.append(Integer.toString(paramStatusLine.getStatusCode()));
    paramCharArrayBuffer.append(' ');
    if (str != null)
      paramCharArrayBuffer.append(str);
  }

  public CharArrayBuffer formatHeader(CharArrayBuffer paramCharArrayBuffer, Header paramHeader)
  {
    if (paramHeader == null)
      throw new IllegalArgumentException("Header may not be null");
    CharArrayBuffer localCharArrayBuffer = null;
    if ((paramHeader instanceof FormattedHeader))
    {
      localCharArrayBuffer = ((FormattedHeader)paramHeader).getBuffer();
    }
    else
    {
      localCharArrayBuffer = initBuffer(paramCharArrayBuffer);
      doFormatHeader(localCharArrayBuffer, paramHeader);
    }
    return localCharArrayBuffer;
  }

  protected void doFormatHeader(CharArrayBuffer paramCharArrayBuffer, Header paramHeader)
  {
    String str1 = paramHeader.getName();
    String str2 = paramHeader.getValue();
    int i = str1.length() + 2;
    if (str2 != null)
      i += str2.length();
    paramCharArrayBuffer.ensureCapacity(i);
    paramCharArrayBuffer.append(str1);
    paramCharArrayBuffer.append(": ");
    if (str2 != null)
      paramCharArrayBuffer.append(str2);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.message.BasicLineFormatter
 * JD-Core Version:    0.6.2
 */