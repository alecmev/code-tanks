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
import com.google.inject.spi.InstanceBinding;
import com.google.inject.util.Providers;
import java.util.Set;

final class InstanceBindingImpl extends BindingImpl
  implements InstanceBinding
{
  final Object instance;
  final Provider provider;
  final .ImmutableSet injectionPoints;

  public InstanceBindingImpl(InjectorImpl paramInjectorImpl, Key paramKey, Object paramObject1, InternalFactory paramInternalFactory, Set paramSet, Object paramObject2)
  {
    super(paramInjectorImpl, paramKey, paramObject1, paramInternalFactory, Scoping.EAGER_SINGLETON);
    this.injectionPoints = $ImmutableSet.copyOf(paramSet);
    this.instance = paramObject2;
    this.provider = Providers.of(paramObject2);
  }

  public InstanceBindingImpl(Object paramObject1, Key paramKey, Scoping paramScoping, Set paramSet, Object paramObject2)
  {
    super(paramObject1, paramKey, paramScoping);
    this.injectionPoints = $ImmutableSet.copyOf(paramSet);
    this.instance = paramObject2;
    this.provider = Providers.of(paramObject2);
  }

  public Provider getProvider()
  {
    return this.provider;
  }

  public Object acceptTargetVisitor(BindingTargetVisitor paramBindingTargetVisitor)
  {
    return paramBindingTargetVisitor.visit(this);
  }

  public Object getInstance()
  {
    return this.instance;
  }

  public Set getInjectionPoints()
  {
    return this.injectionPoints;
  }

  public Set getDependencies()
  {
    return (this.instance instanceof HasDependencies) ? .ImmutableSet.copyOf(((HasDependencies)this.instance).getDependencies()) : Dependency.forInjectionPoints(this.injectionPoints);
  }

  public BindingImpl withScoping(Scoping paramScoping)
  {
    return new InstanceBindingImpl(getSource(), getKey(), paramScoping, this.injectionPoints, this.instance);
  }

  public BindingImpl withKey(Key paramKey)
  {
    return new InstanceBindingImpl(getSource(), paramKey, getScoping(), this.injectionPoints, this.instance);
  }

  public void applyTo(Binder paramBinder)
  {
    paramBinder.withSource(getSource()).bind(getKey()).toInstance(this.instance);
  }

  public String toString()
  {
    return new $ToStringBuilder(InstanceBinding.class).add("key", getKey()).add("source", getSource()).add("instance", this.instance).toString();
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof InstanceBindingImpl))
    {
      InstanceBindingImpl localInstanceBindingImpl = (InstanceBindingImpl)paramObject;
      return (getKey().equals(localInstanceBindingImpl.getKey())) && (getScoping().equals(localInstanceBindingImpl.getScoping())) && ($Objects.equal(this.instance, localInstanceBindingImpl.instance));
    }
    return false;
  }

  public int hashCode()
  {
    return .Objects.hashCode(new Object[] { getKey(), getScoping() });
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.InstanceBindingImpl
 * JD-Core Version:    0.6.2
 */