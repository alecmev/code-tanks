package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedSet;

@GwtCompatible
public final class Multisets
{
  private static final Ordering DECREASING_COUNT_ORDERING = new Ordering()
  {
    public int compare(Multiset.Entry paramAnonymousEntry1, Multiset.Entry paramAnonymousEntry2)
    {
      return Ints.compare(paramAnonymousEntry2.getCount(), paramAnonymousEntry1.getCount());
    }
  };

  public static Multiset unmodifiableMultiset(Multiset paramMultiset)
  {
    if (((paramMultiset instanceof UnmodifiableMultiset)) || ((paramMultiset instanceof ImmutableMultiset)))
    {
      Multiset localMultiset = paramMultiset;
      return localMultiset;
    }
    return new UnmodifiableMultiset((Multiset)Preconditions.checkNotNull(paramMultiset));
  }

  @Deprecated
  public static Multiset unmodifiableMultiset(ImmutableMultiset paramImmutableMultiset)
  {
    return (Multiset)Preconditions.checkNotNull(paramImmutableMultiset);
  }

  @Beta
  public static SortedMultiset unmodifiableSortedMultiset(SortedMultiset paramSortedMultiset)
  {
    return new UnmodifiableSortedMultiset((SortedMultiset)Preconditions.checkNotNull(paramSortedMultiset), null);
  }

  public static Multiset.Entry immutableEntry(Object paramObject, int paramInt)
  {
    return new ImmutableEntry(paramObject, paramInt);
  }

  static Multiset forSet(Set paramSet)
  {
    return new SetMultiset(paramSet);
  }

  static int inferDistinctElements(Iterable paramIterable)
  {
    if ((paramIterable instanceof Multiset))
      return ((Multiset)paramIterable).elementSet().size();
    return 11;
  }

  public static Multiset intersection(Multiset paramMultiset1, final Multiset paramMultiset2)
  {
    Preconditions.checkNotNull(paramMultiset1);
    Preconditions.checkNotNull(paramMultiset2);
    return new AbstractMultiset()
    {
      public int count(Object paramAnonymousObject)
      {
        int i = this.val$multiset1.count(paramAnonymousObject);
        return i == 0 ? 0 : Math.min(i, paramMultiset2.count(paramAnonymousObject));
      }

      Set createElementSet()
      {
        return Sets.intersection(this.val$multiset1.elementSet(), paramMultiset2.elementSet());
      }

      Iterator entryIterator()
      {
        final Iterator localIterator = this.val$multiset1.entrySet().iterator();
        return new AbstractIterator()
        {
          protected Multiset.Entry computeNext()
          {
            while (localIterator.hasNext())
            {
              Multiset.Entry localEntry = (Multiset.Entry)localIterator.next();
              Object localObject = localEntry.getElement();
              int i = Math.min(localEntry.getCount(), Multisets.1.this.val$multiset2.count(localObject));
              if (i > 0)
                return Multisets.immutableEntry(localObject, i);
            }
            return (Multiset.Entry)endOfData();
          }
        };
      }

      int distinctElements()
      {
        return elementSet().size();
      }
    };
  }

