package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@GwtCompatible
abstract class RegularImmutableTable extends ImmutableTable
{
  private final ImmutableSet cellSet;
  private static final Function GET_VALUE_FUNCTION = new Function()
  {
    public Object apply(Table.Cell paramAnonymousCell)
    {
      return paramAnonymousCell.getValue();
    }
  };
  private volatile transient ImmutableList valueList;

  private RegularImmutableTable(ImmutableSet paramImmutableSet)
  {
    this.cellSet = paramImmutableSet;
  }

  private Function getValueFunction()
  {
    return GET_VALUE_FUNCTION;
  }

  public final ImmutableCollection values()
  {
    ImmutableList localImmutableList = this.valueList;
    if (localImmutableList == null)
      this.valueList = (localImmutableList = ImmutableList.copyOf(Iterables.transform(cellSet(), getValueFunction())));
    return localImmutableList;
  }

  public final int size()
  {
    return cellSet().size();
  }

  public final boolean containsValue(Object paramObject)
  {
    return values().contains(paramObject);
  }

  public final boolean isEmpty()
  {
    return false;
  }

  public final ImmutableSet cellSet()
  {
    return this.cellSet;
  }

  static final RegularImmutableTable forCells(List paramList, Comparator paramComparator1, final Comparator paramComparator2)
  {
    Preconditions.checkNotNull(paramList);
    if ((paramComparator1 != null) || (paramComparator2 != null))
    {
      Comparator local2 = new Comparator()
      {
        public int compare(Table.Cell paramAnonymousCell1, Table.Cell paramAnonymousCell2)
        {
          int i = this.val$rowComparator == null ? 0 : this.val$rowComparator.compare(paramAnonymousCell1.getRowKey(), paramAnonymousCell2.getRowKey());
          if (i != 0)
            return i;
          return paramComparator2 == null ? 0 : paramComparator2.compare(paramAnonymousCell1.getColumnKey(), paramAnonymousCell2.getColumnKey());
        }
      };
      Collections.sort(paramList, local2);
    }
    return forCellsInternal(paramList, paramComparator1, paramComparator2);
  }

  static final RegularImmutableTable forCells(Iterable paramIterable)
  {
    return forCellsInternal(paramIterable, null, null);
  }

