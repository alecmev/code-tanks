package com.google.inject.internal;

import com.google.inject.Scope;
import com.google.inject.internal.util..Preconditions;
import com.google.inject.spi.ScopeBinding;

final class ScopeBindingProcessor extends AbstractProcessor
{
  ScopeBindingProcessor(Errors paramErrors)
  {
    super(paramErrors);
  }

  public Boolean visit(ScopeBinding paramScopeBinding)
  {
    Scope localScope1 = paramScopeBinding.getScope();
    Class localClass = paramScopeBinding.getAnnotationType();
    if (!Annotations.isScopeAnnotation(localClass))
      this.errors.withSource(localClass).missingScopeAnnotation();
    if (!Annotations.isRetainedAtRuntime(localClass))
      this.errors.withSource(localClass).missingRuntimeRetention(paramScopeBinding.getSource());
    Scope localScope2 = this.injector.state.getScope((Class).Preconditions.checkNotNull(localClass, "annotation type"));
    if (localScope2 != null)
      this.errors.duplicateScopes(localScope2, localClass, localScope1);
    else
      this.injector.state.putAnnotation(localClass, (Scope).Preconditions.checkNotNull(localScope1, "scope"));
    return Boolean.valueOf(true);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.ScopeBindingProcessor
 * JD-Core Version:    0.6.2
 */