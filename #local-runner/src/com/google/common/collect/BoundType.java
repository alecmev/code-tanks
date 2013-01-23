package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;

@Beta
@GwtCompatible
public enum BoundType
{
  OPEN, CLOSED;

  static BoundType forBoolean(boolean paramBoolean)
  {
    return paramBoolean ? CLOSED : OPEN;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.BoundType
 * JD-Core Version:    0.6.2
 */