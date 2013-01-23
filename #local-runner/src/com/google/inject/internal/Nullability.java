package com.google.inject.internal;

import com.google.inject.internal.util..Nullable;
import java.lang.annotation.Annotation;

public class Nullability
{
  public static boolean allowsNull(Annotation[] paramArrayOfAnnotation)
  {
    for (Annotation localAnnotation : paramArrayOfAnnotation)
    {
      Class localClass = localAnnotation.annotationType();
      if (("Nullable".equals(localClass.getSimpleName())) || (localClass == $Nullable.class))
        return true;
    }
    return false;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.Nullability
 * JD-Core Version:    0.6.2
 */