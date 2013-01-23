package com.google.inject.internal.util;

import java.util.Map.Entry;

public abstract class $AbstractMapEntry
  implements Map.Entry
{
  public abstract Object getKey();

  public abstract Object getValue();

  public Object setValue(Object paramObject)
  {
    throw new UnsupportedOperationException();
  }

  public boolean equals(@$Nullable Object paramObject)
  {
    if ((paramObject instanceof Map.Entry))
    {
      Map.Entry localEntry = (Map.Entry)paramObject;
      return ($Objects.equal(getKey(), localEntry.getKey())) && ($Objects.equal(getValue(), localEntry.getValue()));
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
 * Qualified Name:     com.google.inject.internal.util..AbstractMapEntry
 * JD-Core Version:    0.6.2
 */