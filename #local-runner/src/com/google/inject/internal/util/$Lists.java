package com.google.inject.internal.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public final class $Lists
{
  public static ArrayList newArrayList()
  {
    return new ArrayList();
  }

  public static ArrayList newArrayList(Object[] paramArrayOfObject)
  {
    int i = computeArrayListCapacity(paramArrayOfObject.length);
    ArrayList localArrayList = new ArrayList(i);
    Collections.addAll(localArrayList, paramArrayOfObject);
    return localArrayList;
  }

  static int computeArrayListCapacity(int paramInt)
  {
    $Preconditions.checkArgument(paramInt >= 0);
    return (int)Math.min(5L + paramInt + paramInt / 10, 2147483647L);
  }

  public static ArrayList newArrayList(Iterable paramIterable)
  {
    if ((paramIterable instanceof Collection))
    {
      Collection localCollection = (Collection)paramIterable;
      return new ArrayList(localCollection);
    }
    return newArrayList(paramIterable.iterator());
  }

  public static ArrayList newArrayList(Iterator paramIterator)
  {
    ArrayList localArrayList = newArrayList();
    while (paramIterator.hasNext())
      localArrayList.add(paramIterator.next());
    return localArrayList;
  }

  public static ArrayList newArrayList(@$Nullable Object paramObject, Object[] paramArrayOfObject)
  {
    ArrayList localArrayList = new ArrayList(paramArrayOfObject.length + 1);
    localArrayList.add(paramObject);
    for (Object localObject : paramArrayOfObject)
      localArrayList.add(localObject);
    return localArrayList;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.util..Lists
 * JD-Core Version:    0.6.2
 */