package com.google.inject.spi;

import com.google.inject.Binder;
import com.google.inject.internal.util..Preconditions;

public final class RequireExplicitBindingsOption
  implements Element
{
  private final Object source;

  RequireExplicitBindingsOption(Object paramObject)
  {
    this.source = $Preconditions.checkNotNull(paramObject, "source");
  }

  public Object getSource()
  {
    return this.source;
  }

  public void applyTo(Binder paramBinder)
  {
    paramBinder.withSource(getSource()).requireExplicitBindings();
  }

  public Object acceptVisitor(ElementVisitor paramElementVisitor)
  {
    return paramElementVisitor.visit(this);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.spi.RequireExplicitBindingsOption
 * JD-Core Version:    0.6.2
 */