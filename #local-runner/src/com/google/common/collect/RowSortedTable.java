package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import java.util.SortedMap;
import java.util.SortedSet;

@GwtCompatible
@Beta
public abstract interface RowSortedTable extends Table
{
  public abstract SortedSet rowKeySet();

  public abstract SortedMap rowMap();
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.RowSortedTable
 * JD-Core Version:    0.6.2
 */