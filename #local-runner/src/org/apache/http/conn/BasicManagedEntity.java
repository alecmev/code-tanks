package org.apache.http.conn;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import org.apache.http.HttpEntity;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.util.EntityUtils;

public class BasicManagedEntity extends HttpEntityWrapper
  implements ConnectionReleaseTrigger, EofSensorWatcher
{
  protected ManagedClientConnection managedConn;
  protected final boolean attemptReuse;

  public BasicManagedEntity(HttpEntity paramHttpEntity, ManagedClientConnection paramManagedClientConnection, boolean paramBoolean)
  {
    super(paramHttpEntity);
    if (paramManagedClientConnection == null)
      throw new IllegalArgumentException("Connection may not be null.");
    this.managedConn = paramManagedClientConnection;
    this.attemptReuse = paramBoolean;
  }

  public boolean isRepeatable()
  {
    return false;
  }

  public InputStream getContent()
    throws IOException
  {
    return new EofSensorInputStream(this.wrappedEntity.getContent(), this);
  }

  private void ensureConsumed()
    throws IOException
  {
    if (this.managedConn == null)
      return;
    try
    {
      if (this.attemptReuse)
      {
        EntityUtils.consume(this.wrappedEntity);
        this.managedConn.markReusable();
      }
    }
    finally
    {
      releaseManagedConnection();
    }
  }

  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    super.writeTo(paramOutputStream);
    ensureConsumed();
  }

  public void releaseConnection()
    throws IOException
  {
    ensureConsumed();
  }

  public void abortConnection()
    throws IOException
  {
    if (this.managedConn != null)
      try
      {
        this.managedConn.abortConnection();
      }
      finally
      {
        this.managedConn = null;
      }
  }

  public boolean eofDetected(InputStream paramInputStream)
    throws IOException
  {
    try
    {
      if ((this.attemptReuse) && (this.managedConn != null))
      {
        paramInputStream.close();
        this.managedConn.markReusable();
      }
    }
    finally
    {
      releaseManagedConnection();
    }
    return false;
  }

  public boolean streamClosed(InputStream paramInputStream)
    throws IOException
  {
    try
    {
      if ((this.attemptReuse) && (this.managedConn != null))
      {
        boolean bool = this.managedConn.isOpen();
        try
        {
          paramInputStream.close();
          this.managedConn.markReusable();
        }
        catch (SocketException localSocketException)
        {
          if (bool)
            throw localSocketException;
        }
      }
    }
    finally
    {
      releaseManagedConnection();
    }
    return false;
  }

  public boolean streamAbort(InputStream paramInputStream)
    throws IOException
  {
    if (this.managedConn != null)
      this.managedConn.abortConnection();
    return false;
  }

  protected void releaseManagedConnection()
    throws IOException
  {
    if (this.managedConn != null)
      try
      {
        this.managedConn.releaseConnection();
      }
      finally
      {
        this.managedConn = null;
      }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.conn.BasicManagedEntity
 * JD-Core Version:    0.6.2
 */