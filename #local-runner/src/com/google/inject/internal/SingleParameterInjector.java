package com.google.inject.internal;

import com.google.inject.spi.Dependency;

final class SingleParameterInjector
{
  private static final Object[] NO_ARGUMENTS = new Object[0];
  private final Dependency dependency;
  private final InternalFactory factory;

  SingleParameterInjector(Dependency paramDependency, InternalFactory paramInternalFactory)
  {
    this.dependency = paramDependency;
    this.factory = paramInternalFactory;
  }

  private Object inject(Errors paramErrors, InternalContext paramInternalContext)
    throws ErrorsException
  {
    Dependency localDependency = paramInternalContext.setDependency(this.dependency);
    try
    {
      Object localObject1 = this.factory.get(paramErrors.withSource(this.dependency), paramInternalContext, this.dependency, false);
      return localObject1;
    }
    finally
    {
      paramInternalContext.setDependency(localDependency);
    }
  }

  static Object[] getAll(Errors paramErrors, InternalContext paramInternalContext, SingleParameterInjector[] paramArrayOfSingleParameterInjector)
    throws ErrorsException
  {
    if (paramArrayOfSingleParameterInjector == null)
      return NO_ARGUMENTS;
    int i = paramErrors.size();
    int j = paramArrayOfSingleParameterInjector.length;
    Object[] arrayOfObject = new Object[j];
    for (int k = 0; k < j; k++)
    {
      SingleParameterInjector localSingleParameterInjector = paramArrayOfSingleParameterInjector[k];
      try
      {
        arrayOfObject[k] = localSingleParameterInjector.inject(paramErrors, paramInternalContext);
      }
      catch (ErrorsException localErrorsException)
      {
        paramErrors.merge(localErrorsException.getErrors());
      }
    }
    paramErrors.throwIfNewErrors(i);
    return arrayOfObject;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.SingleParameterInjector
 * JD-Core Version:    0.6.2
 */