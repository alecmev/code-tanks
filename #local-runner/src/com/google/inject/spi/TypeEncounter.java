package com.google.inject.spi;

import com.google.inject.Key;
import com.google.inject.MembersInjector;
import com.google.inject.Provider;
import com.google.inject.TypeLiteral;
import com.google.inject.matcher.Matcher;
import org.aopalliance.intercept.MethodInterceptor;

public abstract interface TypeEncounter
{
  public abstract void addError(String paramString, Object[] paramArrayOfObject);

  public abstract void addError(Throwable paramThrowable);

  public abstract void addError(Message paramMessage);

  public abstract Provider getProvider(Key paramKey);

  public abstract Provider getProvider(Class paramClass);

  public abstract MembersInjector getMembersInjector(TypeLiteral paramTypeLiteral);

  public abstract MembersInjector getMembersInjector(Class paramClass);

  public abstract void register(MembersInjector paramMembersInjector);

  public abstract void register(InjectionListener paramInjectionListener);

  public abstract void bindInterceptor(Matcher paramMatcher, MethodInterceptor[] paramArrayOfMethodInterceptor);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.spi.TypeEncounter
 * JD-Core Version:    0.6.2
 */