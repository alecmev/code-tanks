package com.a.a.a.a.d;

import com.a.a.a.a.a.g;
import java.awt.Panel;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

class d
  implements Runnable
{
  d(a parama, Map paramMap)
  {
  }

  public void run()
  {
    long l = System.currentTimeMillis();
    try
    {
      g localg;
      for (Object localObject = null; (localg = a()) != null; localObject = localg)
      {
        l = a(l, 16L);
        a.a(this.b, localg);
      }
      if (localObject != null)
        a.a(this.b, localObject, a.b(this.b).getGraphics());
      Thread.sleep(TimeUnit.SECONDS.toMillis(30L));
    }
    catch (InterruptedException localInterruptedException)
    {
    }
    System.exit(0);
  }

  private g a()
    throws InterruptedException
  {
    a.a locala;
    if (Boolean.parseBoolean((String)this.a.get("debug")))
      locala = (a.a)a.c(this.b).poll(10L, TimeUnit.MINUTES);
    else
      locala = (a.a)a.c(this.b).poll(30L, TimeUnit.SECONDS);
    return locala == null ? null : locala.a();
  }

  private long a(long paramLong1, long paramLong2)
  {
    long l = System.currentTimeMillis();
    if (l - paramLong1 < paramLong2)
      com.a.c.a.a.d.a(paramLong2 - l + paramLong1);
    return System.currentTimeMillis();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.a.a.a.a.d.d
 * JD-Core Version:    0.6.2
 */