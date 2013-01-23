package com.google.gson;

public abstract interface ExclusionStrategy
{
  public abstract boolean shouldSkipField(FieldAttributes paramFieldAttributes);

  public abstract boolean shouldSkipClass(Class paramClass);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.gson.ExclusionStrategy
 * JD-Core Version:    0.6.2
 */