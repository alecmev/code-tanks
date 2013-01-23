package org.slf4j;

public abstract interface Logger
{
  public abstract void debug(String paramString);

  public abstract void info(String paramString);

  public abstract void warn(String paramString);

  public abstract void warn(String paramString, Throwable paramThrowable);

  public abstract void error(String paramString);

  public abstract void error(String paramString, Throwable paramThrowable);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.slf4j.Logger
 * JD-Core Version:    0.6.2
 */