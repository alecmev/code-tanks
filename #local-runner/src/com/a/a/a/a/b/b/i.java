package com.a.a.a.a.b.b;

import com.a.a.g;
import com.a.b.b.c;
import java.util.Iterator;
import java.util.List;

public final class i
{
  private i()
  {
    throw new UnsupportedOperationException();
  }

  public static boolean a(double paramDouble1, double paramDouble2, double paramDouble3, g paramg)
  {
    Iterator localIterator = paramg.a().iterator();
    while (localIterator.hasNext())
    {
      com.a.a.d locald = (com.a.a.d)localIterator.next();
      if (((locald instanceof com.a.a.a.a.b.c.e.b)) || ((locald instanceof com.a.a.a.a.b.c.d.d)) || ((locald instanceof com.a.a.a.a.b.c.b.a)) || ((locald instanceof com.a.a.a.a.b.c.c.a)))
      {
        double d = StrictMath.hypot(locald.d().c() - paramDouble1, locald.d().d() - paramDouble2);
        if (d < paramDouble3 + a(locald.d().o()))
          return false;
      }
      else if (!(locald instanceof com.a.a.a.a.b.c.a.a))
      {
        throw new IllegalArgumentException("Unsupported unit class: " + locald.getClass() + '.');
      }
    }
    return true;
  }

  public static double a(com.a.b.b.b paramb)
  {
    if ((paramb instanceof c))
    {
      c localc = (c)paramb;
      return 0.5D * StrictMath.hypot(localc.a(), localc.c());
    }
    if ((paramb instanceof com.a.b.b.a))
      return ((com.a.b.b.a)paramb).a();
    throw new IllegalArgumentException("Unsupported form: " + paramb + '.');
  }

  public static double a(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    return StrictMath.atan2(paramDouble4 - paramDouble2, paramDouble3 - paramDouble1);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.a.a.a.a.b.b.i
 * JD-Core Version:    0.6.2
 */