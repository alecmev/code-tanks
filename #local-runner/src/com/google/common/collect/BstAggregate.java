package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible
abstract interface BstAggregate
{
  public abstract long treeValue(BstNode paramBstNode);

  public abstract int entryValue(BstNode paramBstNode);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.BstAggregate
 * JD-Core Version:    0.6.2
 */