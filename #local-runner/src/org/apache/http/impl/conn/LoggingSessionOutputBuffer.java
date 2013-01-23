package org.apache.http.impl.conn;

import java.io.IOException;
import java.nio.charset.Charset;
import org.apache.http.Consts;
import org.apache.http.io.HttpTransportMetrics;
import org.apache.http.io.SessionOutputBuffer;
import org.apache.http.util.CharArrayBuffer;

public class LoggingSessionOutputBuffer
  implements SessionOutputBuffer
{
  private final SessionOutputBuffer out;
  private final Wire wire;
  private final String charset;

  public LoggingSessionOutputBuffer(SessionOutputBuffer paramSessionOutputBuffer, Wire paramWire, String paramString)
  {
    this.out = paramSessionOutputBuffer;
    this.wire = paramWire;
    this.charset = (paramString != null ? paramString : Consts.ASCII.name());
  }

  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    this.out.write(paramArrayOfByte, paramInt1, paramInt2);
    if (this.wire.enabled())
      this.wire.output(paramArrayOfByte, paramInt1, paramInt2);
  }

  public void write(int paramInt)
    throws IOException
  {
    this.out.write(paramInt);
    if (this.wire.enabled())
      this.wire.output(paramInt);
  }

  public void flush()
    throws IOException
  {
    this.out.flush();
  }

  public void writeLine(CharArrayBuffer paramCharArrayBuffer)
    throws IOException
  {
    this.out.writeLine(paramCharArrayBuffer);
    if (this.wire.enabled())
    {
      String str1 = new String(paramCharArrayBuffer.buffer(), 0, paramCharArrayBuffer.length());
      String str2 = str1 + "\r\n";
      this.wire.output(str2.getBytes(this.charset));
    }
  }

  public void writeLine(String paramString)
    throws IOException
  {
    this.out.writeLine(paramString);
    if (this.wire.enabled())
    {
      String str = paramString + "\r\n";
      this.wire.output(str.getBytes(this.charset));
    }
  }

  public HttpTransportMetrics getMetrics()
  {
    return this.out.getMetrics();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.conn.LoggingSessionOutputBuffer
 * JD-Core Version:    0.6.2
 */