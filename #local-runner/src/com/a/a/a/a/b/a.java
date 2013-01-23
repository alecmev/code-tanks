package com.a.a.a.a.b;

import com.a.a.d;
import com.a.a.g;
import com.a.b.b;
import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class a
  implements g
{

  @Inject
  private com.a.b.c a;
  private final Map b = new HashMap();
  private final Map c = new HashMap();
  private final List d = new ArrayList();

  public d a(d paramd)
  {
    b localb = this.a.a(paramd.d());
    this.b.put(Long.valueOf(paramd.c()), paramd);
    this.c.put(localb.a(), paramd);
    paramd.a(localb);
    return paramd;
  }

  public void b(d paramd)
  {
    this.a.a(paramd.d().a().longValue());
    this.b.remove(Long.valueOf(paramd.c()));
    this.c.remove(paramd.d().a());
    paramd.d().a(null);
  }

  public List a()
  {
    return Collections.unmodifiableList(new ArrayList(this.b.values()));
  }

  public com.a.a.a.a.b.e.a a(com.a.a.a.a.b.e.a parama)
  {
    this.d.add(parama);
    return parama;
  }

  public List b()
  {
    return Collections.unmodifiableList(this.d);
  }

  public void c()
  {
    Iterator localIterator = this.d.iterator();
    while (localIterator.hasNext())
    {
      localObject = (com.a.a.a.a.b.e.a)localIterator.next();
      ((com.a.a.a.a.b.e.a)localObject).g();
      if (((com.a.a.a.a.b.e.a)localObject).h())
        localIterator.remove();
    }
    Object localObject = this.b.values().iterator();
    d locald;
    while (((Iterator)localObject).hasNext())
    {
      locald = (d)((Iterator)localObject).next();
      this.a.b(locald.d());
    }
    this.a.a();
    localObject = this.b.values().iterator();
    while (((Iterator)localObject).hasNext())
    {
      locald = (d)((Iterator)localObject).next();
      b localb = this.a.b(locald.d().a().longValue());
      locald.a(localb);
    }
  }

  public void a(Class paramClass1, Class paramClass2, com.a.a.a parama)
  {
    this.a.a(new c(this, paramClass1, paramClass2, parama));
  }

  public int d()
  {
    return this.a.b();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.a.a.a.a.b.a
 * JD-Core Version:    0.6.2
 */