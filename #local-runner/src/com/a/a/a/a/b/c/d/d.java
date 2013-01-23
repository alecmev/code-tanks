package com.a.a.a.a.b.c.d;

import com.a.a.a.a.b.h;

public abstract class d extends com.a.a.d
{
  private final h a;
  private final int b;
  private double c;
  private double d;
  private double e;
  private double f;
  private double g;
  private double h;
  private int i;
  private int j;
  private int k;
  private int l;
  private int m;
  private int n;
  private int o;
  private Integer p;
  private int q;
  private int r;

  protected d(h paramh, int paramInt1, double paramDouble1, double paramDouble2, double paramDouble3, com.a.b.b.b paramb, double paramDouble4, double paramDouble5, double paramDouble6, double paramDouble7, double paramDouble8, double paramDouble9, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9)
  {
    super(paramb);
    this.a = paramh;
    this.b = paramInt1;
    d().a(paramDouble1);
    d().b(paramDouble2);
    d().c(paramDouble3);
    d().i(paramDouble4);
    this.e = paramDouble5;
    this.d = paramDouble6;
    this.f = paramDouble7;
    this.g = paramDouble8;
    this.h = paramDouble9;
    this.i = paramInt2;
    this.j = paramInt3;
    this.k = paramInt4;
    this.l = paramInt5;
    this.m = paramInt6;
    this.n = paramInt7;
    this.o = paramInt8;
    this.q = paramInt9;
    this.r = 0;
    d().e(0.5D);
    d().f(paramDouble4 * 20.0D);
    d().g(0.65D);
    d().h(0.75D);
  }

  public h a()
  {
    return this.a;
  }

  public int b()
  {
    return this.b;
  }

  public double e()
  {
    t();
    return this.c;
  }

  public void a(double paramDouble)
  {
    this.c = paramDouble;
    t();
  }

  public double f()
  {
    return this.e;
  }

  public double g()
  {
    return this.f;
  }

  public double h()
  {
    return this.g;
  }

  public double i()
  {
    return this.h;
  }

  public int j()
  {
    return this.i;
  }

  public void a(int paramInt)
  {
    this.i = paramInt;
  }

  public int k()
  {
    return this.j;
  }

  public int l()
  {
    return this.k;
  }

  public void b(int paramInt)
  {
    this.k = paramInt;
  }

  public int m()
  {
    return this.l;
  }

  public int n()
  {
    return this.m;
  }

  public int o()
  {
    return this.n;
  }

  public int p()
  {
    return this.o;
  }

  public Integer q()
  {
    return this.p;
  }

  public void a(Integer paramInteger)
  {
    this.p = paramInteger;
  }

  public int r()
  {
    return this.q;
  }

  public int s()
  {
    return this.r;
  }

  public void c(int paramInt)
  {
    this.r = paramInt;
  }

  private void t()
  {
    while (this.c > 3.141592653589793D)
      this.c -= 6.283185307179586D;
    while (this.c < -3.141592653589793D)
      this.c += 6.283185307179586D;
    if (this.c > this.d)
      this.c = this.d;
    if (this.c < -this.d)
      this.c = (-this.d);
  }

  public String toString()
  {
    return String.format("%s {id=%d, player.name='%s', crewHealth=%d, hullDurability=%d}", new Object[] { getClass().getSimpleName(), Long.valueOf(c()), this.a.b(), Integer.valueOf(this.i), Integer.valueOf(this.k) });
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.a.a.a.a.b.c.d.d
 * JD-Core Version:    0.6.2
 */