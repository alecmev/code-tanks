package com.google.inject.internal.util;

import java.io.Serializable;

class $ImmutableEntry extends $AbstractMapEntry
  implements Serializable
{
  private final Object key;
  private final Object value;
  private static final long serialVersionUID = 0L;

  $ImmutableEntry(@$Nullable Object paramObject1, @$Nullable Object paramObject2)
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
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.util..ImmutableEntry
 * JD-Core Version:    0.6.2
 */