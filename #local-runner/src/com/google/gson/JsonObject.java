package com.google.gson;

import com.google.gson.internal..Gson.Preconditions;
import com.google.gson.internal.StringMap;
import java.util.Set;

public final class JsonObject extends JsonElement
{
  private final StringMap members = new StringMap();

  public void add(String paramString, JsonElement paramJsonElement)
  {
    if (paramJsonElement == null)
      paramJsonElement = JsonNull.INSTANCE;
    this.members.put((String).Gson.Preconditions.checkNotNull(paramString), paramJsonElement);
  }

  public JsonElement remove(String paramString)
  {
    return (JsonElement)this.members.remove(paramString);
  }

  public void addProperty(String paramString1, String paramString2)
  {
    add(paramString1, createJsonElement(paramString2));
  }

  public void addProperty(String paramString, Number paramNumber)
  {
    add(paramString, createJsonElement(paramNumber));
  }

  public void addProperty(String paramString, Boolean paramBoolean)
  {
    add(paramString, createJsonElement(paramBoolean));
  }

  public void addProperty(String paramString, Character paramCharacter)
  {
    add(paramString, createJsonElement(paramCharacter));
  }

  private JsonElement createJsonElement(Object paramObject)
  {
    return paramObject == null ? JsonNull.INSTANCE : new JsonPrimitive(paramObject);
  }

  public Set entrySet()
  {
    return this.members.entrySet();
  }

  public boolean has(String paramString)
  {
    return this.members.containsKey(paramString);
  }

  public JsonElement get(String paramString)
  {
    if (this.members.containsKey(paramString))
    {
      JsonElement localJsonElement = (JsonElement)this.members.get(paramString);
      return localJsonElement == null ? JsonNull.INSTANCE : localJsonElement;
    }
    return null;
  }

  public JsonPrimitive getAsJsonPrimitive(String paramString)
  {
    return (JsonPrimitive)this.members.get(paramString);
  }

  public JsonArray getAsJsonArray(String paramString)
  {
    return (JsonArray)this.members.get(paramString);
  }

  public JsonObject getAsJsonObject(String paramString)
  {
    return (JsonObject)this.members.get(paramString);
  }

  public boolean equals(Object paramObject)
  {
    return (paramObject == this) || (((paramObject instanceof JsonObject)) && (((JsonObject)paramObject).members.equals(this.members)));
  }

  public int hashCode()
  {
    return this.members.hashCode();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.gson.JsonObject
 * JD-Core Version:    0.6.2
 */