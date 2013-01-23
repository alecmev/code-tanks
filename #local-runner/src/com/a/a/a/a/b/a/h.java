package com.a.a.a.a.b.a;

import com.a.a.a.a.b.c.b.a;
import com.a.a.d;
import com.a.a.f;
import com.a.a.g;
import java.util.Iterator;
import java.util.List;

public class h
  implements f
{
  public void a(g paramg, int paramInt)
  {
    Iterator localIterator = paramg.a().iterator();
    while (localIterator.hasNext())
    {
      d locald = (d)localIterator.next();
      if ((locald instanceof a))
      {
        a locala = (a)locald;
        locala.a(locala.a() - 1);
        if (locala.a() < 0)
          paramg.b(locala);
      }
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.a.a.a.a.b.a.h
 * JD-Core Version:    0.6.2
 */