package com.google.inject.spi;

import com.google.inject.Binding;
import com.google.inject.Provider;
import java.util.Set;

public abstract interface ProviderInstanceBinding extends Binding, HasDependencies
{
  public abstract Provider getProviderInstance();

  public abstract Set getInjectionPoints();
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.spi.ProviderInstanceBinding
 * JD-Core Version:    0.6.2
 */