  private static final RegularImmutableTable forCellsInternal(Iterable paramIterable, Comparator paramComparator1, Comparator paramComparator2)
  {
    ImmutableSet.Builder localBuilder1 = ImmutableSet.builder();
    ImmutableSet.Builder localBuilder2 = ImmutableSet.builder();
    ImmutableSet.Builder localBuilder3 = ImmutableSet.builder();
    Object localObject1 = paramIterable.iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (Table.Cell)((Iterator)localObject1).next();
      localBuilder1.add(localObject2);
      localBuilder2.add(((Table.Cell)localObject2).getRowKey());
      localBuilder3.add(((Table.Cell)localObject2).getColumnKey());
    }
    localObject1 = localBuilder1.build();
    Object localObject2 = localBuilder2.build();
    if (paramComparator1 != null)
    {
      localObject3 = Lists.newArrayList((Iterable)localObject2);
      Collections.sort((List)localObject3, paramComparator1);
      localObject2 = ImmutableSet.copyOf((Collection)localObject3);
    }
    Object localObject3 = localBuilder3.build();
    if (paramComparator2 != null)
    {
      ArrayList localArrayList = Lists.newArrayList((Iterable)localObject3);
      Collections.sort(localArrayList, paramComparator2);
      localObject3 = ImmutableSet.copyOf(localArrayList);
    }
    return ((ImmutableSet)localObject1).size() > ((ImmutableSet)localObject2).size() * ((ImmutableSet)localObject3).size() / 2 ? new DenseImmutableTable((ImmutableSet)localObject1, (ImmutableSet)localObject2, (ImmutableSet)localObject3) : new SparseImmutableTable((ImmutableSet)localObject1, (ImmutableSet)localObject2, (ImmutableSet)localObject3);
  }

  @VisibleForTesting
  static final class DenseImmutableTable extends RegularImmutableTable
  {
    private final ImmutableBiMap rowKeyToIndex;
    private final ImmutableBiMap columnKeyToIndex;
    private final Object[][] values;
    private volatile transient ImmutableMap columnMap;
    private volatile transient ImmutableMap rowMap;

    private static ImmutableBiMap makeIndex(ImmutableSet paramImmutableSet)
    {
      ImmutableBiMap.Builder localBuilder = ImmutableBiMap.builder();
      int i = 0;
      Iterator localIterator = paramImmutableSet.iterator();
      while (localIterator.hasNext())
      {
        Object localObject = localIterator.next();
        localBuilder.put(localObject, Integer.valueOf(i));
        i++;
      }
      return localBuilder.build();
    }

    DenseImmutableTable(ImmutableSet paramImmutableSet1, ImmutableSet paramImmutableSet2, ImmutableSet paramImmutableSet3)
    {
      super(null);
      Object[][] arrayOfObject = (Object[][])new Object[paramImmutableSet2.size()][paramImmutableSet3.size()];
      this.values = arrayOfObject;
      this.rowKeyToIndex = makeIndex(paramImmutableSet2);
      this.columnKeyToIndex = makeIndex(paramImmutableSet3);
      Iterator localIterator = paramImmutableSet1.iterator();
      while (localIterator.hasNext())
      {
        Table.Cell localCell = (Table.Cell)localIterator.next();
        Object localObject1 = localCell.getRowKey();
        Object localObject2 = localCell.getColumnKey();
        int i = ((Integer)this.rowKeyToIndex.get(localObject1)).intValue();
        int j = ((Integer)this.columnKeyToIndex.get(localObject2)).intValue();
        Object localObject3 = this.values[i][j];
        Preconditions.checkArgument(localObject3 == null, "duplicate key: (%s, %s)", new Object[] { localObject1, localObject2 });
        this.values[i][j] = localCell.getValue();
      }
    }

    public ImmutableMap column(Object paramObject)
    {
      Preconditions.checkNotNull(paramObject);
      Integer localInteger = (Integer)this.columnKeyToIndex.get(paramObject);
      if (localInteger == null)
        return ImmutableMap.of();
      int i = localInteger.intValue();
      ImmutableMap.Builder localBuilder = ImmutableMap.builder();
      for (int j = 0; j < this.values.length; j++)
      {
        Object localObject = this.values[j][i];
        if (localObject != null)
          localBuilder.put(this.rowKeyToIndex.inverse().get(Integer.valueOf(j)), localObject);
      }
      return localBuilder.build();
    }

    public ImmutableSet columnKeySet()
    {
      return this.columnKeyToIndex.keySet();
    }

    private ImmutableMap makeColumnMap()
    {
      ImmutableMap.Builder localBuilder1 = ImmutableMap.builder();
      for (int i = 0; i < this.columnKeyToIndex.size(); i++)
      {
        ImmutableMap.Builder localBuilder2 = ImmutableMap.builder();
        for (int j = 0; j < this.rowKeyToIndex.size(); j++)
        {
          Object localObject = this.values[j][i];
          if (localObject != null)
            localBuilder2.put(this.rowKeyToIndex.inverse().get(Integer.valueOf(j)), localObject);
        }
        localBuilder1.put(this.columnKeyToIndex.inverse().get(Integer.valueOf(i)), localBuilder2.build());
      }
      return localBuilder1.build();
    }

    public ImmutableMap columnMap()
    {
      ImmutableMap localImmutableMap = this.columnMap;
      if (localImmutableMap == null)
        this.columnMap = (localImmutableMap = makeColumnMap());
      return localImmutableMap;
    }

    public boolean contains(Object paramObject1, Object paramObject2)
    {
      return get(paramObject1, paramObject2) != null;
    }

    public boolean containsColumn(Object paramObject)
    {
      return this.columnKeyToIndex.containsKey(paramObject);
    }

    public boolean containsRow(Object paramObject)
    {
      return this.rowKeyToIndex.containsKey(paramObject);
    }

    public Object get(Object paramObject1, Object paramObject2)
    {
      Integer localInteger1 = (Integer)this.rowKeyToIndex.get(paramObject1);
      Integer localInteger2 = (Integer)this.columnKeyToIndex.get(paramObject2);
      return (localInteger1 == null) || (localInteger2 == null) ? null : this.values[localInteger1.intValue()][localInteger2.intValue()];
    }

    public ImmutableMap row(Object paramObject)
    {
      Preconditions.checkNotNull(paramObject);
      Integer localInteger = (Integer)this.rowKeyToIndex.get(paramObject);
      if (localInteger == null)
        return ImmutableMap.of();
      ImmutableMap.Builder localBuilder = ImmutableMap.builder();
      Object[] arrayOfObject = this.values[localInteger.intValue()];
      for (int i = 0; i < arrayOfObject.length; i++)
      {
        Object localObject = arrayOfObject[i];
        if (localObject != null)
          localBuilder.put(this.columnKeyToIndex.inverse().get(Integer.valueOf(i)), localObject);
      }
      return localBuilder.build();
    }

    public ImmutableSet rowKeySet()
    {
      return this.rowKeyToIndex.keySet();
    }

    private ImmutableMap makeRowMap()
    {
      ImmutableMap.Builder localBuilder1 = ImmutableMap.builder();
      for (int i = 0; i < this.values.length; i++)
      {
        Object[] arrayOfObject = this.values[i];
        ImmutableMap.Builder localBuilder2 = ImmutableMap.builder();
        for (int j = 0; j < arrayOfObject.length; j++)
        {
          Object localObject = arrayOfObject[j];
          if (localObject != null)
            localBuilder2.put(this.columnKeyToIndex.inverse().get(Integer.valueOf(j)), localObject);
        }
        localBuilder1.put(this.rowKeyToIndex.inverse().get(Integer.valueOf(i)), localBuilder2.build());
      }
      return localBuilder1.build();
    }

    public ImmutableMap rowMap()
    {
      ImmutableMap localImmutableMap = this.rowMap;
      if (localImmutableMap == null)
        this.rowMap = (localImmutableMap = makeRowMap());
      return localImmutableMap;
    }
  }

  @VisibleForTesting
  static final class SparseImmutableTable extends RegularImmutableTable
  {
    private final ImmutableMap rowMap;
    private final ImmutableMap columnMap;

    private static final Map makeIndexBuilder(ImmutableSet paramImmutableSet)
    {
      LinkedHashMap localLinkedHashMap = Maps.newLinkedHashMap();
      Iterator localIterator = paramImmutableSet.iterator();
      while (localIterator.hasNext())
      {
        Object localObject = localIterator.next();
        localLinkedHashMap.put(localObject, ImmutableMap.builder());
      }
      return localLinkedHashMap;
    }

    private static final ImmutableMap buildIndex(Map paramMap)
    {
      return ImmutableMap.copyOf(Maps.transformValues(paramMap, new Function()
      {
        public Map apply(ImmutableMap.Builder paramAnonymousBuilder)
        {
          return paramAnonymousBuilder.build();
        }
      }));
    }

    SparseImmutableTable(ImmutableSet paramImmutableSet1, ImmutableSet paramImmutableSet2, ImmutableSet paramImmutableSet3)
    {
      super(null);
      Map localMap1 = makeIndexBuilder(paramImmutableSet2);
      Map localMap2 = makeIndexBuilder(paramImmutableSet3);
      Iterator localIterator = paramImmutableSet1.iterator();
      while (localIterator.hasNext())
      {
        Table.Cell localCell = (Table.Cell)localIterator.next();
        Object localObject1 = localCell.getRowKey();
        Object localObject2 = localCell.getColumnKey();
        Object localObject3 = localCell.getValue();
        ((ImmutableMap.Builder)localMap1.get(localObject1)).put(localObject2, localObject3);
        ((ImmutableMap.Builder)localMap2.get(localObject2)).put(localObject1, localObject3);
      }
      this.rowMap = buildIndex(localMap1);
      this.columnMap = buildIndex(localMap2);
    }

    public ImmutableMap column(Object paramObject)
    {
      Preconditions.checkNotNull(paramObject);
      return (ImmutableMap)Objects.firstNonNull((ImmutableMap)this.columnMap.get(paramObject), ImmutableMap.of());
    }

    public ImmutableSet columnKeySet()
    {
      return this.columnMap.keySet();
    }

    public ImmutableMap columnMap()
    {
      return this.columnMap;
    }

    public ImmutableMap row(Object paramObject)
    {
      Preconditions.checkNotNull(paramObject);
      return (ImmutableMap)Objects.firstNonNull((ImmutableMap)this.rowMap.get(paramObject), ImmutableMap.of());
    }

    public ImmutableSet rowKeySet()
    {
      return this.rowMap.keySet();
    }

    public ImmutableMap rowMap()
    {
      return this.rowMap;
    }

    public boolean contains(Object paramObject1, Object paramObject2)
    {
      Map localMap = (Map)this.rowMap.get(paramObject1);
      return (localMap != null) && (localMap.containsKey(paramObject2));
    }

    public boolean containsColumn(Object paramObject)
    {
      return this.columnMap.containsKey(paramObject);
    }

    public boolean containsRow(Object paramObject)
    {
      return this.rowMap.containsKey(paramObject);
    }

    public Object get(Object paramObject1, Object paramObject2)
    {
      Map localMap = (Map)this.rowMap.get(paramObject1);
      return localMap == null ? null : localMap.get(paramObject2);
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.RegularImmutableTable
 * JD-Core Version:    0.6.2
 */