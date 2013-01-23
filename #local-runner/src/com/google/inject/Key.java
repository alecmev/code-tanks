package com.google.inject;

import com.google.inject.internal.Annotations;
import com.google.inject.internal.MoreTypes;
import com.google.inject.internal.util..Preconditions;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

public class Key
{
  private final AnnotationStrategy annotationStrategy;
  private final TypeLiteral typeLiteral;
  private final int hashCode;

  protected Key(Class paramClass)
  {
    this.annotationStrategy = strategyFor(paramClass);
    this.typeLiteral = TypeLiteral.fromSuperclassTypeParameter(getClass());
    this.hashCode = computeHashCode();
  }

  protected Key(Annotation paramAnnotation)
  {
    this.annotationStrategy = strategyFor(paramAnnotation);
    this.typeLiteral = TypeLiteral.fromSuperclassTypeParameter(getClass());
    this.hashCode = computeHashCode();
  }

  protected Key()
  {
    this.annotationStrategy = NullAnnotationStrategy.INSTANCE;
    this.typeLiteral = TypeLiteral.fromSuperclassTypeParameter(getClass());
    this.hashCode = computeHashCode();
  }

  private Key(Type paramType, AnnotationStrategy paramAnnotationStrategy)
  {
    this.annotationStrategy = paramAnnotationStrategy;
    this.typeLiteral = MoreTypes.canonicalizeForKey(TypeLiteral.get(paramType));
    this.hashCode = computeHashCode();
  }

  private Key(TypeLiteral paramTypeLiteral, AnnotationStrategy paramAnnotationStrategy)
  {
    this.annotationStrategy = paramAnnotationStrategy;
    this.typeLiteral = MoreTypes.canonicalizeForKey(paramTypeLiteral);
    this.hashCode = computeHashCode();
  }

  private int computeHashCode()
  {
    return this.typeLiteral.hashCode() * 31 + this.annotationStrategy.hashCode();
  }

  public final TypeLiteral getTypeLiteral()
  {
    return this.typeLiteral;
  }

  public final Class getAnnotationType()
  {
    return this.annotationStrategy.getAnnotationType();
  }

  public final Annotation getAnnotation()
  {
    return this.annotationStrategy.getAnnotation();
  }

  boolean hasAnnotationType()
  {
    return this.annotationStrategy.getAnnotationType() != null;
  }

  String getAnnotationName()
  {
    Annotation localAnnotation = this.annotationStrategy.getAnnotation();
    if (localAnnotation != null)
      return localAnnotation.toString();
    return this.annotationStrategy.getAnnotationType().toString();
  }

  Class getRawType()
  {
    return this.typeLiteral.getRawType();
  }

  Key providerKey()
  {
    return ofType(this.typeLiteral.providerType());
  }

  public final boolean equals(Object paramObject)
  {
    if (paramObject == this)
      return true;
    if (!(paramObject instanceof Key))
      return false;
    Key localKey = (Key)paramObject;
    return (this.annotationStrategy.equals(localKey.annotationStrategy)) && (this.typeLiteral.equals(localKey.typeLiteral));
  }

  public final int hashCode()
  {
    return this.hashCode;
  }

  public final String toString()
  {
    return "Key[type=" + this.typeLiteral + ", annotation=" + this.annotationStrategy + "]";
  }

  static Key get(Class paramClass, AnnotationStrategy paramAnnotationStrategy)
  {
    return new Key(paramClass, paramAnnotationStrategy);
  }

  public static Key get(Class paramClass)
  {
    return new Key(paramClass, NullAnnotationStrategy.INSTANCE);
  }

  public static Key get(Class paramClass1, Class paramClass2)
  {
    return new Key(paramClass1, strategyFor(paramClass2));
  }

  public static Key get(Class paramClass, Annotation paramAnnotation)
  {
    return new Key(paramClass, strategyFor(paramAnnotation));
  }

  public static Key get(Type paramType)
  {
    return new Key(paramType, NullAnnotationStrategy.INSTANCE);
  }

  public static Key get(Type paramType, Class paramClass)
  {
    return new Key(paramType, strategyFor(paramClass));
  }

  public static Key get(Type paramType, Annotation paramAnnotation)
  {
    return new Key(paramType, strategyFor(paramAnnotation));
  }

  public static Key get(TypeLiteral paramTypeLiteral)
  {
    return new Key(paramTypeLiteral, NullAnnotationStrategy.INSTANCE);
  }

  public static Key get(TypeLiteral paramTypeLiteral, Class paramClass)
  {
    return new Key(paramTypeLiteral, strategyFor(paramClass));
  }

  public static Key get(TypeLiteral paramTypeLiteral, Annotation paramAnnotation)
  {
    return new Key(paramTypeLiteral, strategyFor(paramAnnotation));
  }

  public Key ofType(Class paramClass)
  {
    return new Key(paramClass, this.annotationStrategy);
  }

  public Key ofType(Type paramType)
  {
    return new Key(paramType, this.annotationStrategy);
  }

