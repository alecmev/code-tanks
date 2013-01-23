package org.apache.http.conn.routing;

import java.net.InetAddress;
import org.apache.http.HttpHost;

public abstract interface RouteInfo
{
  public abstract HttpHost getTargetHost();

  public abstract InetAddress getLocalAddress();

  public abstract int getHopCount();

  public abstract HttpHost getHopTarget(int paramInt);

  public abstract boolean isTunnelled();

  public abstract boolean isLayered();

  public abstract boolean isSecure();

  public static enum LayerType
  {
    PLAIN, LAYERED;
  }

  public static enum TunnelType
  {
    PLAIN, TUNNELLED;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.conn.routing.RouteInfo
 * JD-Core Version:    0.6.2
 */