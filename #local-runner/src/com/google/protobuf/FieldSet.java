package com.google.protobuf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

final class FieldSet
{
  private final SmallSortedMap fields;
  private boolean isImmutable;
  private static final FieldSet DEFAULT_INSTANCE = new FieldSet(true);

  private FieldSet()
  {
    this.fields = SmallSortedMap.newFieldMap(16);
  }

  private FieldSet(boolean paramBoolean)
  {
    this.fields = SmallSortedMap.newFieldMap(0);
    makeImmutable();
  }

  public static FieldSet newFieldSet()
  {
    return new FieldSet();
  }

  public static FieldSet emptySet()
  {
    return DEFAULT_INSTANCE;
  }

  public void makeImmutable()
  {
    if (this.isImmutable)
      return;
    this.fields.makeImmutable();
    this.isImmutable = true;
  }

  public boolean isImmutable()
  {
    return this.isImmutable;
  }

  public FieldSet clone()
  {
    FieldSet localFieldSet = newFieldSet();
    Map.Entry localEntry;
    FieldDescriptorLite localFieldDescriptorLite;
    for (int i = 0; i < this.fields.getNumArrayEntries(); i++)
    {
      localEntry = this.fields.getArrayEntryAt(i);
      localFieldDescriptorLite = (FieldDescriptorLite)localEntry.getKey();
      localFieldSet.setField(localFieldDescriptorLite, localEntry.getValue());
    }
    Iterator localIterator = this.fields.getOverflowEntries().iterator();
    while (localIterator.hasNext())
    {
      localEntry = (Map.Entry)localIterator.next();
      localFieldDescriptorLite = (FieldDescriptorLite)localEntry.getKey();
      localFieldSet.setField(localFieldDescriptorLite, localEntry.getValue());
    }
    return localFieldSet;
  }

  public void clear()
  {
    this.fields.clear();
  }

  public Map getAllFields()
  {
    return this.fields.isImmutable() ? this.fields : Collections.unmodifiableMap(this.fields);
  }

  public Iterator iterator()
  {
    return this.fields.entrySet().iterator();
  }

  public boolean hasField(FieldDescriptorLite paramFieldDescriptorLite)
  {
    if (paramFieldDescriptorLite.isRepeated())
      throw new IllegalArgumentException("hasField() can only be called on non-repeated fields.");
    return this.fields.get(paramFieldDescriptorLite) != null;
  }

  public Object getField(FieldDescriptorLite paramFieldDescriptorLite)
  {
    return this.fields.get(paramFieldDescriptorLite);
  }

  public void setField(FieldDescriptorLite paramFieldDescriptorLite, Object paramObject)
  {
    if (paramFieldDescriptorLite.isRepeated())
    {
      if (!(paramObject instanceof List))
        throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
      ArrayList localArrayList = new ArrayList();
      localArrayList.addAll((List)paramObject);
      Iterator localIterator = localArrayList.iterator();
      while (localIterator.hasNext())
      {
        Object localObject = localIterator.next();
        verifyType(paramFieldDescriptorLite.getLiteType(), localObject);
      }
      paramObject = localArrayList;
    }
    else
    {
      verifyType(paramFieldDescriptorLite.getLiteType(), paramObject);
    }
    this.fields.put(paramFieldDescriptorLite, paramObject);
  }

  public void clearField(FieldDescriptorLite paramFieldDescriptorLite)
  {
    this.fields.remove(paramFieldDescriptorLite);
  }

  public int getRepeatedFieldCount(FieldDescriptorLite paramFieldDescriptorLite)
  {
    if (!paramFieldDescriptorLite.isRepeated())
      throw new IllegalArgumentException("getRepeatedField() can only be called on repeated fields.");
    Object localObject = this.fields.get(paramFieldDescriptorLite);
    if (localObject == null)
      return 0;
    return ((List)localObject).size();
  }

