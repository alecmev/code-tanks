package com.google.inject;

public abstract interface Scope
{
  public abstract Provider scope(Key paramKey, Provider paramProvider);

  public abstract String toString();
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.Scope
 * JD-Core Version:    0.6.2
 */