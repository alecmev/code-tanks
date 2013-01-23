package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Map;

@GwtCompatible
public abstract interface ClassToInstanceMap extends Map
{
  public abstract Object getInstance(Class paramClass);

  public abstract Object putInstance(Class paramClass, Object paramObject);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.ClassToInstanceMap
 * JD-Core Version:    0.6.2
 */