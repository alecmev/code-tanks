package com.google.protobuf;

public abstract interface RpcController
{
  public abstract void reset();

  public abstract boolean failed();

  public abstract String errorText();

  public abstract void startCancel();

  public abstract void setFailed(String paramString);

  public abstract boolean isCanceled();

  public abstract void notifyOnCancel(RpcCallback paramRpcCallback);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.protobuf.RpcController
 * JD-Core Version:    0.6.2
 */