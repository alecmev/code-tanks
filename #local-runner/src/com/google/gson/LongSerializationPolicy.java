package com.google.gson;

public enum LongSerializationPolicy
{
  DEFAULT, STRING;

  public abstract JsonElement serialize(Long paramLong);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.gson.LongSerializationPolicy
 * JD-Core Version:    0.6.2
 */