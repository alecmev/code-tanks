package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.ListIterator;

@GwtCompatible
public abstract class UnmodifiableListIterator extends UnmodifiableIterator
  implements ListIterator
{
  public final void add(Object paramObject)
  {
    throw new UnsupportedOperationException();
  }

  public final void set(Object paramObject)
  {
    throw new UnsupportedOperationException();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.UnmodifiableListIterator
 * JD-Core Version:    0.6.2
 */