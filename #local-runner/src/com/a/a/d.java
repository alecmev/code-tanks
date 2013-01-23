package com.a.a;

import java.util.concurrent.atomic.AtomicLong;

public abstract class d
{
  private static final AtomicLong a = new AtomicLong();
  private final long b = a.incrementAndGet();
  private com.a.b.b c = new com.a.b.b();

  protected d(com.a.b.b.b paramb)
  {
    this.c.a(getClass().getSimpleName() + '#' + this.b);
    this.c.a(paramb);
  }

  public final long c()
  {
    return this.b;
  }

  public com.a.b.b d()
  {
    return this.c;
  }

  public void a(com.a.b.b paramb)
  {
    this.c = paramb;
  }

  public final boolean equals(Object paramObject)
  {
    return (this == paramObject) || ((getClass() == paramObject.getClass()) && (this.b == ((d)paramObject).b));
  }

  public final int hashCode()
  {
    return Long.valueOf(this.b).hashCode();
  }

  public String toString()
  {
    return String.format("%s {id=%d, body.name='%s'}", new Object[] { getClass().getSimpleName(), Long.valueOf(this.b), this.c == null ? null : this.c.b() });
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.a.a.d
 * JD-Core Version:    0.6.2
 */