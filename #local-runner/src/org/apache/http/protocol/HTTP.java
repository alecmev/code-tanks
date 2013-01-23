package org.apache.http.protocol;

import java.nio.charset.Charset;
import org.apache.http.Consts;

public final class HTTP
{
  public static final Charset DEF_CONTENT_CHARSET = Consts.ISO_8859_1;
  public static final Charset DEF_PROTOCOL_CHARSET = Consts.ASCII;

  public static boolean isWhitespace(char paramChar)
  {
    return (paramChar == ' ') || (paramChar == '\t') || (paramChar == '\r') || (paramChar == '\n');
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.protocol.HTTP
 * JD-Core Version:    0.6.2
 */