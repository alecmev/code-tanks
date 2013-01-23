package com.google.inject.internal;

import com.google.inject.BindingAnnotation;
import com.google.inject.Key;
import com.google.inject.ScopeAnnotation;
import com.google.inject.TypeLiteral;
import com.google.inject.internal.util..Classes;
import com.google.inject.internal.util..Function;
import com.google.inject.internal.util..MapMaker;
import com.google.inject.name.Names;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Member;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import javax.inject.Qualifier;
import javax.inject.Scope;

public class Annotations
{
  private static final AnnotationChecker scopeChecker = new AnnotationChecker(Arrays.asList(new Class[] { ScopeAnnotation.class, Scope.class }));
  private static final AnnotationChecker bindingAnnotationChecker = new AnnotationChecker(Arrays.asList(new Class[] { BindingAnnotation.class, Qualifier.class }));

  public static boolean isMarker(Class paramClass)
  {
    return paramClass.getDeclaredMethods().length == 0;
  }

  public static boolean isRetainedAtRuntime(Class paramClass)
  {
    Retention localRetention = (Retention)paramClass.getAnnotation(Retention.class);
    return (localRetention != null) && (localRetention.value() == RetentionPolicy.RUNTIME);
  }

  public static Class findScopeAnnotation(Errors paramErrors, Class paramClass)
  {
    return findScopeAnnotation(paramErrors, paramClass.getAnnotations());
  }

  public static Class findScopeAnnotation(Errors paramErrors, Annotation[] paramArrayOfAnnotation)
  {
    Object localObject = null;
    for (Annotation localAnnotation : paramArrayOfAnnotation)
    {
      Class localClass = localAnnotation.annotationType();
      if (isScopeAnnotation(localClass))
        if (localObject != null)
          paramErrors.duplicateScopeAnnotations((Class)localObject, localClass);
        else
          localObject = localClass;
    }
    return localObject;
  }

  public static boolean isScopeAnnotation(Class paramClass)
  {
    return scopeChecker.hasAnnotations(paramClass);
  }

  public static void checkForMisplacedScopeAnnotations(Class paramClass, Object paramObject, Errors paramErrors)
  {
    if ($Classes.isConcrete(paramClass))
      return;
    Class localClass = findScopeAnnotation(paramErrors, paramClass);
    if (localClass != null)
      paramErrors.withSource(paramClass).scopeAnnotationOnAbstractType(localClass, paramClass, paramObject);
  }

  public static Key getKey(TypeLiteral paramTypeLiteral, Member paramMember, Annotation[] paramArrayOfAnnotation, Errors paramErrors)
    throws ErrorsException
  {
    int i = paramErrors.size();
    Annotation localAnnotation = findBindingAnnotation(paramErrors, paramMember, paramArrayOfAnnotation);
    paramErrors.throwIfNewErrors(i);
    return localAnnotation == null ? Key.get(paramTypeLiteral) : Key.get(paramTypeLiteral, localAnnotation);
  }

  public static Annotation findBindingAnnotation(Errors paramErrors, Member paramMember, Annotation[] paramArrayOfAnnotation)
  {
    Object localObject = null;
    for (Annotation localAnnotation : paramArrayOfAnnotation)
    {
      Class localClass = localAnnotation.annotationType();
      if (isBindingAnnotation(localClass))
        if (localObject != null)
          paramErrors.duplicateBindingAnnotations(paramMember, localObject.annotationType(), localClass);
        else
          localObject = localAnnotation;
    }
    return localObject;
  }

  public static boolean isBindingAnnotation(Class paramClass)
  {
    return bindingAnnotationChecker.hasAnnotations(paramClass);
  }

  public static Annotation canonicalizeIfNamed(Annotation paramAnnotation)
  {
    if ((paramAnnotation instanceof javax.inject.Named))
      return Names.named(((javax.inject.Named)paramAnnotation).value());
    return paramAnnotation;
  }

  public static Class canonicalizeIfNamed(Class paramClass)
  {
    if (paramClass == javax.inject.Named.class)
      return com.google.inject.name.Named.class;
    return paramClass;
  }

  static class AnnotationChecker
  {
    private final Collection annotationTypes;
    private $Function hasAnnotations = new $Function()
    {
      public Boolean apply(Class paramAnonymousClass)
      {
        for (Annotation localAnnotation : paramAnonymousClass.getAnnotations())
          if (Annotations.AnnotationChecker.this.annotationTypes.contains(localAnnotation.annotationType()))
            return Boolean.valueOf(true);
        return Boolean.valueOf(false);
      }
    };
    final Map cache = new $MapMaker().weakKeys().makeComputingMap(this.hasAnnotations);

    AnnotationChecker(Collection paramCollection)
    {
      this.annotationTypes = paramCollection;
    }

    boolean hasAnnotations(Class paramClass)
    {
      return ((Boolean)this.cache.get(paramClass)).booleanValue();
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.Annotations
 * JD-Core Version:    0.6.2
 */