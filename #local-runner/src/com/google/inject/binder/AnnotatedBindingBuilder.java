package com.google.inject.binder;

import java.lang.annotation.Annotation;

public abstract interface AnnotatedBindingBuilder extends LinkedBindingBuilder
{
  public abstract LinkedBindingBuilder annotatedWith(Class paramClass);

  public abstract LinkedBindingBuilder annotatedWith(Annotation paramAnnotation);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.binder.AnnotatedBindingBuilder
 * JD-Core Version:    0.6.2
 */