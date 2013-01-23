package com.google.protobuf;

public abstract interface BlockingRpcChannel
{
  public abstract Message callBlockingMethod(Descriptors.MethodDescriptor paramMethodDescriptor, RpcController paramRpcController, Message paramMessage1, Message paramMessage2)
    throws ServiceException;
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.protobuf.BlockingRpcChannel
 * JD-Core Version:    0.6.2
 */