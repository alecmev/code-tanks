package org.apache.http.conn.routing;

import java.net.InetAddress;
import org.apache.http.HttpHost;
import org.apache.http.util.LangUtils;

public final class RouteTracker
  implements Cloneable, RouteInfo
{
  private final HttpHost targetHost;
  private final InetAddress localAddress;
  private boolean connected;
  private HttpHost[] proxyChain;
  private RouteInfo.TunnelType tunnelled;
  private RouteInfo.LayerType layered;
  private boolean secure;

  public RouteTracker(HttpHost paramHttpHost, InetAddress paramInetAddress)
  {
    if (paramHttpHost == null)
      throw new IllegalArgumentException("Target host may not be null.");
    this.targetHost = paramHttpHost;
    this.localAddress = paramInetAddress;
    this.tunnelled = RouteInfo.TunnelType.PLAIN;
    this.layered = RouteInfo.LayerType.PLAIN;
  }

  public void reset()
  {
    this.connected = false;
    this.proxyChain = null;
    this.tunnelled = RouteInfo.TunnelType.PLAIN;
    this.layered = RouteInfo.LayerType.PLAIN;
    this.secure = false;
  }

  public RouteTracker(HttpRoute paramHttpRoute)
  {
    this(paramHttpRoute.getTargetHost(), paramHttpRoute.getLocalAddress());
  }

  public final void connectTarget(boolean paramBoolean)
  {
    if (this.connected)
      throw new IllegalStateException("Already connected.");
    this.connected = true;
    this.secure = paramBoolean;
  }

  public final void connectProxy(HttpHost paramHttpHost, boolean paramBoolean)
  {
    if (paramHttpHost == null)
      throw new IllegalArgumentException("Proxy host may not be null.");
    if (this.connected)
      throw new IllegalStateException("Already connected.");
    this.connected = true;
    this.proxyChain = new HttpHost[] { paramHttpHost };
    this.secure = paramBoolean;
  }

  public final void tunnelTarget(boolean paramBoolean)
  {
    if (!this.connected)
      throw new IllegalStateException("No tunnel unless connected.");
    if (this.proxyChain == null)
      throw new IllegalStateException("No tunnel without proxy.");
    this.tunnelled = RouteInfo.TunnelType.TUNNELLED;
    this.secure = paramBoolean;
  }

  public final void tunnelProxy(HttpHost paramHttpHost, boolean paramBoolean)
  {
    if (paramHttpHost == null)
      throw new IllegalArgumentException("Proxy host may not be null.");
    if (!this.connected)
      throw new IllegalStateException("No tunnel unless connected.");
    if (this.proxyChain == null)
      throw new IllegalStateException("No proxy tunnel without proxy.");
    HttpHost[] arrayOfHttpHost = new HttpHost[this.proxyChain.length + 1];
    System.arraycopy(this.proxyChain, 0, arrayOfHttpHost, 0, this.proxyChain.length);
    arrayOfHttpHost[(arrayOfHttpHost.length - 1)] = paramHttpHost;
    this.proxyChain = arrayOfHttpHost;
    this.secure = paramBoolean;
  }

  public final void layerProtocol(boolean paramBoolean)
  {
    if (!this.connected)
      throw new IllegalStateException("No layered protocol unless connected.");
    this.layered = RouteInfo.LayerType.LAYERED;
    this.secure = paramBoolean;
  }

  public final HttpHost getTargetHost()
  {
    return this.targetHost;
  }

  public final InetAddress getLocalAddress()
  {
    return this.localAddress;
  }

  public final int getHopCount()
  {
    int i = 0;
    if (this.connected)
      if (this.proxyChain == null)
        i = 1;
      else
        i = this.proxyChain.length + 1;
    return i;
  }

  public final HttpHost getHopTarget(int paramInt)
  {
    if (paramInt < 0)
      throw new IllegalArgumentException("Hop index must not be negative: " + paramInt);
    int i = getHopCount();
    if (paramInt >= i)
      throw new IllegalArgumentException("Hop index " + paramInt + " exceeds tracked route length " + i + ".");
    HttpHost localHttpHost = null;
    if (paramInt < i - 1)
      localHttpHost = this.proxyChain[paramInt];
    else
      localHttpHost = this.targetHost;
    return localHttpHost;
  }

  public final boolean isConnected()
  {
    return this.connected;
  }

  public final boolean isTunnelled()
  {
    return this.tunnelled == RouteInfo.TunnelType.TUNNELLED;
  }

  public final boolean isLayered()
  {
    return this.layered == RouteInfo.LayerType.LAYERED;
  }

  public final boolean isSecure()
  {
    return this.secure;
  }

  public final HttpRoute toRoute()
  {
    return !this.connected ? null : new HttpRoute(this.targetHost, this.localAddress, this.proxyChain, this.secure, this.tunnelled, this.layered);
  }

  public final boolean equals(Object paramObject)
  {
    if (paramObject == this)
      return true;
    if (!(paramObject instanceof RouteTracker))
      return false;
    RouteTracker localRouteTracker = (RouteTracker)paramObject;
    return (this.connected == localRouteTracker.connected) && (this.secure == localRouteTracker.secure) && (this.tunnelled == localRouteTracker.tunnelled) && (this.layered == localRouteTracker.layered) && (LangUtils.equals(this.targetHost, localRouteTracker.targetHost)) && (LangUtils.equals(this.localAddress, localRouteTracker.localAddress)) && (LangUtils.equals(this.proxyChain, localRouteTracker.proxyChain));
  }

  public final int hashCode()
  {
    int i = 17;
    i = LangUtils.hashCode(i, this.targetHost);
    i = LangUtils.hashCode(i, this.localAddress);
    if (this.proxyChain != null)
      for (int j = 0; j < this.proxyChain.length; j++)
        i = LangUtils.hashCode(i, this.proxyChain[j]);
    i = LangUtils.hashCode(i, this.connected);
    i = LangUtils.hashCode(i, this.secure);
    i = LangUtils.hashCode(i, this.tunnelled);
    i = LangUtils.hashCode(i, this.layered);
    return i;
  }

  public final String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(50 + getHopCount() * 30);
    localStringBuilder.append("RouteTracker[");
    if (this.localAddress != null)
    {
      localStringBuilder.append(this.localAddress);
      localStringBuilder.append("->");
    }
    localStringBuilder.append('{');
    if (this.connected)
      localStringBuilder.append('c');
    if (this.tunnelled == RouteInfo.TunnelType.TUNNELLED)
      localStringBuilder.append('t');
    if (this.layered == RouteInfo.LayerType.LAYERED)
      localStringBuilder.append('l');
    if (this.secure)
      localStringBuilder.append('s');
    localStringBuilder.append("}->");
    if (this.proxyChain != null)
      for (int i = 0; i < this.proxyChain.length; i++)
      {
        localStringBuilder.append(this.proxyChain[i]);
        localStringBuilder.append("->");
      }
    localStringBuilder.append(this.targetHost);
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    return super.clone();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.conn.routing.RouteTracker
 * JD-Core Version:    0.6.2
 */