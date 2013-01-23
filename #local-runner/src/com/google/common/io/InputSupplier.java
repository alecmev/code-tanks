package com.google.common.io;

import java.io.IOException;

public abstract interface InputSupplier
{
  public abstract Object getInput()
    throws IOException;
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.io.InputSupplier
 * JD-Core Version:    0.6.2
 */