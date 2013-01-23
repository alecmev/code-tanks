package com.google.protobuf;

public abstract interface ProtocolMessageEnum extends Internal.EnumLite
{
  public abstract int getNumber();

  public abstract Descriptors.EnumValueDescriptor getValueDescriptor();

  public abstract Descriptors.EnumDescriptor getDescriptorForType();
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.protobuf.ProtocolMessageEnum
 * JD-Core Version:    0.6.2
 */