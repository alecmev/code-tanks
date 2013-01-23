package com.google.inject.internal;

import com.google.inject.ConfigurationException;
import com.google.inject.TypeLiteral;
import com.google.inject.internal.util..ImmutableList;
import com.google.inject.internal.util..Lists;
import com.google.inject.matcher.Matcher;
import com.google.inject.spi.InjectionPoint;
import com.google.inject.spi.TypeListener;
import com.google.inject.spi.TypeListenerBinding;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

final class MembersInjectorStore
{
  private final InjectorImpl injector;
  private final .ImmutableList typeListenerBindings;
  private final FailableCache cache = new FailableCache()
  {
    protected MembersInjectorImpl create(TypeLiteral paramAnonymousTypeLiteral, Errors paramAnonymousErrors)
      throws ErrorsException
    {
      return MembersInjectorStore.this.createWithListeners(paramAnonymousTypeLiteral, paramAnonymousErrors);
    }
  };

  MembersInjectorStore(InjectorImpl paramInjectorImpl, List paramList)
  {
    this.injector = paramInjectorImpl;
    this.typeListenerBindings = $ImmutableList.copyOf(paramList);
  }

  public boolean hasTypeListeners()
  {
    return !this.typeListenerBindings.isEmpty();
  }

  public MembersInjectorImpl get(TypeLiteral paramTypeLiteral, Errors paramErrors)
    throws ErrorsException
  {
    return (MembersInjectorImpl)this.cache.get(paramTypeLiteral, paramErrors);
  }

  boolean remove(TypeLiteral paramTypeLiteral)
  {
    return this.cache.remove(paramTypeLiteral);
  }

  private MembersInjectorImpl createWithListeners(TypeLiteral paramTypeLiteral, Errors paramErrors)
    throws ErrorsException
  {
    int i = paramErrors.size();
    Set localSet;
    try
    {
      localSet = InjectionPoint.forInstanceMethodsAndFields(paramTypeLiteral);
    }
    catch (ConfigurationException localConfigurationException)
    {
      paramErrors.merge(localConfigurationException.getErrorMessages());
      localSet = (Set)localConfigurationException.getPartialValue();
    }
    $ImmutableList localImmutableList = getInjectors(localSet, paramErrors);
    paramErrors.throwIfNewErrors(i);
    EncounterImpl localEncounterImpl = new EncounterImpl(paramErrors, this.injector.lookups);
    Iterator localIterator = this.typeListenerBindings.iterator();
    while (localIterator.hasNext())
    {
      TypeListenerBinding localTypeListenerBinding = (TypeListenerBinding)localIterator.next();
      if (localTypeListenerBinding.getTypeMatcher().matches(paramTypeLiteral))
        try
        {
          localTypeListenerBinding.getListener().hear(paramTypeLiteral, localEncounterImpl);
        }
        catch (RuntimeException localRuntimeException)
        {
          paramErrors.errorNotifyingTypeListener(localTypeListenerBinding, paramTypeLiteral, localRuntimeException);
        }
    }
    localEncounterImpl.invalidate();
    paramErrors.throwIfNewErrors(i);
    return new MembersInjectorImpl(this.injector, paramTypeLiteral, localEncounterImpl, localImmutableList);
  }

  $ImmutableList getInjectors(Set paramSet, Errors paramErrors)
  {
    ArrayList localArrayList = $Lists.newArrayList();
    Iterator localIterator = paramSet.iterator();
    while (localIterator.hasNext())
    {
      InjectionPoint localInjectionPoint = (InjectionPoint)localIterator.next();
      try
      {
        Errors localErrors = localInjectionPoint.isOptional() ? new Errors(localInjectionPoint) : paramErrors.withSource(localInjectionPoint);
        SingleMethodInjector localSingleMethodInjector = (localInjectionPoint.getMember() instanceof Field) ? new SingleFieldInjector(this.injector, localInjectionPoint, localErrors) : new SingleMethodInjector(this.injector, localInjectionPoint, localErrors);
        localArrayList.add(localSingleMethodInjector);
      }
      catch (ErrorsException localErrorsException)
      {
      }
    }
    return .ImmutableList.copyOf(localArrayList);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.MembersInjectorStore
 * JD-Core Version:    0.6.2
 */