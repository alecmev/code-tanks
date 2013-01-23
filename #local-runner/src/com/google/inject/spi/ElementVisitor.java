package com.google.inject.spi;

import com.google.inject.Binding;

public abstract interface ElementVisitor
{
  public abstract Object visit(Binding paramBinding);

  public abstract Object visit(InterceptorBinding paramInterceptorBinding);

  public abstract Object visit(ScopeBinding paramScopeBinding);

  public abstract Object visit(TypeConverterBinding paramTypeConverterBinding);

  public abstract Object visit(InjectionRequest paramInjectionRequest);

  public abstract Object visit(StaticInjectionRequest paramStaticInjectionRequest);

  public abstract Object visit(ProviderLookup paramProviderLookup);

  public abstract Object visit(MembersInjectorLookup paramMembersInjectorLookup);

  public abstract Object visit(Message paramMessage);

  public abstract Object visit(PrivateElements paramPrivateElements);

  public abstract Object visit(TypeListenerBinding paramTypeListenerBinding);

  public abstract Object visit(RequireExplicitBindingsOption paramRequireExplicitBindingsOption);

  public abstract Object visit(DisableCircularProxiesOption paramDisableCircularProxiesOption);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.spi.ElementVisitor
 * JD-Core Version:    0.6.2
 */