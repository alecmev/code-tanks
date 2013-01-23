package com.google.gson.internal;

import com.google.gson.stream.JsonReader;
import java.io.IOException;

public abstract class JsonReaderInternalAccess
{
  public static JsonReaderInternalAccess INSTANCE;

  public abstract void promoteNameToValue(JsonReader paramJsonReader)
    throws IOException;
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.gson.internal.JsonReaderInternalAccess
 * JD-Core Version:    0.6.2
 */