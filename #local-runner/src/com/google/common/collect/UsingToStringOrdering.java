package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;

@GwtCompatible(serializable=true)
final class UsingToStringOrdering extends Ordering
  implements Serializable
{
  static final UsingToStringOrdering INSTANCE = new UsingToStringOrdering();
  private static final long serialVersionUID = 0L;

  public int compare(Object paramObject1, Object paramObject2)
  {
    return paramObject1.toString().compareTo(paramObject2.toString());
  }

  private Object readResolve()
  {
    return INSTANCE;
  }

  public String toString()
  {
    return "Ordering.usingToString()";
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.UsingToStringOrdering
 * JD-Core Version:    0.6.2
 */