  public Object getRepeatedField(FieldDescriptorLite paramFieldDescriptorLite, int paramInt)
  {
    if (!paramFieldDescriptorLite.isRepeated())
      throw new IllegalArgumentException("getRepeatedField() can only be called on repeated fields.");
    Object localObject = this.fields.get(paramFieldDescriptorLite);
    if (localObject == null)
      throw new IndexOutOfBoundsException();
    return ((List)localObject).get(paramInt);
  }

  public void setRepeatedField(FieldDescriptorLite paramFieldDescriptorLite, int paramInt, Object paramObject)
  {
    if (!paramFieldDescriptorLite.isRepeated())
      throw new IllegalArgumentException("getRepeatedField() can only be called on repeated fields.");
    Object localObject = this.fields.get(paramFieldDescriptorLite);
    if (localObject == null)
      throw new IndexOutOfBoundsException();
    verifyType(paramFieldDescriptorLite.getLiteType(), paramObject);
    ((List)localObject).set(paramInt, paramObject);
  }

  public void addRepeatedField(FieldDescriptorLite paramFieldDescriptorLite, Object paramObject)
  {
    if (!paramFieldDescriptorLite.isRepeated())
      throw new IllegalArgumentException("addRepeatedField() can only be called on repeated fields.");
    verifyType(paramFieldDescriptorLite.getLiteType(), paramObject);
    Object localObject1 = this.fields.get(paramFieldDescriptorLite);
    Object localObject2;
    if (localObject1 == null)
    {
      localObject2 = new ArrayList();
      this.fields.put(paramFieldDescriptorLite, localObject2);
    }
    else
    {
      localObject2 = (List)localObject1;
    }
    ((List)localObject2).add(paramObject);
  }

  private static void verifyType(WireFormat.FieldType paramFieldType, Object paramObject)
  {
    if (paramObject == null)
      throw new NullPointerException();
    boolean bool = false;
    switch (1.$SwitchMap$com$google$protobuf$WireFormat$JavaType[paramFieldType.getJavaType().ordinal()])
    {
    case 1:
      bool = paramObject instanceof Integer;
      break;
    case 2:
      bool = paramObject instanceof Long;
      break;
    case 3:
      bool = paramObject instanceof Float;
      break;
    case 4:
      bool = paramObject instanceof Double;
      break;
    case 5:
      bool = paramObject instanceof Boolean;
      break;
    case 6:
      bool = paramObject instanceof String;
      break;
    case 7:
      bool = paramObject instanceof ByteString;
      break;
    case 8:
      bool = paramObject instanceof Internal.EnumLite;
      break;
    case 9:
      bool = paramObject instanceof MessageLite;
    }
    if (!bool)
      throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
  }

