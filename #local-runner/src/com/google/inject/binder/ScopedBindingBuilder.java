package com.google.inject.binder;

import com.google.inject.Scope;

public abstract interface ScopedBindingBuilder
{
  public abstract void in(Class paramClass);

  public abstract void in(Scope paramScope);

  public abstract void asEagerSingleton();
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.binder.ScopedBindingBuilder
 * JD-Core Version:    0.6.2
 */