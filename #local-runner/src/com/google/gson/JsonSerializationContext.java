package com.google.gson;

import java.lang.reflect.Type;

public abstract interface JsonSerializationContext
{
  public abstract JsonElement serialize(Object paramObject);

  public abstract JsonElement serialize(Object paramObject, Type paramType);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.gson.JsonSerializationContext
 * JD-Core Version:    0.6.2
 */