package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

@GwtCompatible(serializable=true, emulated=true)
final class RegularImmutableSortedSet extends ImmutableSortedSet
{
  private final transient ImmutableList elements;

  RegularImmutableSortedSet(ImmutableList paramImmutableList, Comparator paramComparator)
  {
    super(paramComparator);
    this.elements = paramImmutableList;
    Preconditions.checkArgument(!paramImmutableList.isEmpty());
  }

  public UnmodifiableIterator iterator()
  {
    return this.elements.iterator();
  }

  public boolean isEmpty()
  {
    return false;
  }

  public int size()
  {
    return this.elements.size();
  }

  public boolean contains(Object paramObject)
  {
    if (paramObject == null)
      return false;
    try
    {
      return binarySearch(paramObject) >= 0;
    }
    catch (ClassCastException localClassCastException)
    {
    }
    return false;
  }

  public boolean containsAll(Collection paramCollection)
  {
    if ((!SortedIterables.hasSameComparator(comparator(), paramCollection)) || (paramCollection.size() <= 1))
      return super.containsAll(paramCollection);
    UnmodifiableIterator localUnmodifiableIterator = iterator();
    Iterator localIterator = paramCollection.iterator();
    Object localObject = localIterator.next();
    try
    {
      while (localUnmodifiableIterator.hasNext())
      {
        int i = unsafeCompare(localUnmodifiableIterator.next(), localObject);
        if (i == 0)
        {
          if (!localIterator.hasNext())
            return true;
          localObject = localIterator.next();
        }
        else if (i > 0)
        {
          return false;
        }
      }
    }
    catch (NullPointerException localNullPointerException)
    {
      return false;
    }
    catch (ClassCastException localClassCastException)
    {
      return false;
    }
    return false;
  }

  private int binarySearch(Object paramObject)
  {
    Comparator localComparator = this.comparator;
    return Collections.binarySearch(this.elements, paramObject, localComparator);
  }

  boolean isPartialView()
  {
    return this.elements.isPartialView();
  }

  public Object[] toArray()
  {
    return this.elements.toArray();
  }

  public Object[] toArray(Object[] paramArrayOfObject)
  {
    return this.elements.toArray(paramArrayOfObject);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == this)
      return true;
    if (!(paramObject instanceof Set))
      return false;
    Set localSet = (Set)paramObject;
    if (size() != localSet.size())
      return false;
    if (SortedIterables.hasSameComparator(this.comparator, localSet))
    {
      Iterator localIterator = localSet.iterator();
      try
      {
        UnmodifiableIterator localUnmodifiableIterator = iterator();
        while (localUnmodifiableIterator.hasNext())
        {
          Object localObject1 = localUnmodifiableIterator.next();
          Object localObject2 = localIterator.next();
          if ((localObject2 == null) || (unsafeCompare(localObject1, localObject2) != 0))
            return false;
        }
        return true;
      }
      catch (ClassCastException localClassCastException)
      {
        return false;
      }
      catch (NoSuchElementException localNoSuchElementException)
      {
        return false;
      }
    }
    return containsAll(localSet);
  }

  public Object first()
  {
    return this.elements.get(0);
  }

  public Object last()
  {
    return this.elements.get(size() - 1);
  }

  ImmutableSortedSet headSetImpl(Object paramObject, boolean paramBoolean)
  {
    int i;
    if (paramBoolean)
      i = SortedLists.binarySearch(this.elements, Preconditions.checkNotNull(paramObject), comparator(), SortedLists.KeyPresentBehavior.FIRST_AFTER, SortedLists.KeyAbsentBehavior.NEXT_HIGHER);
    else
      i = SortedLists.binarySearch(this.elements, Preconditions.checkNotNull(paramObject), comparator(), SortedLists.KeyPresentBehavior.FIRST_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_HIGHER);
    return createSubset(0, i);
  }

  ImmutableSortedSet subSetImpl(Object paramObject1, boolean paramBoolean1, Object paramObject2, boolean paramBoolean2)
  {
    return tailSetImpl(paramObject1, paramBoolean1).headSetImpl(paramObject2, paramBoolean2);
  }

  ImmutableSortedSet tailSetImpl(Object paramObject, boolean paramBoolean)
  {
    int i;
    if (paramBoolean)
      i = SortedLists.binarySearch(this.elements, Preconditions.checkNotNull(paramObject), comparator(), SortedLists.KeyPresentBehavior.FIRST_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_HIGHER);
    else
      i = SortedLists.binarySearch(this.elements, Preconditions.checkNotNull(paramObject), comparator(), SortedLists.KeyPresentBehavior.FIRST_AFTER, SortedLists.KeyAbsentBehavior.NEXT_HIGHER);
    return createSubset(i, size());
  }

  Comparator unsafeComparator()
  {
    return this.comparator;
  }

  private ImmutableSortedSet createSubset(int paramInt1, int paramInt2)
  {
    if ((paramInt1 == 0) && (paramInt2 == size()))
      return this;
    if (paramInt1 < paramInt2)
      return new RegularImmutableSortedSet(this.elements.subList(paramInt1, paramInt2), this.comparator);
    return emptySet(this.comparator);
  }

  int indexOf(Object paramObject)
  {
    if (paramObject == null)
      return -1;
    int i;
    try
    {
      i = SortedLists.binarySearch(this.elements, paramObject, comparator(), SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.INVERTED_INSERTION_INDEX);
    }
    catch (ClassCastException localClassCastException)
    {
      return -1;
    }
    return (i >= 0) && (this.elements.get(i).equals(paramObject)) ? i : -1;
  }

  ImmutableList createAsList()
  {
    return new ImmutableSortedAsList(this, this.elements);
  }

  ImmutableSortedSet createDescendingSet()
  {
    return new RegularImmutableSortedSet(this.elements.reverse(), Ordering.from(this.comparator).reverse());
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.RegularImmutableSortedSet
 * JD-Core Version:    0.6.2
 */