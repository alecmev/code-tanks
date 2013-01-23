package com.a.b.b;

public class a extends b
{
  private double a;

  public a(double paramDouble)
  {
    this.a = paramDouble;
  }

  public double a()
  {
    return this.a;
  }

  public b b()
  {
    return new a(this.a);
  }

  public String toString()
  {
    return String.format("%s {radius=%f}", new Object[] { a.class.getSimpleName(), Double.valueOf(this.a) });
  }

  public boolean a(b paramb, double paramDouble)
  {
    if ((paramb == null) || (getClass() != paramb.getClass()))
      return false;
    a locala = (a)paramb;
    return StrictMath.abs(this.a - locala.a) <= paramDouble;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.a.b.b.a
 * JD-Core Version:    0.6.2
 */