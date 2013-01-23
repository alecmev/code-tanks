package com.a.a.a.a.b.d;

import com.a.a.a.a.b.c.d.d;
import com.a.a.g;
import javax.vecmath.Vector2d;

public class i
  implements com.a.a.a
{
  public void a(com.a.a.c paramc)
  {
    d locald = (d)paramc.b();
    com.a.b.b localb1 = locald.d();
    Vector2d localVector2d = localb1.f();
    com.a.a.a.a.b.c.e.b localb = (com.a.a.a.a.b.c.e.b)paramc.c();
    com.a.b.b localb2 = localb.d();
    double d1 = com.a.a.a.a.b.b.i.a(0.0D, 0.0D, localVector2d.x, localVector2d.y);
    double d2 = com.a.a.a.a.b.b.i.a(localb1.c(), localb1.d(), localb2.c(), localb2.d());
    double d3 = StrictMath.abs(d1 - d2);
    double d4 = localVector2d.length() * StrictMath.cos(d3);
    if (d4 > 100.0D)
      paramc.a().a(new com.a.a.a.a.b.e.a(com.a.a.a.a.a.c.j, paramc.d().x, paramc.d().y));
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.a.a.a.a.b.d.i
 * JD-Core Version:    0.6.2
 */