package com.google.protobuf;

import java.util.Map;

public abstract interface MessageOrBuilder extends MessageLiteOrBuilder
{
  public abstract Message getDefaultInstanceForType();

  public abstract Descriptors.Descriptor getDescriptorForType();

  public abstract Map getAllFields();

  public abstract boolean hasField(Descriptors.FieldDescriptor paramFieldDescriptor);

  public abstract Object getField(Descriptors.FieldDescriptor paramFieldDescriptor);

  public abstract int getRepeatedFieldCount(Descriptors.FieldDescriptor paramFieldDescriptor);

  public abstract Object getRepeatedField(Descriptors.FieldDescriptor paramFieldDescriptor, int paramInt);

  public abstract UnknownFieldSet getUnknownFields();
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.protobuf.MessageOrBuilder
 * JD-Core Version:    0.6.2
 */