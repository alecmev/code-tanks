package com.google.inject.internal;

import com.google.inject.Binder;
import com.google.inject.Key;
import com.google.inject.binder.AnnotatedElementBuilder;
import com.google.inject.internal.util..Preconditions;
import java.lang.annotation.Annotation;

public class ExposureBuilder
  implements AnnotatedElementBuilder
{
  private final Binder binder;
  private final Object source;
  private Key key;

  public ExposureBuilder(Binder paramBinder, Object paramObject, Key paramKey)
  {
    this.binder = paramBinder;
    this.source = paramObject;
    this.key = paramKey;
  }

  protected void checkNotAnnotated()
  {
    if (this.key.getAnnotationType() != null)
      this.binder.addError("More than one annotation is specified for this binding.", new Object[0]);
  }

  public void annotatedWith(Class paramClass)
  {
    $Preconditions.checkNotNull(paramClass, "annotationType");
    checkNotAnnotated();
    this.key = Key.get(this.key.getTypeLiteral(), paramClass);
  }

  public void annotatedWith(Annotation paramAnnotation)
  {
    $Preconditions.checkNotNull(paramAnnotation, "annotation");
    checkNotAnnotated();
    this.key = Key.get(this.key.getTypeLiteral(), paramAnnotation);
  }

  public Key getKey()
  {
    return this.key;
  }

  public Object getSource()
  {
    return this.source;
  }

  public String toString()
  {
    return "AnnotatedElementBuilder";
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.ExposureBuilder
 * JD-Core Version:    0.6.2
 */