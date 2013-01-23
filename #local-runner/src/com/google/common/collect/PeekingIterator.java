package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Iterator;

@GwtCompatible
public abstract interface PeekingIterator extends Iterator
{
  public abstract Object peek();

  public abstract Object next();

  public abstract void remove();
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.PeekingIterator
 * JD-Core Version:    0.6.2
 */