package com.a.a.a.a.a;

import com.google.gson.annotations.Until;
import java.util.Arrays;

public class n
{
  private final int a;
  private final Boolean b;

  @Until(1.0D)
  private final double c;

  @Until(1.0D)
  private final double d;
  private final i[] e;
  private final a[] f;
  private final o[] g;
  private final f[] h;
  private final k[] i;

  public n(int paramInt, boolean paramBoolean, double paramDouble1, double paramDouble2, i[] paramArrayOfi, a[] paramArrayOfa, o[] paramArrayOfo, f[] paramArrayOff, k[] paramArrayOfk)
  {
    this.a = paramInt;
    this.b = (paramBoolean ? Boolean.valueOf(true) : null);
    this.c = paramDouble1;
    this.d = paramDouble2;
    this.e = ((i[])Arrays.copyOf(paramArrayOfi, paramArrayOfi.length));
    this.f = ((a[])Arrays.copyOf(paramArrayOfa, paramArrayOfa.length));
    this.g = ((o[])Arrays.copyOf(paramArrayOfo, paramArrayOfo.length));
    this.h = ((f[])Arrays.copyOf(paramArrayOff, paramArrayOff.length));
    this.i = ((k[])Arrays.copyOf(paramArrayOfk, paramArrayOfk.length));
  }

  public int b()
  {
    return this.a;
  }

  public boolean c()
  {
    return this.b == null ? false : this.b.booleanValue();
  }

  public double d()
  {
    return this.c;
  }

  public double e()
  {
    return this.d;
  }

  public i[] f()
  {
    return (i[])Arrays.copyOf(this.e, this.e.length);
  }

  public a[] g()
  {
    return (a[])Arrays.copyOf(this.f, this.f.length);
  }

  public o[] h()
  {
    return (o[])Arrays.copyOf(this.g, this.g.length);
  }

  public f[] i()
  {
    return (f[])Arrays.copyOf(this.h, this.h.length);
  }

  public k[] j()
  {
    return (k[])Arrays.copyOf(this.i, this.i.length);
  }

  public l[] k()
  {
    l[] arrayOfl = new l[this.f.length + this.g.length + this.h.length + this.i.length];
    System.arraycopy(this.f, 0, arrayOfl, 0, this.f.length);
    System.arraycopy(this.g, 0, arrayOfl, this.f.length, this.g.length);
    System.arraycopy(this.h, 0, arrayOfl, this.f.length + this.g.length, this.h.length);
    System.arraycopy(this.i, 0, arrayOfl, this.f.length + this.g.length + this.h.length, this.i.length);
    return arrayOfl;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.a.a.a.a.a.n
 * JD-Core Version:    0.6.2
 */