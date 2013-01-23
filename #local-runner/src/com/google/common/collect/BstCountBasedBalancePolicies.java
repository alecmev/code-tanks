package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

@GwtCompatible
final class BstCountBasedBalancePolicies
{
  private static final int SINGLE_ROTATE_RATIO = 4;
  private static final int SECOND_ROTATE_RATIO = 2;

  public static BstBalancePolicy noRebalancePolicy(BstAggregate paramBstAggregate)
  {
    Preconditions.checkNotNull(paramBstAggregate);
    return new BstBalancePolicy()
    {
      public BstNode balance(BstNodeFactory paramAnonymousBstNodeFactory, BstNode paramAnonymousBstNode1, BstNode paramAnonymousBstNode2, BstNode paramAnonymousBstNode3)
      {
        return ((BstNodeFactory)Preconditions.checkNotNull(paramAnonymousBstNodeFactory)).createNode(paramAnonymousBstNode1, paramAnonymousBstNode2, paramAnonymousBstNode3);
      }

      public BstNode combine(BstNodeFactory paramAnonymousBstNodeFactory, BstNode paramAnonymousBstNode1, BstNode paramAnonymousBstNode2)
      {
        if (paramAnonymousBstNode1 == null)
          return paramAnonymousBstNode2;
        if (paramAnonymousBstNode2 == null)
          return paramAnonymousBstNode1;
        if (this.val$countAggregate.treeValue(paramAnonymousBstNode1) > this.val$countAggregate.treeValue(paramAnonymousBstNode2))
          return paramAnonymousBstNodeFactory.createNode(paramAnonymousBstNode1, paramAnonymousBstNode1.childOrNull(BstSide.LEFT), combine(paramAnonymousBstNodeFactory, paramAnonymousBstNode1.childOrNull(BstSide.RIGHT), paramAnonymousBstNode2));
        return paramAnonymousBstNodeFactory.createNode(paramAnonymousBstNode2, combine(paramAnonymousBstNodeFactory, paramAnonymousBstNode1, paramAnonymousBstNode2.childOrNull(BstSide.LEFT)), paramAnonymousBstNode2.childOrNull(BstSide.RIGHT));
      }
    };
  }

