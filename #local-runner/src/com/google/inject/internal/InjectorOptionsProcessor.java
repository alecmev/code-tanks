package com.google.inject.internal;

import com.google.inject.Stage;
import com.google.inject.internal.util..Preconditions;
import com.google.inject.spi.DisableCircularProxiesOption;
import com.google.inject.spi.RequireExplicitBindingsOption;

class InjectorOptionsProcessor extends AbstractProcessor
{
  private boolean disableCircularProxies = false;
  private boolean jitDisabled = false;

  InjectorOptionsProcessor(Errors paramErrors)
  {
    super(paramErrors);
  }

  public Boolean visit(DisableCircularProxiesOption paramDisableCircularProxiesOption)
  {
    this.disableCircularProxies = true;
    return Boolean.valueOf(true);
  }

  public Boolean visit(RequireExplicitBindingsOption paramRequireExplicitBindingsOption)
  {
    this.jitDisabled = true;
    return Boolean.valueOf(true);
  }

  InjectorImpl.InjectorOptions getOptions(Stage paramStage, InjectorImpl.InjectorOptions paramInjectorOptions)
  {
    $Preconditions.checkNotNull(paramStage, "stage must be set");
    if (paramInjectorOptions == null)
      return new InjectorImpl.InjectorOptions(paramStage, this.jitDisabled, this.disableCircularProxies);
    $Preconditions.checkState(paramStage == paramInjectorOptions.stage, "child & parent stage don't match");
    return new InjectorImpl.InjectorOptions(paramStage, (this.jitDisabled) || (paramInjectorOptions.jitDisabled), (this.disableCircularProxies) || (paramInjectorOptions.disableCircularProxies));
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.InjectorOptionsProcessor
 * JD-Core Version:    0.6.2
 */