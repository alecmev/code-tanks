package com.google.protobuf;

public abstract interface Service
{
  public abstract Descriptors.ServiceDescriptor getDescriptorForType();

  public abstract void callMethod(Descriptors.MethodDescriptor paramMethodDescriptor, RpcController paramRpcController, Message paramMessage, RpcCallback paramRpcCallback);

  public abstract Message getRequestPrototype(Descriptors.MethodDescriptor paramMethodDescriptor);

  public abstract Message getResponsePrototype(Descriptors.MethodDescriptor paramMethodDescriptor);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.protobuf.Service
 * JD-Core Version:    0.6.2
 */