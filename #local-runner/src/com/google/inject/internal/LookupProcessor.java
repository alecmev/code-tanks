package com.google.inject.internal;

import com.google.inject.Provider;
import com.google.inject.spi.MembersInjectorLookup;
import com.google.inject.spi.ProviderLookup;

final class LookupProcessor extends AbstractProcessor
{
  LookupProcessor(Errors paramErrors)
  {
    super(paramErrors);
  }

  public Boolean visit(MembersInjectorLookup paramMembersInjectorLookup)
  {
    try
    {
      MembersInjectorImpl localMembersInjectorImpl = this.injector.membersInjectorStore.get(paramMembersInjectorLookup.getType(), this.errors);
      paramMembersInjectorLookup.initializeDelegate(localMembersInjectorImpl);
    }
    catch (ErrorsException localErrorsException)
    {
      this.errors.merge(localErrorsException.getErrors());
    }
    return Boolean.valueOf(true);
  }

  public Boolean visit(ProviderLookup paramProviderLookup)
  {
    try
    {
      Provider localProvider = this.injector.getProviderOrThrow(paramProviderLookup.getKey(), this.errors);
      paramProviderLookup.initializeDelegate(localProvider);
    }
    catch (ErrorsException localErrorsException)
    {
      this.errors.merge(localErrorsException.getErrors());
    }
    return Boolean.valueOf(true);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.LookupProcessor
 * JD-Core Version:    0.6.2
 */