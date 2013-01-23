package com.google.inject.internal;

import com.google.inject.spi.InterceptorBinding;

final class InterceptorBindingProcessor extends AbstractProcessor
{
  InterceptorBindingProcessor(Errors paramErrors)
  {
    super(paramErrors);
  }

  public Boolean visit(InterceptorBinding paramInterceptorBinding)
  {
    this.injector.state.addMethodAspect(new MethodAspect(paramInterceptorBinding.getClassMatcher(), paramInterceptorBinding.getMethodMatcher(), paramInterceptorBinding.getInterceptors()));
    return Boolean.valueOf(true);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.InterceptorBindingProcessor
 * JD-Core Version:    0.6.2
 */