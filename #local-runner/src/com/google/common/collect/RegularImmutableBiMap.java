package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Iterator;
import java.util.Map.Entry;

@GwtCompatible(serializable=true, emulated=true)
class RegularImmutableBiMap extends ImmutableBiMap
{
  final transient ImmutableMap delegate;
  final transient ImmutableBiMap inverse;

  RegularImmutableBiMap(ImmutableMap paramImmutableMap)
  {
    this.delegate = paramImmutableMap;
    ImmutableMap.Builder localBuilder = ImmutableMap.builder();
    Object localObject = paramImmutableMap.entrySet().iterator();
    while (((Iterator)localObject).hasNext())
    {
      Map.Entry localEntry = (Map.Entry)((Iterator)localObject).next();
      localBuilder.put(localEntry.getValue(), localEntry.getKey());
    }
    localObject = localBuilder.build();
    this.inverse = new RegularImmutableBiMap((ImmutableMap)localObject, this);
  }

  RegularImmutableBiMap(ImmutableMap paramImmutableMap, ImmutableBiMap paramImmutableBiMap)
  {
    this.delegate = paramImmutableMap;
    this.inverse = paramImmutableBiMap;
  }

  ImmutableMap delegate()
  {
    return this.delegate;
  }

  public ImmutableBiMap inverse()
  {
    return this.inverse;
  }

  boolean isPartialView()
  {
    return (this.delegate.isPartialView()) || (this.inverse.delegate().isPartialView());
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.RegularImmutableBiMap
 * JD-Core Version:    0.6.2
 */