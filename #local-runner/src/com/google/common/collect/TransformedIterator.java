package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.Iterator;

@GwtCompatible
abstract class TransformedIterator
  implements Iterator
{
  final Iterator backingIterator;

  TransformedIterator(Iterator paramIterator)
  {
    this.backingIterator = ((Iterator)Preconditions.checkNotNull(paramIterator));
  }

  abstract Object transform(Object paramObject);

  public final boolean hasNext()
  {
    return this.backingIterator.hasNext();
  }

  public final Object next()
  {
    return transform(this.backingIterator.next());
  }

  public final void remove()
  {
    this.backingIterator.remove();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.TransformedIterator
 * JD-Core Version:    0.6.2
 */