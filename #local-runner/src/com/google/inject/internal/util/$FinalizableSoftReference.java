package com.google.inject.internal.util;

import java.lang.ref.SoftReference;

public abstract class $FinalizableSoftReference extends SoftReference
  implements .FinalizableReference
{
  protected $FinalizableSoftReference(Object paramObject, .FinalizableReferenceQueue paramFinalizableReferenceQueue)
  {
    super(paramObject, paramFinalizableReferenceQueue.queue);
    paramFinalizableReferenceQueue.cleanUp();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.util..FinalizableSoftReference
 * JD-Core Version:    0.6.2
 */