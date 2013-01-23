package com.google.inject.spi;

import com.google.inject.Binding;
import com.google.inject.Key;
import java.util.Set;

public abstract interface ConvertedConstantBinding extends Binding, HasDependencies
{
  public abstract Object getValue();

  public abstract TypeConverterBinding getTypeConverterBinding();

  public abstract Key getSourceKey();

  public abstract Set getDependencies();
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.spi.ConvertedConstantBinding
 * JD-Core Version:    0.6.2
 */