package com.google.inject.binder;

import java.lang.annotation.Annotation;

public abstract interface AnnotatedConstantBindingBuilder
{
  public abstract ConstantBindingBuilder annotatedWith(Class paramClass);

  public abstract ConstantBindingBuilder annotatedWith(Annotation paramAnnotation);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.binder.AnnotatedConstantBindingBuilder
 * JD-Core Version:    0.6.2
 */