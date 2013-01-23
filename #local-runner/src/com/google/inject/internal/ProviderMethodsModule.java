package com.google.inject.internal;

import com.google.inject.Binder;
import com.google.inject.Key;
import com.google.inject.Module;
import com.google.inject.Provider;
import com.google.inject.Provides;
import com.google.inject.TypeLiteral;
import com.google.inject.binder.LinkedBindingBuilder;
import com.google.inject.internal.util..ImmutableSet;
import com.google.inject.internal.util..Lists;
import com.google.inject.internal.util..Preconditions;
import com.google.inject.spi.Dependency;
import com.google.inject.spi.Message;
import com.google.inject.util.Modules;
import java.lang.annotation.Annotation;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

public final class ProviderMethodsModule
  implements Module
{
  private final Object delegate;
  private final TypeLiteral typeLiteral;

  private ProviderMethodsModule(Object paramObject)
  {
    this.delegate = $Preconditions.checkNotNull(paramObject, "delegate");
    this.typeLiteral = TypeLiteral.get(this.delegate.getClass());
  }

  public static Module forModule(Module paramModule)
  {
    return forObject(paramModule);
  }

  public static Module forObject(Object paramObject)
  {
    if ((paramObject instanceof ProviderMethodsModule))
      return Modules.EMPTY_MODULE;
    return new ProviderMethodsModule(paramObject);
  }

  public synchronized void configure(Binder paramBinder)
  {
    Iterator localIterator = getProviderMethods(paramBinder).iterator();
    while (localIterator.hasNext())
    {
      ProviderMethod localProviderMethod = (ProviderMethod)localIterator.next();
      localProviderMethod.configure(paramBinder);
    }
  }

  public List getProviderMethods(Binder paramBinder)
  {
    ArrayList localArrayList = $Lists.newArrayList();
    for (Class localClass = this.delegate.getClass(); localClass != Object.class; localClass = localClass.getSuperclass())
      for (Method localMethod : localClass.getDeclaredMethods())
        if (localMethod.isAnnotationPresent(Provides.class))
          localArrayList.add(createProviderMethod(paramBinder, localMethod));
    return localArrayList;
  }

  ProviderMethod createProviderMethod(Binder paramBinder, Method paramMethod)
  {
    paramBinder = paramBinder.withSource(paramMethod);
    Errors localErrors = new Errors(paramMethod);
    ArrayList localArrayList1 = $Lists.newArrayList();
    ArrayList localArrayList2 = $Lists.newArrayList();
    List localList = this.typeLiteral.getParameterTypes(paramMethod);
    Annotation[][] arrayOfAnnotation = paramMethod.getParameterAnnotations();
    for (int i = 0; i < localList.size(); i++)
    {
      localObject1 = getKey(localErrors, (TypeLiteral)localList.get(i), paramMethod, arrayOfAnnotation[i]);
      if (((Key)localObject1).equals(Key.get(Logger.class)))
      {
        localObject2 = Key.get(Logger.class, UniqueAnnotations.create());
        paramBinder.bind((Key)localObject2).toProvider(new LogProvider(paramMethod));
        localObject1 = localObject2;
      }
      localArrayList1.add(Dependency.get((Key)localObject1));
      localArrayList2.add(paramBinder.getProvider((Key)localObject1));
    }
    TypeLiteral localTypeLiteral = this.typeLiteral.getReturnType(paramMethod);
    Object localObject1 = getKey(localErrors, localTypeLiteral, paramMethod, paramMethod.getAnnotations());
    Object localObject2 = Annotations.findScopeAnnotation(localErrors, paramMethod.getAnnotations());
    Iterator localIterator = localErrors.getMessages().iterator();
    while (localIterator.hasNext())
    {
      Message localMessage = (Message)localIterator.next();
      paramBinder.addError(localMessage);
    }
    return new ProviderMethod((Key)localObject1, paramMethod, this.delegate, .ImmutableSet.copyOf(localArrayList1), localArrayList2, (Class)localObject2);
  }

  Key getKey(Errors paramErrors, TypeLiteral paramTypeLiteral, Member paramMember, Annotation[] paramArrayOfAnnotation)
  {
    Annotation localAnnotation = Annotations.findBindingAnnotation(paramErrors, paramMember, paramArrayOfAnnotation);
    return localAnnotation == null ? Key.get(paramTypeLiteral) : Key.get(paramTypeLiteral, localAnnotation);
  }

  public boolean equals(Object paramObject)
  {
    return ((paramObject instanceof ProviderMethodsModule)) && (((ProviderMethodsModule)paramObject).delegate == this.delegate);
  }

  public int hashCode()
  {
    return this.delegate.hashCode();
  }

  private static final class LogProvider
    implements Provider
  {
    private final String name;

    public LogProvider(Method paramMethod)
    {
      this.name = (paramMethod.getDeclaringClass().getName() + "." + paramMethod.getName());
    }

    public Logger get()
    {
      return Logger.getLogger(this.name);
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.ProviderMethodsModule
 * JD-Core Version:    0.6.2
 */