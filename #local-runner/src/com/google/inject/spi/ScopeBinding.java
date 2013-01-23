package com.google.inject.spi;

import com.google.inject.Binder;
import com.google.inject.Scope;
import com.google.inject.internal.util..Preconditions;

public final class ScopeBinding
  implements Element
{
  private final Object source;
  private final Class annotationType;
  private final Scope scope;

  ScopeBinding(Object paramObject, Class paramClass, Scope paramScope)
  {
    this.source = $Preconditions.checkNotNull(paramObject, "source");
    this.annotationType = ((Class).Preconditions.checkNotNull(paramClass, "annotationType"));
    this.scope = ((Scope).Preconditions.checkNotNull(paramScope, "scope"));
  }

  public Object getSource()
  {
    return this.source;
  }

  public Class getAnnotationType()
  {
    return this.annotationType;
  }

  public Scope getScope()
  {
    return this.scope;
  }

  public Object acceptVisitor(ElementVisitor paramElementVisitor)
  {
    return paramElementVisitor.visit(this);
  }

  public void applyTo(Binder paramBinder)
  {
    paramBinder.withSource(getSource()).bindScope(this.annotationType, this.scope);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.spi.ScopeBinding
 * JD-Core Version:    0.6.2
 */