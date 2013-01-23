package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Comparator;
import java.util.Iterator;

@GwtCompatible
abstract interface SortedIterable extends Iterable
{
  public abstract Comparator comparator();

  public abstract Iterator iterator();
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.SortedIterable
 * JD-Core Version:    0.6.2
 */