package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

@GwtCompatible
final class BstMutationRule
{
  private final BstModifier modifier;
  private final BstBalancePolicy balancePolicy;
  private final BstNodeFactory nodeFactory;

  public static BstMutationRule createRule(BstModifier paramBstModifier, BstBalancePolicy paramBstBalancePolicy, BstNodeFactory paramBstNodeFactory)
  {
    return new BstMutationRule(paramBstModifier, paramBstBalancePolicy, paramBstNodeFactory);
  }

  private BstMutationRule(BstModifier paramBstModifier, BstBalancePolicy paramBstBalancePolicy, BstNodeFactory paramBstNodeFactory)
  {
    this.balancePolicy = ((BstBalancePolicy)Preconditions.checkNotNull(paramBstBalancePolicy));
    this.nodeFactory = ((BstNodeFactory)Preconditions.checkNotNull(paramBstNodeFactory));
    this.modifier = ((BstModifier)Preconditions.checkNotNull(paramBstModifier));
  }

  public BstModifier getModifier()
  {
    return this.modifier;
  }

  public BstBalancePolicy getBalancePolicy()
  {
    return this.balancePolicy;
  }

  public BstNodeFactory getNodeFactory()
  {
    return this.nodeFactory;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.BstMutationRule
 * JD-Core Version:    0.6.2
 */