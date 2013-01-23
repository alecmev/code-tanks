package com.google.inject.spi;

import com.google.inject.Scope;

public abstract interface BindingScopingVisitor
{
  public abstract Object visitEagerSingleton();

  public abstract Object visitScope(Scope paramScope);

  public abstract Object visitScopeAnnotation(Class paramClass);

  public abstract Object visitNoScoping();
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.spi.BindingScopingVisitor
 * JD-Core Version:    0.6.2
 */