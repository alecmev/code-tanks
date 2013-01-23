package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;

@Beta
public abstract interface Service
{
  public abstract ListenableFuture start();

  public abstract State startAndWait();

  public abstract boolean isRunning();

  public abstract State state();

  public abstract ListenableFuture stop();

  public abstract State stopAndWait();

  @Beta
  public static enum State
  {
    NEW, STARTING, RUNNING, STOPPING, TERMINATED, FAILED;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.util.concurrent.Service
 * JD-Core Version:    0.6.2
 */