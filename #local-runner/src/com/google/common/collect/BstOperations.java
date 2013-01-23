package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.Comparator;

@GwtCompatible
final class BstOperations
{
  public static BstNode seek(Comparator paramComparator, BstNode paramBstNode, Object paramObject)
  {
    Preconditions.checkNotNull(paramComparator);
    if (paramBstNode == null)
      return null;
    int i = paramComparator.compare(paramObject, paramBstNode.getKey());
    if (i == 0)
      return paramBstNode;
    BstSide localBstSide = i < 0 ? BstSide.LEFT : BstSide.RIGHT;
    return seek(paramComparator, paramBstNode.childOrNull(localBstSide), paramObject);
  }

  public static BstMutationResult mutate(Comparator paramComparator, BstMutationRule paramBstMutationRule, BstNode paramBstNode, Object paramObject)
  {
    Preconditions.checkNotNull(paramComparator);
    Preconditions.checkNotNull(paramBstMutationRule);
    if (paramBstNode != null)
    {
      int i = paramComparator.compare(paramObject, paramBstNode.getKey());
      if (i != 0)
      {
        BstSide localBstSide = i < 0 ? BstSide.LEFT : BstSide.RIGHT;
        BstMutationResult localBstMutationResult = mutate(paramComparator, paramBstMutationRule, paramBstNode.childOrNull(localBstSide), paramObject);
        return localBstMutationResult.lift(paramBstNode, localBstSide, paramBstMutationRule.getNodeFactory(), paramBstMutationRule.getBalancePolicy());
      }
    }
    return modify(paramBstNode, paramObject, paramBstMutationRule);
  }

  public static BstMutationResult mutate(BstInOrderPath paramBstInOrderPath, BstMutationRule paramBstMutationRule)
  {
    Preconditions.checkNotNull(paramBstInOrderPath);
    Preconditions.checkNotNull(paramBstMutationRule);
    BstBalancePolicy localBstBalancePolicy = paramBstMutationRule.getBalancePolicy();
    BstNodeFactory localBstNodeFactory = paramBstMutationRule.getNodeFactory();
    BstNode localBstNode = paramBstInOrderPath.getTip();
    Object localObject = localBstNode.getKey();
    BstMutationResult localBstMutationResult = modify(localBstNode, localObject, paramBstMutationRule);
    while (paramBstInOrderPath.hasPrefix())
    {
      BstInOrderPath localBstInOrderPath = (BstInOrderPath)paramBstInOrderPath.getPrefix();
      localBstMutationResult = localBstMutationResult.lift(localBstInOrderPath.getTip(), paramBstInOrderPath.getSideOfExtension(), localBstNodeFactory, localBstBalancePolicy);
      paramBstInOrderPath = localBstInOrderPath;
    }
    return localBstMutationResult;
  }

  private static BstMutationResult modify(BstNode paramBstNode, Object paramObject, BstMutationRule paramBstMutationRule)
  {
    BstBalancePolicy localBstBalancePolicy = paramBstMutationRule.getBalancePolicy();
    BstNodeFactory localBstNodeFactory = paramBstMutationRule.getNodeFactory();
    BstModifier localBstModifier = paramBstMutationRule.getModifier();
    BstNode localBstNode1 = paramBstNode;
    BstNode localBstNode3 = paramBstNode == null ? null : localBstNodeFactory.createLeaf(paramBstNode);
    BstModificationResult localBstModificationResult = localBstModifier.modify(paramObject, localBstNode3);
    BstNode localBstNode4 = null;
    BstNode localBstNode5 = null;
    if (paramBstNode != null)
    {
      localBstNode4 = paramBstNode.childOrNull(BstSide.LEFT);
      localBstNode5 = paramBstNode.childOrNull(BstSide.RIGHT);
    }
    BstNode localBstNode2;
    switch (1.$SwitchMap$com$google$common$collect$BstModificationResult$ModificationType[localBstModificationResult.getType().ordinal()])
    {
    case 1:
      localBstNode2 = paramBstNode;
      break;
    case 2:
      if (localBstModificationResult.getChangedTarget() != null)
        localBstNode2 = localBstNodeFactory.createNode(localBstModificationResult.getChangedTarget(), localBstNode4, localBstNode5);
      else if (paramBstNode == null)
        localBstNode2 = null;
      else
        throw new AssertionError("Modification result is a REBUILDING_CHANGE, but rebalancing required");
      break;
    case 3:
      if (localBstModificationResult.getChangedTarget() != null)
        localBstNode2 = localBstBalancePolicy.balance(localBstNodeFactory, localBstModificationResult.getChangedTarget(), localBstNode4, localBstNode5);
      else if (paramBstNode != null)
        localBstNode2 = localBstBalancePolicy.combine(localBstNodeFactory, localBstNode4, localBstNode5);
      else
        localBstNode2 = null;
      break;
    default:
      throw new AssertionError();
    }
    return BstMutationResult.mutationResult(paramObject, localBstNode1, localBstNode2, localBstModificationResult);
  }