  @Beta
  public static boolean containsOccurrences(Multiset paramMultiset1, Multiset paramMultiset2)
  {
    Preconditions.checkNotNull(paramMultiset1);
    Preconditions.checkNotNull(paramMultiset2);
    Iterator localIterator = paramMultiset2.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Multiset.Entry localEntry = (Multiset.Entry)localIterator.next();
      int i = paramMultiset1.count(localEntry.getElement());
      if (i < localEntry.getCount())
        return false;
    }
    return true;
  }

  @Beta
  public static boolean retainOccurrences(Multiset paramMultiset1, Multiset paramMultiset2)
  {
    return retainOccurrencesImpl(paramMultiset1, paramMultiset2);
  }

  private static boolean retainOccurrencesImpl(Multiset paramMultiset1, Multiset paramMultiset2)
  {
    Preconditions.checkNotNull(paramMultiset1);
    Preconditions.checkNotNull(paramMultiset2);
    Iterator localIterator = paramMultiset1.entrySet().iterator();
    boolean bool = false;
    while (localIterator.hasNext())
    {
      Multiset.Entry localEntry = (Multiset.Entry)localIterator.next();
      int i = paramMultiset2.count(localEntry.getElement());
      if (i == 0)
      {
        localIterator.remove();
        bool = true;
      }
      else if (i < localEntry.getCount())
      {
        paramMultiset1.setCount(localEntry.getElement(), i);
        bool = true;
      }
    }
    return bool;
  }

  @Beta
  public static boolean removeOccurrences(Multiset paramMultiset1, Multiset paramMultiset2)
  {
    return removeOccurrencesImpl(paramMultiset1, paramMultiset2);
  }

  private static boolean removeOccurrencesImpl(Multiset paramMultiset1, Multiset paramMultiset2)
  {
    Preconditions.checkNotNull(paramMultiset1);
    Preconditions.checkNotNull(paramMultiset2);
    boolean bool = false;
    Iterator localIterator = paramMultiset1.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Multiset.Entry localEntry = (Multiset.Entry)localIterator.next();
      int i = paramMultiset2.count(localEntry.getElement());
      if (i >= localEntry.getCount())
      {
        localIterator.remove();
        bool = true;
      }
      else if (i > 0)
      {
        paramMultiset1.remove(localEntry.getElement(), i);
        bool = true;
      }
    }
    return bool;
  }

  static boolean equalsImpl(Multiset paramMultiset, Object paramObject)
  {
    if (paramObject == paramMultiset)
      return true;
    if ((paramObject instanceof Multiset))
    {
      Multiset localMultiset = (Multiset)paramObject;
      if ((paramMultiset.size() != localMultiset.size()) || (paramMultiset.entrySet().size() != localMultiset.entrySet().size()))
        return false;
      Iterator localIterator = localMultiset.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Multiset.Entry localEntry = (Multiset.Entry)localIterator.next();
        if (paramMultiset.count(localEntry.getElement()) != localEntry.getCount())
          return false;
      }
      return true;
    }
    return false;
  }

  static boolean addAllImpl(Multiset paramMultiset, Collection paramCollection)
  {
    if (paramCollection.isEmpty())
      return false;
    if ((paramCollection instanceof Multiset))
    {
      Multiset localMultiset = cast(paramCollection);
      Iterator localIterator = localMultiset.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Multiset.Entry localEntry = (Multiset.Entry)localIterator.next();
        paramMultiset.add(localEntry.getElement(), localEntry.getCount());
      }
    }
    else
    {
      Iterators.addAll(paramMultiset, paramCollection.iterator());
    }
    return true;
  }

  static boolean removeAllImpl(Multiset paramMultiset, Collection paramCollection)
  {
    Collection localCollection = (paramCollection instanceof Multiset) ? ((Multiset)paramCollection).elementSet() : paramCollection;
    return paramMultiset.elementSet().removeAll(localCollection);
  }

  static boolean retainAllImpl(Multiset paramMultiset, Collection paramCollection)
  {
    Collection localCollection = (paramCollection instanceof Multiset) ? ((Multiset)paramCollection).elementSet() : paramCollection;
    return paramMultiset.elementSet().retainAll(localCollection);
  }

  static int setCountImpl(Multiset paramMultiset, Object paramObject, int paramInt)
  {
    checkNonnegative(paramInt, "count");
    int i = paramMultiset.count(paramObject);
    int j = paramInt - i;
    if (j > 0)
      paramMultiset.add(paramObject, j);
    else if (j < 0)
      paramMultiset.remove(paramObject, -j);
    return i;
  }

  static boolean setCountImpl(Multiset paramMultiset, Object paramObject, int paramInt1, int paramInt2)
  {
    checkNonnegative(paramInt1, "oldCount");
    checkNonnegative(paramInt2, "newCount");
    if (paramMultiset.count(paramObject) == paramInt1)
    {
      paramMultiset.setCount(paramObject, paramInt2);
      return true;
    }
    return false;
  }

  static Iterator iteratorImpl(Multiset paramMultiset)
  {
    return new MultisetIteratorImpl(paramMultiset, paramMultiset.entrySet().iterator());
  }

  static int sizeImpl(Multiset paramMultiset)
  {
    long l = 0L;
    Iterator localIterator = paramMultiset.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Multiset.Entry localEntry = (Multiset.Entry)localIterator.next();
      l += localEntry.getCount();
    }
    return Ints.saturatedCast(l);
  }

  static void checkNonnegative(int paramInt, String paramString)
  {
    Preconditions.checkArgument(paramInt >= 0, "%s cannot be negative: %s", new Object[] { paramString, Integer.valueOf(paramInt) });
  }

  static Multiset cast(Iterable paramIterable)
  {
    return (Multiset)paramIterable;
  }

  @Beta
  public static ImmutableMultiset copyHighestCountFirst(Multiset paramMultiset)
  {
    List localList = DECREASING_COUNT_ORDERING.sortedCopy(paramMultiset.entrySet());
    return ImmutableMultiset.copyFromEntries(localList);
  }

  static final class MultisetIteratorImpl
    implements Iterator
  {
    private final Multiset multiset;
    private final Iterator entryIterator;
    private Multiset.Entry currentEntry;
    private int laterCount;
    private int totalCount;
    private boolean canRemove;

    MultisetIteratorImpl(Multiset paramMultiset, Iterator paramIterator)
    {
      this.multiset = paramMultiset;
      this.entryIterator = paramIterator;
    }

    public boolean hasNext()
    {
      return (this.laterCount > 0) || (this.entryIterator.hasNext());
    }

    public Object next()
    {
      if (!hasNext())
        throw new NoSuchElementException();
      if (this.laterCount == 0)
      {
        this.currentEntry = ((Multiset.Entry)this.entryIterator.next());
        this.totalCount = (this.laterCount = this.currentEntry.getCount());
      }
      this.laterCount -= 1;
      this.canRemove = true;
      return this.currentEntry.getElement();
    }

    public void remove()
    {
      Iterators.checkRemove(this.canRemove);
      if (this.totalCount == 1)
        this.entryIterator.remove();
      else
        this.multiset.remove(this.currentEntry.getElement());
      this.totalCount -= 1;
      this.canRemove = false;
    }
  }

  static abstract class EntrySet extends AbstractSet
  {
    abstract Multiset multiset();

    public boolean contains(Object paramObject)
    {
      if ((paramObject instanceof Multiset.Entry))
      {
        Multiset.Entry localEntry = (Multiset.Entry)paramObject;
        if (localEntry.getCount() <= 0)
          return false;
        int i = multiset().count(localEntry.getElement());
        return i == localEntry.getCount();
      }
      return false;
    }

    public boolean remove(Object paramObject)
    {
      return (contains(paramObject)) && (multiset().elementSet().remove(((Multiset.Entry)paramObject).getElement()));
    }

    public void clear()
    {
      multiset().clear();
    }
  }

  static abstract class ElementSet extends AbstractSet
  {
    abstract Multiset multiset();

    public void clear()
    {
      multiset().clear();
    }

    public boolean contains(Object paramObject)
    {
      return multiset().contains(paramObject);
    }

    public boolean containsAll(Collection paramCollection)
    {
      return multiset().containsAll(paramCollection);
    }

    public boolean isEmpty()
    {
      return multiset().isEmpty();
    }

    public Iterator iterator()
    {
      return new TransformedIterator(multiset().entrySet().iterator())
      {
        Object transform(Multiset.Entry paramAnonymousEntry)
        {
          return paramAnonymousEntry.getElement();
        }
      };
    }

    public boolean remove(Object paramObject)
    {
      int i = multiset().count(paramObject);
      if (i > 0)
      {
        multiset().remove(paramObject, i);
        return true;
      }
      return false;
    }

    public int size()
    {
      return multiset().entrySet().size();
    }
  }

  static abstract class AbstractEntry
    implements Multiset.Entry
  {
    public boolean equals(Object paramObject)
    {
      if ((paramObject instanceof Multiset.Entry))
      {
        Multiset.Entry localEntry = (Multiset.Entry)paramObject;
        return (getCount() == localEntry.getCount()) && (Objects.equal(getElement(), localEntry.getElement()));
      }
      return false;
    }

    public int hashCode()
    {
      Object localObject = getElement();
      return (localObject == null ? 0 : localObject.hashCode()) ^ getCount();
    }

    public String toString()
    {
      String str = String.valueOf(getElement());
      int i = getCount();
      return str + " x " + i;
    }
  }

  private static class SetMultiset extends ForwardingCollection
    implements Multiset, Serializable
  {
    final Set delegate;
    transient Set elementSet;
    transient Set entrySet;
    private static final long serialVersionUID = 0L;

    SetMultiset(Set paramSet)
    {
      this.delegate = ((Set)Preconditions.checkNotNull(paramSet));
    }

    protected Set delegate()
    {
      return this.delegate;
    }

    public int count(Object paramObject)
    {
      return this.delegate.contains(paramObject) ? 1 : 0;
    }

    public int add(Object paramObject, int paramInt)
    {
      throw new UnsupportedOperationException();
    }

    public int remove(Object paramObject, int paramInt)
    {
      if (paramInt == 0)
        return count(paramObject);
      Preconditions.checkArgument(paramInt > 0);
      return this.delegate.remove(paramObject) ? 1 : 0;
    }

    public Set elementSet()
    {
      Set localSet = this.elementSet;
      return localSet == null ? (this.elementSet = new ElementSet()) : localSet;
    }

    public Set entrySet()
    {
      Object localObject = this.entrySet;
      if (localObject == null)
        localObject = this.entrySet = new Multisets.EntrySet()
        {
          Multiset multiset()
          {
            return Multisets.SetMultiset.this;
          }

          public Iterator iterator()
          {
            return new TransformedIterator(Multisets.SetMultiset.this.delegate.iterator())
            {
              Multiset.Entry transform(Object paramAnonymous2Object)
              {
                return Multisets.immutableEntry(paramAnonymous2Object, 1);
              }
            };
          }

          public int size()
          {
            return Multisets.SetMultiset.this.delegate.size();
          }
        };
      return localObject;
    }

    public boolean add(Object paramObject)
    {
      throw new UnsupportedOperationException();
    }

    public boolean addAll(Collection paramCollection)
    {
      throw new UnsupportedOperationException();
    }

    public int setCount(Object paramObject, int paramInt)
    {
      Multisets.checkNonnegative(paramInt, "count");
      if (paramInt == count(paramObject))
        return paramInt;
      if (paramInt == 0)
      {
        remove(paramObject);
        return 1;
      }
      throw new UnsupportedOperationException();
    }

    public boolean setCount(Object paramObject, int paramInt1, int paramInt2)
    {
      return Multisets.setCountImpl(this, paramObject, paramInt1, paramInt2);
    }

    public boolean equals(Object paramObject)
    {
      if ((paramObject instanceof Serializable))
      {
        Multiset localMultiset = (Serializable)paramObject;
        return (size() == localMultiset.size()) && (this.delegate.equals(localMultiset.elementSet()));
      }
      return false;
    }

    public int hashCode()
    {
      int i = 0;
      Iterator localIterator = iterator();
      while (localIterator.hasNext())
      {
        Object localObject = localIterator.next();
        i += ((localObject == null ? 0 : localObject.hashCode()) ^ 0x1);
      }
      return i;
    }

    class ElementSet extends ForwardingSet
    {
      ElementSet()
      {
      }

      protected Set delegate()
      {
        return Multisets.SetMultiset.this.delegate;
      }

      public boolean add(Object paramObject)
      {
        throw new UnsupportedOperationException();
      }

      public boolean addAll(Collection paramCollection)
      {
        throw new UnsupportedOperationException();
      }
    }
  }

  static final class ImmutableEntry extends Multisets.AbstractEntry
    implements Serializable
  {
    final Object element;
    final int count;
    private static final long serialVersionUID = 0L;

    ImmutableEntry(Object paramObject, int paramInt)
    {
      this.element = paramObject;
      this.count = paramInt;
      Preconditions.checkArgument(paramInt >= 0);
    }

    public Object getElement()
    {
      return this.element;
    }

    public int getCount()
    {
      return this.count;
    }
  }

  private static final class UnmodifiableSortedMultiset extends Multisets.UnmodifiableMultiset
    implements SortedMultiset
  {
    private transient UnmodifiableSortedMultiset descendingMultiset;
    private static final long serialVersionUID = 0L;

    private UnmodifiableSortedMultiset(SortedMultiset paramSortedMultiset)
    {
      super();
    }

    protected SortedMultiset delegate()
    {
      return (SortedMultiset)super.delegate();
    }

    public Comparator comparator()
    {
      return delegate().comparator();
    }

    SortedSet createElementSet()
    {
      return Collections.unmodifiableSortedSet(delegate().elementSet());
    }

    public SortedSet elementSet()
    {
      return (SortedSet)super.elementSet();
    }

    public SortedMultiset descendingMultiset()
    {
      UnmodifiableSortedMultiset localUnmodifiableSortedMultiset = this.descendingMultiset;
      if (localUnmodifiableSortedMultiset == null)
      {
        localUnmodifiableSortedMultiset = new UnmodifiableSortedMultiset(delegate().descendingMultiset());
        localUnmodifiableSortedMultiset.descendingMultiset = this;
        return this.descendingMultiset = localUnmodifiableSortedMultiset;
      }
      return localUnmodifiableSortedMultiset;
    }

    public Multiset.Entry firstEntry()
    {
      return delegate().firstEntry();
    }

    public Multiset.Entry lastEntry()
    {
      return delegate().lastEntry();
    }

    public Multiset.Entry pollFirstEntry()
    {
      throw new UnsupportedOperationException();
    }

    public Multiset.Entry pollLastEntry()
    {
      throw new UnsupportedOperationException();
    }

    public SortedMultiset headMultiset(Object paramObject, BoundType paramBoundType)
    {
      return Multisets.unmodifiableSortedMultiset(delegate().headMultiset(paramObject, paramBoundType));
    }

    public SortedMultiset subMultiset(Object paramObject1, BoundType paramBoundType1, Object paramObject2, BoundType paramBoundType2)
    {
      return Multisets.unmodifiableSortedMultiset(delegate().subMultiset(paramObject1, paramBoundType1, paramObject2, paramBoundType2));
    }

    public SortedMultiset tailMultiset(Object paramObject, BoundType paramBoundType)
    {
      return Multisets.unmodifiableSortedMultiset(delegate().tailMultiset(paramObject, paramBoundType));
    }
  }

  static class UnmodifiableMultiset extends ForwardingMultiset
    implements Serializable
  {
    final Multiset delegate;
    transient Set elementSet;
    transient Set entrySet;
    private static final long serialVersionUID = 0L;

    UnmodifiableMultiset(Multiset paramMultiset)
    {
      this.delegate = paramMultiset;
    }

    protected Multiset delegate()
    {
      return this.delegate;
    }

    Set createElementSet()
    {
      return Collections.unmodifiableSet(this.delegate.elementSet());
    }

    public Set elementSet()
    {
      Set localSet = this.elementSet;
      return localSet == null ? (this.elementSet = createElementSet()) : localSet;
    }

    public Set entrySet()
    {
      Set localSet = this.entrySet;
      return localSet == null ? (this.entrySet = Collections.unmodifiableSet(this.delegate.entrySet())) : localSet;
    }

    public Iterator iterator()
    {
      return Iterators.unmodifiableIterator(this.delegate.iterator());
    }

    public boolean add(Object paramObject)
    {
      throw new UnsupportedOperationException();
    }

    public int add(Object paramObject, int paramInt)
    {
      throw new UnsupportedOperationException();
    }

    public boolean addAll(Collection paramCollection)
    {
      throw new UnsupportedOperationException();
    }

    public boolean remove(Object paramObject)
    {
      throw new UnsupportedOperationException();
    }

    public int remove(Object paramObject, int paramInt)
    {
      throw new UnsupportedOperationException();
    }

    public boolean removeAll(Collection paramCollection)
    {
      throw new UnsupportedOperationException();
    }

    public boolean retainAll(Collection paramCollection)
    {
      throw new UnsupportedOperationException();
    }

    public void clear()
    {
      throw new UnsupportedOperationException();
    }

    public int setCount(Object paramObject, int paramInt)
    {
      throw new UnsupportedOperationException();
    }

    public boolean setCount(Object paramObject, int paramInt1, int paramInt2)
    {
      throw new UnsupportedOperationException();
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.Multisets
 * JD-Core Version:    0.6.2
 */