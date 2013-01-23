package com.google.protobuf;

public final class RpcUtil
{
  public static RpcCallback specializeCallback(RpcCallback paramRpcCallback)
  {
    return paramRpcCallback;
  }

  public static RpcCallback generalizeCallback(final RpcCallback paramRpcCallback, Class paramClass, final Message paramMessage)
  {
    return new RpcCallback()
    {
      public void run(Message paramAnonymousMessage)
      {
        Message localMessage;
        try
        {
          localMessage = (Message)this.val$originalClass.cast(paramAnonymousMessage);
        }
        catch (ClassCastException localClassCastException)
        {
          localMessage = RpcUtil.copyAsType(paramMessage, paramAnonymousMessage);
        }
        paramRpcCallback.run(localMessage);
      }
    };
  }

  private static Message copyAsType(Message paramMessage1, Message paramMessage2)
  {
    return paramMessage1.newBuilderForType().mergeFrom(paramMessage2).build();
  }

  public static RpcCallback newOneTimeCallback(RpcCallback paramRpcCallback)
  {
    return new RpcCallback()
    {
      private boolean alreadyCalled = false;

      public void run(Object paramAnonymousObject)
      {
        synchronized (this)
        {
          if (this.alreadyCalled)
            throw new RpcUtil.AlreadyCalledException();
          this.alreadyCalled = true;
        }
        this.val$originalCallback.run(paramAnonymousObject);
      }
    };
  }

  public static final class AlreadyCalledException extends RuntimeException
  {
    private static final long serialVersionUID = 5469741279507848266L;

    public AlreadyCalledException()
    {
      super();
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.protobuf.RpcUtil
 * JD-Core Version:    0.6.2
 */