  public static BstMutationResult extractMin(BstNode paramBstNode, BstNodeFactory paramBstNodeFactory, BstBalancePolicy paramBstBalancePolicy)
  {
    Preconditions.checkNotNull(paramBstNode);
    Preconditions.checkNotNull(paramBstNodeFactory);
    Preconditions.checkNotNull(paramBstBalancePolicy);
    if (paramBstNode.hasChild(BstSide.LEFT))
    {
      BstMutationResult localBstMutationResult = extractMin(paramBstNode.getChild(BstSide.LEFT), paramBstNodeFactory, paramBstBalancePolicy);
      return localBstMutationResult.lift(paramBstNode, BstSide.LEFT, paramBstNodeFactory, paramBstBalancePolicy);
    }
    return BstMutationResult.mutationResult(paramBstNode.getKey(), paramBstNode, paramBstNode.childOrNull(BstSide.RIGHT), BstModificationResult.rebalancingChange(paramBstNode, null));
  }

  public static BstMutationResult extractMax(BstNode paramBstNode, BstNodeFactory paramBstNodeFactory, BstBalancePolicy paramBstBalancePolicy)
  {
    Preconditions.checkNotNull(paramBstNode);
    Preconditions.checkNotNull(paramBstNodeFactory);
    Preconditions.checkNotNull(paramBstBalancePolicy);
    if (paramBstNode.hasChild(BstSide.RIGHT))
    {
      BstMutationResult localBstMutationResult = extractMax(paramBstNode.getChild(BstSide.RIGHT), paramBstNodeFactory, paramBstBalancePolicy);
      return localBstMutationResult.lift(paramBstNode, BstSide.RIGHT, paramBstNodeFactory, paramBstBalancePolicy);
    }
    return BstMutationResult.mutationResult(paramBstNode.getKey(), paramBstNode, paramBstNode.childOrNull(BstSide.LEFT), BstModificationResult.rebalancingChange(paramBstNode, null));
  }

  public static BstNode insertMin(BstNode paramBstNode1, BstNode paramBstNode2, BstNodeFactory paramBstNodeFactory, BstBalancePolicy paramBstBalancePolicy)
  {
    Preconditions.checkNotNull(paramBstNode2);
    Preconditions.checkNotNull(paramBstNodeFactory);
    Preconditions.checkNotNull(paramBstBalancePolicy);
    if (paramBstNode1 == null)
      return paramBstNodeFactory.createLeaf(paramBstNode2);
    return paramBstBalancePolicy.balance(paramBstNodeFactory, paramBstNode1, insertMin(paramBstNode1.childOrNull(BstSide.LEFT), paramBstNode2, paramBstNodeFactory, paramBstBalancePolicy), paramBstNode1.childOrNull(BstSide.RIGHT));
  }

  public static BstNode insertMax(BstNode paramBstNode1, BstNode paramBstNode2, BstNodeFactory paramBstNodeFactory, BstBalancePolicy paramBstBalancePolicy)
  {
    Preconditions.checkNotNull(paramBstNode2);
    Preconditions.checkNotNull(paramBstNodeFactory);
    Preconditions.checkNotNull(paramBstBalancePolicy);
    if (paramBstNode1 == null)
      return paramBstNodeFactory.createLeaf(paramBstNode2);
    return paramBstBalancePolicy.balance(paramBstNodeFactory, paramBstNode1, paramBstNode1.childOrNull(BstSide.LEFT), insertMax(paramBstNode1.childOrNull(BstSide.RIGHT), paramBstNode2, paramBstNodeFactory, paramBstBalancePolicy));
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.BstOperations
 * JD-Core Version:    0.6.2
 */