  public boolean isInitialized()
  {
    for (int i = 0; i < this.fields.getNumArrayEntries(); i++)
      if (!isInitialized(this.fields.getArrayEntryAt(i)))
        return false;
    Iterator localIterator = this.fields.getOverflowEntries().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if (!isInitialized(localEntry))
        return false;
    }
    return true;
  }

  private boolean isInitialized(Map.Entry paramEntry)
  {
    FieldDescriptorLite localFieldDescriptorLite = (FieldDescriptorLite)paramEntry.getKey();
    if (localFieldDescriptorLite.getLiteJavaType() == WireFormat.JavaType.MESSAGE)
      if (localFieldDescriptorLite.isRepeated())
      {
        Iterator localIterator = ((List)paramEntry.getValue()).iterator();
        while (localIterator.hasNext())
        {
          MessageLite localMessageLite = (MessageLite)localIterator.next();
          if (!localMessageLite.isInitialized())
            return false;
        }
      }
      else if (!((MessageLite)paramEntry.getValue()).isInitialized())
      {
        return false;
      }
    return true;
  }

  static int getWireFormatForFieldType(WireFormat.FieldType paramFieldType, boolean paramBoolean)
  {
    if (paramBoolean)
      return 2;
    return paramFieldType.getWireType();
  }

  public void mergeFrom(FieldSet paramFieldSet)
  {
    for (int i = 0; i < paramFieldSet.fields.getNumArrayEntries(); i++)
      mergeFromField(paramFieldSet.fields.getArrayEntryAt(i));
    Iterator localIterator = paramFieldSet.fields.getOverflowEntries().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      mergeFromField(localEntry);
    }
  }

  private void mergeFromField(Map.Entry paramEntry)
  {
    FieldDescriptorLite localFieldDescriptorLite = (FieldDescriptorLite)paramEntry.getKey();
    Object localObject1 = paramEntry.getValue();
    Object localObject2;
    if (localFieldDescriptorLite.isRepeated())
    {
      localObject2 = this.fields.get(localFieldDescriptorLite);
      if (localObject2 == null)
        this.fields.put(localFieldDescriptorLite, new ArrayList((List)localObject1));
      else
        ((List)localObject2).addAll((List)localObject1);
    }
    else if (localFieldDescriptorLite.getLiteJavaType() == WireFormat.JavaType.MESSAGE)
    {
      localObject2 = this.fields.get(localFieldDescriptorLite);
      if (localObject2 == null)
        this.fields.put(localFieldDescriptorLite, localObject1);
      else
        this.fields.put(localFieldDescriptorLite, localFieldDescriptorLite.internalMergeFrom(((MessageLite)localObject2).toBuilder(), (MessageLite)localObject1).build());
    }
    else
    {
      this.fields.put(localFieldDescriptorLite, localObject1);
    }
  }

  public static Object readPrimitiveField(CodedInputStream paramCodedInputStream, WireFormat.FieldType paramFieldType)
    throws IOException
  {
    switch (1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[paramFieldType.ordinal()])
    {
    case 1:
      return Double.valueOf(paramCodedInputStream.readDouble());
    case 2:
      return Float.valueOf(paramCodedInputStream.readFloat());
    case 3:
      return Long.valueOf(paramCodedInputStream.readInt64());
    case 4:
      return Long.valueOf(paramCodedInputStream.readUInt64());
    case 5:
      return Integer.valueOf(paramCodedInputStream.readInt32());
    case 6:
      return Long.valueOf(paramCodedInputStream.readFixed64());
    case 7:
      return Integer.valueOf(paramCodedInputStream.readFixed32());
    case 8:
      return Boolean.valueOf(paramCodedInputStream.readBool());
    case 9:
      return paramCodedInputStream.readString();
    case 10:
      return paramCodedInputStream.readBytes();
    case 11:
      return Integer.valueOf(paramCodedInputStream.readUInt32());
    case 12:
      return Integer.valueOf(paramCodedInputStream.readSFixed32());
    case 13:
      return Long.valueOf(paramCodedInputStream.readSFixed64());
    case 14:
      return Integer.valueOf(paramCodedInputStream.readSInt32());
    case 15:
      return Long.valueOf(paramCodedInputStream.readSInt64());
    case 16:
      throw new IllegalArgumentException("readPrimitiveField() cannot handle nested groups.");
    case 17:
      throw new IllegalArgumentException("readPrimitiveField() cannot handle embedded messages.");
    case 18:
      throw new IllegalArgumentException("readPrimitiveField() cannot handle enums.");
    }
    throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
  }

  public void writeTo(CodedOutputStream paramCodedOutputStream)
    throws IOException
  {
    Map.Entry localEntry;
    for (int i = 0; i < this.fields.getNumArrayEntries(); i++)
    {
      localEntry = this.fields.getArrayEntryAt(i);
      writeField((FieldDescriptorLite)localEntry.getKey(), localEntry.getValue(), paramCodedOutputStream);
    }
    Iterator localIterator = this.fields.getOverflowEntries().iterator();
    while (localIterator.hasNext())
    {
      localEntry = (Map.Entry)localIterator.next();
      writeField((FieldDescriptorLite)localEntry.getKey(), localEntry.getValue(), paramCodedOutputStream);
    }
  }

  public void writeMessageSetTo(CodedOutputStream paramCodedOutputStream)
    throws IOException
  {
    for (int i = 0; i < this.fields.getNumArrayEntries(); i++)
      writeMessageSetTo(this.fields.getArrayEntryAt(i), paramCodedOutputStream);
    Iterator localIterator = this.fields.getOverflowEntries().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      writeMessageSetTo(localEntry, paramCodedOutputStream);
    }
  }

  private void writeMessageSetTo(Map.Entry paramEntry, CodedOutputStream paramCodedOutputStream)
    throws IOException
  {
    FieldDescriptorLite localFieldDescriptorLite = (FieldDescriptorLite)paramEntry.getKey();
    if ((localFieldDescriptorLite.getLiteJavaType() == WireFormat.JavaType.MESSAGE) && (!localFieldDescriptorLite.isRepeated()) && (!localFieldDescriptorLite.isPacked()))
      paramCodedOutputStream.writeMessageSetExtension(((FieldDescriptorLite)paramEntry.getKey()).getNumber(), (MessageLite)paramEntry.getValue());
    else
      writeField(localFieldDescriptorLite, paramEntry.getValue(), paramCodedOutputStream);
  }

  private static void writeElement(CodedOutputStream paramCodedOutputStream, WireFormat.FieldType paramFieldType, int paramInt, Object paramObject)
    throws IOException
  {
    if (paramFieldType == WireFormat.FieldType.GROUP)
    {
      paramCodedOutputStream.writeGroup(paramInt, (MessageLite)paramObject);
    }
    else
    {
      paramCodedOutputStream.writeTag(paramInt, getWireFormatForFieldType(paramFieldType, false));
      writeElementNoTag(paramCodedOutputStream, paramFieldType, paramObject);
    }
  }

  private static void writeElementNoTag(CodedOutputStream paramCodedOutputStream, WireFormat.FieldType paramFieldType, Object paramObject)
    throws IOException
  {
    switch (1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[paramFieldType.ordinal()])
    {
    case 1:
      paramCodedOutputStream.writeDoubleNoTag(((Double)paramObject).doubleValue());
      break;
    case 2:
      paramCodedOutputStream.writeFloatNoTag(((Float)paramObject).floatValue());
      break;
    case 3:
      paramCodedOutputStream.writeInt64NoTag(((Long)paramObject).longValue());
      break;
    case 4:
      paramCodedOutputStream.writeUInt64NoTag(((Long)paramObject).longValue());
      break;
    case 5:
      paramCodedOutputStream.writeInt32NoTag(((Integer)paramObject).intValue());
      break;
    case 6:
      paramCodedOutputStream.writeFixed64NoTag(((Long)paramObject).longValue());
      break;
    case 7:
      paramCodedOutputStream.writeFixed32NoTag(((Integer)paramObject).intValue());
      break;
    case 8:
      paramCodedOutputStream.writeBoolNoTag(((Boolean)paramObject).booleanValue());
      break;
    case 9:
      paramCodedOutputStream.writeStringNoTag((String)paramObject);
      break;
    case 16:
      paramCodedOutputStream.writeGroupNoTag((MessageLite)paramObject);
      break;
    case 17:
      paramCodedOutputStream.writeMessageNoTag((MessageLite)paramObject);
      break;
    case 10:
      paramCodedOutputStream.writeBytesNoTag((ByteString)paramObject);
      break;
    case 11:
      paramCodedOutputStream.writeUInt32NoTag(((Integer)paramObject).intValue());
      break;
    case 12:
      paramCodedOutputStream.writeSFixed32NoTag(((Integer)paramObject).intValue());
      break;
    case 13:
      paramCodedOutputStream.writeSFixed64NoTag(((Long)paramObject).longValue());
      break;
    case 14:
      paramCodedOutputStream.writeSInt32NoTag(((Integer)paramObject).intValue());
      break;
    case 15:
      paramCodedOutputStream.writeSInt64NoTag(((Long)paramObject).longValue());
      break;
    case 18:
      paramCodedOutputStream.writeEnumNoTag(((Internal.EnumLite)paramObject).getNumber());
    }
  }

  public static void writeField(FieldDescriptorLite paramFieldDescriptorLite, Object paramObject, CodedOutputStream paramCodedOutputStream)
    throws IOException
  {
    WireFormat.FieldType localFieldType = paramFieldDescriptorLite.getLiteType();
    int i = paramFieldDescriptorLite.getNumber();
    if (paramFieldDescriptorLite.isRepeated())
    {
      List localList = (List)paramObject;
      Object localObject1;
      if (paramFieldDescriptorLite.isPacked())
      {
        paramCodedOutputStream.writeTag(i, 2);
        int j = 0;
        localObject1 = localList.iterator();
        Object localObject2;
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = ((Iterator)localObject1).next();
          j += computeElementSizeNoTag(localFieldType, localObject2);
        }
        paramCodedOutputStream.writeRawVarint32(j);
        localObject1 = localList.iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = ((Iterator)localObject1).next();
          writeElementNoTag(paramCodedOutputStream, localFieldType, localObject2);
        }
      }
      else
      {
        Iterator localIterator = localList.iterator();
        while (localIterator.hasNext())
        {
          localObject1 = localIterator.next();
          writeElement(paramCodedOutputStream, localFieldType, i, localObject1);
        }
      }
    }
    else
    {
      writeElement(paramCodedOutputStream, localFieldType, i, paramObject);
    }
  }

  public int getSerializedSize()
  {
    int i = 0;
    Map.Entry localEntry;
    for (int j = 0; j < this.fields.getNumArrayEntries(); j++)
    {
      localEntry = this.fields.getArrayEntryAt(j);
      i += computeFieldSize((FieldDescriptorLite)localEntry.getKey(), localEntry.getValue());
    }
    Iterator localIterator = this.fields.getOverflowEntries().iterator();
    while (localIterator.hasNext())
    {
      localEntry = (Map.Entry)localIterator.next();
      i += computeFieldSize((FieldDescriptorLite)localEntry.getKey(), localEntry.getValue());
    }
    return i;
  }

  public int getMessageSetSerializedSize()
  {
    int i = 0;
    for (int j = 0; j < this.fields.getNumArrayEntries(); j++)
      i += getMessageSetSerializedSize(this.fields.getArrayEntryAt(j));
    Iterator localIterator = this.fields.getOverflowEntries().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      i += getMessageSetSerializedSize(localEntry);
    }
    return i;
  }

  private int getMessageSetSerializedSize(Map.Entry paramEntry)
  {
    FieldDescriptorLite localFieldDescriptorLite = (FieldDescriptorLite)paramEntry.getKey();
    if ((localFieldDescriptorLite.getLiteJavaType() == WireFormat.JavaType.MESSAGE) && (!localFieldDescriptorLite.isRepeated()) && (!localFieldDescriptorLite.isPacked()))
      return CodedOutputStream.computeMessageSetExtensionSize(((FieldDescriptorLite)paramEntry.getKey()).getNumber(), (MessageLite)paramEntry.getValue());
    return computeFieldSize(localFieldDescriptorLite, paramEntry.getValue());
  }

  private static int computeElementSize(WireFormat.FieldType paramFieldType, int paramInt, Object paramObject)
  {
    int i = CodedOutputStream.computeTagSize(paramInt);
    if (paramFieldType == WireFormat.FieldType.GROUP)
      i *= 2;
    return i + computeElementSizeNoTag(paramFieldType, paramObject);
  }

  private static int computeElementSizeNoTag(WireFormat.FieldType paramFieldType, Object paramObject)
  {
    switch (1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[paramFieldType.ordinal()])
    {
    case 1:
      return CodedOutputStream.computeDoubleSizeNoTag(((Double)paramObject).doubleValue());
    case 2:
      return CodedOutputStream.computeFloatSizeNoTag(((Float)paramObject).floatValue());
    case 3:
      return CodedOutputStream.computeInt64SizeNoTag(((Long)paramObject).longValue());
    case 4:
      return CodedOutputStream.computeUInt64SizeNoTag(((Long)paramObject).longValue());
    case 5:
      return CodedOutputStream.computeInt32SizeNoTag(((Integer)paramObject).intValue());
    case 6:
      return CodedOutputStream.computeFixed64SizeNoTag(((Long)paramObject).longValue());
    case 7:
      return CodedOutputStream.computeFixed32SizeNoTag(((Integer)paramObject).intValue());
    case 8:
      return CodedOutputStream.computeBoolSizeNoTag(((Boolean)paramObject).booleanValue());
    case 9:
      return CodedOutputStream.computeStringSizeNoTag((String)paramObject);
    case 16:
      return CodedOutputStream.computeGroupSizeNoTag((MessageLite)paramObject);
    case 17:
      return CodedOutputStream.computeMessageSizeNoTag((MessageLite)paramObject);
    case 10:
      return CodedOutputStream.computeBytesSizeNoTag((ByteString)paramObject);
    case 11:
      return CodedOutputStream.computeUInt32SizeNoTag(((Integer)paramObject).intValue());
    case 12:
      return CodedOutputStream.computeSFixed32SizeNoTag(((Integer)paramObject).intValue());
    case 13:
      return CodedOutputStream.computeSFixed64SizeNoTag(((Long)paramObject).longValue());
    case 14:
      return CodedOutputStream.computeSInt32SizeNoTag(((Integer)paramObject).intValue());
    case 15:
      return CodedOutputStream.computeSInt64SizeNoTag(((Long)paramObject).longValue());
    case 18:
      return CodedOutputStream.computeEnumSizeNoTag(((Internal.EnumLite)paramObject).getNumber());
    }
    throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
  }

  public static int computeFieldSize(FieldDescriptorLite paramFieldDescriptorLite, Object paramObject)
  {
    WireFormat.FieldType localFieldType = paramFieldDescriptorLite.getLiteType();
    int i = paramFieldDescriptorLite.getNumber();
    if (paramFieldDescriptorLite.isRepeated())
    {
      Object localObject;
      if (paramFieldDescriptorLite.isPacked())
      {
        j = 0;
        localIterator = ((List)paramObject).iterator();
        while (localIterator.hasNext())
        {
          localObject = localIterator.next();
          j += computeElementSizeNoTag(localFieldType, localObject);
        }
        return j + CodedOutputStream.computeTagSize(i) + CodedOutputStream.computeRawVarint32Size(j);
      }
      int j = 0;
      Iterator localIterator = ((List)paramObject).iterator();
      while (localIterator.hasNext())
      {
        localObject = localIterator.next();
        j += computeElementSize(localFieldType, i, localObject);
      }
      return j;
    }
    return computeElementSize(localFieldType, i, paramObject);
  }

  public static abstract interface FieldDescriptorLite extends Comparable
  {
    public abstract int getNumber();

    public abstract WireFormat.FieldType getLiteType();

    public abstract WireFormat.JavaType getLiteJavaType();

    public abstract boolean isRepeated();

    public abstract boolean isPacked();

    public abstract Internal.EnumLiteMap getEnumType();

    public abstract MessageLite.Builder internalMergeFrom(MessageLite.Builder paramBuilder, MessageLite paramMessageLite);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.protobuf.FieldSet
 * JD-Core Version:    0.6.2
 */