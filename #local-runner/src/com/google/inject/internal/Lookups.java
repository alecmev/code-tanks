package com.google.inject.internal;

import com.google.inject.Key;
import com.google.inject.MembersInjector;
import com.google.inject.Provider;
import com.google.inject.TypeLiteral;

abstract interface Lookups
{
  public abstract Provider getProvider(Key paramKey);

  public abstract MembersInjector getMembersInjector(TypeLiteral paramTypeLiteral);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.Lookups
 * JD-Core Version:    0.6.2
 */