package com.google.inject.internal.util;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;

public abstract class $ImmutableList extends $ImmutableCollection
  implements List, RandomAccess
{
  private static final ImmutableList EMPTY_IMMUTABLE_LIST = new EmptyImmutableList(null);

  public static ImmutableList of()
  {
    return EMPTY_IMMUTABLE_LIST;
  }

  public static ImmutableList of(Object paramObject)
  {
    return new RegularImmutableList(copyIntoArray(new Object[] { paramObject }), null);
  }

  public static ImmutableList of(Object paramObject1, Object paramObject2)
  {
    return new RegularImmutableList(copyIntoArray(new Object[] { paramObject1, paramObject2 }), null);
  }

  public static ImmutableList of(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    return new RegularImmutableList(copyIntoArray(new Object[] { paramObject1, paramObject2, paramObject3 }), null);
  }

  public static ImmutableList of(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4)
  {
    return new RegularImmutableList(copyIntoArray(new Object[] { paramObject1, paramObject2, paramObject3, paramObject4 }), null);
  }

  public static ImmutableList of(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5)
  {
    return new RegularImmutableList(copyIntoArray(new Object[] { paramObject1, paramObject2, paramObject3, paramObject4, paramObject5 }), null);
  }

  public static ImmutableList of(Object[] paramArrayOfObject)
  {
    return paramArrayOfObject.length == 0 ? of() : new RegularImmutableList(copyIntoArray(paramArrayOfObject), null);
  }

  public static ImmutableList copyOf(Iterable paramIterable)
  {
    Object localObject;
    if ((paramIterable instanceof ImmutableList))
    {
      localObject = (ImmutableList)paramIterable;
      return localObject;
    }
    if ((paramIterable instanceof Collection))
    {
      localObject = (Collection)paramIterable;
      return copyOfInternal((Collection)localObject);
    }
    return copyOfInternal($Lists.newArrayList(paramIterable));
  }

  public static ImmutableList copyOf(Iterator paramIterator)
  {
    return copyOfInternal($Lists.newArrayList(paramIterator));
  }

  private static ImmutableList copyOfInternal(ArrayList paramArrayList)
  {
    return paramArrayList.isEmpty() ? of() : new RegularImmutableList(nullChecked(paramArrayList.toArray()), null);
  }

  private static Object[] nullChecked(Object[] paramArrayOfObject)
  {
    int i = 0;
    int j = paramArrayOfObject.length;
    while (i < j)
    {
      if (paramArrayOfObject[i] == null)
        throw new NullPointerException("at index " + i);
      i++;
    }
    return paramArrayOfObject;
  }

  private static ImmutableList copyOfInternal(Collection paramCollection)
  {
    int i = paramCollection.size();
    return i == 0 ? of() : createFromIterable(paramCollection, i);
  }

  public abstract $UnmodifiableIterator iterator();

  public abstract int indexOf(@$Nullable Object paramObject);

  public abstract int lastIndexOf(@$Nullable Object paramObject);

  public abstract ImmutableList subList(int paramInt1, int paramInt2);

  public final boolean addAll(int paramInt, Collection paramCollection)
  {
    throw new UnsupportedOperationException();
  }

  public final Object set(int paramInt, Object paramObject)
  {
    throw new UnsupportedOperationException();
  }

  public final void add(int paramInt, Object paramObject)
  {
    throw new UnsupportedOperationException();
  }

  public final Object remove(int paramInt)
  {
    throw new UnsupportedOperationException();
  }

  private static Object[] copyIntoArray(Object[] paramArrayOfObject)
  {
    Object[] arrayOfObject1 = new Object[paramArrayOfObject.length];
    int i = 0;
    for (Object localObject : paramArrayOfObject)
    {
      if (localObject == null)
        throw new NullPointerException("at index " + i);
      arrayOfObject1[(i++)] = localObject;
    }
    return arrayOfObject1;
  }

  private static ImmutableList createFromIterable(Iterable paramIterable, int paramInt)
  {
    Object[] arrayOfObject = new Object[paramInt];
    int i = 0;
    Iterator localIterator = paramIterable.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      if (i == paramInt)
      {
        paramInt = (paramInt / 2 + 1) * 3;
        arrayOfObject = copyOf(arrayOfObject, paramInt);
      }
      if (localObject == null)
        throw new NullPointerException("at index " + i);
      arrayOfObject[(i++)] = localObject;
    }
    if (i == 0)
      return of();
    if (i != paramInt)
      arrayOfObject = copyOf(arrayOfObject, i);
    return new RegularImmutableList(arrayOfObject, 0, i, null);
  }

  private static Object[] copyOf(Object[] paramArrayOfObject, int paramInt)
  {
    Object[] arrayOfObject = new Object[paramInt];
    System.arraycopy(paramArrayOfObject, 0, arrayOfObject, 0, Math.min(paramArrayOfObject.length, paramInt));
    return arrayOfObject;
  }

  private void readObject(ObjectInputStream paramObjectInputStream)
    throws InvalidObjectException
  {
    throw new InvalidObjectException("Use SerializedForm");
  }

  Object writeReplace()
  {
    return new SerializedForm(toArray());
  }

  public static Builder builder()
  {
    return new Builder();
  }

  public static class Builder
  {
    private final ArrayList contents = $Lists.newArrayList();

    public Builder add(Object paramObject)
    {
      $Preconditions.checkNotNull(paramObject, "element cannot be null");
      this.contents.add(paramObject);
      return this;
    }

    public Builder addAll(Iterable paramIterable)
    {
      if ((paramIterable instanceof Collection))
      {
        localObject1 = (Collection)paramIterable;
        this.contents.ensureCapacity(this.contents.size() + ((Collection)localObject1).size());
      }
      Object localObject1 = paramIterable.iterator();
      while (((Iterator)localObject1).hasNext())
      {
        Object localObject2 = ((Iterator)localObject1).next();
        $Preconditions.checkNotNull(localObject2, "elements contains a null");
        this.contents.add(localObject2);
      }
      return this;
    }

    public .ImmutableList build()
    {
      return .ImmutableList.copyOf(this.contents);
    }
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
      return .ImmutableList.of(this.elements);
    }
  }

  private static final class RegularImmutableList extends $ImmutableList
  {
    private final int offset;
    private final int size;
    private final Object[] array;

    private RegularImmutableList(Object[] paramArrayOfObject, int paramInt1, int paramInt2)
    {
      super();
      this.offset = paramInt1;
      this.size = paramInt2;
      this.array = paramArrayOfObject;
    }

    private RegularImmutableList(Object[] paramArrayOfObject)
    {
      this(paramArrayOfObject, 0, paramArrayOfObject.length);
    }

    public int size()
    {
      return this.size;
    }

    public boolean isEmpty()
    {
      return false;
    }

    public boolean contains(Object paramObject)
    {
      return indexOf(paramObject) != -1;
    }

    public .UnmodifiableIterator iterator()
    {
      return .Iterators.forArray(this.array, this.offset, this.size);
    }

    public Object[] toArray()
    {
      Object[] arrayOfObject = new Object[size()];
      System.arraycopy(this.array, this.offset, arrayOfObject, 0, this.size);
      return arrayOfObject;
    }

    public Object[] toArray(Object[] paramArrayOfObject)
    {
      if (paramArrayOfObject.length < this.size)
        paramArrayOfObject = $ObjectArrays.newArray(paramArrayOfObject, this.size);
      else if (paramArrayOfObject.length > this.size)
        paramArrayOfObject[this.size] = null;
      System.arraycopy(this.array, this.offset, paramArrayOfObject, 0, this.size);
      return paramArrayOfObject;
    }

    public Object get(int paramInt)
    {
      $Preconditions.checkElementIndex(paramInt, this.size);
      return this.array[(paramInt + this.offset)];
    }

    public int indexOf(Object paramObject)
    {
      if (paramObject != null)
        for (int i = this.offset; i < this.offset + this.size; i++)
          if (this.array[i].equals(paramObject))
            return i - this.offset;
      return -1;
    }

    public int lastIndexOf(Object paramObject)
    {
      if (paramObject != null)
        for (int i = this.offset + this.size - 1; i >= this.offset; i--)
          if (this.array[i].equals(paramObject))
            return i - this.offset;
      return -1;
    }

    public .ImmutableList subList(int paramInt1, int paramInt2)
    {
      $Preconditions.checkPositionIndexes(paramInt1, paramInt2, this.size);
      return paramInt1 == paramInt2 ? .ImmutableList.of() : new RegularImmutableList(this.array, this.offset + paramInt1, paramInt2 - paramInt1);
    }

    public ListIterator listIterator()
    {
      return listIterator(0);
    }

    public ListIterator listIterator(final int paramInt)
    {
      $Preconditions.checkPositionIndex(paramInt, this.size);
      return new ListIterator()
      {
        int index = paramInt;

        public boolean hasNext()
        {
          return this.index < .ImmutableList.RegularImmutableList.this.size;
        }

        public boolean hasPrevious()
        {
          return this.index > 0;
        }

        public int nextIndex()
        {
          return this.index;
        }

        public int previousIndex()
        {
          return this.index - 1;
        }

        public Object next()
        {
          Object localObject;
          try
          {
            localObject = $ImmutableList.RegularImmutableList.this.get(this.index);
          }
          catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
          {
            throw new NoSuchElementException();
          }
          this.index += 1;
          return localObject;
        }

        public Object previous()
        {
          Object localObject;
          try
          {
            localObject = $ImmutableList.RegularImmutableList.this.get(this.index - 1);
          }
          catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
          {
            throw new NoSuchElementException();
          }
          this.index -= 1;
          return localObject;
        }

        public void set(Object paramAnonymousObject)
        {
          throw new UnsupportedOperationException();
        }

        public void add(Object paramAnonymousObject)
        {
          throw new UnsupportedOperationException();
        }

        public void remove()
        {
          throw new UnsupportedOperationException();
        }
      };
    }

    public boolean equals(@$Nullable Object paramObject)
    {
      if (paramObject == this)
        return true;
      if (!(paramObject instanceof List))
        return false;
      List localList = (List)paramObject;
      if (size() != localList.size())
        return false;
      int i = this.offset;
      Object localObject1;
      if ((paramObject instanceof RegularImmutableList))
      {
        localObject1 = (RegularImmutableList)paramObject;
        for (int j = ((RegularImmutableList)localObject1).offset; j < ((RegularImmutableList)localObject1).offset + ((RegularImmutableList)localObject1).size; j++)
          if (!this.array[(i++)].equals(localObject1.array[j]))
            return false;
      }
      else
      {
        localObject1 = localList.iterator();
        while (((Iterator)localObject1).hasNext())
        {
          Object localObject2 = ((Iterator)localObject1).next();
          if (!this.array[(i++)].equals(localObject2))
            return false;
        }
      }
      return true;
    }

    public int hashCode()
    {
      int i = 1;
      for (int j = this.offset; j < this.offset + this.size; j++)
        i = 31 * i + this.array[j].hashCode();
      return i;
    }

    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder(size() * 16);
      localStringBuilder.append('[').append(this.array[this.offset]);
      for (int i = this.offset + 1; i < this.offset + this.size; i++)
        localStringBuilder.append(", ").append(this.array[i]);
      return ']';
    }
  }

  private static final class EmptyImmutableList extends $ImmutableList
  {
    private static final Object[] EMPTY_ARRAY = new Object[0];

    private EmptyImmutableList()
    {
      super();
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

    public .UnmodifiableIterator iterator()
    {
      return .Iterators.emptyIterator();
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
      $Preconditions.checkElementIndex(paramInt, 0);
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

    public .ImmutableList subList(int paramInt1, int paramInt2)
    {
      $Preconditions.checkPositionIndexes(paramInt1, paramInt2, 0);
      return this;
    }

    public ListIterator listIterator()
    {
      return .Iterators.emptyListIterator();
    }

    public ListIterator listIterator(int paramInt)
    {
      $Preconditions.checkPositionIndex(paramInt, 0);
      return .Iterators.emptyListIterator();
    }

    public boolean containsAll(Collection paramCollection)
    {
      return paramCollection.isEmpty();
    }

    public boolean equals(@$Nullable Object paramObject)
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
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.util..ImmutableList
 * JD-Core Version:    0.6.2
 */