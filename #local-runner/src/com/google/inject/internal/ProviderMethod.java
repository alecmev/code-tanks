package com.google.inject.internal;

import com.google.inject.Binder;
import com.google.inject.Exposed;
import com.google.inject.Key;
import com.google.inject.PrivateBinder;
import com.google.inject.Provider;
import com.google.inject.binder.LinkedBindingBuilder;
import com.google.inject.binder.ScopedBindingBuilder;
import com.google.inject.internal.util..ImmutableSet;
import com.google.inject.internal.util..Objects;
import com.google.inject.internal.util..StackTraceElements;
import com.google.inject.spi.ProviderWithDependencies;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

public class ProviderMethod
  implements ProviderWithDependencies
{
  private final Key key;
  private final Class scopeAnnotation;
  private final Object instance;
  private final Method method;
  private final .ImmutableSet dependencies;
  private final List parameterProviders;
  private final boolean exposed;

  ProviderMethod(Key paramKey, Method paramMethod, Object paramObject, .ImmutableSet paramImmutableSet, List paramList, Class paramClass)
  {
    this.key = paramKey;
    this.scopeAnnotation = paramClass;
    this.instance = paramObject;
    this.dependencies = paramImmutableSet;
    this.method = paramMethod;
    this.parameterProviders = paramList;
    this.exposed = paramMethod.isAnnotationPresent(Exposed.class);
    paramMethod.setAccessible(true);
  }

  public Key getKey()
  {
    return this.key;
  }

  public Method getMethod()
  {
    return this.method;
  }

  public Object getInstance()
  {
    return this.instance;
  }

  public void configure(Binder paramBinder)
  {
    paramBinder = paramBinder.withSource(this.method);
    if (this.scopeAnnotation != null)
      paramBinder.bind(this.key).toProvider(this).in(this.scopeAnnotation);
    else
      paramBinder.bind(this.key).toProvider(this);
    if (this.exposed)
      ((PrivateBinder)paramBinder).expose(this.key);
  }

  public Object get()
  {
    Object[] arrayOfObject = new Object[this.parameterProviders.size()];
    for (int i = 0; i < arrayOfObject.length; i++)
      arrayOfObject[i] = ((Provider)this.parameterProviders.get(i)).get();
    try
    {
      Object localObject = this.method.invoke(this.instance, arrayOfObject);
      return localObject;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new AssertionError(localIllegalAccessException);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      throw Exceptions.throwCleanly(localInvocationTargetException);
    }
  }

  public Set getDependencies()
  {
    return this.dependencies;
  }

  public String toString()
  {
    return "@Provides " + .StackTraceElements.forMember(this.method).toString();
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof ProviderMethod))
    {
      ProviderMethod localProviderMethod = (ProviderMethod)paramObject;
      return (this.method.equals(localProviderMethod.method)) && (this.instance.equals(localProviderMethod.instance));
    }
    return false;
  }

  public int hashCode()
  {
    return .Objects.hashCode(new Object[] { this.method });
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.ProviderMethod
 * JD-Core Version:    0.6.2
 */