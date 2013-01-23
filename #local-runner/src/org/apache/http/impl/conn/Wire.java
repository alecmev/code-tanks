package org.apache.http.impl.conn;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.logging.Log;

public class Wire
{
  private final Log log;

  public Wire(Log paramLog)
  {
    this.log = paramLog;
  }

  private void wire(String paramString, InputStream paramInputStream)
    throws IOException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i;
    while ((i = paramInputStream.read()) != -1)
      if (i == 13)
      {
        localStringBuilder.append("[\\r]");
      }
      else if (i == 10)
      {
        localStringBuilder.append("[\\n]\"");
        localStringBuilder.insert(0, "\"");
        localStringBuilder.insert(0, paramString);
        this.log.debug(localStringBuilder.toString());
        localStringBuilder.setLength(0);
      }
      else if ((i < 32) || (i > 127))
      {
        localStringBuilder.append("[0x");
        localStringBuilder.append(Integer.toHexString(i));
        localStringBuilder.append("]");
      }
      else
      {
        localStringBuilder.append((char)i);
      }
    if (localStringBuilder.length() > 0)
    {
      localStringBuilder.append('"');
      localStringBuilder.insert(0, '"');
      localStringBuilder.insert(0, paramString);
      this.log.debug(localStringBuilder.toString());
    }
  }

  public boolean enabled()
  {
    return this.log.isDebugEnabled();
  }

  public void output(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (paramArrayOfByte == null)
      throw new IllegalArgumentException("Output may not be null");
    wire(">> ", new ByteArrayInputStream(paramArrayOfByte, paramInt1, paramInt2));
  }

  public void input(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (paramArrayOfByte == null)
      throw new IllegalArgumentException("Input may not be null");
    wire("<< ", new ByteArrayInputStream(paramArrayOfByte, paramInt1, paramInt2));
  }

  public void output(byte[] paramArrayOfByte)
    throws IOException
  {
    if (paramArrayOfByte == null)
      throw new IllegalArgumentException("Output may not be null");
    wire(">> ", new ByteArrayInputStream(paramArrayOfByte));
  }

  public void input(byte[] paramArrayOfByte)
    throws IOException
  {
    if (paramArrayOfByte == null)
      throw new IllegalArgumentException("Input may not be null");
    wire("<< ", new ByteArrayInputStream(paramArrayOfByte));
  }

  public void output(int paramInt)
    throws IOException
  {
    output(new byte[] { (byte)paramInt });
  }

  public void input(int paramInt)
    throws IOException
  {
    input(new byte[] { (byte)paramInt });
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.conn.Wire
 * JD-Core Version:    0.6.2
 */