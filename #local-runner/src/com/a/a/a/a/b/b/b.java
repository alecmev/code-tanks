package com.a.a.a.a.b.b;

import com.a.a.a.a.a.j;
import com.a.a.a.a.a.k;
import com.a.a.a.a.a.n;
import com.a.a.a.a.a.o;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class b
{
  private static final Logger a = LoggerFactory.getLogger(b.class);

  private b()
  {
    throw new UnsupportedOperationException();
  }

  public static n a(int paramInt, boolean paramBoolean, double paramDouble1, double paramDouble2, double paramDouble3, List paramList1, List paramList2, com.a.a.a.a.b.h paramh)
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    ArrayList localArrayList3 = new ArrayList();
    ArrayList localArrayList4 = new ArrayList();
    Iterator localIterator = paramList2.iterator();
    while (localIterator.hasNext())
    {
      com.a.a.d locald = (com.a.a.d)localIterator.next();
      if ((locald instanceof com.a.a.a.a.b.c.e.b))
        localArrayList1.add(e.a((com.a.a.a.a.b.c.e.b)locald));
      else if ((locald instanceof com.a.a.a.a.b.c.d.d))
        localArrayList2.add(f.a(paramInt, (com.a.a.a.a.b.c.d.d)locald, paramDouble3, paramh));
      else if ((locald instanceof com.a.a.a.a.b.c.c.a))
        localArrayList3.add(h.a((com.a.a.a.a.b.c.c.a)locald, paramDouble3));
      else if ((locald instanceof com.a.a.a.a.b.c.b.a))
        localArrayList4.add(d.a((com.a.a.a.a.b.c.b.a)locald));
      else if (!(locald instanceof com.a.a.a.a.b.c.a.a))
        throw new IllegalArgumentException("Unsupported unit class: " + locald.getClass() + '.');
    }
    Collections.shuffle(localArrayList1, com.a.c.a.a.b.a());
    Collections.shuffle(localArrayList2, com.a.c.a.a.b.a());
    Collections.shuffle(localArrayList3, com.a.c.a.a.b.a());
    Collections.shuffle(localArrayList4, com.a.c.a.a.b.a());
    return new n(paramInt, paramBoolean, paramDouble1, paramDouble2, g.a(paramList1), (com.a.a.a.a.a.a[])localArrayList1.toArray(new com.a.a.a.a.a.a[localArrayList1.size()]), (o[])localArrayList2.toArray(new o[localArrayList2.size()]), (com.a.a.a.a.a.f[])localArrayList3.toArray(new com.a.a.a.a.a.f[localArrayList3.size()]), (k[])localArrayList4.toArray(new k[localArrayList4.size()]));
  }

  public static com.a.a.a.a.a.g a(n paramn, List paramList)
  {
    int i = paramList.size();
    j[] arrayOfj = new j[i];
    for (int j = 0; j < i; j++)
      arrayOfj[j] = a.a((com.a.a.a.a.b.e.a)paramList.get(j));
    return new com.a.a.a.a.a.g(paramn, arrayOfj);
  }

  public static String a(n paramn)
  {
    try
    {
      return new GsonBuilder().setVersion(paramn.b()).create().toJson(paramn);
    }
    catch (RuntimeException localRuntimeException1)
    {
      String str = null;
      try
      {
        str = new GsonBuilder().setVersion(paramn.b()).serializeSpecialFloatingPointValues().serializeNulls().create().toJson(paramn);
      }
      catch (RuntimeException localRuntimeException2)
      {
      }
      a.error(String.format("Can't serialize world with default settings. Result of serialization with safe settings: %s.", new Object[] { str }));
      throw localRuntimeException1;
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.a.a.a.a.b.b.b
 * JD-Core Version:    0.6.2
 */