package com.google.inject.internal;

import com.google.inject.internal.util..Lists;
import com.google.inject.spi.PrivateElements;
import java.util.List;

final class PrivateElementProcessor extends AbstractProcessor
{
  private final List injectorShellBuilders = $Lists.newArrayList();

  PrivateElementProcessor(Errors paramErrors)
  {
    super(paramErrors);
  }

  public Boolean visit(PrivateElements paramPrivateElements)
  {
    InjectorShell.Builder localBuilder = new InjectorShell.Builder().parent(this.injector).privateElements(paramPrivateElements);
    this.injectorShellBuilders.add(localBuilder);
    return Boolean.valueOf(true);
  }

  public List getInjectorShellBuilders()
  {
    return this.injectorShellBuilders;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.PrivateElementProcessor
 * JD-Core Version:    0.6.2
 */