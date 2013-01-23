package org.apache.http.impl.io;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import org.apache.http.io.EofSensor;
import org.apache.http.params.HttpParams;

public class SocketInputBuffer extends AbstractSessionInputBuffer
  implements EofSensor
{
  private final Socket socket;
  private boolean eof;

  public SocketInputBuffer(Socket paramSocket, int paramInt, HttpParams paramHttpParams)
    throws IOException
  {
    if (paramSocket == null)
      throw new IllegalArgumentException("Socket may not be null");
    this.socket = paramSocket;
    this.eof = false;
    if (paramInt < 0)
      paramInt = paramSocket.getReceiveBufferSize();
    if (paramInt < 1024)
      paramInt = 1024;
    init(paramSocket.getInputStream(), paramInt, paramHttpParams);
  }

  protected int fillBuffer()
    throws IOException
  {
    int i = super.fillBuffer();
    this.eof = (i == -1);
    return i;
  }

  public boolean isDataAvailable(int paramInt)
    throws IOException
  {
    boolean bool = hasBufferedData();
    if (!bool)
    {
      int i = this.socket.getSoTimeout();
      try
      {
        this.socket.setSoTimeout(paramInt);
        fillBuffer();
        bool = hasBufferedData();
      }
      catch (SocketTimeoutException localSocketTimeoutException)
      {
        throw localSocketTimeoutException;
      }
      finally
      {
        this.socket.setSoTimeout(i);
      }
    }
    return bool;
  }

  public boolean isEof()
  {
    return this.eof;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.io.SocketInputBuffer
 * JD-Core Version:    0.6.2
 */