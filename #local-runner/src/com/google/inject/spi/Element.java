package com.google.inject.spi;

import com.google.inject.Binder;

public abstract interface Element
{
  public abstract Object getSource();

  public abstract Object acceptVisitor(ElementVisitor paramElementVisitor);

  public abstract void applyTo(Binder paramBinder);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.spi.Element
 * JD-Core Version:    0.6.2
 */