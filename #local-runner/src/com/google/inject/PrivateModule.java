package com.google.inject;

import com.google.inject.binder.AnnotatedBindingBuilder;
import com.google.inject.binder.AnnotatedConstantBindingBuilder;
import com.google.inject.binder.AnnotatedElementBuilder;
import com.google.inject.binder.LinkedBindingBuilder;
import com.google.inject.internal.util..Preconditions;
import com.google.inject.matcher.Matcher;
import com.google.inject.spi.Message;
import com.google.inject.spi.TypeConverter;
import com.google.inject.spi.TypeListener;
import org.aopalliance.intercept.MethodInterceptor;

public abstract class PrivateModule
  implements Module
{
  private PrivateBinder binder;

  public final synchronized void configure(Binder paramBinder)
  {
    $Preconditions.checkState(this.binder == null, "Re-entry is not allowed.");
    this.binder = ((PrivateBinder)paramBinder.skipSources(new Class[] { PrivateModule.class }));
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

  protected final void expose(Key paramKey)
  {
    this.binder.expose(paramKey);
  }

  protected final AnnotatedElementBuilder expose(Class paramClass)
  {
    return this.binder.expose(paramClass);
  }

  protected final AnnotatedElementBuilder expose(TypeLiteral paramTypeLiteral)
  {
    return this.binder.expose(paramTypeLiteral);
  }

  protected final PrivateBinder binder()
  {
    return this.binder;
  }

  protected final void bindScope(Class paramClass, Scope paramScope)
  {
    this.binder.bindScope(paramClass, paramScope);
  }

  protected final LinkedBindingBuilder bind(Key paramKey)
  {
    return this.binder.bind(paramKey);
  }

  protected final AnnotatedBindingBuilder bind(TypeLiteral paramTypeLiteral)
  {
    return this.binder.bind(paramTypeLiteral);
  }

  protected final AnnotatedBindingBuilder bind(Class paramClass)
  {
    return this.binder.bind(paramClass);
  }

  protected final AnnotatedConstantBindingBuilder bindConstant()
  {
    return this.binder.bindConstant();
  }

  protected final void install(Module paramModule)
  {
    this.binder.install(paramModule);
  }

  protected final void addError(String paramString, Object[] paramArrayOfObject)
  {
    this.binder.addError(paramString, paramArrayOfObject);
  }

  protected final void addError(Throwable paramThrowable)
  {
    this.binder.addError(paramThrowable);
  }

  protected final void addError(Message paramMessage)
  {
    this.binder.addError(paramMessage);
  }

  protected final void requestInjection(Object paramObject)
  {
    this.binder.requestInjection(paramObject);
  }

  protected final void requestStaticInjection(Class[] paramArrayOfClass)
  {
    this.binder.requestStaticInjection(paramArrayOfClass);
  }

  protected final void bindInterceptor(Matcher paramMatcher1, Matcher paramMatcher2, MethodInterceptor[] paramArrayOfMethodInterceptor)
  {
    this.binder.bindInterceptor(paramMatcher1, paramMatcher2, paramArrayOfMethodInterceptor);
  }

  protected final void requireBinding(Key paramKey)
  {
    this.binder.getProvider(paramKey);
  }

  protected final void requireBinding(Class paramClass)
  {
    this.binder.getProvider(paramClass);
  }

  protected final Provider getProvider(Key paramKey)
  {
    return this.binder.getProvider(paramKey);
  }

  protected final Provider getProvider(Class paramClass)
  {
    return this.binder.getProvider(paramClass);
  }

  protected final void convertToTypes(Matcher paramMatcher, TypeConverter paramTypeConverter)
  {
    this.binder.convertToTypes(paramMatcher, paramTypeConverter);
  }

  protected final Stage currentStage()
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
 * Qualified Name:     com.google.inject.PrivateModule
 * JD-Core Version:    0.6.2
 */