package org.apache.http.conn.routing;

import java.net.InetAddress;
import org.apache.http.HttpHost;
import org.apache.http.util.LangUtils;

public final class HttpRoute
  implements Cloneable, RouteInfo
{
  private static final HttpHost[] EMPTY_HTTP_HOST_ARRAY = new HttpHost[0];
  private final HttpHost targetHost;
  private final InetAddress localAddress;
  private final HttpHost[] proxyChain;
  private final RouteInfo.TunnelType tunnelled;
  private final RouteInfo.LayerType layered;
  private final boolean secure;

  private HttpRoute(InetAddress paramInetAddress, HttpHost paramHttpHost, HttpHost[] paramArrayOfHttpHost, boolean paramBoolean, RouteInfo.TunnelType paramTunnelType, RouteInfo.LayerType paramLayerType)
  {
    if (paramHttpHost == null)
      throw new IllegalArgumentException("Target host may not be null.");
    if (paramArrayOfHttpHost == null)
      throw new IllegalArgumentException("Proxies may not be null.");
    if ((paramTunnelType == RouteInfo.TunnelType.TUNNELLED) && (paramArrayOfHttpHost.length == 0))
      throw new IllegalArgumentException("Proxy required if tunnelled.");
    if (paramTunnelType == null)
      paramTunnelType = RouteInfo.TunnelType.PLAIN;
    if (paramLayerType == null)
      paramLayerType = RouteInfo.LayerType.PLAIN;
    this.targetHost = paramHttpHost;
    this.localAddress = paramInetAddress;
    this.proxyChain = paramArrayOfHttpHost;
    this.secure = paramBoolean;
    this.tunnelled = paramTunnelType;
    this.layered = paramLayerType;
  }

  public HttpRoute(HttpHost paramHttpHost, InetAddress paramInetAddress, HttpHost[] paramArrayOfHttpHost, boolean paramBoolean, RouteInfo.TunnelType paramTunnelType, RouteInfo.LayerType paramLayerType)
  {
    this(paramInetAddress, paramHttpHost, toChain(paramArrayOfHttpHost), paramBoolean, paramTunnelType, paramLayerType);
  }

  public HttpRoute(HttpHost paramHttpHost, InetAddress paramInetAddress, boolean paramBoolean)
  {
    this(paramInetAddress, paramHttpHost, EMPTY_HTTP_HOST_ARRAY, paramBoolean, RouteInfo.TunnelType.PLAIN, RouteInfo.LayerType.PLAIN);
  }

  public HttpRoute(HttpHost paramHttpHost)
  {
    this(null, paramHttpHost, EMPTY_HTTP_HOST_ARRAY, false, RouteInfo.TunnelType.PLAIN, RouteInfo.LayerType.PLAIN);
  }

  public HttpRoute(HttpHost paramHttpHost1, InetAddress paramInetAddress, HttpHost paramHttpHost2, boolean paramBoolean)
  {
    this(paramInetAddress, paramHttpHost1, toChain(paramHttpHost2), paramBoolean, paramBoolean ? RouteInfo.TunnelType.TUNNELLED : RouteInfo.TunnelType.PLAIN, paramBoolean ? RouteInfo.LayerType.LAYERED : RouteInfo.LayerType.PLAIN);
    if (paramHttpHost2 == null)
      throw new IllegalArgumentException("Proxy host may not be null.");
  }

  private static HttpHost[] toChain(HttpHost paramHttpHost)
  {
    if (paramHttpHost == null)
      return EMPTY_HTTP_HOST_ARRAY;
    return new HttpHost[] { paramHttpHost };
  }

  private static HttpHost[] toChain(HttpHost[] paramArrayOfHttpHost)
  {
    if ((paramArrayOfHttpHost == null) || (paramArrayOfHttpHost.length < 1))
      return EMPTY_HTTP_HOST_ARRAY;
    for (HttpHost localHttpHost : paramArrayOfHttpHost)
      if (localHttpHost == null)
        throw new IllegalArgumentException("Proxy chain may not contain null elements.");
    ??? = new HttpHost[paramArrayOfHttpHost.length];
    System.arraycopy(paramArrayOfHttpHost, 0, ???, 0, paramArrayOfHttpHost.length);
    return ???;
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
    return this.proxyChain.length + 1;
  }

  public final HttpHost getHopTarget(int paramInt)
  {
    if (paramInt < 0)
      throw new IllegalArgumentException("Hop index must not be negative: " + paramInt);
    int i = getHopCount();
    if (paramInt >= i)
      throw new IllegalArgumentException("Hop index " + paramInt + " exceeds route length " + i);
    HttpHost localHttpHost = null;
    if (paramInt < i - 1)
      localHttpHost = this.proxyChain[paramInt];
    else
      localHttpHost = this.targetHost;
    return localHttpHost;
  }

  public final HttpHost getProxyHost()
  {
    return this.proxyChain.length == 0 ? null : this.proxyChain[0];
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

  public final boolean equals(Object paramObject)
  {
    if (this == paramObject)
      return true;
    if ((paramObject instanceof HttpRoute))
    {
      HttpRoute localHttpRoute = (HttpRoute)paramObject;
      return (this.secure == localHttpRoute.secure) && (this.tunnelled == localHttpRoute.tunnelled) && (this.layered == localHttpRoute.layered) && (LangUtils.equals(this.targetHost, localHttpRoute.targetHost)) && (LangUtils.equals(this.localAddress, localHttpRoute.localAddress)) && (LangUtils.equals(this.proxyChain, localHttpRoute.proxyChain));
    }
    return false;
  }

  public final int hashCode()
  {
    int i = 17;
    i = LangUtils.hashCode(i, this.targetHost);
    i = LangUtils.hashCode(i, this.localAddress);
    for (int j = 0; j < this.proxyChain.length; j++)
      i = LangUtils.hashCode(i, this.proxyChain[j]);
    i = LangUtils.hashCode(i, this.secure);
    i = LangUtils.hashCode(i, this.tunnelled);
    i = LangUtils.hashCode(i, this.layered);
    return i;
  }

  public final String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(50 + getHopCount() * 30);
    if (this.localAddress != null)
    {
      localStringBuilder.append(this.localAddress);
      localStringBuilder.append("->");
    }
    localStringBuilder.append('{');
    if (this.tunnelled == RouteInfo.TunnelType.TUNNELLED)
      localStringBuilder.append('t');
    if (this.layered == RouteInfo.LayerType.LAYERED)
      localStringBuilder.append('l');
    if (this.secure)
      localStringBuilder.append('s');
    localStringBuilder.append("}->");
    for (HttpHost localHttpHost : this.proxyChain)
    {
      localStringBuilder.append(localHttpHost);
      localStringBuilder.append("->");
    }
    localStringBuilder.append(this.targetHost);
    return localStringBuilder.toString();
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    return super.clone();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.conn.routing.HttpRoute
 * JD-Core Version:    0.6.2
 */