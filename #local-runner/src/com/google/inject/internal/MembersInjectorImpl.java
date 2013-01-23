package com.google.inject.internal;

import com.google.inject.MembersInjector;
import com.google.inject.TypeLiteral;
import com.google.inject.internal.util..ImmutableList;
import com.google.inject.internal.util..ImmutableSet;
import com.google.inject.internal.util..ImmutableSet.Builder;
import com.google.inject.spi.InjectionListener;
import com.google.inject.spi.InjectionPoint;
import java.util.Iterator;

final class MembersInjectorImpl
  implements MembersInjector
{
  private final TypeLiteral typeLiteral;
  private final InjectorImpl injector;
  private final .ImmutableList memberInjectors;
  private final .ImmutableList userMembersInjectors;
  private final .ImmutableList injectionListeners;
  private final .ImmutableList addedAspects;

  MembersInjectorImpl(InjectorImpl paramInjectorImpl, TypeLiteral paramTypeLiteral, EncounterImpl paramEncounterImpl, .ImmutableList paramImmutableList)
  {
    this.injector = paramInjectorImpl;
    this.typeLiteral = paramTypeLiteral;
    this.memberInjectors = paramImmutableList;
    this.userMembersInjectors = paramEncounterImpl.getMembersInjectors();
    this.injectionListeners = paramEncounterImpl.getInjectionListeners();
    this.addedAspects = paramEncounterImpl.getAspects();
  }

  public .ImmutableList getMemberInjectors()
  {
    return this.memberInjectors;
  }

  public void injectMembers(Object paramObject)
  {
    Errors localErrors = new Errors(this.typeLiteral);
    try
    {
      injectAndNotify(paramObject, localErrors, false);
    }
    catch (ErrorsException localErrorsException)
    {
      localErrors.merge(localErrorsException.getErrors());
    }
    localErrors.throwProvisionExceptionIfErrorsExist();
  }

  void injectAndNotify(final Object paramObject, final Errors paramErrors, final boolean paramBoolean)
    throws ErrorsException
  {
    if (paramObject == null)
      return;
    this.injector.callInContext(new ContextualCallable()
    {
      public Void call(InternalContext paramAnonymousInternalContext)
        throws ErrorsException
      {
        MembersInjectorImpl.this.injectMembers(paramObject, paramErrors, paramAnonymousInternalContext, paramBoolean);
        return null;
      }
    });
    if (!paramBoolean)
      notifyListeners(paramObject, paramErrors);
  }

  void notifyListeners(Object paramObject, Errors paramErrors)
    throws ErrorsException
  {
    int i = paramErrors.size();
    Iterator localIterator = this.injectionListeners.iterator();
    while (localIterator.hasNext())
    {
      InjectionListener localInjectionListener = (InjectionListener)localIterator.next();
      try
      {
        localInjectionListener.afterInjection(paramObject);
      }
      catch (RuntimeException localRuntimeException)
      {
        paramErrors.errorNotifyingInjectionListener(localInjectionListener, this.typeLiteral, localRuntimeException);
      }
    }
    paramErrors.throwIfNewErrors(i);
  }

  void injectMembers(Object paramObject, Errors paramErrors, InternalContext paramInternalContext, boolean paramBoolean)
  {
    int i = 0;
    int j = this.memberInjectors.size();
    Object localObject;
    while (i < j)
    {
      localObject = (SingleMemberInjector)this.memberInjectors.get(i);
      if ((!paramBoolean) || (((SingleMemberInjector)localObject).getInjectionPoint().isToolable()))
        ((SingleMemberInjector)localObject).inject(paramErrors, paramInternalContext, paramObject);
      i++;
    }
    if (!paramBoolean)
    {
      i = 0;
      j = this.userMembersInjectors.size();
      while (i < j)
      {
        localObject = (MembersInjector)this.userMembersInjectors.get(i);
        try
        {
          ((MembersInjector)localObject).injectMembers(paramObject);
        }
        catch (RuntimeException localRuntimeException)
        {
          paramErrors.errorInUserInjector((MembersInjector)localObject, this.typeLiteral, localRuntimeException);
        }
        i++;
      }
    }
  }

  public String toString()
  {
    return "MembersInjector<" + this.typeLiteral + ">";
  }

  public .ImmutableSet getInjectionPoints()
  {
    $ImmutableSet.Builder localBuilder = $ImmutableSet.builder();
    Iterator localIterator = this.memberInjectors.iterator();
    while (localIterator.hasNext())
    {
      SingleMemberInjector localSingleMemberInjector = (SingleMemberInjector)localIterator.next();
      localBuilder.add(localSingleMemberInjector.getInjectionPoint());
    }
    return localBuilder.build();
  }

  public .ImmutableList getAddedAspects()
  {
    return this.addedAspects;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.MembersInjectorImpl
 * JD-Core Version:    0.6.2
 */