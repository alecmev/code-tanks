package com.google.protobuf;

import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public abstract class GeneratedMessageLite extends AbstractMessageLite
  implements Serializable
{
  private static final long serialVersionUID = 1L;

  protected GeneratedMessageLite()
  {
  }

  protected GeneratedMessageLite(Builder paramBuilder)
  {
  }

  public static GeneratedExtension newSingularGeneratedExtension(MessageLite paramMessageLite1, Object paramObject, MessageLite paramMessageLite2, Internal.EnumLiteMap paramEnumLiteMap, int paramInt, WireFormat.FieldType paramFieldType)
  {
    return new GeneratedExtension(paramMessageLite1, paramObject, paramMessageLite2, new ExtensionDescriptor(paramEnumLiteMap, paramInt, paramFieldType, false, false, null), null);
  }

  public static GeneratedExtension newRepeatedGeneratedExtension(MessageLite paramMessageLite1, MessageLite paramMessageLite2, Internal.EnumLiteMap paramEnumLiteMap, int paramInt, WireFormat.FieldType paramFieldType, boolean paramBoolean)
  {
    List localList = Collections.emptyList();
    return new GeneratedExtension(paramMessageLite1, localList, paramMessageLite2, new ExtensionDescriptor(paramEnumLiteMap, paramInt, paramFieldType, true, paramBoolean, null), null);
  }

  protected Object writeReplace()
    throws ObjectStreamException
  {
    return new SerializedForm(this);
  }

  static final class SerializedForm
    implements Serializable
  {
    private static final long serialVersionUID = 0L;
    private String messageClassName;
    private byte[] asBytes;

    SerializedForm(MessageLite paramMessageLite)
    {
      this.messageClassName = paramMessageLite.getClass().getName();
      this.asBytes = paramMessageLite.toByteArray();
    }

    protected Object readResolve()
      throws ObjectStreamException
    {
      try
      {
        Class localClass = Class.forName(this.messageClassName);
        Method localMethod = localClass.getMethod("newBuilder", new Class[0]);
        MessageLite.Builder localBuilder = (MessageLite.Builder)localMethod.invoke(null, new Object[0]);
        localBuilder.mergeFrom(this.asBytes);
        return localBuilder.buildPartial();
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        throw new RuntimeException("Unable to find proto buffer class", localClassNotFoundException);
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        throw new RuntimeException("Unable to find newBuilder method", localNoSuchMethodException);
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        throw new RuntimeException("Unable to call newBuilder method", localIllegalAccessException);
      }
      catch (InvocationTargetException localInvocationTargetException)
      {
        throw new RuntimeException("Error calling newBuilder", localInvocationTargetException.getCause());
      }
      catch (InvalidProtocolBufferException localInvalidProtocolBufferException)
      {
        throw new RuntimeException("Unable to understand proto buffer", localInvalidProtocolBufferException);
      }
    }
  }

  public static final class GeneratedExtension
  {
    private final MessageLite containingTypeDefaultInstance;
    private final Object defaultValue;
    private final MessageLite messageDefaultInstance;
    private final GeneratedMessageLite.ExtensionDescriptor descriptor;

    private GeneratedExtension(MessageLite paramMessageLite1, Object paramObject, MessageLite paramMessageLite2, GeneratedMessageLite.ExtensionDescriptor paramExtensionDescriptor)
    {
      if (paramMessageLite1 == null)
        throw new IllegalArgumentException("Null containingTypeDefaultInstance");
      if ((paramExtensionDescriptor.getLiteType() == WireFormat.FieldType.MESSAGE) && (paramMessageLite2 == null))
        throw new IllegalArgumentException("Null messageDefaultInstance");
      this.containingTypeDefaultInstance = paramMessageLite1;
      this.defaultValue = paramObject;
      this.messageDefaultInstance = paramMessageLite2;
      this.descriptor = paramExtensionDescriptor;
    }

    public MessageLite getContainingTypeDefaultInstance()
    {
      return this.containingTypeDefaultInstance;
    }

    public int getNumber()
    {
      return this.descriptor.getNumber();
    }

    public MessageLite getMessageDefaultInstance()
    {
      return this.messageDefaultInstance;
    }
  }

  private static final class ExtensionDescriptor
    implements FieldSet.FieldDescriptorLite
  {
    private final Internal.EnumLiteMap enumTypeMap;
    private final int number;
    private final WireFormat.FieldType type;
    private final boolean isRepeated;
    private final boolean isPacked;

    private ExtensionDescriptor(Internal.EnumLiteMap paramEnumLiteMap, int paramInt, WireFormat.FieldType paramFieldType, boolean paramBoolean1, boolean paramBoolean2)
    {
      this.enumTypeMap = paramEnumLiteMap;
      this.number = paramInt;
      this.type = paramFieldType;
      this.isRepeated = paramBoolean1;
      this.isPacked = paramBoolean2;
    }

    public int getNumber()
    {
      return this.number;
    }

    public WireFormat.FieldType getLiteType()
    {
      return this.type;
    }

    public WireFormat.JavaType getLiteJavaType()
    {
      return this.type.getJavaType();
    }

    public boolean isRepeated()
    {
      return this.isRepeated;
    }

    public boolean isPacked()
    {
      return this.isPacked;
    }

    public Internal.EnumLiteMap getEnumType()
    {
      return this.enumTypeMap;
    }

    public MessageLite.Builder internalMergeFrom(MessageLite.Builder paramBuilder, MessageLite paramMessageLite)
    {
      return ((GeneratedMessageLite.Builder)paramBuilder).mergeFrom((GeneratedMessageLite)paramMessageLite);
    }

    public int compareTo(ExtensionDescriptor paramExtensionDescriptor)
    {
      return this.number - paramExtensionDescriptor.number;
    }
  }

  public static abstract class ExtendableBuilder extends GeneratedMessageLite.Builder
    implements GeneratedMessageLite.ExtendableMessageOrBuilder
  {
    private FieldSet extensions = FieldSet.emptySet();
    private boolean extensionsIsMutable;

    public ExtendableBuilder clear()
    {
      this.extensions.clear();
      this.extensionsIsMutable = false;
      return (ExtendableBuilder)super.clear();
    }

    private void ensureExtensionsIsMutable()
    {
      if (!this.extensionsIsMutable)
      {
        this.extensions = this.extensions.clone();
        this.extensionsIsMutable = true;
      }
    }

    private FieldSet buildExtensions()
    {
      this.extensions.makeImmutable();
      this.extensionsIsMutable = false;
      return this.extensions;
    }

    private void verifyExtensionContainingType(GeneratedMessageLite.GeneratedExtension paramGeneratedExtension)
    {
      if (paramGeneratedExtension.getContainingTypeDefaultInstance() != getDefaultInstanceForType())
        throw new IllegalArgumentException("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings.");
    }

    public final boolean hasExtension(GeneratedMessageLite.GeneratedExtension paramGeneratedExtension)
    {
      verifyExtensionContainingType(paramGeneratedExtension);
      return this.extensions.hasField(paramGeneratedExtension.descriptor);
    }

    public final int getExtensionCount(GeneratedMessageLite.GeneratedExtension paramGeneratedExtension)
    {
      verifyExtensionContainingType(paramGeneratedExtension);
      return this.extensions.getRepeatedFieldCount(paramGeneratedExtension.descriptor);
    }

    public final Object getExtension(GeneratedMessageLite.GeneratedExtension paramGeneratedExtension)
    {
      verifyExtensionContainingType(paramGeneratedExtension);
      Object localObject = this.extensions.getField(paramGeneratedExtension.descriptor);
      if (localObject == null)
        return paramGeneratedExtension.defaultValue;
      return localObject;
    }

    public final Object getExtension(GeneratedMessageLite.GeneratedExtension paramGeneratedExtension, int paramInt)
    {
      verifyExtensionContainingType(paramGeneratedExtension);
      return this.extensions.getRepeatedField(paramGeneratedExtension.descriptor, paramInt);
    }

    public ExtendableBuilder clone()
    {
      throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
    }

    public final ExtendableBuilder setExtension(GeneratedMessageLite.GeneratedExtension paramGeneratedExtension, Object paramObject)
    {
      verifyExtensionContainingType(paramGeneratedExtension);
      ensureExtensionsIsMutable();
      this.extensions.setField(paramGeneratedExtension.descriptor, paramObject);
      return this;
    }

    public final ExtendableBuilder setExtension(GeneratedMessageLite.GeneratedExtension paramGeneratedExtension, int paramInt, Object paramObject)
    {
      verifyExtensionContainingType(paramGeneratedExtension);
      ensureExtensionsIsMutable();
      this.extensions.setRepeatedField(paramGeneratedExtension.descriptor, paramInt, paramObject);
      return this;
    }

    public final ExtendableBuilder addExtension(GeneratedMessageLite.GeneratedExtension paramGeneratedExtension, Object paramObject)
    {
      verifyExtensionContainingType(paramGeneratedExtension);
      ensureExtensionsIsMutable();
      this.extensions.addRepeatedField(paramGeneratedExtension.descriptor, paramObject);
      return this;
    }

    public final ExtendableBuilder clearExtension(GeneratedMessageLite.GeneratedExtension paramGeneratedExtension)
    {
      verifyExtensionContainingType(paramGeneratedExtension);
      ensureExtensionsIsMutable();
      this.extensions.clearField(paramGeneratedExtension.descriptor);
      return this;
    }

    protected boolean extensionsAreInitialized()
    {
      return this.extensions.isInitialized();
    }

    protected boolean parseUnknownField(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite, int paramInt)
      throws IOException
    {
      int i = WireFormat.getTagWireType(paramInt);
      int j = WireFormat.getTagFieldNumber(paramInt);
      GeneratedMessageLite.GeneratedExtension localGeneratedExtension = paramExtensionRegistryLite.findLiteExtensionByNumber(getDefaultInstanceForType(), j);
      int k = 0;
      int m = 0;
      if (localGeneratedExtension == null)
        k = 1;
      else if (i == FieldSet.getWireFormatForFieldType(localGeneratedExtension.descriptor.getLiteType(), false))
        m = 0;
      else if ((GeneratedMessageLite.GeneratedExtension.access$100(localGeneratedExtension).isRepeated) && (GeneratedMessageLite.GeneratedExtension.access$100(localGeneratedExtension).type.isPackable()) && (i == FieldSet.getWireFormatForFieldType(localGeneratedExtension.descriptor.getLiteType(), true)))
        m = 1;
      else
        k = 1;
      if (k != 0)
        return paramCodedInputStream.skipField(paramInt);
      Object localObject2;
      if (m != 0)
      {
        int n = paramCodedInputStream.readRawVarint32();
        int i1 = paramCodedInputStream.pushLimit(n);
        if (localGeneratedExtension.descriptor.getLiteType() == WireFormat.FieldType.ENUM)
          while (paramCodedInputStream.getBytesUntilLimit() > 0)
          {
            int i3 = paramCodedInputStream.readEnum();
            Internal.EnumLite localEnumLite = localGeneratedExtension.descriptor.getEnumType().findValueByNumber(i3);
            if (localEnumLite == null)
              return true;
            ensureExtensionsIsMutable();
            this.extensions.addRepeatedField(localGeneratedExtension.descriptor, localEnumLite);
          }
        while (paramCodedInputStream.getBytesUntilLimit() > 0)
        {
          localObject2 = FieldSet.readPrimitiveField(paramCodedInputStream, localGeneratedExtension.descriptor.getLiteType());
          ensureExtensionsIsMutable();
          this.extensions.addRepeatedField(localGeneratedExtension.descriptor, localObject2);
        }
        paramCodedInputStream.popLimit(i1);
      }
      else
      {
        Object localObject1;
        switch (GeneratedMessageLite.1.$SwitchMap$com$google$protobuf$WireFormat$JavaType[localGeneratedExtension.descriptor.getLiteJavaType().ordinal()])
        {
        case 1:
          MessageLite.Builder localBuilder = null;
          if (!localGeneratedExtension.descriptor.isRepeated())
          {
            localObject2 = (MessageLite)this.extensions.getField(localGeneratedExtension.descriptor);
            if (localObject2 != null)
              localBuilder = ((MessageLite)localObject2).toBuilder();
          }
          if (localBuilder == null)
            localBuilder = localGeneratedExtension.messageDefaultInstance.newBuilderForType();
          if (localGeneratedExtension.descriptor.getLiteType() == WireFormat.FieldType.GROUP)
            paramCodedInputStream.readGroup(localGeneratedExtension.getNumber(), localBuilder, paramExtensionRegistryLite);
          else
            paramCodedInputStream.readMessage(localBuilder, paramExtensionRegistryLite);
          localObject1 = localBuilder.build();
          break;
        case 2:
          int i2 = paramCodedInputStream.readEnum();
          localObject1 = localGeneratedExtension.descriptor.getEnumType().findValueByNumber(i2);
          if (localObject1 == null)
            return true;
          break;
        default:
          localObject1 = FieldSet.readPrimitiveField(paramCodedInputStream, localGeneratedExtension.descriptor.getLiteType());
        }
        if (localGeneratedExtension.descriptor.isRepeated())
        {
          ensureExtensionsIsMutable();
          this.extensions.addRepeatedField(localGeneratedExtension.descriptor, localObject1);
        }
        else
        {
          ensureExtensionsIsMutable();
          this.extensions.setField(localGeneratedExtension.descriptor, localObject1);
        }
      }
      return true;
    }

    protected final void mergeExtensionFields(GeneratedMessageLite.ExtendableMessage paramExtendableMessage)
    {
      ensureExtensionsIsMutable();
      this.extensions.mergeFrom(GeneratedMessageLite.ExtendableMessage.access$300(paramExtendableMessage));
    }
  }

  public static abstract class ExtendableMessage extends GeneratedMessageLite
    implements GeneratedMessageLite.ExtendableMessageOrBuilder
  {
    private final FieldSet extensions;

    protected ExtendableMessage()
    {
      this.extensions = FieldSet.newFieldSet();
    }

    protected ExtendableMessage(GeneratedMessageLite.ExtendableBuilder paramExtendableBuilder)
    {
      this.extensions = paramExtendableBuilder.buildExtensions();
    }

    private void verifyExtensionContainingType(GeneratedMessageLite.GeneratedExtension paramGeneratedExtension)
    {
      if (paramGeneratedExtension.getContainingTypeDefaultInstance() != getDefaultInstanceForType())
        throw new IllegalArgumentException("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings.");
    }

    public final boolean hasExtension(GeneratedMessageLite.GeneratedExtension paramGeneratedExtension)
    {
      verifyExtensionContainingType(paramGeneratedExtension);
      return this.extensions.hasField(paramGeneratedExtension.descriptor);
    }

    public final int getExtensionCount(GeneratedMessageLite.GeneratedExtension paramGeneratedExtension)
    {
      verifyExtensionContainingType(paramGeneratedExtension);
      return this.extensions.getRepeatedFieldCount(paramGeneratedExtension.descriptor);
    }

    public final Object getExtension(GeneratedMessageLite.GeneratedExtension paramGeneratedExtension)
    {
      verifyExtensionContainingType(paramGeneratedExtension);
      Object localObject = this.extensions.getField(paramGeneratedExtension.descriptor);
      if (localObject == null)
        return paramGeneratedExtension.defaultValue;
      return localObject;
    }

    public final Object getExtension(GeneratedMessageLite.GeneratedExtension paramGeneratedExtension, int paramInt)
    {
      verifyExtensionContainingType(paramGeneratedExtension);
      return this.extensions.getRepeatedField(paramGeneratedExtension.descriptor, paramInt);
    }

    protected boolean extensionsAreInitialized()
    {
      return this.extensions.isInitialized();
    }

    protected ExtensionWriter newExtensionWriter()
    {
      return new ExtensionWriter(false, null);
    }

    protected ExtensionWriter newMessageSetExtensionWriter()
    {
      return new ExtensionWriter(true, null);
    }

    protected int extensionsSerializedSize()
    {
      return this.extensions.getSerializedSize();
    }

    protected int extensionsSerializedSizeAsMessageSet()
    {
      return this.extensions.getMessageSetSerializedSize();
    }

    protected class ExtensionWriter
    {
      private final Iterator iter = GeneratedMessageLite.ExtendableMessage.this.extensions.iterator();
      private Map.Entry next;
      private final boolean messageSetWireFormat;

      private ExtensionWriter(boolean arg2)
      {
        if (this.iter.hasNext())
          this.next = ((Map.Entry)this.iter.next());
        boolean bool;
        this.messageSetWireFormat = bool;
      }

      public void writeUntil(int paramInt, CodedOutputStream paramCodedOutputStream)
        throws IOException
      {
        while ((this.next != null) && (((GeneratedMessageLite.ExtensionDescriptor)this.next.getKey()).getNumber() < paramInt))
        {
          GeneratedMessageLite.ExtensionDescriptor localExtensionDescriptor = (GeneratedMessageLite.ExtensionDescriptor)this.next.getKey();
          if ((this.messageSetWireFormat) && (localExtensionDescriptor.getLiteJavaType() == WireFormat.JavaType.MESSAGE) && (!localExtensionDescriptor.isRepeated()))
            paramCodedOutputStream.writeMessageSetExtension(localExtensionDescriptor.getNumber(), (MessageLite)this.next.getValue());
          else
            FieldSet.writeField(localExtensionDescriptor, this.next.getValue(), paramCodedOutputStream);
          if (this.iter.hasNext())
            this.next = ((Map.Entry)this.iter.next());
          else
            this.next = null;
        }
      }
    }
  }

  public static abstract interface ExtendableMessageOrBuilder extends MessageLiteOrBuilder
  {
    public abstract boolean hasExtension(GeneratedMessageLite.GeneratedExtension paramGeneratedExtension);

    public abstract int getExtensionCount(GeneratedMessageLite.GeneratedExtension paramGeneratedExtension);

    public abstract Object getExtension(GeneratedMessageLite.GeneratedExtension paramGeneratedExtension);

    public abstract Object getExtension(GeneratedMessageLite.GeneratedExtension paramGeneratedExtension, int paramInt);
  }

  public static abstract class Builder extends AbstractMessageLite.Builder
  {
    public Builder clear()
    {
      return this;
    }

    public Builder clone()
    {
      throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
    }

    public abstract Builder mergeFrom(GeneratedMessageLite paramGeneratedMessageLite);

    public abstract GeneratedMessageLite getDefaultInstanceForType();

    protected boolean parseUnknownField(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite, int paramInt)
      throws IOException
    {
      return paramCodedInputStream.skipField(paramInt);
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.protobuf.GeneratedMessageLite
 * JD-Core Version:    0.6.2
 */