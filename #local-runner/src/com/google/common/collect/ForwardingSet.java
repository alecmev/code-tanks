package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.Set;

@GwtCompatible
public abstract class ForwardingSet extends ForwardingCollection
  implements Set
{
  protected abstract Set delegate();

  public boolean equals(Object paramObject)
  {
    return (paramObject == this) || (delegate().equals(paramObject));
  }

  public int hashCode()
  {
    return delegate().hashCode();
  }

  protected boolean standardRemoveAll(Collection paramCollection)
  {
    return Sets.removeAllImpl(this, (Collection)Preconditions.checkNotNull(paramCollection));
  }

  @Beta
  protected boolean standardEquals(Object paramObject)
  {
    return Sets.equalsImpl(this, paramObject);
  }

  @Beta
  protected int standardHashCode()
  {
    return Sets.hashCodeImpl(this);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.ForwardingSet
 * JD-Core Version:    0.6.2
 */