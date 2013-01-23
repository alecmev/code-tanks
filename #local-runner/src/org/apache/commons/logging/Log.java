package org.apache.commons.logging;

public abstract interface Log
{
  public abstract boolean isDebugEnabled();

  public abstract boolean isErrorEnabled();

  public abstract boolean isInfoEnabled();

  public abstract boolean isWarnEnabled();

  public abstract void debug(Object paramObject);

  public abstract void debug(Object paramObject, Throwable paramThrowable);

  public abstract void info(Object paramObject);

  public abstract void warn(Object paramObject);

  public abstract void warn(Object paramObject, Throwable paramThrowable);

  public abstract void error(Object paramObject);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.commons.logging.Log
 * JD-Core Version:    0.6.2
 */