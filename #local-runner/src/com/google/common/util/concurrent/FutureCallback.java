package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;

@Beta
public abstract interface FutureCallback
{
  public abstract void onSuccess(Object paramObject);

  public abstract void onFailure(Throwable paramThrowable);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.util.concurrent.FutureCallback
 * JD-Core Version:    0.6.2
 */