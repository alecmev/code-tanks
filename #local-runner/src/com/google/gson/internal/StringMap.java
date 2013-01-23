package com.google.gson.internal;

import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;

public final class StringMap extends AbstractMap
{
  private static final int MINIMUM_CAPACITY = 4;
  private static final int MAXIMUM_CAPACITY = 1073741824;
  private LinkedEntry header = new LinkedEntry();
  private static final Map.Entry[] EMPTY_TABLE = new LinkedEntry[2];
  private LinkedEntry[] table = (LinkedEntry[])EMPTY_TABLE;
  private int size;
  private int threshold = -1;
  private Set keySet;
  private Set entrySet;
  private Collection values;
  private static final int seed = new Random().nextInt();

  public int size()
  {
    return this.size;
  }

  public boolean containsKey(Object paramObject)
  {
    return ((paramObject instanceof String)) && (getEntry((String)paramObject) != null);
  }

  public Object get(Object paramObject)
  {
    if ((paramObject instanceof String))
    {
      LinkedEntry localLinkedEntry = getEntry((String)paramObject);
      return localLinkedEntry != null ? localLinkedEntry.value : null;
    }
    return null;
  }

  private LinkedEntry getEntry(String paramString)
  {
    if (paramString == null)
      return null;
    int i = hash(paramString);
    LinkedEntry[] arrayOfLinkedEntry = this.table;
    for (LinkedEntry localLinkedEntry = arrayOfLinkedEntry[(i & arrayOfLinkedEntry.length - 1)]; localLinkedEntry != null; localLinkedEntry = localLinkedEntry.next)
    {
      String str = localLinkedEntry.key;
      if ((str == paramString) || ((localLinkedEntry.hash == i) && (paramString.equals(str))))
        return localLinkedEntry;
    }
    return null;
  }

  public Object put(String paramString, Object paramObject)
  {
    if (paramString == null)
      throw new NullPointerException("key == null");
    int i = hash(paramString);
    LinkedEntry[] arrayOfLinkedEntry = this.table;
    int j = i & arrayOfLinkedEntry.length - 1;
    for (LinkedEntry localLinkedEntry = arrayOfLinkedEntry[j]; localLinkedEntry != null; localLinkedEntry = localLinkedEntry.next)
      if ((localLinkedEntry.hash == i) && (paramString.equals(localLinkedEntry.key)))
      {
        Object localObject = localLinkedEntry.value;
        localLinkedEntry.value = paramObject;
        return localObject;
      }
    if (this.size++ > this.threshold)
    {
      arrayOfLinkedEntry = doubleCapacity();
      j = i & arrayOfLinkedEntry.length - 1;
    }
    addNewEntry(paramString, paramObject, i, j);
    return null;
  }

  private void addNewEntry(String paramString, Object paramObject, int paramInt1, int paramInt2)
  {
    LinkedEntry localLinkedEntry1 = this.header;
    LinkedEntry localLinkedEntry2 = localLinkedEntry1.prv;
    LinkedEntry localLinkedEntry3 = new LinkedEntry(paramString, paramObject, paramInt1, this.table[paramInt2], localLinkedEntry1, localLinkedEntry2);
    LinkedEntry tmp52_49 = (localLinkedEntry1.prv = localLinkedEntry3);
    localLinkedEntry2.nxt = tmp52_49;
    this.table[paramInt2] = tmp52_49;
  }

  private LinkedEntry[] makeTable(int paramInt)
  {
    LinkedEntry[] arrayOfLinkedEntry = (LinkedEntry[])new LinkedEntry[paramInt];
    this.table = arrayOfLinkedEntry;
    this.threshold = ((paramInt >> 1) + (paramInt >> 2));
    return arrayOfLinkedEntry;
  }

  private LinkedEntry[] doubleCapacity()
  {
    LinkedEntry[] arrayOfLinkedEntry1 = this.table;
    int i = arrayOfLinkedEntry1.length;
    if (i == 1073741824)
      return arrayOfLinkedEntry1;
    int j = i * 2;
    LinkedEntry[] arrayOfLinkedEntry2 = makeTable(j);
    if (this.size == 0)
      return arrayOfLinkedEntry2;
    for (int k = 0; k < i; k++)
    {
      Object localObject1 = arrayOfLinkedEntry1[k];
      if (localObject1 != null)
      {
        int m = ((LinkedEntry)localObject1).hash & i;
        Object localObject2 = null;
        arrayOfLinkedEntry2[(k | m)] = localObject1;
        for (LinkedEntry localLinkedEntry = ((LinkedEntry)localObject1).next; localLinkedEntry != null; localLinkedEntry = localLinkedEntry.next)
        {
          int n = localLinkedEntry.hash & i;
          if (n != m)
          {
            if (localObject2 == null)
              arrayOfLinkedEntry2[(k | n)] = localLinkedEntry;
            else
              localObject2.next = localLinkedEntry;
            localObject2 = localObject1;
            m = n;
          }
          localObject1 = localLinkedEntry;
        }
        if (localObject2 != null)
          localObject2.next = null;
      }
    }
    return arrayOfLinkedEntry2;
  }

