package com.google.inject.internal;

import com.google.inject.internal.util..ImmutableMap;
import com.google.inject.spi.InjectionPoint;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

abstract interface ConstructionProxy
{
  public abstract Object newInstance(Object[] paramArrayOfObject)
    throws InvocationTargetException;

  public abstract InjectionPoint getInjectionPoint();

  public abstract Constructor getConstructor();

  public abstract $ImmutableMap getMethodInterceptors();
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.ConstructionProxy
 * JD-Core Version:    0.6.2
 */