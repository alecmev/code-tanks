package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;

@GwtCompatible(serializable=true)
final class NullsFirstOrdering extends Ordering
  implements Serializable
{
  final Ordering ordering;
  private static final long serialVersionUID = 0L;

  NullsFirstOrdering(Ordering paramOrdering)
  {
    this.ordering = paramOrdering;
  }

  public int compare(Object paramObject1, Object paramObject2)
  {
    if (paramObject1 == paramObject2)
      return 0;
    if (paramObject1 == null)
      return -1;
    if (paramObject2 == null)
      return 1;
    return this.ordering.compare(paramObject1, paramObject2);
  }

  public Ordering reverse()
  {
    return this.ordering.reverse().nullsLast();
  }

  public Ordering nullsFirst()
  {
    return this;
  }

  public Ordering nullsLast()
  {
    return this.ordering.nullsLast();
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == this)
      return true;
    if ((paramObject instanceof NullsFirstOrdering))
    {
      NullsFirstOrdering localNullsFirstOrdering = (NullsFirstOrdering)paramObject;
      return this.ordering.equals(localNullsFirstOrdering.ordering);
    }
    return false;
  }

  public int hashCode()
  {
    return this.ordering.hashCode() ^ 0x39153A74;
  }

  public String toString()
  {
    return this.ordering + ".nullsFirst()";
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.NullsFirstOrdering
 * JD-Core Version:    0.6.2
 */