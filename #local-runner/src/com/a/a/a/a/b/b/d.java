package com.a.a.a.a.b.b;

import com.a.a.a.a.a.k;
import com.a.a.a.a.a.p;
import com.a.a.a.a.b.c.b.a;

public final class d
{
  private d()
  {
    throw new UnsupportedOperationException();
  }

  public static k a(a parama)
  {
    p localp;
    if ((parama instanceof com.a.a.a.a.b.c.b.d))
      localp = p.a;
    else if ((parama instanceof com.a.a.a.a.b.c.b.b))
      localp = p.b;
    else if ((parama instanceof com.a.a.a.a.b.c.b.c))
      localp = p.c;
    else
      throw new IllegalArgumentException("Unsupported bonus class: " + parama.getClass() + '.');
    com.a.b.b.b localb = parama.d().o();
    if (!(localb instanceof com.a.b.b.c))
      throw new IllegalArgumentException("Unsupported bonus form: " + localb + '.');
    com.a.b.b.c localc = (com.a.b.b.c)localb;
    return new k(parama.c(), localc.a(), localc.c(), parama.d().c(), parama.d().d(), localp);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.a.a.a.a.b.b.d
 * JD-Core Version:    0.6.2
 */