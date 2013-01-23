package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.Collection;

@GwtCompatible(emulated=true)
final class RegularContiguousSet extends ContiguousSet
{
  private final Range range;
  private static final long serialVersionUID = 0L;

  RegularContiguousSet(Range paramRange, DiscreteDomain paramDiscreteDomain)
  {
    super(paramDiscreteDomain);
    this.range = paramRange;
  }

  private ContiguousSet intersectionInCurrentDomain(Range paramRange)
  {
    return this.range.isConnected(paramRange) ? this.range.intersection(paramRange).asSet(this.domain) : new EmptyContiguousSet(this.domain);
  }

  ContiguousSet headSetImpl(Comparable paramComparable, boolean paramBoolean)
  {
    return intersectionInCurrentDomain(Ranges.upTo(paramComparable, BoundType.forBoolean(paramBoolean)));
  }

  ContiguousSet subSetImpl(Comparable paramComparable1, boolean paramBoolean1, Comparable paramComparable2, boolean paramBoolean2)
  {
    if ((paramComparable1.compareTo(paramComparable2) == 0) && (!paramBoolean1) && (!paramBoolean2))
      return new EmptyContiguousSet(this.domain);
    return intersectionInCurrentDomain(Ranges.range(paramComparable1, BoundType.forBoolean(paramBoolean1), paramComparable2, BoundType.forBoolean(paramBoolean2)));
  }

  ContiguousSet tailSetImpl(Comparable paramComparable, boolean paramBoolean)
  {
    return intersectionInCurrentDomain(Ranges.downTo(paramComparable, BoundType.forBoolean(paramBoolean)));
  }

  @GwtIncompatible("not used by GWT emulation")
  int indexOf(Object paramObject)
  {
    return contains(paramObject) ? (int)this.domain.distance(first(), (Comparable)paramObject) : -1;
  }

  public UnmodifiableIterator iterator()
  {
    return new AbstractSequentialIterator(first())
    {
      final Comparable last = RegularContiguousSet.this.last();

      protected Comparable computeNext(Comparable paramAnonymousComparable)
      {
        return RegularContiguousSet.equalsOrThrow(paramAnonymousComparable, this.last) ? null : RegularContiguousSet.this.domain.next(paramAnonymousComparable);
      }
    };
  }

  private static boolean equalsOrThrow(Comparable paramComparable1, Comparable paramComparable2)
  {
    return (paramComparable2 != null) && (Range.compareOrThrow(paramComparable1, paramComparable2) == 0);
  }

  boolean isPartialView()
  {
    return false;
  }

  public Comparable first()
  {
    return this.range.lowerBound.leastValueAbove(this.domain);
  }

  public Comparable last()
  {
    return this.range.upperBound.greatestValueBelow(this.domain);
  }

  public int size()
  {
    long l = this.domain.distance(first(), last());
    return l >= 2147483647L ? 2147483647 : (int)l + 1;
  }

  public boolean contains(Object paramObject)
  {
    if (paramObject == null)
      return false;
    try
    {
      return this.range.contains((Comparable)paramObject);
    }
    catch (ClassCastException localClassCastException)
    {
    }
    return false;
  }

  public boolean containsAll(Collection paramCollection)
  {
    return Collections2.containsAllImpl(this, paramCollection);
  }

  public boolean isEmpty()
  {
    return false;
  }

  public Object[] toArray()
  {
    return ObjectArrays.toArrayImpl(this);
  }

  public Object[] toArray(Object[] paramArrayOfObject)
  {
    return ObjectArrays.toArrayImpl(this, paramArrayOfObject);
  }

  public ContiguousSet intersection(ContiguousSet paramContiguousSet)
  {
    Preconditions.checkNotNull(paramContiguousSet);
    Preconditions.checkArgument(this.domain.equals(paramContiguousSet.domain));
    if (paramContiguousSet.isEmpty())
      return paramContiguousSet;
    Comparable localComparable1 = (Comparable)Ordering.natural().max(first(), paramContiguousSet.first());
    Comparable localComparable2 = (Comparable)Ordering.natural().min(last(), paramContiguousSet.last());
    return localComparable1.compareTo(localComparable2) < 0 ? Ranges.closed(localComparable1, localComparable2).asSet(this.domain) : new EmptyContiguousSet(this.domain);
  }

