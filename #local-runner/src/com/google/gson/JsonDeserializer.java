package com.google.gson;

import java.lang.reflect.Type;

public abstract interface JsonDeserializer
{
  public abstract Object deserialize(JsonElement paramJsonElement, Type paramType, JsonDeserializationContext paramJsonDeserializationContext)
    throws JsonParseException;
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.gson.JsonDeserializer
 * JD-Core Version:    0.6.2
 */