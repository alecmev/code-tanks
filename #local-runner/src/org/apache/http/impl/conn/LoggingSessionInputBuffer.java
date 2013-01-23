package org.apache.http.impl.conn;

import java.io.IOException;
import java.nio.charset.Charset;
import org.apache.http.Consts;
import org.apache.http.io.EofSensor;
import org.apache.http.io.HttpTransportMetrics;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.util.CharArrayBuffer;

public class LoggingSessionInputBuffer
  implements EofSensor, SessionInputBuffer
{
  private final SessionInputBuffer in;
  private final EofSensor eofSensor;
  private final Wire wire;
  private final String charset;

  public LoggingSessionInputBuffer(SessionInputBuffer paramSessionInputBuffer, Wire paramWire, String paramString)
  {
    this.in = paramSessionInputBuffer;
    this.eofSensor = ((paramSessionInputBuffer instanceof SessionInputBuffer) ? (SessionInputBuffer)paramSessionInputBuffer : null);
    this.wire = paramWire;
    this.charset = (paramString != null ? paramString : Consts.ASCII.name());
  }

  public boolean isDataAvailable(int paramInt)
    throws IOException
  {
    return this.in.isDataAvailable(paramInt);
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    int i = this.in.read(paramArrayOfByte, paramInt1, paramInt2);
    if ((this.wire.enabled()) && (i > 0))
      this.wire.input(paramArrayOfByte, paramInt1, i);
    return i;
  }

  public int read()
    throws IOException
  {
    int i = this.in.read();
    if ((this.wire.enabled()) && (i != -1))
      this.wire.input(i);
    return i;
  }

  public int readLine(CharArrayBuffer paramCharArrayBuffer)
    throws IOException
  {
    int i = this.in.readLine(paramCharArrayBuffer);
    if ((this.wire.enabled()) && (i >= 0))
    {
      int j = paramCharArrayBuffer.length() - i;
      String str1 = new String(paramCharArrayBuffer.buffer(), j, i);
      String str2 = str1 + "\r\n";
      this.wire.input(str2.getBytes(this.charset));
    }
    return i;
  }

  public HttpTransportMetrics getMetrics()
  {
    return this.in.getMetrics();
  }

  public boolean isEof()
  {
    if (this.eofSensor != null)
      return this.eofSensor.isEof();
    return false;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.conn.LoggingSessionInputBuffer
 * JD-Core Version:    0.6.2
 */