package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import java.util.NoSuchElementException;

@GwtCompatible
@Beta
public abstract class DiscreteDomain
{
  public abstract Comparable next(Comparable paramComparable);

  public abstract Comparable previous(Comparable paramComparable);

  public abstract long distance(Comparable paramComparable1, Comparable paramComparable2);

  public Comparable minValue()
  {
    throw new NoSuchElementException();
  }

  public Comparable maxValue()
  {
    throw new NoSuchElementException();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.DiscreteDomain
 * JD-Core Version:    0.6.2
 */