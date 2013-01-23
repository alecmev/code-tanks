package com.a.a.a.a.b.d;

import com.a.a.a.a.b.b.a;
import com.a.a.a.a.b.b.f;
import com.a.a.a.a.b.b.i;
import com.a.a.a.a.b.c.d.d;
import com.a.a.a.a.b.h;
import com.a.a.g;
import javax.vecmath.Vector2d;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class j
  implements com.a.a.a
{
  private static final Logger a = LoggerFactory.getLogger(j.class);
  private final b.a b;

  public j(b.a parama)
  {
    this.b = parama;
  }

  public void a(com.a.a.c paramc)
  {
    d locald = (d)paramc.b();
    com.a.a.a.a.b.c.c.a locala = (com.a.a.a.a.b.c.c.a)paramc.c();
    if (locald.a().a() == locala.b().a())
      a.warn(String.format("%s hit the %s of the same %s.", new Object[] { locala, locald, locala.b() }));
    double d1 = a(locala.d(), paramc.e());
    if (locala.e() == this.b.a())
      d1 = 0.0D;
    a.debug(String.format("%s hit the %s at an angle of %.2f PI and at a speed of %.1f.", new Object[] { locala, locald, Double.valueOf(d1 / 3.141592653589793D), Double.valueOf(locala.d().f().length()) }));
    com.a.a.a.a.a.c localc;
    if (d1 <= locala.i())
    {
      paramc.a().b(locala);
      double d2 = a(locala);
      double d3 = a(d1, locald, locala, paramc.d());
      int i = d2 >= d3 ? 1 : 0;
      if (i != 0)
      {
        f.a(locald, locala, true);
        localc = com.a.a.a.a.b.b.a.b(locala);
        a.debug(String.format("%s pierced armor of the %s (%.1f vs. %.1f).", new Object[] { locala, locald, Double.valueOf(d2), Double.valueOf(d3) }));
      }
      else
      {
        f.a(locald, locala, false);
        localc = com.a.a.a.a.b.b.a.a(locala);
        a.debug(String.format("%s struck the %s (%.1f vs. %.1f).", new Object[] { locala, locald, Double.valueOf(d2), Double.valueOf(d3) }));
      }
      if (!f.a(locald));
    }
    else
    {
      localc = com.a.a.a.a.b.b.a.c(locala);
      a.debug(String.format("%s ricocheted from the %s.", new Object[] { locala, locald }));
    }
    paramc.a().a(new com.a.a.a.a.b.e.a(localc, paramc.d().x, paramc.d().y)).a(locala.d().e());
  }

  private static double a(com.a.a.a.a.b.c.c.a parama)
  {
    double d = parama.g() + parama.d().f().length() * parama.f();
    int i = com.a.c.a.a.b.a(41) - 20;
    return d * (100.0D + i) / 100.0D;
  }

  private double a(double paramDouble, d paramd, com.a.a.a.a.b.c.c.a parama, Vector2d paramVector2d)
  {
    Vector2d localVector2d = parama.e() == this.b.a() ? new Vector2d(parama.a().d().c(), parama.a().d().d()) : paramVector2d;
    double d1 = b(paramd.d(), localVector2d);
    com.a.b.b.b localb = paramd.d().o();
    int i;
    if ((localb instanceof com.a.b.b.c))
    {
      com.a.b.b.c localc = (com.a.b.b.c)localb;
      double d3 = StrictMath.atan(localc.c() / localc.a());
      double d4 = 3.141592653589793D - d3;
      if (d1 < d3)
        i = paramd.n();
      else if (d1 < d4)
        i = paramd.o();
      else
        i = paramd.p();
    }
    else
    {
      throw new IllegalArgumentException("Unsupported tank form: " + localb + '.');
    }
    double d2 = StrictMath.max(0.0D, paramDouble - parama.j());
    return i / StrictMath.cos(d2);
  }

  private static double a(com.a.b.b paramb, Vector2d paramVector2d)
  {
    double d1 = i.a(0.0D, 0.0D, paramVector2d.x, paramVector2d.y);
    double d2 = i.a(0.0D, 0.0D, paramb.f().x, paramb.f().y);
    for (double d3 = StrictMath.abs(d1 - d2); d3 > 3.141592653589793D; d3 -= 3.141592653589793D);
    if (d3 > 1.570796326794897D)
      d3 = 3.141592653589793D - d3;
    return d3;
  }

  private static double b(com.a.b.b paramb, Vector2d paramVector2d)
  {
    double d1 = i.a(paramb.c(), paramb.d(), paramVector2d.x, paramVector2d.y);
    for (double d2 = StrictMath.abs(d1 - paramb.e()); d2 > 3.141592653589793D; d2 -= 3.141592653589793D);
    return d2;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.a.a.a.a.b.d.j
 * JD-Core Version:    0.6.2
 */