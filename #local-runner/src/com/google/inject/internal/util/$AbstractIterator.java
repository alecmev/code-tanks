package com.google.inject.internal.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class $AbstractIterator
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

  public boolean hasNext()
  {
    $Preconditions.checkState(this.state != State.FAILED);
    switch (1.$SwitchMap$com$google$inject$internal$util$AbstractIterator$State[this.state.ordinal()])
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

  public Object next()
  {
    if (!hasNext())
      throw new NoSuchElementException();
    this.state = State.NOT_READY;
    return this.next;
  }

  public void remove()
  {
    throw new UnsupportedOperationException();
  }

  private static enum State
  {
    READY, NOT_READY, DONE, FAILED;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.util..AbstractIterator
 * JD-Core Version:    0.6.2
 */