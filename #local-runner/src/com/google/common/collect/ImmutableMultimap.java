package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@GwtCompatible(emulated=true)
public abstract class ImmutableMultimap
  implements Multimap, Serializable
{
  final transient ImmutableMap map;
  final transient int size;
  private transient ImmutableCollection entries;
  private transient ImmutableMultiset keys;
  private transient ImmutableCollection values;
  private static final long serialVersionUID = 0L;

  public static ImmutableMultimap of()
  {
    return ImmutableListMultimap.of();
  }

  public static ImmutableMultimap of(Object paramObject1, Object paramObject2)
  {
    return ImmutableListMultimap.of(paramObject1, paramObject2);
  }

  public static ImmutableMultimap of(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4)
  {
    return ImmutableListMultimap.of(paramObject1, paramObject2, paramObject3, paramObject4);
  }

  public static ImmutableMultimap of(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5, Object paramObject6)
  {
    return ImmutableListMultimap.of(paramObject1, paramObject2, paramObject3, paramObject4, paramObject5, paramObject6);
  }

  public static ImmutableMultimap of(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5, Object paramObject6, Object paramObject7, Object paramObject8)
  {
    return ImmutableListMultimap.of(paramObject1, paramObject2, paramObject3, paramObject4, paramObject5, paramObject6, paramObject7, paramObject8);
  }

  public static ImmutableMultimap of(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5, Object paramObject6, Object paramObject7, Object paramObject8, Object paramObject9, Object paramObject10)
  {
    return ImmutableListMultimap.of(paramObject1, paramObject2, paramObject3, paramObject4, paramObject5, paramObject6, paramObject7, paramObject8, paramObject9, paramObject10);
  }

  public static Builder builder()
  {
    return new Builder();
  }

  public static ImmutableMultimap copyOf(Multimap paramMultimap)
  {
    if ((paramMultimap instanceof ImmutableMultimap))
    {
      ImmutableMultimap localImmutableMultimap = (ImmutableMultimap)paramMultimap;
      if (!localImmutableMultimap.isPartialView())
        return localImmutableMultimap;
    }
    return ImmutableListMultimap.copyOf(paramMultimap);
  }

  ImmutableMultimap(ImmutableMap paramImmutableMap, int paramInt)
  {
    this.map = paramImmutableMap;
    this.size = paramInt;
  }

  public ImmutableCollection removeAll(Object paramObject)
  {
    throw new UnsupportedOperationException();
  }

  public ImmutableCollection replaceValues(Object paramObject, Iterable paramIterable)
  {
    throw new UnsupportedOperationException();
  }

  public void clear()
  {
    throw new UnsupportedOperationException();
  }

  public abstract ImmutableCollection get(Object paramObject);

  @Beta
  public abstract ImmutableMultimap inverse();

  public boolean put(Object paramObject1, Object paramObject2)
  {
    throw new UnsupportedOperationException();
  }

  public boolean putAll(Object paramObject, Iterable paramIterable)
  {
    throw new UnsupportedOperationException();
  }

  public boolean putAll(Multimap paramMultimap)
  {
    throw new UnsupportedOperationException();
  }

  public boolean remove(Object paramObject1, Object paramObject2)
  {
    throw new UnsupportedOperationException();
  }

  boolean isPartialView()
  {
    return this.map.isPartialView();
  }

  public boolean containsEntry(Object paramObject1, Object paramObject2)
  {
    Collection localCollection = (Collection)this.map.get(paramObject1);
    return (localCollection != null) && (localCollection.contains(paramObject2));
  }

  public boolean containsKey(Object paramObject)
  {
    return this.map.containsKey(paramObject);
  }

  public boolean containsValue(Object paramObject)
  {
    Iterator localIterator = this.map.values().iterator();
    while (localIterator.hasNext())
    {
      ImmutableCollection localImmutableCollection = (ImmutableCollection)localIterator.next();
      if (localImmutableCollection.contains(paramObject))
        return true;
    }
    return false;
  }

  public boolean isEmpty()
  {
    return this.size == 0;
  }

  public int size()
  {
    return this.size;
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof Serializable))
    {
      Multimap localMultimap = (Serializable)paramObject;
      return this.map.equals(localMultimap.asMap());
    }
    return false;
  }

  public int hashCode()
  {
    return this.map.hashCode();
  }

  public String toString()
  {
    return this.map.toString();
  }

  public ImmutableSet keySet()
  {
    return this.map.keySet();
  }

  public ImmutableMap asMap()
  {
    return this.map;
  }

  public ImmutableCollection entries()
  {
    ImmutableCollection localImmutableCollection = this.entries;
    return localImmutableCollection == null ? (this.entries = new EntryCollection(this)) : localImmutableCollection;
  }

  public ImmutableMultiset keys()
  {
    ImmutableMultiset localImmutableMultiset = this.keys;
    return localImmutableMultiset == null ? (this.keys = createKeys()) : localImmutableMultiset;
  }

  private ImmutableMultiset createKeys()
  {
    ImmutableMultiset.Builder localBuilder = ImmutableMultiset.builder();
    Iterator localIterator = this.map.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      localBuilder.addCopies(localEntry.getKey(), ((ImmutableCollection)localEntry.getValue()).size());
    }
    return localBuilder.build();
  }

  public ImmutableCollection values()
  {
    ImmutableCollection localImmutableCollection = this.values;
    return localImmutableCollection == null ? (this.values = new Values(this)) : localImmutableCollection;
  }

  private static class Values extends ImmutableCollection
  {
    final ImmutableMultimap multimap;
    private static final long serialVersionUID = 0L;

    Values(ImmutableMultimap paramImmutableMultimap)
    {
      this.multimap = paramImmutableMultimap;
    }

    public UnmodifiableIterator iterator()
    {
      return Maps.valueIterator(this.multimap.entries().iterator());
    }

    public int size()
    {
      return this.multimap.size();
    }

    boolean isPartialView()
    {
      return true;
    }
  }

  private static class EntryCollection extends ImmutableCollection
  {
    final ImmutableMultimap multimap;
    private static final long serialVersionUID = 0L;

    EntryCollection(ImmutableMultimap paramImmutableMultimap)
    {
      this.multimap = paramImmutableMultimap;
    }

    public UnmodifiableIterator iterator()
    {
      final UnmodifiableIterator localUnmodifiableIterator = this.multimap.map.entrySet().iterator();
      return new UnmodifiableIterator()
      {
        Object key;
        Iterator valueIterator;

        public boolean hasNext()
        {
          return ((this.key != null) && (this.valueIterator.hasNext())) || (localUnmodifiableIterator.hasNext());
        }

        public Map.Entry next()
        {
          if ((this.key == null) || (!this.valueIterator.hasNext()))
          {
            Map.Entry localEntry = (Map.Entry)localUnmodifiableIterator.next();
            this.key = localEntry.getKey();
            this.valueIterator = ((ImmutableCollection)localEntry.getValue()).iterator();
          }
          return Maps.immutableEntry(this.key, this.valueIterator.next());
        }
      };
    }

    boolean isPartialView()
    {
      return this.multimap.isPartialView();
    }

    public int size()
    {
      return this.multimap.size();
    }

    public boolean contains(Object paramObject)
    {
      if ((paramObject instanceof Map.Entry))
      {
        Map.Entry localEntry = (Map.Entry)paramObject;
        return this.multimap.containsEntry(localEntry.getKey(), localEntry.getValue());
      }
      return false;
    }
  }

  @GwtIncompatible("java serialization is not supported")
  static class FieldSettersHolder
  {
    static final Serialization.FieldSetter MAP_FIELD_SETTER = Serialization.getFieldSetter(ImmutableMultimap.class, "map");
    static final Serialization.FieldSetter SIZE_FIELD_SETTER = Serialization.getFieldSetter(ImmutableMultimap.class, "size");
  }

  public static class Builder
  {
    Multimap builderMultimap = new ImmutableMultimap.BuilderMultimap();
    Comparator keyComparator;
    Comparator valueComparator;

    public Builder put(Object paramObject1, Object paramObject2)
    {
      this.builderMultimap.put(Preconditions.checkNotNull(paramObject1), Preconditions.checkNotNull(paramObject2));
      return this;
    }

    public Builder put(Map.Entry paramEntry)
    {
      this.builderMultimap.put(Preconditions.checkNotNull(paramEntry.getKey()), Preconditions.checkNotNull(paramEntry.getValue()));
      return this;
    }

    public Builder putAll(Object paramObject, Iterable paramIterable)
    {
      Collection localCollection = this.builderMultimap.get(Preconditions.checkNotNull(paramObject));
      Iterator localIterator = paramIterable.iterator();
      while (localIterator.hasNext())
      {
        Object localObject = localIterator.next();
        localCollection.add(Preconditions.checkNotNull(localObject));
      }
      return this;
    }

    public Builder putAll(Object paramObject, Object[] paramArrayOfObject)
    {
      return putAll(paramObject, Arrays.asList(paramArrayOfObject));
    }

    public Builder putAll(Multimap paramMultimap)
    {
      Iterator localIterator = paramMultimap.asMap().entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        putAll(localEntry.getKey(), (Iterable)localEntry.getValue());
      }
      return this;
    }

    @Beta
    public Builder orderKeysBy(Comparator paramComparator)
    {
      this.keyComparator = ((Comparator)Preconditions.checkNotNull(paramComparator));
      return this;
    }

    @Beta
    public Builder orderValuesBy(Comparator paramComparator)
    {
      this.valueComparator = ((Comparator)Preconditions.checkNotNull(paramComparator));
      return this;
    }

    public ImmutableMultimap build()
    {
      Object localObject1;
      Object localObject2;
      Object localObject3;
      if (this.valueComparator != null)
      {
        localObject1 = this.builderMultimap.asMap().values().iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (Collection)((Iterator)localObject1).next();
          localObject3 = (List)localObject2;
          Collections.sort((List)localObject3, this.valueComparator);
        }
      }
      if (this.keyComparator != null)
      {
        localObject1 = new ImmutableMultimap.BuilderMultimap();
        localObject2 = Lists.newArrayList(this.builderMultimap.asMap().entrySet());
        Collections.sort((List)localObject2, Ordering.from(this.keyComparator).onResultOf(new Function()
        {
          public Object apply(Map.Entry paramAnonymousEntry)
          {
            return paramAnonymousEntry.getKey();
          }
        }));
        localObject3 = ((List)localObject2).iterator();
        while (((Iterator)localObject3).hasNext())
        {
          Map.Entry localEntry = (Map.Entry)((Iterator)localObject3).next();
          ((Multimap)localObject1).putAll(localEntry.getKey(), (Iterable)localEntry.getValue());
        }
        this.builderMultimap = ((Multimap)localObject1);
      }
      return ImmutableMultimap.copyOf(this.builderMultimap);
    }
  }

  private static class BuilderMultimap extends AbstractMultimap
  {
    private static final long serialVersionUID = 0L;

    BuilderMultimap()
    {
      super();
    }

    Collection createCollection()
    {
      return Lists.newArrayList();
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.ImmutableMultimap
 * JD-Core Version:    0.6.2
 */