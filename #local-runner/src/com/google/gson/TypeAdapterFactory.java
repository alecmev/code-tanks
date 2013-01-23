package com.google.gson;

import com.google.gson.reflect.TypeToken;

public abstract interface TypeAdapterFactory
{
  public abstract TypeAdapter create(Gson paramGson, TypeToken paramTypeToken);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.gson.TypeAdapterFactory
 * JD-Core Version:    0.6.2
 */