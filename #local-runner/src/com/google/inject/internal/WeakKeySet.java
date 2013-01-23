package com.google.inject.internal;

import com.google.inject.Key;
import com.google.inject.internal.util..Maps;
import com.google.inject.internal.util..Sets;
import com.google.inject.internal.util..SourceProvider;
import java.util.Map;
import java.util.Set;

final class WeakKeySet
{
  private Map backingSet;

  public void add(Key paramKey, Object paramObject)
  {
    if (this.backingSet == null)
      this.backingSet = $Maps.newHashMap();
    if (((paramObject instanceof Class)) || (paramObject == $SourceProvider.UNKNOWN_SOURCE))
      paramObject = null;
    String str = paramKey.toString();
    Object localObject = (Set)this.backingSet.get(str);
    if (localObject == null)
    {
      localObject = $Sets.newLinkedHashSet();
      this.backingSet.put(str, localObject);
    }
    ((Set)localObject).add(Errors.convert(paramObject));
  }

  public boolean contains(Key paramKey)
  {
    return (this.backingSet != null) && (this.backingSet.containsKey(paramKey.toString()));
  }

  public Set getSources(Key paramKey)
  {
    return (Set)this.backingSet.get(paramKey.toString());
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.WeakKeySet
 * JD-Core Version:    0.6.2
 */