package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;

@Beta
@GwtCompatible
public class ExecutionError extends Error
{
  private static final long serialVersionUID = 0L;

  protected ExecutionError()
  {
  }

  protected ExecutionError(String paramString)
  {
    super(paramString);
  }

  public ExecutionError(String paramString, Error paramError)
  {
    super(paramString, paramError);
  }

  public ExecutionError(Error paramError)
  {
    super(paramError);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.util.concurrent.ExecutionError
 * JD-Core Version:    0.6.2
 */