package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class TimeTypeAdapter extends TypeAdapter
{
  public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory()
  {
    public TypeAdapter create(Gson paramAnonymousGson, TypeToken paramAnonymousTypeToken)
    {
      return paramAnonymousTypeToken.getRawType() == Time.class ? new TimeTypeAdapter() : null;
    }
  };
  private final DateFormat format = new SimpleDateFormat("hh:mm:ss a");

  public synchronized Time read(JsonReader paramJsonReader)
    throws IOException
  {
    if (paramJsonReader.peek() == JsonToken.NULL)
    {
      paramJsonReader.nextNull();
      return null;
    }
    try
    {
      Date localDate = this.format.parse(paramJsonReader.nextString());
      return new Time(localDate.getTime());
    }
    catch (ParseException localParseException)
    {
      throw new JsonSyntaxException(localParseException);
    }
  }

  public synchronized void write(JsonWriter paramJsonWriter, Time paramTime)
    throws IOException
  {
    paramJsonWriter.value(paramTime == null ? null : this.format.format(paramTime));
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.gson.internal.bind.TimeTypeAdapter
 * JD-Core Version:    0.6.2
 */