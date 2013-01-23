package com.a.b.b;

public class c extends b
{
  private double a;
  private double b;

  public c(double paramDouble1, double paramDouble2)
  {
    this.a = paramDouble1;
    this.b = paramDouble2;
  }

  public double a()
  {
    return this.a;
  }

  public double c()
  {
    return this.b;
  }

  public b b()
  {
    return new c(this.a, this.b);
  }

  public String toString()
  {
    return String.format("%s {width=%f, height=%f}", new Object[] { c.class.getSimpleName(), Double.valueOf(this.a), Double.valueOf(this.b) });
  }

  public boolean a(b paramb, double paramDouble)
  {
    if ((paramb == null) || (getClass() != paramb.getClass()))
      return false;
    c localc = (c)paramb;
    return (StrictMath.abs(this.a - localc.a) <= paramDouble) && (StrictMath.abs(this.b - localc.b) <= paramDouble);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.a.b.b.c
 * JD-Core Version:    0.6.2
 */