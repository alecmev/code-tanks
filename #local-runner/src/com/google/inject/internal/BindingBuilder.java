package com.google.inject.internal;

import com.google.inject.Binder;
import com.google.inject.ConfigurationException;
import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.TypeLiteral;
import com.google.inject.binder.AnnotatedBindingBuilder;
import com.google.inject.binder.ScopedBindingBuilder;
import com.google.inject.internal.util..ImmutableSet;
import com.google.inject.internal.util..Preconditions;
import com.google.inject.spi.InjectionPoint;
import com.google.inject.spi.Message;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class BindingBuilder extends AbstractBindingBuilder
  implements AnnotatedBindingBuilder
{
  public BindingBuilder(Binder paramBinder, List paramList, Object paramObject, Key paramKey)
  {
    super(paramBinder, paramList, paramObject, paramKey);
  }

  public BindingBuilder annotatedWith(Class paramClass)
  {
    annotatedWithInternal(paramClass);
    return this;
  }

  public BindingBuilder annotatedWith(Annotation paramAnnotation)
  {
    annotatedWithInternal(paramAnnotation);
    return this;
  }

  public BindingBuilder to(Class paramClass)
  {
    return to(Key.get(paramClass));
  }

  public BindingBuilder to(TypeLiteral paramTypeLiteral)
  {
    return to(Key.get(paramTypeLiteral));
  }

  public BindingBuilder to(Key paramKey)
  {
    $Preconditions.checkNotNull(paramKey, "linkedKey");
    checkNotTargetted();
    BindingImpl localBindingImpl = getBinding();
    setBinding(new LinkedBindingImpl(localBindingImpl.getSource(), localBindingImpl.getKey(), localBindingImpl.getScoping(), paramKey));
    return this;
  }

  public void toInstance(Object paramObject)
  {
    checkNotTargetted();
    Object localObject;
    if (paramObject != null)
    {
      try
      {
        localObject = InjectionPoint.forInstanceMethodsAndFields(paramObject.getClass());
      }
      catch (ConfigurationException localConfigurationException)
      {
        copyErrorsToBinder(localConfigurationException);
        localObject = (Set)localConfigurationException.getPartialValue();
      }
    }
    else
    {
      this.binder.addError("Binding to null instances is not allowed. Use toProvider(Providers.of(null)) if this is your intended behaviour.", new Object[0]);
      localObject = $ImmutableSet.of();
    }
    BindingImpl localBindingImpl = getBinding();
    setBinding(new InstanceBindingImpl(localBindingImpl.getSource(), localBindingImpl.getKey(), Scoping.EAGER_SINGLETON, (Set)localObject, paramObject));
  }

  public BindingBuilder toProvider(Provider paramProvider)
  {
    $Preconditions.checkNotNull(paramProvider, "provider");
    checkNotTargetted();
    Set localSet;
    try
    {
      localSet = InjectionPoint.forInstanceMethodsAndFields(paramProvider.getClass());
    }
    catch (ConfigurationException localConfigurationException)
    {
      copyErrorsToBinder(localConfigurationException);
      localSet = (Set)localConfigurationException.getPartialValue();
    }
    BindingImpl localBindingImpl = getBinding();
    setBinding(new ProviderInstanceBindingImpl(localBindingImpl.getSource(), localBindingImpl.getKey(), localBindingImpl.getScoping(), localSet, paramProvider));
    return this;
  }

  public BindingBuilder toProvider(Class paramClass)
  {
    return toProvider(Key.get(paramClass));
  }

  public BindingBuilder toProvider(TypeLiteral paramTypeLiteral)
  {
    return toProvider(Key.get(paramTypeLiteral));
  }

  public BindingBuilder toProvider(Key paramKey)
  {
    $Preconditions.checkNotNull(paramKey, "providerKey");
    checkNotTargetted();
    BindingImpl localBindingImpl = getBinding();
    setBinding(new LinkedProviderBindingImpl(localBindingImpl.getSource(), localBindingImpl.getKey(), localBindingImpl.getScoping(), paramKey));
    return this;
  }

  public ScopedBindingBuilder toConstructor(Constructor paramConstructor)
  {
    return toConstructor(paramConstructor, TypeLiteral.get(paramConstructor.getDeclaringClass()));
  }

  public ScopedBindingBuilder toConstructor(Constructor paramConstructor, TypeLiteral paramTypeLiteral)
  {
    $Preconditions.checkNotNull(paramConstructor, "constructor");
    $Preconditions.checkNotNull(paramTypeLiteral, "type");
    checkNotTargetted();
    BindingImpl localBindingImpl = getBinding();
    Set localSet;
    try
    {
      localSet = InjectionPoint.forInstanceMethodsAndFields(paramTypeLiteral);
    }
    catch (ConfigurationException localConfigurationException1)
    {
      copyErrorsToBinder(localConfigurationException1);
      localSet = (Set)localConfigurationException1.getPartialValue();
    }
    try
    {
      InjectionPoint localInjectionPoint = InjectionPoint.forConstructor(paramConstructor, paramTypeLiteral);
      setBinding(new ConstructorBindingImpl(localBindingImpl.getKey(), localBindingImpl.getSource(), localBindingImpl.getScoping(), localInjectionPoint, localSet));
    }
    catch (ConfigurationException localConfigurationException2)
    {
      copyErrorsToBinder(localConfigurationException2);
    }
    return this;
  }

  public String toString()
  {
    return "BindingBuilder<" + getBinding().getKey().getTypeLiteral() + ">";
  }

  private void copyErrorsToBinder(ConfigurationException paramConfigurationException)
  {
    Iterator localIterator = paramConfigurationException.getErrorMessages().iterator();
    while (localIterator.hasNext())
    {
      Message localMessage = (Message)localIterator.next();
      this.binder.addError(localMessage);
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.BindingBuilder
 * JD-Core Version:    0.6.2
 */