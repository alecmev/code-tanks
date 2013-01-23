package com.google.inject.internal.util;

import java.lang.ref.WeakReference;

public abstract class $FinalizableWeakReference extends WeakReference
  implements .FinalizableReference
{
  protected $FinalizableWeakReference(Object paramObject, .FinalizableReferenceQueue paramFinalizableReferenceQueue)
  {
    super(paramObject, paramFinalizableReferenceQueue.queue);
    paramFinalizableReferenceQueue.cleanUp();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.util..FinalizableWeakReference
 * JD-Core Version:    0.6.2
 */