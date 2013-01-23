package com.google.inject.spi;

import com.google.inject.TypeLiteral;

public abstract interface TypeListener
{
  public abstract void hear(TypeLiteral paramTypeLiteral, TypeEncounter paramTypeEncounter);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.spi.TypeListener
 * JD-Core Version:    0.6.2
 */