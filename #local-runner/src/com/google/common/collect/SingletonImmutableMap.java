package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@GwtCompatible(serializable=true, emulated=true)
final class SingletonImmutableMap extends ImmutableMap
{
  final transient Object singleKey;
  final transient Object singleValue;
  private transient Map.Entry entry;

  SingletonImmutableMap(Object paramObject1, Object paramObject2)
  {
    this.singleKey = paramObject1;
    this.singleValue = paramObject2;
  }

  SingletonImmutableMap(Map.Entry paramEntry)
  {
    this.entry = paramEntry;
    this.singleKey = paramEntry.getKey();
    this.singleValue = paramEntry.getValue();
  }

  private Map.Entry entry()
  {
    Map.Entry localEntry = this.entry;
    return localEntry == null ? (this.entry = Maps.immutableEntry(this.singleKey, this.singleValue)) : localEntry;
  }

  public Object get(Object paramObject)
  {
    return this.singleKey.equals(paramObject) ? this.singleValue : null;
  }

  public int size()
  {
    return 1;
  }

  public boolean isEmpty()
  {
    return false;
  }

  public boolean containsKey(Object paramObject)
  {
    return this.singleKey.equals(paramObject);
  }

  public boolean containsValue(Object paramObject)
  {
    return this.singleValue.equals(paramObject);
  }

  boolean isPartialView()
  {
    return false;
  }

  ImmutableSet createEntrySet()
  {
    return ImmutableSet.of(entry());
  }

  ImmutableSet createKeySet()
  {
    return ImmutableSet.of(this.singleKey);
  }

  ImmutableCollection createValues()
  {
    return ImmutableList.of(this.singleValue);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == this)
      return true;
    if ((paramObject instanceof Map))
    {
      Map localMap = (Map)paramObject;
      if (localMap.size() != 1)
        return false;
      Map.Entry localEntry = (Map.Entry)localMap.entrySet().iterator().next();
      return (this.singleKey.equals(localEntry.getKey())) && (this.singleValue.equals(localEntry.getValue()));
    }
    return false;
  }

  public int hashCode()
  {
    return this.singleKey.hashCode() ^ this.singleValue.hashCode();
  }

  public String toString()
  {
    return '{' + this.singleKey.toString() + '=' + this.singleValue.toString() + '}';
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.SingletonImmutableMap
 * JD-Core Version:    0.6.2
 */