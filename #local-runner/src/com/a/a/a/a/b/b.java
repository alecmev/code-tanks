package com.a.a.a.a.b;

import com.a.a.a.a.a.m;
import com.a.a.a.a.a.n;
import com.a.a.a.a.a.o;
import com.a.a.a.a.b.d.i;
import com.a.a.a.a.b.d.j;
import com.a.a.a.a.b.d.k;
import com.google.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.vecmath.Vector2d;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class b
  implements com.a.a.b
{
  private static final Logger a = LoggerFactory.getLogger(b.class);
  private static final Pattern b = Pattern.compile("[1-9][0-9]{0,5}x[1-9][0-9]{0,5}");

  @Inject
  private com.a.a.g c;
  private Map d = new HashMap();
  private final AtomicBoolean e = new AtomicBoolean(false);
  private final AtomicReference f = new AtomicReference(null);
  private int g;
  private int h;
  private int i;
  private int j;
  private final List k = new ArrayList();
  private final Map l = new LinkedHashMap();
  private final Map m = new LinkedHashMap();
  private final List n = new ArrayList();
  private ExecutorService o;
  private long p;

  public void a(Map paramMap, List paramList)
  {
    a.info("Game has been started.");
    this.p = System.currentTimeMillis();
    this.d = Collections.unmodifiableMap(new HashMap(paramMap));
    this.i = com.a.a.a.a.b.b.c.a(this.d);
    f();
    g();
    a(paramList);
    h();
    i();
    j();
    a.info("Game has been initialized.");
  }

  public void a()
  {
    for (this.j = 0; (this.j < this.i) && (!this.e.get()) && (!m()); this.j += 1)
    {
      if (k())
      {
        h localh = l();
        if (localh == null)
          break;
        localh.a(localh.f() + 100);
        break;
      }
      a(false);
      d();
      e();
      this.c.c();
    }
    a(true);
  }

  public void b()
  {
    a.info("Game has been finished in " + (System.currentTimeMillis() - this.p) + " ms.");
    Object localObject1 = this.l.keySet().iterator();
    Object localObject2;
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (h)((Iterator)localObject1).next();
      IOUtils.closeQuietly(((h)localObject2).c());
    }
    localObject1 = this.k.iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (com.a.a.a.a.d.h)((Iterator)localObject1).next();
      try
      {
        ((com.a.a.a.a.d.h)localObject2).close();
      }
      catch (IOException localIOException2)
      {
        a.error(String.format("Can't close renderer '%s'.", new Object[] { localObject2.getClass().getSimpleName() }), localIOException2);
        a(String.format("Can't close renderer '%s': %s", new Object[] { localObject2.getClass().getSimpleName(), ExceptionUtils.getStackTrace(localIOException2) }));
      }
    }
    localObject1 = (String)this.d.get("results-file");
    if (!StringUtils.isBlank((String)localObject1))
      try
      {
        localObject2 = new StringBuilder();
        if (this.e.get())
        {
          a.error("Game has failed with the message: " + (String)this.f.get());
          ((StringBuilder)localObject2).append("FAILED\n").append((String)this.f.get()).append('\n');
        }
        else
        {
          ((StringBuilder)localObject2).append("OK\n");
          Map localMap = c();
          Iterator localIterator = this.l.keySet().iterator();
          while (localIterator.hasNext())
          {
            h localh = (h)localIterator.next();
            ((StringBuilder)localObject2).append(localMap.get(Integer.valueOf(localh.f()))).append(' ').append(localh.f()).append(' ').append(localh.d() ? "CRASHED" : "OK").append('\n');
          }
        }
        FileUtils.writeByteArrayToFile(new File((String)localObject1), ((StringBuilder)localObject2).toString().getBytes("UTF-8"));
      }
      catch (IOException localIOException1)
      {
        a.error(String.format("Can't write results to file '%s'.", new Object[] { localObject1 }), localIOException1);
      }
  }

  private Map c()
  {
    HashMap localHashMap = new HashMap();
    ArrayList localArrayList = new ArrayList(this.l.keySet());
    Collections.sort(localArrayList, new d(this));
    for (int i1 = localArrayList.size() - 1; i1 >= 0; i1--)
      localHashMap.put(Integer.valueOf(((h)localArrayList.get(i1)).f()), Integer.valueOf(i1 + 1));
    return localHashMap;
  }

  private void a(boolean paramBoolean)
  {
    Iterator localIterator = this.k.iterator();
    while (localIterator.hasNext())
    {
      com.a.a.a.a.d.h localh = (com.a.a.a.a.d.h)localIterator.next();
      try
      {
        localh.a(com.a.a.a.a.b.b.b.a(com.a.a.a.a.b.b.b.a(this.j, paramBoolean, this.g, this.h, 1.0D / this.c.d(), new ArrayList(this.l.keySet()), this.c.a(), null), this.c.b()));
      }
      catch (IOException localIOException)
      {
        a.error(String.format("Can't render world using renderer '%s'.", new Object[] { localh.getClass().getSimpleName() }), localIOException);
        a(String.format("Can't render world using renderer '%s': %s", new Object[] { localh.getClass().getSimpleName(), ExceptionUtils.getStackTrace(localIOException) }));
      }
    }
  }

  private void d()
  {
    ArrayList localArrayList = new ArrayList(this.l.keySet());
    List localList1 = this.c.a();
    boolean bool = Boolean.parseBoolean((String)this.d.get("debug"));
    Iterator localIterator = this.l.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      h localh = (h)localEntry.getKey();
      if (!localh.d())
      {
        n localn = com.a.a.a.a.b.b.b.a(this.j, false, this.g, this.h, 1.0D / this.c.d(), localArrayList, localList1, localh);
        List localList2 = (List)localEntry.getValue();
        int i1 = localList2.size();
        o[] arrayOfo = new o[i1];
        for (int i2 = 0; i2 < i1; i2++)
          arrayOfo[i2] = com.a.a.a.a.b.b.f.a(this.j, (com.a.a.a.a.b.c.d.d)localList2.get(i2), 1.0D / this.c.d(), localh);
        Future localFuture = this.o.submit(new e(this, localh, arrayOfo, localn));
        long l1 = System.currentTimeMillis();
        m[] arrayOfm;
        try
        {
          if (bool)
            arrayOfm = (m[])localFuture.get(10L, TimeUnit.MINUTES);
          else
            arrayOfm = (m[])localFuture.get(i1 * 2000L, TimeUnit.MILLISECONDS);
        }
        catch (InterruptedException localInterruptedException)
        {
          a.error(String.format("Strategy adapter '%s' of %s has been interrupted at a tick %d.", new Object[] { localh.c().getClass().getSimpleName(), localh, Integer.valueOf(this.j) }), localInterruptedException);
          localFuture.cancel(true);
          localh.e();
          continue;
        }
        catch (ExecutionException localExecutionException)
        {
          a.warn(String.format("Strategy adapter '%s' of %s has failed at a tick %d.", new Object[] { localh.c().getClass().getSimpleName(), localh, Integer.valueOf(this.j) }), localExecutionException);
          localFuture.cancel(true);
          localh.e();
          continue;
        }
        catch (TimeoutException localTimeoutException)
        {
          a.warn(String.format("Strategy adapter '%s' of %s has timed out at a tick %d.", new Object[] { localh.c().getClass().getSimpleName(), localh, Integer.valueOf(this.j) }), localTimeoutException);
          localFuture.cancel(true);
          localh.e();
        }
        continue;
        if (!bool)
        {
          long l2 = System.currentTimeMillis() - l1;
          long l3 = ((Long)this.m.get(localh)).longValue();
          if (l3 < l2)
          {
            a.warn(String.format("Strategy adapter '%s' of %s has consumed all available game time at a tick %d.", new Object[] { localh.c().getClass().getSimpleName(), localh, Integer.valueOf(this.j) }));
            localh.e();
          }
          else
          {
            this.m.put(localh, Long.valueOf(l3 - l2));
          }
        }
        else
        {
          if (arrayOfm.length != i1)
            throw new RuntimeException(String.format("Strategy adapter '%s' of %s returned %d moves for %d tanks at tick %d.", new Object[] { localh.c().getClass().getSimpleName(), localh, Integer.valueOf(arrayOfm.length), Integer.valueOf(i1), Integer.valueOf(this.j) }));
          for (int i3 = 0; i3 < i1; i3++)
          {
            m localm = arrayOfm[i3];
            com.a.a.a.a.b.c.d.d locald = (com.a.a.a.a.b.c.d.d)localList2.get(i3);
            if ((localm != null) && (!com.a.a.a.a.b.b.f.a(locald)))
            {
              a(locald, localm);
              b(locald, localm);
              c(locald, localm);
            }
          }
        }
      }
    }
  }

  private void e()
  {
    Iterator localIterator = this.n.iterator();
    while (localIterator.hasNext())
    {
      com.a.a.f localf = (com.a.a.f)localIterator.next();
      localf.a(this.c, this.j);
    }
  }

  private void a(com.a.a.a.a.b.c.d.d paramd, m paramm)
  {
    com.a.a.a.a.a.d locald = paramm.d();
    if ((locald == null) || (locald == com.a.a.a.a.a.d.a))
      return;
    int i1 = com.a.a.a.a.b.b.f.c(paramd);
    if ((paramd.q() == null) || (this.j - paramd.q().intValue() >= i1))
    {
      Object localObject = null;
      switch (b.a[locald.ordinal()])
      {
      case 1:
        localObject = new com.a.a.a.a.b.c.c.c(this.j, paramd);
        break;
      case 2:
        if (paramd.s() > 0)
        {
          localObject = new com.a.a.a.a.b.c.c.b(this.j, paramd);
          paramd.c(paramd.s() - 1);
        }
        break;
      case 3:
        if (paramd.s() > 0)
        {
          localObject = new com.a.a.a.a.b.c.c.b(this.j, paramd);
          paramd.c(paramd.s() - 1);
        }
        else
        {
          localObject = new com.a.a.a.a.b.c.c.c(this.j, paramd);
        }
        break;
      default:
        throw new IllegalArgumentException("Unsupported fire type: " + locald + '.');
      }
      if (localObject != null)
      {
        this.c.a((com.a.a.d)localObject);
        paramd.a(Integer.valueOf(this.j));
        Vector2d localVector2d = new Vector2d(((com.a.a.a.a.b.c.c.a)localObject).d().f());
        localVector2d.scale(-((com.a.a.a.a.b.c.c.a)localObject).d().n() / paramd.d().n());
        paramd.d().f().add(localVector2d);
        this.c.a(new com.a.a.a.a.b.e.a(com.a.a.a.a.b.b.a.d((com.a.a.a.a.b.c.c.a)localObject), ((com.a.a.a.a.b.c.c.a)localObject).d().c(), ((com.a.a.a.a.b.c.c.a)localObject).d().d())).a(((com.a.a.a.a.b.c.c.a)localObject).d().e());
      }
    }
  }

  private static void b(com.a.a.a.a.b.c.d.d paramd, m paramm)
  {
    double d1 = paramm.a();
    if ((Double.isNaN(d1)) || (Double.isInfinite(d1)))
    {
      a.warn(String.format("Received unexpected 'leftTrackPowerFactor' value (%s) for %s.", new Object[] { Double.valueOf(d1), paramd }));
      return;
    }
    d1 = StrictMath.min(StrictMath.max(d1, -1.0D), 1.0D);
    double d2 = paramm.b();
    if ((Double.isNaN(d1)) || (Double.isInfinite(d1)))
    {
      a.warn(String.format("Received unexpected 'rightTrackPowerFactor' value (%s) for %s.", new Object[] { Double.valueOf(d2), paramd }));
      return;
    }
    d2 = StrictMath.min(StrictMath.max(d2, -1.0D), 1.0D);
    double d3 = com.a.a.a.a.b.b.f.b(paramd);
    double d4 = paramd.h() * d3;
    double d5 = paramd.i() * d3;
    double d6 = 0.5D * d1 * (d1 > 0.0D ? d4 : d5) + 0.5D * d2 * (d2 > 0.0D ? d4 : d5);
    double d7 = 0.0D;
    if ((d1 >= 0.0D) && (d2 >= 0.0D))
      d7 = (d1 - d2) * d4;
    else if ((d1 <= 0.0D) && (d2 <= 0.0D))
      d7 = (d1 - d2) * d5;
    else if ((d1 < 0.0D) && (d2 > 0.0D))
      d7 = d1 * d5 - d2 * d4;
    else if ((d1 > 0.0D) && (d2 < 0.0D))
      d7 = d1 * d4 - d2 * d5;
    d7 *= 2.0D;
    paramd.d().h().add(com.a.c.a.a.c.a(new Vector2d(d6, 0.0D), paramd.d().e()));
    paramd.d().d(paramd.d().i() + d7);
  }

  private static void c(com.a.a.a.a.b.c.d.d paramd, m paramm)
  {
    double d1 = paramm.c();
    if ((Double.isNaN(d1)) || (Double.isInfinite(d1)))
    {
      a.warn(String.format("Received unexpected 'turretTurnAngle' value (%s) for %s.", new Object[] { Double.valueOf(d1), paramd }));
      return;
    }
    double d2 = paramd.f() * com.a.a.a.a.b.b.f.b(paramd);
    if (StrictMath.abs(d1) > d2)
      d1 = d2 * StrictMath.signum(d1);
    paramd.a(paramd.e() + d1);
  }

  private void f()
  {
    a.debug("Started to create static objects.");
    String str = (String)this.d.get("size");
    if (StringUtils.isBlank(str))
      str = "1280x800";
    int i1 = str.indexOf('x');
    if ((i1 <= 0) || (i1 == str.length() - 1) || (!b.matcher(str).matches()))
      throw new IllegalArgumentException("Illegal world size value: '" + str + "'.");
    this.g = Integer.parseInt(str.substring(0, i1));
    this.h = Integer.parseInt(str.substring(i1 + 1));
    this.c.a(new com.a.a.a.a.b.c.a.b(0.0D, 0.0D, this.g, 0.0D));
    this.c.a(new com.a.a.a.a.b.c.a.b(0.0D, this.h, this.g, this.h));
    this.c.a(new com.a.a.a.a.b.c.a.b(0.0D, 0.0D, 0.0D, this.h));
    this.c.a(new com.a.a.a.a.b.c.a.b(this.g, 0.0D, this.g, this.h));
    if (Boolean.parseBoolean((String)this.d.get("bunker")))
      this.c.a(new com.a.a.a.a.b.c.e.a(this.g / 2.0D, this.h / 2.0D));
    a.debug("Finished to create static objects.");
  }

  private void g()
  {
    a.debug("Started to add renderers.");
    if (Boolean.parseBoolean((String)this.d.get("render-to-screen")))
    {
      a.debug("Adding " + com.a.a.a.a.d.a.class.getSimpleName() + '.');
      this.k.add(new com.a.a.a.a.d.a(this.g, this.h, this.d));
    }
    String str1 = (String)this.d.get("write-to-text-file");
    if (!StringUtils.isBlank(str1))
      try
      {
        a.debug("Adding " + com.a.a.a.a.d.b.class.getSimpleName() + '.');
        this.k.add(new com.a.a.a.a.d.b(new File(str1)));
      }
      catch (IOException localIOException1)
      {
        a.error(String.format("Can't create renderer '%s'.", new Object[] { com.a.a.a.a.d.b.class.getSimpleName() }), localIOException1);
        a(String.format("Can't create renderer '%s': %s", new Object[] { com.a.a.a.a.d.b.class.getSimpleName(), ExceptionUtils.getStackTrace(localIOException1) }));
      }
    String str2 = (String)this.d.get("write-to-remote-storage");
    if (!StringUtils.isBlank(str2))
      try
      {
        a.debug("Adding " + com.a.a.a.a.d.g.class.getSimpleName() + '.');
        this.k.add(new com.a.a.a.a.d.g(str2));
      }
      catch (IOException localIOException2)
      {
        a.error(String.format("Can't create renderer '%s'.", new Object[] { com.a.a.a.a.d.g.class.getSimpleName() }), localIOException2);
        a(String.format("Can't create renderer '%s': %s", new Object[] { com.a.a.a.a.d.g.class.getSimpleName(), ExceptionUtils.getStackTrace(localIOException2) }));
      }
    a.debug("Finished to add renderers.");
  }

  private void a(List paramList)
  {
    a.debug("Started to add players.");
    int i1 = paramList.size();
    if (this.o != null)
      this.o.shutdown();
    this.o = Executors.newFixedThreadPool(i1, new f(this));
    for (int i2 = 0; i2 < i1; i2++)
    {
      String str1 = (String)paramList.get(i2);
      String str2 = (String)this.d.get("p" + (i2 + 1) + "-name");
      if (StringUtils.isBlank(str2))
        str2 = "Player #" + (i2 + 1);
      int i3 = com.a.a.a.a.b.b.c.a(this.d, i2);
      h localh = com.a.a.a.a.b.b.g.a(this.d, i2, str2, str1, i3);
      long l1 = i3 * (this.i + 1) * 50L;
      this.l.put(localh, new ArrayList());
      this.m.put(localh, Long.valueOf(l1));
    }
    a.debug("Finished to add players.");
  }

  private void h()
  {
    a.debug("Adding player units.");
    int i1 = 0;
    Iterator localIterator = this.l.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      h localh = (h)localEntry.getKey();
      int i2 = com.a.a.a.a.b.b.c.a(this.d, i1);
      Future localFuture = this.o.submit(new g(this, localh));
      long l1 = System.currentTimeMillis();
      com.a.a.a.a.a.b[] arrayOfb = new com.a.a.a.a.a.b[i2];
      try
      {
        if (Boolean.parseBoolean((String)this.d.get("debug")))
          arrayOfb = (com.a.a.a.a.a.b[])localFuture.get(10L, TimeUnit.MINUTES);
        else
          arrayOfb = (com.a.a.a.a.a.b[])localFuture.get(i2 * 2000L, TimeUnit.MILLISECONDS);
      }
      catch (InterruptedException localInterruptedException)
      {
        a.error(String.format("Strategy adapter '%s' of %s has been interrupted at a tick %d.", new Object[] { localh.c().getClass().getSimpleName(), localh, Integer.valueOf(this.j) }), localInterruptedException);
        localFuture.cancel(true);
        localh.e();
      }
      catch (ExecutionException localExecutionException)
      {
        a.warn(String.format("Strategy adapter '%s' of %s has failed at a tick %d.", new Object[] { localh.c().getClass().getSimpleName(), localh, Integer.valueOf(this.j) }), localExecutionException);
        localFuture.cancel(true);
        localh.e();
      }
      catch (TimeoutException localTimeoutException)
      {
        a.warn(String.format("Strategy adapter '%s' of %s has timed out at a tick %d.", new Object[] { localh.c().getClass().getSimpleName(), localh, Integer.valueOf(this.j) }), localTimeoutException);
        localFuture.cancel(true);
        localh.e();
      }
      long l2 = System.currentTimeMillis() - l1;
      long l3 = ((Long)this.m.get(localh)).longValue();
      if (l3 < l2)
      {
        a.warn(String.format("Strategy adapter '%s' of %s has consumed all available game time at a tick %d.", new Object[] { localh.c().getClass().getSimpleName(), localh, Integer.valueOf(this.j) }));
        localh.e();
      }
      this.m.put(localh, Long.valueOf(l3 - l2));
      if (arrayOfb.length != i2)
        throw new RuntimeException(String.format("Strategy adapter '%s' of %s selected %d tanks, while %d expected.", new Object[] { localh.c().getClass().getSimpleName(), localh, Integer.valueOf(arrayOfb.length), Integer.valueOf(i2) }));
      for (int i3 = 0; i3 < i2; i3++)
      {
        Vector2d localVector2d = a(i1, this.l.size(), i2, i3);
        com.a.a.a.a.a.b localb = arrayOfb[i3];
        if (localb == null)
          localb = com.a.a.a.a.e.b.a;
        double d1 = b(i1, this.l.size(), i2, i3);
        Object localObject;
        switch (b.b[localb.ordinal()])
        {
        case 1:
          localObject = new com.a.a.a.a.b.c.d.b(localh, i3, localVector2d.x, localVector2d.y, d1);
          break;
        case 2:
          localObject = new com.a.a.a.a.b.c.d.c(localh, i3, localVector2d.x, localVector2d.y, d1);
          break;
        case 3:
          localObject = new com.a.a.a.a.b.c.d.a(localh, i3, localVector2d.x, localVector2d.y, d1);
          break;
        default:
          a(localb, localh.b());
          return;
        }
        this.c.a((com.a.a.d)localObject);
        ((List)localEntry.getValue()).add(localObject);
      }
      i1++;
    }
  }

  private void a(com.a.a.a.a.a.b paramb, String paramString)
  {
    String str = String.format("Player '%s' specified unsupported tank type: %s.", new Object[] { paramString, paramb });
    a.error(str);
    a(str);
  }

  private void i()
  {
    a.debug("Adding collision listeners.");
    this.c.a(com.a.a.a.a.b.c.d.d.class, com.a.a.a.a.b.c.c.a.class, new j(new a()));
    this.c.a(com.a.a.a.a.b.c.e.b.class, com.a.a.a.a.b.c.c.a.class, new com.a.a.a.a.b.d.g());
    this.c.a(com.a.a.a.a.b.c.c.a.class, com.a.a.a.a.b.c.c.a.class, new com.a.a.a.a.b.d.d());
    this.c.a(com.a.a.a.a.b.c.b.a.class, com.a.a.a.a.b.c.c.a.class, new com.a.a.a.a.b.d.b());
    this.c.a(com.a.a.a.a.b.c.a.a.class, com.a.a.a.a.b.c.c.a.class, new com.a.a.a.a.b.d.c());
    this.c.a(com.a.a.a.a.b.c.d.d.class, com.a.a.a.a.b.c.b.d.class, new com.a.a.a.a.b.d.h());
    this.c.a(com.a.a.a.a.b.c.d.d.class, com.a.a.a.a.b.c.b.b.class, new k());
    this.c.a(com.a.a.a.a.b.c.d.d.class, com.a.a.a.a.b.c.b.c.class, new com.a.a.a.a.b.d.a());
    this.c.a(com.a.a.a.a.b.c.d.d.class, com.a.a.a.a.b.c.d.d.class, new com.a.a.a.a.b.d.f());
    this.c.a(com.a.a.a.a.b.c.d.d.class, com.a.a.a.a.b.c.e.b.class, new i());
    this.c.a(com.a.a.a.a.b.c.d.d.class, com.a.a.a.a.b.c.a.a.class, new com.a.a.a.a.b.d.e());
  }

  private void j()
  {
    a.debug("Adding game events.");
    this.n.add(new com.a.a.a.a.b.a.g(0.0D, 0.0D, this.g, this.h, this.i));
    this.n.add(new com.a.a.a.a.b.a.c(0.0D, 0.0D, this.g, this.h, this.i));
    this.n.add(new com.a.a.a.a.b.a.e(0.0D, 0.0D, this.g, this.h, this.i));
    this.n.add(new com.a.a.a.a.b.a.h());
    this.n.add(new com.a.a.a.a.b.a.f());
    this.n.add(new com.a.a.a.a.b.a.b());
  }

  private Vector2d a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    c(paramInt1, paramInt2, paramInt3, paramInt4);
    double d1 = 0.4D * StrictMath.min(this.g, this.h);
    double d2 = a(paramInt1, paramInt2, paramInt3, paramInt4, d1);
    return new Vector2d(0.5D * this.g + StrictMath.cos(d2) * d1, 0.5D * this.h + StrictMath.sin(d2) * d1);
  }

  private double b(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    c(paramInt1, paramInt2, paramInt3, paramInt4);
    double d1 = 0.4D * StrictMath.min(this.g, this.h);
    return 3.141592653589793D + a(paramInt1, paramInt2, paramInt3, paramInt4, d1);
  }

  private static double a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, double paramDouble)
  {
    double d1 = a(paramInt1, paramInt2);
    double d2 = 2.0D * StrictMath.asin(93.75D / paramDouble);
    return d1 + (paramInt4 - 0.5D * (paramInt3 - 1)) * d2;
  }

  private static double a(int paramInt1, int paramInt2)
  {
    return 6.283185307179586D / paramInt2 * paramInt1;
  }

  private static void c(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (paramInt1 < 0)
      throw new IllegalArgumentException("Argument 'teamIndex' should be non-negative.");
    if ((paramInt3 > 5) || (paramInt4 >= paramInt3) || (paramInt2 * paramInt3 > 15))
      throw new IllegalArgumentException(String.format("Unsupported game format [teamIndex=%d, teamSize=%d, tankIndex=%d].", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt3), Integer.valueOf(paramInt4) }));
  }

  private boolean k()
  {
    int i1 = 0;
    Iterator localIterator1 = this.l.values().iterator();
    while (localIterator1.hasNext())
    {
      List localList = (List)localIterator1.next();
      int i2 = 0;
      Iterator localIterator2 = localList.iterator();
      while (localIterator2.hasNext())
      {
        com.a.a.a.a.b.c.d.d locald = (com.a.a.a.a.b.c.d.d)localIterator2.next();
        if (!com.a.a.a.a.b.b.f.a(locald))
        {
          i2 = 1;
          break;
        }
      }
      if (i2 != 0)
        i1++;
    }
    return i1 <= 1;
  }

  private h l()
  {
    Iterator localIterator1 = this.l.entrySet().iterator();
    while (localIterator1.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator1.next();
      Iterator localIterator2 = ((List)localEntry.getValue()).iterator();
      while (localIterator2.hasNext())
      {
        com.a.a.a.a.b.c.d.d locald = (com.a.a.a.a.b.c.d.d)localIterator2.next();
        if (!com.a.a.a.a.b.b.f.a(locald))
          return (h)localEntry.getKey();
      }
    }
    return null;
  }

  private boolean m()
  {
    Iterator localIterator = this.l.keySet().iterator();
    while (localIterator.hasNext())
    {
      h localh = (h)localIterator.next();
      if (!localh.d())
        return false;
    }
    return true;
  }

  private void a(String paramString)
  {
    if (!this.e.getAndSet(true))
      this.f.set(paramString);
  }

  public final class a
  {
    public a()
    {
    }

    public int a()
    {
      return b.a(b.this);
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.a.a.a.a.b.b
 * JD-Core Version:    0.6.2
 */