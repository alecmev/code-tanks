package com.google.common.io;

import com.google.common.annotations.Beta;
import java.io.IOException;

@Beta
public abstract interface LineProcessor
{
  public abstract boolean processLine(String paramString)
    throws IOException;

  public abstract Object getResult();
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.io.LineProcessor
 * JD-Core Version:    0.6.2
 */