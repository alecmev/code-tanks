package com.google.inject.spi;

import com.google.inject.Scope;

public class DefaultBindingScopingVisitor
  implements BindingScopingVisitor
{
  protected Object visitOther()
  {
    return null;
  }

  public Object visitEagerSingleton()
  {
    return visitOther();
  }

  public Object visitScope(Scope paramScope)
  {
    return visitOther();
  }

  public Object visitScopeAnnotation(Class paramClass)
  {
    return visitOther();
  }

  public Object visitNoScoping()
  {
    return visitOther();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.spi.DefaultBindingScopingVisitor
 * JD-Core Version:    0.6.2
 */