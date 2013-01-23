package com.google.common.base;

import java.lang.ref.WeakReference;

public abstract class FinalizableWeakReference extends WeakReference
  implements FinalizableReference
{
  protected FinalizableWeakReference(Object paramObject, FinalizableReferenceQueue paramFinalizableReferenceQueue)
  {
    super(paramObject, paramFinalizableReferenceQueue.queue);
    paramFinalizableReferenceQueue.cleanUp();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.base.FinalizableWeakReference
 * JD-Core Version:    0.6.2
 */