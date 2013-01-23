package com.google.inject.matcher;

import com.google.inject.internal.util..Preconditions;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;

public class Matchers
{
  private static final Matcher ANY = new Any(null);

  public static Matcher any()
  {
    return ANY;
  }

  public static Matcher not(Matcher paramMatcher)
  {
    return new Not(paramMatcher, null);
  }

  private static void checkForRuntimeRetention(Class paramClass)
  {
    Retention localRetention = (Retention)paramClass.getAnnotation(Retention.class);
    $Preconditions.checkArgument((localRetention != null) && (localRetention.value() == RetentionPolicy.RUNTIME), "Annotation " + paramClass.getSimpleName() + " is missing RUNTIME retention");
  }

  public static Matcher annotatedWith(Class paramClass)
  {
    return new AnnotatedWithType(paramClass);
  }

  public static Matcher annotatedWith(Annotation paramAnnotation)
  {
    return new AnnotatedWith(paramAnnotation);
  }

  public static Matcher subclassesOf(Class paramClass)
  {
    return new SubclassesOf(paramClass);
  }

  public static Matcher only(Object paramObject)
  {
    return new Only(paramObject);
  }

  public static Matcher identicalTo(Object paramObject)
  {
    return new IdenticalTo(paramObject);
  }

  public static Matcher inPackage(Package paramPackage)
  {
    return new InPackage(paramPackage);
  }

  public static Matcher inSubpackage(String paramString)
  {
    return new InSubpackage(paramString);
  }

  public static Matcher returns(Matcher paramMatcher)
  {
    return new Returns(paramMatcher);
  }

  private static class Returns extends AbstractMatcher
    implements Serializable
  {
    private final Matcher returnType;
    private static final long serialVersionUID = 0L;

    public Returns(Matcher paramMatcher)
    {
      this.returnType = ((Matcher).Preconditions.checkNotNull(paramMatcher, "return type matcher"));
    }

    public boolean matches(Method paramMethod)
    {
      return this.returnType.matches(paramMethod.getReturnType());
    }

    public boolean equals(Object paramObject)
    {
      return ((paramObject instanceof Returns)) && (((Returns)paramObject).returnType.equals(this.returnType));
    }

    public int hashCode()
    {
      return 37 * this.returnType.hashCode();
    }

    public String toString()
    {
      return "returns(" + this.returnType + ")";
    }
  }

  private static class InSubpackage extends AbstractMatcher
    implements Serializable
  {
    private final String targetPackageName;
    private static final long serialVersionUID = 0L;

    public InSubpackage(String paramString)
    {
      this.targetPackageName = paramString;
    }

    public boolean matches(Class paramClass)
    {
      String str = paramClass.getPackage().getName();
      return (str.equals(this.targetPackageName)) || (str.startsWith(this.targetPackageName + "."));
    }

    public boolean equals(Object paramObject)
    {
      return ((paramObject instanceof InSubpackage)) && (((InSubpackage)paramObject).targetPackageName.equals(this.targetPackageName));
    }

    public int hashCode()
    {
      return 37 * this.targetPackageName.hashCode();
    }

    public String toString()
    {
      return "inSubpackage(" + this.targetPackageName + ")";
    }
  }

  private static class InPackage extends AbstractMatcher
    implements Serializable
  {
    private final transient Package targetPackage;
    private final String packageName;
    private static final long serialVersionUID = 0L;

    public InPackage(Package paramPackage)
    {
      this.targetPackage = ((Package).Preconditions.checkNotNull(paramPackage, "package"));
      this.packageName = paramPackage.getName();
    }

    public boolean matches(Class paramClass)
    {
      return paramClass.getPackage().equals(this.targetPackage);
    }

    public boolean equals(Object paramObject)
    {
      return ((paramObject instanceof InPackage)) && (((InPackage)paramObject).targetPackage.equals(this.targetPackage));
    }

    public int hashCode()
    {
      return 37 * this.targetPackage.hashCode();
    }

    public String toString()
    {
      return "inPackage(" + this.targetPackage.getName() + ")";
    }

    public Object readResolve()
    {
      return Matchers.inPackage(Package.getPackage(this.packageName));
    }
  }

  private static class IdenticalTo extends AbstractMatcher
    implements Serializable
  {
    private final Object value;
    private static final long serialVersionUID = 0L;

    public IdenticalTo(Object paramObject)
    {
      this.value = $Preconditions.checkNotNull(paramObject, "value");
    }

    public boolean matches(Object paramObject)
    {
      return this.value == paramObject;
    }

    public boolean equals(Object paramObject)
    {
      return ((paramObject instanceof IdenticalTo)) && (((IdenticalTo)paramObject).value == this.value);
    }

    public int hashCode()
    {
      return 37 * System.identityHashCode(this.value);
    }

    public String toString()
    {
      return "identicalTo(" + this.value + ")";
    }
  }

