package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

@GwtCompatible
final class BstModificationResult
{
  private final BstNode originalTarget;
  private final BstNode changedTarget;
  private final ModificationType type;

  static BstModificationResult identity(BstNode paramBstNode)
  {
    return new BstModificationResult(paramBstNode, paramBstNode, ModificationType.IDENTITY);
  }

  static BstModificationResult rebuildingChange(BstNode paramBstNode1, BstNode paramBstNode2)
  {
    return new BstModificationResult(paramBstNode1, paramBstNode2, ModificationType.REBUILDING_CHANGE);
  }

  static BstModificationResult rebalancingChange(BstNode paramBstNode1, BstNode paramBstNode2)
  {
    return new BstModificationResult(paramBstNode1, paramBstNode2, ModificationType.REBALANCING_CHANGE);
  }

  private BstModificationResult(BstNode paramBstNode1, BstNode paramBstNode2, ModificationType paramModificationType)
  {
    this.originalTarget = paramBstNode1;
    this.changedTarget = paramBstNode2;
    this.type = ((ModificationType)Preconditions.checkNotNull(paramModificationType));
  }

  BstNode getOriginalTarget()
  {
    return this.originalTarget;
  }

  BstNode getChangedTarget()
  {
    return this.changedTarget;
  }

  ModificationType getType()
  {
    return this.type;
  }

  static enum ModificationType
  {
    IDENTITY, REBUILDING_CHANGE, REBALANCING_CHANGE;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.BstModificationResult
 * JD-Core Version:    0.6.2
 */