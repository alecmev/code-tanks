package com.a.a.a.a.b.a;

import com.a.a.a.a.b.b.i;
import com.a.a.d;
import com.a.a.f;
import com.a.a.g;
import java.util.HashSet;
import java.util.Set;
import javax.vecmath.Vector2d;

public abstract class a
  implements f
{
  private final double a;
  private final double b;
  private final double c;
  private final double d;
  private final a e;
  private final Set f = new HashSet();

  protected a(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, int paramInt, double paramDouble5, a parama)
  {
    this.a = paramDouble1;
    this.b = paramDouble2;
    this.c = paramDouble3;
    this.d = paramDouble4;
    this.e = parama;
    int i = (int)(paramDouble5 * paramInt / 1000.0D);
    while (this.f.size() < i)
      this.f.add(Integer.valueOf(com.a.c.a.a.b.a(paramInt)));
  }

  public void a(g paramg, int paramInt)
  {
    if (this.f.contains(Integer.valueOf(paramInt)))
    {
      com.a.a.a.a.b.c.b.a locala = this.e.b();
      Vector2d localVector2d = a(locala, paramg);
      locala.d().a(localVector2d.x);
      locala.d().b(localVector2d.y);
      paramg.a(locala);
    }
  }

  private Vector2d a(d paramd, g paramg)
  {
    double d1 = i.a(paramd.d().o());
    double d2;
    double d3;
    do
    {
      d2 = this.a + d1 + (this.c - this.a - 2.0D * d1) * com.a.c.a.a.b.b();
      d3 = this.b + d1 + (this.d - this.b - 2.0D * d1) * com.a.c.a.a.b.b();
    }
    while (!i.a(d2, d3, d1, paramg));
    return new Vector2d(d2, d3);
  }

  public static abstract interface a
  {
    public abstract com.a.a.a.a.b.c.b.a b();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.a.a.a.a.b.a.a
 * JD-Core Version:    0.6.2
 */