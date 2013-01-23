package com.google.inject.internal;

import com.google.inject.internal.cglib.core..CodeGenerationException;
import com.google.inject.internal.cglib.reflect..FastClass;
import com.google.inject.internal.cglib.reflect..FastConstructor;
import com.google.inject.internal.util..ImmutableMap;
import com.google.inject.spi.InjectionPoint;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

final class DefaultConstructionProxyFactory
  implements ConstructionProxyFactory
{
  private final InjectionPoint injectionPoint;

  DefaultConstructionProxyFactory(InjectionPoint paramInjectionPoint)
  {
    this.injectionPoint = paramInjectionPoint;
  }

  public ConstructionProxy create()
  {
    final Constructor localConstructor = (Constructor)this.injectionPoint.getMember();
    if (Modifier.isPublic(localConstructor.getModifiers()))
    {
      Class localClass = localConstructor.getDeclaringClass();
      try
      {
        final .FastConstructor localFastConstructor = BytecodeGen.newFastClass(localClass, BytecodeGen.Visibility.forMember(localConstructor)).getConstructor(localConstructor);
        return new ConstructionProxy()
        {
          public Object newInstance(Object[] paramAnonymousArrayOfObject)
            throws InvocationTargetException
          {
            return localFastConstructor.newInstance(paramAnonymousArrayOfObject);
          }

          public InjectionPoint getInjectionPoint()
          {
            return DefaultConstructionProxyFactory.this.injectionPoint;
          }

          public Constructor getConstructor()
          {
            return localConstructor;
          }

          public .ImmutableMap getMethodInterceptors()
          {
            return .ImmutableMap.of();
          }
        };
      }
      catch ($CodeGenerationException localCodeGenerationException)
      {
        if (!Modifier.isPublic(localClass.getModifiers()))
          localConstructor.setAccessible(true);
      }
    }
    else
    {
      localConstructor.setAccessible(true);
    }
    return new ConstructionProxy()
    {
      public Object newInstance(Object[] paramAnonymousArrayOfObject)
        throws InvocationTargetException
      {
        try
        {
          return localConstructor.newInstance(paramAnonymousArrayOfObject);
        }
        catch (InstantiationException localInstantiationException)
        {
          throw new AssertionError(localInstantiationException);
        }
        catch (IllegalAccessException localIllegalAccessException)
        {
          throw new AssertionError(localIllegalAccessException);
        }
      }

      public InjectionPoint getInjectionPoint()
      {
        return DefaultConstructionProxyFactory.this.injectionPoint;
      }

      public Constructor getConstructor()
      {
        return localConstructor;
      }

      public .ImmutableMap getMethodInterceptors()
      {
        return .ImmutableMap.of();
      }
    };
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.DefaultConstructionProxyFactory
 * JD-Core Version:    0.6.2
 */