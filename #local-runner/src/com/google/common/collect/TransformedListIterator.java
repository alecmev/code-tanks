package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.ListIterator;

@GwtCompatible
abstract class TransformedListIterator extends TransformedIterator
  implements ListIterator
{
  TransformedListIterator(ListIterator paramListIterator)
  {
    super(paramListIterator);
  }

  private ListIterator backingIterator()
  {
    return Iterators.cast(this.backingIterator);
  }

  public final boolean hasPrevious()
  {
    return backingIterator().hasPrevious();
  }

  public final Object previous()
  {
    return transform(backingIterator().previous());
  }

  public final int nextIndex()
  {
    return backingIterator().nextIndex();
  }

  public final int previousIndex()
  {
    return backingIterator().previousIndex();
  }

  public void set(Object paramObject)
  {
    throw new UnsupportedOperationException();
  }

  public void add(Object paramObject)
  {
    throw new UnsupportedOperationException();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.TransformedListIterator
 * JD-Core Version:    0.6.2
 */