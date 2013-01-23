package com.google.inject.spi;

import com.google.inject.Provider;

public abstract interface ProviderWithExtensionVisitor extends Provider
{
  public abstract Object acceptExtensionVisitor(BindingTargetVisitor paramBindingTargetVisitor, ProviderInstanceBinding paramProviderInstanceBinding);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.spi.ProviderWithExtensionVisitor
 * JD-Core Version:    0.6.2
 */