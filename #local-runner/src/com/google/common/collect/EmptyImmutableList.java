package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

@GwtCompatible(serializable=true, emulated=true)
final class EmptyImmutableList extends ImmutableList
{
  static final EmptyImmutableList INSTANCE = new EmptyImmutableList();
  static final UnmodifiableListIterator ITERATOR = new UnmodifiableListIterator()
  {
    public boolean hasNext()
    {
      return false;
    }

    public boolean hasPrevious()
    {
      return false;
    }

    public Object next()
    {
      throw new NoSuchElementException();
    }

    public int nextIndex()
    {
      return 0;
    }

    public Object previous()
    {
      throw new NoSuchElementException();
    }

    public int previousIndex()
    {
      return -1;
    }
  };
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

  boolean isPartialView()
  {
    return false;
  }

  public boolean contains(Object paramObject)
  {
    return false;
  }

  public UnmodifiableIterator iterator()
  {
    return Iterators.emptyIterator();
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

  public Object get(int paramInt)
  {
    Preconditions.checkElementIndex(paramInt, 0);
    throw new AssertionError("unreachable");
  }

  public int indexOf(Object paramObject)
  {
    return -1;
  }

  public int lastIndexOf(Object paramObject)
  {
    return -1;
  }

  public ImmutableList subList(int paramInt1, int paramInt2)
  {
    Preconditions.checkPositionIndexes(paramInt1, paramInt2, 0);
    return this;
  }

  public ImmutableList reverse()
  {
    return this;
  }

  public UnmodifiableListIterator listIterator()
  {
    return ITERATOR;
  }

  public UnmodifiableListIterator listIterator(int paramInt)
  {
    Preconditions.checkPositionIndex(paramInt, 0);
    return ITERATOR;
  }

  public boolean containsAll(Collection paramCollection)
  {
    return paramCollection.isEmpty();
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof List))
    {
      List localList = (List)paramObject;
      return localList.isEmpty();
    }
    return false;
  }

  public int hashCode()
  {
    return 1;
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
 * Qualified Name:     com.google.common.collect.EmptyImmutableList
 * JD-Core Version:    0.6.2
 */