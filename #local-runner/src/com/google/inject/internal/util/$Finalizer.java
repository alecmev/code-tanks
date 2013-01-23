package com.google.inject.internal.util;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

public class $Finalizer extends Thread
{
  private static final Logger logger = Logger.getLogger(Finalizer.class.getName());
  private static final String FINALIZABLE_REFERENCE = "com.google.inject.internal.util.$FinalizableReference";
  private final WeakReference finalizableReferenceClassReference;
  private final PhantomReference frqReference;
  private final ReferenceQueue queue = new ReferenceQueue();

  public static ReferenceQueue startFinalizer(Class paramClass, Object paramObject)
  {
    if (!paramClass.getName().equals("com.google.inject.internal.util.$FinalizableReference"))
      throw new IllegalArgumentException("Expected com.google.inject.internal.util.FinalizableReference.");
    Finalizer localFinalizer = new Finalizer(paramClass, paramObject);
    localFinalizer.start();
    return localFinalizer.queue;
  }

  private $Finalizer(Class paramClass, Object paramObject)
  {
    super(Finalizer.class.getName());
    this.finalizableReferenceClassReference = new WeakReference(paramClass);
    this.frqReference = new PhantomReference(paramObject, this.queue);
    setDaemon(true);
  }

  public void run()
  {
    try
    {
      while (true)
        try
        {
          cleanUp(this.queue.remove());
        }
        catch (InterruptedException localInterruptedException)
        {
        }
    }
    catch (ShutDown localShutDown)
    {
    }
  }

  private void cleanUp(Reference paramReference)
    throws .Finalizer.ShutDown
  {
    Method localMethod = getFinalizeReferentMethod();
    do
    {
      paramReference.clear();
      if (paramReference == this.frqReference)
        throw new ShutDown(null);
      try
      {
        localMethod.invoke(paramReference, new Object[0]);
      }
      catch (Throwable localThrowable)
      {
        logger.log(Level.SEVERE, "Error cleaning up after reference.", localThrowable);
      }
    }
    while ((paramReference = this.queue.poll()) != null);
  }

  private Method getFinalizeReferentMethod()
    throws .Finalizer.ShutDown
  {
    Class localClass = (Class)this.finalizableReferenceClassReference.get();
    if (localClass == null)
      throw new ShutDown(null);
    try
    {
      return localClass.getMethod("finalizeReferent", new Class[0]);
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      throw new AssertionError(localNoSuchMethodException);
    }
  }

  private static class ShutDown extends Exception
  {
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.util..Finalizer
 * JD-Core Version:    0.6.2
 */