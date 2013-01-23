package org.apache.http.conn.routing;

import java.net.InetAddress;
import org.apache.http.HttpHost;

public class BasicRouteDirector
  implements HttpRouteDirector
{
  public int nextStep(RouteInfo paramRouteInfo1, RouteInfo paramRouteInfo2)
  {
    if (paramRouteInfo1 == null)
      throw new IllegalArgumentException("Planned route may not be null.");
    int i = -1;
    if ((paramRouteInfo2 == null) || (paramRouteInfo2.getHopCount() < 1))
      i = firstStep(paramRouteInfo1);
    else if (paramRouteInfo1.getHopCount() > 1)
      i = proxiedStep(paramRouteInfo1, paramRouteInfo2);
    else
      i = directStep(paramRouteInfo1, paramRouteInfo2);
    return i;
  }

  protected int firstStep(RouteInfo paramRouteInfo)
  {
    return paramRouteInfo.getHopCount() > 1 ? 2 : 1;
  }

  protected int directStep(RouteInfo paramRouteInfo1, RouteInfo paramRouteInfo2)
  {
    if (paramRouteInfo2.getHopCount() > 1)
      return -1;
    if (!paramRouteInfo1.getTargetHost().equals(paramRouteInfo2.getTargetHost()))
      return -1;
    if (paramRouteInfo1.isSecure() != paramRouteInfo2.isSecure())
      return -1;
    if ((paramRouteInfo1.getLocalAddress() != null) && (!paramRouteInfo1.getLocalAddress().equals(paramRouteInfo2.getLocalAddress())))
      return -1;
    return 0;
  }

  protected int proxiedStep(RouteInfo paramRouteInfo1, RouteInfo paramRouteInfo2)
  {
    if (paramRouteInfo2.getHopCount() <= 1)
      return -1;
    if (!paramRouteInfo1.getTargetHost().equals(paramRouteInfo2.getTargetHost()))
      return -1;
    int i = paramRouteInfo1.getHopCount();
    int j = paramRouteInfo2.getHopCount();
    if (i < j)
      return -1;
    for (int k = 0; k < j - 1; k++)
      if (!paramRouteInfo1.getHopTarget(k).equals(paramRouteInfo2.getHopTarget(k)))
        return -1;
    if (i > j)
      return 4;
    if (((paramRouteInfo2.isTunnelled()) && (!paramRouteInfo1.isTunnelled())) || ((paramRouteInfo2.isLayered()) && (!paramRouteInfo1.isLayered())))
      return -1;
    if ((paramRouteInfo1.isTunnelled()) && (!paramRouteInfo2.isTunnelled()))
      return 3;
    if ((paramRouteInfo1.isLayered()) && (!paramRouteInfo2.isLayered()))
      return 5;
    if (paramRouteInfo1.isSecure() != paramRouteInfo2.isSecure())
      return -1;
    return 0;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.conn.routing.BasicRouteDirector
 * JD-Core Version:    0.6.2
 */