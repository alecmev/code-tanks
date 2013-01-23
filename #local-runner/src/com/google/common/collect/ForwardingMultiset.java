package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

@GwtCompatible
public abstract class ForwardingMultiset extends ForwardingCollection
  implements Multiset
{
  protected abstract Multiset delegate();

  public int count(Object paramObject)
  {
    return delegate().count(paramObject);
  }

  public int add(Object paramObject, int paramInt)
  {
    return delegate().add(paramObject, paramInt);
  }

  public int remove(Object paramObject, int paramInt)
  {
    return delegate().remove(paramObject, paramInt);
  }

  public Set elementSet()
  {
    return delegate().elementSet();
  }

  public Set entrySet()
  {
    return delegate().entrySet();
  }

  public boolean equals(Object paramObject)
  {
    return (paramObject == this) || (delegate().equals(paramObject));
  }

  public int hashCode()
  {
    return delegate().hashCode();
  }

  public int setCount(Object paramObject, int paramInt)
  {
    return delegate().setCount(paramObject, paramInt);
  }

  public boolean setCount(Object paramObject, int paramInt1, int paramInt2)
  {
    return delegate().setCount(paramObject, paramInt1, paramInt2);
  }

  @Beta
  protected boolean standardContains(Object paramObject)
  {
    return count(paramObject) > 0;
  }

  @Beta
  protected void standardClear()
  {
    Iterator localIterator = entrySet().iterator();
    while (localIterator.hasNext())
    {
      localIterator.next();
      localIterator.remove();
    }
  }

  @Beta
  protected int standardCount(Object paramObject)
  {
    Iterator localIterator = entrySet().iterator();
    while (localIterator.hasNext())
    {
      Multiset.Entry localEntry = (Multiset.Entry)localIterator.next();
      if (Objects.equal(localEntry.getElement(), paramObject))
        return localEntry.getCount();
    }
    return 0;
  }

  @Beta
  protected boolean standardAdd(Object paramObject)
  {
    add(paramObject, 1);
    return true;
  }

  @Beta
  protected boolean standardAddAll(Collection paramCollection)
  {
    return Multisets.addAllImpl(this, paramCollection);
  }

  @Beta
  protected boolean standardRemove(Object paramObject)
  {
    return remove(paramObject, 1) > 0;
  }

  @Beta
  protected boolean standardRemoveAll(Collection paramCollection)
  {
    return Multisets.removeAllImpl(this, paramCollection);
  }

  @Beta
  protected boolean standardRetainAll(Collection paramCollection)
  {
    return Multisets.retainAllImpl(this, paramCollection);
  }

  @Beta
  protected int standardSetCount(Object paramObject, int paramInt)
  {
    return Multisets.setCountImpl(this, paramObject, paramInt);
  }

  @Beta
  protected boolean standardSetCount(Object paramObject, int paramInt1, int paramInt2)
  {
    return Multisets.setCountImpl(this, paramObject, paramInt1, paramInt2);
  }

  @Beta
  protected Iterator standardIterator()
  {
    return Multisets.iteratorImpl(this);
  }

  @Beta
  protected int standardSize()
  {
    return Multisets.sizeImpl(this);
  }

  @Beta
  protected boolean standardEquals(Object paramObject)
  {
    return Multisets.equalsImpl(this, paramObject);
  }

  @Beta
  protected int standardHashCode()
  {
    return entrySet().hashCode();
  }

  @Beta
  protected String standardToString()
  {
    return entrySet().toString();
  }

  @Beta
  protected class StandardElementSet extends Multisets.ElementSet
  {
    public StandardElementSet()
    {
    }

    Multiset multiset()
    {
      return ForwardingMultiset.this;
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.ForwardingMultiset
 * JD-Core Version:    0.6.2
 */