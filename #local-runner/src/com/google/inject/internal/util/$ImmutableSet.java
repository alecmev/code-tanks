package com.google.inject.internal.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public abstract class $ImmutableSet extends $ImmutableCollection
  implements Set
{
  private static final ImmutableSet EMPTY_IMMUTABLE_SET = new EmptyImmutableSet(null);

  public static ImmutableSet of()
  {
    return EMPTY_IMMUTABLE_SET;
  }

  public static ImmutableSet of(Object paramObject)
  {
    return new SingletonImmutableSet(paramObject, paramObject.hashCode());
  }

  public static ImmutableSet of(Object[] paramArrayOfObject)
  {
    switch (paramArrayOfObject.length)
    {
    case 0:
      return of();
    case 1:
      return of(paramArrayOfObject[0]);
    }
    return create(Arrays.asList(paramArrayOfObject), paramArrayOfObject.length);
  }

  public static ImmutableSet copyOf(Iterable paramIterable)
  {
    if ((paramIterable instanceof ImmutableSet))
    {
      ImmutableSet localImmutableSet = (ImmutableSet)paramIterable;
      return localImmutableSet;
    }
    return copyOfInternal($Collections2.toCollection(paramIterable));
  }

  public static ImmutableSet copyOf(Iterator paramIterator)
  {
    ArrayList localArrayList = $Lists.newArrayList(paramIterator);
    return copyOfInternal(localArrayList);
  }

  private static ImmutableSet copyOfInternal(Collection paramCollection)
  {
    switch (paramCollection.size())
    {
    case 0:
      return of();
    case 1:
      return of(paramCollection.iterator().next());
    }
    return create(paramCollection, paramCollection.size());
  }

  boolean isHashCodeFast()
  {
    return false;
  }

  public boolean equals(@$Nullable Object paramObject)
  {
    if (paramObject == this)
      return true;
    if (((paramObject instanceof ImmutableSet)) && (isHashCodeFast()) && (((ImmutableSet)paramObject).isHashCodeFast()) && (hashCode() != paramObject.hashCode()))
      return false;
    return .Collections2.setEquals(this, paramObject);
  }

  public int hashCode()
  {
    int i = 0;
    Iterator localIterator = iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      i += localObject.hashCode();
    }
    return i;
  }

  public abstract $UnmodifiableIterator iterator();

  public String toString()
  {
    if (isEmpty())
      return "[]";
    $UnmodifiableIterator localUnmodifiableIterator = iterator();
    StringBuilder localStringBuilder = new StringBuilder(size() * 16);
    localStringBuilder.append('[').append(localUnmodifiableIterator.next().toString());
    for (int i = 1; i < size(); i++)
      localStringBuilder.append(", ").append(localUnmodifiableIterator.next().toString());
    return ']';
  }

  private static ImmutableSet create(Iterable paramIterable, int paramInt)
  {
    int i = $Hashing.chooseTableSize(paramInt);
    Object[] arrayOfObject = new Object[i];
    int j = i - 1;
    ArrayList localArrayList = new ArrayList(paramInt);
    int k = 0;
    Iterator localIterator = paramIterable.iterator();
    while (localIterator.hasNext())
    {
      Object localObject1 = localIterator.next();
      int m = localObject1.hashCode();
      for (int n = $Hashing.smear(m); ; n++)
      {
        int i1 = n & j;
        Object localObject2 = arrayOfObject[i1];
        if (localObject2 == null)
        {
          arrayOfObject[i1] = localObject1;
          localArrayList.add(localObject1);
          k += m;
        }
        else
        {
          if (localObject2.equals(localObject1))
            break;
        }
      }
    }
    return localArrayList.size() == 1 ? new SingletonImmutableSet(localArrayList.get(0), k) : new RegularImmutableSet(localArrayList.toArray(), k, arrayOfObject, j);
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
    final ArrayList contents = $Lists.newArrayList();

    public Builder add(Object paramObject)
    {
      $Preconditions.checkNotNull(paramObject, "element cannot be null");
      this.contents.add(paramObject);
      return this;
    }

    public Builder add(Object[] paramArrayOfObject)
    {
      $Preconditions.checkNotNull(paramArrayOfObject, "elements cannot be null");
      List localList = Arrays.asList(paramArrayOfObject);
      $Preconditions.checkContentsNotNull(localList, "elements cannot contain null");
      this.contents.addAll(localList);
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

    public Builder addAll(Iterator paramIterator)
    {
      while (paramIterator.hasNext())
      {
        Object localObject = paramIterator.next();
        $Preconditions.checkNotNull(localObject, "element cannot be null");
        this.contents.add(localObject);
      }
      return this;
    }

    public .ImmutableSet build()
    {
      return .ImmutableSet.copyOf(this.contents);
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
      return .ImmutableSet.of(this.elements);
    }
  }

  static abstract class TransformedImmutableSet extends $ImmutableSet
  {
    final Object[] source;
    final int hashCode;

    TransformedImmutableSet(Object[] paramArrayOfObject, int paramInt)
    {
      this.source = paramArrayOfObject;
      this.hashCode = paramInt;
    }

    abstract Object transform(Object paramObject);

    public int size()
    {
      return this.source.length;
    }

    public boolean isEmpty()
    {
      return false;
    }

    public .UnmodifiableIterator iterator()
    {
      $AbstractIterator local1 = new $AbstractIterator()
      {
        int index = 0;

        protected Object computeNext()
        {
          return this.index < .ImmutableSet.TransformedImmutableSet.this.source.length ? .ImmutableSet.TransformedImmutableSet.this.transform($ImmutableSet.TransformedImmutableSet.this.source[(this.index++)]) : endOfData();
        }
      };
      return .Iterators.unmodifiableIterator(local1);
    }

    public Object[] toArray()
    {
      return toArray(new Object[size()]);
    }

    public Object[] toArray(Object[] paramArrayOfObject)
    {
      int i = size();
      if (paramArrayOfObject.length < i)
        paramArrayOfObject = $ObjectArrays.newArray(paramArrayOfObject, i);
      else if (paramArrayOfObject.length > i)
        paramArrayOfObject[i] = null;
      for (int j = 0; j < this.source.length; j++)
        paramArrayOfObject[j] = transform(this.source[j]);
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

  private static final class RegularImmutableSet extends $ImmutableSet.ArrayImmutableSet
  {
    final Object[] table;
    final int mask;
    final int hashCode;

    RegularImmutableSet(Object[] paramArrayOfObject1, int paramInt1, Object[] paramArrayOfObject2, int paramInt2)
    {
      super();
      this.table = paramArrayOfObject2;
      this.mask = paramInt2;
      this.hashCode = paramInt1;
    }

    public boolean contains(Object paramObject)
    {
      if (paramObject == null)
        return false;
      for (int i = $Hashing.smear(paramObject.hashCode()); ; i++)
      {
        Object localObject = this.table[(i & this.mask)];
        if (localObject == null)
          return false;
        if (localObject.equals(paramObject))
          return true;
      }
    }

    public int hashCode()
    {
      return this.hashCode;
    }

    boolean isHashCodeFast()
    {
      return true;
    }
  }

  static abstract class ArrayImmutableSet extends $ImmutableSet
  {
    final Object[] elements;

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

    public .UnmodifiableIterator iterator()
    {
      return .Iterators.forArray(this.elements);
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
        paramArrayOfObject = $ObjectArrays.newArray(paramArrayOfObject, i);
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
  }

  private static final class SingletonImmutableSet extends $ImmutableSet
  {
    final Object element;
    final int hashCode;

    SingletonImmutableSet(Object paramObject, int paramInt)
    {
      this.element = paramObject;
      this.hashCode = paramInt;
    }

    public int size()
    {
      return 1;
    }

    public boolean isEmpty()
    {
      return false;
    }

    public boolean contains(Object paramObject)
    {
      return this.element.equals(paramObject);
    }

    public .UnmodifiableIterator iterator()
    {
      return .Iterators.singletonIterator(this.element);
    }

    public Object[] toArray()
    {
      return new Object[] { this.element };
    }

    public Object[] toArray(Object[] paramArrayOfObject)
    {
      if (paramArrayOfObject.length == 0)
        paramArrayOfObject = $ObjectArrays.newArray(paramArrayOfObject, 1);
      else if (paramArrayOfObject.length > 1)
        paramArrayOfObject[1] = null;
      paramArrayOfObject[0] = this.element;
      return paramArrayOfObject;
    }

    public boolean equals(@$Nullable Object paramObject)
    {
      if (paramObject == this)
        return true;
      if ((paramObject instanceof Set))
      {
        Set localSet = (Set)paramObject;
        return (localSet.size() == 1) && (this.element.equals(localSet.iterator().next()));
      }
      return false;
    }

    public final int hashCode()
    {
      return this.hashCode;
    }

    boolean isHashCodeFast()
    {
      return true;
    }

    public String toString()
    {
      String str = this.element.toString();
      return str.length() + 2 + '[' + str + ']';
    }
  }

  private static final class EmptyImmutableSet extends $ImmutableSet
  {
    private static final Object[] EMPTY_ARRAY = new Object[0];

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

    public boolean containsAll(Collection paramCollection)
    {
      return paramCollection.isEmpty();
    }

    public boolean equals(@$Nullable Object paramObject)
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
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.util..ImmutableSet
 * JD-Core Version:    0.6.2
 */