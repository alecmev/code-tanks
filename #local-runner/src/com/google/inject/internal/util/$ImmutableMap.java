package com.google.inject.internal.util;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

public abstract class $ImmutableMap
  implements Serializable, ConcurrentMap
{
  private static final ImmutableMap EMPTY_IMMUTABLE_MAP = new EmptyImmutableMap(null);

  public static ImmutableMap of()
  {
    return EMPTY_IMMUTABLE_MAP;
  }

  public static ImmutableMap of(Object paramObject1, Object paramObject2)
  {
    return new SingletonImmutableMap($Preconditions.checkNotNull(paramObject1), .Preconditions.checkNotNull(paramObject2), null);
  }

  public static ImmutableMap of(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4)
  {
    return new RegularImmutableMap(new Map.Entry[] { entryOf(paramObject1, paramObject2), entryOf(paramObject3, paramObject4) }, null);
  }

  public static ImmutableMap of(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5, Object paramObject6)
  {
    return new RegularImmutableMap(new Map.Entry[] { entryOf(paramObject1, paramObject2), entryOf(paramObject3, paramObject4), entryOf(paramObject5, paramObject6) }, null);
  }

  public static ImmutableMap of(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5, Object paramObject6, Object paramObject7, Object paramObject8)
  {
    return new RegularImmutableMap(new Map.Entry[] { entryOf(paramObject1, paramObject2), entryOf(paramObject3, paramObject4), entryOf(paramObject5, paramObject6), entryOf(paramObject7, paramObject8) }, null);
  }

  public static ImmutableMap of(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5, Object paramObject6, Object paramObject7, Object paramObject8, Object paramObject9, Object paramObject10)
  {
    return new RegularImmutableMap(new Map.Entry[] { entryOf(paramObject1, paramObject2), entryOf(paramObject3, paramObject4), entryOf(paramObject5, paramObject6), entryOf(paramObject7, paramObject8), entryOf(paramObject9, paramObject10) }, null);
  }

  public static Builder builder()
  {
    return new Builder();
  }

  private static Map.Entry entryOf(Object paramObject1, Object paramObject2)
  {
    return .Maps.immutableEntry($Preconditions.checkNotNull(paramObject1), .Preconditions.checkNotNull(paramObject2));
  }

  public static ImmutableMap copyOf(Map paramMap)
  {
    if ((paramMap instanceof ImmutableMap))
    {
      ImmutableMap localImmutableMap = (ImmutableMap)paramMap;
      return localImmutableMap;
    }
    int i = paramMap.size();
    switch (i)
    {
    case 0:
      return of();
    case 1:
      Map.Entry localEntry1 = (Map.Entry).Iterables.getOnlyElement(paramMap.entrySet());
      return of(localEntry1.getKey(), localEntry1.getValue());
    }
    Map.Entry[] arrayOfEntry = new Map.Entry[i];
    int j = 0;
    Iterator localIterator = paramMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry2 = (Map.Entry)localIterator.next();
      arrayOfEntry[(j++)] = entryOf(localEntry2.getKey(), localEntry2.getValue());
    }
    return new RegularImmutableMap(arrayOfEntry, null);
  }

  public final Object put(Object paramObject1, Object paramObject2)
  {
    throw new UnsupportedOperationException();
  }

  public final Object remove(Object paramObject)
  {
    throw new UnsupportedOperationException();
  }

  public final Object putIfAbsent(Object paramObject1, Object paramObject2)
  {
    throw new UnsupportedOperationException();
  }

  public final boolean remove(Object paramObject1, Object paramObject2)
  {
    throw new UnsupportedOperationException();
  }

  public final boolean replace(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    throw new UnsupportedOperationException();
  }

  public final Object replace(Object paramObject1, Object paramObject2)
  {
    throw new UnsupportedOperationException();
  }

  public final void putAll(Map paramMap)
  {
    throw new UnsupportedOperationException();
  }

  public final void clear()
  {
    throw new UnsupportedOperationException();
  }

  public abstract boolean containsKey(@$Nullable Object paramObject);

  public abstract boolean containsValue(@$Nullable Object paramObject);

  public abstract Object get(@$Nullable Object paramObject);

  public abstract $ImmutableSet entrySet();

  public abstract $ImmutableSet keySet();

  public abstract $ImmutableCollection values();

  public boolean equals(@$Nullable Object paramObject)
  {
    if (paramObject == this)
      return true;
    if ((paramObject instanceof Map))
    {
      Map localMap = (Map)paramObject;
      return entrySet().equals(localMap.entrySet());
    }
    return false;
  }

  public int hashCode()
  {
    return entrySet().hashCode();
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(size() * 16).append('{');
    $UnmodifiableIterator localUnmodifiableIterator = entrySet().iterator();
    localStringBuilder.append(localUnmodifiableIterator.next());
    while (localUnmodifiableIterator.hasNext())
      localStringBuilder.append(", ").append(localUnmodifiableIterator.next());
    return '}';
  }

  Object writeReplace()
  {
    return new SerializedForm(this);
  }

  private static class SerializedForm
    implements Serializable
  {
    final Object[] keys;
    final Object[] values;
    private static final long serialVersionUID = 0L;

    SerializedForm($ImmutableMap paramImmutableMap)
    {
      this.keys = new Object[paramImmutableMap.size()];
      this.values = new Object[paramImmutableMap.size()];
      int i = 0;
      Iterator localIterator = paramImmutableMap.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        this.keys[i] = localEntry.getKey();
        this.values[i] = localEntry.getValue();
        i++;
      }
    }

    Object readResolve()
    {
      $ImmutableMap.Builder localBuilder = new $ImmutableMap.Builder();
      for (int i = 0; i < this.keys.length; i++)
        localBuilder.put(this.keys[i], this.values[i]);
      return localBuilder.build();
    }
  }

  private static final class RegularImmutableMap extends $ImmutableMap
  {
    private final transient Map.Entry[] entries;
    private final transient Object[] table;
    private final transient int mask;
    private final transient int keySetHashCode;
    private transient .ImmutableSet entrySet;
    private transient .ImmutableSet keySet;
    private transient .ImmutableCollection values;

    private RegularImmutableMap(Map.Entry[] paramArrayOfEntry)
    {
      Map.Entry[] arrayOfEntry1 = (Map.Entry[])paramArrayOfEntry;
      this.entries = arrayOfEntry1;
      int i = $Hashing.chooseTableSize(paramArrayOfEntry.length);
      this.table = new Object[i * 2];
      this.mask = (i - 1);
      int j = 0;
      for (Map.Entry localEntry : this.entries)
      {
        Object localObject1 = localEntry.getKey();
        int n = localObject1.hashCode();
        for (int i1 = $Hashing.smear(n); ; i1++)
        {
          int i2 = (i1 & this.mask) * 2;
          Object localObject2 = this.table[i2];
          if (localObject2 == null)
          {
            Object localObject3 = localEntry.getValue();
            this.table[i2] = localObject1;
            this.table[(i2 + 1)] = localObject3;
            j += n;
            break;
          }
          if (localObject2.equals(localObject1))
            throw new IllegalArgumentException("duplicate key: " + localObject1);
        }
      }
      this.keySetHashCode = j;
    }

    public Object get(Object paramObject)
    {
      if (paramObject == null)
        return null;
      for (int i = $Hashing.smear(paramObject.hashCode()); ; i++)
      {
        int j = (i & this.mask) * 2;
        Object localObject1 = this.table[j];
        if (localObject1 == null)
          return null;
        if (localObject1.equals(paramObject))
        {
          Object localObject2 = this.table[(j + 1)];
          return localObject2;
        }
      }
    }

    public int size()
    {
      return this.entries.length;
    }

    public boolean isEmpty()
    {
      return false;
    }

    public boolean containsKey(Object paramObject)
    {
      return get(paramObject) != null;
    }

    public boolean containsValue(Object paramObject)
    {
      if (paramObject == null)
        return false;
      for (Map.Entry localEntry : this.entries)
        if (localEntry.getValue().equals(paramObject))
          return true;
      return false;
    }

    public .ImmutableSet entrySet()
    {
      $ImmutableSet localImmutableSet = this.entrySet;
      return localImmutableSet == null ? (this.entrySet = new EntrySet(this)) : localImmutableSet;
    }

    public .ImmutableSet keySet()
    {
      $ImmutableSet localImmutableSet = this.keySet;
      return localImmutableSet == null ? (this.keySet = new KeySet(this)) : localImmutableSet;
    }

    public .ImmutableCollection values()
    {
      $ImmutableCollection localImmutableCollection = this.values;
      return localImmutableCollection == null ? (this.values = new Values(this)) : localImmutableCollection;
    }

    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder(size() * 16).append('{').append(this.entries[0]);
      for (int i = 1; i < this.entries.length; i++)
        localStringBuilder.append(", ").append(this.entries[i].toString());
      return '}';
    }

    private static class Values extends $ImmutableCollection
    {
      final .ImmutableMap.RegularImmutableMap map;

      Values($ImmutableMap.RegularImmutableMap paramRegularImmutableMap)
      {
        this.map = paramRegularImmutableMap;
      }

      public int size()
      {
        return this.map.entries.length;
      }

      public boolean isEmpty()
      {
        return false;
      }

      public .UnmodifiableIterator iterator()
      {
        $AbstractIterator local1 = new $AbstractIterator()
        {
          int index = 0;

          protected Object computeNext()
          {
            return this.index < .ImmutableMap.RegularImmutableMap.Values.this.map.entries.length ? .ImmutableMap.RegularImmutableMap.Values.this.map.entries[(this.index++)].getValue() : endOfData();
          }
        };
        return .Iterators.unmodifiableIterator(local1);
      }

      public boolean contains(Object paramObject)
      {
        return this.map.containsValue(paramObject);
      }
    }

    private static class KeySet extends $ImmutableSet.TransformedImmutableSet
    {
      final .ImmutableMap.RegularImmutableMap map;

      KeySet($ImmutableMap.RegularImmutableMap paramRegularImmutableMap)
      {
        super(paramRegularImmutableMap.keySetHashCode);
        this.map = paramRegularImmutableMap;
      }

      Object transform(Map.Entry paramEntry)
      {
        return paramEntry.getKey();
      }

      public boolean contains(Object paramObject)
      {
        return this.map.containsKey(paramObject);
      }
    }

    private static class EntrySet extends $ImmutableSet.ArrayImmutableSet
    {
      final .ImmutableMap.RegularImmutableMap map;

      EntrySet($ImmutableMap.RegularImmutableMap paramRegularImmutableMap)
      {
        super();
        this.map = paramRegularImmutableMap;
      }

      public boolean contains(Object paramObject)
      {
        if ((paramObject instanceof Map.Entry))
        {
          Map.Entry localEntry = (Map.Entry)paramObject;
          Object localObject = this.map.get(localEntry.getKey());
          return (localObject != null) && (localObject.equals(localEntry.getValue()));
        }
        return false;
      }
    }
  }

  private static final class SingletonImmutableMap extends $ImmutableMap
  {
    private final transient Object singleKey;
    private final transient Object singleValue;
    private transient Map.Entry entry;
    private transient .ImmutableSet entrySet;
    private transient .ImmutableSet keySet;
    private transient .ImmutableCollection values;

    private SingletonImmutableMap(Object paramObject1, Object paramObject2)
    {
      this.singleKey = paramObject1;
      this.singleValue = paramObject2;
    }

    private SingletonImmutableMap(Map.Entry paramEntry)
    {
      this.entry = paramEntry;
      this.singleKey = paramEntry.getKey();
      this.singleValue = paramEntry.getValue();
    }

    private Map.Entry entry()
    {
      Map.Entry localEntry = this.entry;
      return localEntry == null ? (this.entry = $Maps.immutableEntry(this.singleKey, this.singleValue)) : localEntry;
    }

    public Object get(Object paramObject)
    {
      return this.singleKey.equals(paramObject) ? this.singleValue : null;
    }

    public int size()
    {
      return 1;
    }

    public boolean isEmpty()
    {
      return false;
    }

    public boolean containsKey(Object paramObject)
    {
      return this.singleKey.equals(paramObject);
    }

    public boolean containsValue(Object paramObject)
    {
      return this.singleValue.equals(paramObject);
    }

    public .ImmutableSet entrySet()
    {
      $ImmutableSet localImmutableSet = this.entrySet;
      return localImmutableSet == null ? (this.entrySet = $ImmutableSet.of(entry())) : localImmutableSet;
    }

    public .ImmutableSet keySet()
    {
      $ImmutableSet localImmutableSet = this.keySet;
      return localImmutableSet == null ? (this.keySet = $ImmutableSet.of(this.singleKey)) : localImmutableSet;
    }

    public .ImmutableCollection values()
    {
      $ImmutableCollection localImmutableCollection = this.values;
      return localImmutableCollection == null ? (this.values = new Values(this.singleValue)) : localImmutableCollection;
    }

    public boolean equals(@$Nullable Object paramObject)
    {
      if (paramObject == this)
        return true;
      if ((paramObject instanceof Map))
      {
        Map localMap = (Map)paramObject;
        if (localMap.size() != 1)
          return false;
        Map.Entry localEntry = (Map.Entry)localMap.entrySet().iterator().next();
        return (this.singleKey.equals(localEntry.getKey())) && (this.singleValue.equals(localEntry.getValue()));
      }
      return false;
    }

    public int hashCode()
    {
      return this.singleKey.hashCode() ^ this.singleValue.hashCode();
    }

    public String toString()
    {
      return '{' + this.singleKey.toString() + '=' + this.singleValue.toString() + '}';
    }

    private static class Values extends $ImmutableCollection
    {
      final Object singleValue;

      Values(Object paramObject)
      {
        this.singleValue = paramObject;
      }

      public boolean contains(Object paramObject)
      {
        return this.singleValue.equals(paramObject);
      }

      public boolean isEmpty()
      {
        return false;
      }

      public int size()
      {
        return 1;
      }

      public .UnmodifiableIterator iterator()
      {
        return .Iterators.singletonIterator(this.singleValue);
      }
    }
  }

  private static final class EmptyImmutableMap extends $ImmutableMap
  {
    public Object get(Object paramObject)
    {
      return null;
    }

    public int size()
    {
      return 0;
    }

    public boolean isEmpty()
    {
      return true;
    }

    public boolean containsKey(Object paramObject)
    {
      return false;
    }

    public boolean containsValue(Object paramObject)
    {
      return false;
    }

    public .ImmutableSet entrySet()
    {
      return .ImmutableSet.of();
    }

    public .ImmutableSet keySet()
    {
      return .ImmutableSet.of();
    }

    public .ImmutableCollection values()
    {
      return .ImmutableCollection.EMPTY_IMMUTABLE_COLLECTION;
    }

    public boolean equals(@$Nullable Object paramObject)
    {
      if ((paramObject instanceof Map))
      {
        Map localMap = (Map)paramObject;
        return localMap.isEmpty();
      }
      return false;
    }

    public int hashCode()
    {
      return 0;
    }

    public String toString()
    {
      return "{}";
    }
  }

  public static class Builder
  {
    final List entries = $Lists.newArrayList();

    public Builder put(Object paramObject1, Object paramObject2)
    {
      this.entries.add($ImmutableMap.entryOf(paramObject1, paramObject2));
      return this;
    }

    public Builder putAll(Map paramMap)
    {
      Iterator localIterator = paramMap.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        put(localEntry.getKey(), localEntry.getValue());
      }
      return this;
    }

    public .ImmutableMap build()
    {
      return fromEntryList(this.entries);
    }

    private static .ImmutableMap fromEntryList(List paramList)
    {
      int i = paramList.size();
      switch (i)
      {
      case 0:
        return .ImmutableMap.of();
      case 1:
        return new $ImmutableMap.SingletonImmutableMap((Map.Entry).Iterables.getOnlyElement(paramList), null);
      }
      Map.Entry[] arrayOfEntry = (Map.Entry[])paramList.toArray(new Map.Entry[paramList.size()]);
      return new $ImmutableMap.RegularImmutableMap(arrayOfEntry, null);
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.util..ImmutableMap
 * JD-Core Version:    0.6.2
 */