  public Object remove(Object paramObject)
  {
    if ((paramObject == null) || (!(paramObject instanceof String)))
      return null;
    int i = hash((String)paramObject);
    LinkedEntry[] arrayOfLinkedEntry = this.table;
    int j = i & arrayOfLinkedEntry.length - 1;
    LinkedEntry localLinkedEntry1 = arrayOfLinkedEntry[j];
    LinkedEntry localLinkedEntry2 = null;
    while (localLinkedEntry1 != null)
    {
      if ((localLinkedEntry1.hash == i) && (paramObject.equals(localLinkedEntry1.key)))
      {
        if (localLinkedEntry2 == null)
          arrayOfLinkedEntry[j] = localLinkedEntry1.next;
        else
          localLinkedEntry2.next = localLinkedEntry1.next;
        this.size -= 1;
        unlink(localLinkedEntry1);
        return localLinkedEntry1.value;
      }
      localLinkedEntry2 = localLinkedEntry1;
      localLinkedEntry1 = localLinkedEntry1.next;
    }
    return null;
  }

  private void unlink(LinkedEntry paramLinkedEntry)
  {
    paramLinkedEntry.prv.nxt = paramLinkedEntry.nxt;
    paramLinkedEntry.nxt.prv = paramLinkedEntry.prv;
    paramLinkedEntry.nxt = (paramLinkedEntry.prv = null);
  }

  public void clear()
  {
    if (this.size != 0)
    {
      Arrays.fill(this.table, null);
      this.size = 0;
    }
    LinkedEntry localLinkedEntry1 = this.header;
    LinkedEntry localLinkedEntry2;
    for (Object localObject = localLinkedEntry1.nxt; localObject != localLinkedEntry1; localObject = localLinkedEntry2)
    {
      localLinkedEntry2 = ((LinkedEntry)localObject).nxt;
      ((LinkedEntry)localObject).nxt = (((LinkedEntry)localObject).prv = null);
    }
    localLinkedEntry1.nxt = (localLinkedEntry1.prv = localLinkedEntry1);
  }

  public Set keySet()
  {
    Set localSet = this.keySet;
    return this.keySet = new KeySet(null);
  }

  public Collection values()
  {
    Collection localCollection = this.values;
    return this.values = new Values(null);
  }

  public Set entrySet()
  {
    Set localSet = this.entrySet;
    return this.entrySet = new EntrySet(null);
  }

  private boolean removeMapping(Object paramObject1, Object paramObject2)
  {
    if ((paramObject1 == null) || (!(paramObject1 instanceof String)))
      return false;
    int i = hash((String)paramObject1);
    LinkedEntry[] arrayOfLinkedEntry = this.table;
    int j = i & arrayOfLinkedEntry.length - 1;
    LinkedEntry localLinkedEntry1 = arrayOfLinkedEntry[j];
    LinkedEntry localLinkedEntry2 = null;
    while (localLinkedEntry1 != null)
    {
      if ((localLinkedEntry1.hash == i) && (paramObject1.equals(localLinkedEntry1.key)))
      {
        if (paramObject2 == null ? localLinkedEntry1.value != null : !paramObject2.equals(localLinkedEntry1.value))
          return false;
        if (localLinkedEntry2 == null)
          arrayOfLinkedEntry[j] = localLinkedEntry1.next;
        else
          localLinkedEntry2.next = localLinkedEntry1.next;
        this.size -= 1;
        unlink(localLinkedEntry1);
        return true;
      }
      localLinkedEntry2 = localLinkedEntry1;
      localLinkedEntry1 = localLinkedEntry1.next;
    }
    return false;
  }

  private static int hash(String paramString)
  {
    int i = seed;
    for (int j = 0; j < paramString.length(); j++)
    {
      int k = i + paramString.charAt(j);
      int m = k + k << 10;
      i = m ^ m >>> 6;
    }
    i ^= i >>> 20 ^ i >>> 12;
    return i ^ i >>> 7 ^ i >>> 4;
  }

  private final class EntrySet extends AbstractSet
  {
    private EntrySet()
    {
    }

    public Iterator iterator()
    {
      // Byte code:
      //   0: new 4	com/google/gson/internal/StringMap$EntrySet$1
      //   3: dup
      //   4: aload_0
      //   5: invokespecial 15	com/google/gson/internal/StringMap$EntrySet$1:<init>	(Lcom/google/gson/internal/StringMap$EntrySet;)V
      //   8: areturn
    }

