package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import java.util.Map.Entry;

@GwtCompatible
abstract class AbstractMapEntry
  implements Map.Entry
{
  public abstract Object getKey();

  public abstract Object getValue();

  public Object setValue(Object paramObject)
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
 * Qualified Name:     com.google.common.collect.AbstractMapEntry
 * JD-Core Version:    0.6.2
 */