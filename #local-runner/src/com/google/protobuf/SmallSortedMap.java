package com.google.protobuf;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

class SmallSortedMap extends AbstractMap
{
  private final int maxArraySize;
  private List entryList;
  private Map overflowEntries;
  private boolean isImmutable;
  private volatile EntrySet lazyEntrySet;

  static SmallSortedMap newFieldMap(int paramInt)
  {
    // Byte code:
    //   0: new 4	com/google/protobuf/SmallSortedMap$1
    //   3: dup
    //   4: iload_0
    //   5: invokespecial 33	com/google/protobuf/SmallSortedMap$1:<init>	(I)V
    //   8: areturn
  }

  static SmallSortedMap newInstanceForTest(int paramInt)
  {
    return new SmallSortedMap(paramInt);
  }

  private SmallSortedMap(int paramInt)
  {
    this.maxArraySize = paramInt;
    this.entryList = Collections.emptyList();
    this.overflowEntries = Collections.emptyMap();
  }

  public void makeImmutable()
  {
    if (!this.isImmutable)
    {
      this.overflowEntries = (this.overflowEntries.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.overflowEntries));
      this.isImmutable = true;
    }
  }

  public boolean isImmutable()
  {
    return this.isImmutable;
  }

  public int getNumArrayEntries()
  {
    return this.entryList.size();
  }

  public Map.Entry getArrayEntryAt(int paramInt)
  {
    return (Map.Entry)this.entryList.get(paramInt);
  }

  public int getNumOverflowEntries()
  {
    return this.overflowEntries.size();
  }

  public Iterable getOverflowEntries()
  {
    return this.overflowEntries.isEmpty() ? EmptySet.iterable() : this.overflowEntries.entrySet();
  }

  public int size()
  {
    return this.entryList.size() + this.overflowEntries.size();
  }

  public boolean containsKey(Object paramObject)
  {
    Comparable localComparable = (Comparable)paramObject;
    return (binarySearchInArray(localComparable) >= 0) || (this.overflowEntries.containsKey(localComparable));
  }

  public Object get(Object paramObject)
  {
    Comparable localComparable = (Comparable)paramObject;
    int i = binarySearchInArray(localComparable);
    if (i >= 0)
      return ((Entry)this.entryList.get(i)).getValue();
    return this.overflowEntries.get(localComparable);
  }

  public Object put(Comparable paramComparable, Object paramObject)
  {
    checkMutable();
    int i = binarySearchInArray(paramComparable);
    if (i >= 0)
      return ((Entry)this.entryList.get(i)).setValue(paramObject);
    ensureEntryArrayMutable();
    int j = -(i + 1);
    if (j >= this.maxArraySize)
      return getOverflowEntriesMutable().put(paramComparable, paramObject);
    if (this.entryList.size() == this.maxArraySize)
    {
      Entry localEntry = (Entry)this.entryList.remove(this.maxArraySize - 1);
      getOverflowEntriesMutable().put(localEntry.getKey(), localEntry.getValue());
    }
    this.entryList.add(j, new Entry(paramComparable, paramObject));
    return null;
  }

  public void clear()
  {
    checkMutable();
    if (!this.entryList.isEmpty())
      this.entryList.clear();
    if (!this.overflowEntries.isEmpty())
      this.overflowEntries.clear();
  }

  public Object remove(Object paramObject)
  {
    checkMutable();
    Comparable localComparable = (Comparable)paramObject;
    int i = binarySearchInArray(localComparable);
    if (i >= 0)
      return removeArrayEntryAt(i);
    if (this.overflowEntries.isEmpty())
      return null;
    return this.overflowEntries.remove(localComparable);
  }

  private Object removeArrayEntryAt(int paramInt)
  {
    checkMutable();
    Object localObject = ((Entry)this.entryList.remove(paramInt)).getValue();
    if (!this.overflowEntries.isEmpty())
    {
      Iterator localIterator = getOverflowEntriesMutable().entrySet().iterator();
      this.entryList.add(new Entry((Map.Entry)localIterator.next()));
      localIterator.remove();
    }
    return localObject;
  }

  private int binarySearchInArray(Comparable paramComparable)
  {
    int i = 0;
    int j = this.entryList.size() - 1;
    int k;
    if (j >= 0)
    {
      k = paramComparable.compareTo(((Entry)this.entryList.get(j)).getKey());
      if (k > 0)
        return -(j + 2);
      if (k == 0)
        return j;
    }
    while (i <= j)
    {
      k = (i + j) / 2;
      int m = paramComparable.compareTo(((Entry)this.entryList.get(k)).getKey());
      if (m < 0)
        j = k - 1;
      else if (m > 0)
        i = k + 1;
      else
        return k;
    }
    return -(i + 1);
  }

  public Set entrySet()
  {
    if (this.lazyEntrySet == null)
      this.lazyEntrySet = new EntrySet(null);
    return this.lazyEntrySet;
  }

  private void checkMutable()
  {
    if (this.isImmutable)
      throw new UnsupportedOperationException();
  }

  private SortedMap getOverflowEntriesMutable()
  {
    checkMutable();
    if ((this.overflowEntries.isEmpty()) && (!(this.overflowEntries instanceof TreeMap)))
      this.overflowEntries = new TreeMap();
    return (SortedMap)this.overflowEntries;
  }

  private void ensureEntryArrayMutable()
  {
    checkMutable();
    if ((this.entryList.isEmpty()) && (!(this.entryList instanceof ArrayList)))
      this.entryList = new ArrayList(this.maxArraySize);
  }

  private static class EmptySet
  {
    private static final Iterator ITERATOR = new Iterator()
    {
      public boolean hasNext()
      {
        return false;
      }

      public Object next()
      {
        throw new NoSuchElementException();
      }

      public void remove()
      {
        throw new UnsupportedOperationException();
      }
    };
    private static final Iterable ITERABLE = new Iterable()
    {
      public Iterator iterator()
      {
        return SmallSortedMap.EmptySet.ITERATOR;
      }
    };

    static Iterable iterable()
    {
      return ITERABLE;
    }
  }

  private class EntryIterator
    implements Iterator
  {
    private int pos = -1;
    private boolean nextCalledBeforeRemove;
    private Iterator lazyOverflowIterator;

    private EntryIterator()
    {
    }

    public boolean hasNext()
    {
      return (this.pos + 1 < SmallSortedMap.this.entryList.size()) || (getOverflowIterator().hasNext());
    }

    public Map.Entry next()
    {
      this.nextCalledBeforeRemove = true;
      if (++this.pos < SmallSortedMap.this.entryList.size())
        return (Map.Entry)SmallSortedMap.this.entryList.get(this.pos);
      return (Map.Entry)getOverflowIterator().next();
    }

    public void remove()
    {
      if (!this.nextCalledBeforeRemove)
        throw new IllegalStateException("remove() was called before next()");
      this.nextCalledBeforeRemove = false;
      SmallSortedMap.this.checkMutable();
      if (this.pos < SmallSortedMap.this.entryList.size())
        SmallSortedMap.this.removeArrayEntryAt(this.pos--);
      else
        getOverflowIterator().remove();
    }

    private Iterator getOverflowIterator()
    {
      if (this.lazyOverflowIterator == null)
        this.lazyOverflowIterator = SmallSortedMap.this.overflowEntries.entrySet().iterator();
      return this.lazyOverflowIterator;
    }
  }

  private class EntrySet extends AbstractSet
  {
    private EntrySet()
    {
    }

    public Iterator iterator()
    {
      return new SmallSortedMap.EntryIterator(SmallSortedMap.this, null);
    }

    public int size()
    {
      return SmallSortedMap.this.size();
    }

    public boolean contains(Object paramObject)
    {
      Map.Entry localEntry = (Map.Entry)paramObject;
      Object localObject1 = SmallSortedMap.this.get(localEntry.getKey());
      Object localObject2 = localEntry.getValue();
      return (localObject1 == localObject2) || ((localObject1 != null) && (localObject1.equals(localObject2)));
    }

    public boolean add(Map.Entry paramEntry)
    {
      if (!contains(paramEntry))
      {
        SmallSortedMap.this.put((Comparable)paramEntry.getKey(), paramEntry.getValue());
        return true;
      }
      return false;
    }

    public boolean remove(Object paramObject)
    {
      Map.Entry localEntry = (Map.Entry)paramObject;
      if (contains(localEntry))
      {
        SmallSortedMap.this.remove(localEntry.getKey());
        return true;
      }
      return false;
    }

    public void clear()
    {
      SmallSortedMap.this.clear();
    }
  }

  private class Entry
    implements Comparable, Map.Entry
  {
    private final Comparable key;
    private Object value;

    Entry(Map.Entry arg2)
    {
      this((Map.Entry)localObject.getKey(), localObject.getValue());
    }

    Entry(Comparable paramObject, Object arg3)
    {
      this.key = paramObject;
      Object localObject;
      this.value = localObject;
    }

    public Comparable getKey()
    {
      return this.key;
    }

    public Object getValue()
    {
      return this.value;
    }

    public int compareTo(Entry paramEntry)
    {
      return getKey().compareTo(paramEntry.getKey());
    }

    public Object setValue(Object paramObject)
    {
      SmallSortedMap.this.checkMutable();
      Object localObject = this.value;
      this.value = paramObject;
      return localObject;
    }

    public boolean equals(Object paramObject)
    {
      if (paramObject == this)
        return true;
      if (!(paramObject instanceof Map.Entry))
        return false;
      Map.Entry localEntry = (Map.Entry)paramObject;
      return (equals(this.key, localEntry.getKey())) && (equals(this.value, localEntry.getValue()));
    }

    public int hashCode()
    {
      return (this.key == null ? 0 : this.key.hashCode()) ^ (this.value == null ? 0 : this.value.hashCode());
    }

    public String toString()
    {
      return this.key + "=" + this.value;
    }

    private boolean equals(Object paramObject1, Object paramObject2)
    {
      return paramObject1 == null ? false : paramObject2 == null ? true : paramObject1.equals(paramObject2);
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.protobuf.SmallSortedMap
 * JD-Core Version:    0.6.2
 */