  public Range range()
  {
    return range(BoundType.CLOSED, BoundType.CLOSED);
  }

  public Range range(BoundType paramBoundType1, BoundType paramBoundType2)
  {
    return Ranges.create(this.range.lowerBound.withLowerBoundType(paramBoundType1, this.domain), this.range.upperBound.withUpperBoundType(paramBoundType2, this.domain));
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == this)
      return true;
    if ((paramObject instanceof RegularContiguousSet))
    {
      RegularContiguousSet localRegularContiguousSet = (RegularContiguousSet)paramObject;
      if (this.domain.equals(localRegularContiguousSet.domain))
        return (first().equals(localRegularContiguousSet.first())) && (last().equals(localRegularContiguousSet.last()));
    }
    return super.equals(paramObject);
  }

  public int hashCode()
  {
    return Sets.hashCodeImpl(this);
  }

  @GwtIncompatible("serialization")
  Object writeReplace()
  {
    return new SerializedForm(this.range, this.domain, null);
  }

  @GwtIncompatible("NavigableSet")
  ImmutableSortedSet createDescendingSet()
  {
    return new DescendingContiguousSet(null);
  }

  @GwtIncompatible("NavigableSet")
  private final class DescendingContiguousSet extends ImmutableSortedSet
  {
    private DescendingContiguousSet()
    {
      super();
    }

    public Comparable first()
    {
      return RegularContiguousSet.this.last();
    }

    public Comparable last()
    {
      return RegularContiguousSet.this.first();
    }

    public int size()
    {
      return RegularContiguousSet.this.size();
    }

    public UnmodifiableIterator iterator()
    {
      return new AbstractSequentialIterator(first())
      {
        final Comparable last = RegularContiguousSet.DescendingContiguousSet.this.last();

        protected Comparable computeNext(Comparable paramAnonymousComparable)
        {
          return RegularContiguousSet.equalsOrThrow(paramAnonymousComparable, this.last) ? null : RegularContiguousSet.this.domain.previous(paramAnonymousComparable);
        }
      };
    }

    ImmutableSortedSet headSetImpl(Comparable paramComparable, boolean paramBoolean)
    {
      return RegularContiguousSet.this.tailSetImpl(paramComparable, paramBoolean).descendingSet();
    }

    ImmutableSortedSet subSetImpl(Comparable paramComparable1, boolean paramBoolean1, Comparable paramComparable2, boolean paramBoolean2)
    {
      return RegularContiguousSet.this.subSetImpl(paramComparable2, paramBoolean2, paramComparable1, paramBoolean1).descendingSet();
    }

    ImmutableSortedSet tailSetImpl(Comparable paramComparable, boolean paramBoolean)
    {
      return RegularContiguousSet.this.headSetImpl(paramComparable, paramBoolean).descendingSet();
    }

    ImmutableSortedSet createDescendingSet()
    {
      return RegularContiguousSet.this;
    }

    int indexOf(Object paramObject)
    {
      return contains(paramObject) ? (int)RegularContiguousSet.this.domain.distance(last(), (Comparable)paramObject) : -1;
    }

    boolean isPartialView()
    {
      return false;
    }
  }

  @GwtIncompatible("serialization")
  private static final class SerializedForm
    implements Serializable
  {
    final Range range;
    final DiscreteDomain domain;

    private SerializedForm(Range paramRange, DiscreteDomain paramDiscreteDomain)
    {
      this.range = paramRange;
      this.domain = paramDiscreteDomain;
    }

    private Object readResolve()
    {
      return new RegularContiguousSet(this.range, this.domain);
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.RegularContiguousSet
 * JD-Core Version:    0.6.2
 */