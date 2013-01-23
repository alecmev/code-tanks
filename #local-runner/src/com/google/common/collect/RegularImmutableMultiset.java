package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Map.Entry;

@GwtCompatible(serializable=true)
class RegularImmutableMultiset extends ImmutableMultiset
{
  private final transient ImmutableMap map;
  private final transient int size;

  RegularImmutableMultiset(ImmutableMap paramImmutableMap, int paramInt)
  {
    this.map = paramImmutableMap;
    this.size = paramInt;
  }

  boolean isPartialView()
  {
    return this.map.isPartialView();
  }

  public int count(Object paramObject)
  {
    Integer localInteger = (Integer)this.map.get(paramObject);
    return localInteger == null ? 0 : localInteger.intValue();
  }

  public int size()
  {
    return this.size;
  }

  public boolean contains(Object paramObject)
  {
    return this.map.containsKey(paramObject);
  }

  public ImmutableSet elementSet()
  {
    return this.map.keySet();
  }

  private static Multiset.Entry entryFromMapEntry(Map.Entry paramEntry)
  {
    return Multisets.immutableEntry(paramEntry.getKey(), ((Integer)paramEntry.getValue()).intValue());
  }

  ImmutableSet createEntrySet()
  {
    // Byte code:
    //   0: new 8	com/google/common/collect/RegularImmutableMultiset$1
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 24	com/google/common/collect/RegularImmutableMultiset$1:<init>	(Lcom/google/common/collect/RegularImmutableMultiset;)V
    //   8: areturn
  }

  public int hashCode()
  {
    return this.map.hashCode();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.RegularImmutableMultiset
 * JD-Core Version:    0.6.2
 */