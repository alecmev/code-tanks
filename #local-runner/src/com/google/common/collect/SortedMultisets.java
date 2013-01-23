package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedSet;

@GwtCompatible
final class SortedMultisets
{
  private static Object getElementOrThrow(Multiset.Entry paramEntry)
  {
    if (paramEntry == null)
      throw new NoSuchElementException();
    return paramEntry.getElement();
  }

  static abstract class DescendingMultiset extends ForwardingMultiset
    implements SortedMultiset
  {
    private transient Comparator comparator;
    private transient SortedSet elementSet;
    private transient Set entrySet;

    abstract SortedMultiset forwardMultiset();

    public Comparator comparator()
    {
      Comparator localComparator = this.comparator;
      if (localComparator == null)
        return this.comparator = Ordering.from(forwardMultiset().comparator()).reverse();
      return localComparator;
    }

    public SortedSet elementSet()
    {
      SortedSet localSortedSet = this.elementSet;
      if (localSortedSet == null)
        return this.elementSet = new SortedMultisets.ElementSet()
        {
          SortedMultiset multiset()
          {
            return SortedMultisets.DescendingMultiset.this;
          }
        };
      return localSortedSet;
    }

    public Multiset.Entry pollFirstEntry()
    {
      return forwardMultiset().pollLastEntry();
    }

    public Multiset.Entry pollLastEntry()
    {
      return forwardMultiset().pollFirstEntry();
    }

    public SortedMultiset headMultiset(Object paramObject, BoundType paramBoundType)
    {
      return forwardMultiset().tailMultiset(paramObject, paramBoundType).descendingMultiset();
    }

    public SortedMultiset subMultiset(Object paramObject1, BoundType paramBoundType1, Object paramObject2, BoundType paramBoundType2)
    {
      return forwardMultiset().subMultiset(paramObject2, paramBoundType2, paramObject1, paramBoundType1).descendingMultiset();
    }

    public SortedMultiset tailMultiset(Object paramObject, BoundType paramBoundType)
    {
      return forwardMultiset().headMultiset(paramObject, paramBoundType).descendingMultiset();
    }

    protected Multiset delegate()
    {
      return forwardMultiset();
    }

    public SortedMultiset descendingMultiset()
    {
      return forwardMultiset();
    }

    public Multiset.Entry firstEntry()
    {
      return forwardMultiset().lastEntry();
    }

    public Multiset.Entry lastEntry()
    {
      return forwardMultiset().firstEntry();
    }

    abstract Iterator entryIterator();

    public Set entrySet()
    {
      Set localSet = this.entrySet;
      return localSet == null ? (this.entrySet = createEntrySet()) : localSet;
    }

    Set createEntrySet()
    {
      return new Multisets.EntrySet()
      {
        Multiset multiset()
        {
          return SortedMultisets.DescendingMultiset.this;
        }

        public Iterator iterator()
        {
          return SortedMultisets.DescendingMultiset.this.entryIterator();
        }

        public int size()
        {
          return SortedMultisets.DescendingMultiset.this.forwardMultiset().entrySet().size();
        }
      };
    }

    public Iterator iterator()
    {
      return Multisets.iteratorImpl(this);
    }

    public Object[] toArray()
    {
      return standardToArray();
    }

    public Object[] toArray(Object[] paramArrayOfObject)
    {
      return standardToArray(paramArrayOfObject);
    }

    public String toString()
    {
      return entrySet().toString();
    }
  }

  static abstract class ElementSet extends Multisets.ElementSet
    implements SortedSet
  {
    abstract SortedMultiset multiset();

    public Comparator comparator()
    {
      return multiset().comparator();
    }

    public SortedSet subSet(Object paramObject1, Object paramObject2)
    {
      return multiset().subMultiset(paramObject1, BoundType.CLOSED, paramObject2, BoundType.OPEN).elementSet();
    }

    public SortedSet headSet(Object paramObject)
    {
      return multiset().headMultiset(paramObject, BoundType.OPEN).elementSet();
    }

    public SortedSet tailSet(Object paramObject)
    {
      return multiset().tailMultiset(paramObject, BoundType.CLOSED).elementSet();
    }

    public Object first()
    {
      return SortedMultisets.getElementOrThrow(multiset().firstEntry());
    }

    public Object last()
    {
      return SortedMultisets.getElementOrThrow(multiset().lastEntry());
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.SortedMultisets
 * JD-Core Version:    0.6.2
 */