package com.google.inject.internal;

import com.google.inject.Binder;
import com.google.inject.Key;
import com.google.inject.internal.util..Objects;
import com.google.inject.internal.util..ToStringBuilder;
import com.google.inject.spi.BindingTargetVisitor;
import com.google.inject.spi.Dependency;
import com.google.inject.spi.UntargettedBinding;

final class UntargettedBindingImpl extends BindingImpl
  implements UntargettedBinding
{
  UntargettedBindingImpl(InjectorImpl paramInjectorImpl, Key paramKey, Object paramObject)
  {
    super(paramInjectorImpl, paramKey, paramObject, new InternalFactory()
    {
      public Object get(Errors paramAnonymousErrors, InternalContext paramAnonymousInternalContext, Dependency paramAnonymousDependency, boolean paramAnonymousBoolean)
      {
        throw new AssertionError();
      }
    }
    , Scoping.UNSCOPED);
  }

  public UntargettedBindingImpl(Object paramObject, Key paramKey, Scoping paramScoping)
  {
    super(paramObject, paramKey, paramScoping);
  }

  public Object acceptTargetVisitor(BindingTargetVisitor paramBindingTargetVisitor)
  {
    return paramBindingTargetVisitor.visit(this);
  }

  public BindingImpl withScoping(Scoping paramScoping)
  {
    return new UntargettedBindingImpl(getSource(), getKey(), paramScoping);
  }

  public BindingImpl withKey(Key paramKey)
  {
    return new UntargettedBindingImpl(getSource(), paramKey, getScoping());
  }

  public void applyTo(Binder paramBinder)
  {
    getScoping().applyTo(paramBinder.withSource(getSource()).bind(getKey()));
  }

  public String toString()
  {
    return new $ToStringBuilder(UntargettedBinding.class).add("key", getKey()).add("source", getSource()).toString();
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof UntargettedBindingImpl))
    {
      UntargettedBindingImpl localUntargettedBindingImpl = (UntargettedBindingImpl)paramObject;
      return (getKey().equals(localUntargettedBindingImpl.getKey())) && (getScoping().equals(localUntargettedBindingImpl.getScoping()));
    }
    return false;
  }

  public int hashCode()
  {
    return .Objects.hashCode(new Object[] { getKey(), getScoping() });
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.UntargettedBindingImpl
 * JD-Core Version:    0.6.2
 */