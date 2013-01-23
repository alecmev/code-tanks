package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.ListIterator;

@GwtCompatible
public abstract class ForwardingListIterator extends ForwardingIterator
  implements ListIterator
{
  protected abstract ListIterator delegate();

  public void add(Object paramObject)
  {
    delegate().add(paramObject);
  }

  public boolean hasPrevious()
  {
    return delegate().hasPrevious();
  }

  public int nextIndex()
  {
    return delegate().nextIndex();
  }

  public Object previous()
  {
    return delegate().previous();
  }

  public int previousIndex()
  {
    return delegate().previousIndex();
  }

  public void set(Object paramObject)
  {
    delegate().set(paramObject);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.ForwardingListIterator
 * JD-Core Version:    0.6.2
 */