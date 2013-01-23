package com.google.inject.binder;

import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.TypeLiteral;
import java.lang.reflect.Constructor;

public abstract interface LinkedBindingBuilder extends ScopedBindingBuilder
{
  public abstract ScopedBindingBuilder to(Class paramClass);

  public abstract ScopedBindingBuilder to(TypeLiteral paramTypeLiteral);

  public abstract ScopedBindingBuilder to(Key paramKey);

  public abstract void toInstance(Object paramObject);

  public abstract ScopedBindingBuilder toProvider(Provider paramProvider);

  public abstract ScopedBindingBuilder toProvider(Class paramClass);

  public abstract ScopedBindingBuilder toProvider(TypeLiteral paramTypeLiteral);

  public abstract ScopedBindingBuilder toProvider(Key paramKey);

  public abstract ScopedBindingBuilder toConstructor(Constructor paramConstructor);

  public abstract ScopedBindingBuilder toConstructor(Constructor paramConstructor, TypeLiteral paramTypeLiteral);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.binder.LinkedBindingBuilder
 * JD-Core Version:    0.6.2
 */