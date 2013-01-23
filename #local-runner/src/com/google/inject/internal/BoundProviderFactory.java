package com.google.inject.internal;

import com.google.inject.Key;
import com.google.inject.spi.Dependency;
import javax.inject.Provider;

final class BoundProviderFactory
  implements CreationListener, InternalFactory
{
  private final InjectorImpl injector;
  final Key providerKey;
  final Object source;
  private InternalFactory providerFactory;

  BoundProviderFactory(InjectorImpl paramInjectorImpl, Key paramKey, Object paramObject)
  {
    this.injector = paramInjectorImpl;
    this.providerKey = paramKey;
    this.source = paramObject;
  }

  public void notify(Errors paramErrors)
  {
    try
    {
      this.providerFactory = this.injector.getInternalFactory(this.providerKey, paramErrors.withSource(this.source), InjectorImpl.JitLimitation.NEW_OR_EXISTING_JIT);
    }
    catch (ErrorsException localErrorsException)
    {
      paramErrors.merge(localErrorsException.getErrors());
    }
  }

  public Object get(Errors paramErrors, InternalContext paramInternalContext, Dependency paramDependency, boolean paramBoolean)
    throws ErrorsException
  {
    paramErrors = paramErrors.withSource(this.providerKey);
    Provider localProvider = (Provider)this.providerFactory.get(paramErrors, paramInternalContext, paramDependency, true);
    try
    {
      return paramErrors.checkForNull(localProvider.get(), this.source, paramDependency);
    }
    catch (RuntimeException localRuntimeException)
    {
      throw paramErrors.errorInProvider(localRuntimeException).toException();
    }
  }

  public String toString()
  {
    return this.providerKey.toString();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.BoundProviderFactory
 * JD-Core Version:    0.6.2
 */