  private static class Only extends AbstractMatcher
    implements Serializable
  {
    private final Object value;
    private static final long serialVersionUID = 0L;

    public Only(Object paramObject)
    {
      this.value = $Preconditions.checkNotNull(paramObject, "value");
    }

    public boolean matches(Object paramObject)
    {
      return this.value.equals(paramObject);
    }

    public boolean equals(Object paramObject)
    {
      return ((paramObject instanceof Only)) && (((Only)paramObject).value.equals(this.value));
    }

    public int hashCode()
    {
      return 37 * this.value.hashCode();
    }

    public String toString()
    {
      return "only(" + this.value + ")";
    }
  }

  private static class SubclassesOf extends AbstractMatcher
    implements Serializable
  {
    private final Class superclass;
    private static final long serialVersionUID = 0L;

    public SubclassesOf(Class paramClass)
    {
      this.superclass = ((Class).Preconditions.checkNotNull(paramClass, "superclass"));
    }

    public boolean matches(Class paramClass)
    {
      return this.superclass.isAssignableFrom(paramClass);
    }

    public boolean equals(Object paramObject)
    {
      return ((paramObject instanceof SubclassesOf)) && (((SubclassesOf)paramObject).superclass.equals(this.superclass));
    }

    public int hashCode()
    {
      return 37 * this.superclass.hashCode();
    }

    public String toString()
    {
      return "subclassesOf(" + this.superclass.getSimpleName() + ".class)";
    }
  }

  private static class AnnotatedWith extends AbstractMatcher
    implements Serializable
  {
    private final Annotation annotation;
    private static final long serialVersionUID = 0L;

    public AnnotatedWith(Annotation paramAnnotation)
    {
      this.annotation = ((Annotation).Preconditions.checkNotNull(paramAnnotation, "annotation"));
      Matchers.checkForRuntimeRetention(paramAnnotation.annotationType());
    }

    public boolean matches(AnnotatedElement paramAnnotatedElement)
    {
      Annotation localAnnotation = paramAnnotatedElement.getAnnotation(this.annotation.annotationType());
      return (localAnnotation != null) && (this.annotation.equals(localAnnotation));
    }

    public boolean equals(Object paramObject)
    {
      return ((paramObject instanceof AnnotatedWith)) && (((AnnotatedWith)paramObject).annotation.equals(this.annotation));
    }

    public int hashCode()
    {
      return 37 * this.annotation.hashCode();
    }

    public String toString()
    {
      return "annotatedWith(" + this.annotation + ")";
    }
  }

  private static class AnnotatedWithType extends AbstractMatcher
    implements Serializable
  {
    private final Class annotationType;
    private static final long serialVersionUID = 0L;

    public AnnotatedWithType(Class paramClass)
    {
      this.annotationType = ((Class).Preconditions.checkNotNull(paramClass, "annotation type"));
      Matchers.checkForRuntimeRetention(paramClass);
    }

    public boolean matches(AnnotatedElement paramAnnotatedElement)
    {
      return paramAnnotatedElement.getAnnotation(this.annotationType) != null;
    }

    public boolean equals(Object paramObject)
    {
      return ((paramObject instanceof AnnotatedWithType)) && (((AnnotatedWithType)paramObject).annotationType.equals(this.annotationType));
    }

    public int hashCode()
    {
      return 37 * this.annotationType.hashCode();
    }

    public String toString()
    {
      return "annotatedWith(" + this.annotationType.getSimpleName() + ".class)";
    }
  }

  private static class Not extends AbstractMatcher
    implements Serializable
  {
    final Matcher delegate;
    private static final long serialVersionUID = 0L;

    private Not(Matcher paramMatcher)
    {
      this.delegate = ((Matcher).Preconditions.checkNotNull(paramMatcher, "delegate"));
    }

    public boolean matches(Object paramObject)
    {
      return !this.delegate.matches(paramObject);
    }

    public boolean equals(Object paramObject)
    {
      return ((paramObject instanceof Not)) && (((Not)paramObject).delegate.equals(this.delegate));
    }

    public int hashCode()
    {
      return -this.delegate.hashCode();
    }

    public String toString()
    {
      return "not(" + this.delegate + ")";
    }
  }

  private static class Any extends AbstractMatcher
    implements Serializable
  {
    private static final long serialVersionUID = 0L;

    public boolean matches(Object paramObject)
    {
      return true;
    }

    public String toString()
    {
      return "any()";
    }

    public Object readResolve()
    {
      return Matchers.any();
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.matcher.Matchers
 * JD-Core Version:    0.6.2
 */