  public static BstBalancePolicy singleRebalancePolicy(BstAggregate paramBstAggregate)
  {
    Preconditions.checkNotNull(paramBstAggregate);
    return new BstBalancePolicy()
    {
      public BstNode balance(BstNodeFactory paramAnonymousBstNodeFactory, BstNode paramAnonymousBstNode1, BstNode paramAnonymousBstNode2, BstNode paramAnonymousBstNode3)
      {
        long l1 = this.val$countAggregate.treeValue(paramAnonymousBstNode2);
        long l2 = this.val$countAggregate.treeValue(paramAnonymousBstNode3);
        if (l1 + l2 > 1L)
        {
          if (l2 >= 4L * l1)
            return rotateL(paramAnonymousBstNodeFactory, paramAnonymousBstNode1, paramAnonymousBstNode2, paramAnonymousBstNode3);
          if (l1 >= 4L * l2)
            return rotateR(paramAnonymousBstNodeFactory, paramAnonymousBstNode1, paramAnonymousBstNode2, paramAnonymousBstNode3);
        }
        return paramAnonymousBstNodeFactory.createNode(paramAnonymousBstNode1, paramAnonymousBstNode2, paramAnonymousBstNode3);
      }

      private BstNode rotateL(BstNodeFactory paramAnonymousBstNodeFactory, BstNode paramAnonymousBstNode1, BstNode paramAnonymousBstNode2, BstNode paramAnonymousBstNode3)
      {
        Preconditions.checkNotNull(paramAnonymousBstNode3);
        BstNode localBstNode1 = paramAnonymousBstNode3.childOrNull(BstSide.LEFT);
        BstNode localBstNode2 = paramAnonymousBstNode3.childOrNull(BstSide.RIGHT);
        if (this.val$countAggregate.treeValue(localBstNode1) >= 2L * this.val$countAggregate.treeValue(localBstNode2))
          paramAnonymousBstNode3 = singleR(paramAnonymousBstNodeFactory, paramAnonymousBstNode3, localBstNode1, localBstNode2);
        return singleL(paramAnonymousBstNodeFactory, paramAnonymousBstNode1, paramAnonymousBstNode2, paramAnonymousBstNode3);
      }

      private BstNode rotateR(BstNodeFactory paramAnonymousBstNodeFactory, BstNode paramAnonymousBstNode1, BstNode paramAnonymousBstNode2, BstNode paramAnonymousBstNode3)
      {
        Preconditions.checkNotNull(paramAnonymousBstNode2);
        BstNode localBstNode1 = paramAnonymousBstNode2.childOrNull(BstSide.RIGHT);
        BstNode localBstNode2 = paramAnonymousBstNode2.childOrNull(BstSide.LEFT);
        if (this.val$countAggregate.treeValue(localBstNode1) >= 2L * this.val$countAggregate.treeValue(localBstNode2))
          paramAnonymousBstNode2 = singleL(paramAnonymousBstNodeFactory, paramAnonymousBstNode2, localBstNode2, localBstNode1);
        return singleR(paramAnonymousBstNodeFactory, paramAnonymousBstNode1, paramAnonymousBstNode2, paramAnonymousBstNode3);
      }

      private BstNode singleL(BstNodeFactory paramAnonymousBstNodeFactory, BstNode paramAnonymousBstNode1, BstNode paramAnonymousBstNode2, BstNode paramAnonymousBstNode3)
      {
        Preconditions.checkNotNull(paramAnonymousBstNode3);
        return paramAnonymousBstNodeFactory.createNode(paramAnonymousBstNode3, paramAnonymousBstNodeFactory.createNode(paramAnonymousBstNode1, paramAnonymousBstNode2, paramAnonymousBstNode3.childOrNull(BstSide.LEFT)), paramAnonymousBstNode3.childOrNull(BstSide.RIGHT));
      }

      private BstNode singleR(BstNodeFactory paramAnonymousBstNodeFactory, BstNode paramAnonymousBstNode1, BstNode paramAnonymousBstNode2, BstNode paramAnonymousBstNode3)
      {
        Preconditions.checkNotNull(paramAnonymousBstNode2);
        return paramAnonymousBstNodeFactory.createNode(paramAnonymousBstNode2, paramAnonymousBstNode2.childOrNull(BstSide.LEFT), paramAnonymousBstNodeFactory.createNode(paramAnonymousBstNode1, paramAnonymousBstNode2.childOrNull(BstSide.RIGHT), paramAnonymousBstNode3));
      }

      public BstNode combine(BstNodeFactory paramAnonymousBstNodeFactory, BstNode paramAnonymousBstNode1, BstNode paramAnonymousBstNode2)
      {
        if (paramAnonymousBstNode1 == null)
          return paramAnonymousBstNode2;
        if (paramAnonymousBstNode2 == null)
          return paramAnonymousBstNode1;
        BstMutationResult localBstMutationResult;
        BstNode localBstNode;
        if (this.val$countAggregate.treeValue(paramAnonymousBstNode1) > this.val$countAggregate.treeValue(paramAnonymousBstNode2))
        {
          localBstMutationResult = BstOperations.extractMax(paramAnonymousBstNode1, paramAnonymousBstNodeFactory, this);
          localBstNode = localBstMutationResult.getOriginalTarget();
          paramAnonymousBstNode1 = localBstMutationResult.getChangedRoot();
        }
        else
        {
          localBstMutationResult = BstOperations.extractMin(paramAnonymousBstNode2, paramAnonymousBstNodeFactory, this);
          localBstNode = localBstMutationResult.getOriginalTarget();
          paramAnonymousBstNode2 = localBstMutationResult.getChangedRoot();
        }
        return paramAnonymousBstNodeFactory.createNode(localBstNode, paramAnonymousBstNode1, paramAnonymousBstNode2);
      }
    };
  }

