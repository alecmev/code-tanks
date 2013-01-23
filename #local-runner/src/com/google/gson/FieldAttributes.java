package com.google.gson;

import com.google.gson.internal..Gson.Preconditions;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;

public final class FieldAttributes
{
  private final Field field;

  public FieldAttributes(Field paramField)
  {
    $Gson.Preconditions.checkNotNull(paramField);
    this.field = paramField;
  }

  public Class getDeclaringClass()
  {
    return this.field.getDeclaringClass();
  }

  public String getName()
  {
    return this.field.getName();
  }

  public Type getDeclaredType()
  {
    return this.field.getGenericType();
  }

  public Class getDeclaredClass()
  {
    return this.field.getType();
  }

  public Annotation getAnnotation(Class paramClass)
  {
    return this.field.getAnnotation(paramClass);
  }

  public Collection getAnnotations()
  {
    return Arrays.asList(this.field.getAnnotations());
  }

  public boolean hasModifier(int paramInt)
  {
    return (this.field.getModifiers() & paramInt) != 0;
  }

  Object get(Object paramObject)
    throws IllegalAccessException
  {
    return this.field.get(paramObject);
  }

  boolean isSynthetic()
  {
    return this.field.isSynthetic();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.gson.FieldAttributes
 * JD-Core Version:    0.6.2
 */