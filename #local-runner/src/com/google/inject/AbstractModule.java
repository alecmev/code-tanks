package com.google.inject;

import com.google.inject.binder.AnnotatedBindingBuilder;
import com.google.inject.binder.AnnotatedConstantBindingBuilder;
import com.google.inject.binder.LinkedBindingBuilder;
import com.google.inject.internal.util..Preconditions;
import com.google.inject.matcher.Matcher;
import com.google.inject.spi.Message;
import com.google.inject.spi.TypeConverter;
import com.google.inject.spi.TypeListener;
import org.aopalliance.intercept.MethodInterceptor;

public abstract class AbstractModule
  implements Module
{
  Binder binder;

  public final synchronized void configure(Binder paramBinder)
  {
    $Preconditions.checkState(this.binder == null, "Re-entry is not allowed.");
    this.binder = ((Binder).Preconditions.checkNotNull(paramBinder, "builder"));
    try
    {
      configure();
    }
    finally
    {
      this.binder = null;
    }
  }

  protected abstract void configure();

  protected Binder binder()
  {
    return this.binder;
  }

  protected void bindScope(Class paramClass, Scope paramScope)
  {
    this.binder.bindScope(paramClass, paramScope);
  }

  protected LinkedBindingBuilder bind(Key paramKey)
  {
    return this.binder.bind(paramKey);
  }

  protected AnnotatedBindingBuilder bind(TypeLiteral paramTypeLiteral)
  {
    return this.binder.bind(paramTypeLiteral);
  }

  protected AnnotatedBindingBuilder bind(Class paramClass)
  {
    return this.binder.bind(paramClass);
  }

  protected AnnotatedConstantBindingBuilder bindConstant()
  {
    return this.binder.bindConstant();
  }

  protected void install(Module paramModule)
  {
    this.binder.install(paramModule);
  }

  protected void addError(String paramString, Object[] paramArrayOfObject)
  {
    this.binder.addError(paramString, paramArrayOfObject);
  }

  protected void addError(Throwable paramThrowable)
  {
    this.binder.addError(paramThrowable);
  }

  protected void addError(Message paramMessage)
  {
    this.binder.addError(paramMessage);
  }

  protected void requestInjection(Object paramObject)
  {
    this.binder.requestInjection(paramObject);
  }

  protected void requestStaticInjection(Class[] paramArrayOfClass)
  {
    this.binder.requestStaticInjection(paramArrayOfClass);
  }

  protected void bindInterceptor(Matcher paramMatcher1, Matcher paramMatcher2, MethodInterceptor[] paramArrayOfMethodInterceptor)
  {
    this.binder.bindInterceptor(paramMatcher1, paramMatcher2, paramArrayOfMethodInterceptor);
  }

  protected void requireBinding(Key paramKey)
  {
    this.binder.getProvider(paramKey);
  }

  protected void requireBinding(Class paramClass)
  {
    this.binder.getProvider(paramClass);
  }

  protected Provider getProvider(Key paramKey)
  {
    return this.binder.getProvider(paramKey);
  }

  protected Provider getProvider(Class paramClass)
  {
    return this.binder.getProvider(paramClass);
  }

  protected void convertToTypes(Matcher paramMatcher, TypeConverter paramTypeConverter)
  {
    this.binder.convertToTypes(paramMatcher, paramTypeConverter);
  }

  protected Stage currentStage()
  {
    return this.binder.currentStage();
  }

  protected MembersInjector getMembersInjector(Class paramClass)
  {
    return this.binder.getMembersInjector(paramClass);
  }

  protected MembersInjector getMembersInjector(TypeLiteral paramTypeLiteral)
  {
    return this.binder.getMembersInjector(paramTypeLiteral);
  }

  protected void bindListener(Matcher paramMatcher, TypeListener paramTypeListener)
  {
    this.binder.bindListener(paramMatcher, paramTypeListener);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.AbstractModule
 * JD-Core Version:    0.6.2
 */