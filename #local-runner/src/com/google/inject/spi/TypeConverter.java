package com.google.inject.spi;

import com.google.inject.TypeLiteral;

public abstract interface TypeConverter
{
  public abstract Object convert(String paramString, TypeLiteral paramTypeLiteral);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.spi.TypeConverter
 * JD-Core Version:    0.6.2
 */