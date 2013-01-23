package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

@GwtCompatible
abstract class BstPath
{
  private final BstNode tip;
  private final BstPath prefix;

  BstPath(BstNode paramBstNode, BstPath paramBstPath)
  {
    this.tip = ((BstNode)Preconditions.checkNotNull(paramBstNode));
    this.prefix = paramBstPath;
  }

  public final BstNode getTip()
  {
    return this.tip;
  }

  public final boolean hasPrefix()
  {
    return this.prefix != null;
  }

  public final BstPath prefixOrNull()
  {
    return this.prefix;
  }

  public final BstPath getPrefix()
  {
    Preconditions.checkState(hasPrefix());
    return this.prefix;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.BstPath
 * JD-Core Version:    0.6.2
 */