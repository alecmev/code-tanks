package com.google.inject.internal;

import com.google.inject.Stage;
import com.google.inject.TypeLiteral;
import com.google.inject.internal.util..Lists;
import com.google.inject.internal.util..Maps;
import com.google.inject.internal.util..Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

final class Initializer
{
  private final Thread creatingThread = Thread.currentThread();
  private final CountDownLatch ready = new CountDownLatch(1);
  private final Map pendingInjection = $Maps.newIdentityHashMap();

  Initializable requestInjection(InjectorImpl paramInjectorImpl, Object paramObject1, Object paramObject2, Set paramSet)
  {
    $Preconditions.checkNotNull(paramObject2);
    if ((paramObject1 == null) || ((paramSet.isEmpty()) && (!paramInjectorImpl.membersInjectorStore.hasTypeListeners())))
      return Initializables.of(paramObject1);
    InjectableReference localInjectableReference = new InjectableReference(paramInjectorImpl, paramObject1, paramObject2);
    this.pendingInjection.put(paramObject1, localInjectableReference);
    return localInjectableReference;
  }

  void validateOustandingInjections(Errors paramErrors)
  {
    Iterator localIterator = this.pendingInjection.values().iterator();
    while (localIterator.hasNext())
    {
      InjectableReference localInjectableReference = (InjectableReference)localIterator.next();
      try
      {
        localInjectableReference.validate(paramErrors);
      }
      catch (ErrorsException localErrorsException)
      {
        paramErrors.merge(localErrorsException.getErrors());
      }
    }
  }

  void injectAll(Errors paramErrors)
  {
    Iterator localIterator = $Lists.newArrayList(this.pendingInjection.values()).iterator();
    while (localIterator.hasNext())
    {
      InjectableReference localInjectableReference = (InjectableReference)localIterator.next();
      try
      {
        localInjectableReference.get(paramErrors);
      }
      catch (ErrorsException localErrorsException)
      {
        paramErrors.merge(localErrorsException.getErrors());
      }
    }
    if (!this.pendingInjection.isEmpty())
      throw new AssertionError("Failed to satisfy " + this.pendingInjection);
    this.ready.countDown();
  }

  private class InjectableReference
    implements Initializable
  {
    private final InjectorImpl injector;
    private final Object instance;
    private final Object source;
    private MembersInjectorImpl membersInjector;

    public InjectableReference(InjectorImpl paramObject1, Object paramObject2, Object arg4)
    {
      this.injector = paramObject1;
      this.instance = $Preconditions.checkNotNull(paramObject2, "instance");
      Object localObject;
      this.source = $Preconditions.checkNotNull(localObject, "source");
    }

    public void validate(Errors paramErrors)
      throws ErrorsException
    {
      TypeLiteral localTypeLiteral = TypeLiteral.get(this.instance.getClass());
      this.membersInjector = this.injector.membersInjectorStore.get(localTypeLiteral, paramErrors.withSource(this.source));
    }

    public Object get(Errors paramErrors)
      throws ErrorsException
    {
      if (Initializer.this.ready.getCount() == 0L)
        return this.instance;
      if (Thread.currentThread() != Initializer.this.creatingThread)
        try
        {
          Initializer.this.ready.await();
          return this.instance;
        }
        catch (InterruptedException localInterruptedException)
        {
          throw new RuntimeException(localInterruptedException);
        }
      if (Initializer.this.pendingInjection.remove(this.instance) != null)
        this.membersInjector.injectAndNotify(this.instance, paramErrors.withSource(this.source), this.injector.options.stage == Stage.TOOL);
      return this.instance;
    }

    public String toString()
    {
      return this.instance.toString();
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.Initializer
 * JD-Core Version:    0.6.2
 */