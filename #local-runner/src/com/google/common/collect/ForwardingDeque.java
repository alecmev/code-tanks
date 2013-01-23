package com.google.common.collect;

import com.google.common.annotations.Beta;
import java.util.Deque;
import java.util.Iterator;

@Beta
public abstract class ForwardingDeque extends ForwardingQueue
  implements Deque
{
  protected abstract Deque delegate();

  public void addFirst(Object paramObject)
  {
    delegate().addFirst(paramObject);
  }

  public void addLast(Object paramObject)
  {
    delegate().addLast(paramObject);
  }

  public Iterator descendingIterator()
  {
    return delegate().descendingIterator();
  }

  public Object getFirst()
  {
    return delegate().getFirst();
  }

  public Object getLast()
  {
    return delegate().getLast();
  }

  public boolean offerFirst(Object paramObject)
  {
    return delegate().offerFirst(paramObject);
  }

  public boolean offerLast(Object paramObject)
  {
    return delegate().offerLast(paramObject);
  }

  public Object peekFirst()
  {
    return delegate().peekFirst();
  }

  public Object peekLast()
  {
    return delegate().peekLast();
  }

  public Object pollFirst()
  {
    return delegate().pollFirst();
  }

  public Object pollLast()
  {
    return delegate().pollLast();
  }

  public Object pop()
  {
    return delegate().pop();
  }

  public void push(Object paramObject)
  {
    delegate().push(paramObject);
  }

  public Object removeFirst()
  {
    return delegate().removeFirst();
  }

  public Object removeLast()
  {
    return delegate().removeLast();
  }

  public boolean removeFirstOccurrence(Object paramObject)
  {
    return delegate().removeFirstOccurrence(paramObject);
  }

  public boolean removeLastOccurrence(Object paramObject)
  {
    return delegate().removeLastOccurrence(paramObject);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.ForwardingDeque
 * JD-Core Version:    0.6.2
 */