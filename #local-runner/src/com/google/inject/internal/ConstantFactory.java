package com.google.inject.internal;

import com.google.inject.internal.util..ToStringBuilder;
import com.google.inject.spi.Dependency;

final class ConstantFactory
  implements InternalFactory
{
  private final Initializable initializable;

  public ConstantFactory(Initializable paramInitializable)
  {
    this.initializable = paramInitializable;
  }

  public Object get(Errors paramErrors, InternalContext paramInternalContext, Dependency paramDependency, boolean paramBoolean)
    throws ErrorsException
  {
    return this.initializable.get(paramErrors);
  }

  public String toString()
  {
    return new $ToStringBuilder(ConstantFactory.class).add("value", this.initializable).toString();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.ConstantFactory
 * JD-Core Version:    0.6.2
 */