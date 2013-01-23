package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

@GwtCompatible(serializable=true, emulated=true)
public abstract class ImmutableSet extends ImmutableCollection
  implements Set
{
  static final int MAX_TABLE_SIZE = 1073741824;
  private static final double DESIRED_LOAD_FACTOR = 0.7D;
  private static final int CUTOFF = (int)Math.floor(751619276.79999995D);

  public static ImmutableSet of()
  {
    return EmptyImmutableSet.INSTANCE;
  }

  public static ImmutableSet of(Object paramObject)
  {
    return new SingletonImmutableSet(paramObject);
  }

  public static ImmutableSet of(Object paramObject1, Object paramObject2)
  {
    return construct(new Object[] { paramObject1, paramObject2 });
  }

  public static ImmutableSet of(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    return construct(new Object[] { paramObject1, paramObject2, paramObject3 });
  }

  public static ImmutableSet of(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4)
  {
    return construct(new Object[] { paramObject1, paramObject2, paramObject3, paramObject4 });
  }

  public static ImmutableSet of(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5)
  {
    return construct(new Object[] { paramObject1, paramObject2, paramObject3, paramObject4, paramObject5 });
  }

  public static ImmutableSet of(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5, Object paramObject6, Object[] paramArrayOfObject)
  {
    int i = 6;
    Object[] arrayOfObject = new Object[6 + paramArrayOfObject.length];
    arrayOfObject[0] = paramObject1;
    arrayOfObject[1] = paramObject2;
    arrayOfObject[2] = paramObject3;
    arrayOfObject[3] = paramObject4;
    arrayOfObject[4] = paramObject5;
    arrayOfObject[5] = paramObject6;
    for (int j = 6; j < arrayOfObject.length; j++)
      arrayOfObject[j] = paramArrayOfObject[(j - 6)];
    return construct(arrayOfObject);
  }

  private static ImmutableSet construct(Object[] paramArrayOfObject)
  {
    int i = chooseTableSize(paramArrayOfObject.length);
    Object[] arrayOfObject1 = new Object[i];
    int j = i - 1;
    ArrayList localArrayList = null;
    int k = 0;
    Object localObject1;
    for (int m = 0; m < paramArrayOfObject.length; m++)
    {
      localObject1 = paramArrayOfObject[m];
      int n = localObject1.hashCode();
      for (int i1 = Hashing.smear(n); ; i1++)
      {
        int i2 = i1 & j;
        Object localObject2 = arrayOfObject1[i2];
        if (localObject2 == null)
        {
          if (localArrayList != null)
            localArrayList.add(localObject1);
          arrayOfObject1[i2] = localObject1;
          k += n;
          break;
        }
        if (localObject2.equals(localObject1))
        {
          if (localArrayList != null)
            break;
          localArrayList = new ArrayList(paramArrayOfObject.length);
          for (int i3 = 0; i3 < m; i3++)
          {
            Object localObject3 = paramArrayOfObject[i3];
            localArrayList.add(localObject3);
          }
          break;
        }
      }
    }
    Object[] arrayOfObject2 = localArrayList == null ? paramArrayOfObject : localArrayList.toArray();
    if (arrayOfObject2.length == 1)
    {
      localObject1 = arrayOfObject2[0];
      return new SingletonImmutableSet(localObject1, k);
    }
    if (i != chooseTableSize(arrayOfObject2.length))
      return construct(arrayOfObject2);
    return new RegularImmutableSet(arrayOfObject2, k, arrayOfObject1, j);
  }

  @VisibleForTesting
  static int chooseTableSize(int paramInt)
  {
    if (paramInt < CUTOFF)
    {
      int i = Integer.highestOneBit(paramInt - 1) << 1;
      while (i * 0.7D < paramInt)
        i <<= 1;
      return i;
    }
    Preconditions.checkArgument(paramInt < 1073741824, "collection too large");
    return 1073741824;
  }

  public static ImmutableSet copyOf(Object[] paramArrayOfObject)
  {
    switch (paramArrayOfObject.length)
    {
    case 0:
      return of();
    case 1:
      return of(paramArrayOfObject[0]);
    }
    return construct((Object[])paramArrayOfObject.clone());
  }

  public static ImmutableSet copyOf(Iterable paramIterable)
  {
    return (paramIterable instanceof Collection) ? copyOf(Collections2.cast(paramIterable)) : copyOf(paramIterable.iterator());
  }

  public static ImmutableSet copyOf(Iterator paramIterator)
  {
    return copyFromCollection(Lists.newArrayList(paramIterator));
  }

  public static ImmutableSet copyOf(Collection paramCollection)
  {
    if (((paramCollection instanceof ImmutableSet)) && (!(paramCollection instanceof ImmutableSortedSet)))
    {
      ImmutableSet localImmutableSet = (ImmutableSet)paramCollection;
      if (!localImmutableSet.isPartialView())
        return localImmutableSet;
    }
    return copyFromCollection(paramCollection);
  }

  private static ImmutableSet copyFromCollection(Collection paramCollection)
  {
    Object[] arrayOfObject = paramCollection.toArray();
    switch (arrayOfObject.length)
    {
    case 0:
      return of();
    case 1:
      Object localObject = arrayOfObject[0];
      return of(localObject);
    }
    return construct(arrayOfObject);
  }

  boolean isHashCodeFast()
  {
    return false;
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == this)
      return true;
    if (((paramObject instanceof ImmutableSet)) && (isHashCodeFast()) && (((ImmutableSet)paramObject).isHashCodeFast()) && (hashCode() != paramObject.hashCode()))
      return false;
    return Sets.equalsImpl(this, paramObject);
  }

  public int hashCode()
  {
    return Sets.hashCodeImpl(this);
  }

  public abstract UnmodifiableIterator iterator();

  Object writeReplace()
  {
    return new SerializedForm(toArray());
  }

  public static Builder builder()
  {
    return new Builder();
  }

  public static class Builder extends ImmutableCollection.Builder
  {
    final ArrayList contents = Lists.newArrayList();

    public Builder add(Object paramObject)
    {
      this.contents.add(Preconditions.checkNotNull(paramObject));
      return this;
    }

    public Builder add(Object[] paramArrayOfObject)
    {
      this.contents.ensureCapacity(this.contents.size() + paramArrayOfObject.length);
      super.add(paramArrayOfObject);
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

    public Builder addAll(Iterator paramIterator)
    {
      super.addAll(paramIterator);
      return this;
    }

    public ImmutableSet build()
    {
      return ImmutableSet.copyOf(this.contents);
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
      return ImmutableSet.copyOf(this.elements);
    }
  }

  static abstract class TransformedImmutableSet extends ImmutableSet
  {
    final ImmutableCollection source;
    final int hashCode;

    TransformedImmutableSet(ImmutableCollection paramImmutableCollection)
    {
      this.source = paramImmutableCollection;
      this.hashCode = Sets.hashCodeImpl(this);
    }

    TransformedImmutableSet(ImmutableCollection paramImmutableCollection, int paramInt)
    {
      this.source = paramImmutableCollection;
      this.hashCode = paramInt;
    }

    abstract Object transform(Object paramObject);

    public int size()
    {
      return this.source.size();
    }

    public boolean isEmpty()
    {
      return false;
    }

    public UnmodifiableIterator iterator()
    {
      final UnmodifiableIterator localUnmodifiableIterator = this.source.iterator();
      return new UnmodifiableIterator()
      {
        public boolean hasNext()
        {
          return localUnmodifiableIterator.hasNext();
        }

        public Object next()
        {
          return ImmutableSet.TransformedImmutableSet.this.transform(localUnmodifiableIterator.next());
        }
      };
    }

    public Object[] toArray()
    {
      return toArray(new Object[size()]);
    }

    public Object[] toArray(Object[] paramArrayOfObject)
    {
      int i = size();
      if (paramArrayOfObject.length < i)
        paramArrayOfObject = ObjectArrays.newArray(paramArrayOfObject, i);
      else if (paramArrayOfObject.length > i)
        paramArrayOfObject[i] = null;
      Object[] arrayOfObject = paramArrayOfObject;
      int j = 0;
      Iterator localIterator = this.source.iterator();
      while (localIterator.hasNext())
      {
        Object localObject = localIterator.next();
        arrayOfObject[(j++)] = transform(localObject);
      }
      return paramArrayOfObject;
    }

    public final int hashCode()
    {
      return this.hashCode;
    }

    boolean isHashCodeFast()
    {
      return true;
    }
  }

  static abstract class ArrayImmutableSet extends ImmutableSet
  {
    final transient Object[] elements;

    ArrayImmutableSet(Object[] paramArrayOfObject)
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

    public UnmodifiableIterator iterator()
    {
      return Iterators.forArray(this.elements);
    }

    public Object[] toArray()
    {
      Object[] arrayOfObject = new Object[size()];
      System.arraycopy(this.elements, 0, arrayOfObject, 0, size());
      return arrayOfObject;
    }

    public Object[] toArray(Object[] paramArrayOfObject)
    {
      int i = size();
      if (paramArrayOfObject.length < i)
        paramArrayOfObject = ObjectArrays.newArray(paramArrayOfObject, i);
      else if (paramArrayOfObject.length > i)
        paramArrayOfObject[i] = null;
      System.arraycopy(this.elements, 0, paramArrayOfObject, 0, i);
      return paramArrayOfObject;
    }

    public boolean containsAll(Collection paramCollection)
    {
      if (paramCollection == this)
        return true;
      if (!(paramCollection instanceof ArrayImmutableSet))
        return super.containsAll(paramCollection);
      if (paramCollection.size() > size())
        return false;
      for (Object localObject : ((ArrayImmutableSet)paramCollection).elements)
        if (!contains(localObject))
          return false;
      return true;
    }

    boolean isPartialView()
    {
      return false;
    }

    ImmutableList createAsList()
    {
      return new ImmutableAsList(this.elements, this);
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.ImmutableSet
 * JD-Core Version:    0.6.2
 */