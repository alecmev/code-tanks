package com.google.inject.internal;

import com.google.inject.Key;
import com.google.inject.internal.util..ToStringBuilder;
import com.google.inject.spi.Dependency;

final class FactoryProxy
  implements CreationListener, InternalFactory
{
  private final InjectorImpl injector;
  private final Key key;
  private final Key targetKey;
  private final Object source;
  private InternalFactory targetFactory;

  FactoryProxy(InjectorImpl paramInjectorImpl, Key paramKey1, Key paramKey2, Object paramObject)
  {
    this.injector = paramInjectorImpl;
    this.key = paramKey1;
    this.targetKey = paramKey2;
    this.source = paramObject;
  }

  public void notify(Errors paramErrors)
  {
    try
    {
      this.targetFactory = this.injector.getInternalFactory(this.targetKey, paramErrors.withSource(this.source), InjectorImpl.JitLimitation.NEW_OR_EXISTING_JIT);
    }
    catch (ErrorsException localErrorsException)
    {
      paramErrors.merge(localErrorsException.getErrors());
    }
  }

  public Object get(Errors paramErrors, InternalContext paramInternalContext, Dependency paramDependency, boolean paramBoolean)
    throws ErrorsException
  {
    return this.targetFactory.get(paramErrors.withSource(this.targetKey), paramInternalContext, paramDependency, true);
  }

  public String toString()
  {
    return new $ToStringBuilder(FactoryProxy.class).add("key", this.key).add("provider", this.targetFactory).toString();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.FactoryProxy
 * JD-Core Version:    0.6.2
 */