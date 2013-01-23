package com.google.inject.spi;

import com.google.inject.Binding;
import java.util.Set;

public abstract interface InstanceBinding extends Binding, HasDependencies
{
  public abstract Object getInstance();

  public abstract Set getInjectionPoints();
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.spi.InstanceBinding
 * JD-Core Version:    0.6.2
 */