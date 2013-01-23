package com.google.inject.internal;

import com.google.inject.Binder;
import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.binder.LinkedBindingBuilder;
import com.google.inject.internal.util..ImmutableSet;
import com.google.inject.internal.util..Objects;
import com.google.inject.internal.util..ToStringBuilder;
import com.google.inject.spi.BindingTargetVisitor;
import com.google.inject.spi.Dependency;
import com.google.inject.spi.HasDependencies;
import com.google.inject.spi.ProviderInstanceBinding;
import com.google.inject.spi.ProviderWithExtensionVisitor;
import java.util.Set;

final class ProviderInstanceBindingImpl extends BindingImpl
  implements ProviderInstanceBinding
{
  final Provider providerInstance;
  final .ImmutableSet injectionPoints;

  public ProviderInstanceBindingImpl(InjectorImpl paramInjectorImpl, Key paramKey, Object paramObject, InternalFactory paramInternalFactory, Scoping paramScoping, Provider paramProvider, Set paramSet)
  {
    super(paramInjectorImpl, paramKey, paramObject, paramInternalFactory, paramScoping);
    this.providerInstance = paramProvider;
    this.injectionPoints = $ImmutableSet.copyOf(paramSet);
  }

  public ProviderInstanceBindingImpl(Object paramObject, Key paramKey, Scoping paramScoping, Set paramSet, Provider paramProvider)
  {
    super(paramObject, paramKey, paramScoping);
    this.injectionPoints = $ImmutableSet.copyOf(paramSet);
    this.providerInstance = paramProvider;
  }

  public Object acceptTargetVisitor(BindingTargetVisitor paramBindingTargetVisitor)
  {
    if ((this.providerInstance instanceof ProviderWithExtensionVisitor))
      return ((ProviderWithExtensionVisitor)this.providerInstance).acceptExtensionVisitor(paramBindingTargetVisitor, this);
    return paramBindingTargetVisitor.visit(this);
  }

  public Provider getProviderInstance()
  {
    return this.providerInstance;
  }

  public Set getInjectionPoints()
  {
    return this.injectionPoints;
  }

  public Set getDependencies()
  {
    return (this.providerInstance instanceof HasDependencies) ? .ImmutableSet.copyOf(((HasDependencies)this.providerInstance).getDependencies()) : Dependency.forInjectionPoints(this.injectionPoints);
  }

  public BindingImpl withScoping(Scoping paramScoping)
  {
    return new ProviderInstanceBindingImpl(getSource(), getKey(), paramScoping, this.injectionPoints, this.providerInstance);
  }

  public BindingImpl withKey(Key paramKey)
  {
    return new ProviderInstanceBindingImpl(getSource(), paramKey, getScoping(), this.injectionPoints, this.providerInstance);
  }

  public void applyTo(Binder paramBinder)
  {
    getScoping().applyTo(paramBinder.withSource(getSource()).bind(getKey()).toProvider(getProviderInstance()));
  }

  public String toString()
  {
    return new $ToStringBuilder(ProviderInstanceBinding.class).add("key", getKey()).add("source", getSource()).add("scope", getScoping()).add("provider", this.providerInstance).toString();
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof ProviderInstanceBindingImpl))
    {
      ProviderInstanceBindingImpl localProviderInstanceBindingImpl = (ProviderInstanceBindingImpl)paramObject;
      return (getKey().equals(localProviderInstanceBindingImpl.getKey())) && (getScoping().equals(localProviderInstanceBindingImpl.getScoping())) && ($Objects.equal(this.providerInstance, localProviderInstanceBindingImpl.providerInstance));
    }
    return false;
  }

  public int hashCode()
  {
    return .Objects.hashCode(new Object[] { getKey(), getScoping() });
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.ProviderInstanceBindingImpl
 * JD-Core Version:    0.6.2
 */