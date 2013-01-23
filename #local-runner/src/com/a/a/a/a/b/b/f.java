package com.a.a.a.a.b.b;

import com.a.a.a.a.a.o;
import com.a.a.a.a.b.c.d.d;
import com.a.a.a.a.b.h;
import javax.vecmath.Vector2d;

public final class f
{
  private f()
  {
    throw new UnsupportedOperationException();
  }

  public static void a(d paramd, com.a.a.a.a.b.c.c.a parama, boolean paramBoolean)
  {
    a(paramd, parama.b(), parama.h(), paramBoolean);
  }

  public static void a(d paramd, h paramh, int paramInt, boolean paramBoolean)
  {
    if (a(paramd))
      return;
    int i = paramh.a() == paramd.a().a() ? 1 : 0;
    int j = 0;
    int k = Math.min(paramd.l(), paramInt);
    j = (int)(j + k * 1.0D);
    paramd.b(paramd.l() - k);
    if (paramBoolean)
    {
      int m = Math.min(paramd.j(), paramInt);
      j = (int)(j + m * 1.0D);
      paramd.a(paramd.j() - m);
    }
    if (a(paramd))
    {
      j += 25;
      if (i == 0)
        paramh.b(paramh.g() + 1);
      paramd.a().c(paramd.a().h() + 1);
    }
    if (i == 0)
      paramh.a(paramh.f() + j);
  }

  public static void a(d paramd, int paramInt)
  {
    boolean bool = a(paramd);
    paramd.a(Math.min(paramd.k(), paramd.j() + paramInt));
    if ((bool) && (!a(paramd)))
      paramd.a().a(paramd.a().f() + 50);
  }

  public static void b(d paramd, int paramInt)
  {
    boolean bool = a(paramd);
    paramd.b(Math.min(paramd.m(), paramd.l() + paramInt));
    if ((bool) && (!a(paramd)))
      paramd.a().a(paramd.a().f() + 50);
  }

  public static boolean a(d paramd)
  {
    return a(paramd.j(), paramd.l());
  }

  public static boolean a(int paramInt1, int paramInt2)
  {
    return (paramInt1 <= 0) || (paramInt2 <= 0);
  }

  public static double b(d paramd)
  {
    double d1 = paramd.j() / paramd.k();
    double d2 = 0.5D + 0.5D * d1;
    if (d2 >= 1.0D)
      return 1.0D;
    if (d2 <= 0.0D)
      return 0.0D;
    return d2;
  }

  public static int c(d paramd)
  {
    return Double.valueOf(StrictMath.ceil(paramd.r() / b(paramd))).intValue();
  }

  public static o a(int paramInt, d paramd, double paramDouble, h paramh)
  {
    com.a.a.a.a.a.b localb;
    if ((paramd instanceof com.a.a.a.a.b.c.d.b))
      localb = com.a.a.a.a.a.b.a;
    else if ((paramd instanceof com.a.a.a.a.b.c.d.c))
      localb = com.a.a.a.a.a.b.b;
    else if ((paramd instanceof com.a.a.a.a.b.c.d.a))
      localb = com.a.a.a.a.a.b.c;
    else
      throw new IllegalArgumentException("Unsupported tank class: " + paramd.getClass() + '.');
    com.a.b.b.b localb1 = paramd.d().o();
    if (!(localb1 instanceof com.a.b.b.c))
      throw new IllegalArgumentException("Unsupported tank form: " + localb1 + '.');
    int i = c(paramd);
    int j = paramd.q() == null ? 0 : StrictMath.max(i - paramInt + paramd.q().intValue(), 0);
    com.a.b.b.c localc = (com.a.b.b.c)localb1;
    return new o(paramd.c(), paramd.a().a(), paramd.a().b(), paramd.b(), localc.a(), localc.c(), paramd.d().c(), paramd.d().d(), paramd.d().f().x * paramDouble, paramd.d().f().y * paramDouble, paramd.d().e(), paramd.d().g() * paramDouble, paramd.e(), paramd.j(), paramd.k(), paramd.l(), paramd.m(), i, j, paramd.s(), paramd.a().equals(paramh), localb);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.a.a.a.a.b.b.f
 * JD-Core Version:    0.6.2
 */