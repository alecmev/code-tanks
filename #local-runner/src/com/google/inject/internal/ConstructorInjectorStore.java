package com.google.inject.internal;

import com.google.inject.internal.util..ImmutableList;
import com.google.inject.internal.util..Iterables;
import com.google.inject.spi.InjectionPoint;

final class ConstructorInjectorStore
{
  private final InjectorImpl injector;
  private final FailableCache cache = new FailableCache()
  {
    protected ConstructorInjector create(InjectionPoint paramAnonymousInjectionPoint, Errors paramAnonymousErrors)
      throws ErrorsException
    {
      return ConstructorInjectorStore.this.createConstructor(paramAnonymousInjectionPoint, paramAnonymousErrors);
    }
  };

  ConstructorInjectorStore(InjectorImpl paramInjectorImpl)
  {
    this.injector = paramInjectorImpl;
  }

  public ConstructorInjector get(InjectionPoint paramInjectionPoint, Errors paramErrors)
    throws ErrorsException
  {
    return (ConstructorInjector)this.cache.get(paramInjectionPoint, paramErrors);
  }

  boolean remove(InjectionPoint paramInjectionPoint)
  {
    return this.cache.remove(paramInjectionPoint);
  }

  private ConstructorInjector createConstructor(InjectionPoint paramInjectionPoint, Errors paramErrors)
    throws ErrorsException
  {
    int i = paramErrors.size();
    SingleParameterInjector[] arrayOfSingleParameterInjector = this.injector.getParametersInjectors(paramInjectionPoint.getDependencies(), paramErrors);
    MembersInjectorImpl localMembersInjectorImpl = this.injector.membersInjectorStore.get(paramInjectionPoint.getDeclaringType(), paramErrors);
    $ImmutableList localImmutableList1 = this.injector.state.getMethodAspects();
    $ImmutableList localImmutableList2 = localMembersInjectorImpl.getAddedAspects().isEmpty() ? localImmutableList1 : .ImmutableList.copyOf($Iterables.concat(localImmutableList1, localMembersInjectorImpl.getAddedAspects()));
    ProxyFactory localProxyFactory = new ProxyFactory(paramInjectionPoint, localImmutableList2);
    paramErrors.throwIfNewErrors(i);
    return new ConstructorInjector(localMembersInjectorImpl.getInjectionPoints(), localProxyFactory.create(), arrayOfSingleParameterInjector, localMembersInjectorImpl);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.ConstructorInjectorStore
 * JD-Core Version:    0.6.2
 */