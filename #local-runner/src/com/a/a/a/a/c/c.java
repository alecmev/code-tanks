package com.a.a.a.a.c;

import com.a.a.a.a.a.d;
import com.a.a.a.a.a.k;
import com.a.a.a.a.a.m;
import com.a.a.a.a.a.n;
import com.a.a.a.a.a.o;

public class c
  implements b
{
  public void a(o paramo, n paramn, m paramm)
  {
    b(paramo, paramn, paramm);
    c(paramo, paramn, paramm);
  }

  private static void b(o paramo, n paramn, m paramm)
  {
    o[] arrayOfo = paramn.h();
    double d1 = 1.0E+020D;
    int i = arrayOfo.length;
    for (int j = 0; j < arrayOfo.length; j++)
    {
      o localo = arrayOfo[j];
      if (!localo.t())
      {
        double d3 = StrictMath.abs(paramo.c(localo));
        if (d3 < d1)
        {
          d1 = d3;
          i = j;
        }
      }
    }
    if (i != arrayOfo.length)
    {
      double d2 = paramo.c(arrayOfo[i]);
      if (d2 > 0.0174532925199433D)
        paramm.c(1.0D);
      else if (d2 < -0.0174532925199433D)
        paramm.c(-1.0D);
      else
        paramm.a(d.d);
    }
  }

  private static void c(o paramo, n paramn, m paramm)
  {
    k[] arrayOfk = paramn.j();
    double d1 = 1.0E+020D;
    int i = arrayOfk.length;
    for (int j = 0; j < arrayOfk.length; j++)
    {
      k localk = arrayOfk[j];
      double d3 = paramo.b(localk);
      if (d3 < d1)
      {
        d1 = d3;
        i = j;
      }
    }
    if (i != arrayOfk.length)
    {
      double d2 = paramo.a(arrayOfk[i]);
      if (d2 > 0.5235987755982988D)
      {
        paramm.a(0.75D);
        paramm.b(-1.0D);
      }
      else if (d2 < -0.5235987755982988D)
      {
        paramm.a(-1.0D);
        paramm.b(0.75D);
      }
      else
      {
        paramm.a(1.0D);
        paramm.b(1.0D);
      }
    }
  }

  public com.a.a.a.a.a.b a(int paramInt1, int paramInt2)
  {
    return com.a.a.a.a.a.b.a;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.a.a.a.a.c.c
 * JD-Core Version:    0.6.2
 */