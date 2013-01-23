package com.google.inject.internal;

import com.google.inject.Binder;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.internal.util..ImmutableSet;
import com.google.inject.internal.util..ToStringBuilder;
import com.google.inject.spi.BindingTargetVisitor;
import com.google.inject.spi.Dependency;
import com.google.inject.spi.ExposedBinding;
import com.google.inject.spi.PrivateElements;
import java.util.Set;

public final class ExposedBindingImpl extends BindingImpl
  implements ExposedBinding
{
  private final PrivateElements privateElements;

  public ExposedBindingImpl(InjectorImpl paramInjectorImpl, Object paramObject, Key paramKey, InternalFactory paramInternalFactory, PrivateElements paramPrivateElements)
  {
    super(paramInjectorImpl, paramKey, paramObject, paramInternalFactory, Scoping.UNSCOPED);
    this.privateElements = paramPrivateElements;
  }

  public Object acceptTargetVisitor(BindingTargetVisitor paramBindingTargetVisitor)
  {
    return paramBindingTargetVisitor.visit(this);
  }

  public Set getDependencies()
  {
    return .ImmutableSet.of(Dependency.get(Key.get(Injector.class)));
  }

  public PrivateElements getPrivateElements()
  {
    return this.privateElements;
  }

  public String toString()
  {
    return new $ToStringBuilder(ExposedBinding.class).add("key", getKey()).add("source", getSource()).add("privateElements", this.privateElements).toString();
  }

  public void applyTo(Binder paramBinder)
  {
    throw new UnsupportedOperationException("This element represents a synthetic binding.");
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.ExposedBindingImpl
 * JD-Core Version:    0.6.2
 */