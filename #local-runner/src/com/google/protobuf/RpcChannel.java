package com.google.protobuf;

public abstract interface RpcChannel
{
  public abstract void callMethod(Descriptors.MethodDescriptor paramMethodDescriptor, RpcController paramRpcController, Message paramMessage1, Message paramMessage2, RpcCallback paramRpcCallback);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.protobuf.RpcChannel
 * JD-Core Version:    0.6.2
 */