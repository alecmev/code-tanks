package com.google.gson;

import java.lang.reflect.Type;

public abstract interface JsonDeserializationContext
{
  public abstract Object deserialize(JsonElement paramJsonElement, Type paramType)
    throws JsonParseException;
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.gson.JsonDeserializationContext
 * JD-Core Version:    0.6.2
 */