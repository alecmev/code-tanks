package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

@GwtCompatible
final class SortedIterables
{
  public static boolean hasSameComparator(Comparator paramComparator, Iterable paramIterable)
  {
    Preconditions.checkNotNull(paramComparator);
    Preconditions.checkNotNull(paramIterable);
    Object localObject;
    if ((paramIterable instanceof SortedSet))
    {
      SortedSet localSortedSet = (SortedSet)paramIterable;
      localObject = localSortedSet.comparator();
      if (localObject == null)
        localObject = Ordering.natural();
    }
    else if ((paramIterable instanceof SortedIterable))
    {
      localObject = ((SortedIterable)paramIterable).comparator();
    }
    else
    {
      localObject = null;
    }
    return paramComparator.equals(localObject);
  }

  public static Collection sortedUnique(Comparator paramComparator, Iterator paramIterator)
  {
    TreeSet localTreeSet = Sets.newTreeSet(paramComparator);
    Iterators.addAll(localTreeSet, paramIterator);
    return localTreeSet;
  }

  public static Collection sortedUnique(Comparator paramComparator, Iterable paramIterable)
  {
    if ((paramIterable instanceof Multiset))
      paramIterable = ((Multiset)paramIterable).elementSet();
    if ((paramIterable instanceof Set))
    {
      if (hasSameComparator(paramComparator, paramIterable))
        return (Set)paramIterable;
      localObject = Lists.newArrayList(paramIterable);
      Collections.sort((List)localObject, paramComparator);
      return localObject;
    }
    Object localObject = (Object[])Iterables.toArray(paramIterable);
    if (!hasSameComparator(paramComparator, paramIterable))
      Arrays.sort((Object[])localObject, paramComparator);
    return uniquifySortedArray(paramComparator, (Object[])localObject);
  }

  private static Collection uniquifySortedArray(Comparator paramComparator, Object[] paramArrayOfObject)
  {
    if (paramArrayOfObject.length == 0)
      return Collections.emptySet();
    int i = 1;
    for (int j = 1; j < paramArrayOfObject.length; j++)
    {
      int k = paramComparator.compare(paramArrayOfObject[j], paramArrayOfObject[(i - 1)]);
      if (k != 0)
        paramArrayOfObject[(i++)] = paramArrayOfObject[j];
    }
    if (i < paramArrayOfObject.length)
      paramArrayOfObject = ObjectArrays.arraysCopyOf(paramArrayOfObject, i);
    return Arrays.asList(paramArrayOfObject);
  }

  public static Collection sortedCounts(Comparator paramComparator, Iterator paramIterator)
  {
    TreeMultiset localTreeMultiset = TreeMultiset.create(paramComparator);
    Iterators.addAll(localTreeMultiset, paramIterator);
    return localTreeMultiset.entrySet();
  }

  public static Collection sortedCounts(Comparator paramComparator, Iterable paramIterable)
  {
    ArrayList localArrayList1;
    if ((paramIterable instanceof Multiset))
    {
      localObject1 = (Multiset)paramIterable;
      if (hasSameComparator(paramComparator, paramIterable))
        return ((Multiset)localObject1).entrySet();
      localArrayList1 = Lists.newArrayList(((Multiset)localObject1).entrySet());
      Collections.sort(localArrayList1, Ordering.from(paramComparator).onResultOf(new Function()
      {
        public Object apply(Multiset.Entry paramAnonymousEntry)
        {
          return paramAnonymousEntry.getElement();
        }
      }));
      return localArrayList1;
    }
    if ((paramIterable instanceof Set))
    {
      if (hasSameComparator(paramComparator, paramIterable))
      {
        localObject1 = (Collection)paramIterable;
      }
      else
      {
        localArrayList1 = Lists.newArrayList(paramIterable);
        Collections.sort(localArrayList1, paramComparator);
        localObject1 = localArrayList1;
      }
      return singletonEntries((Collection)localObject1);
    }
    if (hasSameComparator(paramComparator, paramIterable))
    {
      localObject1 = null;
      int i = 0;
      ArrayList localArrayList2 = Lists.newArrayList();
      Iterator localIterator = paramIterable.iterator();
      while (localIterator.hasNext())
      {
        Object localObject2 = localIterator.next();
        if (i > 0)
        {
          if (paramComparator.compare(localObject1, localObject2) == 0)
          {
            i++;
          }
          else
          {
            localArrayList2.add(Multisets.immutableEntry(localObject1, i));
            localObject1 = localObject2;
            i = 1;
          }
        }
        else
        {
          localObject1 = localObject2;
          i = 1;
        }
      }
      if (i > 0)
        localArrayList2.add(Multisets.immutableEntry(localObject1, i));
      return localArrayList2;
    }
    Object localObject1 = TreeMultiset.create(paramComparator);
    Iterables.addAll((Collection)localObject1, paramIterable);
    return ((TreeMultiset)localObject1).entrySet();
  }

  static Collection singletonEntries(Collection paramCollection)
  {
    return Collections2.transform(paramCollection, new Function()
    {
      public Multiset.Entry apply(Object paramAnonymousObject)
      {
        return Multisets.immutableEntry(paramAnonymousObject, 1);
      }
    });
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.SortedIterables
 * JD-Core Version:    0.6.2
 */