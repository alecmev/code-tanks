package com.google.inject.internal;

import com.google.inject.Provider;
import com.google.inject.ProvisionException;
import com.google.inject.spi.Dependency;

final class ProviderToInternalFactoryAdapter
  implements Provider
{
  private final InjectorImpl injector;
  private final InternalFactory internalFactory;

  public ProviderToInternalFactoryAdapter(InjectorImpl paramInjectorImpl, InternalFactory paramInternalFactory)
  {
    this.injector = paramInjectorImpl;
    this.internalFactory = paramInternalFactory;
  }

  public Object get()
  {
    final Errors localErrors = new Errors();
    try
    {
      Object localObject = this.injector.callInContext(new ContextualCallable()
      {
        public Object call(InternalContext paramAnonymousInternalContext)
          throws ErrorsException
        {
          Dependency localDependency = paramAnonymousInternalContext.getDependency();
          return ProviderToInternalFactoryAdapter.this.internalFactory.get(localErrors, paramAnonymousInternalContext, localDependency, true);
        }
      });
      localErrors.throwIfNewErrors(0);
      return localObject;
    }
    catch (ErrorsException localErrorsException)
    {
      throw new ProvisionException(localErrors.merge(localErrorsException.getErrors()).getMessages());
    }
  }

  public String toString()
  {
    return this.internalFactory.toString();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.ProviderToInternalFactoryAdapter
 * JD-Core Version:    0.6.2
 */