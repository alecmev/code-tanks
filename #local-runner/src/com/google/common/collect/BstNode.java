package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.Comparator;

@GwtCompatible
class BstNode
{
  private final Object key;
  private final BstNode left;
  private final BstNode right;

  BstNode(Object paramObject, BstNode paramBstNode1, BstNode paramBstNode2)
  {
    this.key = paramObject;
    this.left = paramBstNode1;
    this.right = paramBstNode2;
  }

  public final Object getKey()
  {
    return this.key;
  }

  public final BstNode childOrNull(BstSide paramBstSide)
  {
    switch (1.$SwitchMap$com$google$common$collect$BstSide[paramBstSide.ordinal()])
    {
    case 1:
      return this.left;
    case 2:
      return this.right;
    }
    throw new AssertionError();
  }

  public final boolean hasChild(BstSide paramBstSide)
  {
    return childOrNull(paramBstSide) != null;
  }

  public final BstNode getChild(BstSide paramBstSide)
  {
    BstNode localBstNode = childOrNull(paramBstSide);
    Preconditions.checkState(localBstNode != null);
    return localBstNode;
  }

  protected final boolean orderingInvariantHolds(Comparator paramComparator)
  {
    Preconditions.checkNotNull(paramComparator);
    boolean bool = true;
    if (hasChild(BstSide.LEFT))
      bool &= paramComparator.compare(getChild(BstSide.LEFT).getKey(), this.key) < 0;
    if (hasChild(BstSide.RIGHT))
      bool &= paramComparator.compare(getChild(BstSide.RIGHT).getKey(), this.key) > 0;
    return bool;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.BstNode
 * JD-Core Version:    0.6.2
 */