package com.google.inject.internal;

import com.google.inject.spi.TypeListenerBinding;

final class TypeListenerBindingProcessor extends AbstractProcessor
{
  TypeListenerBindingProcessor(Errors paramErrors)
  {
    super(paramErrors);
  }

  public Boolean visit(TypeListenerBinding paramTypeListenerBinding)
  {
    this.injector.state.addTypeListener(paramTypeListenerBinding);
    return Boolean.valueOf(true);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.TypeListenerBindingProcessor
 * JD-Core Version:    0.6.2
 */