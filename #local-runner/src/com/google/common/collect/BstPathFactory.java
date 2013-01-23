package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible
abstract interface BstPathFactory
{
  public abstract BstPath extension(BstPath paramBstPath, BstSide paramBstSide);

  public abstract BstPath initialPath(BstNode paramBstNode);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.BstPathFactory
 * JD-Core Version:    0.6.2
 */