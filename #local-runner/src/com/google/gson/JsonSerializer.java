package com.google.gson;

import java.lang.reflect.Type;

public abstract interface JsonSerializer
{
  public abstract JsonElement serialize(Object paramObject, Type paramType, JsonSerializationContext paramJsonSerializationContext);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.gson.JsonSerializer
 * JD-Core Version:    0.6.2
 */