  public static BstBalancePolicy fullRebalancePolicy(final BstAggregate paramBstAggregate)
  {
    Preconditions.checkNotNull(paramBstAggregate);
    BstBalancePolicy localBstBalancePolicy = singleRebalancePolicy(paramBstAggregate);
    return new BstBalancePolicy()
    {
      public BstNode balance(BstNodeFactory paramAnonymousBstNodeFactory, BstNode paramAnonymousBstNode1, BstNode paramAnonymousBstNode2, BstNode paramAnonymousBstNode3)
      {
        if (paramAnonymousBstNode2 == null)
          return BstOperations.insertMin(paramAnonymousBstNode3, paramAnonymousBstNode1, paramAnonymousBstNodeFactory, this.val$singleBalancePolicy);
        if (paramAnonymousBstNode3 == null)
          return BstOperations.insertMax(paramAnonymousBstNode2, paramAnonymousBstNode1, paramAnonymousBstNodeFactory, this.val$singleBalancePolicy);
        long l1 = paramBstAggregate.treeValue(paramAnonymousBstNode2);
        long l2 = paramBstAggregate.treeValue(paramAnonymousBstNode3);
        BstNode localBstNode;
        if (4L * l1 <= l2)
        {
          localBstNode = balance(paramAnonymousBstNodeFactory, paramAnonymousBstNode1, paramAnonymousBstNode2, paramAnonymousBstNode3.childOrNull(BstSide.LEFT));
          return this.val$singleBalancePolicy.balance(paramAnonymousBstNodeFactory, paramAnonymousBstNode3, localBstNode, paramAnonymousBstNode3.childOrNull(BstSide.RIGHT));
        }
        if (4L * l2 <= l1)
        {
          localBstNode = balance(paramAnonymousBstNodeFactory, paramAnonymousBstNode1, paramAnonymousBstNode2.childOrNull(BstSide.RIGHT), paramAnonymousBstNode3);
          return this.val$singleBalancePolicy.balance(paramAnonymousBstNodeFactory, paramAnonymousBstNode2, paramAnonymousBstNode2.childOrNull(BstSide.LEFT), localBstNode);
        }
        return paramAnonymousBstNodeFactory.createNode(paramAnonymousBstNode1, paramAnonymousBstNode2, paramAnonymousBstNode3);
      }

      public BstNode combine(BstNodeFactory paramAnonymousBstNodeFactory, BstNode paramAnonymousBstNode1, BstNode paramAnonymousBstNode2)
      {
        if (paramAnonymousBstNode1 == null)
          return paramAnonymousBstNode2;
        if (paramAnonymousBstNode2 == null)
          return paramAnonymousBstNode1;
        long l1 = paramBstAggregate.treeValue(paramAnonymousBstNode1);
        long l2 = paramBstAggregate.treeValue(paramAnonymousBstNode2);
        BstNode localBstNode;
        if (4L * l1 <= l2)
        {
          localBstNode = combine(paramAnonymousBstNodeFactory, paramAnonymousBstNode1, paramAnonymousBstNode2.childOrNull(BstSide.LEFT));
          return this.val$singleBalancePolicy.balance(paramAnonymousBstNodeFactory, paramAnonymousBstNode2, localBstNode, paramAnonymousBstNode2.childOrNull(BstSide.RIGHT));
        }
        if (4L * l2 <= l1)
        {
          localBstNode = combine(paramAnonymousBstNodeFactory, paramAnonymousBstNode1.childOrNull(BstSide.RIGHT), paramAnonymousBstNode2);
          return this.val$singleBalancePolicy.balance(paramAnonymousBstNodeFactory, paramAnonymousBstNode1, paramAnonymousBstNode1.childOrNull(BstSide.LEFT), localBstNode);
        }
        return this.val$singleBalancePolicy.combine(paramAnonymousBstNodeFactory, paramAnonymousBstNode1, paramAnonymousBstNode2);
      }
    };
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.BstCountBasedBalancePolicies
 * JD-Core Version:    0.6.2
 */