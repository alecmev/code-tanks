package com.google.inject.internal;

import com.google.inject.Key;
import com.google.inject.MembersInjector;
import com.google.inject.Provider;
import com.google.inject.TypeLiteral;
import com.google.inject.internal.util..ImmutableList;
import com.google.inject.internal.util..Lists;
import com.google.inject.internal.util..Preconditions;
import com.google.inject.matcher.Matcher;
import com.google.inject.matcher.Matchers;
import com.google.inject.spi.InjectionListener;
import com.google.inject.spi.Message;
import com.google.inject.spi.TypeEncounter;
import java.util.List;
import org.aopalliance.intercept.MethodInterceptor;

final class EncounterImpl
  implements TypeEncounter
{
  private final Errors errors;
  private final Lookups lookups;
  private List membersInjectors;
  private List injectionListeners;
  private List aspects;
  private boolean valid = true;

  EncounterImpl(Errors paramErrors, Lookups paramLookups)
  {
    this.errors = paramErrors;
    this.lookups = paramLookups;
  }

  void invalidate()
  {
    this.valid = false;
  }

  $ImmutableList getAspects()
  {
    return this.aspects == null ? .ImmutableList.of() : .ImmutableList.copyOf(this.aspects);
  }

  public void bindInterceptor(Matcher paramMatcher, MethodInterceptor[] paramArrayOfMethodInterceptor)
  {
    $Preconditions.checkState(this.valid, "Encounters may not be used after hear() returns.");
    if (this.aspects == null)
      this.aspects = $Lists.newArrayList();
    this.aspects.add(new MethodAspect(Matchers.any(), paramMatcher, paramArrayOfMethodInterceptor));
  }

  $ImmutableList getMembersInjectors()
  {
    return this.membersInjectors == null ? .ImmutableList.of() : .ImmutableList.copyOf(this.membersInjectors);
  }

  $ImmutableList getInjectionListeners()
  {
    return this.injectionListeners == null ? .ImmutableList.of() : .ImmutableList.copyOf(this.injectionListeners);
  }

  public void register(MembersInjector paramMembersInjector)
  {
    $Preconditions.checkState(this.valid, "Encounters may not be used after hear() returns.");
    if (this.membersInjectors == null)
      this.membersInjectors = $Lists.newArrayList();
    this.membersInjectors.add(paramMembersInjector);
  }

  public void register(InjectionListener paramInjectionListener)
  {
    $Preconditions.checkState(this.valid, "Encounters may not be used after hear() returns.");
    if (this.injectionListeners == null)
      this.injectionListeners = $Lists.newArrayList();
    this.injectionListeners.add(paramInjectionListener);
  }

  public void addError(String paramString, Object[] paramArrayOfObject)
  {
    $Preconditions.checkState(this.valid, "Encounters may not be used after hear() returns.");
    this.errors.addMessage(paramString, paramArrayOfObject);
  }

  public void addError(Throwable paramThrowable)
  {
    $Preconditions.checkState(this.valid, "Encounters may not be used after hear() returns.");
    this.errors.errorInUserCode(paramThrowable, "An exception was caught and reported. Message: %s", new Object[] { paramThrowable.getMessage() });
  }

  public void addError(Message paramMessage)
  {
    $Preconditions.checkState(this.valid, "Encounters may not be used after hear() returns.");
    this.errors.addMessage(paramMessage);
  }

  public Provider getProvider(Key paramKey)
  {
    $Preconditions.checkState(this.valid, "Encounters may not be used after hear() returns.");
    return this.lookups.getProvider(paramKey);
  }

  public Provider getProvider(Class paramClass)
  {
    return getProvider(Key.get(paramClass));
  }

  public MembersInjector getMembersInjector(TypeLiteral paramTypeLiteral)
  {
    $Preconditions.checkState(this.valid, "Encounters may not be used after hear() returns.");
    return this.lookups.getMembersInjector(paramTypeLiteral);
  }

  public MembersInjector getMembersInjector(Class paramClass)
  {
    return getMembersInjector(TypeLiteral.get(paramClass));
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.EncounterImpl
 * JD-Core Version:    0.6.2
 */