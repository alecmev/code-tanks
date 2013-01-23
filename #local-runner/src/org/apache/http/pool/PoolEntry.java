package org.apache.http.pool;

import java.util.concurrent.TimeUnit;

public abstract class PoolEntry
{
  private final String id;
  private final Object route;
  private final Object conn;
  private final long created;
  private final long validUnit;
  private long updated;
  private long expiry;
  private volatile Object state;

  public PoolEntry(String paramString, Object paramObject1, Object paramObject2, long paramLong, TimeUnit paramTimeUnit)
  {
    if (paramObject1 == null)
      throw new IllegalArgumentException("Route may not be null");
    if (paramObject2 == null)
      throw new IllegalArgumentException("Connection may not be null");
    if (paramTimeUnit == null)
      throw new IllegalArgumentException("Time unit may not be null");
    this.id = paramString;
    this.route = paramObject1;
    this.conn = paramObject2;
    this.created = System.currentTimeMillis();
    if (paramLong > 0L)
      this.validUnit = (this.created + paramTimeUnit.toMillis(paramLong));
    else
      this.validUnit = 9223372036854775807L;
    this.expiry = this.validUnit;
  }

  public Object getRoute()
  {
    return this.route;
  }

  public Object getConnection()
  {
    return this.conn;
  }

  public void setState(Object paramObject)
  {
    this.state = paramObject;
  }

  public synchronized long getExpiry()
  {
    return this.expiry;
  }

  public synchronized void updateExpiry(long paramLong, TimeUnit paramTimeUnit)
  {
    if (paramTimeUnit == null)
      throw new IllegalArgumentException("Time unit may not be null");
    this.updated = System.currentTimeMillis();
    long l;
    if (paramLong > 0L)
      l = this.updated + paramTimeUnit.toMillis(paramLong);
    else
      l = 9223372036854775807L;
    this.expiry = Math.min(l, this.validUnit);
  }

  public synchronized boolean isExpired(long paramLong)
  {
    return paramLong >= this.expiry;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[id:");
    localStringBuilder.append(this.id);
    localStringBuilder.append("][route:");
    localStringBuilder.append(this.route);
    localStringBuilder.append("][state:");
    localStringBuilder.append(this.state);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.pool.PoolEntry
 * JD-Core Version:    0.6.2
 */