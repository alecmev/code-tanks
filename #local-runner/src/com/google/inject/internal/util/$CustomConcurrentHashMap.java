package com.google.inject.internal.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.ReentrantLock;

final class $CustomConcurrentHashMap
{
  private static int rehash(int paramInt)
  {
    paramInt += (paramInt << 15 ^ 0xFFFFCD7D);
    paramInt ^= paramInt >>> 10;
    paramInt += (paramInt << 3);
    paramInt ^= paramInt >>> 6;
    paramInt += (paramInt << 2) + (paramInt << 14);
    return paramInt ^ paramInt >>> 16;
  }

  static class SimpleInternalEntry
  {
    final Object key;
    final int hash;
    final SimpleInternalEntry next;
    volatile Object value;

    SimpleInternalEntry(Object paramObject1, int paramInt, @$Nullable Object paramObject2, SimpleInternalEntry paramSimpleInternalEntry)
    {
      this.key = paramObject1;
      this.hash = paramInt;
      this.value = paramObject2;
      this.next = paramSimpleInternalEntry;
    }
  }

  static class SimpleStrategy
    implements .CustomConcurrentHashMap.Strategy
  {
    public .CustomConcurrentHashMap.SimpleInternalEntry newEntry(Object paramObject, int paramInt, .CustomConcurrentHashMap.SimpleInternalEntry paramSimpleInternalEntry)
    {
      return new $CustomConcurrentHashMap.SimpleInternalEntry(paramObject, paramInt, null, paramSimpleInternalEntry);
    }

    public .CustomConcurrentHashMap.SimpleInternalEntry copyEntry(Object paramObject, .CustomConcurrentHashMap.SimpleInternalEntry paramSimpleInternalEntry1, .CustomConcurrentHashMap.SimpleInternalEntry paramSimpleInternalEntry2)
    {
      return new $CustomConcurrentHashMap.SimpleInternalEntry(paramObject, paramSimpleInternalEntry1.hash, paramSimpleInternalEntry1.value, paramSimpleInternalEntry2);
    }

    public void setValue($CustomConcurrentHashMap.SimpleInternalEntry paramSimpleInternalEntry, Object paramObject)
    {
      paramSimpleInternalEntry.value = paramObject;
    }

    public Object getValue($CustomConcurrentHashMap.SimpleInternalEntry paramSimpleInternalEntry)
    {
      return paramSimpleInternalEntry.value;
    }

    public boolean equalKeys(Object paramObject1, Object paramObject2)
    {
      return paramObject1.equals(paramObject2);
    }

    public boolean equalValues(Object paramObject1, Object paramObject2)
    {
      return paramObject1.equals(paramObject2);
    }

    public int hashKey(Object paramObject)
    {
      return paramObject.hashCode();
    }

    public Object getKey($CustomConcurrentHashMap.SimpleInternalEntry paramSimpleInternalEntry)
    {
      return paramSimpleInternalEntry.key;
    }

    public .CustomConcurrentHashMap.SimpleInternalEntry getNext($CustomConcurrentHashMap.SimpleInternalEntry paramSimpleInternalEntry)
    {
      return paramSimpleInternalEntry.next;
    }

    public int getHash($CustomConcurrentHashMap.SimpleInternalEntry paramSimpleInternalEntry)
    {
      return paramSimpleInternalEntry.hash;
    }

    public void setInternals($CustomConcurrentHashMap.Internals paramInternals)
    {
    }
  }

  static class ComputingImpl extends $CustomConcurrentHashMap.Impl
  {
    static final long serialVersionUID = 0L;
    final .CustomConcurrentHashMap.ComputingStrategy computingStrategy;
    final .Function computer;

    ComputingImpl($CustomConcurrentHashMap.ComputingStrategy paramComputingStrategy, .CustomConcurrentHashMap.Builder paramBuilder, .Function paramFunction)
    {
      super(paramBuilder);
      this.computingStrategy = paramComputingStrategy;
      this.computer = paramFunction;
    }

    public Object get(Object paramObject)
    {
      Object localObject1 = paramObject;
      if (localObject1 == null)
        throw new NullPointerException("key");
      int i = hash(localObject1);
      $CustomConcurrentHashMap.Impl.Segment localSegment = segmentFor(i);
      while (true)
      {
        Object localObject2 = localSegment.getEntry(localObject1, i);
        Object localObject4;
        if (localObject2 == null)
        {
          j = 0;
          localSegment.lock();
          int k;
          try
          {
            localObject2 = localSegment.getEntry(localObject1, i);
            if (localObject2 == null)
            {
              j = 1;
              k = localSegment.count;
              if (k++ > localSegment.threshold)
                localSegment.expand();
              localObject4 = localSegment.table;
              int m = i & ((AtomicReferenceArray)localObject4).length() - 1;
              Object localObject6 = ((AtomicReferenceArray)localObject4).get(m);
              localSegment.modCount += 1;
              localObject2 = this.computingStrategy.newEntry(localObject1, i, localObject6);
              ((AtomicReferenceArray)localObject4).set(m, localObject2);
              localSegment.count = k;
            }
          }
          finally
          {
            localSegment.unlock();
          }
          if (j != 0)
          {
            k = 0;
            try
            {
              localObject4 = this.computingStrategy.compute(localObject1, localObject2, this.computer);
              if (localObject4 == null)
                throw new NullPointerException("compute() returned null unexpectedly");
              k = 1;
              Object localObject5 = localObject4;
              return localObject5;
            }
            finally
            {
              if (k == 0)
                localSegment.removeEntry(localObject2, i);
            }
          }
        }
        int j = 0;
        try
        {
          Object localObject3 = this.computingStrategy.waitForValue(localObject2);
          if (localObject3 == null)
          {
            localSegment.removeEntry(localObject2, i);
            if (j == 0)
              continue;
            Thread.currentThread().interrupt();
            continue;
          }
          localObject4 = localObject3;
          return localObject4;
        }
        catch (InterruptedException localInterruptedException)
        {
          while (true)
            j = 1;
        }
        finally
        {
          if (j != 0)
            Thread.currentThread().interrupt();
        }
      }
    }
  }

