package org.apache.http.conn;

import java.io.IOException;
import java.io.InputStream;

public class EofSensorInputStream extends InputStream
  implements ConnectionReleaseTrigger
{
  protected InputStream wrappedStream;
  private boolean selfClosed;
  private final EofSensorWatcher eofWatcher;

  public EofSensorInputStream(InputStream paramInputStream, EofSensorWatcher paramEofSensorWatcher)
  {
    if (paramInputStream == null)
      throw new IllegalArgumentException("Wrapped stream may not be null.");
    this.wrappedStream = paramInputStream;
    this.selfClosed = false;
    this.eofWatcher = paramEofSensorWatcher;
  }

  protected boolean isReadAllowed()
    throws IOException
  {
    if (this.selfClosed)
      throw new IOException("Attempted read on closed stream.");
    return this.wrappedStream != null;
  }

  public int read()
    throws IOException
  {
    int i = -1;
    if (isReadAllowed())
      try
      {
        i = this.wrappedStream.read();
        checkEOF(i);
      }
      catch (IOException localIOException)
      {
        checkAbort();
        throw localIOException;
      }
    return i;
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    int i = -1;
    if (isReadAllowed())
      try
      {
        i = this.wrappedStream.read(paramArrayOfByte, paramInt1, paramInt2);
        checkEOF(i);
      }
      catch (IOException localIOException)
      {
        checkAbort();
        throw localIOException;
      }
    return i;
  }

  public int read(byte[] paramArrayOfByte)
    throws IOException
  {
    int i = -1;
    if (isReadAllowed())
      try
      {
        i = this.wrappedStream.read(paramArrayOfByte);
        checkEOF(i);
      }
      catch (IOException localIOException)
      {
        checkAbort();
        throw localIOException;
      }
    return i;
  }

  public int available()
    throws IOException
  {
    int i = 0;
    if (isReadAllowed())
      try
      {
        i = this.wrappedStream.available();
      }
      catch (IOException localIOException)
      {
        checkAbort();
        throw localIOException;
      }
    return i;
  }

  public void close()
    throws IOException
  {
    this.selfClosed = true;
    checkClose();
  }

  protected void checkEOF(int paramInt)
    throws IOException
  {
    if ((this.wrappedStream != null) && (paramInt < 0))
      try
      {
        boolean bool = true;
        if (this.eofWatcher != null)
          bool = this.eofWatcher.eofDetected(this.wrappedStream);
        if (bool)
          this.wrappedStream.close();
      }
      finally
      {
        this.wrappedStream = null;
      }
  }

  protected void checkClose()
    throws IOException
  {
    if (this.wrappedStream != null)
      try
      {
        boolean bool = true;
        if (this.eofWatcher != null)
          bool = this.eofWatcher.streamClosed(this.wrappedStream);
        if (bool)
          this.wrappedStream.close();
      }
      finally
      {
        this.wrappedStream = null;
      }
  }

  protected void checkAbort()
    throws IOException
  {
    if (this.wrappedStream != null)
      try
      {
        boolean bool = true;
        if (this.eofWatcher != null)
          bool = this.eofWatcher.streamAbort(this.wrappedStream);
        if (bool)
          this.wrappedStream.close();
      }
      finally
      {
        this.wrappedStream = null;
      }
  }

  public void releaseConnection()
    throws IOException
  {
    close();
  }

  public void abortConnection()
    throws IOException
  {
    this.selfClosed = true;
    checkAbort();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.conn.EofSensorInputStream
 * JD-Core Version:    0.6.2
 */