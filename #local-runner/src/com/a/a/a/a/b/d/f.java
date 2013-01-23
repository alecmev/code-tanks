package com.a.a.a.a.b.d;

import com.a.a.a.a.b.b.i;
import com.a.a.a.a.b.c.d.d;
import com.a.a.g;
import com.a.b.b;
import javax.vecmath.Vector2d;

public class f
  implements com.a.a.a
{
  public void a(com.a.a.c paramc)
  {
    d locald1 = (d)paramc.b();
    b localb1 = locald1.d();
    Vector2d localVector2d1 = localb1.f();
    d locald2 = (d)paramc.c();
    b localb2 = locald2.d();
    Vector2d localVector2d2 = localb2.f();
    double d1 = i.a(0.0D, 0.0D, localVector2d1.x, localVector2d1.y);
    double d2 = i.a(localb1.c(), localb1.d(), localb2.c(), localb2.d());
    double d3 = StrictMath.abs(d1 - d2);
    double d4 = localVector2d1.length() * StrictMath.cos(d3);
    double d5 = i.a(0.0D, 0.0D, localVector2d2.x, localVector2d2.y);
    double d6 = i.a(localb2.c(), localb2.d(), localb1.c(), localb1.d());
    double d7 = StrictMath.abs(d5 - d6);
    double d8 = localVector2d2.length() * StrictMath.cos(d7);
    double d9 = d4 + d8;
    if (d9 > 125.0D)
    {
      int i = Double.valueOf((d9 + d8) * localb2.n() * 0.003D).intValue();
      int j = Double.valueOf((d9 + d4) * localb1.n() * 0.003D).intValue();
      if (i > 0)
        com.a.a.a.a.b.b.f.a(locald1, locald2.a(), i, false);
      if (j > 0)
        com.a.a.a.a.b.b.f.a(locald2, locald1.a(), j, false);
      paramc.a().a(new com.a.a.a.a.b.e.a(com.a.a.a.a.a.c.i, paramc.d().x, paramc.d().y));
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.a.a.a.a.b.d.f
 * JD-Core Version:    0.6.2
 */