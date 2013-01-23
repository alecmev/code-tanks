package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Collection;
import java.util.Set;

@GwtCompatible(serializable=true, emulated=true)
final class EmptyImmutableSet extends ImmutableSet
{
  static final EmptyImmutableSet INSTANCE = new EmptyImmutableSet();
  private static final Object[] EMPTY_ARRAY = new Object[0];
  private static final long serialVersionUID = 0L;

  public int size()
  {
    return 0;
  }

  public boolean isEmpty()
  {
    return true;
  }

  public boolean contains(Object paramObject)
  {
    return false;
  }

  public UnmodifiableIterator iterator()
  {
    return Iterators.emptyIterator();
  }

  boolean isPartialView()
  {
    return false;
  }

  public Object[] toArray()
  {
    return EMPTY_ARRAY;
  }

  public Object[] toArray(Object[] paramArrayOfObject)
  {
    if (paramArrayOfObject.length > 0)
      paramArrayOfObject[0] = null;
    return paramArrayOfObject;
  }

  public boolean containsAll(Collection paramCollection)
  {
    return paramCollection.isEmpty();
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof Set))
    {
      Set localSet = (Set)paramObject;
      return localSet.isEmpty();
    }
    return false;
  }

  public final int hashCode()
  {
    return 0;
  }

  boolean isHashCodeFast()
  {
    return true;
  }

  public String toString()
  {
    return "[]";
  }

  Object readResolve()
  {
    return INSTANCE;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.EmptyImmutableSet
 * JD-Core Version:    0.6.2
 */