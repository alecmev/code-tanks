package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Iterator;

@GwtCompatible
public abstract class ForwardingIterator extends ForwardingObject
  implements Iterator
{
  protected abstract Iterator delegate();

  public boolean hasNext()
  {
    return delegate().hasNext();
  }

  public Object next()
  {
    return delegate().next();
  }

  public void remove()
  {
    delegate().remove();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.ForwardingIterator
 * JD-Core Version:    0.6.2
 */