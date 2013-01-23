package com.google.inject.internal;

import com.google.inject.BindingAnnotation;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.atomic.AtomicInteger;

public class UniqueAnnotations
{
  private static final AtomicInteger nextUniqueValue = new AtomicInteger(1);

  public static Annotation create()
  {
    return create(nextUniqueValue.getAndIncrement());
  }

  static Annotation create(int paramInt)
  {
    return new Internal()
    {
      public int value()
      {
        return this.val$value;
      }

      public Class annotationType()
      {
        return UniqueAnnotations.Internal.class;
      }

      public String toString()
      {
        return "@" + UniqueAnnotations.Internal.class.getName() + "(value=" + this.val$value + ")";
      }

      public boolean equals(Object paramAnonymousObject)
      {
        return ((paramAnonymousObject instanceof UniqueAnnotations.Internal)) && (((UniqueAnnotations.Internal)paramAnonymousObject).value() == value());
      }

      public int hashCode()
      {
        return 127 * "value".hashCode() ^ this.val$value;
      }
    };
  }

  @Retention(RetentionPolicy.RUNTIME)
  @BindingAnnotation
  static @interface Internal
  {
    public abstract int value();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.UniqueAnnotations
 * JD-Core Version:    0.6.2
 */