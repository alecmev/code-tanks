package org.apache.http.impl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import org.apache.http.HttpInetConnection;
import org.apache.http.impl.io.SocketInputBuffer;
import org.apache.http.impl.io.SocketOutputBuffer;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.io.SessionOutputBuffer;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class SocketHttpClientConnection extends AbstractHttpClientConnection
  implements HttpInetConnection
{
  private volatile boolean open;
  private volatile Socket socket = null;

  protected void assertNotOpen()
  {
    if (this.open)
      throw new IllegalStateException("Connection is already open");
  }

  protected void assertOpen()
  {
    if (!this.open)
      throw new IllegalStateException("Connection is not open");
  }

  protected SessionInputBuffer createSessionInputBuffer(Socket paramSocket, int paramInt, HttpParams paramHttpParams)
    throws IOException
  {
    return new SocketInputBuffer(paramSocket, paramInt, paramHttpParams);
  }

  protected SessionOutputBuffer createSessionOutputBuffer(Socket paramSocket, int paramInt, HttpParams paramHttpParams)
    throws IOException
  {
    return new SocketOutputBuffer(paramSocket, paramInt, paramHttpParams);
  }

  protected void bind(Socket paramSocket, HttpParams paramHttpParams)
    throws IOException
  {
    if (paramSocket == null)
      throw new IllegalArgumentException("Socket may not be null");
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    this.socket = paramSocket;
    int i = HttpConnectionParams.getSocketBufferSize(paramHttpParams);
    init(createSessionInputBuffer(paramSocket, i, paramHttpParams), createSessionOutputBuffer(paramSocket, i, paramHttpParams), paramHttpParams);
    this.open = true;
  }

  public boolean isOpen()
  {
    return this.open;
  }

  protected Socket getSocket()
  {
    return this.socket;
  }

  public InetAddress getRemoteAddress()
  {
    if (this.socket != null)
      return this.socket.getInetAddress();
    return null;
  }

  public int getRemotePort()
  {
    if (this.socket != null)
      return this.socket.getPort();
    return -1;
  }

  public void setSocketTimeout(int paramInt)
  {
    assertOpen();
    if (this.socket != null)
      try
      {
        this.socket.setSoTimeout(paramInt);
      }
      catch (SocketException localSocketException)
      {
      }
  }

  public void shutdown()
    throws IOException
  {
    this.open = false;
    Socket localSocket = this.socket;
    if (localSocket != null)
      localSocket.close();
  }

  public void close()
    throws IOException
  {
    if (!this.open)
      return;
    this.open = false;
    Socket localSocket = this.socket;
    try
    {
      doFlush();
      try
      {
        try
        {
        }
        catch (IOException localIOException1)
        {
        }
        try
        {
        }
        catch (IOException localIOException2)
        {
        }
      }
      catch (UnsupportedOperationException localUnsupportedOperationException)
      {
      }
    }
    finally
    {
      localSocket.close();
    }
  }

  private static void formatAddress(StringBuilder paramStringBuilder, SocketAddress paramSocketAddress)
  {
    if ((paramSocketAddress instanceof InetSocketAddress))
    {
      InetSocketAddress localInetSocketAddress = (InetSocketAddress)paramSocketAddress;
      paramStringBuilder.append(localInetSocketAddress.getAddress() != null ? localInetSocketAddress.getAddress().getHostAddress() : localInetSocketAddress.getAddress()).append(':').append(localInetSocketAddress.getPort());
    }
    else
    {
      paramStringBuilder.append(paramSocketAddress);
    }
  }

  public String toString()
  {
    if (this.socket != null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      SocketAddress localSocketAddress1 = this.socket.getRemoteSocketAddress();
      SocketAddress localSocketAddress2 = this.socket.getLocalSocketAddress();
      if ((localSocketAddress1 != null) && (localSocketAddress2 != null))
      {
        formatAddress(localStringBuilder, localSocketAddress2);
        localStringBuilder.append("<->");
        formatAddress(localStringBuilder, localSocketAddress1);
      }
      return localStringBuilder.toString();
    }
    return super.toString();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.SocketHttpClientConnection
 * JD-Core Version:    0.6.2
 */