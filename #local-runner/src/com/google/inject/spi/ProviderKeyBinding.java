package com.google.inject.spi;

import com.google.inject.Binding;
import com.google.inject.Key;

public abstract interface ProviderKeyBinding extends Binding
{
  public abstract Key getProviderKey();
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.spi.ProviderKeyBinding
 * JD-Core Version:    0.6.2
 */