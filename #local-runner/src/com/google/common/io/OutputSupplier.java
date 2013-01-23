package com.google.common.io;

import java.io.IOException;

public abstract interface OutputSupplier
{
  public abstract Object getOutput()
    throws IOException;
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.io.OutputSupplier
 * JD-Core Version:    0.6.2
 */