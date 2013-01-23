package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

@GwtCompatible
final class BstRangeOps
{
  public static long totalInRange(BstAggregate paramBstAggregate, GeneralRange paramGeneralRange, BstNode paramBstNode)
  {
    Preconditions.checkNotNull(paramBstAggregate);
    Preconditions.checkNotNull(paramGeneralRange);
    if ((paramBstNode == null) || (paramGeneralRange.isEmpty()))
      return 0L;
    long l = paramBstAggregate.treeValue(paramBstNode);
    if (paramGeneralRange.hasLowerBound())
      l -= totalBeyondRangeToSide(paramBstAggregate, paramGeneralRange, BstSide.LEFT, paramBstNode);
    if (paramGeneralRange.hasUpperBound())
      l -= totalBeyondRangeToSide(paramBstAggregate, paramGeneralRange, BstSide.RIGHT, paramBstNode);
    return l;
  }

  private static long totalBeyondRangeToSide(BstAggregate paramBstAggregate, GeneralRange paramGeneralRange, BstSide paramBstSide, BstNode paramBstNode)
  {
    long l = 0L;
    while (paramBstNode != null)
      if (beyond(paramGeneralRange, paramBstNode.getKey(), paramBstSide))
      {
        l += paramBstAggregate.entryValue(paramBstNode);
        l += paramBstAggregate.treeValue(paramBstNode.childOrNull(paramBstSide));
        paramBstNode = paramBstNode.childOrNull(paramBstSide.other());
      }
      else
      {
        paramBstNode = paramBstNode.childOrNull(paramBstSide);
      }
    return l;
  }

  public static BstNode minusRange(GeneralRange paramGeneralRange, BstBalancePolicy paramBstBalancePolicy, BstNodeFactory paramBstNodeFactory, BstNode paramBstNode)
  {
    Preconditions.checkNotNull(paramGeneralRange);
    Preconditions.checkNotNull(paramBstBalancePolicy);
    Preconditions.checkNotNull(paramBstNodeFactory);
    BstNode localBstNode1 = paramGeneralRange.hasUpperBound() ? subTreeBeyondRangeToSide(paramGeneralRange, paramBstBalancePolicy, paramBstNodeFactory, BstSide.RIGHT, paramBstNode) : null;
    BstNode localBstNode2 = paramGeneralRange.hasLowerBound() ? subTreeBeyondRangeToSide(paramGeneralRange, paramBstBalancePolicy, paramBstNodeFactory, BstSide.LEFT, paramBstNode) : null;
    return paramBstBalancePolicy.combine(paramBstNodeFactory, localBstNode2, localBstNode1);
  }

  private static BstNode subTreeBeyondRangeToSide(GeneralRange paramGeneralRange, BstBalancePolicy paramBstBalancePolicy, BstNodeFactory paramBstNodeFactory, BstSide paramBstSide, BstNode paramBstNode)
  {
    if (paramBstNode == null)
      return null;
    if (beyond(paramGeneralRange, paramBstNode.getKey(), paramBstSide))
    {
      BstNode localBstNode1 = paramBstNode.childOrNull(BstSide.LEFT);
      BstNode localBstNode2 = paramBstNode.childOrNull(BstSide.RIGHT);
      switch (1.$SwitchMap$com$google$common$collect$BstSide[paramBstSide.ordinal()])
      {
      case 1:
        localBstNode2 = subTreeBeyondRangeToSide(paramGeneralRange, paramBstBalancePolicy, paramBstNodeFactory, BstSide.LEFT, localBstNode2);
        break;
      case 2:
        localBstNode1 = subTreeBeyondRangeToSide(paramGeneralRange, paramBstBalancePolicy, paramBstNodeFactory, BstSide.RIGHT, localBstNode1);
        break;
      default:
        throw new AssertionError();
      }
      return paramBstBalancePolicy.balance(paramBstNodeFactory, paramBstNode, localBstNode1, localBstNode2);
    }
    return subTreeBeyondRangeToSide(paramGeneralRange, paramBstBalancePolicy, paramBstNodeFactory, paramBstSide, paramBstNode.childOrNull(paramBstSide));
  }

  public static BstPath furthestPath(GeneralRange paramGeneralRange, BstSide paramBstSide, BstPathFactory paramBstPathFactory, BstNode paramBstNode)
  {
    Preconditions.checkNotNull(paramGeneralRange);
    Preconditions.checkNotNull(paramBstPathFactory);
    Preconditions.checkNotNull(paramBstSide);
    if (paramBstNode == null)
      return null;
    BstPath localBstPath = paramBstPathFactory.initialPath(paramBstNode);
    return furthestPath(paramGeneralRange, paramBstSide, paramBstPathFactory, localBstPath);
  }

  private static BstPath furthestPath(GeneralRange paramGeneralRange, BstSide paramBstSide, BstPathFactory paramBstPathFactory, BstPath paramBstPath)
  {
    BstNode localBstNode = paramBstPath.getTip();
    Object localObject = localBstNode.getKey();
    if (beyond(paramGeneralRange, localObject, paramBstSide))
    {
      if (localBstNode.hasChild(paramBstSide.other()))
      {
        paramBstPath = paramBstPathFactory.extension(paramBstPath, paramBstSide.other());
        return furthestPath(paramGeneralRange, paramBstSide, paramBstPathFactory, paramBstPath);
      }
      return null;
    }
    if (localBstNode.hasChild(paramBstSide))
    {
      BstPath localBstPath = paramBstPathFactory.extension(paramBstPath, paramBstSide);
      localBstPath = furthestPath(paramGeneralRange, paramBstSide, paramBstPathFactory, localBstPath);
      if (localBstPath != null)
        return localBstPath;
    }
    return beyond(paramGeneralRange, localObject, paramBstSide.other()) ? null : paramBstPath;
  }

  public static boolean beyond(GeneralRange paramGeneralRange, Object paramObject, BstSide paramBstSide)
  {
    Preconditions.checkNotNull(paramGeneralRange);
    switch (1.$SwitchMap$com$google$common$collect$BstSide[paramBstSide.ordinal()])
    {
    case 1:
      return paramGeneralRange.tooLow(paramObject);
    case 2:
      return paramGeneralRange.tooHigh(paramObject);
    }
    throw new AssertionError();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.BstRangeOps
 * JD-Core Version:    0.6.2
 */