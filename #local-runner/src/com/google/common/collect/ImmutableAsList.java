package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

@GwtCompatible(serializable=true, emulated=true)
final class ImmutableAsList extends RegularImmutableList
{
  private final transient ImmutableCollection collection;

  ImmutableAsList(Object[] paramArrayOfObject, ImmutableCollection paramImmutableCollection)
  {
    super(paramArrayOfObject, 0, paramArrayOfObject.length);
    this.collection = paramImmutableCollection;
  }

  public boolean contains(Object paramObject)
  {
    return this.collection.contains(paramObject);
  }

  private void readObject(ObjectInputStream paramObjectInputStream)
    throws InvalidObjectException
  {
    throw new InvalidObjectException("Use SerializedForm");
  }

  Object writeReplace()
  {
    return new SerializedForm(this.collection);
  }

  static class SerializedForm
    implements Serializable
  {
    final ImmutableCollection collection;
    private static final long serialVersionUID = 0L;

    SerializedForm(ImmutableCollection paramImmutableCollection)
    {
      this.collection = paramImmutableCollection;
    }

    Object readResolve()
    {
      return this.collection.asList();
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.ImmutableAsList
 * JD-Core Version:    0.6.2
 */