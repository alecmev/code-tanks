package com.google.common.io;

import com.google.common.annotations.Beta;
import java.io.IOException;

@Beta
public abstract interface ByteProcessor
{
  public abstract boolean processBytes(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException;

  public abstract Object getResult();
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.io.ByteProcessor
 * JD-Core Version:    0.6.2
 */