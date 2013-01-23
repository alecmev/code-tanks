package com.google.inject.binder;

import java.lang.annotation.Annotation;

public abstract interface AnnotatedElementBuilder
{
  public abstract void annotatedWith(Class paramClass);

  public abstract void annotatedWith(Annotation paramAnnotation);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.binder.AnnotatedElementBuilder
 * JD-Core Version:    0.6.2
 */