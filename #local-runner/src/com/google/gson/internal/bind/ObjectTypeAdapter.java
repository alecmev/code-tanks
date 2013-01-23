package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.StringMap;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class ObjectTypeAdapter extends TypeAdapter
{
  public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory()
  {
    public TypeAdapter create(Gson paramAnonymousGson, TypeToken paramAnonymousTypeToken)
    {
      if (paramAnonymousTypeToken.getRawType() == Object.class)
        return new ObjectTypeAdapter(paramAnonymousGson, null);
      return null;
    }
  };
  private final Gson gson;

  private ObjectTypeAdapter(Gson paramGson)
  {
    this.gson = paramGson;
  }

  public Object read(JsonReader paramJsonReader)
    throws IOException
  {
    JsonToken localJsonToken = paramJsonReader.peek();
    switch (2.$SwitchMap$com$google$gson$stream$JsonToken[localJsonToken.ordinal()])
    {
    case 1:
      ArrayList localArrayList = new ArrayList();
      paramJsonReader.beginArray();
      while (paramJsonReader.hasNext())
        localArrayList.add(read(paramJsonReader));
      paramJsonReader.endArray();
      return localArrayList;
    case 2:
      StringMap localStringMap = new StringMap();
      paramJsonReader.beginObject();
      while (paramJsonReader.hasNext())
        localStringMap.put(paramJsonReader.nextName(), read(paramJsonReader));
      paramJsonReader.endObject();
      return localStringMap;
    case 3:
      return paramJsonReader.nextString();
    case 4:
      return Double.valueOf(paramJsonReader.nextDouble());
    case 5:
      return Boolean.valueOf(paramJsonReader.nextBoolean());
    case 6:
      paramJsonReader.nextNull();
      return null;
    }
    throw new IllegalStateException();
  }

  public void write(JsonWriter paramJsonWriter, Object paramObject)
    throws IOException
  {
    if (paramObject == null)
    {
      paramJsonWriter.nullValue();
      return;
    }
    TypeAdapter localTypeAdapter = this.gson.getAdapter(paramObject.getClass());
    if ((localTypeAdapter instanceof ObjectTypeAdapter))
    {
      paramJsonWriter.beginObject();
      paramJsonWriter.endObject();
      return;
    }
    localTypeAdapter.write(paramJsonWriter, paramObject);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.gson.internal.bind.ObjectTypeAdapter
 * JD-Core Version:    0.6.2
 */