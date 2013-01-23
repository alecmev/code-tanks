package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import java.util.Collection;
import java.util.EnumSet;

@GwtCompatible(serializable=true, emulated=true)
final class ImmutableEnumSet extends ImmutableSet
{
  private final transient EnumSet delegate;
  private transient int hashCode;

  ImmutableEnumSet(EnumSet paramEnumSet)
  {
    this.delegate = paramEnumSet;
  }

  boolean isPartialView()
  {
    return false;
  }

  public UnmodifiableIterator iterator()
  {
    return Iterators.unmodifiableIterator(this.delegate.iterator());
  }

  public int size()
  {
    return this.delegate.size();
  }

  public boolean contains(Object paramObject)
  {
    return this.delegate.contains(paramObject);
  }

  public boolean containsAll(Collection paramCollection)
  {
    return this.delegate.containsAll(paramCollection);
  }

  public boolean isEmpty()
  {
    return this.delegate.isEmpty();
  }

  public Object[] toArray()
  {
    return this.delegate.toArray();
  }

  public Object[] toArray(Object[] paramArrayOfObject)
  {
    return this.delegate.toArray(paramArrayOfObject);
  }

  public boolean equals(Object paramObject)
  {
    return (paramObject == this) || (this.delegate.equals(paramObject));
  }

  public int hashCode()
  {
    int i = this.hashCode;
    return i == 0 ? (this.hashCode = this.delegate.hashCode()) : i;
  }

  public String toString()
  {
    return this.delegate.toString();
  }

  Object writeReplace()
  {
    return new EnumSerializedForm(this.delegate);
  }

  private static class EnumSerializedForm
    implements Serializable
  {
    final EnumSet delegate;
    private static final long serialVersionUID = 0L;

    EnumSerializedForm(EnumSet paramEnumSet)
    {
      this.delegate = paramEnumSet;
    }

    Object readResolve()
    {
      return new ImmutableEnumSet(this.delegate.clone());
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.ImmutableEnumSet
 * JD-Core Version:    0.6.2
 */