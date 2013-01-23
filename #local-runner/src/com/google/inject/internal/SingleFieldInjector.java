package com.google.inject.internal;

import com.google.inject.spi.Dependency;
import com.google.inject.spi.InjectionPoint;
import java.lang.reflect.Field;
import java.util.List;

final class SingleFieldInjector
  implements SingleMemberInjector
{
  final Field field;
  final InjectionPoint injectionPoint;
  final Dependency dependency;
  final InternalFactory factory;

  public SingleFieldInjector(InjectorImpl paramInjectorImpl, InjectionPoint paramInjectionPoint, Errors paramErrors)
    throws ErrorsException
  {
    this.injectionPoint = paramInjectionPoint;
    this.field = ((Field)paramInjectionPoint.getMember());
    this.dependency = ((Dependency)paramInjectionPoint.getDependencies().get(0));
    this.field.setAccessible(true);
    this.factory = paramInjectorImpl.getInternalFactory(this.dependency.getKey(), paramErrors, InjectorImpl.JitLimitation.NO_JIT);
  }

  public InjectionPoint getInjectionPoint()
  {
    return this.injectionPoint;
  }

  public void inject(Errors paramErrors, InternalContext paramInternalContext, Object paramObject)
  {
    paramErrors = paramErrors.withSource(this.dependency);
    Dependency localDependency = paramInternalContext.setDependency(this.dependency);
    try
    {
      Object localObject1 = this.factory.get(paramErrors, paramInternalContext, this.dependency, false);
      this.field.set(paramObject, localObject1);
    }
    catch (ErrorsException localErrorsException)
    {
      paramErrors.withSource(this.injectionPoint).merge(localErrorsException.getErrors());
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new AssertionError(localIllegalAccessException);
    }
    finally
    {
      paramInternalContext.setDependency(localDependency);
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.SingleFieldInjector
 * JD-Core Version:    0.6.2
 */