package com.google.protobuf;

public abstract interface BlockingService
{
  public abstract Descriptors.ServiceDescriptor getDescriptorForType();

  public abstract Message callBlockingMethod(Descriptors.MethodDescriptor paramMethodDescriptor, RpcController paramRpcController, Message paramMessage)
    throws ServiceException;

  public abstract Message getRequestPrototype(Descriptors.MethodDescriptor paramMethodDescriptor);

  public abstract Message getResponsePrototype(Descriptors.MethodDescriptor paramMethodDescriptor);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.protobuf.BlockingService
 * JD-Core Version:    0.6.2
 */