  static class Impl extends AbstractMap
    implements Serializable, ConcurrentMap
  {
    static final int MAXIMUM_CAPACITY = 1073741824;
    static final int MAX_SEGMENTS = 65536;
    static final int RETRIES_BEFORE_LOCK = 2;
    final .CustomConcurrentHashMap.Strategy strategy;
    final int segmentMask;
    final int segmentShift;
    final Segment[] segments;
    final float loadFactor;
    Set keySet;
    Collection values;
    Set entrySet;
    private static final long serialVersionUID = 0L;

    Impl($CustomConcurrentHashMap.Strategy paramStrategy, .CustomConcurrentHashMap.Builder paramBuilder)
    {
      this.loadFactor = paramBuilder.loadFactor;
      int i = paramBuilder.concurrencyLevel;
      int j = paramBuilder.initialCapacity;
      if (i > 65536)
        i = 65536;
      int k = 0;
      int m = 1;
      while (m < i)
      {
        k++;
        m <<= 1;
      }
      this.segmentShift = (32 - k);
      this.segmentMask = (m - 1);
      this.segments = newSegmentArray(m);
      if (j > 1073741824)
        j = 1073741824;
      int n = j / m;
      if (n * m < j)
        n++;
      int i1 = 1;
      while (i1 < n)
        i1 <<= 1;
      for (int i2 = 0; i2 < this.segments.length; i2++)
        this.segments[i2] = new Segment(i1);
      this.strategy = paramStrategy;
      paramStrategy.setInternals(new InternalsImpl());
    }

    int hash(Object paramObject)
    {
      int i = this.strategy.hashKey(paramObject);
      return .CustomConcurrentHashMap.rehash(i);
    }

    Segment[] newSegmentArray(int paramInt)
    {
      return (Segment[])Array.newInstance(Segment.class, paramInt);
    }

    Segment segmentFor(int paramInt)
    {
      return this.segments[(paramInt >>> this.segmentShift & this.segmentMask)];
    }

    public boolean isEmpty()
    {
      Segment[] arrayOfSegment = this.segments;
      int[] arrayOfInt = new int[arrayOfSegment.length];
      int i = 0;
      for (int j = 0; j < arrayOfSegment.length; j++)
      {
        if (arrayOfSegment[j].count != 0)
          return false;
        i += (arrayOfInt[j] = arrayOfSegment[j].modCount);
      }
      if (i != 0)
        for (j = 0; j < arrayOfSegment.length; j++)
          if ((arrayOfSegment[j].count != 0) || (arrayOfInt[j] != arrayOfSegment[j].modCount))
            return false;
      return true;
    }

    public int size()
    {
      Segment[] arrayOfSegment1 = this.segments;
      long l1 = 0L;
      long l2 = 0L;
      int[] arrayOfInt = new int[arrayOfSegment1.length];
      int j;
      int k;
      for (int i = 0; i < 2; i++)
      {
        l2 = 0L;
        l1 = 0L;
        j = 0;
        for (k = 0; k < arrayOfSegment1.length; k++)
        {
          l1 += arrayOfSegment1[k].count;
          j += (arrayOfInt[k] = arrayOfSegment1[k].modCount);
        }
        if (j != 0)
          for (k = 0; k < arrayOfSegment1.length; k++)
          {
            l2 += arrayOfSegment1[k].count;
            if (arrayOfInt[k] != arrayOfSegment1[k].modCount)
            {
              l2 = -1L;
              break;
            }
          }
        if (l2 == l1)
          break;
      }
      if (l2 != l1)
      {
        l1 = 0L;
        Segment localSegment;
        for (localSegment : arrayOfSegment1)
          localSegment.lock();
        for (localSegment : arrayOfSegment1)
          l1 += localSegment.count;
        for (localSegment : arrayOfSegment1)
          localSegment.unlock();
      }
      if (l1 > 2147483647L)
        return 2147483647;
      return (int)l1;
    }

    public Object get(Object paramObject)
    {
      if (paramObject == null)
        throw new NullPointerException("key");
      int i = hash(paramObject);
      return segmentFor(i).get(paramObject, i);
    }

    public boolean containsKey(Object paramObject)
    {
      if (paramObject == null)
        throw new NullPointerException("key");
      int i = hash(paramObject);
      return segmentFor(i).containsKey(paramObject, i);
    }

    public boolean containsValue(Object paramObject)
    {
      if (paramObject == null)
        throw new NullPointerException("value");
      Segment[] arrayOfSegment1 = this.segments;
      int[] arrayOfInt = new int[arrayOfSegment1.length];
      int j;
      int k;
      for (int i = 0; i < 2; i++)
      {
        j = 0;
        int m;
        for (k = 0; k < arrayOfSegment1.length; k++)
        {
          m = arrayOfSegment1[k].count;
          j += (arrayOfInt[k] = arrayOfSegment1[k].modCount);
          if (arrayOfSegment1[k].containsValue(paramObject))
            return true;
        }
        k = 1;
        if (j != 0)
          for (m = 0; m < arrayOfSegment1.length; m++)
          {
            int i1 = arrayOfSegment1[m].count;
            if (arrayOfInt[m] != arrayOfSegment1[m].modCount)
            {
              k = 0;
              break;
            }
          }
        if (k != 0)
          return false;
      }
      for (Segment localSegment1 : arrayOfSegment1)
        localSegment1.lock();
      boolean bool = false;
      try
      {
        for (localSegment2 : arrayOfSegment1)
          if (localSegment2.containsValue(paramObject))
          {
            bool = true;
            break;
          }
      }
      finally
      {
        Segment localSegment2;
        for (Segment localSegment3 : arrayOfSegment1)
          localSegment3.unlock();
      }
      return bool;
    }

    public Object put(Object paramObject1, Object paramObject2)
    {
      if (paramObject1 == null)
        throw new NullPointerException("key");
      if (paramObject2 == null)
        throw new NullPointerException("value");
      int i = hash(paramObject1);
      return segmentFor(i).put(paramObject1, i, paramObject2, false);
    }

    public Object putIfAbsent(Object paramObject1, Object paramObject2)
    {
      if (paramObject1 == null)
        throw new NullPointerException("key");
      if (paramObject2 == null)
        throw new NullPointerException("value");
      int i = hash(paramObject1);
      return segmentFor(i).put(paramObject1, i, paramObject2, true);
    }

    public void putAll(Map paramMap)
    {
      Iterator localIterator = paramMap.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        put(localEntry.getKey(), localEntry.getValue());
      }
    }

