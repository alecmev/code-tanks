package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;

@GwtCompatible(serializable=true)
class ImmutableEntry extends AbstractMapEntry
  implements Serializable
{
  private final Object key;
  private final Object value;
  private static final long serialVersionUID = 0L;

  ImmutableEntry(Object paramObject1, Object paramObject2)
  {
    this.key = paramObject1;
    this.value = paramObject2;
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
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.ImmutableEntry
 * JD-Core Version:    0.6.2
 */