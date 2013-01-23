package com.a.a.a.a.b.c.c;

import com.a.a.a.a.b.h;
import com.a.c.a.a.c;
import javax.vecmath.Vector2d;

public abstract class a extends com.a.a.d
{
  private final com.a.a.a.a.b.c.d.d a;
  private final h b;
  private final int c;
  private final double d;
  private final int e;
  private final int f;
  private final double g;
  private final double h;

  protected a(int paramInt1, com.a.a.a.a.b.c.d.d paramd, com.a.b.b.b paramb, double paramDouble1, double paramDouble2, double paramDouble3, int paramInt2, int paramInt3, double paramDouble4, double paramDouble5)
  {
    super(paramb);
    this.a = paramd;
    this.b = paramd.a();
    this.c = paramInt1;
    Vector2d localVector2d = c.a(new Vector2d(paramd.g(), 0.0D), paramd.d().e() + paramd.e());
    d().a(paramd.d().c() + localVector2d.x);
    d().b(paramd.d().d() + localVector2d.y);
    d().c(paramd.d().e() + paramd.e());
    d().i(paramDouble1);
    d().a(c.a(new Vector2d(paramDouble2, 0.0D), paramd.d().e() + paramd.e()));
    this.d = paramDouble3;
    this.e = paramInt2;
    this.f = paramInt3;
    this.g = paramDouble4;
    this.h = paramDouble5;
    d().e(0.005D);
    d().f(paramDouble1 * 5.0D);
    d().g(0.5D);
    d().h(0.1D);
  }

  public com.a.a.a.a.b.c.d.d a()
  {
    return this.a;
  }

  public h b()
  {
    return this.b;
  }

  public int e()
  {
    return this.c;
  }

  public double f()
  {
    return this.d;
  }

  public int g()
  {
    return this.e;
  }

  public int h()
  {
    return this.f;
  }

  public double i()
  {
    return this.g;
  }

  public double j()
  {
    return this.h;
  }

  public String toString()
  {
    return String.format("%s {id=%d, player.name='%s'}", new Object[] { getClass().getSimpleName(), Long.valueOf(c()), this.b.b() });
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.a.a.a.a.b.c.c.a
 * JD-Core Version:    0.6.2
 */