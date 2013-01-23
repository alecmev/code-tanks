package com.google.inject.spi;

import com.google.inject.Binder;
import com.google.inject.Binding;

public abstract interface ExposedBinding extends Binding, HasDependencies
{
  public abstract PrivateElements getPrivateElements();

  public abstract void applyTo(Binder paramBinder);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.spi.ExposedBinding
 * JD-Core Version:    0.6.2
 */