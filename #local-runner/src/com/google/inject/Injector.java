package com.google.inject;

import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract interface Injector
{
  public abstract void injectMembers(Object paramObject);

  public abstract MembersInjector getMembersInjector(TypeLiteral paramTypeLiteral);

  public abstract MembersInjector getMembersInjector(Class paramClass);

  public abstract Map getBindings();

  public abstract Map getAllBindings();

  public abstract Binding getBinding(Key paramKey);

  public abstract Binding getBinding(Class paramClass);

  public abstract Binding getExistingBinding(Key paramKey);

  public abstract List findBindingsByType(TypeLiteral paramTypeLiteral);

  public abstract Provider getProvider(Key paramKey);

  public abstract Provider getProvider(Class paramClass);

  public abstract Object getInstance(Key paramKey);

  public abstract Object getInstance(Class paramClass);

  public abstract Injector getParent();

  public abstract Injector createChildInjector(Iterable paramIterable);

  public abstract Injector createChildInjector(Module[] paramArrayOfModule);

  public abstract Map getScopeBindings();

  public abstract Set getTypeConverterBindings();
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.Injector
 * JD-Core Version:    0.6.2
 */