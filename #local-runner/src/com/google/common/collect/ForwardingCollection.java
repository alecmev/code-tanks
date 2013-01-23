package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import java.util.Collection;
import java.util.Iterator;

@GwtCompatible
public abstract class ForwardingCollection extends ForwardingObject
  implements Collection
{
  protected abstract Collection delegate();

  public Iterator iterator()
  {
    return delegate().iterator();
  }

  public int size()
  {
    return delegate().size();
  }

  public boolean removeAll(Collection paramCollection)
  {
    return delegate().removeAll(paramCollection);
  }

  public boolean isEmpty()
  {
    return delegate().isEmpty();
  }

  public boolean contains(Object paramObject)
  {
    return delegate().contains(paramObject);
  }

  public boolean add(Object paramObject)
  {
    return delegate().add(paramObject);
  }

  public boolean remove(Object paramObject)
  {
    return delegate().remove(paramObject);
  }

  public boolean containsAll(Collection paramCollection)
  {
    return delegate().containsAll(paramCollection);
  }

  public boolean addAll(Collection paramCollection)
  {
    return delegate().addAll(paramCollection);
  }

  public boolean retainAll(Collection paramCollection)
  {
    return delegate().retainAll(paramCollection);
  }

  public void clear()
  {
    delegate().clear();
  }

  public Object[] toArray()
  {
    return delegate().toArray();
  }

  public Object[] toArray(Object[] paramArrayOfObject)
  {
    return delegate().toArray(paramArrayOfObject);
  }

  @Beta
  protected boolean standardContains(Object paramObject)
  {
    return Iterators.contains(iterator(), paramObject);
  }

  @Beta
  protected boolean standardContainsAll(Collection paramCollection)
  {
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      if (!contains(localObject))
        return false;
    }
    return true;
  }

  @Beta
  protected boolean standardAddAll(Collection paramCollection)
  {
    return Iterators.addAll(this, paramCollection.iterator());
  }

  @Beta
  protected boolean standardRemove(Object paramObject)
  {
    Iterator localIterator = iterator();
    while (localIterator.hasNext())
      if (Objects.equal(localIterator.next(), paramObject))
      {
        localIterator.remove();
        return true;
      }
    return false;
  }

  @Beta
  protected boolean standardRemoveAll(Collection paramCollection)
  {
    return Iterators.removeAll(iterator(), paramCollection);
  }

  @Beta
  protected boolean standardRetainAll(Collection paramCollection)
  {
    return Iterators.retainAll(iterator(), paramCollection);
  }

  @Beta
  protected void standardClear()
  {
    Iterator localIterator = iterator();
    while (localIterator.hasNext())
    {
      localIterator.next();
      localIterator.remove();
    }
  }

  @Beta
  protected boolean standardIsEmpty()
  {
    return !iterator().hasNext();
  }

  @Beta
  protected String standardToString()
  {
    return Collections2.toStringImpl(this);
  }

  @Beta
  protected Object[] standardToArray()
  {
    Object[] arrayOfObject = new Object[size()];
    return toArray(arrayOfObject);
  }

  @Beta
  protected Object[] standardToArray(Object[] paramArrayOfObject)
  {
    return ObjectArrays.toArrayImpl(this, paramArrayOfObject);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.ForwardingCollection
 * JD-Core Version:    0.6.2
 */