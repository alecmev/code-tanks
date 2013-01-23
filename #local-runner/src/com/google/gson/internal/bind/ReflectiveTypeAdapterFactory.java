package com.google.gson.internal.bind;

import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal..Gson.Types;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.internal.Primitives;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public final class ReflectiveTypeAdapterFactory
  implements TypeAdapterFactory
{
  private final ConstructorConstructor constructorConstructor;
  private final FieldNamingStrategy fieldNamingPolicy;
  private final Excluder excluder;

  public ReflectiveTypeAdapterFactory(ConstructorConstructor paramConstructorConstructor, FieldNamingStrategy paramFieldNamingStrategy, Excluder paramExcluder)
  {
    this.constructorConstructor = paramConstructorConstructor;
    this.fieldNamingPolicy = paramFieldNamingStrategy;
    this.excluder = paramExcluder;
  }

  public boolean excludeField(Field paramField, boolean paramBoolean)
  {
    return (!this.excluder.excludeClass(paramField.getType(), paramBoolean)) && (!this.excluder.excludeField(paramField, paramBoolean));
  }

  private String getFieldName(Field paramField)
  {
    SerializedName localSerializedName = (SerializedName)paramField.getAnnotation(SerializedName.class);
    return localSerializedName == null ? this.fieldNamingPolicy.translateName(paramField) : localSerializedName.value();
  }

  public TypeAdapter create(Gson paramGson, TypeToken paramTypeToken)
  {
    Class localClass = paramTypeToken.getRawType();
    if (!Object.class.isAssignableFrom(localClass))
      return null;
    ObjectConstructor localObjectConstructor = this.constructorConstructor.get(paramTypeToken);
    return new Adapter(localObjectConstructor, getBoundFields(paramGson, paramTypeToken, localClass), null);
  }

  private BoundField createBoundField(final Gson paramGson, final Field paramField, String paramString, final TypeToken paramTypeToken, boolean paramBoolean1, boolean paramBoolean2)
  {
    final boolean bool = Primitives.isPrimitive(paramTypeToken.getRawType());
    return new BoundField(paramString, paramBoolean1, paramBoolean2)
    {
      final TypeAdapter typeAdapter = paramGson.getAdapter(paramTypeToken);

      void write(JsonWriter paramAnonymousJsonWriter, Object paramAnonymousObject)
        throws IOException, IllegalAccessException
      {
        Object localObject = paramField.get(paramAnonymousObject);
        TypeAdapterRuntimeTypeWrapper localTypeAdapterRuntimeTypeWrapper = new TypeAdapterRuntimeTypeWrapper(paramGson, this.typeAdapter, paramTypeToken.getType());
        localTypeAdapterRuntimeTypeWrapper.write(paramAnonymousJsonWriter, localObject);
      }

      void read(JsonReader paramAnonymousJsonReader, Object paramAnonymousObject)
        throws IOException, IllegalAccessException
      {
        Object localObject = this.typeAdapter.read(paramAnonymousJsonReader);
        if ((localObject != null) || (!bool))
          paramField.set(paramAnonymousObject, localObject);
      }
    };
  }

  private Map getBoundFields(Gson paramGson, TypeToken paramTypeToken, Class paramClass)
  {
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    if (paramClass.isInterface())
      return localLinkedHashMap;
    Type localType1 = paramTypeToken.getType();
    while (paramClass != Object.class)
    {
      Field[] arrayOfField1 = paramClass.getDeclaredFields();
      for (Field localField : arrayOfField1)
      {
        boolean bool1 = excludeField(localField, true);
        boolean bool2 = excludeField(localField, false);
        if ((bool1) || (bool2))
        {
          localField.setAccessible(true);
          Type localType2 = $Gson.Types.resolve(paramTypeToken.getType(), paramClass, localField.getGenericType());
          BoundField localBoundField1 = createBoundField(paramGson, localField, getFieldName(localField), TypeToken.get(localType2), bool1, bool2);
          BoundField localBoundField2 = (BoundField)localLinkedHashMap.put(localBoundField1.name, localBoundField1);
          if (localBoundField2 != null)
            throw new IllegalArgumentException(localType1 + " declares multiple JSON fields named " + localBoundField2.name);
        }
      }
      paramTypeToken = TypeToken.get($Gson.Types.resolve(paramTypeToken.getType(), paramClass, paramClass.getGenericSuperclass()));
      paramClass = paramTypeToken.getRawType();
    }
    return localLinkedHashMap;
  }

  public final class Adapter extends TypeAdapter
  {
    private final ObjectConstructor constructor;
    private final Map boundFields;

    private Adapter(ObjectConstructor paramMap, Map arg3)
    {
      this.constructor = paramMap;
      Object localObject;
      this.boundFields = localObject;
    }

    public Object read(JsonReader paramJsonReader)
      throws IOException
    {
      if (paramJsonReader.peek() == JsonToken.NULL)
      {
        paramJsonReader.nextNull();
        return null;
      }
      Object localObject = this.constructor.construct();
      try
      {
        paramJsonReader.beginObject();
        while (paramJsonReader.hasNext())
        {
          String str = paramJsonReader.nextName();
          ReflectiveTypeAdapterFactory.BoundField localBoundField = (ReflectiveTypeAdapterFactory.BoundField)this.boundFields.get(str);
          if ((localBoundField == null) || (!localBoundField.deserialized))
            paramJsonReader.skipValue();
          else
            localBoundField.read(paramJsonReader, localObject);
        }
      }
      catch (IllegalStateException localIllegalStateException)
      {
        throw new JsonSyntaxException(localIllegalStateException);
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        throw new AssertionError(localIllegalAccessException);
      }
      paramJsonReader.endObject();
      return localObject;
    }

    public void write(JsonWriter paramJsonWriter, Object paramObject)
      throws IOException
    {
      if (paramObject == null)
      {
        paramJsonWriter.nullValue();
        return;
      }
      paramJsonWriter.beginObject();
      try
      {
        Iterator localIterator = this.boundFields.values().iterator();
        while (localIterator.hasNext())
        {
          ReflectiveTypeAdapterFactory.BoundField localBoundField = (ReflectiveTypeAdapterFactory.BoundField)localIterator.next();
          if (localBoundField.serialized)
          {
            paramJsonWriter.name(localBoundField.name);
            localBoundField.write(paramJsonWriter, paramObject);
          }
        }
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        throw new AssertionError();
      }
      paramJsonWriter.endObject();
    }
  }

  static abstract class BoundField
  {
    final String name;
    final boolean serialized;
    final boolean deserialized;

    protected BoundField(String paramString, boolean paramBoolean1, boolean paramBoolean2)
    {
      this.name = paramString;
      this.serialized = paramBoolean1;
      this.deserialized = paramBoolean2;
    }

    abstract void write(JsonWriter paramJsonWriter, Object paramObject)
      throws IOException, IllegalAccessException;

    abstract void read(JsonReader paramJsonReader, Object paramObject)
      throws IOException, IllegalAccessException;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.gson.internal.bind.ReflectiveTypeAdapterFactory
 * JD-Core Version:    0.6.2
 */