    public boolean contains(Object paramObject)
    {
      if (!(paramObject instanceof Map.Entry))
        return false;
      Map.Entry localEntry = (Map.Entry)paramObject;
      Object localObject = StringMap.this.get(localEntry.getKey());
      return (localObject != null) && (localObject.equals(localEntry.getValue()));
    }

    public boolean remove(Object paramObject)
    {
      if (!(paramObject instanceof Map.Entry))
        return false;
      Map.Entry localEntry = (Map.Entry)paramObject;
      return StringMap.this.removeMapping(localEntry.getKey(), localEntry.getValue());
    }

    public int size()
    {
      return StringMap.this.size;
    }

    public void clear()
    {
      StringMap.this.clear();
    }
  }

  private final class Values extends AbstractCollection
  {
    private Values()
    {
    }

    public Iterator iterator()
    {
      // Byte code:
      //   0: new 4	com/google/gson/internal/StringMap$Values$1
      //   3: dup
      //   4: aload_0
      //   5: invokespecial 11	com/google/gson/internal/StringMap$Values$1:<init>	(Lcom/google/gson/internal/StringMap$Values;)V
      //   8: areturn
    }

    public int size()
    {
      return StringMap.this.size;
    }

    public boolean contains(Object paramObject)
    {
      return StringMap.this.containsValue(paramObject);
    }

    public void clear()
    {
      StringMap.this.clear();
    }
  }

  private final class KeySet extends AbstractSet
  {
    private KeySet()
    {
    }

    public Iterator iterator()
    {
      // Byte code:
      //   0: new 4	com/google/gson/internal/StringMap$KeySet$1
      //   3: dup
      //   4: aload_0
      //   5: invokespecial 12	com/google/gson/internal/StringMap$KeySet$1:<init>	(Lcom/google/gson/internal/StringMap$KeySet;)V
      //   8: areturn
    }

    public int size()
    {
      return StringMap.this.size;
    }

    public boolean contains(Object paramObject)
    {
      return StringMap.this.containsKey(paramObject);
    }

    public boolean remove(Object paramObject)
    {
      int i = StringMap.this.size;
      StringMap.this.remove(paramObject);
      return StringMap.this.size != i;
    }

    public void clear()
    {
      StringMap.this.clear();
    }
  }

  private abstract class LinkedHashIterator
    implements Iterator
  {
    StringMap.LinkedEntry next = StringMap.this.header.nxt;
    StringMap.LinkedEntry lastReturned = null;

    private LinkedHashIterator()
    {
    }

    public final boolean hasNext()
    {
      return this.next != StringMap.this.header;
    }

    final StringMap.LinkedEntry nextEntry()
    {
      StringMap.LinkedEntry localLinkedEntry = this.next;
      if (localLinkedEntry == StringMap.this.header)
        throw new NoSuchElementException();
      this.next = localLinkedEntry.nxt;
      return this.lastReturned = localLinkedEntry;
    }

    public final void remove()
    {
      if (this.lastReturned == null)
        throw new IllegalStateException();
      StringMap.this.remove(this.lastReturned.key);
      this.lastReturned = null;
    }
  }

  static class LinkedEntry
    implements Map.Entry
  {
    final String key;
    Object value;
    final int hash;
    LinkedEntry next;
    LinkedEntry nxt;
    LinkedEntry prv;

    LinkedEntry()
    {
      this(null, null, 0, null, null, null);
      this.prv = this;
      this.nxt = this;
    }

    LinkedEntry(String paramString, Object paramObject, int paramInt, LinkedEntry paramLinkedEntry1, LinkedEntry paramLinkedEntry2, LinkedEntry paramLinkedEntry3)
    {
      this.key = paramString;
      this.value = paramObject;
      this.hash = paramInt;
      this.next = paramLinkedEntry1;
      this.nxt = paramLinkedEntry2;
      this.prv = paramLinkedEntry3;
    }

    public final String getKey()
    {
      return this.key;
    }

    public final Object getValue()
    {
      return this.value;
    }

    public final Object setValue(Object paramObject)
    {
      Object localObject = this.value;
      this.value = paramObject;
      return localObject;
    }

    public final boolean equals(Object paramObject)
    {
      if (!(paramObject instanceof Map.Entry))
        return false;
      Map.Entry localEntry = (Map.Entry)paramObject;
      Object localObject = localEntry.getValue();
      return (this.key.equals(localEntry.getKey())) && (this.value == null ? localObject == null : this.value.equals(localObject));
    }

    public final int hashCode()
    {
      return (this.key == null ? 0 : this.key.hashCode()) ^ (this.value == null ? 0 : this.value.hashCode());
    }

    public final String toString()
    {
      return this.key + "=" + this.value;
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.gson.internal.StringMap
 * JD-Core Version:    0.6.2
 */