package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible
 enum BstSide
{
  LEFT, RIGHT;

  abstract BstSide other();
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.BstSide
 * JD-Core Version:    0.6.2
 */