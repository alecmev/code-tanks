package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@GwtCompatible(serializable=true, emulated=true)
public abstract class ImmutableMap
  implements Serializable, Map
{
  private transient ImmutableSet entrySet;
  private transient ImmutableSet keySet;
  private transient ImmutableCollection values;

  public static ImmutableMap of()
  {
    return EmptyImmutableMap.INSTANCE;
  }

  public static ImmutableMap of(Object paramObject1, Object paramObject2)
  {
    return new SingletonImmutableMap(Preconditions.checkNotNull(paramObject1), Preconditions.checkNotNull(paramObject2));
  }

  public static ImmutableMap of(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4)
  {
    return new RegularImmutableMap(new Map.Entry[] { entryOf(paramObject1, paramObject2), entryOf(paramObject3, paramObject4) });
  }

  public static ImmutableMap of(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5, Object paramObject6)
  {
    return new RegularImmutableMap(new Map.Entry[] { entryOf(paramObject1, paramObject2), entryOf(paramObject3, paramObject4), entryOf(paramObject5, paramObject6) });
  }

  public static ImmutableMap of(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5, Object paramObject6, Object paramObject7, Object paramObject8)
  {
    return new RegularImmutableMap(new Map.Entry[] { entryOf(paramObject1, paramObject2), entryOf(paramObject3, paramObject4), entryOf(paramObject5, paramObject6), entryOf(paramObject7, paramObject8) });
  }

  public static ImmutableMap of(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5, Object paramObject6, Object paramObject7, Object paramObject8, Object paramObject9, Object paramObject10)
  {
    return new RegularImmutableMap(new Map.Entry[] { entryOf(paramObject1, paramObject2), entryOf(paramObject3, paramObject4), entryOf(paramObject5, paramObject6), entryOf(paramObject7, paramObject8), entryOf(paramObject9, paramObject10) });
  }

  public static Builder builder()
  {
    return new Builder();
  }

  static Map.Entry entryOf(Object paramObject1, Object paramObject2)
  {
    return Maps.immutableEntry(Preconditions.checkNotNull(paramObject1, "null key"), Preconditions.checkNotNull(paramObject2, "null value"));
  }

  public static ImmutableMap copyOf(Map paramMap)
  {
    if (((paramMap instanceof ImmutableMap)) && (!(paramMap instanceof ImmutableSortedMap)))
    {
      localObject1 = (ImmutableMap)paramMap;
      if (!((ImmutableMap)localObject1).isPartialView())
        return localObject1;
    }
    Object localObject1 = (Map.Entry[])paramMap.entrySet().toArray(new Map.Entry[0]);
    switch (localObject1.length)
    {
    case 0:
      return of();
    case 1:
      return new SingletonImmutableMap(entryOf(localObject1[0].getKey(), localObject1[0].getValue()));
    }
    for (int i = 0; i < localObject1.length; i++)
    {
      Object localObject2 = localObject1[i].getKey();
      Object localObject3 = localObject1[i].getValue();
      localObject1[i] = entryOf(localObject2, localObject3);
    }
    return new RegularImmutableMap((Map.Entry[])localObject1);
  }

  public final Object put(Object paramObject1, Object paramObject2)
  {
    throw new UnsupportedOperationException();
  }

  public final Object remove(Object paramObject)
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

  public boolean isEmpty()
  {
    return size() == 0;
  }

  public boolean containsKey(Object paramObject)
  {
    return get(paramObject) != null;
  }

  public abstract boolean containsValue(Object paramObject);

  public abstract Object get(Object paramObject);

  public ImmutableSet entrySet()
  {
    ImmutableSet localImmutableSet = this.entrySet;
    return localImmutableSet == null ? (this.entrySet = createEntrySet()) : localImmutableSet;
  }

  abstract ImmutableSet createEntrySet();

  public ImmutableSet keySet()
  {
    ImmutableSet localImmutableSet = this.keySet;
    return localImmutableSet == null ? (this.keySet = createKeySet()) : localImmutableSet;
  }

  ImmutableSet createKeySet()
  {
    return new KeySet();
  }

  public ImmutableCollection values()
  {
    ImmutableCollection localImmutableCollection = this.values;
    return localImmutableCollection == null ? (this.values = createValues()) : localImmutableCollection;
  }

  ImmutableCollection createValues()
  {
    return new Values();
  }

  public boolean equals(Object paramObject)
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

  abstract boolean isPartialView();

  public int hashCode()
  {
    return entrySet().hashCode();
  }

  public String toString()
  {
    return Maps.toStringImpl(this);
  }

  Object writeReplace()
  {
    return new SerializedForm(this);
  }

  static class SerializedForm
    implements Serializable
  {
    private final Object[] keys;
    private final Object[] values;
    private static final long serialVersionUID = 0L;

    SerializedForm(ImmutableMap paramImmutableMap)
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
      ImmutableMap.Builder localBuilder = new ImmutableMap.Builder();
      return createMap(localBuilder);
    }

    Object createMap(ImmutableMap.Builder paramBuilder)
    {
      for (int i = 0; i < this.keys.length; i++)
        paramBuilder.put(this.keys[i], this.values[i]);
      return paramBuilder.build();
    }
  }

  private static class ValuesSerializedForm
    implements Serializable
  {
    final ImmutableMap map;
    private static final long serialVersionUID = 0L;

    ValuesSerializedForm(ImmutableMap paramImmutableMap)
    {
      this.map = paramImmutableMap;
    }

    Object readResolve()
    {
      return this.map.values();
    }
  }

  class Values extends ImmutableCollection
  {
    Values()
    {
    }

    public int size()
    {
      return ImmutableMap.this.size();
    }

    public UnmodifiableIterator iterator()
    {
      return Maps.valueIterator(ImmutableMap.this.entrySet().iterator());
    }

    public boolean contains(Object paramObject)
    {
      return ImmutableMap.this.containsValue(paramObject);
    }

    boolean isPartialView()
    {
      return true;
    }

    ImmutableList createAsList()
    {
      return new TransformedImmutableList(ImmutableMap.this.entrySet().asList())
      {
        Object transform(Map.Entry paramAnonymousEntry)
        {
          return paramAnonymousEntry.getValue();
        }
      };
    }

    Object writeReplace()
    {
      return new ImmutableMap.ValuesSerializedForm(ImmutableMap.this);
    }
  }

  private static class KeySetSerializedForm
    implements Serializable
  {
    final ImmutableMap map;
    private static final long serialVersionUID = 0L;

    KeySetSerializedForm(ImmutableMap paramImmutableMap)
    {
      this.map = paramImmutableMap;
    }

    Object readResolve()
    {
      return this.map.keySet();
    }
  }

  class KeySet extends ImmutableSet.TransformedImmutableSet
  {
    KeySet()
    {
      super();
    }

    KeySet(int arg2)
    {
      super(i);
    }

    Object transform(Map.Entry paramEntry)
    {
      return paramEntry.getKey();
    }

    public boolean contains(Object paramObject)
    {
      return ImmutableMap.this.containsKey(paramObject);
    }

    boolean isPartialView()
    {
      return true;
    }

    ImmutableList createAsList()
    {
      return new TransformedImmutableList(ImmutableMap.this.entrySet().asList())
      {
        Object transform(Map.Entry paramAnonymousEntry)
        {
          return paramAnonymousEntry.getKey();
        }
      };
    }

    Object writeReplace()
    {
      return new ImmutableMap.KeySetSerializedForm(ImmutableMap.this);
    }
  }

  private static class EntrySetSerializedForm
    implements Serializable
  {
    final ImmutableMap map;
    private static final long serialVersionUID = 0L;

    EntrySetSerializedForm(ImmutableMap paramImmutableMap)
    {
      this.map = paramImmutableMap;
    }

    Object readResolve()
    {
      return this.map.entrySet();
    }
  }

  abstract class EntrySet extends ImmutableSet
  {
    EntrySet()
    {
    }

    public int size()
    {
      return ImmutableMap.this.size();
    }

    public boolean contains(Object paramObject)
    {
      if ((paramObject instanceof Map.Entry))
      {
        Map.Entry localEntry = (Map.Entry)paramObject;
        Object localObject = ImmutableMap.this.get(localEntry.getKey());
        return (localObject != null) && (localObject.equals(localEntry.getValue()));
      }
      return false;
    }

    boolean isPartialView()
    {
      return ImmutableMap.this.isPartialView();
    }

    Object writeReplace()
    {
      return new ImmutableMap.EntrySetSerializedForm(ImmutableMap.this);
    }
  }

  public static class Builder
  {
    final ArrayList entries = Lists.newArrayList();

    public Builder put(Object paramObject1, Object paramObject2)
    {
      this.entries.add(ImmutableMap.entryOf(paramObject1, paramObject2));
      return this;
    }

    public Builder put(Map.Entry paramEntry)
    {
      Object localObject1 = paramEntry.getKey();
      Object localObject2 = paramEntry.getValue();
      if ((paramEntry instanceof ImmutableEntry))
      {
        Preconditions.checkNotNull(localObject1);
        Preconditions.checkNotNull(localObject2);
        Map.Entry localEntry = paramEntry;
        this.entries.add(localEntry);
      }
      else
      {
        this.entries.add(ImmutableMap.entryOf(localObject1, localObject2));
      }
      return this;
    }

    public Builder putAll(Map paramMap)
    {
      this.entries.ensureCapacity(this.entries.size() + paramMap.size());
      Iterator localIterator = paramMap.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        put(localEntry.getKey(), localEntry.getValue());
      }
      return this;
    }

    public ImmutableMap build()
    {
      return fromEntryList(this.entries);
    }

    private static ImmutableMap fromEntryList(List paramList)
    {
      int i = paramList.size();
      switch (i)
      {
      case 0:
        return ImmutableMap.of();
      case 1:
        return new SingletonImmutableMap((Map.Entry)Iterables.getOnlyElement(paramList));
      }
      Map.Entry[] arrayOfEntry = (Map.Entry[])paramList.toArray(new Map.Entry[paramList.size()]);
      return new RegularImmutableMap(arrayOfEntry);
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.ImmutableMap
 * JD-Core Version:    0.6.2
 */