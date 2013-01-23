package com.google.inject.internal;

import com.google.inject.internal.util..ImmutableSet;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

final class ConstructorInjector
{
  private final .ImmutableSet injectableMembers;
  private final SingleParameterInjector[] parameterInjectors;
  private final ConstructionProxy constructionProxy;
  private final MembersInjectorImpl membersInjector;

  ConstructorInjector(Set paramSet, ConstructionProxy paramConstructionProxy, SingleParameterInjector[] paramArrayOfSingleParameterInjector, MembersInjectorImpl paramMembersInjectorImpl)
  {
    this.injectableMembers = $ImmutableSet.copyOf(paramSet);
    this.constructionProxy = paramConstructionProxy;
    this.parameterInjectors = paramArrayOfSingleParameterInjector;
    this.membersInjector = paramMembersInjectorImpl;
  }

  public .ImmutableSet getInjectableMembers()
  {
    return this.injectableMembers;
  }

  ConstructionProxy getConstructionProxy()
  {
    return this.constructionProxy;
  }

  Object construct(Errors paramErrors, InternalContext paramInternalContext, Class paramClass, boolean paramBoolean)
    throws ErrorsException
  {
    ConstructionContext localConstructionContext = paramInternalContext.getConstructionContext(this);
    if (localConstructionContext.isConstructing())
    {
      if (!paramBoolean)
        throw paramErrors.circularProxiesDisabled(paramClass).toException();
      return localConstructionContext.createProxy(paramErrors, paramClass);
    }
    Object localObject1 = localConstructionContext.getCurrentReference();
    if (localObject1 != null)
      return localObject1;
    try
    {
      localConstructionContext.startConstruction();
      try
      {
        localObject2 = SingleParameterInjector.getAll(paramErrors, paramInternalContext, this.parameterInjectors);
        localObject1 = this.constructionProxy.newInstance((Object[])localObject2);
        localConstructionContext.setProxyDelegates(localObject1);
      }
      finally
      {
      }
      localConstructionContext.setCurrentReference(localObject1);
      this.membersInjector.injectMembers(localObject1, paramErrors, paramInternalContext, false);
      this.membersInjector.notifyListeners(localObject1, paramErrors);
      Object localObject2 = localObject1;
      return localObject2;
    }
    catch (InvocationTargetException localInvocationTargetException1)
    {
      InvocationTargetException localInvocationTargetException2 = localInvocationTargetException1.getCause() != null ? localInvocationTargetException1.getCause() : localInvocationTargetException1;
      throw paramErrors.withSource(this.constructionProxy.getInjectionPoint()).errorInjectingConstructor(localInvocationTargetException2).toException();
    }
    finally
    {
      localConstructionContext.removeCurrentReference();
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.ConstructorInjector
 * JD-Core Version:    0.6.2
 */