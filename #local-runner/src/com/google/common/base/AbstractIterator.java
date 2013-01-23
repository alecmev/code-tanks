package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import java.util.Iterator;
import java.util.NoSuchElementException;

@GwtCompatible
abstract class AbstractIterator
  implements Iterator
{
  private State state = State.NOT_READY;
  private Object next;

  protected abstract Object computeNext();

  protected final Object endOfData()
  {
    this.state = State.DONE;
    return null;
  }

  public final boolean hasNext()
  {
    Preconditions.checkState(this.state != State.FAILED);
    switch (1.$SwitchMap$com$google$common$base$AbstractIterator$State[this.state.ordinal()])
    {
    case 1:
      return false;
    case 2:
      return true;
    }
    return tryToComputeNext();
  }

  private boolean tryToComputeNext()
  {
    this.state = State.FAILED;
    this.next = computeNext();
    if (this.state != State.DONE)
    {
      this.state = State.READY;
      return true;
    }
    return false;
  }

  public final Object next()
  {
    if (!hasNext())
      throw new NoSuchElementException();
    this.state = State.NOT_READY;
    return this.next;
  }

  public final void remove()
  {
    throw new UnsupportedOperationException();
  }

  private static enum State
  {
    READY, NOT_READY, DONE, FAILED;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.base.AbstractIterator
 * JD-Core Version:    0.6.2
 */