package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import java.util.NoSuchElementException;

@GwtCompatible
final class BstInOrderPath extends BstPath
{
  private final BstSide sideExtension;
  private transient Optional prevInOrder;
  private transient Optional nextInOrder;

  public static BstPathFactory inOrderFactory()
  {
    return new BstPathFactory()
    {
      public BstInOrderPath extension(BstInOrderPath paramAnonymousBstInOrderPath, BstSide paramAnonymousBstSide)
      {
        return BstInOrderPath.extension(paramAnonymousBstInOrderPath, paramAnonymousBstSide);
      }

      public BstInOrderPath initialPath(BstNode paramAnonymousBstNode)
      {
        return new BstInOrderPath(paramAnonymousBstNode, null, null, null);
      }
    };
  }

  private static BstInOrderPath extension(BstInOrderPath paramBstInOrderPath, BstSide paramBstSide)
  {
    Preconditions.checkNotNull(paramBstInOrderPath);
    BstNode localBstNode = paramBstInOrderPath.getTip();
    return new BstInOrderPath(localBstNode.getChild(paramBstSide), paramBstSide, paramBstInOrderPath);
  }

  private BstInOrderPath(BstNode paramBstNode, BstSide paramBstSide, BstInOrderPath paramBstInOrderPath)
  {
    super(paramBstNode, paramBstInOrderPath);
    this.sideExtension = paramBstSide;
    if (!$assertionsDisabled)
      if ((paramBstSide == null ? 1 : 0) != (paramBstInOrderPath == null ? 1 : 0))
        throw new AssertionError();
  }

  private Optional computeNextInOrder(BstSide paramBstSide)
  {
    if (getTip().hasChild(paramBstSide))
    {
      localBstInOrderPath = extension(this, paramBstSide);
      BstSide localBstSide = paramBstSide.other();
      while (localBstInOrderPath.getTip().hasChild(localBstSide))
        localBstInOrderPath = extension(localBstInOrderPath, localBstSide);
      return Optional.of(localBstInOrderPath);
    }
    for (BstInOrderPath localBstInOrderPath = this; localBstInOrderPath.sideExtension == paramBstSide; localBstInOrderPath = (BstInOrderPath)localBstInOrderPath.getPrefix());
    localBstInOrderPath = (BstInOrderPath)localBstInOrderPath.prefixOrNull();
    return Optional.fromNullable(localBstInOrderPath);
  }

  private Optional nextInOrder(BstSide paramBstSide)
  {
    Optional localOptional;
    switch (2.$SwitchMap$com$google$common$collect$BstSide[paramBstSide.ordinal()])
    {
    case 1:
      localOptional = this.prevInOrder;
      return localOptional == null ? (this.prevInOrder = computeNextInOrder(paramBstSide)) : localOptional;
    case 2:
      localOptional = this.nextInOrder;
      return localOptional == null ? (this.nextInOrder = computeNextInOrder(paramBstSide)) : localOptional;
    }
    throw new AssertionError();
  }

  public boolean hasNext(BstSide paramBstSide)
  {
    return nextInOrder(paramBstSide).isPresent();
  }

  public BstInOrderPath next(BstSide paramBstSide)
  {
    if (!hasNext(paramBstSide))
      throw new NoSuchElementException();
    return (BstInOrderPath)nextInOrder(paramBstSide).get();
  }

  public BstSide getSideOfExtension()
  {
    return this.sideExtension;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.BstInOrderPath
 * JD-Core Version:    0.6.2
 */