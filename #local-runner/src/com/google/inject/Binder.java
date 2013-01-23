package com.google.inject;

import com.google.inject.binder.AnnotatedBindingBuilder;
import com.google.inject.binder.AnnotatedConstantBindingBuilder;
import com.google.inject.binder.LinkedBindingBuilder;
import com.google.inject.matcher.Matcher;
import com.google.inject.spi.Message;
import com.google.inject.spi.TypeConverter;
import com.google.inject.spi.TypeListener;
import org.aopalliance.intercept.MethodInterceptor;

public abstract interface Binder
{
  public abstract void bindInterceptor(Matcher paramMatcher1, Matcher paramMatcher2, MethodInterceptor[] paramArrayOfMethodInterceptor);

  public abstract void bindScope(Class paramClass, Scope paramScope);

  public abstract LinkedBindingBuilder bind(Key paramKey);

  public abstract AnnotatedBindingBuilder bind(TypeLiteral paramTypeLiteral);

  public abstract AnnotatedBindingBuilder bind(Class paramClass);

  public abstract AnnotatedConstantBindingBuilder bindConstant();

  public abstract void requestInjection(TypeLiteral paramTypeLiteral, Object paramObject);

  public abstract void requestInjection(Object paramObject);

  public abstract void requestStaticInjection(Class[] paramArrayOfClass);

  public abstract void install(Module paramModule);

  public abstract Stage currentStage();

  public abstract void addError(String paramString, Object[] paramArrayOfObject);

  public abstract void addError(Throwable paramThrowable);

  public abstract void addError(Message paramMessage);

  public abstract Provider getProvider(Key paramKey);

  public abstract Provider getProvider(Class paramClass);

  public abstract MembersInjector getMembersInjector(TypeLiteral paramTypeLiteral);

  public abstract MembersInjector getMembersInjector(Class paramClass);

  public abstract void convertToTypes(Matcher paramMatcher, TypeConverter paramTypeConverter);

  public abstract void bindListener(Matcher paramMatcher, TypeListener paramTypeListener);

  public abstract Binder withSource(Object paramObject);

  public abstract Binder skipSources(Class[] paramArrayOfClass);

  public abstract PrivateBinder newPrivateBinder();

  public abstract void requireExplicitBindings();

  public abstract void disableCircularProxies();
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.Binder
 * JD-Core Version:    0.6.2
 */