    public Object remove(Object paramObject)
    {
      if (paramObject == null)
        throw new NullPointerException("key");
      int i = hash(paramObject);
      return segmentFor(i).remove(paramObject, i);
    }

    public boolean remove(Object paramObject1, Object paramObject2)
    {
      if (paramObject1 == null)
        throw new NullPointerException("key");
      int i = hash(paramObject1);
      return segmentFor(i).remove(paramObject1, i, paramObject2);
    }

    public boolean replace(Object paramObject1, Object paramObject2, Object paramObject3)
    {
      if (paramObject1 == null)
        throw new NullPointerException("key");
      if (paramObject2 == null)
        throw new NullPointerException("oldValue");
      if (paramObject3 == null)
        throw new NullPointerException("newValue");
      int i = hash(paramObject1);
      return segmentFor(i).replace(paramObject1, i, paramObject2, paramObject3);
    }

    public Object replace(Object paramObject1, Object paramObject2)
    {
      if (paramObject1 == null)
        throw new NullPointerException("key");
      if (paramObject2 == null)
        throw new NullPointerException("value");
      int i = hash(paramObject1);
      return segmentFor(i).replace(paramObject1, i, paramObject2);
    }

    public void clear()
    {
      for (Segment localSegment : this.segments)
        localSegment.clear();
    }

    public Set keySet()
    {
      Set localSet = this.keySet;
      return this.keySet = new KeySet();
    }

    public Collection values()
    {
      Collection localCollection = this.values;
      return this.values = new Values();
    }

    public Set entrySet()
    {
      Set localSet = this.entrySet;
      return this.entrySet = new EntrySet();
    }

