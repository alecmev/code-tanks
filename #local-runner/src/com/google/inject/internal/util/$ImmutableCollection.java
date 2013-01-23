package com.google.inject.internal.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class $ImmutableCollection
  implements Serializable, Collection
{
  static final ImmutableCollection EMPTY_IMMUTABLE_COLLECTION = new EmptyImmutableCollection(null);
  private static final Object[] EMPTY_ARRAY = new Object[0];
  private static final .UnmodifiableIterator EMPTY_ITERATOR = new $UnmodifiableIterator()
  {
    public boolean hasNext()
    {
      return false;
    }

    public Object next()
    {
      throw new NoSuchElementException();
    }
  };

  public abstract $UnmodifiableIterator iterator();

  public Object[] toArray()
  {
    Object[] arrayOfObject = new Object[size()];
    return toArray(arrayOfObject);
  }

  public Object[] toArray(Object[] paramArrayOfObject)
  {
    int i = size();
    if (paramArrayOfObject.length < i)
      paramArrayOfObject = $ObjectArrays.newArray(paramArrayOfObject, i);
    else if (paramArrayOfObject.length > i)
      paramArrayOfObject[i] = null;
    int j = 0;
    Iterator localIterator = iterator();
    while (localIterator.hasNext())
    {
      Object localObject1 = localIterator.next();
      Object localObject2 = localObject1;
      paramArrayOfObject[(j++)] = localObject2;
    }
    return paramArrayOfObject;
  }

  public boolean contains(@$Nullable Object paramObject)
  {
    if (paramObject == null)
      return false;
    Iterator localIterator = iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      if (localObject.equals(paramObject))
        return true;
    }
    return false;
  }

  public boolean containsAll(Collection paramCollection)
  {
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      if (!contains(localObject))
        return false;
    }
    return true;
  }

  public boolean isEmpty()
  {
    return size() == 0;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(size() * 16);
    localStringBuilder.append('[');
    $UnmodifiableIterator localUnmodifiableIterator = iterator();
    if (localUnmodifiableIterator.hasNext())
      localStringBuilder.append(localUnmodifiableIterator.next());
    while (localUnmodifiableIterator.hasNext())
    {
      localStringBuilder.append(", ");
      localStringBuilder.append(localUnmodifiableIterator.next());
    }
    return ']';
  }

  public final boolean add(Object paramObject)
  {
    throw new UnsupportedOperationException();
  }

  public final boolean remove(Object paramObject)
  {
    throw new UnsupportedOperationException();
  }

  public final boolean addAll(Collection paramCollection)
  {
    throw new UnsupportedOperationException();
  }

  public final boolean removeAll(Collection paramCollection)
  {
    throw new UnsupportedOperationException();
  }

  public final boolean retainAll(Collection paramCollection)
  {
    throw new UnsupportedOperationException();
  }

  public final void clear()
  {
    throw new UnsupportedOperationException();
  }

  Object writeReplace()
  {
    return new SerializedForm(toArray());
  }

  private static class SerializedForm
    implements Serializable
  {
    final Object[] elements;
    private static final long serialVersionUID = 0L;

    SerializedForm(Object[] paramArrayOfObject)
    {
      this.elements = paramArrayOfObject;
    }

    Object readResolve()
    {
      return this.elements.length == 0 ? .ImmutableCollection.EMPTY_IMMUTABLE_COLLECTION : new $ImmutableCollection.ArrayImmutableCollection((Object[])this.elements.clone());
    }
  }

  private static class ArrayImmutableCollection extends $ImmutableCollection
  {
    private final Object[] elements;

    ArrayImmutableCollection(Object[] paramArrayOfObject)
    {
      this.elements = paramArrayOfObject;
    }

    public int size()
    {
      return this.elements.length;
    }

    public boolean isEmpty()
    {
      return false;
    }

    public .UnmodifiableIterator iterator()
    {
      return new $UnmodifiableIterator()
      {
        int i = 0;

        public boolean hasNext()
        {
          return this.i < .ImmutableCollection.ArrayImmutableCollection.this.elements.length;
        }

        public Object next()
        {
          if (!hasNext())
            throw new NoSuchElementException();
          return .ImmutableCollection.ArrayImmutableCollection.this.elements[(this.i++)];
        }
      };
    }
  }

  private static class EmptyImmutableCollection extends $ImmutableCollection
  {
    public int size()
    {
      return 0;
    }

    public boolean isEmpty()
    {
      return true;
    }

    public boolean contains(@$Nullable Object paramObject)
    {
      return false;
    }

    public .UnmodifiableIterator iterator()
    {
      return .ImmutableCollection.EMPTY_ITERATOR;
    }

    public Object[] toArray()
    {
      return .ImmutableCollection.EMPTY_ARRAY;
    }

    public Object[] toArray(Object[] paramArrayOfObject)
    {
      if (paramArrayOfObject.length > 0)
        paramArrayOfObject[0] = null;
      return paramArrayOfObject;
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.util..ImmutableCollection
 * JD-Core Version:    0.6.2
 */