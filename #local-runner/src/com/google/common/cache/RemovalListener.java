package com.google.common.cache;

import com.google.common.annotations.Beta;

@Beta
public abstract interface RemovalListener
{
  public abstract void onRemoval(RemovalNotification paramRemovalNotification);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.cache.RemovalListener
 * JD-Core Version:    0.6.2
 */