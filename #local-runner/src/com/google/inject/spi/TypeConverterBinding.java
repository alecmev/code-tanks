package com.google.inject.spi;

import com.google.inject.Binder;
import com.google.inject.internal.util..Preconditions;
import com.google.inject.matcher.Matcher;

public final class TypeConverterBinding
  implements Element
{
  private final Object source;
  private final Matcher typeMatcher;
  private final TypeConverter typeConverter;

  public TypeConverterBinding(Object paramObject, Matcher paramMatcher, TypeConverter paramTypeConverter)
  {
    this.source = $Preconditions.checkNotNull(paramObject, "source");
    this.typeMatcher = ((Matcher).Preconditions.checkNotNull(paramMatcher, "typeMatcher"));
    this.typeConverter = ((TypeConverter).Preconditions.checkNotNull(paramTypeConverter, "typeConverter"));
  }

  public Object getSource()
  {
    return this.source;
  }

  public Matcher getTypeMatcher()
  {
    return this.typeMatcher;
  }

  public TypeConverter getTypeConverter()
  {
    return this.typeConverter;
  }

  public Object acceptVisitor(ElementVisitor paramElementVisitor)
  {
    return paramElementVisitor.visit(this);
  }

  public void applyTo(Binder paramBinder)
  {
    paramBinder.withSource(getSource()).convertToTypes(this.typeMatcher, this.typeConverter);
  }

  public String toString()
  {
    return this.typeConverter + " which matches " + this.typeMatcher + " (bound at " + this.source + ")";
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.spi.TypeConverterBinding
 * JD-Core Version:    0.6.2
 */