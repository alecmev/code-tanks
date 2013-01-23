package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@GwtCompatible
public abstract class Ordering
  implements Comparator
{
  static final int LEFT_IS_GREATER = 1;
  static final int RIGHT_IS_GREATER = -1;

  @GwtCompatible(serializable=true)
  public static Ordering natural()
  {
    return NaturalOrdering.INSTANCE;
  }

  @GwtCompatible(serializable=true)
  public static Ordering from(Comparator paramComparator)
  {
    return (paramComparator instanceof Ordering) ? (Ordering)paramComparator : new ComparatorOrdering(paramComparator);
  }

  @GwtCompatible(serializable=true)
  @Deprecated
  public static Ordering from(Ordering paramOrdering)
  {
    return (Ordering)Preconditions.checkNotNull(paramOrdering);
  }

  @GwtCompatible(serializable=true)
  public static Ordering explicit(List paramList)
  {
    return new ExplicitOrdering(paramList);
  }

  @GwtCompatible(serializable=true)
  public static Ordering explicit(Object paramObject, Object[] paramArrayOfObject)
  {
    return explicit(Lists.asList(paramObject, paramArrayOfObject));
  }

  public static Ordering arbitrary()
  {
    return ArbitraryOrderingHolder.ARBITRARY_ORDERING;
  }

  @GwtCompatible(serializable=true)
  public static Ordering usingToString()
  {
    return UsingToStringOrdering.INSTANCE;
  }

  @GwtCompatible(serializable=true)
  public static Ordering compound(Iterable paramIterable)
  {
    return new CompoundOrdering(paramIterable);
  }

  @GwtCompatible(serializable=true)
  public Ordering compound(Comparator paramComparator)
  {
    return new CompoundOrdering(this, (Comparator)Preconditions.checkNotNull(paramComparator));
  }

  @GwtCompatible(serializable=true)
  public Ordering reverse()
  {
    return new ReverseOrdering(this);
  }

  @GwtCompatible(serializable=true)
  public Ordering onResultOf(Function paramFunction)
  {
    return new ByFunctionOrdering(paramFunction, this);
  }

  @GwtCompatible(serializable=true)
  public Ordering lexicographical()
  {
    return new LexicographicalOrdering(this);
  }

  @GwtCompatible(serializable=true)
  public Ordering nullsFirst()
  {
    return new NullsFirstOrdering(this);
  }

  @GwtCompatible(serializable=true)
  public Ordering nullsLast()
  {
    return new NullsLastOrdering(this);
  }

  public abstract int compare(Object paramObject1, Object paramObject2);

  @Beta
  public List leastOf(Iterable paramIterable, int paramInt)
  {
    Preconditions.checkArgument(paramInt >= 0, "%d is negative", new Object[] { Integer.valueOf(paramInt) });
    Object[] arrayOfObject1 = (Object[])Iterables.toArray(paramIterable);
    Object localObject;
    if (arrayOfObject1.length <= paramInt)
    {
      Arrays.sort(arrayOfObject1, this);
      localObject = arrayOfObject1;
    }
    else
    {
      quicksortLeastK(arrayOfObject1, 0, arrayOfObject1.length - 1, paramInt);
      Object[] arrayOfObject2 = (Object[])new Object[paramInt];
      localObject = arrayOfObject2;
      System.arraycopy(arrayOfObject1, 0, localObject, 0, paramInt);
    }
    return Collections.unmodifiableList(Arrays.asList((Object[])localObject));
  }

  @Beta
  public List greatestOf(Iterable paramIterable, int paramInt)
  {
    return reverse().leastOf(paramIterable, paramInt);
  }

  private void quicksortLeastK(Object[] paramArrayOfObject, int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt2 > paramInt1)
    {
      int i = paramInt1 + paramInt2 >>> 1;
      int j = partition(paramArrayOfObject, paramInt1, paramInt2, i);
      quicksortLeastK(paramArrayOfObject, paramInt1, j - 1, paramInt3);
      if (j < paramInt3)
        quicksortLeastK(paramArrayOfObject, j + 1, paramInt2, paramInt3);
    }
  }

  private int partition(Object[] paramArrayOfObject, int paramInt1, int paramInt2, int paramInt3)
  {
    Object localObject = paramArrayOfObject[paramInt3];
    paramArrayOfObject[paramInt3] = paramArrayOfObject[paramInt2];
    paramArrayOfObject[paramInt2] = localObject;
    int i = paramInt1;
    for (int j = paramInt1; j < paramInt2; j++)
      if (compare(paramArrayOfObject[j], localObject) < 0)
      {
        ObjectArrays.swap(paramArrayOfObject, i, j);
        i++;
      }
    ObjectArrays.swap(paramArrayOfObject, paramInt2, i);
    return i;
  }

  public int binarySearch(List paramList, Object paramObject)
  {
    return Collections.binarySearch(paramList, paramObject, this);
  }

  public List sortedCopy(Iterable paramIterable)
  {
    ArrayList localArrayList = Lists.newArrayList(paramIterable);
    Collections.sort(localArrayList, this);
    return localArrayList;
  }

  public ImmutableList immutableSortedCopy(Iterable paramIterable)
  {
    return ImmutableList.copyOf(sortedCopy(paramIterable));
  }

  public boolean isOrdered(Iterable paramIterable)
  {
    Iterator localIterator = paramIterable.iterator();
    if (localIterator.hasNext())
    {
      Object localObject2;
      for (Object localObject1 = localIterator.next(); localIterator.hasNext(); localObject1 = localObject2)
      {
        localObject2 = localIterator.next();
        if (compare(localObject1, localObject2) > 0)
          return false;
      }
    }
    return true;
  }

  public boolean isStrictlyOrdered(Iterable paramIterable)
  {
    Iterator localIterator = paramIterable.iterator();
    if (localIterator.hasNext())
    {
      Object localObject2;
      for (Object localObject1 = localIterator.next(); localIterator.hasNext(); localObject1 = localObject2)
      {
        localObject2 = localIterator.next();
        if (compare(localObject1, localObject2) >= 0)
          return false;
      }
    }
    return true;
  }

  @Beta
  public Object max(Iterator paramIterator)
  {
    for (Object localObject = paramIterator.next(); paramIterator.hasNext(); localObject = max(localObject, paramIterator.next()));
    return localObject;
  }

  public Object max(Iterable paramIterable)
  {
    return max(paramIterable.iterator());
  }

  public Object max(Object paramObject1, Object paramObject2, Object paramObject3, Object[] paramArrayOfObject)
  {
    Object localObject1 = max(max(paramObject1, paramObject2), paramObject3);
    for (Object localObject2 : paramArrayOfObject)
      localObject1 = max(localObject1, localObject2);
    return localObject1;
  }

  public Object max(Object paramObject1, Object paramObject2)
  {
    return compare(paramObject1, paramObject2) >= 0 ? paramObject1 : paramObject2;
  }

  @Beta
  public Object min(Iterator paramIterator)
  {
    for (Object localObject = paramIterator.next(); paramIterator.hasNext(); localObject = min(localObject, paramIterator.next()));
    return localObject;
  }

  public Object min(Iterable paramIterable)
  {
    return min(paramIterable.iterator());
  }

  public Object min(Object paramObject1, Object paramObject2, Object paramObject3, Object[] paramArrayOfObject)
  {
    Object localObject1 = min(min(paramObject1, paramObject2), paramObject3);
    for (Object localObject2 : paramArrayOfObject)
      localObject1 = min(localObject1, localObject2);
    return localObject1;
  }

  public Object min(Object paramObject1, Object paramObject2)
  {
    return compare(paramObject1, paramObject2) <= 0 ? paramObject1 : paramObject2;
  }

  @VisibleForTesting
  static class ArbitraryOrdering extends Ordering
  {
    private Map uids = Platform.tryWeakKeys(new MapMaker()).makeComputingMap(new Function()
    {
      final AtomicInteger counter = new AtomicInteger(0);

      public Integer apply(Object paramAnonymousObject)
      {
        return Integer.valueOf(this.counter.getAndIncrement());
      }
    });

    public int compare(Object paramObject1, Object paramObject2)
    {
      if (paramObject1 == paramObject2)
        return 0;
      int i = identityHashCode(paramObject1);
      int j = identityHashCode(paramObject2);
      if (i != j)
        return i < j ? -1 : 1;
      int k = ((Integer)this.uids.get(paramObject1)).compareTo((Integer)this.uids.get(paramObject2));
      if (k == 0)
        throw new AssertionError();
      return k;
    }

    public String toString()
    {
      return "Ordering.arbitrary()";
    }

    int identityHashCode(Object paramObject)
    {
      return System.identityHashCode(paramObject);
    }
  }

  private static class ArbitraryOrderingHolder
  {
    static final Ordering ARBITRARY_ORDERING = new Ordering.ArbitraryOrdering();
  }

  @VisibleForTesting
  static class IncomparableValueException extends ClassCastException
  {
    final Object value;
    private static final long serialVersionUID = 0L;

    IncomparableValueException(Object paramObject)
    {
      super();
      this.value = paramObject;
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.Ordering
 * JD-Core Version:    0.6.2
 */