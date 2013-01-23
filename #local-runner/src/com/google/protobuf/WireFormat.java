package com.google.protobuf;

public final class WireFormat
{
  public static final int WIRETYPE_VARINT = 0;
  public static final int WIRETYPE_FIXED64 = 1;
  public static final int WIRETYPE_LENGTH_DELIMITED = 2;
  public static final int WIRETYPE_START_GROUP = 3;
  public static final int WIRETYPE_END_GROUP = 4;
  public static final int WIRETYPE_FIXED32 = 5;
  static final int TAG_TYPE_BITS = 3;
  static final int TAG_TYPE_MASK = 7;
  static final int MESSAGE_SET_ITEM = 1;
  static final int MESSAGE_SET_TYPE_ID = 2;
  static final int MESSAGE_SET_MESSAGE = 3;
  static final int MESSAGE_SET_ITEM_TAG = makeTag(1, 3);
  static final int MESSAGE_SET_ITEM_END_TAG = makeTag(1, 4);
  static final int MESSAGE_SET_TYPE_ID_TAG = makeTag(2, 0);
  static final int MESSAGE_SET_MESSAGE_TAG = makeTag(3, 2);

  static int getTagWireType(int paramInt)
  {
    return paramInt & 0x7;
  }

  public static int getTagFieldNumber(int paramInt)
  {
    return paramInt >>> 3;
  }

  static int makeTag(int paramInt1, int paramInt2)
  {
    return paramInt1 << 3 | paramInt2;
  }

  public static enum FieldType
  {
    DOUBLE(WireFormat.JavaType.DOUBLE, 1), FLOAT(WireFormat.JavaType.FLOAT, 5), INT64(WireFormat.JavaType.LONG, 0), UINT64(WireFormat.JavaType.LONG, 0), INT32(WireFormat.JavaType.INT, 0), FIXED64(WireFormat.JavaType.LONG, 1), FIXED32(WireFormat.JavaType.INT, 5), BOOL(WireFormat.JavaType.BOOLEAN, 0), STRING(WireFormat.JavaType.STRING, 2), GROUP(WireFormat.JavaType.MESSAGE, 3), MESSAGE(WireFormat.JavaType.MESSAGE, 2), BYTES(WireFormat.JavaType.BYTE_STRING, 2), UINT32(WireFormat.JavaType.INT, 0), ENUM(WireFormat.JavaType.ENUM, 0), SFIXED32(WireFormat.JavaType.INT, 5), SFIXED64(WireFormat.JavaType.LONG, 1), SINT32(WireFormat.JavaType.INT, 0), SINT64(WireFormat.JavaType.LONG, 0);

    private final WireFormat.JavaType javaType;
    private final int wireType;

    private FieldType(WireFormat.JavaType arg3, int arg4)
    {
      Object localObject;
      this.javaType = localObject;
      int i;
      this.wireType = i;
    }

    public WireFormat.JavaType getJavaType()
    {
      return this.javaType;
    }

    public int getWireType()
    {
      return this.wireType;
    }

    public boolean isPackable()
    {
      return true;
    }
  }

  public static enum JavaType
  {
    INT(Integer.valueOf(0)), LONG(Long.valueOf(0L)), FLOAT(Float.valueOf(0.0F)), DOUBLE(Double.valueOf(0.0D)), BOOLEAN(Boolean.valueOf(false)), STRING(""), BYTE_STRING(ByteString.EMPTY), ENUM(null), MESSAGE(null);

    private final Object defaultDefault;

    private JavaType(Object arg3)
    {
      Object localObject;
      this.defaultDefault = localObject;
    }

    Object getDefaultDefault()
    {
      return this.defaultDefault;
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.protobuf.WireFormat
 * JD-Core Version:    0.6.2
 */