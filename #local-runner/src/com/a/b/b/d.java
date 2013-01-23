package com.a.b.b;

public class d extends b
{
  private double a;
  private double b;
  private double c;
  private double d;

  public d(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    this.a = paramDouble1;
    this.b = paramDouble2;
    this.c = paramDouble3;
    this.d = paramDouble4;
  }

  public double a()
  {
    return this.a;
  }

  public double c()
  {
    return this.b;
  }

  public double d()
  {
    return this.c;
  }

  public double e()
  {
    return this.d;
  }

  public b b()
  {
    return new d(this.a, this.b, this.c, this.d);
  }

  public String toString()
  {
    return String.format("%s {x1=%f, y1=%f, x2=%f, y2=%f}", new Object[] { d.class.getSimpleName(), Double.valueOf(this.a), Double.valueOf(this.b), Double.valueOf(this.c), Double.valueOf(this.d) });
  }

  public boolean a(b paramb, double paramDouble)
  {
    if ((paramb == null) || (getClass() != paramb.getClass()))
      return false;
    d locald = (d)paramb;
    return (StrictMath.abs(this.a - locald.a) <= paramDouble) && (StrictMath.abs(this.b - locald.b) <= paramDouble) && (StrictMath.abs(this.c - locald.c) <= paramDouble) && (StrictMath.abs(this.d - locald.d) <= paramDouble);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.a.b.b.d
 * JD-Core Version:    0.6.2
 */