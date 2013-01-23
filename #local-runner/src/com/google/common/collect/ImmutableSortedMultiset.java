package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Beta
@GwtIncompatible("hasn't been tested yet")
public abstract class ImmutableSortedMultiset extends ImmutableSortedMultisetFauxverideShim
  implements SortedMultiset
{
  private static final Comparator NATURAL_ORDER = Ordering.natural();
  private static final ImmutableSortedMultiset NATURAL_EMPTY_MULTISET = new EmptyImmutableSortedMultiset(NATURAL_ORDER);
  private final transient Comparator comparator;
  private transient Comparator reverseComparator;
  private transient ImmutableSortedSet elementSet;
  transient ImmutableSortedMultiset descendingMultiset;

  public static ImmutableSortedMultiset of()
  {
    return NATURAL_EMPTY_MULTISET;
  }

  public static ImmutableSortedMultiset of(Comparable paramComparable)
  {
    return RegularImmutableSortedMultiset.createFromSorted(NATURAL_ORDER, ImmutableList.of(Multisets.immutableEntry(Preconditions.checkNotNull(paramComparable), 1)));
  }

  public static ImmutableSortedMultiset of(Comparable paramComparable1, Comparable paramComparable2)
  {
    return copyOf(Ordering.natural(), Arrays.asList(new Comparable[] { paramComparable1, paramComparable2 }));
  }

  public static ImmutableSortedMultiset of(Comparable paramComparable1, Comparable paramComparable2, Comparable paramComparable3)
  {
    return copyOf(Ordering.natural(), Arrays.asList(new Comparable[] { paramComparable1, paramComparable2, paramComparable3 }));
  }

  public static ImmutableSortedMultiset of(Comparable paramComparable1, Comparable paramComparable2, Comparable paramComparable3, Comparable paramComparable4)
  {
    return copyOf(Ordering.natural(), Arrays.asList(new Comparable[] { paramComparable1, paramComparable2, paramComparable3, paramComparable4 }));
  }

  public static ImmutableSortedMultiset of(Comparable paramComparable1, Comparable paramComparable2, Comparable paramComparable3, Comparable paramComparable4, Comparable paramComparable5)
  {
    return copyOf(Ordering.natural(), Arrays.asList(new Comparable[] { paramComparable1, paramComparable2, paramComparable3, paramComparable4, paramComparable5 }));
  }

  public static ImmutableSortedMultiset of(Comparable paramComparable1, Comparable paramComparable2, Comparable paramComparable3, Comparable paramComparable4, Comparable paramComparable5, Comparable paramComparable6, Comparable[] paramArrayOfComparable)
  {
    int i = paramArrayOfComparable.length + 6;
    ArrayList localArrayList = Lists.newArrayListWithCapacity(i);
    Collections.addAll(localArrayList, new Comparable[] { paramComparable1, paramComparable2, paramComparable3, paramComparable4, paramComparable5, paramComparable6 });
    Collections.addAll(localArrayList, paramArrayOfComparable);
    return copyOf(Ordering.natural(), localArrayList);
  }

  public static ImmutableSortedMultiset copyOf(Comparable[] paramArrayOfComparable)
  {
    return copyOf(Ordering.natural(), Arrays.asList(paramArrayOfComparable));
  }

  public static ImmutableSortedMultiset copyOf(Iterable paramIterable)
  {
    Ordering localOrdering = Ordering.natural();
    return copyOf(localOrdering, paramIterable);
  }

  public static ImmutableSortedMultiset copyOf(Iterator paramIterator)
  {
    Ordering localOrdering = Ordering.natural();
    return copyOfInternal(localOrdering, paramIterator);
  }

  public static ImmutableSortedMultiset copyOf(Comparator paramComparator, Iterator paramIterator)
  {
    Preconditions.checkNotNull(paramComparator);
    return copyOfInternal(paramComparator, paramIterator);
  }

  public static ImmutableSortedMultiset copyOf(Comparator paramComparator, Iterable paramIterable)
  {
    Preconditions.checkNotNull(paramComparator);
    return copyOfInternal(paramComparator, paramIterable);
  }

  public static ImmutableSortedMultiset copyOfSorted(SortedMultiset paramSortedMultiset)
  {
    Comparator localComparator = paramSortedMultiset.comparator();
    if (localComparator == null)
      localComparator = NATURAL_ORDER;
    return copyOfInternal(localComparator, paramSortedMultiset);
  }

  private static ImmutableSortedMultiset copyOfInternal(Comparator paramComparator, Iterable paramIterable)
  {
    if ((SortedIterables.hasSameComparator(paramComparator, paramIterable)) && ((paramIterable instanceof ImmutableSortedMultiset)))
    {
      localObject = (ImmutableSortedMultiset)paramIterable;
      if (!((ImmutableSortedMultiset)localObject).isPartialView())
        return (ImmutableSortedMultiset)paramIterable;
    }
    Object localObject = ImmutableList.copyOf(SortedIterables.sortedCounts(paramComparator, paramIterable));
    if (((ImmutableList)localObject).isEmpty())
      return emptyMultiset(paramComparator);
    verifyEntries((Collection)localObject);
    return RegularImmutableSortedMultiset.createFromSorted(paramComparator, (List)localObject);
  }

  private static ImmutableSortedMultiset copyOfInternal(Comparator paramComparator, Iterator paramIterator)
  {
    ImmutableList localImmutableList = ImmutableList.copyOf(SortedIterables.sortedCounts(paramComparator, paramIterator));
    if (localImmutableList.isEmpty())
      return emptyMultiset(paramComparator);
    verifyEntries(localImmutableList);
    return RegularImmutableSortedMultiset.createFromSorted(paramComparator, localImmutableList);
  }

  private static void verifyEntries(Collection paramCollection)
  {
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext())
    {
      Multiset.Entry localEntry = (Multiset.Entry)localIterator.next();
      Preconditions.checkNotNull(localEntry.getElement());
    }
  }

  static ImmutableSortedMultiset emptyMultiset(Comparator paramComparator)
  {
    if (NATURAL_ORDER.equals(paramComparator))
      return NATURAL_EMPTY_MULTISET;
    return new EmptyImmutableSortedMultiset(paramComparator);
  }

  ImmutableSortedMultiset(Comparator paramComparator)
  {
    this.comparator = ((Comparator)Preconditions.checkNotNull(paramComparator));
  }

  public Comparator comparator()
  {
    return this.comparator;
  }

  Comparator unsafeComparator()
  {
    return this.comparator;
  }

  Comparator reverseComparator()
  {
    Comparator localComparator = this.reverseComparator;
    if (localComparator == null)
      return this.reverseComparator = Ordering.from(this.comparator).reverse();
    return localComparator;
  }

  public ImmutableSortedSet elementSet()
  {
    ImmutableSortedSet localImmutableSortedSet = this.elementSet;
    if (localImmutableSortedSet == null)
      return this.elementSet = createElementSet();
    return localImmutableSortedSet;
  }

  abstract ImmutableSortedSet createElementSet();

  abstract ImmutableSortedSet createDescendingElementSet();

  public ImmutableSortedMultiset descendingMultiset()
  {
    ImmutableSortedMultiset localImmutableSortedMultiset = this.descendingMultiset;
    if (localImmutableSortedMultiset == null)
      return this.descendingMultiset = new DescendingImmutableSortedMultiset(this);
    return localImmutableSortedMultiset;
  }

  public final Multiset.Entry pollFirstEntry()
  {
    throw new UnsupportedOperationException();
  }

  public Multiset.Entry pollLastEntry()
  {
    throw new UnsupportedOperationException();
  }

  public abstract ImmutableSortedMultiset headMultiset(Object paramObject, BoundType paramBoundType);

  public ImmutableSortedMultiset subMultiset(Object paramObject1, BoundType paramBoundType1, Object paramObject2, BoundType paramBoundType2)
  {
    return tailMultiset(paramObject1, paramBoundType1).headMultiset(paramObject2, paramBoundType2);
  }

  public abstract ImmutableSortedMultiset tailMultiset(Object paramObject, BoundType paramBoundType);

  public static Builder orderedBy(Comparator paramComparator)
  {
    return new Builder(paramComparator);
  }

  public static Builder reverseOrder()
  {
    return new Builder(Ordering.natural().reverse());
  }

  public static Builder naturalOrder()
  {
    return new Builder(Ordering.natural());
  }

  Object writeReplace()
  {
    return new SerializedForm(this);
  }

  private static final class SerializedForm
    implements Serializable
  {
    Comparator comparator;
    Object[] elements;
    int[] counts;

    SerializedForm(SortedMultiset paramSortedMultiset)
    {
      this.comparator = paramSortedMultiset.comparator();
      int i = paramSortedMultiset.entrySet().size();
      this.elements = new Object[i];
      this.counts = new int[i];
      int j = 0;
      Iterator localIterator = paramSortedMultiset.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Multiset.Entry localEntry = (Multiset.Entry)localIterator.next();
        this.elements[j] = localEntry.getElement();
        this.counts[j] = localEntry.getCount();
        j++;
      }
    }

    Object readResolve()
    {
      int i = this.elements.length;
      ImmutableSortedMultiset.Builder localBuilder = ImmutableSortedMultiset.orderedBy(this.comparator);
      for (int j = 0; j < i; j++)
        localBuilder.addCopies(this.elements[j], this.counts[j]);
      return localBuilder.build();
    }
  }

  public static class Builder extends ImmutableMultiset.Builder
  {
    private final Comparator comparator;

    public Builder(Comparator paramComparator)
    {
      super();
      this.comparator = ((Comparator)Preconditions.checkNotNull(paramComparator));
    }

    public Builder add(Object paramObject)
    {
      super.add(paramObject);
      return this;
    }

    public Builder addCopies(Object paramObject, int paramInt)
    {
      super.addCopies(paramObject, paramInt);
      return this;
    }

    public Builder setCount(Object paramObject, int paramInt)
    {
      super.setCount(paramObject, paramInt);
      return this;
    }

    public Builder add(Object[] paramArrayOfObject)
    {
      super.add(paramArrayOfObject);
      return this;
    }

    public Builder addAll(Iterable paramIterable)
    {
      super.addAll(paramIterable);
      return this;
    }

    public Builder addAll(Iterator paramIterator)
    {
      super.addAll(paramIterator);
      return this;
    }

    public ImmutableSortedMultiset build()
    {
      return ImmutableSortedMultiset.copyOf(this.comparator, this.contents);
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.ImmutableSortedMultiset
 * JD-Core Version:    0.6.2
 */