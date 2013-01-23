package com.google.common.collect;

import com.google.common.annotations.Beta;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.SortedSet;

@Beta
public abstract class ForwardingNavigableSet extends ForwardingSortedSet
  implements NavigableSet
{
  protected abstract NavigableSet delegate();

  public Object lower(Object paramObject)
  {
    return delegate().lower(paramObject);
  }

  protected Object standardLower(Object paramObject)
  {
    return Iterators.getNext(headSet(paramObject, false).descendingIterator(), null);
  }

  public Object floor(Object paramObject)
  {
    return delegate().floor(paramObject);
  }

  protected Object standardFloor(Object paramObject)
  {
    return Iterators.getNext(headSet(paramObject, true).descendingIterator(), null);
  }

  public Object ceiling(Object paramObject)
  {
    return delegate().ceiling(paramObject);
  }

  protected Object standardCeiling(Object paramObject)
  {
    return Iterators.getNext(tailSet(paramObject, true).iterator(), null);
  }

  public Object higher(Object paramObject)
  {
    return delegate().higher(paramObject);
  }

  protected Object standardHigher(Object paramObject)
  {
    return Iterators.getNext(tailSet(paramObject, false).iterator(), null);
  }

  public Object pollFirst()
  {
    return delegate().pollFirst();
  }

  protected Object standardPollFirst()
  {
    return poll(iterator());
  }

  public Object pollLast()
  {
    return delegate().pollLast();
  }

  protected Object standardPollLast()
  {
    return poll(delegate().descendingIterator());
  }

  protected Object standardFirst()
  {
    return iterator().next();
  }

  protected Object standardLast()
  {
    return descendingIterator().next();
  }

  public NavigableSet descendingSet()
  {
    return delegate().descendingSet();
  }

  public Iterator descendingIterator()
  {
    return delegate().descendingIterator();
  }

  public NavigableSet subSet(Object paramObject1, boolean paramBoolean1, Object paramObject2, boolean paramBoolean2)
  {
    return delegate().subSet(paramObject1, paramBoolean1, paramObject2, paramBoolean2);
  }

  protected NavigableSet standardSubSet(Object paramObject1, boolean paramBoolean1, Object paramObject2, boolean paramBoolean2)
  {
    return tailSet(paramObject1, paramBoolean1).headSet(paramObject2, paramBoolean2);
  }

  protected SortedSet standardSubSet(Object paramObject1, Object paramObject2)
  {
    return subSet(paramObject1, true, paramObject2, false);
  }

  public NavigableSet headSet(Object paramObject, boolean paramBoolean)
  {
    return delegate().headSet(paramObject, paramBoolean);
  }

  protected SortedSet standardHeadSet(Object paramObject)
  {
    return headSet(paramObject, false);
  }

  public NavigableSet tailSet(Object paramObject, boolean paramBoolean)
  {
    return delegate().tailSet(paramObject, paramBoolean);
  }

  protected SortedSet standardTailSet(Object paramObject)
  {
    return tailSet(paramObject, true);
  }

  private Object poll(Iterator paramIterator)
  {
    if (paramIterator.hasNext())
    {
      Object localObject = paramIterator.next();
      paramIterator.remove();
      return localObject;
    }
    return null;
  }

  @Beta
  protected class StandardDescendingSet extends Sets.DescendingSet
  {
    public StandardDescendingSet()
    {
      super();
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.ForwardingNavigableSet
 * JD-Core Version:    0.6.2
 */