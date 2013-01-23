package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Set;

@GwtCompatible(serializable=true, emulated=true)
class EmptyImmutableSortedSet extends ImmutableSortedSet
{
  private static final Object[] EMPTY_ARRAY = new Object[0];

  EmptyImmutableSortedSet(Comparator paramComparator)
  {
    super(paramComparator);
  }

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

  public int hashCode()
  {
    return 0;
  }

  public String toString()
  {
    return "[]";
  }

  public Object first()
  {
    throw new NoSuchElementException();
  }

  public Object last()
  {
    throw new NoSuchElementException();
  }

  ImmutableSortedSet headSetImpl(Object paramObject, boolean paramBoolean)
  {
    return this;
  }

  ImmutableSortedSet subSetImpl(Object paramObject1, boolean paramBoolean1, Object paramObject2, boolean paramBoolean2)
  {
    return this;
  }

  ImmutableSortedSet tailSetImpl(Object paramObject, boolean paramBoolean)
  {
    return this;
  }

  int indexOf(Object paramObject)
  {
    return -1;
  }

  ImmutableSortedSet createDescendingSet()
  {
    return new EmptyImmutableSortedSet(Ordering.from(this.comparator).reverse());
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.EmptyImmutableSortedSet
 * JD-Core Version:    0.6.2
 */