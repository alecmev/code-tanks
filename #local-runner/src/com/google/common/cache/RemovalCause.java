package com.google.common.cache;

import com.google.common.annotations.Beta;

@Beta
public enum RemovalCause
{
  EXPLICIT, REPLACED, COLLECTED, EXPIRED, SIZE;

  abstract boolean wasEvicted();
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.cache.RemovalCause
 * JD-Core Version:    0.6.2
 */