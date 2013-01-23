package com.google.inject.spi;

import com.google.inject.Binding;
import java.util.Map;
import java.util.Set;

public abstract interface ConstructorBinding extends Binding, HasDependencies
{
  public abstract InjectionPoint getConstructor();

  public abstract Set getInjectableMembers();

  public abstract Map getMethodInterceptors();
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.spi.ConstructorBinding
 * JD-Core Version:    0.6.2
 */