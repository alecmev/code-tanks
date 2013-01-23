package com.google.inject.internal.util;

import java.util.Arrays;
import java.util.Iterator;

public final class $Iterables
{
  public static String toString(Iterable paramIterable)
  {
    return .Iterators.toString(paramIterable.iterator());
  }

  public static Object getOnlyElement(Iterable paramIterable)
  {
    return .Iterators.getOnlyElement(paramIterable.iterator());
  }

  public static Iterable concat(Iterable paramIterable1, Iterable paramIterable2)
  {
    $Preconditions.checkNotNull(paramIterable1);
    $Preconditions.checkNotNull(paramIterable2);
    return concat(Arrays.asList(new Iterable[] { paramIterable1, paramIterable2 }));
  }

  public static Iterable concat(Iterable paramIterable)
  {
    $Function local1 = new $Function()
    {
      public Iterator apply(Iterable paramAnonymousIterable)
      {
        return paramAnonymousIterable.iterator();
      }
    };
    Iterable localIterable = transform(paramIterable, local1);
    return new IterableWithToString()
    {
      public Iterator iterator()
      {
        return .Iterators.concat(this.val$iterators.iterator());
      }
    };
  }

  public static Iterable transform(Iterable paramIterable, final .Function paramFunction)
  {
    $Preconditions.checkNotNull(paramIterable);
    $Preconditions.checkNotNull(paramFunction);
    return new IterableWithToString()
    {
      public Iterator iterator()
      {
        return .Iterators.transform(this.val$fromIterable.iterator(), paramFunction);
      }
    };
  }

  static abstract class IterableWithToString
    implements Iterable
  {
    public String toString()
    {
      return .Iterables.toString(this);
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.util..Iterables
 * JD-Core Version:    0.6.2
 */