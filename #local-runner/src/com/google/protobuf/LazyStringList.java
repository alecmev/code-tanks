package com.google.protobuf;

import java.util.List;

public abstract interface LazyStringList extends List
{
  public abstract ByteString getByteString(int paramInt);

  public abstract void add(ByteString paramByteString);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.protobuf.LazyStringList
 * JD-Core Version:    0.6.2
 */