package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@Beta
public final class ArrayTable
  implements Table, Serializable
{
  private final ImmutableList rowList;
  private final ImmutableList columnList;
  private final ImmutableMap rowKeyToIndex;
  private final ImmutableMap columnKeyToIndex;
  private final Object[][] array;
  private transient CellSet cellSet;
  private transient ColumnMap columnMap;
  private transient RowMap rowMap;
  private transient Collection values;
  private static final long serialVersionUID = 0L;

  public static ArrayTable create(Iterable paramIterable1, Iterable paramIterable2)
  {
    return new ArrayTable(paramIterable1, paramIterable2);
  }

  public static ArrayTable create(Table paramTable)
  {
    return new ArrayTable(paramTable);
  }

  public static ArrayTable create(ArrayTable paramArrayTable)
  {
    return new ArrayTable(paramArrayTable);
  }

  private ArrayTable(Iterable paramIterable1, Iterable paramIterable2)
  {
    this.rowList = ImmutableList.copyOf(paramIterable1);
    this.columnList = ImmutableList.copyOf(paramIterable2);
    Preconditions.checkArgument(!this.rowList.isEmpty());
    Preconditions.checkArgument(!this.columnList.isEmpty());
    ImmutableMap.Builder localBuilder1 = ImmutableMap.builder();
    for (int i = 0; i < this.rowList.size(); i++)
      localBuilder1.put(this.rowList.get(i), Integer.valueOf(i));
    this.rowKeyToIndex = localBuilder1.build();
    ImmutableMap.Builder localBuilder2 = ImmutableMap.builder();
    for (int j = 0; j < this.columnList.size(); j++)
      localBuilder2.put(this.columnList.get(j), Integer.valueOf(j));
    this.columnKeyToIndex = localBuilder2.build();
    Object[][] arrayOfObject = (Object[][])new Object[this.rowList.size()][this.columnList.size()];
    this.array = arrayOfObject;
  }

  private ArrayTable(Table paramTable)
  {
    this(paramTable.rowKeySet(), paramTable.columnKeySet());
    putAll(paramTable);
  }

  private ArrayTable(ArrayTable paramArrayTable)
  {
    this.rowList = paramArrayTable.rowList;
    this.columnList = paramArrayTable.columnList;
    this.rowKeyToIndex = paramArrayTable.rowKeyToIndex;
    this.columnKeyToIndex = paramArrayTable.columnKeyToIndex;
    Object[][] arrayOfObject = (Object[][])new Object[this.rowList.size()][this.columnList.size()];
    this.array = arrayOfObject;
    for (int i = 0; i < this.rowList.size(); i++)
      System.arraycopy(paramArrayTable.array[i], 0, arrayOfObject[i], 0, paramArrayTable.array[i].length);
  }

  public ImmutableList rowKeyList()
  {
    return this.rowList;
  }

  public ImmutableList columnKeyList()
  {
    return this.columnList;
  }

  public Object at(int paramInt1, int paramInt2)
  {
    return this.array[paramInt1][paramInt2];
  }

  public Object set(int paramInt1, int paramInt2, Object paramObject)
  {
    Object localObject = this.array[paramInt1][paramInt2];
    this.array[paramInt1][paramInt2] = paramObject;
    return localObject;
  }

  public Object[][] toArray(Class paramClass)
  {
    Object[][] arrayOfObject = (Object[][])Array.newInstance(paramClass, new int[] { this.rowList.size(), this.columnList.size() });
    for (int i = 0; i < this.rowList.size(); i++)
      System.arraycopy(this.array[i], 0, arrayOfObject[i], 0, this.array[i].length);
    return arrayOfObject;
  }

  @Deprecated
  public void clear()
  {
    throw new UnsupportedOperationException();
  }

  public void eraseAll()
  {
    for (Object[] arrayOfObject1 : this.array)
      Arrays.fill(arrayOfObject1, null);
  }

  public boolean contains(Object paramObject1, Object paramObject2)
  {
    return (containsRow(paramObject1)) && (containsColumn(paramObject2));
  }

  public boolean containsColumn(Object paramObject)
  {
    return this.columnKeyToIndex.containsKey(paramObject);
  }

  public boolean containsRow(Object paramObject)
  {
    return this.rowKeyToIndex.containsKey(paramObject);
  }

  public boolean containsValue(Object paramObject)
  {
    for (Object[] arrayOfObject1 : this.array)
      for (Object localObject : arrayOfObject1)
        if (Objects.equal(paramObject, localObject))
          return true;
    return false;
  }

  public Object get(Object paramObject1, Object paramObject2)
  {
    Integer localInteger1 = (Integer)this.rowKeyToIndex.get(paramObject1);
    Integer localInteger2 = (Integer)this.columnKeyToIndex.get(paramObject2);
    return getIndexed(localInteger1, localInteger2);
  }

  private Object getIndexed(Integer paramInteger1, Integer paramInteger2)
  {
    return (paramInteger1 == null) || (paramInteger2 == null) ? null : this.array[paramInteger1.intValue()][paramInteger2.intValue()];
  }

  public boolean isEmpty()
  {
    return false;
  }

  public Object put(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    Preconditions.checkNotNull(paramObject1);
    Preconditions.checkNotNull(paramObject2);
    Integer localInteger1 = (Integer)this.rowKeyToIndex.get(paramObject1);
    Preconditions.checkArgument(localInteger1 != null, "Row %s not in %s", new Object[] { paramObject1, this.rowList });
    Integer localInteger2 = (Integer)this.columnKeyToIndex.get(paramObject2);
    Preconditions.checkArgument(localInteger2 != null, "Column %s not in %s", new Object[] { paramObject2, this.columnList });
    return set(localInteger1.intValue(), localInteger2.intValue(), paramObject3);
  }

  public void putAll(Table paramTable)
  {
    Iterator localIterator = paramTable.cellSet().iterator();
    while (localIterator.hasNext())
    {
      Table.Cell localCell = (Table.Cell)localIterator.next();
      put(localCell.getRowKey(), localCell.getColumnKey(), localCell.getValue());
    }
  }

  @Deprecated
  public Object remove(Object paramObject1, Object paramObject2)
  {
    throw new UnsupportedOperationException();
  }

  public Object erase(Object paramObject1, Object paramObject2)
  {
    Integer localInteger1 = (Integer)this.rowKeyToIndex.get(paramObject1);
    Integer localInteger2 = (Integer)this.columnKeyToIndex.get(paramObject2);
    if ((localInteger1 == null) || (localInteger2 == null))
      return null;
    return set(localInteger1.intValue(), localInteger2.intValue(), null);
  }

  public int size()
  {
    return this.rowList.size() * this.columnList.size();
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof Serializable))
    {
      Table localTable = (Serializable)paramObject;
      return cellSet().equals(localTable.cellSet());
    }
    return false;
  }

  public int hashCode()
  {
    return cellSet().hashCode();
  }

  public String toString()
  {
    return rowMap().toString();
  }

  public Set cellSet()
  {
    CellSet localCellSet = this.cellSet;
    return localCellSet == null ? (this.cellSet = new CellSet(null)) : localCellSet;
  }

  public Map column(Object paramObject)
  {
    Preconditions.checkNotNull(paramObject);
    Integer localInteger = (Integer)this.columnKeyToIndex.get(paramObject);
    return localInteger == null ? ImmutableMap.of() : new Column(localInteger.intValue());
  }

  public ImmutableSet columnKeySet()
  {
    return this.columnKeyToIndex.keySet();
  }

  public Map columnMap()
  {
    ColumnMap localColumnMap = this.columnMap;
    return localColumnMap == null ? (this.columnMap = new ColumnMap(null)) : localColumnMap;
  }

  public Map row(Object paramObject)
  {
    Preconditions.checkNotNull(paramObject);
    Integer localInteger = (Integer)this.rowKeyToIndex.get(paramObject);
    return localInteger == null ? ImmutableMap.of() : new Row(localInteger.intValue());
  }

  public ImmutableSet rowKeySet()
  {
    return this.rowKeyToIndex.keySet();
  }

  public Map rowMap()
  {
    RowMap localRowMap = this.rowMap;
    return localRowMap == null ? (this.rowMap = new RowMap(null)) : localRowMap;
  }

  public Collection values()
  {
    Collection localCollection = this.values;
    return localCollection == null ? (this.values = new Values(null)) : localCollection;
  }

  private class Values extends AbstractCollection
  {
    private Values()
    {
    }

    public Iterator iterator()
    {
      return new AbstractIndexedListIterator(size())
      {
        protected Object get(int paramAnonymousInt)
        {
          int i = paramAnonymousInt / ArrayTable.this.columnList.size();
          int j = paramAnonymousInt % ArrayTable.this.columnList.size();
          return ArrayTable.this.array[i][j];
        }
      };
    }

    public int size()
    {
      return ArrayTable.this.size();
    }

    public boolean contains(Object paramObject)
    {
      return ArrayTable.this.containsValue(paramObject);
    }
  }

  private class RowMapEntrySet extends AbstractSet
  {
    private RowMapEntrySet()
    {
    }

    public Iterator iterator()
    {
      return new AbstractIndexedListIterator(size())
      {
        protected Map.Entry get(int paramAnonymousInt)
        {
          return Maps.immutableEntry(ArrayTable.this.rowList.get(paramAnonymousInt), new ArrayTable.Row(ArrayTable.this, paramAnonymousInt));
        }
      };
    }

    public int size()
    {
      return ArrayTable.this.rowList.size();
    }
  }

  private class RowMap extends AbstractMap
  {
    transient ArrayTable.RowMapEntrySet entrySet;

    private RowMap()
    {
    }

    public Set entrySet()
    {
      ArrayTable.RowMapEntrySet localRowMapEntrySet = this.entrySet;
      return localRowMapEntrySet == null ? (this.entrySet = new ArrayTable.RowMapEntrySet(ArrayTable.this, null)) : localRowMapEntrySet;
    }

    public Map get(Object paramObject)
    {
      Integer localInteger = (Integer)ArrayTable.this.rowKeyToIndex.get(paramObject);
      return localInteger == null ? null : new ArrayTable.Row(ArrayTable.this, localInteger.intValue());
    }

    public boolean containsKey(Object paramObject)
    {
      return ArrayTable.this.containsRow(paramObject);
    }

    public Set keySet()
    {
      return ArrayTable.this.rowKeySet();
    }

    public Map remove(Object paramObject)
    {
      throw new UnsupportedOperationException();
    }
  }

  private class RowEntrySet extends AbstractSet
  {
    final int rowIndex;

    RowEntrySet(int arg2)
    {
      int i;
      this.rowIndex = i;
    }

    public Iterator iterator()
    {
      return new AbstractIndexedListIterator(size())
      {
        protected Map.Entry get(final int paramAnonymousInt)
        {
          return new AbstractMapEntry()
          {
            public Object getKey()
            {
              return ArrayTable.this.columnList.get(paramAnonymousInt);
            }

            public Object getValue()
            {
              return ArrayTable.this.array[ArrayTable.RowEntrySet.this.rowIndex][paramAnonymousInt];
            }

            public Object setValue(Object paramAnonymous2Object)
            {
              return ArrayTable.this.set(ArrayTable.RowEntrySet.this.rowIndex, paramAnonymousInt, paramAnonymous2Object);
            }
          };
        }
      };
    }

    public int size()
    {
      return ArrayTable.this.columnList.size();
    }
  }

  private class Row extends AbstractMap
  {
    final int rowIndex;
    ArrayTable.RowEntrySet entrySet;

    Row(int arg2)
    {
      int i;
      this.rowIndex = i;
    }

    public Set entrySet()
    {
      ArrayTable.RowEntrySet localRowEntrySet = this.entrySet;
      return localRowEntrySet == null ? (this.entrySet = new ArrayTable.RowEntrySet(ArrayTable.this, this.rowIndex)) : localRowEntrySet;
    }

    public Object get(Object paramObject)
    {
      Integer localInteger = (Integer)ArrayTable.this.columnKeyToIndex.get(paramObject);
      return ArrayTable.this.getIndexed(Integer.valueOf(this.rowIndex), localInteger);
    }

    public boolean containsKey(Object paramObject)
    {
      return ArrayTable.this.containsColumn(paramObject);
    }

    public Object put(Object paramObject1, Object paramObject2)
    {
      Preconditions.checkNotNull(paramObject1);
      Integer localInteger = (Integer)ArrayTable.this.columnKeyToIndex.get(paramObject1);
      Preconditions.checkArgument(localInteger != null, "Column %s not in %s", new Object[] { paramObject1, ArrayTable.this.columnList });
      return ArrayTable.this.set(this.rowIndex, localInteger.intValue(), paramObject2);
    }

    public Set keySet()
    {
      return ArrayTable.this.columnKeySet();
    }
  }

  private class ColumnMapEntrySet extends AbstractSet
  {
    private ColumnMapEntrySet()
    {
    }

    public Iterator iterator()
    {
      return new AbstractIndexedListIterator(size())
      {
        protected Map.Entry get(int paramAnonymousInt)
        {
          return Maps.immutableEntry(ArrayTable.this.columnList.get(paramAnonymousInt), new ArrayTable.Column(ArrayTable.this, paramAnonymousInt));
        }
      };
    }

    public int size()
    {
      return ArrayTable.this.columnList.size();
    }
  }

  private class ColumnMap extends AbstractMap
  {
    transient ArrayTable.ColumnMapEntrySet entrySet;

    private ColumnMap()
    {
    }

    public Set entrySet()
    {
      ArrayTable.ColumnMapEntrySet localColumnMapEntrySet = this.entrySet;
      return localColumnMapEntrySet == null ? (this.entrySet = new ArrayTable.ColumnMapEntrySet(ArrayTable.this, null)) : localColumnMapEntrySet;
    }

    public Map get(Object paramObject)
    {
      Integer localInteger = (Integer)ArrayTable.this.columnKeyToIndex.get(paramObject);
      return localInteger == null ? null : new ArrayTable.Column(ArrayTable.this, localInteger.intValue());
    }

    public boolean containsKey(Object paramObject)
    {
      return ArrayTable.this.containsColumn(paramObject);
    }

    public Set keySet()
    {
      return ArrayTable.this.columnKeySet();
    }

    public Map remove(Object paramObject)
    {
      throw new UnsupportedOperationException();
    }
  }

  private class ColumnEntrySet extends AbstractSet
  {
    final int columnIndex;

    ColumnEntrySet(int arg2)
    {
      int i;
      this.columnIndex = i;
    }

    public Iterator iterator()
    {
      return new AbstractIndexedListIterator(size())
      {
        protected Map.Entry get(final int paramAnonymousInt)
        {
          return new AbstractMapEntry()
          {
            public Object getKey()
            {
              return ArrayTable.this.rowList.get(paramAnonymousInt);
            }

            public Object getValue()
            {
              return ArrayTable.this.array[paramAnonymousInt][ArrayTable.ColumnEntrySet.this.columnIndex];
            }

            public Object setValue(Object paramAnonymous2Object)
            {
              return ArrayTable.this.set(paramAnonymousInt, ArrayTable.ColumnEntrySet.this.columnIndex, paramAnonymous2Object);
            }
          };
        }
      };
    }

    public int size()
    {
      return ArrayTable.this.rowList.size();
    }
  }

  private class Column extends AbstractMap
  {
    final int columnIndex;
    ArrayTable.ColumnEntrySet entrySet;

    Column(int arg2)
    {
      int i;
      this.columnIndex = i;
    }

    public Set entrySet()
    {
      ArrayTable.ColumnEntrySet localColumnEntrySet = this.entrySet;
      return localColumnEntrySet == null ? (this.entrySet = new ArrayTable.ColumnEntrySet(ArrayTable.this, this.columnIndex)) : localColumnEntrySet;
    }

    public Object get(Object paramObject)
    {
      Integer localInteger = (Integer)ArrayTable.this.rowKeyToIndex.get(paramObject);
      return ArrayTable.this.getIndexed(localInteger, Integer.valueOf(this.columnIndex));
    }

    public boolean containsKey(Object paramObject)
    {
      return ArrayTable.this.rowKeyToIndex.containsKey(paramObject);
    }

    public Object put(Object paramObject1, Object paramObject2)
    {
      Preconditions.checkNotNull(paramObject1);
      Integer localInteger = (Integer)ArrayTable.this.rowKeyToIndex.get(paramObject1);
      Preconditions.checkArgument(localInteger != null, "Row %s not in %s", new Object[] { paramObject1, ArrayTable.this.rowList });
      return ArrayTable.this.set(localInteger.intValue(), this.columnIndex, paramObject2);
    }

    public Set keySet()
    {
      return ArrayTable.this.rowKeySet();
    }
  }

  private class CellSet extends AbstractSet
  {
    private CellSet()
    {
    }

    public Iterator iterator()
    {
      return new AbstractIndexedListIterator(size())
      {
        protected Table.Cell get(final int paramAnonymousInt)
        {
          return new Tables.AbstractCell()
          {
            final int rowIndex = paramAnonymousInt / ArrayTable.this.columnList.size();
            final int columnIndex = paramAnonymousInt % ArrayTable.this.columnList.size();

            public Object getRowKey()
            {
              return ArrayTable.this.rowList.get(this.rowIndex);
            }

            public Object getColumnKey()
            {
              return ArrayTable.this.columnList.get(this.columnIndex);
            }

            public Object getValue()
            {
              return ArrayTable.this.array[this.rowIndex][this.columnIndex];
            }
          };
        }
      };
    }

    public int size()
    {
      return ArrayTable.this.size();
    }

    public boolean contains(Object paramObject)
    {
      if ((paramObject instanceof Table.Cell))
      {
        Table.Cell localCell = (Table.Cell)paramObject;
        Integer localInteger1 = (Integer)ArrayTable.this.rowKeyToIndex.get(localCell.getRowKey());
        Integer localInteger2 = (Integer)ArrayTable.this.columnKeyToIndex.get(localCell.getColumnKey());
        return (localInteger1 != null) && (localInteger2 != null) && (Objects.equal(ArrayTable.this.array[localInteger1.intValue()][localInteger2.intValue()], localCell.getValue()));
      }
      return false;
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.ArrayTable
 * JD-Core Version:    0.6.2
 */