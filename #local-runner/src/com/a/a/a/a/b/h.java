package com.a.a.a.a.b;

import com.a.a.a.a.c.a.e;
import java.util.concurrent.atomic.AtomicLong;

public class h
{
  private static final AtomicLong a = new AtomicLong();
  private final long b = a.incrementAndGet();
  private final String c;
  private final e d;
  private boolean e;
  private int f;
  private int g;
  private int h;

  public h(String paramString, e parame)
  {
    this.c = paramString;
    this.d = parame;
  }

  public final long a()
  {
    return this.b;
  }

  public String b()
  {
    return this.c;
  }

  public e c()
  {
    return this.d;
  }

  public boolean d()
  {
    return this.e;
  }

  public void e()
  {
    this.e = true;
    try
    {
      if (this.d != null)
        this.d.close();
    }
    catch (RuntimeException localRuntimeException)
    {
    }
  }

  public int f()
  {
    return this.f;
  }

  public void a(int paramInt)
  {
    this.f = paramInt;
  }

  public int g()
  {
    return this.g;
  }

  public void b(int paramInt)
  {
    this.g = paramInt;
  }

  public int h()
  {
    return this.h;
  }

  public void c(int paramInt)
  {
    this.h = paramInt;
  }

  public String toString()
  {
    return String.format("%s {id=%d, name='%s'}", new Object[] { getClass().getSimpleName(), Long.valueOf(this.b), this.c });
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.a.a.a.a.b.h
 * JD-Core Version:    0.6.2
 */