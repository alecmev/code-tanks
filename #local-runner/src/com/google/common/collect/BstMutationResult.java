package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

@GwtCompatible
final class BstMutationResult
{
  private final Object targetKey;
  private BstNode originalRoot;
  private BstNode changedRoot;
  private final BstModificationResult modificationResult;

  public static BstMutationResult mutationResult(Object paramObject, BstNode paramBstNode1, BstNode paramBstNode2, BstModificationResult paramBstModificationResult)
  {
    return new BstMutationResult(paramObject, paramBstNode1, paramBstNode2, paramBstModificationResult);
  }

  private BstMutationResult(Object paramObject, BstNode paramBstNode1, BstNode paramBstNode2, BstModificationResult paramBstModificationResult)
  {
    this.targetKey = paramObject;
    this.originalRoot = paramBstNode1;
    this.changedRoot = paramBstNode2;
    this.modificationResult = ((BstModificationResult)Preconditions.checkNotNull(paramBstModificationResult));
  }

  public Object getTargetKey()
  {
    return this.targetKey;
  }

  public BstNode getOriginalRoot()
  {
    return this.originalRoot;
  }

  public BstNode getChangedRoot()
  {
    return this.changedRoot;
  }

  public BstNode getOriginalTarget()
  {
    return this.modificationResult.getOriginalTarget();
  }

  public BstNode getChangedTarget()
  {
    return this.modificationResult.getChangedTarget();
  }

  BstModificationResult.ModificationType modificationType()
  {
    return this.modificationResult.getType();
  }

  public BstMutationResult lift(BstNode paramBstNode, BstSide paramBstSide, BstNodeFactory paramBstNodeFactory, BstBalancePolicy paramBstBalancePolicy)
  {
    if (!$assertionsDisabled)
      if (((paramBstNode != null ? 1 : 0) & (paramBstSide != null ? 1 : 0) & (paramBstNodeFactory != null ? 1 : 0) & (paramBstBalancePolicy != null ? 1 : 0)) == 0)
        throw new AssertionError();
    switch (1.$SwitchMap$com$google$common$collect$BstModificationResult$ModificationType[modificationType().ordinal()])
    {
    case 1:
      this.originalRoot = (this.changedRoot = paramBstNode);
      return this;
    case 2:
    case 3:
      this.originalRoot = paramBstNode;
      BstNode localBstNode1 = paramBstNode.childOrNull(BstSide.LEFT);
      BstNode localBstNode2 = paramBstNode.childOrNull(BstSide.RIGHT);
      switch (paramBstSide)
      {
      case LEFT:
        localBstNode1 = this.changedRoot;
        break;
      case RIGHT:
        localBstNode2 = this.changedRoot;
        break;
      default:
        throw new AssertionError();
      }
      if (modificationType() == BstModificationResult.ModificationType.REBUILDING_CHANGE)
        this.changedRoot = paramBstNodeFactory.createNode(paramBstNode, localBstNode1, localBstNode2);
      else
        this.changedRoot = paramBstBalancePolicy.balance(paramBstNodeFactory, paramBstNode, localBstNode1, localBstNode2);
      return this;
    }
    throw new AssertionError();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.BstMutationResult
 * JD-Core Version:    0.6.2
 */