package com.google.common.cache;

import com.google.common.annotations.Beta;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.util.Map.Entry;

@Beta
public final class RemovalNotification
  implements Map.Entry
{
  private final Object key;
  private final Object value;
  private final RemovalCause cause;
  private static final long serialVersionUID = 0L;

  RemovalNotification(Object paramObject1, Object paramObject2, RemovalCause paramRemovalCause)
  {
    this.key = paramObject1;
    this.value = paramObject2;
    this.cause = ((RemovalCause)Preconditions.checkNotNull(paramRemovalCause));
  }

  public RemovalCause getCause()
  {
    return this.cause;
  }

  public boolean wasEvicted()
  {
    return this.cause.wasEvicted();
  }

  public Object getKey()
  {
    return this.key;
  }

  public Object getValue()
  {
    return this.value;
  }

  public final Object setValue(Object paramObject)
  {
    throw new UnsupportedOperationException();
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof Map.Entry))
    {
      Map.Entry localEntry = (Map.Entry)paramObject;
      return (Objects.equal(getKey(), localEntry.getKey())) && (Objects.equal(getValue(), localEntry.getValue()));
    }
    return false;
  }

  public int hashCode()
  {
    Object localObject1 = getKey();
    Object localObject2 = getValue();
    return (localObject1 == null ? 0 : localObject1.hashCode()) ^ (localObject2 == null ? 0 : localObject2.hashCode());
  }

  public String toString()
  {
    return getKey() + "=" + getValue();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.cache.RemovalNotification
 * JD-Core Version:    0.6.2
 */