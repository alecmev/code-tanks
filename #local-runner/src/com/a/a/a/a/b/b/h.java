package com.a.a.a.a.b.b;

import com.a.a.a.a.a.e;
import com.a.a.a.a.a.f;
import com.a.a.a.a.b.c.c.a;
import javax.vecmath.Vector2d;

public final class h
{
  private h()
  {
    throw new UnsupportedOperationException();
  }

  public static f a(a parama, double paramDouble)
  {
    e locale;
    if ((parama instanceof com.a.a.a.a.b.c.c.c))
      locale = e.a;
    else if ((parama instanceof com.a.a.a.a.b.c.c.b))
      locale = e.b;
    else
      throw new IllegalArgumentException("Unsupported shell class: " + parama.getClass() + '.');
    com.a.b.b.b localb = parama.d().o();
    if (!(localb instanceof com.a.b.b.c))
      throw new IllegalArgumentException("Unsupported shell form: " + localb + '.');
    com.a.b.b.c localc = (com.a.b.b.c)localb;
    return new f(parama.c(), parama.b().b(), localc.a(), localc.c(), parama.d().c(), parama.d().d(), parama.d().f().x * paramDouble, parama.d().f().y * paramDouble, parama.d().e(), parama.d().g() * paramDouble, locale);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.a.a.a.a.b.b.h
 * JD-Core Version:    0.6.2
 */