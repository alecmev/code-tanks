package com.a.b.a.a;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.vecmath.Vector2d;
import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.World;

public class b
  implements com.a.b.c
{
  private final Lock a = new ReentrantLock();
  private final Map b = new HashMap();
  private final World c;

  public b(Vector2d paramVector2d, int paramInt)
  {
    this.c = new World(new Vector2f((float)paramVector2d.x, (float)paramVector2d.y), paramInt);
  }

  public com.a.b.b a(com.a.b.b paramb)
  {
    this.a.lock();
    try
    {
      Body localBody = c.a(paramb);
      this.c.add(localBody);
      this.b.put(Long.valueOf(localBody.getID()), localBody);
      com.a.b.b localb = c.a(localBody);
      return localb;
    }
    finally
    {
      this.a.unlock();
    }
  }

  public void b(com.a.b.b paramb)
  {
    this.a.lock();
    try
    {
      Body localBody = (Body)this.b.get(paramb.a());
      if (localBody == null)
        throw new IllegalArgumentException("No body with ID " + paramb.a() + '.');
      c.a(localBody, paramb);
    }
    finally
    {
      this.a.unlock();
    }
  }

  public com.a.b.b a(long paramLong)
  {
    this.a.lock();
    try
    {
      Body localBody = (Body)this.b.remove(Long.valueOf(paramLong));
      if (localBody == null)
      {
        localb = null;
        return localb;
      }
      this.c.remove(localBody);
      com.a.b.b localb = c.a(localBody);
      return localb;
    }
    finally
    {
      this.a.unlock();
    }
  }

  public com.a.b.b b(long paramLong)
  {
    this.a.lock();
    try
    {
      com.a.b.b localb = c(paramLong);
      return localb;
    }
    finally
    {
      this.a.unlock();
    }
  }

  private com.a.b.b c(long paramLong)
  {
    Body localBody = (Body)this.b.get(Long.valueOf(paramLong));
    return localBody == null ? null : c.a(localBody);
  }

  public void a()
  {
    this.a.lock();
    try
    {
      this.c.step();
      c();
    }
    finally
    {
      this.a.unlock();
    }
  }

  private void c()
  {
    Iterator localIterator = this.b.values().iterator();
    while (localIterator.hasNext())
    {
      Body localBody = (Body)localIterator.next();
      while (localBody.getRotation() > 3.141592653589793D)
        localBody.setRotation(localBody.getRotation() - 6.283186F);
      while (localBody.getRotation() < -3.141592653589793D)
        localBody.setRotation(localBody.getRotation() + 6.283186F);
    }
  }

  public void a(com.a.b.a parama)
  {
    this.a.lock();
    try
    {
      this.c.addListener(new a(this, parama));
    }
    finally
    {
      this.a.unlock();
    }
  }

  public int b()
  {
    return 60;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.a.b.a.a.b
 * JD-Core Version:    0.6.2
 */