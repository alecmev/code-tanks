package com.google.inject.spi;

import com.google.inject.Binder;
import com.google.inject.internal.util..Preconditions;

public final class DisableCircularProxiesOption
  implements Element
{
  private final Object source;

  DisableCircularProxiesOption(Object paramObject)
  {
    this.source = $Preconditions.checkNotNull(paramObject, "source");
  }

  public Object getSource()
  {
    return this.source;
  }

  public void applyTo(Binder paramBinder)
  {
    paramBinder.withSource(getSource()).disableCircularProxies();
  }

  public Object acceptVisitor(ElementVisitor paramElementVisitor)
  {
    return paramElementVisitor.visit(this);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.spi.DisableCircularProxiesOption
 * JD-Core Version:    0.6.2
 */