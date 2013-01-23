package com.google.inject.internal;

import com.google.inject.Provider;
import com.google.inject.internal.util..Preconditions;
import com.google.inject.spi.Dependency;

final class InternalFactoryToProviderAdapter
  implements InternalFactory
{
  private final Initializable initializable;
  private final Object source;

  public InternalFactoryToProviderAdapter(Initializable paramInitializable, Object paramObject)
  {
    this.initializable = ((Initializable).Preconditions.checkNotNull(paramInitializable, "provider"));
    this.source = $Preconditions.checkNotNull(paramObject, "source");
  }

  public Object get(Errors paramErrors, InternalContext paramInternalContext, Dependency paramDependency, boolean paramBoolean)
    throws ErrorsException
  {
    try
    {
      return paramErrors.checkForNull(((Provider)this.initializable.get(paramErrors)).get(), this.source, paramDependency);
    }
    catch (RuntimeException localRuntimeException)
    {
      throw paramErrors.withSource(this.source).errorInProvider(localRuntimeException).toException();
    }
  }

  public String toString()
  {
    return this.initializable.toString();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.InternalFactoryToProviderAdapter
 * JD-Core Version:    0.6.2
 */