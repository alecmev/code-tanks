package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible
abstract class BstNodeFactory
{
  public abstract BstNode createNode(BstNode paramBstNode1, BstNode paramBstNode2, BstNode paramBstNode3);

  public final BstNode createLeaf(BstNode paramBstNode)
  {
    return createNode(paramBstNode, null, null);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.BstNodeFactory
 * JD-Core Version:    0.6.2
 */