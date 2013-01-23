package com.google.inject.internal.util;

import java.lang.ref.PhantomReference;

public abstract class $FinalizablePhantomReference extends PhantomReference
  implements .FinalizableReference
{
  protected $FinalizablePhantomReference(Object paramObject, .FinalizableReferenceQueue paramFinalizableReferenceQueue)
  {
    super(paramObject, paramFinalizableReferenceQueue.queue);
    paramFinalizableReferenceQueue.cleanUp();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.util..FinalizablePhantomReference
 * JD-Core Version:    0.6.2
 */