package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible
abstract interface BstBalancePolicy
{
  public abstract BstNode balance(BstNodeFactory paramBstNodeFactory, BstNode paramBstNode1, BstNode paramBstNode2, BstNode paramBstNode3);

  public abstract BstNode combine(BstNodeFactory paramBstNodeFactory, BstNode paramBstNode1, BstNode paramBstNode2);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.BstBalancePolicy
 * JD-Core Version:    0.6.2
 */