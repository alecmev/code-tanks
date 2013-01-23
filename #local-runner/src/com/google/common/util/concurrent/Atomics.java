package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;

@Beta
public final class Atomics
{
  public static AtomicReference newReference()
  {
    return new AtomicReference();
  }

  public static AtomicReference newReference(Object paramObject)
  {
    return new AtomicReference(paramObject);
  }

  public static AtomicReferenceArray newReferenceArray(int paramInt)
  {
    return new AtomicReferenceArray(paramInt);
  }

  public static AtomicReferenceArray newReferenceArray(Object[] paramArrayOfObject)
  {
    return new AtomicReferenceArray(paramArrayOfObject);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.util.concurrent.Atomics
 * JD-Core Version:    0.6.2
 */