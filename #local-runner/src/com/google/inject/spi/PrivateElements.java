package com.google.inject.spi;

import com.google.inject.Injector;
import com.google.inject.Key;
import java.util.List;
import java.util.Set;

public abstract interface PrivateElements extends Element
{
  public abstract List getElements();

  public abstract Injector getInjector();

  public abstract Set getExposedKeys();

  public abstract Object getExposedSource(Key paramKey);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.spi.PrivateElements
 * JD-Core Version:    0.6.2
 */