    private void writeObject(ObjectOutputStream paramObjectOutputStream)
      throws IOException
    {
      paramObjectOutputStream.writeInt(size());
      paramObjectOutputStream.writeFloat(this.loadFactor);
      paramObjectOutputStream.writeInt(this.segments.length);
      paramObjectOutputStream.writeObject(this.strategy);
      Iterator localIterator = entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        paramObjectOutputStream.writeObject(localEntry.getKey());
        paramObjectOutputStream.writeObject(localEntry.getValue());
      }
      paramObjectOutputStream.writeObject(null);
    }

    private void readObject(ObjectInputStream paramObjectInputStream)
      throws IOException, ClassNotFoundException
    {
      try
      {
        int i = paramObjectInputStream.readInt();
        float f = paramObjectInputStream.readFloat();
        int j = paramObjectInputStream.readInt();
        $CustomConcurrentHashMap.Strategy localStrategy = ($CustomConcurrentHashMap.Strategy)paramObjectInputStream.readObject();
        Fields.loadFactor.set(this, Float.valueOf(f));
        if (j > 65536)
          j = 65536;
        int k = 0;
        int m = 1;
        while (m < j)
        {
          k++;
          m <<= 1;
        }
        Fields.segmentShift.set(this, Integer.valueOf(32 - k));
        Fields.segmentMask.set(this, Integer.valueOf(m - 1));
        Fields.segments.set(this, newSegmentArray(m));
        if (i > 1073741824)
          i = 1073741824;
        int n = i / m;
        if (n * m < i)
          n++;
        int i1 = 1;
        while (i1 < n)
          i1 <<= 1;
        for (int i2 = 0; i2 < this.segments.length; i2++)
          this.segments[i2] = new Segment(i1);
        Fields.strategy.set(this, localStrategy);
        while (true)
        {
          Object localObject1 = paramObjectInputStream.readObject();
          if (localObject1 == null)
            break;
          Object localObject2 = paramObjectInputStream.readObject();
          put(localObject1, localObject2);
        }
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        throw new AssertionError(localIllegalAccessException);
      }
    }

    static class Fields
    {
      static final Field loadFactor = findField("loadFactor");
      static final Field segmentShift = findField("segmentShift");
      static final Field segmentMask = findField("segmentMask");
      static final Field segments = findField("segments");
      static final Field strategy = findField("strategy");

      static Field findField(String paramString)
      {
        try
        {
          Field localField = $CustomConcurrentHashMap.Impl.class.getDeclaredField(paramString);
          localField.setAccessible(true);
          return localField;
        }
        catch (NoSuchFieldException localNoSuchFieldException)
        {
          throw new AssertionError(localNoSuchFieldException);
        }
      }
    }

    final class EntrySet extends AbstractSet
    {
      EntrySet()
      {
      }

      public Iterator iterator()
      {
        return new $CustomConcurrentHashMap.Impl.EntryIterator($CustomConcurrentHashMap.Impl.this);
      }

      public boolean contains(Object paramObject)
      {
        if (!(paramObject instanceof Map.Entry))
          return false;
        Map.Entry localEntry = (Map.Entry)paramObject;
        Object localObject1 = localEntry.getKey();
        if (localObject1 == null)
          return false;
        Object localObject2 = $CustomConcurrentHashMap.Impl.this.get(localObject1);
        return (localObject2 != null) && ($CustomConcurrentHashMap.Impl.this.strategy.equalValues(localObject2, localEntry.getValue()));
      }

      public boolean remove(Object paramObject)
      {
        if (!(paramObject instanceof Map.Entry))
          return false;
        Map.Entry localEntry = (Map.Entry)paramObject;
        Object localObject = localEntry.getKey();
        return (localObject != null) && ($CustomConcurrentHashMap.Impl.this.remove(localObject, localEntry.getValue()));
      }

      public int size()
      {
        return .CustomConcurrentHashMap.Impl.this.size();
      }

      public boolean isEmpty()
      {
        return .CustomConcurrentHashMap.Impl.this.isEmpty();
      }

      public void clear()
      {
        $CustomConcurrentHashMap.Impl.this.clear();
      }
    }

    final class Values extends AbstractCollection
    {
      Values()
      {
      }

      public Iterator iterator()
      {
        return new $CustomConcurrentHashMap.Impl.ValueIterator($CustomConcurrentHashMap.Impl.this);
      }

      public int size()
      {
        return .CustomConcurrentHashMap.Impl.this.size();
      }

      public boolean isEmpty()
      {
        return .CustomConcurrentHashMap.Impl.this.isEmpty();
      }

      public boolean contains(Object paramObject)
      {
        return .CustomConcurrentHashMap.Impl.this.containsValue(paramObject);
      }

      public void clear()
      {
        $CustomConcurrentHashMap.Impl.this.clear();
      }
    }

    final class KeySet extends AbstractSet
    {
      KeySet()
      {
      }

      public Iterator iterator()
      {
        return new $CustomConcurrentHashMap.Impl.KeyIterator($CustomConcurrentHashMap.Impl.this);
      }

      public int size()
      {
        return .CustomConcurrentHashMap.Impl.this.size();
      }

      public boolean isEmpty()
      {
        return .CustomConcurrentHashMap.Impl.this.isEmpty();
      }

      public boolean contains(Object paramObject)
      {
        return .CustomConcurrentHashMap.Impl.this.containsKey(paramObject);
      }

      public boolean remove(Object paramObject)
      {
        return .CustomConcurrentHashMap.Impl.this.remove(paramObject) != null;
      }

      public void clear()
      {
        $CustomConcurrentHashMap.Impl.this.clear();
      }
    }

    final class EntryIterator extends $CustomConcurrentHashMap.Impl.HashIterator
      implements Iterator
    {
      EntryIterator()
      {
        super();
      }

      public Map.Entry next()
      {
        return nextEntry();
      }
    }

    final class WriteThroughEntry extends $AbstractMapEntry
    {
      final Object key;
      Object value;

      WriteThroughEntry(Object paramObject1, Object arg3)
      {
        this.key = paramObject1;
        Object localObject;
        this.value = localObject;
      }

      public Object getKey()
      {
        return this.key;
      }

      public Object getValue()
      {
        return this.value;
      }

      public Object setValue(Object paramObject)
      {
        if (paramObject == null)
          throw new NullPointerException();
        Object localObject = $CustomConcurrentHashMap.Impl.this.put(getKey(), paramObject);
        this.value = paramObject;
        return localObject;
      }
    }

    final class ValueIterator extends $CustomConcurrentHashMap.Impl.HashIterator
      implements Iterator
    {
      ValueIterator()
      {
        super();
      }

      public Object next()
      {
        return super.nextEntry().getValue();
      }
    }

    final class KeyIterator extends $CustomConcurrentHashMap.Impl.HashIterator
      implements Iterator
    {
      KeyIterator()
      {
        super();
      }

      public Object next()
      {
        return super.nextEntry().getKey();
      }
    }

    abstract class HashIterator
    {
      int nextSegmentIndex = $CustomConcurrentHashMap.Impl.this.segments.length - 1;
      int nextTableIndex = -1;
      AtomicReferenceArray currentTable;
      Object nextEntry;
      $CustomConcurrentHashMap.Impl.WriteThroughEntry nextExternal;
      $CustomConcurrentHashMap.Impl.WriteThroughEntry lastReturned;

      HashIterator()
      {
        advance();
      }

      public boolean hasMoreElements()
      {
        return hasNext();
      }

      final void advance()
      {
        this.nextExternal = null;
        if (nextInChain())
          return;
        if (nextInTable())
          return;
        while (this.nextSegmentIndex >= 0)
        {
          $CustomConcurrentHashMap.Impl.Segment localSegment = $CustomConcurrentHashMap.Impl.this.segments[(this.nextSegmentIndex--)];
          if (localSegment.count != 0)
          {
            this.currentTable = localSegment.table;
            this.nextTableIndex = (this.currentTable.length() - 1);
            if (nextInTable())
              return;
          }
        }
      }

      boolean nextInChain()
      {
        $CustomConcurrentHashMap.Strategy localStrategy = $CustomConcurrentHashMap.Impl.this.strategy;
        if (this.nextEntry != null)
          for (this.nextEntry = localStrategy.getNext(this.nextEntry); this.nextEntry != null; this.nextEntry = localStrategy.getNext(this.nextEntry))
            if (advanceTo(this.nextEntry))
              return true;
        return false;
      }

      boolean nextInTable()
      {
        while (this.nextTableIndex >= 0)
          if (((this.nextEntry = this.currentTable.get(this.nextTableIndex--)) != null) && ((advanceTo(this.nextEntry)) || (nextInChain())))
            return true;
        return false;
      }

      boolean advanceTo(Object paramObject)
      {
        $CustomConcurrentHashMap.Strategy localStrategy = $CustomConcurrentHashMap.Impl.this.strategy;
        Object localObject1 = localStrategy.getKey(paramObject);
        Object localObject2 = localStrategy.getValue(paramObject);
        if ((localObject1 != null) && (localObject2 != null))
        {
          this.nextExternal = new $CustomConcurrentHashMap.Impl.WriteThroughEntry($CustomConcurrentHashMap.Impl.this, localObject1, localObject2);
          return true;
        }
        return false;
      }

      public boolean hasNext()
      {
        return this.nextExternal != null;
      }

      $CustomConcurrentHashMap.Impl.WriteThroughEntry nextEntry()
      {
        if (this.nextExternal == null)
          throw new NoSuchElementException();
        this.lastReturned = this.nextExternal;
        advance();
        return this.lastReturned;
      }

      public void remove()
      {
        if (this.lastReturned == null)
          throw new IllegalStateException();
        $CustomConcurrentHashMap.Impl.this.remove(this.lastReturned.getKey());
        this.lastReturned = null;
      }
    }

    final class Segment extends ReentrantLock
    {
      volatile int count;
      int modCount;
      int threshold;
      volatile AtomicReferenceArray table;

      Segment(int arg2)
      {
        int i;
        setTable(newEntryArray(i));
      }

      AtomicReferenceArray newEntryArray(int paramInt)
      {
        return new AtomicReferenceArray(paramInt);
      }

      void setTable(AtomicReferenceArray paramAtomicReferenceArray)
      {
        this.threshold = ((int)(paramAtomicReferenceArray.length() * .CustomConcurrentHashMap.Impl.this.loadFactor));
        this.table = paramAtomicReferenceArray;
      }

      Object getFirst(int paramInt)
      {
        AtomicReferenceArray localAtomicReferenceArray = this.table;
        return localAtomicReferenceArray.get(paramInt & localAtomicReferenceArray.length() - 1);
      }

      public Object getEntry(Object paramObject, int paramInt)
      {
        $CustomConcurrentHashMap.Strategy localStrategy = $CustomConcurrentHashMap.Impl.this.strategy;
        if (this.count != 0)
          for (Object localObject1 = getFirst(paramInt); localObject1 != null; localObject1 = localStrategy.getNext(localObject1))
            if (localStrategy.getHash(localObject1) == paramInt)
            {
              Object localObject2 = localStrategy.getKey(localObject1);
              if ((localObject2 != null) && (localStrategy.equalKeys(localObject2, paramObject)))
                return localObject1;
            }
        return null;
      }

      Object get(Object paramObject, int paramInt)
      {
        Object localObject = getEntry(paramObject, paramInt);
        if (localObject == null)
          return null;
        return .CustomConcurrentHashMap.Impl.this.strategy.getValue(localObject);
      }

      boolean containsKey(Object paramObject, int paramInt)
      {
        $CustomConcurrentHashMap.Strategy localStrategy = $CustomConcurrentHashMap.Impl.this.strategy;
        if (this.count != 0)
          for (Object localObject1 = getFirst(paramInt); localObject1 != null; localObject1 = localStrategy.getNext(localObject1))
            if (localStrategy.getHash(localObject1) == paramInt)
            {
              Object localObject2 = localStrategy.getKey(localObject1);
              if ((localObject2 != null) && (localStrategy.equalKeys(localObject2, paramObject)))
                return localStrategy.getValue(localObject1) != null;
            }
        return false;
      }

      boolean containsValue(Object paramObject)
      {
        $CustomConcurrentHashMap.Strategy localStrategy = $CustomConcurrentHashMap.Impl.this.strategy;
        if (this.count != 0)
        {
          AtomicReferenceArray localAtomicReferenceArray = this.table;
          int i = localAtomicReferenceArray.length();
          for (int j = 0; j < i; j++)
            for (Object localObject1 = localAtomicReferenceArray.get(j); localObject1 != null; localObject1 = localStrategy.getNext(localObject1))
            {
              Object localObject2 = localStrategy.getValue(localObject1);
              if ((localObject2 != null) && (localStrategy.equalValues(localObject2, paramObject)))
                return true;
            }
        }
        return false;
      }

      boolean replace(Object paramObject1, int paramInt, Object paramObject2, Object paramObject3)
      {
        $CustomConcurrentHashMap.Strategy localStrategy = $CustomConcurrentHashMap.Impl.this.strategy;
        lock();
        try
        {
          for (Object localObject1 = getFirst(paramInt); localObject1 != null; localObject1 = localStrategy.getNext(localObject1))
          {
            Object localObject2 = localStrategy.getKey(localObject1);
            if ((localStrategy.getHash(localObject1) == paramInt) && (localObject2 != null) && (localStrategy.equalKeys(paramObject1, localObject2)))
            {
              Object localObject3 = localStrategy.getValue(localObject1);
              boolean bool2;
              if (localObject3 == null)
              {
                bool2 = false;
                return bool2;
              }
              if (localStrategy.equalValues(localObject3, paramObject2))
              {
                localStrategy.setValue(localObject1, paramObject3);
                bool2 = true;
                return bool2;
              }
            }
          }
          boolean bool1 = false;
          return bool1;
        }
        finally
        {
          unlock();
        }
      }

      Object replace(Object paramObject1, int paramInt, Object paramObject2)
      {
        $CustomConcurrentHashMap.Strategy localStrategy = $CustomConcurrentHashMap.Impl.this.strategy;
        lock();
        try
        {
          for (Object localObject1 = getFirst(paramInt); localObject1 != null; localObject1 = localStrategy.getNext(localObject1))
          {
            Object localObject2 = localStrategy.getKey(localObject1);
            if ((localStrategy.getHash(localObject1) == paramInt) && (localObject2 != null) && (localStrategy.equalKeys(paramObject1, localObject2)))
            {
              Object localObject3 = localStrategy.getValue(localObject1);
              if (localObject3 == null)
              {
                localObject4 = null;
                return localObject4;
              }
              localStrategy.setValue(localObject1, paramObject2);
              Object localObject4 = localObject3;
              return localObject4;
            }
          }
          localObject1 = null;
          return localObject1;
        }
        finally
        {
          unlock();
        }
      }

      Object put(Object paramObject1, int paramInt, Object paramObject2, boolean paramBoolean)
      {
        $CustomConcurrentHashMap.Strategy localStrategy = $CustomConcurrentHashMap.Impl.this.strategy;
        lock();
        try
        {
          int i = this.count;
          if (i++ > this.threshold)
            expand();
          AtomicReferenceArray localAtomicReferenceArray = this.table;
          int j = paramInt & localAtomicReferenceArray.length() - 1;
          Object localObject1 = localAtomicReferenceArray.get(j);
          for (Object localObject2 = localObject1; localObject2 != null; localObject2 = localStrategy.getNext(localObject2))
          {
            localObject3 = localStrategy.getKey(localObject2);
            if ((localStrategy.getHash(localObject2) == paramInt) && (localObject3 != null) && (localStrategy.equalKeys(paramObject1, localObject3)))
            {
              Object localObject4 = localStrategy.getValue(localObject2);
              if ((paramBoolean) && (localObject4 != null))
              {
                localObject5 = localObject4;
                return localObject5;
              }
              localStrategy.setValue(localObject2, paramObject2);
              Object localObject5 = localObject4;
              return localObject5;
            }
          }
          this.modCount += 1;
          localObject2 = localStrategy.newEntry(paramObject1, paramInt, localObject1);
          localStrategy.setValue(localObject2, paramObject2);
          localAtomicReferenceArray.set(j, localObject2);
          this.count = i;
          Object localObject3 = null;
          return localObject3;
        }
        finally
        {
          unlock();
        }
      }

      void expand()
      {
        AtomicReferenceArray localAtomicReferenceArray1 = this.table;
        int i = localAtomicReferenceArray1.length();
        if (i >= 1073741824)
          return;
        $CustomConcurrentHashMap.Strategy localStrategy = $CustomConcurrentHashMap.Impl.this.strategy;
        AtomicReferenceArray localAtomicReferenceArray2 = newEntryArray(i << 1);
        this.threshold = ((int)(localAtomicReferenceArray2.length() * .CustomConcurrentHashMap.Impl.this.loadFactor));
        int j = localAtomicReferenceArray2.length() - 1;
        for (int k = 0; k < i; k++)
        {
          Object localObject1 = localAtomicReferenceArray1.get(k);
          if (localObject1 != null)
          {
            Object localObject2 = localStrategy.getNext(localObject1);
            int m = localStrategy.getHash(localObject1) & j;
            if (localObject2 == null)
            {
              localAtomicReferenceArray2.set(m, localObject1);
            }
            else
            {
              Object localObject3 = localObject1;
              int n = m;
              for (Object localObject4 = localObject2; localObject4 != null; localObject4 = localStrategy.getNext(localObject4))
              {
                int i1 = localStrategy.getHash(localObject4) & j;
                if (i1 != n)
                {
                  n = i1;
                  localObject3 = localObject4;
                }
              }
              localAtomicReferenceArray2.set(n, localObject3);
              for (localObject4 = localObject1; localObject4 != localObject3; localObject4 = localStrategy.getNext(localObject4))
              {
                Object localObject5 = localStrategy.getKey(localObject4);
                if (localObject5 != null)
                {
                  int i2 = localStrategy.getHash(localObject4) & j;
                  Object localObject6 = localAtomicReferenceArray2.get(i2);
                  localAtomicReferenceArray2.set(i2, localStrategy.copyEntry(localObject5, localObject4, localObject6));
                }
              }
            }
          }
        }
        this.table = localAtomicReferenceArray2;
      }

      Object remove(Object paramObject, int paramInt)
      {
        $CustomConcurrentHashMap.Strategy localStrategy = $CustomConcurrentHashMap.Impl.this.strategy;
        lock();
        try
        {
          int i = this.count - 1;
          AtomicReferenceArray localAtomicReferenceArray = this.table;
          int j = paramInt & localAtomicReferenceArray.length() - 1;
          Object localObject1 = localAtomicReferenceArray.get(j);
          for (Object localObject2 = localObject1; localObject2 != null; localObject2 = localStrategy.getNext(localObject2))
          {
            Object localObject3 = localStrategy.getKey(localObject2);
            if ((localStrategy.getHash(localObject2) == paramInt) && (localObject3 != null) && (localStrategy.equalKeys(localObject3, paramObject)))
            {
              Object localObject4 = $CustomConcurrentHashMap.Impl.this.strategy.getValue(localObject2);
              this.modCount += 1;
              Object localObject5 = localStrategy.getNext(localObject2);
              for (Object localObject6 = localObject1; localObject6 != localObject2; localObject6 = localStrategy.getNext(localObject6))
              {
                Object localObject7 = localStrategy.getKey(localObject6);
                if (localObject7 != null)
                  localObject5 = localStrategy.copyEntry(localObject7, localObject6, localObject5);
              }
              localAtomicReferenceArray.set(j, localObject5);
              this.count = i;
              localObject6 = localObject4;
              return localObject6;
            }
          }
          localObject2 = null;
          return localObject2;
        }
        finally
        {
          unlock();
        }
      }

      boolean remove(Object paramObject1, int paramInt, Object paramObject2)
      {
        $CustomConcurrentHashMap.Strategy localStrategy = $CustomConcurrentHashMap.Impl.this.strategy;
        lock();
        try
        {
          int i = this.count - 1;
          AtomicReferenceArray localAtomicReferenceArray = this.table;
          int j = paramInt & localAtomicReferenceArray.length() - 1;
          Object localObject1 = localAtomicReferenceArray.get(j);
          for (Object localObject2 = localObject1; localObject2 != null; localObject2 = localStrategy.getNext(localObject2))
          {
            Object localObject3 = localStrategy.getKey(localObject2);
            if ((localStrategy.getHash(localObject2) == paramInt) && (localObject3 != null) && (localStrategy.equalKeys(localObject3, paramObject1)))
            {
              Object localObject4 = $CustomConcurrentHashMap.Impl.this.strategy.getValue(localObject2);
              if ((paramObject2 == localObject4) || ((paramObject2 != null) && (localObject4 != null) && (localStrategy.equalValues(localObject4, paramObject2))))
              {
                this.modCount += 1;
                Object localObject5 = localStrategy.getNext(localObject2);
                for (Object localObject6 = localObject1; localObject6 != localObject2; localObject6 = localStrategy.getNext(localObject6))
                {
                  Object localObject7 = localStrategy.getKey(localObject6);
                  if (localObject7 != null)
                    localObject5 = localStrategy.copyEntry(localObject7, localObject6, localObject5);
                }
                localAtomicReferenceArray.set(j, localObject5);
                this.count = i;
                boolean bool3 = true;
                return bool3;
              }
              boolean bool2 = false;
              return bool2;
            }
          }
          boolean bool1 = false;
          return bool1;
        }
        finally
        {
          unlock();
        }
      }

      public boolean removeEntry(Object paramObject1, int paramInt, Object paramObject2)
      {
        $CustomConcurrentHashMap.Strategy localStrategy = $CustomConcurrentHashMap.Impl.this.strategy;
        lock();
        try
        {
          int i = this.count - 1;
          AtomicReferenceArray localAtomicReferenceArray = this.table;
          int j = paramInt & localAtomicReferenceArray.length() - 1;
          Object localObject1 = localAtomicReferenceArray.get(j);
          for (Object localObject2 = localObject1; localObject2 != null; localObject2 = localStrategy.getNext(localObject2))
            if ((localStrategy.getHash(localObject2) == paramInt) && (paramObject1.equals(localObject2)))
            {
              Object localObject3 = localStrategy.getValue(localObject2);
              if ((localObject3 == paramObject2) || ((paramObject2 != null) && (localStrategy.equalValues(localObject3, paramObject2))))
              {
                this.modCount += 1;
                Object localObject4 = localStrategy.getNext(localObject2);
                for (Object localObject5 = localObject1; localObject5 != localObject2; localObject5 = localStrategy.getNext(localObject5))
                {
                  Object localObject6 = localStrategy.getKey(localObject5);
                  if (localObject6 != null)
                    localObject4 = localStrategy.copyEntry(localObject6, localObject5, localObject4);
                }
                localAtomicReferenceArray.set(j, localObject4);
                this.count = i;
                boolean bool3 = true;
                return bool3;
              }
              boolean bool2 = false;
              return bool2;
            }
          boolean bool1 = false;
          return bool1;
        }
        finally
        {
          unlock();
        }
      }

      public boolean removeEntry(Object paramObject, int paramInt)
      {
        $CustomConcurrentHashMap.Strategy localStrategy = $CustomConcurrentHashMap.Impl.this.strategy;
        lock();
        try
        {
          int i = this.count - 1;
          AtomicReferenceArray localAtomicReferenceArray = this.table;
          int j = paramInt & localAtomicReferenceArray.length() - 1;
          Object localObject1 = localAtomicReferenceArray.get(j);
          for (Object localObject2 = localObject1; localObject2 != null; localObject2 = localStrategy.getNext(localObject2))
            if ((localStrategy.getHash(localObject2) == paramInt) && (paramObject.equals(localObject2)))
            {
              this.modCount += 1;
              Object localObject3 = localStrategy.getNext(localObject2);
              for (Object localObject4 = localObject1; localObject4 != localObject2; localObject4 = localStrategy.getNext(localObject4))
              {
                Object localObject5 = localStrategy.getKey(localObject4);
                if (localObject5 != null)
                  localObject3 = localStrategy.copyEntry(localObject5, localObject4, localObject3);
              }
              localAtomicReferenceArray.set(j, localObject3);
              this.count = i;
              boolean bool2 = true;
              return bool2;
            }
          boolean bool1 = false;
          return bool1;
        }
        finally
        {
          unlock();
        }
      }

      void clear()
      {
        if (this.count != 0)
        {
          lock();
          try
          {
            AtomicReferenceArray localAtomicReferenceArray = this.table;
            for (int i = 0; i < localAtomicReferenceArray.length(); i++)
              localAtomicReferenceArray.set(i, null);
            this.modCount += 1;
            this.count = 0;
          }
          finally
          {
            unlock();
          }
        }
      }
    }

    class InternalsImpl
      implements .CustomConcurrentHashMap.Internals, Serializable
    {
      static final long serialVersionUID = 0L;

      InternalsImpl()
      {
      }

      public Object getEntry(Object paramObject)
      {
        if (paramObject == null)
          throw new NullPointerException("key");
        int i = $CustomConcurrentHashMap.Impl.this.hash(paramObject);
        return .CustomConcurrentHashMap.Impl.this.segmentFor(i).getEntry(paramObject, i);
      }

      public boolean removeEntry(Object paramObject1, Object paramObject2)
      {
        if (paramObject1 == null)
          throw new NullPointerException("entry");
        int i = $CustomConcurrentHashMap.Impl.this.strategy.getHash(paramObject1);
        return .CustomConcurrentHashMap.Impl.this.segmentFor(i).removeEntry(paramObject1, i, paramObject2);
      }

      public boolean removeEntry(Object paramObject)
      {
        if (paramObject == null)
          throw new NullPointerException("entry");
        int i = $CustomConcurrentHashMap.Impl.this.strategy.getHash(paramObject);
        return .CustomConcurrentHashMap.Impl.this.segmentFor(i).removeEntry(paramObject, i);
      }
    }
  }

  public static abstract interface ComputingStrategy extends $CustomConcurrentHashMap.Strategy
  {
    public abstract Object compute(Object paramObject1, Object paramObject2, .Function paramFunction);

    public abstract Object waitForValue(Object paramObject)
      throws InterruptedException;
  }

  public static abstract interface Internals
  {
    public abstract Object getEntry(Object paramObject);

    public abstract boolean removeEntry(Object paramObject1, @$Nullable Object paramObject2);

    public abstract boolean removeEntry(Object paramObject);
  }

  public static abstract interface Strategy
  {
    public abstract Object newEntry(Object paramObject1, int paramInt, Object paramObject2);

    public abstract Object copyEntry(Object paramObject1, Object paramObject2, Object paramObject3);

    public abstract void setValue(Object paramObject1, Object paramObject2);

    public abstract Object getValue(Object paramObject);

    public abstract boolean equalKeys(Object paramObject1, Object paramObject2);

    public abstract boolean equalValues(Object paramObject1, Object paramObject2);

    public abstract int hashKey(Object paramObject);

    public abstract Object getKey(Object paramObject);

    public abstract Object getNext(Object paramObject);

    public abstract int getHash(Object paramObject);

    public abstract void setInternals($CustomConcurrentHashMap.Internals paramInternals);
  }

  static final class Builder
  {
    float loadFactor = 0.75F;
    int initialCapacity = 16;
    int concurrencyLevel = 16;

    public Builder loadFactor(float paramFloat)
    {
      if (paramFloat <= 0.0F)
        throw new IllegalArgumentException();
      this.loadFactor = paramFloat;
      return this;
    }

    public Builder initialCapacity(int paramInt)
    {
      if (paramInt < 0)
        throw new IllegalArgumentException();
      this.initialCapacity = paramInt;
      return this;
    }

    public Builder concurrencyLevel(int paramInt)
    {
      if (paramInt <= 0)
        throw new IllegalArgumentException();
      this.concurrencyLevel = paramInt;
      return this;
    }

    public ConcurrentMap buildMap($CustomConcurrentHashMap.Strategy paramStrategy)
    {
      if (paramStrategy == null)
        throw new NullPointerException("strategy");
      return new $CustomConcurrentHashMap.Impl(paramStrategy, this);
    }

    public ConcurrentMap buildComputingMap($CustomConcurrentHashMap.ComputingStrategy paramComputingStrategy, .Function paramFunction)
    {
      if (paramComputingStrategy == null)
        throw new NullPointerException("strategy");
      if (paramFunction == null)
        throw new NullPointerException("computer");
      return new $CustomConcurrentHashMap.ComputingImpl(paramComputingStrategy, this, paramFunction);
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.util..CustomConcurrentHashMap
 * JD-Core Version:    0.6.2
 */