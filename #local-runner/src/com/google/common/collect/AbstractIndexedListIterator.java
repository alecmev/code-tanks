package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.NoSuchElementException;

@GwtCompatible
abstract class AbstractIndexedListIterator extends UnmodifiableListIterator
{
  private final int size;
  private int position;

  protected abstract Object get(int paramInt);

  protected AbstractIndexedListIterator(int paramInt)
  {
    this(paramInt, 0);
  }

  protected AbstractIndexedListIterator(int paramInt1, int paramInt2)
  {
    Preconditions.checkPositionIndex(paramInt2, paramInt1);
    this.size = paramInt1;
    this.position = paramInt2;
  }

  public final boolean hasNext()
  {
    return this.position < this.size;
  }

  public final Object next()
  {
    if (!hasNext())
      throw new NoSuchElementException();
    return get(this.position++);
  }

  public final int nextIndex()
  {
    return this.position;
  }

  public final boolean hasPrevious()
  {
    return this.position > 0;
  }

  public final Object previous()
  {
    if (!hasPrevious())
      throw new NoSuchElementException();
    return get(--this.position);
  }

  public final int previousIndex()
  {
    return this.position - 1;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.AbstractIndexedListIterator
 * JD-Core Version:    0.6.2
 */