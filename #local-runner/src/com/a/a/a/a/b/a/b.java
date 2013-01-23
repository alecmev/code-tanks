package com.a.a.a.a.b.a;

import com.a.a.a.a.a.c;
import com.a.a.a.a.b.e.a;
import com.a.a.f;
import com.a.a.g;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class b
  implements f
{
  private Map a = new HashMap();

  public void a(g paramg, int paramInt)
  {
    HashMap localHashMap1 = new HashMap();
    Iterator localIterator = paramg.a().iterator();
    while (localIterator.hasNext())
    {
      com.a.a.d locald = (com.a.a.d)localIterator.next();
      if ((locald instanceof com.a.a.a.a.b.c.d.d))
      {
        com.a.a.a.a.b.c.d.d locald1 = (com.a.a.a.a.b.c.d.d)locald;
        com.a.b.b localb = locald1.d();
        b localb1 = new b(locald1, null);
        b localb2 = (b)this.a.get(Long.valueOf(locald1.c()));
        if ((localb2 != null) && ((localb1.a() != localb2.a()) || (localb1.b() != localb2.b())))
        {
          HashMap localHashMap2 = new HashMap();
          localHashMap2.put("crewHealthDelta", Integer.valueOf(localb1.a() - localb2.a()));
          localHashMap2.put("hullDurabilityDelta", Integer.valueOf(localb1.b() - localb2.b()));
          paramg.a(new a(c.t, localb.c(), localb.d(), localHashMap2));
        }
        localHashMap1.put(Long.valueOf(locald1.c()), localb1);
      }
    }
    this.a = localHashMap1;
  }

  private static final class b
  {
    private final int a;
    private final int b;

    private b(com.a.a.a.a.b.c.d.d paramd)
    {
      this.a = paramd.j();
      this.b = paramd.l();
    }

    public int a()
    {
      return this.a;
    }

    public int b()
    {
      return this.b;
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.a.a.a.a.b.a.b
 * JD-Core Version:    0.6.2
 */