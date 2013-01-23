package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

@GwtCompatible(serializable=true, emulated=true)
public abstract class ImmutableList extends ImmutableCollection
  implements List, RandomAccess
{
  public static ImmutableList of()
  {
    return EmptyImmutableList.INSTANCE;
  }

  public static ImmutableList of(Object paramObject)
  {
    return new SingletonImmutableList(paramObject);
  }

  public static ImmutableList of(Object paramObject1, Object paramObject2)
  {
    return construct(new Object[] { paramObject1, paramObject2 });
  }

  public static ImmutableList of(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    return construct(new Object[] { paramObject1, paramObject2, paramObject3 });
  }

  public static ImmutableList of(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4)
  {
    return construct(new Object[] { paramObject1, paramObject2, paramObject3, paramObject4 });
  }

  public static ImmutableList of(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5)
  {
    return construct(new Object[] { paramObject1, paramObject2, paramObject3, paramObject4, paramObject5 });
  }

  public static ImmutableList of(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5, Object paramObject6)
  {
    return construct(new Object[] { paramObject1, paramObject2, paramObject3, paramObject4, paramObject5, paramObject6 });
  }

  public static ImmutableList of(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5, Object paramObject6, Object paramObject7)
  {
    return construct(new Object[] { paramObject1, paramObject2, paramObject3, paramObject4, paramObject5, paramObject6, paramObject7 });
  }

  public static ImmutableList of(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5, Object paramObject6, Object paramObject7, Object paramObject8)
  {
    return construct(new Object[] { paramObject1, paramObject2, paramObject3, paramObject4, paramObject5, paramObject6, paramObject7, paramObject8 });
  }

  public static ImmutableList of(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5, Object paramObject6, Object paramObject7, Object paramObject8, Object paramObject9)
  {
    return construct(new Object[] { paramObject1, paramObject2, paramObject3, paramObject4, paramObject5, paramObject6, paramObject7, paramObject8, paramObject9 });
  }

  public static ImmutableList of(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5, Object paramObject6, Object paramObject7, Object paramObject8, Object paramObject9, Object paramObject10)
  {
    return construct(new Object[] { paramObject1, paramObject2, paramObject3, paramObject4, paramObject5, paramObject6, paramObject7, paramObject8, paramObject9, paramObject10 });
  }

  public static ImmutableList of(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5, Object paramObject6, Object paramObject7, Object paramObject8, Object paramObject9, Object paramObject10, Object paramObject11)
  {
    return construct(new Object[] { paramObject1, paramObject2, paramObject3, paramObject4, paramObject5, paramObject6, paramObject7, paramObject8, paramObject9, paramObject10, paramObject11 });
  }

  public static ImmutableList of(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5, Object paramObject6, Object paramObject7, Object paramObject8, Object paramObject9, Object paramObject10, Object paramObject11, Object paramObject12, Object[] paramArrayOfObject)
  {
    Object[] arrayOfObject = new Object[12 + paramArrayOfObject.length];
    arrayOfObject[0] = paramObject1;
    arrayOfObject[1] = paramObject2;
    arrayOfObject[2] = paramObject3;
    arrayOfObject[3] = paramObject4;
    arrayOfObject[4] = paramObject5;
    arrayOfObject[5] = paramObject6;
    arrayOfObject[6] = paramObject7;
    arrayOfObject[7] = paramObject8;
    arrayOfObject[8] = paramObject9;
    arrayOfObject[9] = paramObject10;
    arrayOfObject[10] = paramObject11;
    arrayOfObject[11] = paramObject12;
    System.arraycopy(paramArrayOfObject, 0, arrayOfObject, 12, paramArrayOfObject.length);
    return construct(arrayOfObject);
  }

  public static ImmutableList copyOf(Iterable paramIterable)
  {
    Preconditions.checkNotNull(paramIterable);
    return (paramIterable instanceof Collection) ? copyOf(Collections2.cast(paramIterable)) : copyOf(paramIterable.iterator());
  }

  public static ImmutableList copyOf(Collection paramCollection)
  {
    if ((paramCollection instanceof ImmutableCollection))
    {
      ImmutableList localImmutableList = ((ImmutableCollection)paramCollection).asList();
      return localImmutableList.isPartialView() ? copyFromCollection(localImmutableList) : localImmutableList;
    }
    return copyFromCollection(paramCollection);
  }

  public static ImmutableList copyOf(Iterator paramIterator)
  {
    return copyFromCollection(Lists.newArrayList(paramIterator));
  }

  public static ImmutableList copyOf(Object[] paramArrayOfObject)
  {
    switch (paramArrayOfObject.length)
    {
    case 0:
      return of();
    case 1:
      return new SingletonImmutableList(paramArrayOfObject[0]);
    }
    return construct((Object[])paramArrayOfObject.clone());
  }

  private static ImmutableList copyFromCollection(Collection paramCollection)
  {
    Object[] arrayOfObject = paramCollection.toArray();
    switch (arrayOfObject.length)
    {
    case 0:
      return of();
    case 1:
      SingletonImmutableList localSingletonImmutableList = new SingletonImmutableList(arrayOfObject[0]);
      return localSingletonImmutableList;
    }
    return construct(arrayOfObject);
  }

  private static ImmutableList construct(Object[] paramArrayOfObject)
  {
    for (int i = 0; i < paramArrayOfObject.length; i++)
      checkElementNotNull(paramArrayOfObject[i], i);
    return new RegularImmutableList(paramArrayOfObject);
  }

  private static Object checkElementNotNull(Object paramObject, int paramInt)
  {
    if (paramObject == null)
      throw new NullPointerException("at index " + paramInt);
    return paramObject;
  }

  public UnmodifiableIterator iterator()
  {
    return listIterator();
  }

  public UnmodifiableListIterator listIterator()
  {
    return listIterator(0);
  }

  public UnmodifiableListIterator listIterator(int paramInt)
  {
    return new AbstractIndexedListIterator(size(), paramInt)
    {
      protected Object get(int paramAnonymousInt)
      {
        return ImmutableList.this.get(paramAnonymousInt);
      }
    };
  }

  public int indexOf(Object paramObject)
  {
    return paramObject == null ? -1 : Lists.indexOfImpl(this, paramObject);
  }

  public int lastIndexOf(Object paramObject)
  {
    return paramObject == null ? -1 : Lists.lastIndexOfImpl(this, paramObject);
  }

  public boolean contains(Object paramObject)
  {
    return indexOf(paramObject) >= 0;
  }

  public ImmutableList subList(int paramInt1, int paramInt2)
  {
    Preconditions.checkPositionIndexes(paramInt1, paramInt2, size());
    int i = paramInt2 - paramInt1;
    switch (i)
    {
    case 0:
      return of();
    case 1:
      return of(get(paramInt1));
    }
    return subListUnchecked(paramInt1, paramInt2);
  }

  ImmutableList subListUnchecked(int paramInt1, int paramInt2)
  {
    return new SubList(paramInt1, paramInt2 - paramInt1);
  }

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

  public ImmutableList asList()
  {
    return this;
  }

  public ImmutableList reverse()
  {
    return new ReverseImmutableList(this);
  }

  public boolean equals(Object paramObject)
  {
    return Lists.equalsImpl(this, paramObject);
  }

  public int hashCode()
  {
    return Lists.hashCodeImpl(this);
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

  public static final class Builder extends ImmutableCollection.Builder
  {
    private final ArrayList contents = Lists.newArrayList();

    public Builder add(Object paramObject)
    {
      this.contents.add(Preconditions.checkNotNull(paramObject));
      return this;
    }

    public Builder addAll(Iterable paramIterable)
    {
      if ((paramIterable instanceof Collection))
      {
        Collection localCollection = (Collection)paramIterable;
        this.contents.ensureCapacity(this.contents.size() + localCollection.size());
      }
      super.addAll(paramIterable);
      return this;
    }

    public Builder add(Object[] paramArrayOfObject)
    {
      this.contents.ensureCapacity(this.contents.size() + paramArrayOfObject.length);
      super.add(paramArrayOfObject);
      return this;
    }

    public Builder addAll(Iterator paramIterator)
    {
      super.addAll(paramIterator);
      return this;
    }

    public ImmutableList build()
    {
      return ImmutableList.copyOf(this.contents);
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
      return ImmutableList.copyOf(this.elements);
    }
  }

  private static class ReverseImmutableList extends ImmutableList
  {
    private final transient ImmutableList forwardList;
    private final transient int size;

    ReverseImmutableList(ImmutableList paramImmutableList)
    {
      this.forwardList = paramImmutableList;
      this.size = paramImmutableList.size();
    }

    private int reverseIndex(int paramInt)
    {
      return this.size - 1 - paramInt;
    }

    private int reversePosition(int paramInt)
    {
      return this.size - paramInt;
    }

    public ImmutableList reverse()
    {
      return this.forwardList;
    }

    public boolean contains(Object paramObject)
    {
      return this.forwardList.contains(paramObject);
    }

    public boolean containsAll(Collection paramCollection)
    {
      return this.forwardList.containsAll(paramCollection);
    }

    public int indexOf(Object paramObject)
    {
      int i = this.forwardList.lastIndexOf(paramObject);
      return i >= 0 ? reverseIndex(i) : -1;
    }

    public int lastIndexOf(Object paramObject)
    {
      int i = this.forwardList.indexOf(paramObject);
      return i >= 0 ? reverseIndex(i) : -1;
    }

    public ImmutableList subList(int paramInt1, int paramInt2)
    {
      Preconditions.checkPositionIndexes(paramInt1, paramInt2, this.size);
      return this.forwardList.subList(reversePosition(paramInt2), reversePosition(paramInt1)).reverse();
    }

    public Object get(int paramInt)
    {
      Preconditions.checkElementIndex(paramInt, this.size);
      return this.forwardList.get(reverseIndex(paramInt));
    }

    public UnmodifiableListIterator listIterator(int paramInt)
    {
      Preconditions.checkPositionIndex(paramInt, this.size);
      final UnmodifiableListIterator localUnmodifiableListIterator = this.forwardList.listIterator(reversePosition(paramInt));
      return new UnmodifiableListIterator()
      {
        public boolean hasNext()
        {
          return localUnmodifiableListIterator.hasPrevious();
        }

        public boolean hasPrevious()
        {
          return localUnmodifiableListIterator.hasNext();
        }

        public Object next()
        {
          return localUnmodifiableListIterator.previous();
        }

        public int nextIndex()
        {
          return ImmutableList.ReverseImmutableList.this.reverseIndex(localUnmodifiableListIterator.previousIndex());
        }

        public Object previous()
        {
          return localUnmodifiableListIterator.next();
        }

        public int previousIndex()
        {
          return ImmutableList.ReverseImmutableList.this.reverseIndex(localUnmodifiableListIterator.nextIndex());
        }
      };
    }

    public int size()
    {
      return this.size;
    }

    public boolean isEmpty()
    {
      return this.forwardList.isEmpty();
    }

    boolean isPartialView()
    {
      return this.forwardList.isPartialView();
    }
  }

  class SubList extends ImmutableList
  {
    final transient int offset;
    final transient int length;

    SubList(int paramInt1, int arg3)
    {
      this.offset = paramInt1;
      int i;
      this.length = i;
    }

    public int size()
    {
      return this.length;
    }

    public Object get(int paramInt)
    {
      Preconditions.checkElementIndex(paramInt, this.length);
      return ImmutableList.this.get(paramInt + this.offset);
    }

    public ImmutableList subList(int paramInt1, int paramInt2)
    {
      Preconditions.checkPositionIndexes(paramInt1, paramInt2, this.length);
      return ImmutableList.this.subList(paramInt1 + this.offset, paramInt2 + this.offset);
    }

    boolean isPartialView()
    {
      return true;
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.ImmutableList
 * JD-Core Version:    0.6.2
 */