  public Key ofType(TypeLiteral paramTypeLiteral)
  {
    return new Key(paramTypeLiteral, this.annotationStrategy);
  }

  public boolean hasAttributes()
  {
    return this.annotationStrategy.hasAttributes();
  }

  public Key withoutAttributes()
  {
    return new Key(this.typeLiteral, this.annotationStrategy.withoutAttributes());
  }

  static AnnotationStrategy strategyFor(Annotation paramAnnotation)
  {
    $Preconditions.checkNotNull(paramAnnotation, "annotation");
    Class localClass = paramAnnotation.annotationType();
    ensureRetainedAtRuntime(localClass);
    ensureIsBindingAnnotation(localClass);
    if (Annotations.isMarker(localClass))
      return new AnnotationTypeStrategy(localClass, paramAnnotation);
    return new AnnotationInstanceStrategy(Annotations.canonicalizeIfNamed(paramAnnotation));
  }

  static AnnotationStrategy strategyFor(Class paramClass)
  {
    $Preconditions.checkNotNull(paramClass, "annotation type");
    ensureRetainedAtRuntime(paramClass);
    ensureIsBindingAnnotation(paramClass);
    return new AnnotationTypeStrategy(Annotations.canonicalizeIfNamed(paramClass), null);
  }

  private static void ensureRetainedAtRuntime(Class paramClass)
  {
    $Preconditions.checkArgument(Annotations.isRetainedAtRuntime(paramClass), "%s is not retained at runtime. Please annotate it with @Retention(RUNTIME).", new Object[] { paramClass.getName() });
  }

  private static void ensureIsBindingAnnotation(Class paramClass)
  {
    $Preconditions.checkArgument(Annotations.isBindingAnnotation(paramClass), "%s is not a binding annotation. Please annotate it with @BindingAnnotation.", new Object[] { paramClass.getName() });
  }

  static class AnnotationTypeStrategy
    implements Key.AnnotationStrategy
  {
    final Class annotationType;
    final Annotation annotation;

    AnnotationTypeStrategy(Class paramClass, Annotation paramAnnotation)
    {
      this.annotationType = ((Class).Preconditions.checkNotNull(paramClass, "annotation type"));
      this.annotation = paramAnnotation;
    }

    public boolean hasAttributes()
    {
      return false;
    }

    public Key.AnnotationStrategy withoutAttributes()
    {
      throw new UnsupportedOperationException("Key already has no attributes.");
    }

    public Annotation getAnnotation()
    {
      return this.annotation;
    }

    public Class getAnnotationType()
    {
      return this.annotationType;
    }

    public boolean equals(Object paramObject)
    {
      if (!(paramObject instanceof AnnotationTypeStrategy))
        return false;
      AnnotationTypeStrategy localAnnotationTypeStrategy = (AnnotationTypeStrategy)paramObject;
      return this.annotationType.equals(localAnnotationTypeStrategy.annotationType);
    }

    public int hashCode()
    {
      return this.annotationType.hashCode();
    }

    public String toString()
    {
      return "@" + this.annotationType.getName();
    }
  }

  static class AnnotationInstanceStrategy
    implements Key.AnnotationStrategy
  {
    final Annotation annotation;

    AnnotationInstanceStrategy(Annotation paramAnnotation)
    {
      this.annotation = ((Annotation).Preconditions.checkNotNull(paramAnnotation, "annotation"));
    }

    public boolean hasAttributes()
    {
      return true;
    }

    public Key.AnnotationStrategy withoutAttributes()
    {
      return new Key.AnnotationTypeStrategy(getAnnotationType(), this.annotation);
    }

    public Annotation getAnnotation()
    {
      return this.annotation;
    }

    public Class getAnnotationType()
    {
      return this.annotation.annotationType();
    }

    public boolean equals(Object paramObject)
    {
      if (!(paramObject instanceof AnnotationInstanceStrategy))
        return false;
      AnnotationInstanceStrategy localAnnotationInstanceStrategy = (AnnotationInstanceStrategy)paramObject;
      return this.annotation.equals(localAnnotationInstanceStrategy.annotation);
    }

    public int hashCode()
    {
      return this.annotation.hashCode();
    }

    public String toString()
    {
      return this.annotation.toString();
    }
  }

  static enum NullAnnotationStrategy
    implements Key.AnnotationStrategy
  {
    INSTANCE;

    public boolean hasAttributes()
    {
      return false;
    }

    public Key.AnnotationStrategy withoutAttributes()
    {
      throw new UnsupportedOperationException("Key already has no attributes.");
    }

    public Annotation getAnnotation()
    {
      return null;
    }

    public Class getAnnotationType()
    {
      return null;
    }

    public String toString()
    {
      return "[none]";
    }
  }

  static abstract interface AnnotationStrategy
  {
    public abstract Annotation getAnnotation();

    public abstract Class getAnnotationType();

    public abstract boolean hasAttributes();

    public abstract AnnotationStrategy withoutAttributes();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.Key
 * JD-Core Version:    0.6.2
 */