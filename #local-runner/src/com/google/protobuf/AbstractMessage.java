package com.google.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class AbstractMessage extends AbstractMessageLite
  implements Message
{
  private int memoizedSize = -1;

  public boolean isInitialized()
  {
    Iterator localIterator1 = getDescriptorForType().getFields().iterator();
    Object localObject;
    while (localIterator1.hasNext())
    {
      localObject = (Descriptors.FieldDescriptor)localIterator1.next();
      if ((((Descriptors.FieldDescriptor)localObject).isRequired()) && (!hasField((Descriptors.FieldDescriptor)localObject)))
        return false;
    }
    localIterator1 = getAllFields().entrySet().iterator();
    while (localIterator1.hasNext())
    {
      localObject = (Map.Entry)localIterator1.next();
      Descriptors.FieldDescriptor localFieldDescriptor = (Descriptors.FieldDescriptor)((Map.Entry)localObject).getKey();
      if (localFieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE)
        if (localFieldDescriptor.isRepeated())
        {
          Iterator localIterator2 = ((List)((Map.Entry)localObject).getValue()).iterator();
          while (localIterator2.hasNext())
          {
            Message localMessage = (Message)localIterator2.next();
            if (!localMessage.isInitialized())
              return false;
          }
        }
        else if (!((Message)((Map.Entry)localObject).getValue()).isInitialized())
        {
          return false;
        }
    }
    return true;
  }

  public final String toString()
  {
    return TextFormat.printToString(this);
  }

  public void writeTo(CodedOutputStream paramCodedOutputStream)
    throws IOException
  {
    boolean bool = getDescriptorForType().getOptions().getMessageSetWireFormat();
    Object localObject1 = getAllFields().entrySet().iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Map.Entry localEntry = (Map.Entry)((Iterator)localObject1).next();
      Descriptors.FieldDescriptor localFieldDescriptor = (Descriptors.FieldDescriptor)localEntry.getKey();
      Object localObject2 = localEntry.getValue();
      if ((bool) && (localFieldDescriptor.isExtension()) && (localFieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.MESSAGE) && (!localFieldDescriptor.isRepeated()))
        paramCodedOutputStream.writeMessageSetExtension(localFieldDescriptor.getNumber(), (Message)localObject2);
      else
        FieldSet.writeField(localFieldDescriptor, localObject2, paramCodedOutputStream);
    }
    localObject1 = getUnknownFields();
    if (bool)
      ((UnknownFieldSet)localObject1).writeAsMessageSetTo(paramCodedOutputStream);
    else
      ((UnknownFieldSet)localObject1).writeTo(paramCodedOutputStream);
  }

  public int getSerializedSize()
  {
    int i = this.memoizedSize;
    if (i != -1)
      return i;
    i = 0;
    boolean bool = getDescriptorForType().getOptions().getMessageSetWireFormat();
    Object localObject1 = getAllFields().entrySet().iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Map.Entry localEntry = (Map.Entry)((Iterator)localObject1).next();
      Descriptors.FieldDescriptor localFieldDescriptor = (Descriptors.FieldDescriptor)localEntry.getKey();
      Object localObject2 = localEntry.getValue();
      if ((bool) && (localFieldDescriptor.isExtension()) && (localFieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.MESSAGE) && (!localFieldDescriptor.isRepeated()))
        i += CodedOutputStream.computeMessageSetExtensionSize(localFieldDescriptor.getNumber(), (Message)localObject2);
      else
        i += FieldSet.computeFieldSize(localFieldDescriptor, localObject2);
    }
    localObject1 = getUnknownFields();
    if (bool)
      i += ((UnknownFieldSet)localObject1).getSerializedSizeAsMessageSet();
    else
      i += ((UnknownFieldSet)localObject1).getSerializedSize();
    this.memoizedSize = i;
    return i;
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == this)
      return true;
    if (!(paramObject instanceof Message))
      return false;
    Message localMessage = (Message)paramObject;
    if (getDescriptorForType() != localMessage.getDescriptorForType())
      return false;
    return (getAllFields().equals(localMessage.getAllFields())) && (getUnknownFields().equals(localMessage.getUnknownFields()));
  }

  public int hashCode()
  {
    int i = 41;
    i = 19 * i + getDescriptorForType().hashCode();
    i = hashFields(i, getAllFields());
    i = 29 * i + getUnknownFields().hashCode();
    return i;
  }

  protected int hashFields(int paramInt, Map paramMap)
  {
    Iterator localIterator = paramMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      Descriptors.FieldDescriptor localFieldDescriptor = (Descriptors.FieldDescriptor)localEntry.getKey();
      Object localObject = localEntry.getValue();
      paramInt = 37 * paramInt + localFieldDescriptor.getNumber();
      if (localFieldDescriptor.getType() != Descriptors.FieldDescriptor.Type.ENUM)
      {
        paramInt = 53 * paramInt + localObject.hashCode();
      }
      else if (localFieldDescriptor.isRepeated())
      {
        List localList = (List)localObject;
        paramInt = 53 * paramInt + hashEnumList(localList);
      }
      else
      {
        paramInt = 53 * paramInt + hashEnum((Internal.EnumLite)localObject);
      }
    }
    return paramInt;
  }

  protected static int hashLong(long paramLong)
  {
    return (int)(paramLong ^ paramLong >>> 32);
  }

  protected static int hashBoolean(boolean paramBoolean)
  {
    return paramBoolean ? 1231 : 1237;
  }

  protected static int hashEnum(Internal.EnumLite paramEnumLite)
  {
    return paramEnumLite.getNumber();
  }

  protected static int hashEnumList(List paramList)
  {
    int i = 1;
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      Internal.EnumLite localEnumLite = (Internal.EnumLite)localIterator.next();
      i = 31 * i + hashEnum(localEnumLite);
    }
    return i;
  }

  public static abstract class Builder extends AbstractMessageLite.Builder
    implements Message.Builder
  {
    public abstract Builder clone();

    public Builder clear()
    {
      Iterator localIterator = getAllFields().entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        clearField((Descriptors.FieldDescriptor)localEntry.getKey());
      }
      return this;
    }

    public Builder mergeFrom(Message paramMessage)
    {
      if (paramMessage.getDescriptorForType() != getDescriptorForType())
        throw new IllegalArgumentException("mergeFrom(Message) can only merge messages of the same type.");
      Iterator localIterator = paramMessage.getAllFields().entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        Descriptors.FieldDescriptor localFieldDescriptor = (Descriptors.FieldDescriptor)localEntry.getKey();
        Object localObject1;
        if (localFieldDescriptor.isRepeated())
        {
          localObject1 = ((List)localEntry.getValue()).iterator();
          while (((Iterator)localObject1).hasNext())
          {
            Object localObject2 = ((Iterator)localObject1).next();
            addRepeatedField(localFieldDescriptor, localObject2);
          }
        }
        else if (localFieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE)
        {
          localObject1 = (Message)getField(localFieldDescriptor);
          if (localObject1 == ((Message)localObject1).getDefaultInstanceForType())
            setField(localFieldDescriptor, localEntry.getValue());
          else
            setField(localFieldDescriptor, ((Message)localObject1).newBuilderForType().mergeFrom((Message)localObject1).mergeFrom((Message)localEntry.getValue()).build());
        }
        else
        {
          setField(localFieldDescriptor, localEntry.getValue());
        }
      }
      mergeUnknownFields(paramMessage.getUnknownFields());
      return this;
    }

    public Builder mergeFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return mergeFrom(paramCodedInputStream, ExtensionRegistry.getEmptyRegistry());
    }

    public Builder mergeFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      UnknownFieldSet.Builder localBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
      while (true)
      {
        int i = paramCodedInputStream.readTag();
        if ((i == 0) || (!mergeFieldFrom(paramCodedInputStream, localBuilder, paramExtensionRegistryLite, this, i)))
          break;
      }
      setUnknownFields(localBuilder.build());
      return this;
    }

    static boolean mergeFieldFrom(CodedInputStream paramCodedInputStream, UnknownFieldSet.Builder paramBuilder, ExtensionRegistryLite paramExtensionRegistryLite, Message.Builder paramBuilder1, int paramInt)
      throws IOException
    {
      Descriptors.Descriptor localDescriptor = paramBuilder1.getDescriptorForType();
      if ((localDescriptor.getOptions().getMessageSetWireFormat()) && (paramInt == WireFormat.MESSAGE_SET_ITEM_TAG))
      {
        mergeMessageSetExtensionFromCodedStream(paramCodedInputStream, paramBuilder, paramExtensionRegistryLite, paramBuilder1);
        return true;
      }
      int i = WireFormat.getTagWireType(paramInt);
      int j = WireFormat.getTagFieldNumber(paramInt);
      Message localMessage = null;
      Descriptors.FieldDescriptor localFieldDescriptor;
      if (localDescriptor.isExtensionNumber(j))
      {
        if ((paramExtensionRegistryLite instanceof ExtensionRegistry))
        {
          ExtensionRegistry.ExtensionInfo localExtensionInfo = ((ExtensionRegistry)paramExtensionRegistryLite).findExtensionByNumber(localDescriptor, j);
          if (localExtensionInfo == null)
          {
            localFieldDescriptor = null;
          }
          else
          {
            localFieldDescriptor = localExtensionInfo.descriptor;
            localMessage = localExtensionInfo.defaultInstance;
            if ((localMessage == null) && (localFieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE))
              throw new IllegalStateException("Message-typed extension lacked default instance: " + localFieldDescriptor.getFullName());
          }
        }
        else
        {
          localFieldDescriptor = null;
        }
      }
      else
        localFieldDescriptor = localDescriptor.findFieldByNumber(j);
      int k = 0;
      int m = 0;
      if (localFieldDescriptor == null)
        k = 1;
      else if (i == FieldSet.getWireFormatForFieldType(localFieldDescriptor.getLiteType(), false))
        m = 0;
      else if ((localFieldDescriptor.isPackable()) && (i == FieldSet.getWireFormatForFieldType(localFieldDescriptor.getLiteType(), true)))
        m = 1;
      else
        k = 1;
      if (k != 0)
        return paramBuilder.mergeFieldFrom(paramInt, paramCodedInputStream);
      if (m != 0)
      {
        int n = paramCodedInputStream.readRawVarint32();
        int i1 = paramCodedInputStream.pushLimit(n);
        if (localFieldDescriptor.getLiteType() == WireFormat.FieldType.ENUM)
          while (paramCodedInputStream.getBytesUntilLimit() > 0)
          {
            int i3 = paramCodedInputStream.readEnum();
            Descriptors.EnumValueDescriptor localEnumValueDescriptor = localFieldDescriptor.getEnumType().findValueByNumber(i3);
            if (localEnumValueDescriptor == null)
              return true;
            paramBuilder1.addRepeatedField(localFieldDescriptor, localEnumValueDescriptor);
          }
        while (paramCodedInputStream.getBytesUntilLimit() > 0)
        {
          Object localObject2 = FieldSet.readPrimitiveField(paramCodedInputStream, localFieldDescriptor.getLiteType());
          paramBuilder1.addRepeatedField(localFieldDescriptor, localObject2);
        }
        paramCodedInputStream.popLimit(i1);
      }
      else
      {
        Message.Builder localBuilder;
        Object localObject1;
        switch (AbstractMessage.1.$SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[localFieldDescriptor.getType().ordinal()])
        {
        case 1:
          if (localMessage != null)
            localBuilder = localMessage.newBuilderForType();
          else
            localBuilder = paramBuilder1.newBuilderForField(localFieldDescriptor);
          if (!localFieldDescriptor.isRepeated())
            localBuilder.mergeFrom((Message)paramBuilder1.getField(localFieldDescriptor));
          paramCodedInputStream.readGroup(localFieldDescriptor.getNumber(), localBuilder, paramExtensionRegistryLite);
          localObject1 = localBuilder.build();
          break;
        case 2:
          if (localMessage != null)
            localBuilder = localMessage.newBuilderForType();
          else
            localBuilder = paramBuilder1.newBuilderForField(localFieldDescriptor);
          if (!localFieldDescriptor.isRepeated())
            localBuilder.mergeFrom((Message)paramBuilder1.getField(localFieldDescriptor));
          paramCodedInputStream.readMessage(localBuilder, paramExtensionRegistryLite);
          localObject1 = localBuilder.build();
          break;
        case 3:
          int i2 = paramCodedInputStream.readEnum();
          localObject1 = localFieldDescriptor.getEnumType().findValueByNumber(i2);
          if (localObject1 == null)
          {
            paramBuilder.mergeVarintField(j, i2);
            return true;
          }
          break;
        default:
          localObject1 = FieldSet.readPrimitiveField(paramCodedInputStream, localFieldDescriptor.getLiteType());
        }
        if (localFieldDescriptor.isRepeated())
          paramBuilder1.addRepeatedField(localFieldDescriptor, localObject1);
        else
          paramBuilder1.setField(localFieldDescriptor, localObject1);
      }
      return true;
    }

    private static void mergeMessageSetExtensionFromCodedStream(CodedInputStream paramCodedInputStream, UnknownFieldSet.Builder paramBuilder, ExtensionRegistryLite paramExtensionRegistryLite, Message.Builder paramBuilder1)
      throws IOException
    {
      Descriptors.Descriptor localDescriptor = paramBuilder1.getDescriptorForType();
      int i = 0;
      ByteString localByteString = null;
      Message.Builder localBuilder = null;
      Descriptors.FieldDescriptor localFieldDescriptor = null;
      while (true)
      {
        int j = paramCodedInputStream.readTag();
        if (j == 0)
          break;
        if (j == WireFormat.MESSAGE_SET_TYPE_ID_TAG)
        {
          i = paramCodedInputStream.readUInt32();
          if (i != 0)
          {
            ExtensionRegistry.ExtensionInfo localExtensionInfo;
            if ((paramExtensionRegistryLite instanceof ExtensionRegistry))
              localExtensionInfo = ((ExtensionRegistry)paramExtensionRegistryLite).findExtensionByNumber(localDescriptor, i);
            else
              localExtensionInfo = null;
            if (localExtensionInfo != null)
            {
              localFieldDescriptor = localExtensionInfo.descriptor;
              localBuilder = localExtensionInfo.defaultInstance.newBuilderForType();
              Message localMessage = (Message)paramBuilder1.getField(localFieldDescriptor);
              if (localMessage != null)
                localBuilder.mergeFrom(localMessage);
              if (localByteString != null)
              {
                localBuilder.mergeFrom(CodedInputStream.newInstance(localByteString.newInput()));
                localByteString = null;
              }
            }
            else if (localByteString != null)
            {
              paramBuilder.mergeField(i, UnknownFieldSet.Field.newBuilder().addLengthDelimited(localByteString).build());
              localByteString = null;
            }
          }
        }
        else if (j == WireFormat.MESSAGE_SET_MESSAGE_TAG)
        {
          if (i == 0)
            localByteString = paramCodedInputStream.readBytes();
          else if (localBuilder == null)
            paramBuilder.mergeField(i, UnknownFieldSet.Field.newBuilder().addLengthDelimited(paramCodedInputStream.readBytes()).build());
          else
            paramCodedInputStream.readMessage(localBuilder, paramExtensionRegistryLite);
        }
        else
        {
          if (!paramCodedInputStream.skipField(j))
            break;
        }
      }
      paramCodedInputStream.checkLastTagWas(WireFormat.MESSAGE_SET_ITEM_END_TAG);
      if (localBuilder != null)
        paramBuilder1.setField(localFieldDescriptor, localBuilder.build());
    }

    public Builder mergeUnknownFields(UnknownFieldSet paramUnknownFieldSet)
    {
      setUnknownFields(UnknownFieldSet.newBuilder(getUnknownFields()).mergeFrom(paramUnknownFieldSet).build());
      return this;
    }

    protected static UninitializedMessageException newUninitializedMessageException(Message paramMessage)
    {
      return new UninitializedMessageException(findMissingFields(paramMessage));
    }

    private static List findMissingFields(Message paramMessage)
    {
      ArrayList localArrayList = new ArrayList();
      findMissingFields(paramMessage, "", localArrayList);
      return localArrayList;
    }

    private static void findMissingFields(Message paramMessage, String paramString, List paramList)
    {
      Iterator localIterator1 = paramMessage.getDescriptorForType().getFields().iterator();
      Object localObject1;
      while (localIterator1.hasNext())
      {
        localObject1 = (Descriptors.FieldDescriptor)localIterator1.next();
        if ((((Descriptors.FieldDescriptor)localObject1).isRequired()) && (!paramMessage.hasField((Descriptors.FieldDescriptor)localObject1)))
          paramList.add(paramString + ((Descriptors.FieldDescriptor)localObject1).getName());
      }
      localIterator1 = paramMessage.getAllFields().entrySet().iterator();
      while (localIterator1.hasNext())
      {
        localObject1 = (Map.Entry)localIterator1.next();
        Descriptors.FieldDescriptor localFieldDescriptor = (Descriptors.FieldDescriptor)((Map.Entry)localObject1).getKey();
        Object localObject2 = ((Map.Entry)localObject1).getValue();
        if (localFieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE)
          if (localFieldDescriptor.isRepeated())
          {
            int i = 0;
            Iterator localIterator2 = ((List)localObject2).iterator();
            while (localIterator2.hasNext())
            {
              Object localObject3 = localIterator2.next();
              findMissingFields((Message)localObject3, subMessagePrefix(paramString, localFieldDescriptor, i++), paramList);
            }
          }
          else if (paramMessage.hasField(localFieldDescriptor))
          {
            findMissingFields((Message)localObject2, subMessagePrefix(paramString, localFieldDescriptor, -1), paramList);
          }
      }
    }

    private static String subMessagePrefix(String paramString, Descriptors.FieldDescriptor paramFieldDescriptor, int paramInt)
    {
      StringBuilder localStringBuilder = new StringBuilder(paramString);
      if (paramFieldDescriptor.isExtension())
        localStringBuilder.append('(').append(paramFieldDescriptor.getFullName()).append(')');
      else
        localStringBuilder.append(paramFieldDescriptor.getName());
      if (paramInt != -1)
        localStringBuilder.append('[').append(paramInt).append(']');
      localStringBuilder.append('.');
      return localStringBuilder.toString();
    }

    public Builder mergeFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return (Builder)super.mergeFrom(paramByteString);
    }

    public Builder mergeFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return (Builder)super.mergeFrom(paramByteString, paramExtensionRegistryLite);
    }

    public Builder mergeFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return (Builder)super.mergeFrom(paramArrayOfByte);
    }

    public Builder mergeFrom(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws InvalidProtocolBufferException
    {
      return (Builder)super.mergeFrom(paramArrayOfByte, paramInt1, paramInt2);
    }

    public Builder mergeFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return (Builder)super.mergeFrom(paramArrayOfByte, paramExtensionRegistryLite);
    }

    public Builder mergeFrom(byte[] paramArrayOfByte, int paramInt1, int paramInt2, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return (Builder)super.mergeFrom(paramArrayOfByte, paramInt1, paramInt2, paramExtensionRegistryLite);
    }

    public Builder mergeFrom(InputStream paramInputStream)
      throws IOException
    {
      return (Builder)super.mergeFrom(paramInputStream);
    }

    public Builder mergeFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return (Builder)super.mergeFrom(paramInputStream, paramExtensionRegistryLite);
    }

    public boolean mergeDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      return super.mergeDelimitedFrom(paramInputStream);
    }

    public boolean mergeDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return super.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite);
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.protobuf.AbstractMessage
 * JD-Core Version:    0.6.2
 */