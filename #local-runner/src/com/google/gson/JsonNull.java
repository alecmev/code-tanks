package com.google.gson;

public final class JsonNull extends JsonElement
{
  public static final JsonNull INSTANCE = new JsonNull();

  public int hashCode()
  {
    return JsonNull.class.hashCode();
  }

  public boolean equals(Object paramObject)
  {
    return (this == paramObject) || ((paramObject instanceof JsonNull));
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.gson.JsonNull
 * JD-Core Version:    0.6.2
 */