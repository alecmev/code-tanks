package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.List;

@GwtCompatible
abstract class TransformedImmutableList extends ImmutableList
{
  private final transient ImmutableList backingList;

  TransformedImmutableList(ImmutableList paramImmutableList)
  {
    this.backingList = ((ImmutableList)Preconditions.checkNotNull(paramImmutableList));
  }

  abstract Object transform(Object paramObject);

  public Object get(int paramInt)
  {
    return transform(this.backingList.get(paramInt));
  }

  public int size()
  {
    return this.backingList.size();
  }

  public ImmutableList subList(int paramInt1, int paramInt2)
  {
    return new TransformedView(this.backingList.subList(paramInt1, paramInt2));
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == this)
      return true;
    if ((paramObject instanceof List))
    {
      List localList = (List)paramObject;
      return (size() == localList.size()) && (Iterators.elementsEqual(iterator(), localList.iterator()));
    }
    return false;
  }

  public int hashCode()
  {
    return Lists.hashCodeImpl(this);
  }

  boolean isPartialView()
  {
    return true;
  }

  private class TransformedView extends TransformedImmutableList
  {
    TransformedView(ImmutableList arg2)
    {
      super();
    }

    Object transform(Object paramObject)
    {
      return TransformedImmutableList.this.transform(paramObject);
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.TransformedImmutableList
 * JD-Core Version:    0.6.2
 */