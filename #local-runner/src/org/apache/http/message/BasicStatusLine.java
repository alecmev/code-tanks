package org.apache.http.message;

import java.io.Serializable;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.util.CharArrayBuffer;

public class BasicStatusLine
  implements Serializable, Cloneable, StatusLine
{
  private final ProtocolVersion protoVersion;
  private final int statusCode;
  private final String reasonPhrase;

  public BasicStatusLine(ProtocolVersion paramProtocolVersion, int paramInt, String paramString)
  {
    if (paramProtocolVersion == null)
      throw new IllegalArgumentException("Protocol version may not be null.");
    if (paramInt < 0)
      throw new IllegalArgumentException("Status code may not be negative.");
    this.protoVersion = paramProtocolVersion;
    this.statusCode = paramInt;
    this.reasonPhrase = paramString;
  }

  public int getStatusCode()
  {
    return this.statusCode;
  }

  public ProtocolVersion getProtocolVersion()
  {
    return this.protoVersion;
  }

  public String getReasonPhrase()
  {
    return this.reasonPhrase;
  }

  public String toString()
  {
    return BasicLineFormatter.DEFAULT.formatStatusLine(null, this).toString();
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    return super.clone();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.message.BasicStatusLine
 * JD-Core Version:    0.6.2
 */