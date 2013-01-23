package com.google.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public final class UnknownFieldSet
  implements MessageLite
{
  private static final UnknownFieldSet defaultInstance = new UnknownFieldSet(Collections.emptyMap());
  private Map fields;

  private UnknownFieldSet()
  {
  }

  public static Builder newBuilder()
  {
    return Builder.access$000();
  }

  public static Builder newBuilder(UnknownFieldSet paramUnknownFieldSet)
  {
    return newBuilder().mergeFrom(paramUnknownFieldSet);
  }

  public static UnknownFieldSet getDefaultInstance()
  {
    return defaultInstance;
  }

  public UnknownFieldSet getDefaultInstanceForType()
  {
    return defaultInstance;
  }

  private UnknownFieldSet(Map paramMap)
  {
    this.fields = paramMap;
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject)
      return true;
    return ((paramObject instanceof UnknownFieldSet)) && (this.fields.equals(((UnknownFieldSet)paramObject).fields));
  }

  public int hashCode()
  {
    return this.fields.hashCode();
  }

  public Map asMap()
  {
    return this.fields;
  }

  public boolean hasField(int paramInt)
  {
    return this.fields.containsKey(Integer.valueOf(paramInt));
  }

  public Field getField(int paramInt)
  {
    Field localField = (Field)this.fields.get(Integer.valueOf(paramInt));
    return localField == null ? Field.getDefaultInstance() : localField;
  }

  public void writeTo(CodedOutputStream paramCodedOutputStream)
    throws IOException
  {
    Iterator localIterator = this.fields.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      ((Field)localEntry.getValue()).writeTo(((Integer)localEntry.getKey()).intValue(), paramCodedOutputStream);
    }
  }

  public String toString()
  {
    return TextFormat.printToString(this);
  }

  public ByteString toByteString()
  {
    try
    {
      ByteString.CodedBuilder localCodedBuilder = ByteString.newCodedBuilder(getSerializedSize());
      writeTo(localCodedBuilder.getCodedOutput());
      return localCodedBuilder.build();
    }
    catch (IOException localIOException)
    {
      throw new RuntimeException("Serializing to a ByteString threw an IOException (should never happen).", localIOException);
    }
  }

  public byte[] toByteArray()
  {
    try
    {
      byte[] arrayOfByte = new byte[getSerializedSize()];
      CodedOutputStream localCodedOutputStream = CodedOutputStream.newInstance(arrayOfByte);
      writeTo(localCodedOutputStream);
      localCodedOutputStream.checkNoSpaceLeft();
      return arrayOfByte;
    }
    catch (IOException localIOException)
    {
      throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", localIOException);
    }
  }

  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    CodedOutputStream localCodedOutputStream = CodedOutputStream.newInstance(paramOutputStream);
    writeTo(localCodedOutputStream);
    localCodedOutputStream.flush();
  }

  public void writeDelimitedTo(OutputStream paramOutputStream)
    throws IOException
  {
    CodedOutputStream localCodedOutputStream = CodedOutputStream.newInstance(paramOutputStream);
    localCodedOutputStream.writeRawVarint32(getSerializedSize());
    writeTo(localCodedOutputStream);
    localCodedOutputStream.flush();
  }

  public int getSerializedSize()
  {
    int i = 0;
    Iterator localIterator = this.fields.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      i += ((Field)localEntry.getValue()).getSerializedSize(((Integer)localEntry.getKey()).intValue());
    }
    return i;
  }

  public void writeAsMessageSetTo(CodedOutputStream paramCodedOutputStream)
    throws IOException
  {
    Iterator localIterator = this.fields.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      ((Field)localEntry.getValue()).writeAsMessageSetExtensionTo(((Integer)localEntry.getKey()).intValue(), paramCodedOutputStream);
    }
  }

  public int getSerializedSizeAsMessageSet()
  {
    int i = 0;
    Iterator localIterator = this.fields.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      i += ((Field)localEntry.getValue()).getSerializedSizeAsMessageSetExtension(((Integer)localEntry.getKey()).intValue());
    }
    return i;
  }

  public boolean isInitialized()
  {
    return true;
  }

  public static UnknownFieldSet parseFrom(CodedInputStream paramCodedInputStream)
    throws IOException
  {
    return newBuilder().mergeFrom(paramCodedInputStream).build();
  }

  public static UnknownFieldSet parseFrom(ByteString paramByteString)
    throws InvalidProtocolBufferException
  {
    return newBuilder().mergeFrom(paramByteString).build();
  }

  public static UnknownFieldSet parseFrom(byte[] paramArrayOfByte)
    throws InvalidProtocolBufferException
  {
    return newBuilder().mergeFrom(paramArrayOfByte).build();
  }

  public static UnknownFieldSet parseFrom(InputStream paramInputStream)
    throws IOException
  {
    return newBuilder().mergeFrom(paramInputStream).build();
  }

  public Builder newBuilderForType()
  {
    return newBuilder();
  }

  public Builder toBuilder()
  {
    return newBuilder().mergeFrom(this);
  }

  public static final class Field
  {
    private static final Field fieldDefaultInstance = newBuilder().build();
    private List varint;
    private List fixed32;
    private List fixed64;
    private List lengthDelimited;
    private List group;

    public static Builder newBuilder()
    {
      return Builder.access$300();
    }

    public static Builder newBuilder(Field paramField)
    {
      return newBuilder().mergeFrom(paramField);
    }

    public static Field getDefaultInstance()
    {
      return fieldDefaultInstance;
    }

    public List getVarintList()
    {
      return this.varint;
    }

    public List getFixed32List()
    {
      return this.fixed32;
    }

    public List getFixed64List()
    {
      return this.fixed64;
    }

    public List getLengthDelimitedList()
    {
      return this.lengthDelimited;
    }

    public List getGroupList()
    {
      return this.group;
    }

    public boolean equals(Object paramObject)
    {
      if (this == paramObject)
        return true;
      if (!(paramObject instanceof Field))
        return false;
      return Arrays.equals(getIdentityArray(), ((Field)paramObject).getIdentityArray());
    }

    public int hashCode()
    {
      return Arrays.hashCode(getIdentityArray());
    }

    private Object[] getIdentityArray()
    {
      return new Object[] { this.varint, this.fixed32, this.fixed64, this.lengthDelimited, this.group };
    }

    public void writeTo(int paramInt, CodedOutputStream paramCodedOutputStream)
      throws IOException
    {
      Iterator localIterator = this.varint.iterator();
      while (localIterator.hasNext())
      {
        long l1 = ((Long)localIterator.next()).longValue();
        paramCodedOutputStream.writeUInt64(paramInt, l1);
      }
      localIterator = this.fixed32.iterator();
      while (localIterator.hasNext())
      {
        int i = ((Integer)localIterator.next()).intValue();
        paramCodedOutputStream.writeFixed32(paramInt, i);
      }
      localIterator = this.fixed64.iterator();
      while (localIterator.hasNext())
      {
        long l2 = ((Long)localIterator.next()).longValue();
        paramCodedOutputStream.writeFixed64(paramInt, l2);
      }
      localIterator = this.lengthDelimited.iterator();
      Object localObject;
      while (localIterator.hasNext())
      {
        localObject = (ByteString)localIterator.next();
        paramCodedOutputStream.writeBytes(paramInt, (ByteString)localObject);
      }
      localIterator = this.group.iterator();
      while (localIterator.hasNext())
      {
        localObject = (UnknownFieldSet)localIterator.next();
        paramCodedOutputStream.writeGroup(paramInt, (MessageLite)localObject);
      }
    }

    public int getSerializedSize(int paramInt)
    {
      int i = 0;
      Iterator localIterator = this.varint.iterator();
      while (localIterator.hasNext())
      {
        long l1 = ((Long)localIterator.next()).longValue();
        i += CodedOutputStream.computeUInt64Size(paramInt, l1);
      }
      localIterator = this.fixed32.iterator();
      while (localIterator.hasNext())
      {
        int j = ((Integer)localIterator.next()).intValue();
        i += CodedOutputStream.computeFixed32Size(paramInt, j);
      }
      localIterator = this.fixed64.iterator();
      while (localIterator.hasNext())
      {
        long l2 = ((Long)localIterator.next()).longValue();
        i += CodedOutputStream.computeFixed64Size(paramInt, l2);
      }
      localIterator = this.lengthDelimited.iterator();
      Object localObject;
      while (localIterator.hasNext())
      {
        localObject = (ByteString)localIterator.next();
        i += CodedOutputStream.computeBytesSize(paramInt, (ByteString)localObject);
      }
      localIterator = this.group.iterator();
      while (localIterator.hasNext())
      {
        localObject = (UnknownFieldSet)localIterator.next();
        i += CodedOutputStream.computeGroupSize(paramInt, (MessageLite)localObject);
      }
      return i;
    }

    public void writeAsMessageSetExtensionTo(int paramInt, CodedOutputStream paramCodedOutputStream)
      throws IOException
    {
      Iterator localIterator = this.lengthDelimited.iterator();
      while (localIterator.hasNext())
      {
        ByteString localByteString = (ByteString)localIterator.next();
        paramCodedOutputStream.writeRawMessageSetExtension(paramInt, localByteString);
      }
    }

    public int getSerializedSizeAsMessageSetExtension(int paramInt)
    {
      int i = 0;
      Iterator localIterator = this.lengthDelimited.iterator();
      while (localIterator.hasNext())
      {
        ByteString localByteString = (ByteString)localIterator.next();
        i += CodedOutputStream.computeRawMessageSetExtensionSize(paramInt, localByteString);
      }
      return i;
    }

    public static final class Builder
    {
      private UnknownFieldSet.Field result;

      private static Builder create()
      {
        Builder localBuilder = new Builder();
        localBuilder.result = new UnknownFieldSet.Field(null);
        return localBuilder;
      }

      public UnknownFieldSet.Field build()
      {
        if (this.result.varint == null)
          this.result.varint = Collections.emptyList();
        else
          this.result.varint = Collections.unmodifiableList(this.result.varint);
        if (this.result.fixed32 == null)
          this.result.fixed32 = Collections.emptyList();
        else
          this.result.fixed32 = Collections.unmodifiableList(this.result.fixed32);
        if (this.result.fixed64 == null)
          this.result.fixed64 = Collections.emptyList();
        else
          this.result.fixed64 = Collections.unmodifiableList(this.result.fixed64);
        if (this.result.lengthDelimited == null)
          this.result.lengthDelimited = Collections.emptyList();
        else
          this.result.lengthDelimited = Collections.unmodifiableList(this.result.lengthDelimited);
        if (this.result.group == null)
          this.result.group = Collections.emptyList();
        else
          this.result.group = Collections.unmodifiableList(this.result.group);
        UnknownFieldSet.Field localField = this.result;
        this.result = null;
        return localField;
      }

      public Builder clear()
      {
        this.result = new UnknownFieldSet.Field(null);
        return this;
      }

      public Builder mergeFrom(UnknownFieldSet.Field paramField)
      {
        if (!paramField.varint.isEmpty())
        {
          if (this.result.varint == null)
            this.result.varint = new ArrayList();
          this.result.varint.addAll(paramField.varint);
        }
        if (!paramField.fixed32.isEmpty())
        {
          if (this.result.fixed32 == null)
            this.result.fixed32 = new ArrayList();
          this.result.fixed32.addAll(paramField.fixed32);
        }
        if (!paramField.fixed64.isEmpty())
        {
          if (this.result.fixed64 == null)
            this.result.fixed64 = new ArrayList();
          this.result.fixed64.addAll(paramField.fixed64);
        }
        if (!paramField.lengthDelimited.isEmpty())
        {
          if (this.result.lengthDelimited == null)
            this.result.lengthDelimited = new ArrayList();
          this.result.lengthDelimited.addAll(paramField.lengthDelimited);
        }
        if (!paramField.group.isEmpty())
        {
          if (this.result.group == null)
            this.result.group = new ArrayList();
          this.result.group.addAll(paramField.group);
        }
        return this;
      }

      public Builder addVarint(long paramLong)
      {
        if (this.result.varint == null)
          this.result.varint = new ArrayList();
        this.result.varint.add(Long.valueOf(paramLong));
        return this;
      }

      public Builder addFixed32(int paramInt)
      {
        if (this.result.fixed32 == null)
          this.result.fixed32 = new ArrayList();
        this.result.fixed32.add(Integer.valueOf(paramInt));
        return this;
      }

      public Builder addFixed64(long paramLong)
      {
        if (this.result.fixed64 == null)
          this.result.fixed64 = new ArrayList();
        this.result.fixed64.add(Long.valueOf(paramLong));
        return this;
      }

      public Builder addLengthDelimited(ByteString paramByteString)
      {
        if (this.result.lengthDelimited == null)
          this.result.lengthDelimited = new ArrayList();
        this.result.lengthDelimited.add(paramByteString);
        return this;
      }

      public Builder addGroup(UnknownFieldSet paramUnknownFieldSet)
      {
        if (this.result.group == null)
          this.result.group = new ArrayList();
        this.result.group.add(paramUnknownFieldSet);
        return this;
      }
    }
  }

  public static final class Builder
    implements MessageLite.Builder
  {
    private Map fields;
    private int lastFieldNumber;
    private UnknownFieldSet.Field.Builder lastField;

    private static Builder create()
    {
      Builder localBuilder = new Builder();
      localBuilder.reinitialize();
      return localBuilder;
    }

    private UnknownFieldSet.Field.Builder getFieldBuilder(int paramInt)
    {
      if (this.lastField != null)
      {
        if (paramInt == this.lastFieldNumber)
          return this.lastField;
        addField(this.lastFieldNumber, this.lastField.build());
      }
      if (paramInt == 0)
        return null;
      UnknownFieldSet.Field localField = (UnknownFieldSet.Field)this.fields.get(Integer.valueOf(paramInt));
      this.lastFieldNumber = paramInt;
      this.lastField = UnknownFieldSet.Field.newBuilder();
      if (localField != null)
        this.lastField.mergeFrom(localField);
      return this.lastField;
    }

    public UnknownFieldSet build()
    {
      getFieldBuilder(0);
      UnknownFieldSet localUnknownFieldSet;
      if (this.fields.isEmpty())
        localUnknownFieldSet = UnknownFieldSet.getDefaultInstance();
      else
        localUnknownFieldSet = new UnknownFieldSet(Collections.unmodifiableMap(this.fields), null);
      this.fields = null;
      return localUnknownFieldSet;
    }

    public UnknownFieldSet buildPartial()
    {
      return build();
    }

    public Builder clone()
    {
      getFieldBuilder(0);
      return UnknownFieldSet.newBuilder().mergeFrom(new UnknownFieldSet(this.fields, null));
    }

    public UnknownFieldSet getDefaultInstanceForType()
    {
      return UnknownFieldSet.getDefaultInstance();
    }

    private void reinitialize()
    {
      this.fields = Collections.emptyMap();
      this.lastFieldNumber = 0;
      this.lastField = null;
    }

    public Builder clear()
    {
      reinitialize();
      return this;
    }

    public Builder mergeFrom(UnknownFieldSet paramUnknownFieldSet)
    {
      if (paramUnknownFieldSet != UnknownFieldSet.getDefaultInstance())
      {
        Iterator localIterator = paramUnknownFieldSet.fields.entrySet().iterator();
        while (localIterator.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)localIterator.next();
          mergeField(((Integer)localEntry.getKey()).intValue(), (UnknownFieldSet.Field)localEntry.getValue());
        }
      }
      return this;
    }

    public Builder mergeField(int paramInt, UnknownFieldSet.Field paramField)
    {
      if (paramInt == 0)
        throw new IllegalArgumentException("Zero is not a valid field number.");
      if (hasField(paramInt))
        getFieldBuilder(paramInt).mergeFrom(paramField);
      else
        addField(paramInt, paramField);
      return this;
    }

    public Builder mergeVarintField(int paramInt1, int paramInt2)
    {
      if (paramInt1 == 0)
        throw new IllegalArgumentException("Zero is not a valid field number.");
      getFieldBuilder(paramInt1).addVarint(paramInt2);
      return this;
    }

    public boolean hasField(int paramInt)
    {
      if (paramInt == 0)
        throw new IllegalArgumentException("Zero is not a valid field number.");
      return (paramInt == this.lastFieldNumber) || (this.fields.containsKey(Integer.valueOf(paramInt)));
    }

    public Builder addField(int paramInt, UnknownFieldSet.Field paramField)
    {
      if (paramInt == 0)
        throw new IllegalArgumentException("Zero is not a valid field number.");
      if ((this.lastField != null) && (this.lastFieldNumber == paramInt))
      {
        this.lastField = null;
        this.lastFieldNumber = 0;
      }
      if (this.fields.isEmpty())
        this.fields = new TreeMap();
      this.fields.put(Integer.valueOf(paramInt), paramField);
      return this;
    }

    public Map asMap()
    {
      getFieldBuilder(0);
      return Collections.unmodifiableMap(this.fields);
    }

    public Builder mergeFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      while (true)
      {
        int i = paramCodedInputStream.readTag();
        if ((i == 0) || (!mergeFieldFrom(i, paramCodedInputStream)))
          break;
      }
      return this;
    }

    public boolean mergeFieldFrom(int paramInt, CodedInputStream paramCodedInputStream)
      throws IOException
    {
      int i = WireFormat.getTagFieldNumber(paramInt);
      switch (WireFormat.getTagWireType(paramInt))
      {
      case 0:
        getFieldBuilder(i).addVarint(paramCodedInputStream.readInt64());
        return true;
      case 1:
        getFieldBuilder(i).addFixed64(paramCodedInputStream.readFixed64());
        return true;
      case 2:
        getFieldBuilder(i).addLengthDelimited(paramCodedInputStream.readBytes());
        return true;
      case 3:
        Builder localBuilder = UnknownFieldSet.newBuilder();
        paramCodedInputStream.readGroup(i, localBuilder, ExtensionRegistry.getEmptyRegistry());
        getFieldBuilder(i).addGroup(localBuilder.build());
        return true;
      case 4:
        return false;
      case 5:
        getFieldBuilder(i).addFixed32(paramCodedInputStream.readFixed32());
        return true;
      }
      throw InvalidProtocolBufferException.invalidWireType();
    }

    public Builder mergeFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      try
      {
        CodedInputStream localCodedInputStream = paramByteString.newCodedInput();
        mergeFrom(localCodedInputStream);
        localCodedInputStream.checkLastTagWas(0);
        return this;
      }
      catch (InvalidProtocolBufferException localInvalidProtocolBufferException)
      {
        throw localInvalidProtocolBufferException;
      }
      catch (IOException localIOException)
      {
        throw new RuntimeException("Reading from a ByteString threw an IOException (should never happen).", localIOException);
      }
    }

    public Builder mergeFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      try
      {
        CodedInputStream localCodedInputStream = CodedInputStream.newInstance(paramArrayOfByte);
        mergeFrom(localCodedInputStream);
        localCodedInputStream.checkLastTagWas(0);
        return this;
      }
      catch (InvalidProtocolBufferException localInvalidProtocolBufferException)
      {
        throw localInvalidProtocolBufferException;
      }
      catch (IOException localIOException)
      {
        throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", localIOException);
      }
    }

    public Builder mergeFrom(InputStream paramInputStream)
      throws IOException
    {
      CodedInputStream localCodedInputStream = CodedInputStream.newInstance(paramInputStream);
      mergeFrom(localCodedInputStream);
      localCodedInputStream.checkLastTagWas(0);
      return this;
    }

    public boolean mergeDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      int i = paramInputStream.read();
      if (i == -1)
        return false;
      int j = CodedInputStream.readRawVarint32(i, paramInputStream);
      AbstractMessageLite.Builder.LimitedInputStream localLimitedInputStream = new AbstractMessageLite.Builder.LimitedInputStream(paramInputStream, j);
      mergeFrom(localLimitedInputStream);
      return true;
    }

    public boolean mergeDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return mergeDelimitedFrom(paramInputStream);
    }

    public Builder mergeFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return mergeFrom(paramCodedInputStream);
    }

    public Builder mergeFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return mergeFrom(paramByteString);
    }

    public Builder mergeFrom(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws InvalidProtocolBufferException
    {
      try
      {
        CodedInputStream localCodedInputStream = CodedInputStream.newInstance(paramArrayOfByte, paramInt1, paramInt2);
        mergeFrom(localCodedInputStream);
        localCodedInputStream.checkLastTagWas(0);
        return this;
      }
      catch (InvalidProtocolBufferException localInvalidProtocolBufferException)
      {
        throw localInvalidProtocolBufferException;
      }
      catch (IOException localIOException)
      {
        throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", localIOException);
      }
    }

    public Builder mergeFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return mergeFrom(paramArrayOfByte);
    }

    public Builder mergeFrom(byte[] paramArrayOfByte, int paramInt1, int paramInt2, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return mergeFrom(paramArrayOfByte, paramInt1, paramInt2);
    }

    public Builder mergeFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return mergeFrom(paramInputStream);
    }

    public boolean isInitialized()
    {
      return true;
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.protobuf.UnknownFieldSet
 * JD-Core Version:    0.6.2
 */