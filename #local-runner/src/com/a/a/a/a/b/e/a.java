package com.a.a.a.a.b.e;

import com.a.a.a.a.a.c;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class a
{
  private static final AtomicLong a = new AtomicLong();
  private final long b = a.incrementAndGet();
  private double c;
  private double d;
  private double e;
  private final c f;
  private int g;
  private final Map h;

  public a(c paramc, double paramDouble1, double paramDouble2)
  {
    this.c = paramDouble1;
    this.d = paramDouble2;
    this.f = paramc;
    this.h = new HashMap();
  }

  public a(c paramc, double paramDouble1, double paramDouble2, Map paramMap)
  {
    this.c = paramDouble1;
    this.d = paramDouble2;
    this.f = paramc;
    this.h = new HashMap(paramMap);
  }

  public final long a()
  {
    return this.b;
  }

  public double b()
  {
    return this.c;
  }

  public double c()
  {
    return this.d;
  }

  public double d()
  {
    return this.e;
  }

  public void a(double paramDouble)
  {
    this.e = paramDouble;
  }

  public c e()
  {
    return this.f;
  }

  public int f()
  {
    return this.g;
  }

  public void g()
  {
    this.g += 1;
  }

  public boolean h()
  {
    return this.g >= 100;
  }

  public Map i()
  {
    return Collections.unmodifiableMap(this.h);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.a.a.a.a.b.e.a
 * JD-Core Version:    0.6.2
 */