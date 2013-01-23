package com.google.inject.internal;

import com.google.inject.internal.cglib.core..CodeGenerationException;
import com.google.inject.internal.cglib.reflect..FastClass;
import com.google.inject.internal.cglib.reflect..FastMethod;
import com.google.inject.spi.InjectionPoint;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

final class SingleMethodInjector
  implements SingleMemberInjector
{
  private final InjectorImpl.MethodInvoker methodInvoker;
  private final SingleParameterInjector[] parameterInjectors;
  private final InjectionPoint injectionPoint;

  SingleMethodInjector(InjectorImpl paramInjectorImpl, InjectionPoint paramInjectionPoint, Errors paramErrors)
    throws ErrorsException
  {
    this.injectionPoint = paramInjectionPoint;
    Method localMethod = (Method)paramInjectionPoint.getMember();
    this.methodInvoker = createMethodInvoker(localMethod);
    this.parameterInjectors = paramInjectorImpl.getParametersInjectors(paramInjectionPoint.getDependencies(), paramErrors);
  }

  private InjectorImpl.MethodInvoker createMethodInvoker(final Method paramMethod)
  {
    int i = paramMethod.getModifiers();
    if ((!Modifier.isPrivate(i)) && (!Modifier.isProtected(i)))
      try
      {
        final .FastMethod localFastMethod = BytecodeGen.newFastClass(paramMethod.getDeclaringClass(), BytecodeGen.Visibility.forMember(paramMethod)).getMethod(paramMethod);
        return new InjectorImpl.MethodInvoker()
        {
          public Object invoke(Object paramAnonymousObject, Object[] paramAnonymousArrayOfObject)
            throws IllegalAccessException, InvocationTargetException
          {
            return localFastMethod.invoke(paramAnonymousObject, paramAnonymousArrayOfObject);
          }
        };
      }
      catch ($CodeGenerationException localCodeGenerationException)
      {
      }
    if ((!Modifier.isPublic(i)) || (!Modifier.isPublic(paramMethod.getDeclaringClass().getModifiers())))
      paramMethod.setAccessible(true);
    return new InjectorImpl.MethodInvoker()
    {
      public Object invoke(Object paramAnonymousObject, Object[] paramAnonymousArrayOfObject)
        throws IllegalAccessException, InvocationTargetException
      {
        return paramMethod.invoke(paramAnonymousObject, paramAnonymousArrayOfObject);
      }
    };
  }

  public InjectionPoint getInjectionPoint()
  {
    return this.injectionPoint;
  }

  public void inject(Errors paramErrors, InternalContext paramInternalContext, Object paramObject)
  {
    Object[] arrayOfObject;
    try
    {
      arrayOfObject = SingleParameterInjector.getAll(paramErrors, paramInternalContext, this.parameterInjectors);
    }
    catch (ErrorsException localErrorsException)
    {
      paramErrors.merge(localErrorsException.getErrors());
      return;
    }
    try
    {
      this.methodInvoker.invoke(paramObject, arrayOfObject);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new AssertionError(localIllegalAccessException);
    }
    catch (InvocationTargetException localInvocationTargetException1)
    {
      InvocationTargetException localInvocationTargetException2 = localInvocationTargetException1.getCause() != null ? localInvocationTargetException1.getCause() : localInvocationTargetException1;
      paramErrors.withSource(this.injectionPoint).errorInjectingMethod(localInvocationTargetException2);
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.SingleMethodInjector
 * JD-Core Version:    0.6.2
 */