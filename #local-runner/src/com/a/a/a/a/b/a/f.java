package com.a.a.a.a.b.a;

import com.a.a.a.a.a.c;
import com.a.a.d;
import com.a.a.g;
import com.a.b.b;
import java.util.Iterator;
import java.util.List;
import javax.vecmath.Vector2d;

public class f
  implements com.a.a.f
{
  public void a(g paramg, int paramInt)
  {
    Iterator localIterator = paramg.a().iterator();
    while (localIterator.hasNext())
    {
      d locald = (d)localIterator.next();
      if ((locald instanceof com.a.a.a.a.b.c.c.a))
      {
        b localb = locald.d();
        if (localb.f().length() < 100.0D)
        {
          paramg.b(locald);
          paramg.a(new com.a.a.a.a.b.e.a(c.n, localb.c(), localb.d()));
        }
      }
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.a.a.a.a.b.a.f
 * JD-Core Version:    0.6.2
 */